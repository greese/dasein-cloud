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

package org.dasein.cloud.admin;

import java.io.Serializable;

import org.dasein.cloud.compute.Platform;

public class Offering implements Serializable {
    private static final long serialVersionUID = -7657639768345969388L;
    
    private String   currencyCode;
    private String   dataCenterId;
    private double   fixedFee;
    private String   providerOfferingId;
    private int      periodInDays;
    private Platform platform;
    private String   size;
    private String   software;
    private double   usageFee;
    
    public Offering() { }
    
    public Offering(String providerOfferingId, String currencyCode, int period, String dataCenterId, Platform platform, String size, String software, double fixedFee, double usageFee) {
        this.currencyCode = currencyCode;
        this.dataCenterId = dataCenterId;
        this.fixedFee = fixedFee;
        this.providerOfferingId = providerOfferingId;
        periodInDays = period;
        this.platform = platform;
        this.size = size;
        this.software = software;
        this.usageFee = usageFee;
    }
    
    public String getCurrencyCode() {
        return currencyCode;
    }
    
    public String getDataCenterId() {
        return dataCenterId;
    }
    
    public double getFixedFee() {
        return fixedFee;
    }
    
    public String getProviderOfferingId() {
        return providerOfferingId;
    }
    
    public int getPeriodInDays() {
        return periodInDays;
    }
    
    public Platform getPlatform() {
        return platform;
    }
    
    public String getSize() {
        return size;
    }
    
    public String getSoftware() {
        return software;
    }
    
    public double getUsageFee() {
        return usageFee;
    }
    
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    public void setDataCenterId(String dataCenterId) {
        this.dataCenterId = dataCenterId;
    }
    
    public void setFixedFee(double fixedFee) {
        this.fixedFee = fixedFee;
    }
    
    public void setOfferingId(String offeringId) {
        this.providerOfferingId = offeringId;
    }
    
    public void setPeriodInDays(int periodInDays) {
        this.periodInDays = periodInDays;
    }
    
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
    
    public void setSize(String size) {
        this.size = size;
    }
    
    public void setSoftware(String software) {
        this.software = software;
    }
    
    public void setUsageFee(double usageFee) {
        this.usageFee = usageFee;
    }
}
