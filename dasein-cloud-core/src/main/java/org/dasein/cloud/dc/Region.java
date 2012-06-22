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
 *   A group of data centers that share a jurisidiction and some common points of failure. If
 *   a data center is in two different regions, it should have no common failure points. Similarly,
 *   if two data centers are in the same region, they share the same jurisdiction.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class Region implements Serializable {
    private static final long serialVersionUID = 5759708802908910045L;
    
    private boolean       active;          
    private boolean       available; 
    private String        jurisdiction;
    private String        name;       
    private String        providerRegionId;

    /**
     * Constructs an empty region instance used in marshalling a region across the wire.
     *
     */
    public Region() { }
    
    /**
     * Constructs a region with the specified state characteristics.
     * @param regionId the unique provider ID for the region
     * @param name the user-friendly name for the region
     * @param active whether or not the active zones in this region should be available
     * @param available whether or not at least one zone in this region is available
     */
    public Region(String regionId, String name, boolean active, boolean available) {
        providerRegionId = regionId;
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
        return getProviderRegionId().equals(((Region)ob).getProviderRegionId());
    }
    
    /**
     * A (hopefully) user-friendly name for the region as known by the provider. If the provider
     * does not support meta-data, this value should be the same as the region ID.
     * @return the user-friendly name for the region
     */
    public String getName() {
        return name;
    }
    /**
     * @return the unique provider ID for the region
     */
    public String getProviderRegionId() {
        return providerRegionId;
    }
    
    /**
     * A regionis considered to be active if it <em>should have</em> at least one available data center. In other words,
     * this value will be <code>false</code> only for discontinued regions or for new regions not quite
     * released for production.
     * @return <code>true</code> if this region should be operational
     */
    public boolean isActive() {
        return active;
    }
    
    /**
     * A region is considered available if at least one of its data centers is available.
     * A region can be unavailable and active, but it cannot be inactive and available.
     * @return <code>true</code> if at least one data center in the region is available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Used only for marshalling and not to be used programatically.
     * @param active whether or not the marshalled region is active
     */
    public void setActive(boolean active) { 
        this.active = active;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param available whether or not the marshalled region is available
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param name the name of the region being marshalled
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param id the unique provider ID of the region being marshalled
     */
    public void setProviderRegionId(String id) {
        providerRegionId = id;
    }
    
    public String toString() {
        return name;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    /**
     * The two-letter ISO country code or EU (for European Union) of the jurisdiction represented by this
     * region
     * @return the jurisdiction of the region
     */
    public String getJurisdiction() {
        return jurisdiction;
    }
}
