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

public class DatabaseProduct implements Serializable {
    private static final long serialVersionUID = -5535140342676937943L;
    
    private String         currency;
    private DatabaseEngine engine;
    private boolean        highAvailability;
    private String         name;
    private String         productSize;
    private String         providerDataCenterId;
    private float          standardHourlyRate;
    private float          standardIoRate;
    private float          standardStorageRate;
    private int            storageInGigabytes;
    
    public DatabaseProduct(String databaseSizeId) {
        this(databaseSizeId, databaseSizeId);
    }
    
    public DatabaseProduct(String databaseSizeId, String databaseSizeName) {
        this.productSize = databaseSizeId;
        this.name = databaseSizeName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String databaseSizeId) {
        this.productSize = databaseSizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String databaseSizeName) {
        this.name = databaseSizeName;
    }

    public void setEngine(DatabaseEngine engine) {
        this.engine = engine;
    }

    public DatabaseEngine getEngine() {
        return engine;
    }
    
    public void setProviderDataCenterId(String providerDataCenterId) {
        this.providerDataCenterId = providerDataCenterId;
    }

    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }

    public void setStorageInGigabytes(int storageInGigabytes) {
        this.storageInGigabytes = storageInGigabytes;
    }

    public int getStorageInGigabytes() {
        return storageInGigabytes;
    }


    public float getStandardHourlyRate() {
        return standardHourlyRate;
    }

    public void setStandardHourlyRate(float standardHourlyRate) {
        this.standardHourlyRate = standardHourlyRate;
    }

    public float getStandardStorageRate() {
        return standardStorageRate;
    }

    public void setStandardStorageRate(float standardStorageRate) {
        this.standardStorageRate = standardStorageRate;
    }

    public void setStandardIoRate(float standardIoRate) {
        this.standardIoRate = standardIoRate;
    }

    public float getStandardIoRate() {
        return standardIoRate;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
    
    public String toString() {
        return productSize;
    }

    public void setHighAvailability(boolean highAvailability) {
        this.highAvailability = highAvailability;
    }

    public boolean isHighAvailability() {
        return highAvailability;
    }
}
