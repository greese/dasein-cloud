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

import javax.annotation.Nullable;

public interface NetworkServices {
    public abstract @Nullable DNSSupport getDnsSupport();
    
    public abstract @Nullable FirewallSupport getFirewallSupport();
    
    public abstract @Nullable IpAddressSupport getIpAddressSupport();
    
    public abstract @Nullable LoadBalancerSupport getLoadBalancerSupport();
    
    public abstract @Nullable VLANSupport getVlanSupport();
    
    public abstract @Nullable VPNSupport getVpnSupport();
    
    public abstract boolean hasDnsSupport();
    
    public abstract boolean hasFirewallSupport();
    
    public abstract boolean hasIpAddressSupport();
    
    public abstract boolean hasLoadBalancerSupport();
    
    public abstract boolean hasVlanSupport();
    
    public abstract boolean hasVpnSupport();
}
