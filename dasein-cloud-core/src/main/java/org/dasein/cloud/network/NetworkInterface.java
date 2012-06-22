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

public class NetworkInterface implements Serializable {
    private static final long serialVersionUID = 2920482312023383455L;
    
    private boolean defaultRoute;
    private String  gatewayAddress;
    private String  ipAddress;
    private String  netmask;
    private String  providerNetworkInterfaceId;
    private String  providerOwnerId;
    private String  providerRegionId;
    private String  providerVirtualMachineId;
    private String  providerVlanId;
    
    public NetworkInterface() { }

    public boolean equals(Object other) {
        if( other == null ) {
            return false;
        }
        if( other == this ) {
            return true;
        }
        if( !getClass().getName().equals(other.getClass().getName()) ) {
            return false;
        }
        NetworkInterface nic = (NetworkInterface)other;
        
        if( providerNetworkInterfaceId.equals(nic.providerNetworkInterfaceId) ) {
            if( providerVlanId.equals(nic.providerVlanId) ) {
                if( providerRegionId.equals(nic.providerRegionId) ) {
                    return providerOwnerId.equals(nic.providerOwnerId);
                }
            }
        }
        return false;   
    }
    
    public boolean isDefaultRoute() {
        return defaultRoute;
    }

    public void setDefaultRoute(boolean defaultRoute) {
        this.defaultRoute = defaultRoute;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getProviderNetworkInterfaceId() {
        return providerNetworkInterfaceId;
    }

    public void setProviderNetworkInterfaceId(String providerNetworkInterfaceId) {
        this.providerNetworkInterfaceId = providerNetworkInterfaceId;
    }

    public String getProviderVirtualMachineId() {
        return providerVirtualMachineId;
    }

    public void setProviderVirtualMachineId(String providerVirtualMachineId) {
        this.providerVirtualMachineId = providerVirtualMachineId;
    }

    public String getProviderVlanId() {
        return providerVlanId;
    }

    public void setProviderVlanId(String providerVlanId) {
        this.providerVlanId = providerVlanId;
    }
    
    public String toString() {
        return (ipAddress + " [" + providerNetworkInterfaceId + "]");
    }
}
