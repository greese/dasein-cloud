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

import org.dasein.cloud.TimeWindow;

public class Database implements Serializable {
    private static final long serialVersionUID = 2965680337224730031L;
    
    private String          adminUser;
    private int             allocatedStorageInGb;
    private String          configuration;
    private long            creationTimestamp;
    private DatabaseState   currentState;
    private DatabaseEngine  engine;
    private boolean         highAvailability;
    private int             hostPort;
    private String          hostName;
    private TimeWindow      maintenanceWindow;
    private String          name;
    private String          productSize;
    private String          providerDataCenterId;
    private String          providerDatabaseId;
    private String          providerOwnerId;
    private String          providerRegionId;
    private long            recoveryPointTimestamp;
    private TimeWindow      snapshotWindow;
    private int             snapshotRetentionInDays;

    public Database() { }

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
        Database other = (Database)ob;
        
        if( !getProviderOwnerId().equals(other.getProviderOwnerId()) ) {
            return false;
        }
        if( !getProviderRegionId().equals(other.getProviderRegionId()) ) {
            return false;
        }
        return getProviderDatabaseId().equals(other.getProviderDatabaseId());
    }
    
    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public int getAllocatedStorageInGb() {
        return allocatedStorageInGb;
    }

    public void setAllocatedStorageInGb(int allocatedStorageInGb) {
        this.allocatedStorageInGb = allocatedStorageInGb;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public DatabaseState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(DatabaseState currentState) {
        this.currentState = currentState;
    }

    public DatabaseEngine getEngine() {
        return engine;
    }

    public void setEngine(DatabaseEngine engine) {
        this.engine = engine;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getProviderDatabaseId() {
        return providerDatabaseId;
    }

    public void setProviderDatabaseId(String providerDatabaseServerId) {
        this.providerDatabaseId = providerDatabaseServerId;
    }

    public long getRecoveryPointTimestamp() {
        return recoveryPointTimestamp;
    }

    public void setRecoveryPointTimestamp(long recoveryPointTimestamp) {
        this.recoveryPointTimestamp = recoveryPointTimestamp;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderOwnerId() {
        return providerOwnerId;
    }

    public void setProviderOwnerId(String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }

    public int getSnapshotRetentionInDays() {
        return snapshotRetentionInDays;
    }

    public void setSnapshotRetentionInDays(int snapshotRetentionInDays) {
        this.snapshotRetentionInDays = snapshotRetentionInDays;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public TimeWindow getMaintenanceWindow() {
        return maintenanceWindow;
    }

    public void setMaintenanceWindow(TimeWindow maintenanceWindow) {
        this.maintenanceWindow = maintenanceWindow;
    }

    public TimeWindow getSnapshotWindow() {
        return snapshotWindow;
    }

    public void setSnapshotWindow(TimeWindow snapshotWindow) {
        this.snapshotWindow = snapshotWindow;
    }
    
    public String toString() {
        return providerDatabaseId;
    }

    public void setHighAvailability(boolean highAvailability) {
        this.highAvailability = highAvailability;
    }

    public boolean isHighAvailability() {
        return highAvailability;
    }
}
