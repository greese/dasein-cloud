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

package org.dasein.cloud.dc;

import java.io.Serializable;

/**
 * <p>
 *   A data center represents a group of virtualized resources that share a significant amount
 *   of common physical infrastructure. 
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class DataCenter implements Serializable {
    private static final long serialVersionUID = -9043785446229477796L;
    
    private boolean active;
    private boolean available;
    private String  name;
    private String  providerDataCenterId;
    private String  regionId;
    
    /**
     * Constructs an empty data center object, only useful in marshalling and unmarshalling.
     */
    public DataCenter() { }
    
    /**
     * Constructs a new data center object from the specified state values.
     * @param dataCenterId the provider identifier for the data center 
     * @param name the name of the data center
     * @param active whether or not the data center is currently in use with the provider
     * @param available whether or not the data center is currently up and operating normally
     */
    public DataCenter(String dataCenterId, String name, String regionId, boolean active, boolean available) {
        providerDataCenterId = dataCenterId;
        this.regionId = regionId;
        this.name = name;
        this.active = active;
        this.available = available;
    }

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
        return getProviderDataCenterId().equals(((DataCenter)ob).getProviderDataCenterId());
    }
    
    /**
     * A (hopefully) user-friendly name for the data center as known by the provider. If the provider
     * does not support meta-data, this value should be the same as the data center ID.
     * @return the user-friendly name for the data center
     */
    public String getName() {
        return name;
    }
    
    /**
     * The unique ID as understood by the provider for representing this data center.
     * @return the provider's unique identifier for this data center
     */
    public String getProviderDataCenterId() {
        return providerDataCenterId;
    }
    
    /**
     * @return the provider's unique ID for the region in which this data center is located
     */
    public String getRegionId() {
        return regionId;
    }
    
    /**
     * A data center is considered to be active if it <em>should be</em> available. In other words,
     * this value will be <code>false</code> only for discontinued data centers or for new data centers not quite
     * released for production.
     * @return <code>true</code> if this data center should be operational
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * A data center is considered available if a customer can allocate resources in that data center.
     * A data center can be unavailable and active, but it cannot be inactive and available.
     * @return <code>true</code> if the customer should be able to expect successful resource allocation in the data center
     */
    public boolean isAvailable() {
        return available;
    }
 
    /**
     * Used only for marshalling and not to be used programatically.
     * @param active whether or not the marshalled data center is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    /**
     * Used only for marshalling and not to be used programmatically.
     * @param available whether or not the marshalled data center is available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    /**
     * Used only for marshalling and not to be used programmatically.
     * @param name the name of the marshalled data center
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Used only for marshalling and not to be used programmatically.
     * @param id the unique ID of the marshalled data center
     */
    public void setProviderDataCenterId(String id) {
        providerDataCenterId = id;
    }
    
    /**
     * Used only for marshalling and not to be used programmatically.
     * @param regionId the unique ID of the region in which the marshalled data center is located
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
    
    public String toString() {
        return name;
    }
}
