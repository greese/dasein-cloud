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

package org.dasein.cloud.encryption;

import org.dasein.cloud.InternalException;

/**
 * <p>
 * Represents an error encrypting/decrypting a data stream to/from the cloud storage environment.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public class EncryptionException extends InternalException {
    private static final long serialVersionUID = -3046876284326058104L;

    /**
     * Constructs an empty encryption error.
     */
    public EncryptionException() {
        super();
    }
    
    /**
     * Constructs an encryption exception with the specified error message.
     * @param msg the error message explaining why encryption/decryption could not happen
     */
    public EncryptionException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an exception exception resulting from the specified cause.
     * @param cause the cause of this encryption/decryption error
     */
    public EncryptionException(Throwable cause) {
        super(cause.getMessage());
    }
}
