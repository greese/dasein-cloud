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

import java.util.Collection;
import java.util.Locale;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Methods for managing SSH keys for remote access to a virtual machine.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2010.11
 * @version 2010.11
 */
public interface ShellKeySupport extends AccessControlledService {
    @SuppressWarnings("unused") static public final ServiceAction ANY            = new ServiceAction("SSH:ANY");

    @SuppressWarnings("unused") static public final ServiceAction CREATE_KEYPAIR = new ServiceAction("SSH:CREATE_KEYPAIR");
    @SuppressWarnings("unused") static public final ServiceAction GET_KEYPAIR    = new ServiceAction("SSH:GET_KEYPAIR");
    @SuppressWarnings("unused") static public final ServiceAction LIST_KEYPAIR   = new ServiceAction("SSH:LIST_KEYPAIR");
    @SuppressWarnings("unused") static public final ServiceAction REMOVE_KEYPAIR = new ServiceAction("SSH:REMOVE_KEYPAIR");
    
    /**
     * Creates an SSH keypair having the specified name.
     * @param name the name of the SSH keypair
     * @return a new SSH keypair
     * @throws InternalException an error occurred within Dasein Cloud while processing the request
     * @throws CloudException an error occurred in the cloud provider executing the keypair creation
     */
    @SuppressWarnings("unused")
    public @Nonnull SSHKeypair createKeypair(@Nonnull String name) throws InternalException, CloudException;

    /**
     * Deletes the specified keypair having the specified name.
     * @param providerId the provider ID of the keypair to be deleted
     * @throws InternalException an error occurred within Dasein Cloud while performing the deletion
     * @throws CloudException an error occurred with the cloud provider while performing the deletion
     */
    @SuppressWarnings("unused")
    public void deleteKeypair(@Nonnull String providerId) throws InternalException, CloudException;

    /**
     * Fetches the fingerprint of the specified key so you can validate it against the one you have.
     * @param providerId the provider ID of the keypair
     * @return the fingerprint for the specified keypair
     * @throws InternalException an error occurred within Dasein Cloud while fetching the fingerprint
     * @throws CloudException an error occurred with the cloud provider while fetching the fingerprint
     */
    @SuppressWarnings("unused")
    public @Nullable String getFingerprint(@Nonnull String providerId) throws InternalException, CloudException;

    /**
     * Fetches the specified keypair from the cloud provider. The cloud provider may or may not know
     * about the public key at this time, so be prepared for a null {@link SSHKeypair#getPublicKey()}.
     * @param providerId the unique ID of the target keypair
     * @return the keypair matching the specified provider ID or <code>null</code> if no such key exists
     * @throws InternalException an error occurred in the Dasein Cloud implementation while fetching the keypair
     * @throws CloudException an error occurred with the cloud provider while fetching the keypair
     */
    @SuppressWarnings("unused")
    public @Nullable SSHKeypair getKeypair(@Nonnull String providerId) throws InternalException, CloudException;
    
    /**
     * Provides the provider term for an SSH keypair.
     * @param locale the locale into which the term should be translated
     * @return the provider term for the SSH keypair, ideally translated for the specified locale
     */
    @SuppressWarnings("unused")
    public @Nonnull String getProviderTermForKeypair(@Nonnull Locale locale);

    /**
     * @return true if the cloud provider supports shell keypairs in the current region and the current account can use them
     * @throws CloudException an error occurred with the cloud provider while determining subscription status
     * @throws InternalException an error occurred within the Dasein Cloud implementation determining subscription status
     */
    @SuppressWarnings("unused")
    public boolean isSubscribed() throws CloudException, InternalException;

    /**
     * @return a list of all available SSH keypairs (private key is null)
     * @throws InternalException an error occurred within Dasein Cloud listing the keypairs
     * @throws CloudException an error occurred with the cloud provider listing the keyspairs
     */
    @SuppressWarnings("unused")
    public @Nonnull Collection<SSHKeypair> list() throws InternalException, CloudException;
}
