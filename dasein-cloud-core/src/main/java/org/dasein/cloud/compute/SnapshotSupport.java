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

import java.util.Locale;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

public interface SnapshotSupport extends AccessControlledService {
    static public final ServiceAction ANY             = new ServiceAction("SNAPSHOT:ANY");

    static public final ServiceAction CREATE_SNAPSHOT = new ServiceAction("SNAPSHOT:CREATE_SNAPSHOT");
    static public final ServiceAction GET_SNAPSHOT    = new ServiceAction("SNAPSHOT:GET_SNAPSHOT");
    static public final ServiceAction LIST_SNAPSHOT   = new ServiceAction("SNAPSHOT:LIST_SNAPSHOT");
    static public final ServiceAction MAKE_PUBLIC     = new ServiceAction("SNAPSHOT:MAKE_PUBLIC");
    static public final ServiceAction REMOVE_SNAPSHOT = new ServiceAction("SNAPSHOT:REMOVE_SNAPSHOT");
    static public final ServiceAction SHARE_SNAPSHOT  = new ServiceAction("SNAPSHOT:SHARE_SNAPSHOT");
    
    public String create(String ofVolume, String description) throws InternalException, CloudException;
    
    public String getProviderTermForSnapshot(Locale locale);
    
    public Snapshot getSnapshot(String snapshotId) throws InternalException, CloudException;
    
    public Iterable<String> listShares(String snapshotId) throws InternalException, CloudException;
    
    public boolean isPublic(String snapshotId) throws InternalException, CloudException;
    
    public boolean isSubscribed() throws InternalException, CloudException;
    
    public Iterable<Snapshot> listSnapshots() throws InternalException, CloudException;

    public void remove(String snapshotId) throws InternalException, CloudException;
    
    public void shareSnapshot(String snapshotId, String withAccountId, boolean affirmative) throws InternalException, CloudException;    
    
    public boolean supportsSnapshotSharing() throws InternalException, CloudException;    
    
    public boolean supportsSnapshotSharingWithPublic() throws InternalException, CloudException;    
}
