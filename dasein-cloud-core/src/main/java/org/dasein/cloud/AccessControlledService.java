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

package org.dasein.cloud;

import org.dasein.cloud.identity.ServiceAction;

import javax.annotation.Nonnull;

/**
 * Any services to which access is controlled by the cloud IAM {@link org.dasein.cloud.identity.IdentityAndAccessSupport}.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2012.02
 * @version 2012.02
 */
public interface AccessControlledService {
    /**
     * Maps the specified Dasein Cloud service action to an identifier specific to an underlying cloud. If there is
     * no mapping that makes any sense, the method will return an empty array.
     * @param action the Dasein Cloud service action
     * @return a list of cloud-specific IDs (e.g. iam:ListGroups) representing an action with this cloud provider
     */
    public @Nonnull String[] mapServiceAction(@Nonnull ServiceAction action);
}
