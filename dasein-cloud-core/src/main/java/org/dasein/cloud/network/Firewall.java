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

package org.dasein.cloud.network;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * <p>
 * Represents a firewall with a list of positive access rules.
 * </p>
 * @author George Reese (george.reese@imaginary.com)
 * @since 2010.08
 * @version 2010.08
 * @version 2012.02 - Added annotations
 */
public class Firewall implements Serializable {
    private static final long serialVersionUID = 5863221134243831750L;
    
    private boolean active;
    private boolean available;
    private String  description;
    private String  name;
    private String  providerFirewallId;
    private String  providerVlanId;
    private String  regionId;
    
    /**
     * Empty constructor for marshalling.
     */
    public Firewall() { }

    @Override
    public boolean equals(Object other) {
        if( other == null ) {
            return false;
        }
        if( other == this ) {
            return true;
        }
        if( !getClass().getName().equals(other.getClass().getName()) ) {
            return false;
        }
        Firewall fw = (Firewall)other;
        if( (regionId == null && fw.regionId != null) || (regionId != null && fw.regionId == null) ) {
            return false;
        }
        if( regionId == null || !regionId.equals(fw.regionId) ) {
            return false;
        }
        if( (providerVlanId == null && fw.providerVlanId != null) || (providerVlanId != null && fw.providerVlanId == null) ) {
            return false;
        }
        if( providerVlanId == null || !providerVlanId.equals(fw.providerVlanId) ) {
            return false;
        }
        //noinspection SimplifiableIfStatement
        if( providerFirewallId == null && fw.providerFirewallId == null ) {
           return true; 
        }
        return (providerFirewallId != null && providerFirewallId.equals(fw.providerFirewallId));
    }
    
    /**
     * @return the user-friendly name for the firewall
     */
    @SuppressWarnings("unused")
    public @Nullable String getName() {
        return name;
    }

    /**
     * @return the cloud-specific unique identifier for this firewall
     */
    @SuppressWarnings("unused")
    public @Nullable String getProviderFirewallId() {
        return providerFirewallId;
    }

    /**
     * @return the unique region ID of the region in which this firewall operates 
     */
    @SuppressWarnings("unused")
    public @Nullable String getRegionId() {
        return regionId;
    }


    /**
     * @return <code>true</code> if the firewall is currently active
     */
    @SuppressWarnings("unused")
    public boolean isActive() {
        return active;
    }

    /**
     * @return <code>true</code> if the firewall is currently functioning properly
     */
    @SuppressWarnings("unused")
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the current firewall active state.
     * @param active <code>true</code> if the firewall is currently active
     */
    @SuppressWarnings("unused")
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Sets the firewall availability.
     * @param available <code>true</code> if the firewall is currently operating properly
     */
    @SuppressWarnings("unused")
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Sets the firewall name.
     * @param name the user-friendly name for the firewall
     */
    @SuppressWarnings("unused")
    public void setName(@Nonnull String name) {
        this.name = name;
    }

    /**
     * Sets the unique ID for the firewall.
     * @param providerFirewallId the cloud-specific unique ID for this firewall
     */
    @SuppressWarnings("unused")
    public void setProviderFirewallId(@Nonnull String providerFirewallId) {
        this.providerFirewallId = providerFirewallId;
    }

    /**
     * Sets the region ID for the region in which the firewall is located.
     * @param regionId the unique region ID for the firewall region
     */
    @SuppressWarnings("unused")
    public void setRegionId(@Nonnull String regionId) {
        this.regionId = regionId;
    }

    /**
     * Provides a long description of the underlying firewall that ideally describes why the
     * firewall exists.
     * @return a long description of the firewall
     */
    @SuppressWarnings("unused")
	public @Nullable String getDescription() {
		return description;
	}

	/**
	 * Defines the firewall description.
	 * @param description a description of the firewall
	 */
    @SuppressWarnings("unused")
	public void setDescription(@Nonnull String description) {
		this.description = description;
	}

    /**
     * Specifies the VLAN over which this firewall operates
     * @param providerVlanId the unique provider ID for the firewall
     */
    @SuppressWarnings("unused")
    public void setProviderVlanId(@Nullable String providerVlanId) {
        this.providerVlanId = providerVlanId;
    }

    /**
     * @return the unique provider ID for the VLAN in which this firewall operates
     */
    @SuppressWarnings("unused")
    public @Nullable String getProviderVlanId() {
        return providerVlanId;
    }

    @Override
    public @Nonnull String toString() {
        return (name + " [#" + providerFirewallId + "]");
    }
}
