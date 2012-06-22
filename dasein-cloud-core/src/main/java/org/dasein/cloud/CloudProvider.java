/**
 * Copyright (C) 2009-2012 enStratus Networks Inc.
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.dasein.cloud;

import java.util.Properties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.admin.AdminServices;
import org.dasein.cloud.compute.ComputeServices;
import org.dasein.cloud.dc.DataCenterServices;
import org.dasein.cloud.identity.IdentityServices;
import org.dasein.cloud.network.NetworkServices;
import org.dasein.cloud.platform.PlatformServices;
import org.dasein.cloud.storage.StorageServices;
import org.dasein.util.CalendarWrapper;

/**
 * <p>
 * Represents a provider of cloud services. The cloud provider API prescribes a
 * number of services which may or may not be implemented for a given cloud. In addition,
 * the cloud provider API describes the data center structure of the underlying cloud through
 * the concept of regions. Each provider must have at least one region, which in turn has
 * at least one zone or data center.
 * </p>
 * <p>
 * This API specifies a number of services that a given cloud provider may implement, but many
 * cloud providers will not actually implement all of them. If a given service is not implemented,
 * the API call for gaining access to that services should return <code>null</code>. An application
 * should therefore test whether a service is <code>null</code> before trying to trigger operations
 * in that cloud.
 * </p>
 * <p>
 * When implementing a given service for a particular provider, that provider may not support
 * some of the operations of the service. Such methods should throw an 
 * {@link org.dasein.cloud.OperationNotSupportedException} to flag the lack of support.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public abstract class CloudProvider {
    private CloudProvider computeCloud = null;
    private ProviderContext context = null;
    
    private int     holdCount = 0;
    
    /**
     * Base contructor for a cloud provider.
     */
    public CloudProvider() { }
    
    /**
     * Empties out all credentials and removes any other context information from the cloud provider
     * implementation.
     */
    public void close() {
        int h;
        
        synchronized( this ) {
            h = holdCount;
        }
        if( h > 0 ) {
            Thread t = new Thread() {
                public void run() {
                    waitForHold();
                }
            };
            
            t.setName("Close Hold for " + this);
            t.setDaemon(true);
            t.start();
        }
        else {
            waitForHold();
        }
    }
    
    /**
     * Called to initialize a cloud provider with an operational context. The operational context
     * includes authentication information, the regional context, and any cloud-specific
     * context. Prior to initializing itself, this method will close out any existing state.
     * @param context the context for services calls using this provider instance
     */
    public final void connect(@Nonnull ProviderContext context) {
        connect(context, null);
    }
    
    /**
     * Called to initialize a cloud provider with an operational context. The operational context
     * includes authentication information, the regional context, and any cloud-specific
     * context. Prior to initializing itself, this method will close out any existing state.
     * @param context the context for services calls using this provider instance
     * @param computeProvider the compute context if this is a storage-only cloud (the compute context controls the connection)
     */
    public final void connect(@Nonnull ProviderContext context, @Nullable CloudProvider computeProvider) {
        close();
        this.context = context;
        this.computeCloud = computeProvider;
    }

    public abstract @Nullable AdminServices getAdminServices();

    /**
     * If this is a pure storage implementation that is being paired with a compute implementation,
     * the compute implementation holds precedence. This value references the compute provider
     * behind this storage provider
     * @return the compute provider (if any) behind this storage provider
     */
    public final CloudProvider getComputeCloud() {
        return computeCloud;
    }
    
    /**
     * This value will be null unless {@link #connect(ProviderContext)} has been called.
     * @return the operational context for this instance of this provider implementation
     */
    public final @Nullable ProviderContext getContext() { return context; }
    
    /**
     * This value can be the same as {@link #getProviderName()} if it is not a multi-cloud provider. 
     * @return the name of the cloud
     */
    public abstract @Nonnull String getCloudName();
    
    /**
     * Provides access to the data center services that describe the physical structure of the underlying cloud provider.
     * @return an implementation of the {@link org.dasein.cloud.dc.DataCenterServices} API
     */
    public abstract @Nonnull DataCenterServices getDataCenterServices();
    
    public abstract @Nullable ComputeServices getComputeServices();
    
    public abstract @Nullable IdentityServices getIdentityServices();

    public abstract @Nullable NetworkServices getNetworkServices();
    
    public abstract @Nullable PlatformServices getPlatformServices();
    
    /**
     * @return the name of this cloud provider
     */
    public abstract @Nonnull String getProviderName();
    
    private CloudProvider storageCloudProvider = null;
    
    /**
     * Provides access to the cloud storage services supported by this cloud provider.
     * @return an implementation of the {@link org.dasein.cloud.storage.StorageServices} API
     */
    public synchronized @Nullable StorageServices getStorageServices() {
        if( storageCloudProvider != null ) {
            return storageCloudProvider.getStorageServices();
        }
        ProviderContext computeContext = getContext();
        String storage = computeContext.getStorage();

        if( storage == null ) {
            return null;
        }
        try { 
            CloudProvider p = (CloudProvider)Class.forName(storage).newInstance();
            ProviderContext ctx = new ProviderContext();
            Properties props = computeContext.getStorageCustomProperties();
            
            ctx.setRegionId(computeContext.getRegionId());
            ctx.setCloudName(computeContext.getCloudName());
            ctx.setProviderName(computeContext.getProviderName());
            ctx.setEffectiveAccountNumber(computeContext.getEffectiveAccountNumber());
            ctx.setEndpoint(computeContext.getStorageEndpoint());
            ctx.setAccountNumber(computeContext.getStorageAccountNumber());
            ctx.setAccessKeys(computeContext.getStoragePublic(), computeContext.getStoragePrivate());
            ctx.setX509Cert(computeContext.getStorageX509Cert());
            ctx.setX509Key(computeContext.getStorageX509Key());
            ctx.setCustomProperties(props == null ? new Properties() : props);
            p.connect(ctx, this);
            storageCloudProvider = p;
            return p.getStorageServices();
        }
        catch( Throwable t ) {
            t.printStackTrace();
            return null;
        }
    }
    
    public boolean hasComputeServices() {
        return (getComputeServices() != null);
    }
    
    public boolean hasIdentityServices() {
        return (getIdentityServices() != null);
    }
    
    public boolean hasNetworkServices() {
        return (getNetworkServices() != null);
    }
    
    public boolean hasPlatformServices() {
        return (getPlatformServices() != null);
    }
    
    public boolean hasStorageServices() {
        return (getStorageServices() != null);
    }
    
    public void hold() {
        if( computeCloud != null ) {
            computeCloud.hold();
        }
        else {
            synchronized( this ) {
                holdCount++;
            }
        }
    }
    
    public synchronized boolean isConnected() {
        return (context != null);
    }
    
    public void release() {
        if( computeCloud != null ) {
            computeCloud.release();
        }
        else {
            synchronized( this ) {
                holdCount--;
                notifyAll();
            }
        }
    }
    
    /**
     * Tests the validity of the current context and returns the true account identifier for this context.
     * In general, this value will make some kind of connection to the cloud provider using the established
     * context to verify the credentials are right and then return {@link ProviderContext#getAccountNumber()}.
     * In some cases, however, the actual provider account number may differ from the one visible to the 
     * user. For those scenarios, the return value will return the true account number and not the one the
     * user thinks it is. If the connection fails for any reason, this method should return <code>null</code>
     * to indicate the failure.
     * @return On success, the true account number for the account. On failure, <code>null</code>.
     */
    public String testContext() {
        return null;
    }
    
    private void waitForHold() {
        long timeout = System.currentTimeMillis() + (CalendarWrapper.MINUTE * 20L);
        
        while( timeout > System.currentTimeMillis() ) {
            synchronized( this ) {
                if( holdCount < 1 ) {
                    break;
                }
            }
            try { Thread.sleep(1000L); }
            catch( InterruptedException e ) { }
        }
        if( context != null) {
            context.clear();
            context = null;
        }
        if( storageCloudProvider != null ) {
            storageCloudProvider.close();
            storageCloudProvider = null;
        }
    }
}
