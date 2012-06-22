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
 * A user registered in a cloud identity management system.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public class CloudUser implements Serializable {
    private String path;
    private String providerUserId;
    private String providerOwnerId;
    private String userName;
    
    public CloudUser() { }

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
        CloudUser user = (CloudUser)ob;

        //noinspection SimplifiableIfStatement
        if( (providerOwnerId == null && user.providerOwnerId == null) || (providerOwnerId != null && providerOwnerId.equals(user.providerOwnerId)) ) {
            return providerUserId.equals(user.providerUserId);
        }
        return false;
    }
    
    public @Nullable String getPath() {
        return path;
    }

    public void setPath(@Nullable String path) {
        this.path = path;
    }

    public @Nullable String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(@Nonnull String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public @Nullable String getProviderOwnerId() {
        return providerOwnerId;
    }

    public void setProviderOwnerId(@Nullable String providerOwnerId) {
        this.providerOwnerId = providerOwnerId;
    }

    public @Nullable String getUserName() {
        return userName;
    }

    public void setUserName(@Nonnull String userName) {
        this.userName = userName;
    }
    
    @Override
    public @Nonnull String toString() {
        if( path == null ) {
            return (userName + " [#" + providerUserId + "]");
        }
        else if( path.endsWith("/") ) {
            return (path + userName + " [#" + providerUserId + "]");
        }
        else {
            return (path + "/" + userName + " [#" + providerUserId + "]");
        }
    }
}
