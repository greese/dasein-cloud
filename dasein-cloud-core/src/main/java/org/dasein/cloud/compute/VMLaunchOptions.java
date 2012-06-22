/**
 * ========= CONFIDENTIAL =========
 *
 * Copyright (C) 2012 enStratus Networks Inc - ALL RIGHTS RESERVED
 *
 * ====================================================================
 *  NOTICE: All information contained herein is, and remains the
 *  property of enStratus Networks Inc. The intellectual and technical
 *  concepts contained herein are proprietary to enStratus Networks Inc
 *  and may be covered by U.S. and Foreign Patents, patents in process,
 *  and are protected by trade secret or copyright law. Dissemination
 *  of this information or reproduction of this material is strictly
 *  forbidden unless prior written permission is obtained from
 *  enStratus Networks Inc.
 * ====================================================================
 */
package org.dasein.cloud.compute;

/**
 * Configuration of a virtual machine to be launched prior to launch. Dasein Cloud uses this to create a complete
 * virtual machine to the caller's specifications. Implementations may be able to realize a launch configuration in
 * a single API call, or they may require many underlying API calls. All of this will be hidden to the caller.
 * <p>Created by George Reese: 6/22/12 5:35 PM</p>
 * @author George Reese
 * @since 2012-07
 * @version 2012-07 Initial introduction
 */
public class VMLaunchOptions {
    private String description;
    private String friendlyName;
    private String hostName;
    private String machineImageId;
    private String networkProductId;
    private String rootVolumeProductId;
    private String standardProductId;
    
    public VMLaunchOptions(String standardProductId, String machineImageId, String friendlyName, String description) {
        this.standardProductId = standardProductId;
        this.machineImageId = machineImageId;
        this.description = description;
        this.friendlyName = friendlyName;
        this.hostName = friendlyName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getFriendlyName() {
        return friendlyName;
    }
    
    public String getHostName() {
        return hostName;
    }
            
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    
    public String getMachineImageId() {
        return machineImageId;
    }
    
    public String getNetworkProductId() {
        return networkProductId;
    }
}
