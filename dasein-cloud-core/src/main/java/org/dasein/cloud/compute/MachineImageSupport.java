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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.AsynchronousTask;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.CloudProvider;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

public interface MachineImageSupport extends AccessControlledService {
    static public final ServiceAction ANY               = new ServiceAction("IMAGE:ANY");

    static public final ServiceAction DOWNLOAD_IMAGE    = new ServiceAction("IMAGE:DOWNLOAD_IMAGE");
    static public final ServiceAction GET_IMAGE         = new ServiceAction("IMAGE:GET_IMAGE");
    static public final ServiceAction IMAGE_VM          = new ServiceAction("IMAGE:IMAGE_VM");
    static public final ServiceAction LIST_IMAGE        = new ServiceAction("IMAGE:LIST_IMAGE");
    static public final ServiceAction MAKE_PUBLIC       = new ServiceAction("IMAGE:MAKE_PUBLIC");
    static public final ServiceAction REGISTER_IMAGE    = new ServiceAction("IMAGE:REGISTER_IMAGE");
    static public final ServiceAction REMOVE_IMAGE      = new ServiceAction("IMAGE:REMOVE_IMAGE");
    static public final ServiceAction SHARE_IMAGE       = new ServiceAction("IMAGE:SHARE_IMAGE");
    static public final ServiceAction UPLOAD_IMAGE      = new ServiceAction("IMAGE:UPLOAD_IMAGE");

    public abstract void downloadImage(@Nonnull String machineImageId, @Nonnull OutputStream toOutput) throws CloudException, InternalException;
    
    public abstract @Nullable MachineImage getMachineImage(@Nonnull String machineImageId) throws CloudException, InternalException;
    
    public abstract @Nonnull String getProviderTermForImage(@Nonnull Locale locale);
    
    public abstract boolean hasPublicLibrary();
    
    public abstract @Nonnull AsynchronousTask<String> imageVirtualMachine(String vmId, String name, String description) throws CloudException, InternalException;
    
    public abstract @Nonnull AsynchronousTask<String> imageVirtualMachineToStorage(String vmId, String name, String description, String directory) throws CloudException, InternalException;
    
    public abstract @Nonnull String installImageFromUpload(@Nonnull MachineImageFormat format, @Nonnull InputStream imageStream) throws CloudException, InternalException;
    
    public abstract boolean isImageSharedWithPublic(@Nonnull String machineImageId) throws CloudException, InternalException;
    
    public abstract boolean isSubscribed() throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<MachineImage> listMachineImages() throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<MachineImage> listMachineImagesOwnedBy(String accountId) throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<MachineImageFormat> listSupportedFormats() throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<String> listShares(@Nonnull String forMachineImageId) throws CloudException, InternalException;

    public abstract @Nonnull String registerMachineImage(String atStorageLocation) throws CloudException, InternalException;
    
    public abstract void remove(@Nonnull String machineImageId) throws CloudException, InternalException;
    
    public abstract @Nonnull Iterable<MachineImage> searchMachineImages(@Nullable String keyword, @Nullable Platform platform, @Nullable Architecture architecture) throws CloudException, InternalException;

    public abstract void shareMachineImage(@Nonnull String machineImageId, @Nonnull String withAccountId, boolean allow) throws CloudException, InternalException;
    
    public abstract boolean supportsCustomImages();
    
    public abstract boolean supportsImageSharing();
    
    public abstract boolean supportsImageSharingWithPublic();
    
    public abstract @Nonnull String transfer(@Nonnull CloudProvider fromCloud, @Nonnull String machineImageId) throws CloudException, InternalException;
}
