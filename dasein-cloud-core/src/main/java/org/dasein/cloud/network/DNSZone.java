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

import java.io.Serializable;

/**
 * <p>
 * Represents a DNS zone to be managed by a cloud DNS provider. The most common use of a DNS zone is
 * to provide a list of information in the form of {@link DNSRecord}s associated with a DNS domain like
 * <i>dasein.org</i> or <i>imaginary.com</i>. 
 * </p>
 * <p>
 * Operations on these objects do not travel to the cloud. You must call operations on {@link DNSSupport}
 * to effect any changes in the cloud.
 * </p>
 * @author George Reese (george.reese@imaginary.com)
 */
public class DNSZone implements Serializable {
    private static final long serialVersionUID = -5294345277506634365L;
    
    private String   description;
    private String   domainName;
    private String   name;
    private String[] nameservers;
    private String   providerDnsZoneId;
    private String   providerOwnerId;
    
    public DNSZone() { }

    /**
     * Provides a description of the DNS zone.
     * @return the DNS zone description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the DNS zone description.
     * @param description a description for the DNS zone
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Provides he domain name (or general name) of the DNS zone. 
     * @return the zone domain name
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets the domain name for the DNS zone.
     * @param domainName the domain name to set
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Provides a user-friendly name of the DNS zone.
     * @return a user-friendly name for the DNS zone
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user-friendly name of the DNS zone.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lists all nameservers supporting this DNS zone. 
     * @return the nameservers supporting this DNS zone
     */
    public String[] getNameservers() {
        return nameservers;
    }

    /**
     * Sets the nameservers supporting this DNS zone.
     * @param nameservers the nameservers supporting this DNS zone
     */
    public void setNameservers(String[] nameservers) {
        this.nameservers = nameservers;
    }

    /**
     * Provides the cloud provider's unique ID for this zone.
     * @return the cloud provider unique ID for this zone
     */
    public String getProviderDnsZoneId() {
        return providerDnsZoneId;
    }

    /**
     * Sets the unique ID for this zone
     * @param providerDnsZoneId the cloud provider unique ID for this zone
     */
    public void setProviderDnsZoneId(String providerDnsZoneId) {
        this.providerDnsZoneId = providerDnsZoneId;
    }

    /**
     * Provides the account number with which this zone is associated.
     * @return the account number behind this zone
     */
    public String getProviderOwnerId() {
        return providerOwnerId;
    }

    /**
     * Sets the account number for the zone.
     * @param providerOwnerId the account number for the zone
     */
    public void setProviderOwnerId(String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }
    
    @Override
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
        DNSZone other = (DNSZone)ob;
        
        if( !getProviderOwnerId().equals(other.getProviderOwnerId()) ) {
            return false;
        }
        return getProviderDnsZoneId().equals(other.getProviderDnsZoneId());
    }
    
    @Override
    public int hashCode() {
        return providerDnsZoneId.hashCode();
    }
    
    @Override
    public String toString() {
        return (domainName + " [#" + providerDnsZoneId + "]");
    }
}
