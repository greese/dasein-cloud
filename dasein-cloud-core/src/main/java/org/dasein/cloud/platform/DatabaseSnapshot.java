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

package org.dasein.cloud.platform;

import java.io.Serializable;

public class DatabaseSnapshot implements Serializable {
    private static final long serialVersionUID = 3816734659784392296L;
    
    private String                adminUser;
    private DatabaseSnapshotState currentState; 
    private String                providerDatabaseId;
    private String                providerOwnerId;
    private String                providerRegionId;
    private String                providerSnapshotId;
    private long                  snapshotTimestamp;
    private int                   storageInGigabytes;
    
    public DatabaseSnapshot() { }
    
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
        DatabaseSnapshot other = (DatabaseSnapshot)ob;
        
        if( !getProviderOwnerId().equals(other.getProviderOwnerId()) ) {
            return false;
        }
        if( !getProviderRegionId().equals(other.getProviderRegionId()) ) {
            return false;
        }
        return getProviderSnapshotId().equals(other.getProviderSnapshotId());
    }
    
    public String getAdminUser() {
        return adminUser;
    }
    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }
    public DatabaseSnapshotState getCurrentState() {
        return currentState;
    }
    public void setCurrentState(DatabaseSnapshotState currentState) {
        this.currentState = currentState;
    }
    public String getProviderDatabaseId() {
        return providerDatabaseId;
    }
    public void setProviderDatabaseId(String providerDatabaseId) {
        this.providerDatabaseId = providerDatabaseId;
    }
    public String getProviderRegionId() {
        return providerRegionId;
    }
    public void setProviderRegionId(String providerRegionId) {
        this.providerRegionId = providerRegionId;
    }
    public String getProviderSnapshotId() {
        return providerSnapshotId;
    }
    public void setProviderSnapshotId(String providerSnapshotId) {
        this.providerSnapshotId = providerSnapshotId;
    }
    public long getSnapshotTimestamp() {
        return snapshotTimestamp;
    }
    public void setSnapshotTimestamp(long snapshotTimestamp) {
        this.snapshotTimestamp = snapshotTimestamp;
    }
    public int getStorageInGigabytes() {
        return storageInGigabytes;
    }
    public void setStorageInGigabytes(int storageInGigabytes) {
        this.storageInGigabytes = storageInGigabytes;
    }

    public void setProviderOwnerId(String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }

    public String getProviderOwnerId() {
        return providerOwnerId;
    }
    
    public String toString() {
        return getProviderSnapshotId();
    }
}
