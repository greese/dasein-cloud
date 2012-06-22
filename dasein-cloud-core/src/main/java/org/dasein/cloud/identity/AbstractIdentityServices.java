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

import javax.annotation.Nullable;

/**
 * A null implementation of the Dasein Cloud Identity Services. Implementation classes override selected
 * methods to indicate support for specific parts of the Identity Services.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2010.11
 * @version 2012.02 - added support for identity and access management
 */
public abstract class AbstractIdentityServices implements IdentityServices {
    @Override
    public @Nullable
    IdentityAndAccessSupport getIdentityAndAccessSupport() {
        return null;
    }

    @Override
    public @Nullable ShellKeySupport getShellKeySupport() {
        return null;
    }

    @Override
    public boolean hasIdentityAndAccessSupport() {
        return (getIdentityAndAccessSupport() != null);
    }

    @Override
    public boolean hasShellKeySupport() {
        return (getShellKeySupport() != null);
    }
}
