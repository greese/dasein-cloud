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

public class KeyValueDatabase implements Serializable {
    private static final long serialVersionUID = 4271581439650619419L;
    
    private String description;
    private int    itemCount;
    private int    itemSize;
    private int    keyCount;
    private int    keySize;
    private int    keyValueCount;
    private int    keyValueSize;
    private String name;
    private String providerDatabaseId;
    private String providerOwnerId;
    private String providerRegionId;
    
    public KeyValueDatabase() { }

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
        KeyValueDatabase other = (KeyValueDatabase)ob;
        
        if( !getProviderOwnerId().equals(other.getProviderOwnerId()) ) {
            return false;
        }
        if( !getProviderRegionId().equals(other.getProviderRegionId()) ) {
            return false;
        }
        return getProviderDatabaseId().equals(other.getProviderDatabaseId());
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getItemSize() {
        return itemSize;
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public int getKeyValueCount() {
        return keyValueCount;
    }

    public void setKeyValueCount(int keyValueCount) {
        this.keyValueCount = keyValueCount;
    }

    public int getKeyValueSize() {
        return keyValueSize;
    }

    public void setKeyValueSize(int keyValueSize) {
        this.keyValueSize = keyValueSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderDatabaseId() {
        return providerDatabaseId;
    }

    public void setProviderDatabaseId(String providerDatabaseId) {
        this.providerDatabaseId = providerDatabaseId;
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
    
    private transient volatile int hashCode = -1;
    
    public int hashCode() {
        if( hashCode == -1 ) {
            hashCode = (providerOwnerId + ":" + providerRegionId + ":" + providerDatabaseId).hashCode();
        }
        return hashCode;
    }
    
    public String toString() {
        return providerDatabaseId;
    }
}
