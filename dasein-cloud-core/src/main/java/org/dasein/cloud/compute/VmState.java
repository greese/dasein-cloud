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

/**
 * <p>
 * The various possible states in which a server can exist. Implementors should do their best to
 * map cloud-specific states to these states.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public enum VmState {
    /**
     * The server is paused and not currently running or scheduled to run.
     */
    PAUSED, 
    /**
     * A request has been made to launch the server, but it is not currently fully operational.
     */
    PENDING, 
    /**
     * The server is fully operational as far as we know.
     */
    RUNNING, 
    /**
     * The server is currently in the middle of a reboot and should be operational shortly.
     */
    REBOOTING, 
    /**
     * The server is currentlyin the middle of being terminated and should not be expected to be
     * available ever again.
     */
    STOPPING, 
    /**
     * The server has been terminated and is thus entirely useless and all state information is lost.
     */
    TERMINATED
}
