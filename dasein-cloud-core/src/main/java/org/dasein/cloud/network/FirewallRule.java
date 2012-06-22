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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * <p>
 * Describes a specific firewall rule allowing access through the target firewall.
 * </p>
 * @author George Reese (george.reese@imaginary.com)
 * @since 2010.08
 * @version 2010.08
 * @version 2012.02 - Added annotations
 */
public class FirewallRule implements Serializable {
    private static final long serialVersionUID = -3830971132733183138L;
    
    static public String getRuleId(String providerFirewallId, String cidr, Direction direction, Protocol protocol, int startPort, int endPort) {
        return providerFirewallId + ":" + cidr + ":" + direction + ":" + protocol + ":" + startPort + ":" + endPort;
    }
    
    private String     cidr;
    private Direction  direction = Direction.INGRESS;
    private int        endPort;
    private String     firewallId;
    private Permission permission = Permission.ALLOW;
    private Protocol   protocol;
    private String     providerRuleId;
    private int        startPort;
    
    /**
     * Constructs an empty firewall rule for marshalling purposes.
     */
    @SuppressWarnings("unused")
    public FirewallRule() { }

    @Override
    public boolean equals(Object other) {
        if( other == null ) {
            return false;
        }
        if( other == this ) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if( !getClass().getName().equals(other.getClass().getName()) ) {
            return false;
        }
        return getProviderRuleId().equals(((FirewallRule)other).getProviderRuleId());
    }

    /**
     * The source IP address or range of addresses in CIDR format. IP addresses matching
     * this CIDR have positive access to any server protected by this firewall to the port
     * range specified over the protocol specified.
     * @return the source CIDR for this rule
     */
    @SuppressWarnings("unused")
    public @Nullable String getCidr() {
        return cidr;
    }
    
    /**
     * @return the last port in the range of ports allowed by this rule
     */
    @SuppressWarnings("unused")
    public int getEndPort() {
        return endPort;
    }
    
    /**
     * @return the unique provider ID for the firewall behind this rule
     */
    @SuppressWarnings("unused")
    public @Nullable String getFirewallId() {
        return firewallId;
    }
    
    /**
     * @return the network protocol to allow through to the target ports
     */
    @SuppressWarnings("unused")
    public @Nullable Protocol getProtocol() {
        return protocol;
    }
    
    /**
     * @return the first port in the range of ports allowed by this rule
     */
    @SuppressWarnings("unused")
    public int getStartPort() {
        return startPort;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param cidr the source CIDR for the rule being marshalled
     */
    @SuppressWarnings("unused")
    public void setCidr(@Nonnull String cidr) {
        this.cidr = cidr;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param endPort the end port for the rule being marshalled
     */
    @SuppressWarnings("unused")
    public void setEndPort(int endPort) {
        this.endPort = endPort;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param firewallId the unique provider ID for the firewall for the rule being marshalled
     */
    @SuppressWarnings("unused")
    public void setFirewallId(@Nonnull String firewallId) {
        this.firewallId = firewallId;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param protocol the protocol for the rule being marshalled
     */
    @SuppressWarnings("unused")
    public void setProtocol(@Nonnull Protocol protocol) {
        this.protocol = protocol;
    }
    
    /**
     * Used only for marshalling and not to be used programatically.
     * @param startPort the start port for the rule being marshalled
     */
    @SuppressWarnings("unused")
    public void setStartPort(int startPort) {
        this.startPort = startPort;
    }

    /**
     * Defines the direction in which this rule operates.
     * @param direction the direction in which the rule operates
     */
    @SuppressWarnings("unused")
    public void setDirection(@Nonnull Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the direction in which this rule operates
     */
    @SuppressWarnings("unused")
    public @Nullable Direction getDirection() {
        return direction;
    }

    /**
     * Specifies the permission behind the rule.
     * @param permission the permission behind the rule
     */
    @SuppressWarnings("unused")
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    /**
     * @return the permission behind this rule
     */
    @SuppressWarnings("unused")
    public Permission getPermission() {
        return permission;
    }

    /**
     * Specifies the unique ID that identifies this rule in the target cloud. Note that Dasein Cloud
     * has a default identification mechanism for clouds without unique IDs for rules, so 
     * any attempt to fetch the unique rule ID via {@link #getProviderRuleId()} will provide a
     * value even if none has been set by this method.
     * @param providerRuleId the unique ID for identifying the rule with the cloud provider
     */
    @SuppressWarnings("unused")
    public void setProviderRuleId(String providerRuleId) {
        this.providerRuleId = providerRuleId;
    }

    /**
     * @return a unique ID that identifies this rule to the target cloud
     */
    @SuppressWarnings("unused")
    public @Nonnull String getProviderRuleId() {
        if( providerRuleId == null ) {
            return FirewallRule.getRuleId(getFirewallId(), getCidr(), getDirection(), getProtocol(), getStartPort(), getEndPort());
        }
        return providerRuleId;
    }

    @Override
    public String toString() {
        return (direction + "/" + permission + ": " + cidr + "->" + protocol + ":" + startPort + "-" + endPort);
    }
}
