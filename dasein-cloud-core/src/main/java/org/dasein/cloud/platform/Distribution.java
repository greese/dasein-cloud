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

package org.dasein.cloud.platform;

import java.io.Serializable;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * An abstracted CDN distribution. With Dasein Cloud, a distribution represents a source of 
 * content distributed via a single DSN name. Different clouds may or may not support meta-data like
 * distribution names or retaining information about CNAME aliases. In addition, not all clouds 
 * necessarily support log files.
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class Distribution implements Serializable {
    private static final long serialVersionUID = 4868589965234336269L;
    
    private boolean  active;
    private String[] aliases;
    private boolean  deployed;
    private String   dnsName;
    private String   location;
    private String   logDirectory;
    private String   logName;
    private String   name;
    private String   providerDistributionId;
    private String   providerOwnerId;
    
    public Distribution() { }
    
    public boolean equals(@CheckForNull Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        Distribution other = (Distribution)ob;
        
        if( !providerOwnerId.equals(other.providerOwnerId) ) {
            return false;
        }
        return providerDistributionId.equals(other.providerDistributionId);
    }
    
    /**
     * A list of CNAME aliases that map to the {@link #getDnsName()}. You must independently make
     * sure that these aliases point to the distribution DNS name in your DNS server.
     * @return the list of aliases associated with this distribution
     */
    public @Nullable String[] getAliases() {
        return aliases;
    }

    /**
     * The main DNS name used to access the contents of the distribution. This value is set by your
     * cloud provider when you create the distribution.
     * @return the main DNS name used to access the contents of the distribution
     */
    public @Nullable String getDnsName() {
        return dnsName;
    }
    
    /**
     * The origin bucket/directory for your distribution content. You must create the bucket/directory
     * in the cloud storage driving your cloud CDN prior to creating a distribution.
     * @return the name of the origin bucket/directory in cloud storage
     */
    public @Nullable String getLocation() {
        return location;
    }
    
    /**
     * The user-friendly name for this distribution. Not all clouds store meta-data with their
     * CDN distribution records. In those cases, this value will be identical to the 
     * {@link #getProviderDistributionId()} value.
     * @return the user-friendly name for the distribution
     */
    public @Nullable String getName() {
        return name;
    }
    
    /**
     * The identifier that uniquely identifies this distribution to your cloud provider. This
     * value may be provider-generated or created by your Dasein Cloud implementation when
     * you create your distribution.
     * @return the identifier that uniquely identifiers the distribution to your cloud provider
     */
    public @Nullable String getProviderDistributionId() {
        return providerDistributionId;
    }
    
    /**
     * The bucket/directory in your cloud storage where logs are stored. If logging is not
     * enabled or your cloud provider does not support logging, this value will be <code>null</code>.
     * @return the bucket/directory in your cloud storage where logs are stored or <code>null</code>
     * if logging is not enabled or supported
     */
    public @Nullable String getLogDirectory() {
        return logDirectory;
    }
    
    /**
     * The name/prefix of your log files in cloud storage. If logging is not
     * enabled or your cloud provider does not support logging, this value will 
     * be <code>null</code>. The logs will be stored in the bucket/directory specified by
     * {@link #getLogDirectory()}.
     * @return the name/prefix of your log files in cloud storage. or <code>null</code>
     * if logging is not enabled or supported
     */
    public @Nullable String getLogName() {
        return logName;
    }
    
    /**
     * The account number or other account identifier that identifies the account with the 
     * cloud provider that owns this distribution. For example, your AWS account number or
     * your Rackspace login ID. 
     * @return the unique ID with the cloud provider of the account that owns this distribution
     */
    public @Nonnull String getProviderOwnerId() {
        return providerOwnerId;
    }
    
    /**
     * Specifies whether this distribution is active. A distribution can be active, but not deployed
     * in some cloud providers. Typically, a distribution is active when it is created but not
     * immediately deployed. After some period, it eventually enters into a deployed state meaning
     * you can retrieve content through the distribution.
     * @return true if the distribution is active
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * Specifies whether the distribution is in a deployed state. An inactive distribution is never
     * deployed, but an active distribution may be either deployed or not deployed. You can 
     * perform management operations on an active but not deployed distribution. A distribution
     * must be deployed, however, to retrieve content from it.
     * @return true if the distribution is deployed
     */
    public boolean isDeployed() {
        return deployed;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setAliases(@Nullable String[] aliases) {
        this.aliases = aliases;
    }
    
    public void setDeployed(boolean deployed) {
        this.deployed = deployed;
    }
    
    public void setDnsName(@Nonnull String dnsName) {
        this.dnsName = dnsName;
    }
    
    public void setName(@Nonnull String name) {
        this.name = name;
    }
    
    public void setLocation(@Nonnull String location) {
        this.location = location;
    }

    public void setProviderDistributionId(@Nonnull String distributionId) {
        this.providerDistributionId = distributionId;
    }

    public void setProviderOwnerId(@Nonnull String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }
    
    public void setLogDirectory(@Nullable String logDirectory) {
        this.logDirectory = logDirectory;
    }

    public void setLogName(@Nullable String logName) {
        this.logName = logName;
    }

    @Override
    public @Nonnull String toString() {
        return name;
    }
}
