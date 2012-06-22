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

/**
 * Represents a policy tied to a user or group in the cloud.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public class CloudPolicy {
    private ServiceAction   action;
    private String          name;
    private CloudPermission permission;
    private String          resourceId;

    @SuppressWarnings("unused")
    public CloudPolicy(@Nonnull String name, @Nonnull CloudPermission permission, @Nullable ServiceAction action, @Nullable String resourceId) {
        this.permission = permission;
        this.action = action;
        this.name = name;
        this.resourceId = resourceId;
    }

    @SuppressWarnings("unused")
    public @Nullable ServiceAction getAction() {
        return action;
    }

    @SuppressWarnings("unused")
    public @Nonnull String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public @Nonnull CloudPermission getPermission() {
        return permission;
    }

    @SuppressWarnings("unused")
    public @Nullable String getResourceId() {
        return resourceId;
    }
    
    @Override
    public @Nonnull String toString() {
        return (permission + "/" + action + "/" + resourceId);
    }
}
