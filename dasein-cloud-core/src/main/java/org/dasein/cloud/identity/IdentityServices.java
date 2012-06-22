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
 * Access into all cloud services related to identity, access control, and authentication. Includes support for
 * SSH key management, user and group identity, access controls, and more.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2010.11
 * @version 2010.11
 * @version 2012.02 - Added support for user and group management and access controls
 */
public interface IdentityServices {
    public abstract @Nullable
    IdentityAndAccessSupport getIdentityAndAccessSupport();

    public abstract @Nullable ShellKeySupport getShellKeySupport();

    public abstract boolean hasIdentityAndAccessSupport();

    public abstract boolean hasShellKeySupport();
}
