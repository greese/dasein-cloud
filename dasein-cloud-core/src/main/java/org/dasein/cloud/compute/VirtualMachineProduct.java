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

public class VirtualMachineProduct implements Serializable {
    private static final long serialVersionUID = -6761551014614219494L;
    
    private int    cpuCount;
    private String description;
    private int    diskSizeInGb;
    private String name;
    private String productId;
    private int    ramInMb;
    
    public VirtualMachineProduct() { }

    public boolean equals(Object ob) {
        if( ob == null) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        return getProductId().equals(((VirtualMachineProduct)ob).getProductId());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiskSizeInGb() {
        return diskSizeInGb;
    }

    public void setDiskSizeInGb(int diskSizeInGb) {
        this.diskSizeInGb = diskSizeInGb;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRamInMb() {
        return ramInMb;
    }

    public void setRamInMb(int ramInMb) {
        this.ramInMb = ramInMb;
    }

    public String toString() {
        return (name + " [" + productId + "]");
    }
}
