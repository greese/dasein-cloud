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

package org.dasein.cloud.network;

import java.io.Serializable;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents an IP address that in some way belongs to a cloud account holder.
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class IpAddress implements Comparable<IpAddress>, Serializable {
    private static final long serialVersionUID = 4253666763509404379L;
    
    private String      address;
    private AddressType addressType;
    private String      providerIpAddressId;
    private String      providerLoadBalancerId;
    private String      regionId;
    private String      serverId;
    
    public IpAddress() { }
    
    /**
     * Sorts addresses based on their IP address.
     */
    public int compareTo(@Nonnull IpAddress other) {
        int x;
        
        if( other == this ) {
            return 0;
        }
        x = address.compareTo(other.address);
        if( x != 0 ) {
            return x;
        }
        return providerIpAddressId.compareTo(other.providerIpAddressId);
    }
    
    /**
     * Two addresses are equal if they have the same IP address and provider address IDs
     */
    public boolean equals(@CheckForNull Object ob) {
        IpAddress other;
        
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        other = (IpAddress)ob;
        return (address.equals(other.address) && providerIpAddressId.equals(other.providerIpAddressId));
    }
    
    /**
     * @return the IP address for this {@link IpAddress}
     */
    public @Nonnull String getAddress() {
        return address;
    }
    
    /**
     * @return the cloud provider's ID for uniquely identifying this address
     */
    public @Nonnull String getProviderIpAddressId() {
        return providerIpAddressId;
    }
    
    /**
     * @return the provider ID for the region in which this address may be assigned
     */
    public @Nonnull String getRegionId() {
        return regionId;
    }
    
    /**
     * @return the provider ID for the server to which this address is assigned (if any)
     */
    public @Nullable String getServerId() {
        return serverId;
    }
    
    /**
     * @return the provider ID for the load balancer to which this address is assigned (if any)
     */
    public @Nullable String getProviderLoadBalancerId() {
        return providerLoadBalancerId;
    }
    
    /**
     * @return the type of address (public or private) this address is
     */
    public @Nonnull AddressType getAddressType() {
        return addressType;
    }
    
    /**
     * @return <code>true</code> if this address is assigned to a server or load balancer
     */
    public boolean isAssigned() {
        return (serverId != null || providerLoadBalancerId != null);
    }
    
    public void setAddress(@Nonnull String address) {
        this.address = address;
    }
    
    public void setIpAddressId(@Nonnull String ipAddressId) {
        this.providerIpAddressId = ipAddressId;
    }
    
    public void setRegionId(@Nonnull String regionId) {
        this.regionId = regionId;
    }
    
    public void setServerId(@Nullable String serverId) {
        this.serverId = serverId;
    }
    
    public @Nonnull String toString() {
        return (address + " (" + providerIpAddressId + ")"); 
    }

    public void setProviderLoadBalancerId(@Nullable String providerLoadBalancerId) {
        this.providerLoadBalancerId = providerLoadBalancerId;
    }

    public void setAddressType(@Nonnull AddressType addressType) {
        this.addressType = addressType;
    }
}
