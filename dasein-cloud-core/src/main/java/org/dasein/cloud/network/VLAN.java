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
import java.util.Map;

public class VLAN implements Serializable {
    private static final long serialVersionUID = -6958694513309849814L;
    
    private String   cidr;
    private VLANState currentState;
    private String   description;
    private String[] dnsServers;
    private String   domainName;
    private String   gateway;
    private String   name;
    private String[] ntpServers;
    private String   providerDataCenterId;
    private String   providerOwnerId;
    private String   providerRegionId;
    private String   providerVlanId;
    private Map<String,String> tags;
    
    public VLAN() { }

    public boolean equals(Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( ob instanceof VLAN ) {
            VLAN v = (VLAN)ob;
            
            if( !providerOwnerId.equals(v.providerOwnerId) ) {
                return false;
            }
            return providerVlanId.equals(v.providerVlanId);
        }
        return false;
    }
    
    public int hashCode() {
        return (providerOwnerId + providerVlanId).hashCode();
    }
    
    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    public void setProviderDataCenterId(String providerDataCenterId) {
        this.providerDataCenterId = providerDataCenterId;
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

    public String getProviderVlanId() {
        return providerVlanId;
    }

    public void setProviderVlanId(String providerVlanId) {
        this.providerVlanId = providerVlanId;
    }
    
    public String toString() {
        return cidr + " [" + providerOwnerId + "/" + providerVlanId + "]";
    }

    public void setDnsServers(String[] dnsServers) {
        this.dnsServers = dnsServers;
    }

    public String[] getDnsServers() {
        return dnsServers;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getGateway() {
        return gateway;
    }

    public void setTags(Map<String,String> tags) {
        this.tags = tags;
    }

    public Map<String,String> getTags() {
        return tags;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setNtpServers(String[] ntpServers) {
        this.ntpServers = ntpServers;
    }

    public String[] getNtpServers() {
        return ntpServers;
    }

    public void setCurrentState(VLANState currentState) {
        this.currentState = currentState;
    }

    public VLANState getCurrentState() {
        return currentState;
    }

}
