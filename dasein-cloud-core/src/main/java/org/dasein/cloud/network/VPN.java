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

public class VPN implements Serializable {
    private static final long serialVersionUID = 5679874407718228979L;
    
    private VPNState           currentState;
    private String             description;
    private String             name;
    private VPNProtocol        protocol;
    private String             providerDataCenterId;
    private String             providerRegionId;
    private String             providerVlanId;
    private String             providerVpnId;
    private Map<String,String> tags;
    
    public VPN() { }

    public VPNState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VPNState currentState) {
        this.currentState = currentState;
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

    public VPNProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(VPNProtocol protocol) {
        this.protocol = protocol;
    }

    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    public void setProviderDataCenterId(String providerDataCenterId) {
        this.providerDataCenterId = providerDataCenterId;
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

    public String getProviderVpnId() {
        return providerVpnId;
    }

    public void setProviderVpnId(String providerVpnId) {
        this.providerVpnId = providerVpnId;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    
    public String toString() {
        return providerVpnId;
    }
}
