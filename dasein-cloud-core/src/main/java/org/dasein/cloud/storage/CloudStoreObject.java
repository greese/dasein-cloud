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

package org.dasein.cloud.storage;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *   Represents a raw file or object stored in cloud storage.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class CloudStoreObject implements Serializable, Comparable<CloudStoreObject> {
	private static final long serialVersionUID = 2395682226070610011L;
	
	private String  providerRegionId;
    private boolean container;
    private Date    creationDate;
    private String  directory;
    private String  location;
    private String  name;
    private long    size;
    
    public CloudStoreObject() { }
    
    public int compareTo(CloudStoreObject other) {
        int x;
        
        if( other == null ) {
            return 1;
        }
        if( other == this ) {
            return 0;
        }
        if( directory != null && other.directory == null ) {
            return 1;
        }
        else if( directory == null && other.directory != null ) {
            return -1;
        }
        else if( directory != null && other.directory != null ) {
            x = directory.compareTo(other.directory);
            if( x != 0 ) {
                return x;
            }
        }
        return name.compareTo(other.name);
    }
    
    public boolean equals(Object other) {
        if( other == null ) {
            return false;
        }
        if( other == this ) {
            return true;
        }
        if( !other.getClass().getName().equals(getClass().getName()) ) {
            return false;
        }
        CloudStoreObject file = (CloudStoreObject)other;
        if( (file.directory == null && directory != null) || (file.directory != null && directory == null) ) {
            return false;
        }
        if( directory != null && !directory.equals(file.directory) ) {
            return false;
        }
        return name.equals(file.name);
    }

    public boolean isContainer() {
        return container;
    }

    public void setContainer(boolean container) {
        this.container = container;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProviderRegionId() {
        return providerRegionId;
    }
    
    public void setProviderRegionId(String rid) {
        providerRegionId = rid;
    }
    
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }    

    public String toString() {
        return (providerRegionId + "/" + directory + "/" + name + " [" + size + "]");
    }
}
