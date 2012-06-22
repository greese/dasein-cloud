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
 * Represents a local failure in the implementation of this API. It could be a wrapper around an
 * exception caught from a local method call or some other error situation that does not involve
 * any trouble in the cloud.
 * </p>
 * <p>
 * The Dasein Cloud API divides exceptional conditions into two groups: cloud exceptions and internal
 * exception. A cloud exception is simply an error that occurs in the cloud, whereas an internal
 * exception is one that occurs on the localhost within the implementation of this API for
 * a particular provider. This distinction can help clients determine how to recover from any
 * given error.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class InternalException extends Exception {
    private static final long serialVersionUID = 8273745960584454988L;

    /**
     * Constructs an empty internal exception with no message or context information.
     */
    public InternalException() { }

    /**
     * Constructs a local exception with the specified error message.
     * @param msg a message indicating the nature of the exception
     */
    public InternalException(@Nonnull String msg) { super(msg); }
    
    /**
     * Constructs a local exception caused by the specified exception.
     * @param cause the underlying exception that caused this error condition to be raised
     */
    public InternalException(@Nonnull Throwable cause) { super(cause); }
}
