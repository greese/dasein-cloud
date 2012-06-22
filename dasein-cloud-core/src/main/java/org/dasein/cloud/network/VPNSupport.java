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

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

import javax.annotation.Nonnull;

public interface VPNSupport extends AccessControlledService {
    static public final ServiceAction ANY             = new ServiceAction("VPN:ANY");

    static public final ServiceAction ATTACH          = new ServiceAction("VPN:ATTACH");
    static public final ServiceAction CREATE_GATEWAY  = new ServiceAction("VPN:CREATE_GATEWAY");
    static public final ServiceAction CREATE_VPN      = new ServiceAction("VPN:CREATE_VPN");
    static public final ServiceAction DETACH          = new ServiceAction("VPN:DETACH");
    static public final ServiceAction GET_GATEWAY     = new ServiceAction("VPN:GET_GATEWAY");
    static public final ServiceAction GET_VPN         = new ServiceAction("VPN:GET_VPN");
    static public final ServiceAction LIST_GATEWAY    = new ServiceAction("VPN:LIST_GATEWAY");
    static public final ServiceAction LIST_VPN        = new ServiceAction("VPN:LIST_VPN");
    static public final ServiceAction REMOVE_GATEWAY  = new ServiceAction("VPN:REMOVE_GATEWAY");
    static public final ServiceAction REMOVE_VPN      = new ServiceAction("VPN:REMOVE_VPN");

    public abstract void attachToVLAN(String providerVpnId, String providerVlanId) throws CloudException, InternalException;
    
    public abstract void connectToGateway(String providerVpnId, String toGatewayId) throws CloudException, InternalException;
    
    public abstract VPN createVPN(String inProviderDataCenterId, String name, String description, VPNProtocol protocol) throws CloudException, InternalException;
    
    public abstract VPNGateway createVPNGateway(String endpoint, String name, String description, VPNProtocol protocol, String bgpAsn) throws CloudException, InternalException;
    
    public abstract void deleteVPN(String providerVpnId) throws CloudException, InternalException;
    
    public abstract void deleteVPNGateway(String providerVPNGatewayId) throws CloudException, InternalException;
    
    public abstract void detachFromVLAN(String providerVpnId, String providerVlanId) throws CloudException, InternalException;
    
    public abstract void disconnectFromGateway(String providerVpnId) throws CloudException, InternalException;
    
    public abstract VPN getVPN(String providerVpnId) throws CloudException, InternalException;
    
    public abstract Iterable<VPN> listVPNs() throws CloudException, InternalException;
    
    public abstract boolean isSubscribed() throws CloudException, InternalException;
}
