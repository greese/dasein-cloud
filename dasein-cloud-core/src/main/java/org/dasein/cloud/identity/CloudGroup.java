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

package org.dasein.cloud.identity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

/**
 * Represents a hierarchical group stored in a cloud identity management system. {@link CloudUser} instances
 * may belong to groups.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public class CloudGroup implements Serializable {
    private String name;
    private String path;
    private String providerGroupId;
    private String providerOwnerId;

    public CloudGroup() { }

    @Override
    public boolean equals(@Nullable Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        CloudGroup group = (CloudGroup)ob;

        if( (providerOwnerId == null && group.providerOwnerId == null) || (providerOwnerId != null && providerOwnerId.equals(group.providerOwnerId)) ) {
            return providerGroupId.equals(group.providerGroupId);
        }
        return false;
    }
    
    public @Nullable String getName() {
        return name;
    }

    public void setName(@Nonnull String name) {
        this.name = name;
    }

    public @Nullable String getPath() {
        return path;
    }

    public void setPath(@Nullable String path) {
        this.path = path;
    }

    public @Nullable String getProviderGroupId() {
        return providerGroupId;
    }

    public void setProviderGroupId(@Nonnull String providerGroupId) {
        this.providerGroupId = providerGroupId;
    }

    public @Nullable String getProviderOwnerId() {
        return providerOwnerId;
    }

    public void setProviderOwnerId(@Nullable String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }
    
    @Override
    public @Nonnull String toString() {
        if( path == null ) {
            return (name + " [#" + providerGroupId + "]");
        }
        else if( path.endsWith("/") ) {
            return (path + name + " [#" + providerGroupId + "]");
        }
        else {
            return (path + "/" + name + " [#" + providerGroupId + "]");
        }
    }
}
