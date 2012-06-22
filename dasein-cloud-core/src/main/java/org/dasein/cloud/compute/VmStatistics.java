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
import java.util.Date;

/**
 * <p>
 * A very ugly class that contains general analytic data that can be retrieved by a 
 * hypervisor from its guest hosts. Implementations of this class should provide empty
 * values (like 0.0) for anything not supported by the underlying hypervisor or
 * if the operation is not supported for the account in question.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class VmStatistics implements Serializable {
    private static final long serialVersionUID = -5504410320504261431L;
    
    private double averageCpuUtilization;
    private double maximumCpuUtilization;
    private double minimumCpuUtilization;
    
    private double averageDiskReadBytes;
    private double maximumDiskReadBytes;
    private double minimumDiskReadBytes;
    
    private double averageDiskReadOperations;
    private double maximumDiskReadOperations;
    private double minimumDiskReadOperations;
    
    private double averageDiskWriteBytes;
    private double maximumDiskWriteBytes;
    private double minimumDiskWriteBytes;
    
    private double averageDiskWriteOperations;
    private double maximumDiskWriteOperations;
    private double minimumDiskWriteOperations;
    
    private double averageNetworkIn;
    private double maximumNetworkIn;
    private double minimumNetworkIn;
    
    private double averageNetworkOut;
    private double maximumNetworkOut;
    private double minimumNetworkOut;
    
    private long endTimestamp;
    private long startTimestamp;
    
    private int samples;
    
    public VmStatistics() { }

    public double getAverageCpuUtilization() {
        return averageCpuUtilization;
    }

    public double getAverageDiskReadBytes() {
        return averageDiskReadBytes;
    }

    public double getAverageDiskReadOperations() {
        return averageDiskReadOperations;
    }

    public double getAverageDiskWriteBytes() {
        return averageDiskWriteBytes;
    }

    public double getAverageDiskWriteOperations() {
        return averageDiskWriteOperations;
    }

    public double getAverageNetworkIn() {
        return averageNetworkIn;
    }

    public double getAverageNetworkOut() {
        return averageNetworkOut;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public double getMaximumCpuUtilization() {
        return maximumCpuUtilization;
    }

    public double getMaximumDiskReadBytes() {
        return maximumDiskReadBytes;
    }

    public double getMaximumDiskReadOperations() {
        return maximumDiskReadOperations;
    }

    public double getMaximumDiskWriteBytes() {
        return maximumDiskWriteBytes;
    }

    public double getMaximumDiskWriteOperations() {
        return maximumDiskWriteOperations;
    }

    public double getMaximumNetworkIn() {
        return maximumNetworkIn;
    }

    public double getMaximumNetworkOut() {
        return maximumNetworkOut;
    }

    public double getMinimumCpuUtilization() {
        return minimumCpuUtilization;
    }

    public double getMinimumDiskReadBytes() {
        return minimumDiskReadBytes;
    }

    public double getMinimumDiskReadOperations() {
        return minimumDiskReadOperations;
    }

    public double getMinimumDiskWriteBytes() {
        return minimumDiskWriteBytes;
    }

    public double getMinimumDiskWriteOperations() {
        return minimumDiskWriteOperations;
    }

    public double getMinimumNetworkIn() {
        return minimumNetworkIn;
    }

    public double getMinimumNetworkOut() {
        return minimumNetworkOut;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setAverageCpuUtilization(double averageCpuUtilization) {
        this.averageCpuUtilization = averageCpuUtilization;
    }

    public void setAverageDiskReadBytes(double averageDiskReadBytes) {
        this.averageDiskReadBytes = averageDiskReadBytes;
    }

    public void setAverageDiskReadOperations(double averageDiskReadOperations) {
        this.averageDiskReadOperations = averageDiskReadOperations;
    }

    public void setAverageDiskWriteBytes(double averageDiskWriteBytes) {
        this.averageDiskWriteBytes = averageDiskWriteBytes;
    }

    public void setAverageDiskWriteOperations(double averageDiskWriteOperations) {
        this.averageDiskWriteOperations = averageDiskWriteOperations;
    }

    public void setAverageNetworkIn(double averageNetworkIn) {
        this.averageNetworkIn = averageNetworkIn;
    }

    public void setAverageNetworkOut(double averageNetworkOut) {
        this.averageNetworkOut = averageNetworkOut;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public void setMaximumCpuUtilization(double maximumCpuUtilization) {
        this.maximumCpuUtilization = maximumCpuUtilization;
    }

    public void setMaximumDiskReadBytes(double maximumDiskReadBytes) {
        this.maximumDiskReadBytes = maximumDiskReadBytes;
    }

    public void setMaximumDiskReadOperations(double maximumDiskReadOperations) {
        this.maximumDiskReadOperations = maximumDiskReadOperations;
    }

    public void setMaximumDiskWriteBytes(double maximumDiskWriteBytes) {
        this.maximumDiskWriteBytes = maximumDiskWriteBytes;
    }

    public void setMaximumDiskWriteOperations(double maximumDiskWriteOperations) {
        this.maximumDiskWriteOperations = maximumDiskWriteOperations;
    }

    public void setMaximumNetworkIn(double maximumNetworkIn) {
        this.maximumNetworkIn = maximumNetworkIn;
    }

    public void setMaximumNetworkOut(double maximumNetworkOut) {
        this.maximumNetworkOut = maximumNetworkOut;
    }

    public void setMinimumCpuUtilization(double minimumCpuUtilization) {
        this.minimumCpuUtilization = minimumCpuUtilization;
    }

    public void setMinimumDiskReadBytes(double minimumDiskReadBytes) {
        this.minimumDiskReadBytes = minimumDiskReadBytes;
    }

    public void setMinimumDiskReadOperations(double minimumDiskReadOperations) {
        this.minimumDiskReadOperations = minimumDiskReadOperations;
    }

    public void setMinimumDiskWriteBytes(double minimumDiskWriteBytes) {
        this.minimumDiskWriteBytes = minimumDiskWriteBytes;
    }

    public void setMinimumDiskWriteOperations(double minimumDiskWriteOperations) {
        this.minimumDiskWriteOperations = minimumDiskWriteOperations;
    }

    public void setMinimumNetworkIn(double minimumNetworkIn) {
        this.minimumNetworkIn = minimumNetworkIn;
    }

    public void setMinimumNetworkOut(double minimumNetworkOut) {
        this.minimumNetworkOut = minimumNetworkOut;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }
    
    public String toString() {
        return (new Date(startTimestamp)) + " - " + (new Date(endTimestamp));
    }
}
