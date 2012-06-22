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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 * Represents some kind of failure with the cloud provider or with the format of a request to the
 * cloud provider.
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
public class CloudException extends Exception {
    private static final long serialVersionUID = -1975104091752615199L;
    
    private CloudErrorType errorType;
    private int            httpCode;
    private String         providerCode;
    
    /**
     * Constructs an unlabeled exception.
     */
    public CloudException() {
        super();
    }

    /**
     * Constructs a cloud exception with a specific error message.
     * @param msg the message for the error that occurred
     */
    public CloudException(@Nonnull String msg) {
        super(msg);
    }
    
    /**
     * Constructs a cloud exception in response to a specific cause.
     * @param cause the error that caused this exception to be thrown
     */
    public CloudException(@Nonnull Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructs a cloud exception with cloud provider data added in
     * @param httpCode the HTTP error code
     * @param providerCode the provider-specific error code
     * @param msg the error message
     */
    public CloudException(@Nonnull CloudErrorType type, @Nonnegative int httpCode, @Nullable String providerCode, @Nonnull String msg) {
        super(msg);
        this.errorType = type;
        this.httpCode = httpCode;
        this.providerCode = providerCode;
    }
    
    public @Nonnegative int getHttpCode() {
        return httpCode;
    }
    
    public @Nonnull CloudErrorType getErrorType() {
        return (errorType == null ? CloudErrorType.GENERAL : errorType);
    }
    
    public @Nonnull String getProviderCode() {
        return (providerCode == null ? "" : providerCode);
    }
}
