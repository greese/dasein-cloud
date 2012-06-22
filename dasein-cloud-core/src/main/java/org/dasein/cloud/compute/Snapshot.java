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

public class Snapshot implements Serializable {
    private static final long serialVersionUID = 1451559961429054236L;
    
    private SnapshotState currentState;
    private String        description;
    private String        name;
    private String        owner;
    private String        progress;
    private String        providerSnapshotId;
    private String        regionId;
    private int           sizeInGb;
    private long          snapshotTimestamp;
    private String        volumeId;
    
    public Snapshot() { }
    
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
        Snapshot other = (Snapshot)ob;
        
        if( !owner.equals(other.owner) ) {
            return false;
        }
        if( !regionId.equals(other.regionId) ) {
            return false;
        }
        return providerSnapshotId.equals(other.providerSnapshotId);
    }
    
    public SnapshotState getCurrentState() {
        return currentState;
    }
    
    public String getName() { 
        return name;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public String getProviderSnapshotId() {
        return providerSnapshotId;
    }
    
    public String getRegionId() {
        return regionId;
    }
    
    public long getSnapshotTimestamp() {
        return snapshotTimestamp;
    }
    
    public String getVolumeId() {
        return volumeId;
    }
    
    public void setCurrentState(SnapshotState currentState) {
        this.currentState = currentState;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public void setProviderSnapshotId(String providerSnapshotId) {
        this.providerSnapshotId = providerSnapshotId;
    }
    
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    
    public void setSnapshotTimestamp(long snapshotTimestamp) {
        this.snapshotTimestamp = snapshotTimestamp;
    }
    
    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public String getDescription() {
        return description;
    }

    public String getProgress() {
        return progress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

	public int getSizeInGb() {
		return sizeInGb;
	}

	public void setSizeInGb(int sizeInGb) {
		this.sizeInGb = sizeInGb;
	}
	
	public String toString() {
	    return (name + " [" + providerSnapshotId + "]");
	}
}
