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

import javax.annotation.Nonnull;

/**
 * <p>
 *  Thrown by a cloud service implementation in which a given operation is not supported though
 *  the general service has some level of support. If none of the operations are supported, however,
 *  then there should be no service implementation for that service.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class OperationNotSupportedException extends InternalException {
    private static final long serialVersionUID = 9141198296029381403L;

    /**
     * Constructs an empty exception. 
     */
    public OperationNotSupportedException() { }

    /**
     * Constructs an exception with the specified explanation as to why the operation is not supported.
     * @param msg the explanation as to why the operation is not supported.
     */
    public OperationNotSupportedException(@Nonnull String msg) { super(msg); }
}
