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

import java.io.InputStream;
import java.io.OutputStream;

/**
 * <p>
 *   Raw interface to be implemented for different encryption algorithms. An implementation is
 *   expected to store the keys to be used for encrypting or decrypting data and then encrypt/decrypt
 *   a data stream on-demand.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public interface Encryption {
    /**
     * Triggered by cloud provider implementations when done with any encryption/decryption.
     * Implementations should generally call {@link org.dasein.cloud.ProviderContext#clear(byte[][])}
     * passing in any encryption keys used by this object.
     */
    public void clear();
    
    /**
     * Decrypts an incoming encrypted stream of data and sends the de-crypted contents to the
     * specified output stream.
     * @param encryptedData the encrypted data stream
     * @param rawData the de-crypted output stream
     * @throws EncryptionException an error occurred decrypting the data
     */
    public void decrypt(InputStream encryptedData, OutputStream rawData) throws EncryptionException;
    
    /**
     * Encrypts an incoming stream of raw data and sends the encrypted data to the specified
     * output stream.
     * @param rawData the original data in the form of an input stream
     * @param encryptedData the output stream into which the encrypted data is placed
     * @throws EncryptionException an error occurred encrypting the data
     */
    public void encrypt(InputStream rawData, OutputStream encryptedData) throws EncryptionException;
}
