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
 * An operating system associated with servers and images.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public enum Platform {
    /**
     * Generic UNIX
     */
    UNIX, 
    /**
     * Ubuntu
     */
    UBUNTU,
    /**
     * Debian
     */
    DEBIAN,
    /**
     * Solaris
     */
    SOLARIS,
    /**
     * Fedora Core
     */
    FEDORA_CORE,
    /**
     * RHEL
     */
    RHEL,
    /**
     * FreeBSD
     */
    FREE_BSD,
    /**
     * OpenBSD
     */
    OPEN_BSD,
    /**
     * CentOS
     */
    CENT_OS,
    /**
     * Generic Windows
     */
    WINDOWS, 
    /**
     * No clue
     */
    UNKNOWN;
    
    static public Platform guess(String name) {
        
        if( name == null ) {
            return UNKNOWN;
        }
        name = name.toLowerCase();
        if( name.indexOf("centos") > -1 ) {
            return CENT_OS;
        }
        else if( name.indexOf("ubuntu") > -1 ) {
            return UBUNTU;
        }
        else if( name.indexOf("fedora") > -1 ) {
            return FEDORA_CORE;
        }
        else if( name.indexOf("windows") > -1 ) {
            return WINDOWS;
        }
        else if( name.indexOf("linux") > -1 ) {
            return UNIX;
        }
        else if( name.indexOf("red hat") > -1 || name.indexOf("redhat") > -1  || name.indexOf("red-hat") > -1 || name.indexOf("rhel") > -1 ) {
            return RHEL;
        }
        else if( name.indexOf("debian") > -1 ) {
            return DEBIAN;
        }
        else if( name.indexOf("bsd") > -1 ) {
            if( name.indexOf("free") > -1 ) {
                return FREE_BSD;
            }
            else if( name.indexOf("open") > -1 ) {
                return OPEN_BSD;
            }
            else {
                return UNIX;
            }
        }
        else if( name.indexOf("solaris") > -1 ) {
            return SOLARIS;
        }
        return Platform.UNKNOWN;
    }
    
    /**
     * Provides an appropriate device ID (e.g. sdh) for this platform given a device letter.
     * @param letter the letter to be mapped into a platform-specific device ID
     * @return the platform-specific device ID for the target letter
     */
    public String getDeviceId(String letter) {
        switch( this ) {
        case WINDOWS: return "xvd" + letter;
        default: return "sd" + letter;
        }
    }
    
    /**
     * Provides a device mapping (e.g. /dev/sdh) for the target device letter.
     * @param letter the letter to be mapped
     * @return the device mapping for the specified letter
     */
    public String getDeviceMapping(String letter) {
        switch( this ) {
        case WINDOWS: return "xvd" + letter;
        default: return "/dev/sd" + letter;
        }
    }
    
    public boolean isBsd() {
        return (equals(FREE_BSD) || equals(OPEN_BSD));
    }
    
    public boolean isLinux() {
        switch( this ) {
        case SOLARIS: case FREE_BSD: case OPEN_BSD: case WINDOWS: case UNKNOWN: return false;
        default: return true;
        }
    }
    
    public boolean isOpen() {
        return (isLinux() || isBsd() || equals(SOLARIS));
    }
    
    public boolean isUnix() {
        return (!isWindows() && !equals(UNKNOWN));
    }
        
    public boolean isWindows() {
        return equals(WINDOWS);
    }
    
    public String toString() {
        switch( this ) {
        case UNIX: return "Generic Unix"; 
        case UBUNTU: return "Ubuntu";
        case DEBIAN: return "Debian";
        case SOLARIS: return "Solaris";
        case FEDORA_CORE: return "Fedora";
        case RHEL: return "Red Hat";
        case FREE_BSD: return "FreeBSD";
        case OPEN_BSD: return "OpenBSD";
        case CENT_OS: return "CentOS";
        case WINDOWS: return "Windows";
        }
        return "Unknown";
    }
}
