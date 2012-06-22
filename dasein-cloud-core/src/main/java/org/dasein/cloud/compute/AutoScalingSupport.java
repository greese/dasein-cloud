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

import java.util.Collection;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

public interface AutoScalingSupport extends AccessControlledService {
    static public final ServiceAction ANY                         = new ServiceAction("SCALING:ANY");

    static public final ServiceAction CREATE_LAUNCH_CONFIGURATION = new ServiceAction("SCALING:CREATE_LAUNCH_CONFIGURATION");
    static public final ServiceAction CREATE_SCALING_GROUP        = new ServiceAction("SCALING:CREATE_SCALING_GROUP");
    static public final ServiceAction GET_LAUNCH_CONFIGURATION    = new ServiceAction("SCALING:GET_LAUNCH_CONFIGURATION");
    static public final ServiceAction GET_SCALING_GROUP           = new ServiceAction("SCALING:GET_SCALING_GROUP");
    static public final ServiceAction LIST_LAUNCH_CONFIGURATION   = new ServiceAction("SCALING:LIST_LAUNCH_CONFIGURATION");
    static public final ServiceAction LIST_SCALING_GROUP          = new ServiceAction("SCALING:LIST_SCALING_GROUP");
    static public final ServiceAction REMOVE_LAUNCH_CONFIGURATION = new ServiceAction("SCALING:REMOVE_LAUNCH_CONFIGURATION");
    static public final ServiceAction REMOVE_SCALING_GROUP        = new ServiceAction("SCALING:REMOVE_SCALING_GROUP");
    static public final ServiceAction SET_CAPACITY                = new ServiceAction("SCALING:SET_CAPACITY");
    static public final ServiceAction SET_SCALING_TRIGGER         = new ServiceAction("SCALING:SET_SCALING_TRIGGER");
    static public final ServiceAction UPDATE_SCALING_GROUP        = new ServiceAction("SCALING:UPDATE_SCALING_GROUP");
    
    public String createAutoScalingGroup(String name, String launchConfigurationId, int minServers, int maxServers, int cooldown, String ... dataCenterIds) throws InternalException, CloudException;
        
    public String createLaunchConfiguration(String name, String imageId, VirtualMachineProduct size, String ... firewalls) throws InternalException, CloudException;
        
    public void deleteAutoScalingGroup(String providerAutoScalingGroupId) throws CloudException, InternalException;
    
    public void deleteLaunchConfiguration(String providerLaunchConfigurationId) throws CloudException, InternalException;
    
    public LaunchConfiguration getLaunchConfiguration(String providerLaunchConfigurationId) throws CloudException, InternalException;

    public ScalingGroup getScalingGroup(String providerScalingGroupId) throws CloudException, InternalException;

    public boolean isSubscribed() throws CloudException, InternalException;

    public Collection<ScalingGroup> listScalingGroups() throws CloudException, InternalException;
    
    public Collection<LaunchConfiguration> listLaunchConfigurations() throws CloudException, InternalException;

    public void setDesiredCapacity(String scalingGroupId, int capacity) throws CloudException, InternalException;
        
    public String setTrigger(String name, String scalingGroupId, String statistic, String unitOfMeasure, String metric, int periodInSeconds, double lowerThreshold, double upperThreshold, int lowerIncrement, boolean lowerIncrementAbsolute, int upperIncrement, boolean upperIncrementAbsolute, int breachDuration) throws InternalException, CloudException;
    
    public void updateAutoScalingGroup(String scalingGroupId, String launchConfigurationId, int minServers, int maxServers, int cooldown, String ... zoneIds) throws InternalException, CloudException;
        
}
