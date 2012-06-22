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

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * The contextual information necessary for making calls to a cloud provider. Each provider requires
 * a provider context in order to connect to the target cloud account and perform operations within 
 * the cloud. This context includes the account number, operational region, and any number of
 * authentication keys. 
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class ProviderContext implements Serializable {
    private static final long serialVersionUID = 3503959903660616914L;
    
    static private final Random random = new Random();
    
    /**
     * Helper method for clearing out credentials with random data.
     * @param keys a list of credentials to fill with random data
     */
    static public void clear(byte[] ... keys) {
        if( keys != null ) {
            for( byte[] key : keys ) {
                if( key != null ) {
                    random.nextBytes(key);
                }
            }
        }
    }
    
    private byte[]     accessPrivate;
    private byte[]     accessPublic;
    private String     accountNumber;
    private String     cloudName;
    private Properties customProperties;
    private String     effectiveAccountNumber;
    private String     endpoint;
    private String     providerName;
    private String     regionId;
    private String     storage;
    private String     storageAccountNumber;
    private Properties storageCustomProperties;
    private String     storageEndpoint;
    private byte[]     storagePrivate;
    private byte[]     storagePublic;
    private byte[]     storageX509Cert;
    private byte[]     storageX509Key;
    private byte[]     x509Cert;
    private byte[]     x509Key;

    /**
     * Constructs a new, empty provider context for managing the provider context information.
     */
    public ProviderContext() { }

    /**
     * Constructs a new provider context for the specified account number in the specified region.
     * @param accountNumber the account number for the account in the cloud
     * @param inRegionId the region to be referenced by this provider context
     */
    public ProviderContext(@Nonnull String accountNumber, @Nonnull String inRegionId) {
        this.accountNumber = accountNumber;
        regionId = inRegionId;
    }
    
    /**
     * Clears out all keys being stored by this provider. The keys are overwritten with random data.
     * If these keys were set from instance variables in other objects, this method will result in
     * some peculiar side-effects.
     */
    public void clear() {
        clear(accessPublic, accessPrivate, storagePublic, storagePrivate, x509Cert, x509Key, storageX509Cert, storageX509Key);
    }
    
    /**
     * The private access key is the primary authentication password for web services calls.
     * In this AWS world, this is your access secret key.
     * @return the private access key for the cloud provider
     */
    public @Nullable byte[] getAccessPrivate() {
        return accessPrivate;
    }
    
    /**
     * The public access key is the primary authentication user ID for web services calls. In the
     * AWS world, this is your access public key.
     * @return the public access key for the cloud provider
     */
    public @Nullable byte[] getAccessPublic() {
        return accessPublic;
    }

    /**
     * @return the account number that identifies the account to the cloud provider independent of context
     */
    public @Nonnull String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Provides the name of the cloud being accessed through this API.
     * For a multi-cloud provider or a multi-cloud implementation of Dasein Cloud, it is useful
     * to have the cloud name configurable. 
     * @return the name of the underlying cloud
     */
    public @Nullable String getCloudName() {
    	return cloudName;
    }
    
    /**
     * Provides any properties specific to the underlying Dasein Cloud implementation that will
     * help it interact with thge underlying cloud.
     * @return any custom properties supporting cloud connectivity
     */
    public @Nullable Properties getCustomProperties() {
        if( customProperties == null ) {
            return new Properties();
        }
    	return customProperties;
    }
    
    /**
     * Provides the endpoint through which cloud API calls are routed. This is typically configured for
     * private clouds or cloud APIs that support more than one cloud.
     * @return the cloud API host
     */
    public @Nullable String getEndpoint() {
        return endpoint;
    }
    
    /**
     * Provides the name of the organization providing cloud services. This value is configurable
     * for multi-provider APIs like the AWS/Eucalyptus APIs.
     * @return the name of the organization providing the underlying cloud
     */
    public @Nullable String getProviderName() {
    	return providerName;
    }
    
    /**
     * @return the cloud's unique identified for the region in which this context is operating
     */
    public @Nullable String getRegionId() {
        return regionId;
    }

    /**
     * The storage private key is the private key used for cloud storage access. The AWS world has
     * no direct analog to this value, but it is used to represent the AWS private key.
     * @return the private key for cloud storage services
     */
    public @Nullable byte[] getStoragePrivate() {
        return storagePrivate;
    }
    
    /**
     * The storage public key is the public key used for cloud storage access. The AWS world has
     * no direct analog to this value, but it is used to represent the AWS certificate.
     * @return the public key for storafe
     */
    public @Nullable byte[] getStoragePublic() {
        return storagePublic;
    }
    
    /**
     * Establishes the access key values.
     * @param publicKey the public key part of the access key
     * @param privateKey the private key part of the access key
     */
    public void setAccessKeys(@Nullable byte[] publicKey, @Nullable byte[] privateKey) {
        accessPublic = publicKey;
        accessPrivate = privateKey;
    }
    
    /**
     * Sets the private access key.
     * @param accessPrivate the proper private access key value.
     */
    public void setAccessPrivate(@Nullable byte[] accessPrivate) {
        this.accessPrivate = accessPrivate;
    }
    
    /**
     * Sets the public access key.
     * @param accessPublic the proper public access key value
     */
    public void setAccessPublic(@Nullable byte[] accessPublic) {
        this.accessPublic = accessPublic;
    }
    
    /**
     * Sets the account number for this provider context.
     * @param accountNumber the account number associated with the provider account
     */
    public void setAccountNumber(@Nonnull String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    /**
     * Sets the name of the cloud supported by this context.
     * @param name the name of the underlying cloud
     */
    public void setCloudName(@Nullable String name) {
    	cloudName = name;
    }
    
    /**
     * Establishes any custom properties for use by the Dasein Cloud implementation.
     * @param properties custom properties for the Dasein Cloud implementation
     */
    public void setCustomProperties(@Nonnull Properties properties) {
    	customProperties = properties;
    }
    
    /**
     * Sets the cloud endpoint for the provider context.
     * @param endpoint the host to which cloud API calls are routed
     */
    public void setEndpoint(@Nullable String endpoint) {
        this.endpoint = endpoint;
    }
    
    /**
     * Sets the name of the organization providing cloud services.
     * @param name the name of the organization providing cloud services
     */
    public void setProviderName(@Nullable String name) {
    	providerName = name;
    }
    
    /**
     * Sets the region context for this provider context.
     * @param regionId the unique cloud ID for the region supporting this context
     */
    public void setRegionId(@Nullable String regionId) {
        this.regionId = regionId;
    }

    /**
     * Sets the storage keypair for this provider context.
     * @param publicKey the storage key public key
     * @param privateKey the storage key private key
     */
    public void setStorageKeys(@Nullable byte[] publicKey, @Nullable byte[] privateKey) {
        storagePublic = publicKey;
        storagePrivate = privateKey;
    }

    /**
     * Sets the storage private key.
     * @param storagePrivate the storage private key
     */
    public void setStoragePrivate(@Nullable byte[] storagePrivate) {
        this.storagePrivate = storagePrivate;
    }
    
    /**
     * Sets the storage public key value.
     * @param storagePublic the storage public key
     */
    public void setStoragePublic(@Nullable byte[] storagePublic) {
        this.storagePublic = storagePublic;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStorageAccountNumber() {
        return storageAccountNumber;
    }

    public void setStorageAccountNumber(String storageAccountNumber) {
        this.storageAccountNumber = storageAccountNumber;
    }

    public byte[] getX509Cert() {
        return x509Cert;
    }

    public void setX509Cert(byte[] x509Cert) {
        this.x509Cert = x509Cert;
    }

    public byte[] getX509Key() {
        return x509Key;
    }

    public void setX509Key(byte[] x509Key) {
        this.x509Key = x509Key;
    }

    public static Random getRandom() {
        return random;
    }

    public byte[] getStorageX509Cert() {
        return storageX509Cert;
    }

    public void setStorageX509Cert(byte[] storageX509Cert) {
        this.storageX509Cert = storageX509Cert;
    }

    public byte[] getStorageX509Key() {
        return storageX509Key;
    }

    public void setStorageX509Key(byte[] storageX509Key) {
        this.storageX509Key = storageX509Key;
    }

    public void setStorageEndpoint(String storageEndpoint) {
        this.storageEndpoint = storageEndpoint;
    }

    public String getStorageEndpoint() {
        return storageEndpoint;
    }

    public void setEffectiveAccountNumber(String effectiveAccountNumber) {
        this.effectiveAccountNumber = effectiveAccountNumber;
    }

    public String getEffectiveAccountNumber() {
        if( effectiveAccountNumber == null ) {
            return getAccountNumber();
        }
        return effectiveAccountNumber;
    }

    public void setStorageCustomProperties(Properties storageCustomProperties) {
        this.storageCustomProperties = storageCustomProperties;
    }

    public Properties getStorageCustomProperties() {
        return storageCustomProperties;
    }
}
