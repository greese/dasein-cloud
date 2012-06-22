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

public class LoadBalancer implements Serializable {
    private static final long serialVersionUID = -2396639777723684882L;
    
    private String                  address;
    private LoadBalancerAddressType addressType;
    private long                    creationTimestamp;
    private LoadBalancerState       currentState;
    private String                  description;
    private LbListener[]            listeners;
    private String                  name;
    private String[]                providerDataCenterIds;
    private String                  providerLoadBalancerId;
    private String                  providerOwnerId;
    private String                  providerRegionId;
    private String[]                providerServerIds;
    private int[]                   publicPorts;
    
    public LoadBalancer() { }

    public boolean equals(Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        LoadBalancer other = (LoadBalancer)ob;
        if( !providerOwnerId.equals(other.providerOwnerId) ) {
            return false;
        }
        if( !providerRegionId.equals(other.providerRegionId) ) {
            return false;
        }
        return providerLoadBalancerId.equals(other.providerLoadBalancerId);
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LoadBalancerAddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(LoadBalancerAddressType addressType) {
        this.addressType = addressType;
    }

    public String[] getProviderDataCenterIds() {
        return providerDataCenterIds;
    }

    public void setProviderDataCenterIds(String[] providerDataCenterIds) {
        this.providerDataCenterIds = providerDataCenterIds;
    }

    public String getProviderLoadBalancerId() {
        return providerLoadBalancerId;
    }

    public void setProviderLoadBalancerId(String providerLoadBalancerId) {
        this.providerLoadBalancerId = providerLoadBalancerId;
    }

    public String getProviderOwnerId() {
        return providerOwnerId;
    }

    public void setProviderOwnerId(String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }

    public String getProviderRegionId() {
        return providerRegionId;
    }

    public void setProviderRegionId(String providerRegionId) {
        this.providerRegionId = providerRegionId;
    }

    public String[] getProviderServerIds() {
        return providerServerIds;
    }

    public void setProviderServerIds(String[] providerServerIds) {
        this.providerServerIds = providerServerIds;
    }

    public int[] getPublicPorts() {
        return publicPorts;
    }

    public void setPublicPorts(int[] publicPorts) {
        this.publicPorts = publicPorts;
    }

    public void setListeners(LbListener[] listeners) {
        this.listeners = listeners;
    }

    public LbListener[] getListeners() {
        return listeners;
    }

    public void setCurrentState(LoadBalancerState currentState) {
        this.currentState = currentState;
    }

    public LoadBalancerState getCurrentState() {
        return currentState;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
        return (name + " [" + address + "]");
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }
}
