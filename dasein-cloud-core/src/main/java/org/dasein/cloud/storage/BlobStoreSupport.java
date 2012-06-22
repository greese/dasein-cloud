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

import java.io.File;
import java.util.Locale;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.encryption.Encryption;
import org.dasein.cloud.identity.ServiceAction;
import org.omg.CORBA.ServiceDetail;

import javax.annotation.Nonnull;

/**
 * <p>
 *   Interface into the cloud storage services supported by this cloud provider.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public interface BlobStoreSupport extends AccessControlledService {
    static public final ServiceAction ANY                     = new ServiceAction("BLOB:ANY");

    static public final ServiceAction CREATE_BUCKET           = new ServiceAction("BLOB:CREATE_BUCKET");
    static public final ServiceAction DOWNLOAD                = new ServiceAction("BLOB:DOWNLOAD");
    static public final ServiceAction GET_BUCKET              = new ServiceAction("BLOB:GET_BUCKET");
    static public final ServiceAction LIST_BUCKET             = new ServiceAction("BLOB:LIST_BUCKET");
    static public final ServiceAction LIST_BUCKET_CONTENTS    = new ServiceAction("BLOB:LIST_BUCKET_CONTENTS");
    static public final ServiceAction MAKE_PUBLIC             = new ServiceAction("BLOB:MAKE_PUBLIC");
    static public final ServiceAction REMOVE_BUCKET           = new ServiceAction("BLOB:REMOVE_BUCKET");
    static public final ServiceAction UPLOAD                  = new ServiceAction("BLOB:UPLOAD");

    public void clear(String directory) throws CloudException, InternalException;
    
    public String createDirectory(String baseName, boolean findFreeName) throws InternalException, CloudException;
    
    public FileTransfer download(String directory, String fileName, File toFile, Encryption decryption) throws InternalException, CloudException;
    
    public FileTransfer download(CloudStoreObject sourceFile, File toFile) throws CloudException, InternalException;
    
    public boolean exists(String directory) throws InternalException, CloudException;
    
    public long exists(String directory, String fileName, boolean multiPart) throws InternalException, CloudException;
    
    public long getMaxFileSizeInBytes() throws InternalException, CloudException;
    
    public String getProviderTermForDirectory(Locale locale);
    
    public String getProviderTermForFile(Locale locale);
    
    public boolean isPublic(String bucket, String object) throws CloudException, InternalException;
    
    public boolean isSubscribed() throws CloudException, InternalException;
    
    public Iterable<CloudStoreObject> listFiles(String parentDirectory) throws CloudException, InternalException;
    
    public void makePublic(String directory) throws InternalException, CloudException;
    
    public void makePublic(String directory, String fileName) throws InternalException, CloudException;

    public void moveFile(String fromDirectory, String fileName, String toDirectory) throws InternalException, CloudException;
    
    public void removeDirectory(String directory) throws CloudException, InternalException;

    public void removeFile(String directory, String name, boolean multipartFile) throws CloudException, InternalException;

    public String renameDirectory(String oldName, String newName, boolean findFreeName) throws CloudException, InternalException;
        
    public void renameFile(String directory, String oldName, String newName) throws CloudException, InternalException;
        
    public void upload(File sourceFile, String directory, String fileName, boolean multiPart, Encryption encryption) throws CloudException, InternalException;
}
