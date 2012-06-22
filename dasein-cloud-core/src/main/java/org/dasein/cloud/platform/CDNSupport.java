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

package org.dasein.cloud.platform;

import java.util.Collection;
import java.util.Locale;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

/**
 * Provides interaction with cloud CDN services. A cloud CDN service is a service that enables you
 * to define distributions for content in different origins.
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public interface CDNSupport extends AccessControlledService {
    @SuppressWarnings("unused") static public final ServiceAction ANY                 = new ServiceAction("CDN:ANY");

    @SuppressWarnings("unused") static public final ServiceAction CREATE_DISTRIBUTION = new ServiceAction("CDN:CREATE_DISTRIBUTION");
    @SuppressWarnings("unused") static public final ServiceAction GET_DISTRIBUTION    = new ServiceAction("CDN:GET_DISTRIBUTION");
    @SuppressWarnings("unused") static public final ServiceAction LIST_DISTRIBUTION   = new ServiceAction("CDN:LIST_DISTRIBUTION");
    @SuppressWarnings("unused") static public final ServiceAction REMOVE_DISTRIBUTION = new ServiceAction("CDN:REMOVE_DISTRIBUTION");

    /**
     * Creates a new CDN distribution using content from the specified origin storage repository.
     * @param origin the cloud storage directory/bucket from which content is distributed
     * @param name the user-friendly name of your new distribution
     * @param active whether or not the distribution should be immediately enabled
     * @param aliases any CNAME aliases you intend to use for the CDN
     * @return the cloud provider unique identifier for the newly created distribution
     * @throws InternalException an error occurred inside the Dasein Cloud implementation
     * @throws CloudException an error occurred with the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull String create(@Nonnull String origin, @Nonnull String name, boolean active, @CheckForNull String ... aliases) throws InternalException, CloudException;
    
    /**
     * Disables and then deletes the specified distribution.
     * @param distributionId the provider ID for the distribution to delete
     * @throws InternalException an error occurred inside the Dasein Cloud implementation
     * @throws CloudException an error occurred with the cloud provider
     */
    @SuppressWarnings("unused")
    public void delete(@Nonnull String distributionId) throws InternalException, CloudException;
    
    /**
     * Provides encapsulated information about a specific distribution.
     * @param distributionId the provider ID for the desired distribution
     * @return the requested distribution, if it exists (or null if it does not)
     * @throws InternalException an error occurred inside the Dasein Cloud implementation
     * @throws CloudException an error occurred with the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nullable Distribution getDistribution(@Nonnull String distributionId) throws InternalException, CloudException;
    
    /**
     * The term the provider uses to describe distributions.
     * @param locale the language in which the term should be presented
     * @return the provider term for distributions
     */
    @SuppressWarnings("unused")
    public @Nonnull String getProviderTermForDistribution(@Nonnull Locale locale);
    
    /**
     * Verifies that the current account is subscribed to CDN services with this cloud provider.
     * @return true if subscribed, false otherwise
     * @throws InternalException an error occurred within the Dasein Cloud implementation 
     * @throws CloudException an error occurred with the cloud provider
     */
    @SuppressWarnings("unused")
    public boolean isSubscribed() throws InternalException, CloudException;
    
    /**
     * Lists all active distributions.
     * @return all active distributions
     * @throws InternalException an error occurred inside the Dasein Cloud implementation
     * @throws CloudException an error occurred with the cloud provider
     */
    @SuppressWarnings("unused")
    public @Nonnull Collection<Distribution> list() throws InternalException, CloudException;

    /**
     * Modifies the specified distribution with the specified attributes.
     * @param distributionId the provider ID of the distribution to update
     * @param name the new name of the distribution (not all clouds store distribution names)
     * @param active whether or not the distribution should be active
     * @param aliases any CNAME aliases for your distribution dns (not all clouds store alias information)
     * @throws InternalException an error occurred inside the Dasein Cloud implementation
     * @throws CloudException an error occurred with the cloud provider
     */
    @SuppressWarnings("unused")
    public void update(@Nonnull String distributionId, @Nonnull String name, boolean active, @CheckForNull String ... aliases) throws InternalException, CloudException;
}
