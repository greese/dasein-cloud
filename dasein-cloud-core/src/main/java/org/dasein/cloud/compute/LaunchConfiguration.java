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

package org.dasein.cloud.compute;

import java.io.Serializable;

public class LaunchConfiguration implements Serializable {
    private static final long serialVersionUID = -1158496494385174436L;
    
    private long     creationTimestamp;
    private String   name;
    private String[] providerFirewallIds;
    private String   providerImageId;
    private String   providerLaunchConfigurationId;
    private String   serverSizeId;
    
    public LaunchConfiguration() { }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getProviderFirewallIds() {
        return providerFirewallIds;
    }

    public void setProviderFirewallIds(String[] providerFirewallIds) {
        this.providerFirewallIds = providerFirewallIds;
    }

    public String getProviderImageId() {
        return providerImageId;
    }

    public void setProviderImageId(String providerImageId) {
        this.providerImageId = providerImageId;
    }

    public String getProviderLaunchConfigurationId() {
        return providerLaunchConfigurationId;
    }

    public void setProviderLaunchConfigurationId(String providerLaunchConfigurationId) {
        this.providerLaunchConfigurationId = providerLaunchConfigurationId;
    }

    public String getServerSizeId() {
        return serverSizeId;
    }

    public void setServerSizeId(String serverSizeId) {
        this.serverSizeId = serverSizeId;
    }
    
}
