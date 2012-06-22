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

package org.dasein.cloud.identity;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.network.NetworkServices;

import javax.annotation.Nonnull;

/**
 * Represents a mechanism for identifying service actions in a cloud provider using a generic label. Each Dasein
 * Cloud service specifies the actions it supports using a fixed enumeration. Each service then provides a
 * translation method (<code>mapServiceAction()</code>) that maps the Dasein Cloud service action to an
 * ID relevant to the underlying cloud.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public class ServiceAction {
    private String actionId;
    private String serviceId;

    public ServiceAction(String actionId) {
        int idx = actionId.indexOf(":");

        this.actionId = actionId;
        serviceId = (idx < 0 ? "" : this.actionId.substring(0, idx));
    }
    
    public boolean equals(Object other) {
        if( other == null ) {
            return false;
        }
        if( other == this ) {
            return true;
        }
        return (getClass().getName().equals(other.getClass().getName()) && getActionId().equals(((ServiceAction)other).getActionId()));
    }
    
    public String getActionId() {
        return actionId;
    }

    public @Nonnull String[] map(@Nonnull CloudProvider provider) {
        AccessControlledService svc = null;

        if( serviceId.equals("DNS") ) {
            NetworkServices services = provider.getNetworkServices();

            if( services == null ) {
                return null;
            }
            svc = services.getDnsSupport();
        }
        else if( serviceId.equals("IAM") ) {
            IdentityServices services = provider.getIdentityServices();

            if( services == null ) {
                return null;
            }
            svc = services.getIdentityAndAccessSupport();
        }
        else if( serviceId.equals("FW") ) {
            NetworkServices services = provider.getNetworkServices();

            if( services == null ) {
                return null;
            }
            svc = services.getFirewallSupport();
        }
        else if( serviceId.equals("LB") ) {
            NetworkServices services = provider.getNetworkServices();

            if( services == null ) {
                return null;
            }
            svc = services.getLoadBalancerSupport();
        }
        if( svc == null ) {
            return new String[0];
        }
        return svc.mapServiceAction(this);
    }

    public int hashCode() {
        return actionId.hashCode();
    }
    
    public String toString() {
        return actionId;
    }
}
