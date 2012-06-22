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

public class Prepayment implements Serializable {
    private static final long serialVersionUID = -3570970098813092306L;
    
    private int      count;
    private String   currencyCode;
    private String   dataCenterId;
    private double   fixedFee;
    private int      periodInDays;
    private Platform platform;
    private long   periodStartTimestamp;
    private PrepaymentState prepaymentState;

    private String   providerPrepaymentId;
    private String   size;
    private String   software;
    private double   usageFee;
    
    public Prepayment() { }
    
    public Prepayment(String prepaymentId, String currencyCode, int period, long periodStart, String dataCenterId, Platform platform, String size, String software, int count, double fixedFee, double usageFee, PrepaymentState state) {
        this.providerPrepaymentId = prepaymentId;
        this.count = count;
        this.currencyCode = currencyCode;
        this.dataCenterId = dataCenterId;
        periodStartTimestamp = periodStart;
        this.fixedFee = fixedFee;
        this.periodInDays = period;
        this.platform = platform;
        this.size = size;
        this.software = software;
        this.usageFee = usageFee;
        prepaymentState = state;
    }
    
    public int getCount() {
        return count;
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
    
    public int getPeriodInDays() {
        return periodInDays;
    }
    
    public Platform getPlatform() {
        return platform;
    }
    
    public long getPeriodStartTimestamp() {
        return periodStartTimestamp;
    }
    
    public PrepaymentState getPrepaymentState() {
        return prepaymentState;
    }

    public String getProviderPrepaymentId() {
        return providerPrepaymentId;
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
    
    public void setCount(int count) {
        this.count = count;
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
    
    public void setPeriodInDays(int periodInDays) {
        this.periodInDays = periodInDays;
    }
    
    public void setPeriodStartTimestamp(long ts) {
        this.periodStartTimestamp = ts;
    }
    
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
    
    public void setPrepaymentId(String prepaymentId) {
        this.providerPrepaymentId = prepaymentId;
    }
    
    public void setPrepaymentState(PrepaymentState prepaymentState) {
        this.prepaymentState = prepaymentState;
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
