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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

public interface VLANSupport extends AccessControlledService {
    static public final ServiceAction ANY               = new ServiceAction("NET:ANY");

    static public final ServiceAction CREATE_SUBNET     = new ServiceAction("NET:CREATE_SUBNET");
    static public final ServiceAction CREATE_VLAN       = new ServiceAction("NET:CREATE_VLAN");
    static public final ServiceAction GET_SUBNET        = new ServiceAction("NET:GET_SUBNET");
    static public final ServiceAction GET_VLAN          = new ServiceAction("NET:GET_VLAN");
    static public final ServiceAction LIST_SUBNET       = new ServiceAction("NET:LIST_SUBNET");
    static public final ServiceAction LIST_VLAN         = new ServiceAction("NET:LIST_VLAN");
    static public final ServiceAction REMOVE_SUBNET     = new ServiceAction("NET:REMOVE_SUBNET");
    static public final ServiceAction REMOVE_VLAN       = new ServiceAction("NET:REMOVE_VLAN");

    public abstract boolean allowsNewVlanCreation() throws CloudException, InternalException;
    
    public abstract boolean allowsNewSubnetCreation() throws CloudException, InternalException;

    public abstract Subnet createSubnet(String cidr, String inProviderVlanId, String name, String description) throws CloudException, InternalException;
    
    public abstract VLAN createVlan(String cidr, String name, String description, String domainName, String[] dnsServers, String[] ntpServers) throws CloudException, InternalException;
    
    public abstract int getMaxVlanCount() throws CloudException, InternalException;
    
    public abstract String getProviderTermForNetworkInterface(Locale locale);
    
    public abstract String getProviderTermForSubnet(Locale locale);
    
    public abstract String getProviderTermForVlan(Locale locale);
    
    public abstract @Nullable Subnet getSubnet(@Nonnull String subnetId) throws CloudException, InternalException;
    
    public abstract @Nullable VLAN getVlan(@Nonnull String vlanId) throws CloudException, InternalException;
    
    public abstract boolean isSubscribed() throws CloudException, InternalException;
    
    public abstract boolean isSubnetDataCenterConstrained() throws CloudException, InternalException;

    public abstract boolean isVlanDataCenterConstrained() throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<NetworkInterface> listNetworkInterfaces(@Nonnull String forVmId) throws CloudException, InternalException;

    public abstract @Nonnull Iterable<Subnet> listSubnets(@Nonnull String inVlanId) throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<VLAN> listVlans() throws CloudException, InternalException;

    public abstract void removeSubnet(String providerSubnetId) throws CloudException, InternalException;
    
    public abstract void removeVlan(String vlanId) throws CloudException, InternalException; 
    
    public abstract boolean supportsVlansWithSubnets() throws CloudException, InternalException;
}
