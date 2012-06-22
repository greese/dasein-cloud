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

public class Volume implements Serializable {
    private static final long serialVersionUID = -4234782350607591612L;
    
    private long        creationTimestamp;
	private VolumeState currentState;
    private String      providerDataCenterId;
    private String      deviceId;
    private String      name;
    private String      providerVolumeId;
    private String      providerRegionId;
    private String      providerVirtualMachineId;
    private int         sizeInGigabytes;
    private String      providerSnapshotId;
    
	public Volume() { }
    
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
        Volume other = (Volume)ob;
        
        if( !providerRegionId.equals(other.providerRegionId) ) {
            return false;
        }
        return providerVolumeId.equals(other.providerVolumeId);
    }
    
    /**
     * @deprecated Use {@link #getProviderSnapshotId()}
     */
    public String getSnapshotId() {
		return getProviderSnapshotId();
	}

    public String getProviderSnapshotId() {
        return providerSnapshotId;
    }
    
    /**
     * @deprecated use {@link #setProviderSnapshotId(String)}
     */
	public void setSnapshotId(String snapshotId) {
		this.providerSnapshotId = snapshotId;
	}

	public void setProviderSnapshotId(String snapshotId) {
	    this.providerSnapshotId = snapshotId;
	}

    public VolumeState getCurrentState() {
        return currentState;
    }

    /**
     * @deprecated use {@link #getProviderDataCenterId()}
     */
    public String getDataCenterId() {
        return getProviderDataCenterId();
    }
    
    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    public String getDeviceId() {
        return deviceId;
    }
    
    public String getName() {
        return name;
    }

    public String getProviderVolumeId() {
        return providerVolumeId;
    }

    /**
     * @deprecated use {@link #getProviderRegionId()}
     */
    public String getRegionId() {
        return getProviderRegionId();
    }
    
    public String getProviderRegionId() {
        return providerRegionId;
    }

    /**
     * @deprecated use {@link #getProviderVirtualMachineId()}
     */
    public String getServerId() {
        return getProviderVirtualMachineId();
    }

    public String getProviderVirtualMachineId() {
        return providerVirtualMachineId;
    }
    
    public int getSizeInGigabytes() {
        return sizeInGigabytes;
    }

    public void setCurrentState(VolumeState currentState) {
        this.currentState = currentState;
    }

    /**
     * @deprecated use {@link #setProviderDataCenterId(String)} 
     */
    public void setDataCenterId(String dataCenterId) {
        setProviderDataCenterId(dataCenterId);
    }
    
    public void setProviderDataCenterId(String dataCenterId) {
        this.providerDataCenterId = dataCenterId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setProviderVolumeId(String providerVolumeId) {
        this.providerVolumeId = providerVolumeId;
    }

    /**
     * @deprecated use {@link #setProviderRegionId(String)}
     */
    public void setRegionId(String regionId) {
        setProviderRegionId(regionId);
    }
    
    public void setProviderRegionId(String regionId) {
        this.providerRegionId = regionId;
    }

    /**
     * @deprecated use {@link #setProviderVirtualMachineId(String)}
     */
    public void setServerId(String serverId) {
        setProviderVirtualMachineId(serverId);
    }
    
    public void setProviderVirtualMachineId(String serverId) {
        this.providerVirtualMachineId = serverId;
    }

    public void setSizeInGigabytes(int sizeInGigabytes) {
        this.sizeInGigabytes = sizeInGigabytes;
    }

	public long getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(long creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public String toString() {
	    return (name + " [" + providerVolumeId + "]");
	}
}
