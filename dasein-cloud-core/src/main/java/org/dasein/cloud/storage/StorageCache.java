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

import java.util.ArrayList;

import org.dasein.util.CalendarWrapper;

import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;

/**
 * <p>
 * Caches the files stored in cloud storage so that not every call needs to go into the cloud.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class StorageCache {
    static public class CacheEntry {
        private Iterable<CacheEntry> children = null;
        private CloudStoreObject      file     = null;
        
        public Iterable<CacheEntry> list(StorageCache myCache) throws CloudException, InternalException {
            if( children == null ) {
                String directoryName = file.getDirectory();
                
                if( directoryName == null ) {
                    directoryName = file.getName();
                }
                else {
                    directoryName = directoryName + "." + file.getName();
                }
                children = myCache.toEntries(myCache.services.listFiles(directoryName));
            }
            return children;
        }
    }
    
    private long                 lastRefresh = -1L;
    private Iterable<CacheEntry> root        = null;
    private BlobStoreSupport      services    = null;
    
    public StorageCache(BlobStoreSupport services) {
        this.services = services;
    }
    
    public synchronized Iterable<CloudStoreObject> list() throws CloudException, InternalException {
        if( root == null || isStale() ) {
            root = toEntries(services.listFiles(null));
            lastRefresh = System.currentTimeMillis();
        }
        return toFiles(root);
    }
    
    public synchronized Iterable<CloudStoreObject> list(CloudStoreObject directory) throws CloudException, InternalException {
        if( directory != null ) {
            String directoryName = directory.getDirectory();
            CacheEntry match;
                        
            if( directoryName == null ) {
                directoryName = directory.getName();
            }
            else {
                directoryName = directoryName + "." + directory.getName();
            }
            if( root == null || isStale() ) {
                root = toEntries(services.listFiles(null));
                lastRefresh = System.currentTimeMillis();
            }
            if( directoryName.indexOf('.') == -1 ) {
                match = matchPath(null, directoryName);
            }
            else {
                String[] path = directoryName.split("\\.");
                
                match = matchPath(null, path);
            }
            if( match == null ) {
                return null;
            }
            return toFiles(match.list(this));
        }
        return list();
    }
    
    public boolean isStale() {
        return ((System.currentTimeMillis() - lastRefresh) > (CalendarWrapper.MINUTE*5L));
    }
    
    private CacheEntry matchPath(Iterable<CacheEntry> entries, String ... path) throws CloudException, InternalException {
        String base = path[0];
        
        if( path.length > 1 ) {
            String[] tmp = new String[path.length-1];
            
            for( int i=1; i<path.length; i++ ) {
                tmp[i-1] = path[i];
            }
            path = tmp;
        }
        else {
            path = null;
        }
        if( entries == null ) {
            entries = root;
        }
        for( CacheEntry entry : entries ) {
            if( entry.file.getName().equals(base) ) {
                if( path == null ) {
                    return entry;
                }
                else {
                    return matchPath(entry.list(this), path);
                }
            }
        }
        return null;
    }
    
    private Iterable<CacheEntry> toEntries(Iterable<CloudStoreObject> files) {
    	ArrayList<CacheEntry> entries = new ArrayList<CacheEntry>();
        
        for( CloudStoreObject file : files ) {
            CacheEntry entry = new CacheEntry();
            
            entry.file = file;
            entry.children = null;
            entries.add(entry);
        }
        return entries;
    }
    
    private Iterable<CloudStoreObject> toFiles(Iterable<CacheEntry> entries) {
    	ArrayList<CloudStoreObject> files = new ArrayList<CloudStoreObject>();
        
        for( CacheEntry entry : entries ) {
        	files.add(entry.file);
        }
        return files;
    }
}