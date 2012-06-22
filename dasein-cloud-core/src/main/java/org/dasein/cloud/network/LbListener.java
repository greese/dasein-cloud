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

public class LbListener implements Serializable {
    private static final long serialVersionUID = -4824574497439323928L;
    
    private LbAlgorithm algorithm;
    private LbProtocol  networkProtocol;
    private int         privatePort;
    private int         publicPort;
    
    public LbListener() { }

    public LbAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(LbAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public LbProtocol getNetworkProtocol() {
        return networkProtocol;
    }

    public void setNetworkProtocol(LbProtocol networkProtocol) {
        this.networkProtocol = networkProtocol;
    }

    public int getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(int privatePort) {
        this.privatePort = privatePort;
    }

    public int getPublicPort() {
        return publicPort;
    }

    public void setPublicPort(int publicPort) {
        this.publicPort = publicPort;
    }
    
    public String toString() {
        return (networkProtocol + "/" + algorithm + ": " + publicPort + " -> " + privatePort);
    }
}
