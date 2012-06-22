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

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

import javax.annotation.Nonnull;

public interface LoadBalancerSupport extends AccessControlledService {
    static public final ServiceAction ANY                  = new ServiceAction("LB:ANY");

    static public final ServiceAction ADD_DATA_CENTERS     = new ServiceAction("LB:ADD_DC");
    static public final ServiceAction ADD_VMS              = new ServiceAction("LB:ADD_VM");
    static public final ServiceAction CREATE_LOAD_BALANCER = new ServiceAction("LB:CREATE_LOAD_BALANCER");
    static public final ServiceAction GET_LOAD_BALANCER    = new ServiceAction("LB:GET_LOAD_BALANCER");
    static public final ServiceAction LIST_LOAD_BALANCER   = new ServiceAction("LB:LIST_LOAD_BALANCER");
    static public final ServiceAction REMOVE_DATA_CENTERS  = new ServiceAction("LB:REMOVE_DC");
    static public final ServiceAction REMOVE_VMS           = new ServiceAction("LB:REMOVE_VM");
    static public final ServiceAction REMOVE_LOAD_BALANCER = new ServiceAction("LB:REMOVE_LOAD_BALANCER");
    
    public void addDataCenters(String toLoadBalancerId, String ... dataCenterIdsToAdd) throws CloudException, InternalException;

    public void addServers(String toLoadBalancerId, String ... serverIdsToAdd) throws CloudException, InternalException;
    
    public String create(String name, String description, String addressId, String[] dataCenterIds, LbListener[] listeners, String[] serverIds) throws CloudException, InternalException;

    public LoadBalancer getLoadBalancer(String loadBalancerId) throws CloudException, InternalException;
    
    public LoadBalancerAddressType getAddressType() throws CloudException, InternalException;
    
    public int getMaxPublicPorts() throws CloudException, InternalException;
    
    public String getProviderTermForLoadBalancer(Locale locale);
    
    public Iterable<LbAlgorithm> listSupportedAlgorithms() throws CloudException, InternalException;
    
    public Iterable<LbProtocol> listSupportedProtocols() throws CloudException, InternalException;
    
    public boolean isAddressAssignedByProvider() throws CloudException, InternalException;
    
    public boolean isDataCenterLimited() throws CloudException, InternalException;

    public boolean requiresListenerOnCreate() throws CloudException, InternalException;
    
    public boolean requiresServerOnCreate() throws CloudException, InternalException;
    
    public boolean isSubscribed() throws CloudException, InternalException;

    public boolean supportsMonitoring() throws CloudException, InternalException;
    
    public Iterable<LoadBalancer> listLoadBalancers() throws CloudException, InternalException;
    
    public void remove(String loadBalancerId) throws CloudException, InternalException;
    
    public void removeDataCenters(String fromLoadBalancerId, String ... dataCenterIdsToRemove) throws CloudException, InternalException;
    
    public void removeServers(String fromLoadBalancerId, String ... serverIdsToRemove) throws CloudException, InternalException;
}
