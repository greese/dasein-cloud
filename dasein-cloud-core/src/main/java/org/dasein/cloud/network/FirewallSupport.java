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

import java.util.Collection;
import java.util.Locale;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * Operations on whatever concept the underlying cloud uses to regulate network traffic into a
 * server or group of servers.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public interface FirewallSupport extends AccessControlledService {
    @SuppressWarnings("unused") static public final ServiceAction ANY                  = new ServiceAction("FW:ANY");

    @SuppressWarnings("unused") static public final ServiceAction AUTHORIZE            = new ServiceAction("FW:AUTHORIZE");
    @SuppressWarnings("unused") static public final ServiceAction CREATE_FIREWALL      = new ServiceAction("FW:CREATE_FIREWALL");
    @SuppressWarnings("unused") static public final ServiceAction GET_FIREWALL         = new ServiceAction("FW:GET_FIREWALL");
    @SuppressWarnings("unused") static public final ServiceAction LIST_FIREWALL        = new ServiceAction("FW:LIST_FIREWALL");
    @SuppressWarnings("unused") static public final ServiceAction REMOVE_FIREWALL      = new ServiceAction("FW:REMOVE_FIREWALL");
    @SuppressWarnings("unused") static public final ServiceAction REVOKE               = new ServiceAction("FW:REVOKE");

    /**
     * Provides positive authorization for the specified firewall rule. Any call to this method should
     * result in an override of any previous revocations.
     * @param firewallId the unique, cloud-specific ID for the firewall being targeted by the new rule
     * @param cidr the source CIDR (http://en.wikipedia.org/wiki/CIDR) for the allowed traffic
     * @param protocol the protocol (tcp/udp/icmp) supported by this rule
     * @param beginPort the beginning of the port range to be allowed, inclusive
     * @param endPort the end of the port range to be allowed, inclusive
     * @return the provider ID of the new rule
     * @throws CloudException an error occurred with the cloud provider establishing the rule
     * @throws InternalException an error occurred locally trying to establish the rule
     */
    @SuppressWarnings("unused")
    public @Nonnull String authorize(@Nonnull String firewallId, @Nonnull String cidr, @Nonnull Protocol protocol, int beginPort, int endPort) throws CloudException, InternalException;

    /**
     * Creates a new firewall with the specified name.
     * @param name the user-friendly name for the new firewall
     * @param description a description of the purpose of the firewall
     * @return the unique ID for the newly created firewall
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     * @throws InternalException an error occurred locally independent of any events in the cloud
     */
    @SuppressWarnings("unused")
    public @Nonnull String create(@Nonnull String name, @Nonnull String description) throws InternalException, CloudException;
    
    /**
     * Creates a new firewall with the specified name governing the target VLAN. If the underlying cloud 
     * doesn't support VLANs, then this method will throw an UnsupportedOperationException.
     * @param name the user-friendly name for the new firewall
     * @param description a description of the purpose of the firewall
     * @param providerVlanId the ID of the VLAN with which this firewall will be associated
     * @return the unique ID for the newly created firewall
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     * @throws InternalException an error occurred locally independent of any events in the cloud
     * @throws UnsupportedOperationException this cloud doesn't support VLANs with firewalls
     */
    @SuppressWarnings("unused")
    public @Nonnull String createInVLAN(@Nonnull String name, @Nonnull String description, @Nonnull String providerVlanId) throws InternalException, CloudException;
    
    
    /**
     * Deletes the specified firewall from the system.
     * @param firewallId the unique ID of the firewall to be deleted
     * @throws InternalException an error occurred locally independent of any events in the cloud
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     */
    @SuppressWarnings("unused")
    public void delete(@Nonnull String firewallId) throws InternalException, CloudException;
    
    /**
     * Provides the full firewall data for the specified firewall.
     * @param firewallId the unique ID of the desired firewall
     * @return the firewall state for the specified firewall instance
     * @throws InternalException an error occurred locally independent of any events in the cloud
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     */
    @SuppressWarnings("unused")
    public @Nullable Firewall getFirewall(@Nonnull String firewallId) throws InternalException, CloudException;
    
    /**
     * Provides the firewall terminology for the concept of a firewall. For example, AWS calls a 
     * firewall a "security group".
     * @param locale the locale for which you should translate the firewall term
     * @return the translated term for firewall with the target cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull String getProviderTermForFirewall(@Nonnull Locale locale);
    
    /**
     * Provides the affirmative rules supported by the named firewall.
     * @param firewallId the unique ID of the firewall being queried
     * @return all rules supported by the target firewall
     * @throws InternalException an error occurred locally independent of any events in the cloud
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     */
    @SuppressWarnings("unused")
    public @Nonnull Collection<FirewallRule> getRules(@Nonnull String firewallId) throws InternalException, CloudException;

    /**
     * Identifies whether or not the current account is subscribed to firewall services in the current region.
     * @return true if the current account is subscribed to firewall services for the current region
     * @throws CloudException an error occurred with the cloud provider while determining subscription status
     * @throws InternalException an error occurred in the Dasein Cloud implementation while determining subscription status
     */
    @SuppressWarnings("unused")
    public boolean isSubscribed() throws CloudException, InternalException;

    /**
     * Lists all firewalls in the current provider context.
     * @return a list of all firewalls in the current provider context
     * @throws InternalException an error occurred locally independent of any events in the cloud
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     */
    @SuppressWarnings("unused")
    public @Nonnull Collection<Firewall> list() throws InternalException, CloudException;

    /**
     * Revokes the specified access from the named firewall.
     * @param firewallId the firewall from which the rule is being revoked
     * @param cidr the source CIDR (http://en.wikipedia.org/wiki/CIDR) for the rule being removed
     * @param protocol the protocol (tcp/icmp/udp) of the rule being removed
     * @param beginPort the initial port of the rule being removed
     * @param endPort the end port of the rule being removed
     * @throws InternalException an error occurred locally independent of any events in the cloud
     * @throws CloudException an error occurred with the cloud provider while performing the operation
     */
    @SuppressWarnings("unused")
    public void revoke(@Nonnull String firewallId, @Nonnull String cidr, @Nonnull Protocol protocol, int beginPort, int endPort) throws CloudException, InternalException;
}
