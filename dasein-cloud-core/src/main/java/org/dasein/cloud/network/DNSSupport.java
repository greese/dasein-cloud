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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

/**
 * Support for a dynamically managed, cloud-based DNS service. This interface defines all of the core
 * operations necessary for creating managed DNS zones and manipulating the records within those zones.
 * @author George Reese (george.reese@enstratus.com)
 */
public interface DNSSupport extends AccessControlledService {
    @SuppressWarnings("unused") static public final ServiceAction ANY           = new ServiceAction("DNS:ANY");

    @SuppressWarnings("unused") static public final ServiceAction ADD_RECORD    = new ServiceAction("DNS:AddRecord");
    @SuppressWarnings("unused") static public final ServiceAction CREATE_ZONE   = new ServiceAction("DNS:CreateZone");
    @SuppressWarnings("unused") static public final ServiceAction GET_ZONE      = new ServiceAction("DNS:GetZone");
    @SuppressWarnings("unused") static public final ServiceAction LIST_RECORD   = new ServiceAction("DNS:ListRecord");
    @SuppressWarnings("unused") static public final ServiceAction LIST_ZONE     = new ServiceAction("DNS:ListZone");
    @SuppressWarnings("unused") static public final ServiceAction REMOVE_RECORD = new ServiceAction("DNS:RemoveRecord");
    @SuppressWarnings("unused") static public final ServiceAction REMOVE_ZONE   = new ServiceAction("DNS:RemoveZone");

    /**
     * Adds a new record to the target DNS zone.
     * @param providerDnsZoneId the cloud provider's unique identifier for the zone to which a record will be added
     * @param recordType the type of record being added
     * @param name the name of the record (for example, the host name for an A record)
     * @param ttl the time-to-live (when in doubt, use 3600)
     * @param values one or more values to assign to this record (for example, IP addresses for an A record)
     * @return the newly created DNS record
     * @throws CloudException an error occurred with the cloud provider when adding the record
     * @throws InternalException an error occurred within the Dasein Cloud implementation in trying to add the record
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull DNSRecord addDnsRecord(@Nonnull String providerDnsZoneId, @Nonnull DNSRecordType recordType, @Nonnull String name, @Nonnegative int ttl, @Nonnull String ... values) throws CloudException, InternalException;
    
    /**
     * Creates a new DNS zone with the cloud DNS provider. You must be an owner of the specified domain or you are
     * certainly in violation of the terms of services with your DNS provider.
     * @param domainName the name of the domain being managed for this zone
     * @param name a user-friendly name for the zone
     * @param description a description of the zone being created
     * @return the unique cloud provider ID for the newly created zone
     * @throws CloudException an error occurred in the cloud provider attempting to add the zone
     * @throws InternalException an error occurred within the Dasein Cloud implementation attempting to add the zone
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull String createDnsZone(@Nonnull String domainName, @Nonnull String name, @Nonnull String description) throws CloudException, InternalException;
        
    /**
     * Removes one or more DNS records from the zones with which they are associated in the cloud.
     * @param dnsRecords a list of DNS records to be deleted
     * @throws CloudException an error occurred in the cloud provider while attempting to remove the records
     * @throws InternalException an error occurred in the Dasein Cloud implementation while attempting to remove the records
     */
    @SuppressWarnings("unused")
    public abstract void deleteDnsRecords(@Nonnull DNSRecord ... dnsRecords) throws CloudException, InternalException;
    
    /**
     * Removes the specified DNS zone from the DNS cloud provider. This is a very dangerous operation as it obviously 
     * will result in lookups on your DNS records failing.
     * @param providerDnsZoneId the cloud provider unique identifier for the zone to be removed
     * @throws CloudException an error occurred in the cloud provider while attempting to remove the zone
     * @throws InternalException an error occurred in the Dasein Cloud implementation while attempting to remove the zone
     */
    @SuppressWarnings("unused")
    public abstract void deleteDnsZone(@Nonnull String providerDnsZoneId) throws CloudException, InternalException;
    
    /**
     * Retrieves the specified DNS zone.
     * @param providerDnsZoneId the unique cloud provider ID for the desired DNS zone
     * @return the DNS zone (if any) matching the specified ID (<code>null</code> otherwise)
     * @throws CloudException an error occurred in the cloud provider while retrieving the specified zone
     * @throws InternalException an error occurred in the Dasein Cloud implementation while retrieving the specified zone
     */
    @SuppressWarnings("unused")
    public abstract @Nullable DNSZone getDnsZone(@Nonnull String providerDnsZoneId) throws CloudException, InternalException;
    
    /**
     * Provides a user-friendly term for a DNS record using the cloud provider's terminology
     * @param locale the locale into which the term should be translated
     * @return the provider-specific, user-friendly term for a DNS record
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull String getProviderTermForRecord(@Nonnull Locale locale);
    
    /**
     * Provides a user-friendly term for a DNS zone using the cloud provider's terminology
     * @param locale the locale into which the term should be translated
     * @return the provider-specific, user-friendly term for a DNS zone
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull String getProviderTermForZone(@Nonnull Locale locale);
    
    /**
     * Lists all DNS records attached to the zone matching the optional type and name.
     * @param providerDnsZoneId the DNS zone for which the records are to be listed
     * @param forType the record type you want listed
     * @param name the name of the record to be listed 
     * @return all matching DNS records
     * @throws CloudException an error occurred in the cloud provider listing DNS records
     * @throws InternalException an error occurred in Dasein Cloud implementation while retrieving the DNS records
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull Iterable<DNSRecord> listDnsRecords(@Nonnull String providerDnsZoneId, @Nullable DNSRecordType forType, @Nullable String name) throws CloudException, InternalException;
    
    /**
     * Lists all DNS zones associated with your account.
     * @return all DNS zones associated with your account
     * @throws CloudException an error occurred in the cloud provider listing zones
     * @throws InternalException an error occurred in the Dasein Cloud implementation while listing zones
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull Iterable<DNSZone> listDnsZones() throws CloudException, InternalException;
    
    /**
     * Indicates whether or not the account associated with the current Dasein Cloud context is subscribed.
     * This call will generate a request to the cloud provider, so it's not a good idea to make
     * repeated calls to this method.
     * @return true if the account is subscribed, false otherwise
     * @throws CloudException an error occurred in the cloud provider checking the subscription state
     * @throws InternalException an error occurred in the Dasein Cloud implementation while checking the subscription state
     */
    @SuppressWarnings("unused")
    public abstract boolean isSubscribed() throws CloudException, InternalException;
}
