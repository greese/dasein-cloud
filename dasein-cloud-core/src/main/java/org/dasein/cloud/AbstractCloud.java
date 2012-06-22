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

package org.dasein.cloud;

import javax.annotation.Nullable;

import org.dasein.cloud.admin.AdminServices;
import org.dasein.cloud.compute.ComputeServices;
import org.dasein.cloud.dc.DataCenterServices;
import org.dasein.cloud.identity.IdentityServices;
import org.dasein.cloud.network.NetworkServices;
import org.dasein.cloud.platform.PlatformServices;

public abstract class AbstractCloud extends CloudProvider {
    public AbstractCloud() { }

    @Override
    public @Nullable AdminServices getAdminServices() {
        return null;
    }
    
    @Override
    public @Nullable ComputeServices getComputeServices() {
        CloudProvider compute = getComputeCloud();
        
        return (compute == null ? null : compute.getComputeServices());
    }
    
    @Override
    public @Nullable DataCenterServices getDataCenterServices() {
        throw new RuntimeException("A cloud must have a data center services implementation.");
    }
    
    @Override
    public @Nullable IdentityServices getIdentityServices() {
        CloudProvider compute = getComputeCloud();
        
        return (compute == null ? null : compute.getIdentityServices());
    }
    
    @Override
    public @Nullable NetworkServices getNetworkServices() {
        CloudProvider compute = getComputeCloud();
        
        return (compute == null ? null : compute.getNetworkServices());
    }
    
    @Override
    public @Nullable PlatformServices getPlatformServices() {
        CloudProvider compute = getComputeCloud();
        
        return (compute == null ? null : compute.getPlatformServices());
    }
}
