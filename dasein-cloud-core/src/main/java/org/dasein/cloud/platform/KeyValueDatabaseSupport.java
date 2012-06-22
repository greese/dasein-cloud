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

package org.dasein.cloud.platform;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

import javax.annotation.Nonnull;

public interface KeyValueDatabaseSupport extends AccessControlledService {
    static public final ServiceAction ANY         = new ServiceAction("KVDB:ANY");

    static public final ServiceAction CREATE_KVDB = new ServiceAction("KVDB:CREATE_KVDB");
    static public final ServiceAction DELETE      = new ServiceAction("KVDB:DELETE");
    static public final ServiceAction GET_KVDB    = new ServiceAction("KVDB:GET_KVDB");
    static public final ServiceAction LIST_KVDB   = new ServiceAction("KVDB:LIST_KVDB");
    static public final ServiceAction PUT         = new ServiceAction("KVDB:PUT");
    static public final ServiceAction REMOVE_KVDB = new ServiceAction("KVDB:REMOVE_KVDB");
    static public final ServiceAction SELECT      = new ServiceAction("KVDB:SELECT");

    public void addKeyValuePairs(String inDatabaseId, String itemId, KeyValuePair ... pairs) throws CloudException, InternalException;
    
    public String createDatabase(String name, String description) throws CloudException, InternalException;
    
    public Iterable<KeyValuePair> getKeyValuePairs(String inDatabaseId, String itemId, boolean consistentRead) throws CloudException, InternalException;
    
    public KeyValueDatabase getDatabase(String databaseId) throws CloudException, InternalException;
    
    public String getProviderTermForDatabase(Locale locale);

    public boolean isSubscribed() throws CloudException, InternalException;
    
    /**
     * Specifies whether the region represented by the current context supports key value databases. If the
     * context has no region, this method will answer the question in general (should be true).
     * @return true if this region supports key/value databases
     * @throws CloudException an error occurred talking with the cloud provider
     * @throws InternalException an error occurred inside the implementation of Dasein Cloud
     */
    public boolean isSupportsKeyValueDatabases() throws CloudException, InternalException;
    
    public Iterable<String> list() throws CloudException, InternalException;

    public Map<String,Set<KeyValuePair>> query(String queryString, boolean consistentRead) throws CloudException, InternalException;
    
    public void removeKeyValuePairs(String inDatabaseId, String itemId, KeyValuePair ... pairs) throws CloudException, InternalException;

    public void removeKeyValuePairs(String inDatabaseId, String itemId, String ... keys) throws CloudException, InternalException;
    
    public void removeDatabase(String providerDatabaseId) throws CloudException, InternalException;
    
    public void replaceKeyValuePairs(String inDatabaseId, String itemId, KeyValuePair ... pairs) throws CloudException, InternalException;
}
