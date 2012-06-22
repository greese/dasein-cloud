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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.Tag;
import org.dasein.cloud.identity.ServiceAction;

/**
 * <p>
 * Core interface for the server services. Implements all operations critical to managing virtual
 * machines in a cloud environment.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public interface VirtualMachineSupport extends AccessControlledService {
    @SuppressWarnings("unused") static public final ServiceAction ANY               = new ServiceAction("VM:ANY");

    @SuppressWarnings("unused") static public final ServiceAction BOOT              = new ServiceAction("VM:BOOT");
    @SuppressWarnings("unused") static public final ServiceAction CLONE             = new ServiceAction("VM:CLONE");
    @SuppressWarnings("unused") static public final ServiceAction CREATE_VM         = new ServiceAction("VM:CREATE_VM");
    @SuppressWarnings("unused") static public final ServiceAction GET_VM            = new ServiceAction("VM:GET_VM");
    @SuppressWarnings("unused") static public final ServiceAction LIST_VM           = new ServiceAction("VM:LIST_VM");
    @SuppressWarnings("unused") static public final ServiceAction PAUSE             = new ServiceAction("VM:PAUSE");
    @SuppressWarnings("unused") static public final ServiceAction REBOOT            = new ServiceAction("VM:REBOOT");
    @SuppressWarnings("unused") static public final ServiceAction REMOVE_VM         = new ServiceAction("VM:REMOVE_VM");
    @SuppressWarnings("unused") static public final ServiceAction TOGGLE_ANALYTICS  = new ServiceAction("VM:TOGGLE_ANALYTICS");
    @SuppressWarnings("unused") static public final ServiceAction VIEW_ANALYTICS    = new ServiceAction("VM:VIEW_ANALYTICS");
    @SuppressWarnings("unused") static public final ServiceAction VIEW_CONSOLE      = new ServiceAction("VM:VIEW_CONSOLE");
    
    /**
     * Boots up a pre-defined virtuak machine. This works only for systems that support persistent servers that
     * can be paused.
     * @param vmId the virtual machine to boot up
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract void boot(@Nonnull String vmId) throws InternalException, CloudException;
    
    /**
     * Clones an existing virtual machine into a new copy.
     * @param vmId the ID of the server to be cloned
     * @param intoDcId the ID of the data center in which the new server will operate
     * @param name the name of the new server
     * @param description a description for the new server
     * @param powerOn power on the new server
     * @param firewallIds a list of firewall IDs to protect the new server
     * @return a newly deployed server cloned from the original
     * @throws InternalException an internal error occurred processing the request
     * @throws CloudException an error occurred in the cloud processing the request
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull VirtualMachine clone(@Nonnull String vmId, @Nonnull String intoDcId, @Nonnull String name, @Nonnull String description, boolean powerOn, @Nullable String ... firewallIds) throws InternalException, CloudException;               
    
    /**
     * Turns hypervisor monitoring off for the target server. If the underlying cloud does not support
     * hypervisor monitoring or if the underlying cloud does not allow them to be turned off/on for
     * a running instance, this method will be a NO-OP.
     * @param vmId the provider ID for the server to stop monitoring
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public void disableAnalytics(String vmId) throws InternalException, CloudException; 
    
    /**
     * Turns hypervisor monitoring on for the target server. If the underlying cloud does not support
     * hypervisor monitoring or if the underlying cloud does not allow them to be turned off/on for
     * a running instance, this method will be a NO-OP.
     * @param vmId the provider ID for the server to start monitoring
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public void enableAnalytics(String vmId) throws InternalException, CloudException;
    
    /**
     * Provides all output from the console of the target server since the specified Unix time.
     * @param vmId the unique ID of the target server
     * @return the current output from the server console
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull String getConsoleOutput(@Nonnull String vmId) throws InternalException, CloudException;

    /**
     * Fetches the VM product associated with a specific product ID.
     * @param productId the virtual machine product ID (flavor, size, etc.)
     * @return the product represented by the specified product ID
     * @throws InternalException an error occurred within the Dasein Cloud implementation fetching the product
     * @throws CloudException an error occurred fetching the product from the cloud
     */
    @SuppressWarnings("unused")
    public abstract @Nullable VirtualMachineProduct getProduct(@Nonnull String productId) throws InternalException, CloudException;
    
    /**
     * Assists UIs by providing the cloud-specific term for a virtual server in the cloud.
     * @param locale the locale for which the term should be translated
     * @return the provider-specific term for a virtual server
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull String getProviderTermForServer(@Nonnull Locale locale);
    
    /**
     * Provides the data from a specific virtual machine.
     * @param vmId the provider ID for the desired server
     * @return the data behind the target server
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract @Nullable VirtualMachine getVirtualMachine(@Nonnull String vmId) throws InternalException, CloudException;
    
    /**
     * Provides hypervisor statistics for the specified server that fit within the defined time range.
     * For clouds that do not provide hypervisor statistics, this method should return an empty
     * {@link VmStatistics} object and NOT <code>null</code>.
     * @param vmId the unique ID for the target server 
     * @param from the beginning of the timeframe for which you want statistics
     * @param to the end of the timeframe for which you want statistics
     * @return the statistics for the target server
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public VmStatistics getVMStatistics(String vmId, long from, long to) throws InternalException, CloudException;
    
    /**
     * Provides hypervisor statistics for the specified server that fit within the defined time range.
     * For clouds that do not provide hypervisor statistics, this method should return an empty
     * list.
     * @param vmId the unique ID for the target server 
     * @param from the beginning of the timeframe for which you want statistics
     * @param to the end of the timeframe for which you want statistics
     * @return a collection of discreet server statistics over the specified period
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract @Nonnull Iterable<VmStatistics> getVMStatisticsForPeriod(@Nonnull String vmId, @Nonnegative long from, @Nonnegative long to) throws InternalException, CloudException;
  
    
    /**
     * Indicates whether this account is subscribed to using virtual machines.
     * @return true if the subscription is valid for using virtual machines
     * @throws CloudException an error occurred querying the cloud for subscription info
     * @throws InternalException an error occurred within the implementation determining subscription state
     */
    @SuppressWarnings("unused")
    public abstract boolean isSubscribed() throws CloudException, InternalException;
    
    /**
     * Launches a virtual machine in the cloud. If the cloud supports persistent servers, this method will
     * first define a server and then boot it. The end result of this operation should be a server
     * that is in the middle of booting up.
     * @param fromMachineImageId the provider ID of the image from which the server should be built
     * @param product the product being provisioned against
     * @param dataCenterId the provider ID for the data center into which the server will be launched
     * @param name the name of the new server
     * @param description a user-friendly description of the new virtual machine
     * @param withKeypairId the name of the keypair to use for root authentication or null if no keypair
     * @param inVlanId the ID of the VLAN into which the server should be launched, or null if not specifying (or not supported by the cloud)
     * @param withAnalytics whether or not hypervisor analytics should be enabled for the virtual machine
     * @param asSandbox for clouds that require sandboxes for image building, this launches the VM in a sandbox context
     * @param firewallIds the firewalls to protect the new server
     * @return the newly launched server
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull VirtualMachine launch(@Nonnull String fromMachineImageId, @Nonnull VirtualMachineProduct product, @Nonnull String dataCenterId, @Nonnull String name, @Nonnull String description, @Nullable String withKeypairId, @Nullable String inVlanId, boolean withAnalytics, boolean asSandbox, @Nullable String ... firewallIds) throws InternalException, CloudException;
    
    /**
     * Launches a virtual machine in the cloud. If the cloud supports persistent servers, this method will
     * first define a server and then boot it. The end result of this operation should be a server
     * that is in the middle of booting up.
     * @param fromMachineImageId the provider ID of the image from which the server should be built
     * @param product the product being provisioned against
     * @param dataCenterId the provider ID for the data center into which the server will be launched
     * @param name the name of the new server
     * @param description a user-friendly description of the new virtual machine
     * @param withKeypairId the name of the keypair to use for root authentication or null if no keypair
     * @param inVlanId the ID of the VLAN into which the server should be launched, or null if not specifying (or not supported by the cloud)
     * @param withAnalytics whether or not hypervisor analytics should be enabled for the virtual machine
     * @param asSandbox for clouds that require sandboxes for image building, this launches the VM in a sandbox context
     * @param firewallIds the firewalls to protect the new server
     * @param tags a list of meta data to pass to the cloud provider
     * @return the newly launched server
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull VirtualMachine launch(@Nonnull String fromMachineImageId, @Nonnull VirtualMachineProduct product, @Nonnull String dataCenterId, @Nonnull String name, @Nonnull String description, @Nullable String withKeypairId, @Nullable String inVlanId, boolean withAnalytics, boolean asSandbox, @Nullable String[] firewallIds, @Nullable Tag ... tags) throws InternalException, CloudException;
    
    /**
     * Provides a list of firewalls protecting the specified server. If firewalls are not supported
     * in this cloud, the list will be empty.
     * @param vmId the server ID whose firewall list is being sought
     * @return the list of firewalls protecting the target server
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull Iterable<String> listFirewalls(@Nonnull String vmId) throws InternalException, CloudException;
    
    /**
     * Provides a list of instance types, service offerings, or server sizes (however the underlying cloud
     * might describe it) for a particular architecture
     * @param architecture the desired architecture size offerings
     * @return the list of server sizes available for the specified architecture
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public Iterable<VirtualMachineProduct> listProducts(Architecture architecture) throws InternalException, CloudException;
    
    /**
     * Lists all virtual machines belonging to the account owner currently in the cloud.
     * @return all servers belonging to the account owner
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull Iterable<VirtualMachine> listVirtualMachines() throws InternalException, CloudException;

    /**
     * Shuts down the target virtual machine. This method is a NO-OP in clouds that lack persistent
     * servers. The result of this method should be either a) a server that is still runnning
     * (for non-persistent server clouds) or b) paused and capable of being restarted (for persistent
     * server clouds). In no case should this method cause a destructive event such as the loss
     * of a server.
     * @param vmId the provider ID for the server to pause
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract void pause(@Nonnull String vmId) throws InternalException, CloudException;
    
    /**
     * Executes a virtual machine reboot for the target virtual machine.
     * @param vmId the provider ID for the server to reboot
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract void reboot(@Nonnull String vmId) throws CloudException, InternalException;

    /**
     * Identifies whether or not this cloud supports hypervisor-based analytics around usage and performance.
     * @return true if this cloud supports hypervisor-based analytics
     * @throws CloudException an error occurred with the cloud provider determining analytics support
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining analytics support
     */
    @SuppressWarnings("unused")
    public abstract boolean supportsAnalytics() throws CloudException, InternalException;
    
    /**
     * TERMINATES AND DESTROYS the specified virtual machine. If it is running, it will be stopped. Once it is
     * stopped, all of its data will be destroyed and it will no longer be usable. This is a very 
     * dangerous operation, especially in clouds with persistent servers.
     * @param vmId the provider ID of the server to be destroyed
     * @throws InternalException an error occurred within the Dasein Cloud API implementation
     * @throws CloudException an error occurred within the cloud provider
     */
    @SuppressWarnings("unused")
    public abstract void terminate(@Nonnull String vmId) throws InternalException, CloudException;    
}
