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
import java.util.HashMap;
import java.util.Map;

import org.dasein.cloud.Tag;

public class MachineImage implements Serializable {
    private static final long serialVersionUID = 3254097599257280038L;
    
    private Architecture       architecture;    
    private MachineImageState  currentState;
    private Map<String,String> tags;
    private String             description;
    private String             name;
    private Platform           platform;
    private String             providerMachineImageId;
    private String             providerOwnerId;
    private String             providerRegionId;
    private String             software;
    private MachineImageType   type;
    
    public MachineImage() { }

    public boolean equals(Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( ob instanceof MachineImage ) {
            MachineImage image = (MachineImage)ob;
            
            if( !providerRegionId.equals(image.providerRegionId) ) {
                return false;
            }
            return providerMachineImageId.equals(image.providerMachineImageId);
        }
        return false;
    }
    
    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public MachineImageState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MachineImageState currentState) {
        this.currentState = currentState;
    }

    public void addTag(Tag t) {
        addTag(t.getKey(), t.getValue());
    }
    
    public void addTag(String key, String value) {
        getTags().put(key, value);
    }
    
    public Object getTag(String tag) {
        return getTags().get(tag);
    }
    
    public synchronized Map<String,String> getTags() {
        if( tags == null ) {
            tags = new HashMap<String,String>();
        }
        return tags;
    }
    
    public synchronized void setTags(Map<String,String> properties) {
        getTags().clear();
        getTags().putAll(properties);
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

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getProviderMachineImageId() {
        return providerMachineImageId;
    }

    public void setProviderMachineImageId(String providerMachineImageId) {
        this.providerMachineImageId = providerMachineImageId;
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

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public MachineImageType getType() {
        return type;
    }

    public void setType(MachineImageType type) {
        this.type = type;
    } 

    public int hashCode() {
        return (providerRegionId + providerMachineImageId).hashCode();
    }
    
    public String toString() {
        return (name + " [" + providerMachineImageId + "]");
    }
}
