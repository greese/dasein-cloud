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

import java.io.Serializable;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;


/**
 * Represents a rule for forwarding traffic from a public IP address and port to a private
 * IP address and port. 
 * @author George Reese @ enStratus (http://www.enstratus.com)
 *
 */
public class IpForwardingRule implements Comparable<IpForwardingRule>, Serializable {
    private static final long serialVersionUID = -4772439155723800830L;
    
    private String   addressId;
    private int      privatePort;
    private Protocol protocol;
    private String   providerRuleId;
    private int      publicPort;
    private String   serverId;
    
    public IpForwardingRule() { }
    
    /**
     * Compares based on address ID, public port, server, private port, protocol, then rule ID.
     */
    public int compareTo(@Nonnull IpForwardingRule other) {
        int x;
        
        if( other == this ) {
            return 0;
        }
        if( providerRuleId.equals(other.providerRuleId) ) {
            return 0;
        }
        x = addressId.compareTo(other.addressId);
        if( x != 0 ) {
            return x;
        }
        x = (new Integer(publicPort)).compareTo(other.publicPort);
        if( x != 0 ) {
            return x;
        }
        x = serverId.compareTo(other.serverId);
        if( x != 0 ) {
            return x;
        }
        x = (new Integer(privatePort)).compareTo(other.privatePort);
        if( x != 0 ) {
            return x;
        }
        x = protocol.compareTo(other.protocol);
        if( x != 0 ) {
            return x;
        }
        return providerRuleId.compareTo(other.providerRuleId);
    }

    /**
     * Two rules are equal if they have the same ID.
     */
    public boolean equals(@CheckForNull Object ob) {
        IpForwardingRule other;

        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        other = (IpForwardingRule) ob;
        return providerRuleId.equals(other.providerRuleId);
    }

    /**
     * @return the unique ID of the public address being forwarded 
     */
    public @Nonnull String getAddressId() {
        return addressId;
    }

    /**
     * @return the port to which traffic is being forwarded
     */
    public int getPrivatePort() {
        return privatePort;
    }

    /**
     * @return the protocol for the kind of traffic being forwarded
     */
    public @Nonnull Protocol getProtocol() {
        return protocol;
    }

    /**
     * @return the unique ID for this forwarding rule
     */
    public @Nonnull String getProviderRuleId() {
        return providerRuleId;
    }

    /**
     * @return the public port of traffic to be forwarded
     */
    public int getPublicPort() {
        return publicPort;
    }

    /**
     * @return the unique ID of the server to which traffic is being forwarded
     */
    public @Nonnull String getServerId() {
        return serverId;
    }

    public int hashCode() {
        return providerRuleId.hashCode();
    }
    
    public void setAddressId(@Nonnull String addressId) {
        this.addressId = addressId;
    }

    public void setPrivatePort(int privatePort) {
        this.privatePort = privatePort;
    }

    public void setProtocol(@Nonnull Protocol protocol) {
        this.protocol = protocol;
    }

    public void setProviderRuleId(@Nonnull String providerRuleId) {
        this.providerRuleId = providerRuleId;
    }

    public void setPublicPort(int publicPort) {
        this.publicPort = publicPort;
    }

    public void setServerId(@Nonnull String serverId) {
        this.serverId = serverId;
    }
    
    public @Nonnull String toString() {
        return (addressId + ":" + publicPort + " -> " + serverId + ":" + privatePort + " (" + protocol + ")");
    }
}
