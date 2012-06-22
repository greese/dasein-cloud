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

public class ScalingGroup implements Serializable {
    private static final long serialVersionUID = -5317003700769693511L;
    
    private int      cooldown;
    private long     creationTimestamp;
    private String   description;
    private int      maxServers;
    private int      minServers;
    private String   name;
    private String[] providerDataCenterIds;
    private String   providerLaunchConfigurationId;
    private String   providerOwnerId;
    private String   providerRegionId;
    private String   providerScalingGroupId;
    private String[] providerServerIds;
    private int      targetCapacity;
    
    public ScalingGroup() { }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxServers() {
        return maxServers;
    }

    public void setMaxServers(int maxServers) {
        this.maxServers = maxServers;
    }

    public int getMinServers() {
        return minServers;
    }

    public void setMinServers(int minServers) {
        this.minServers = minServers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getProviderDataCenterIds() {
        return providerDataCenterIds;
    }

    public void setProviderDataCenterIds(String[] providerDataCenterIds) {
        this.providerDataCenterIds = providerDataCenterIds;
    }

    public String getProviderLaunchConfigurationId() {
        return providerLaunchConfigurationId;
    }

    public void setProviderLaunchConfigurationId(String providerLaunchConfigurationId) {
        this.providerLaunchConfigurationId = providerLaunchConfigurationId;
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

    public String getProviderScalingGroupId() {
        return providerScalingGroupId;
    }

    public void setProviderScalingGroupId(String providerScalingGroupId) {
        this.providerScalingGroupId = providerScalingGroupId;
    }

    public void setProviderServerIds(String[] providerServerIds) {
        this.providerServerIds = providerServerIds;
    }

    public String[] getProviderServerIds() {
        return providerServerIds;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setTargetCapacity(int targetCapacity) {
        this.targetCapacity = targetCapacity;
    }

    public int getTargetCapacity() {
        return targetCapacity;
    }
    
}
