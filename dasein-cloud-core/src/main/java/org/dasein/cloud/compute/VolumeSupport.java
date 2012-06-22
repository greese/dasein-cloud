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

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

public interface VolumeSupport extends AccessControlledService {
    static public final ServiceAction ANY               = new ServiceAction("VOLUME:ANY");

    static public final ServiceAction ATTACH            = new ServiceAction("VOLUME:ATTACH");
    static public final ServiceAction CREATE_VOLUME     = new ServiceAction("VOLUME:CREATE_VOLUME");
    static public final ServiceAction DETACH            = new ServiceAction("VOLUME:DETACH");
    static public final ServiceAction GET_VOLUME        = new ServiceAction("VOLUME:GET_VOLUME");
    static public final ServiceAction LIST_VOLUME       = new ServiceAction("VOLUME:LIST_VOLUME");
    static public final ServiceAction REMOVE_VOLUME     = new ServiceAction("VOLUME:REMOVE_VOLUME");

    public void attach(String volumeId, String toServer, String device) throws InternalException, CloudException;
    
    public String create(String fromSnapshot, int sizeInGb, String inZone) throws InternalException, CloudException;
    
    public void detach(String volumeId) throws InternalException, CloudException;
    
    public String getProviderTermForVolume(Locale locale);
    
    public Volume getVolume(String volumeId) throws InternalException, CloudException;
    
    public Iterable<String> listPossibleDeviceIds(Platform platform) throws InternalException, CloudException;
    
    public Iterable<Volume> listVolumes() throws InternalException, CloudException;

    public boolean isSubscribed() throws CloudException, InternalException;
    
    public void remove(String volumeId) throws InternalException, CloudException;
}
