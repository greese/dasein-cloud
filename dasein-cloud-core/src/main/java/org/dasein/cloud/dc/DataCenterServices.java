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

package org.dasein.cloud.dc;

import java.util.Collection;
import java.util.Locale;

import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;

/**
 * <p>
 * Describe the physical configuration of the underlying cloud provider so applications
 * may determine how to create redundancies and protect jurisdictional concerns.
 * </p>
 * <p>
 *   The logical structure of this API divides a cloud into multiple regions. A region is
 *   simply a boundary within a cloud across which no common infrastructure is shared. A region
 *   has a jurisdiction that describe what laws govern the data in that region as well as
 *   one or more data centers providing resources in that region.
 * </p>
 * <p>
 *   Data centers share some resources yet provide some level of physical independence. The following
 *   rules should hold for any data center:
 * </p>
 * <ul>
 * <li>If two virtual servers are in different data centers, they cannot be running on the same physical hardware.</li>
 * <li>Outages in one data center are highly unlikely to impact other data centers.</li>
 * </ul>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public interface DataCenterServices {
    /**
     * Provides access to the full data center information for the specified data center.
     * @param providerDataCenterId the provider-specific identifier that the provider uses to identify the data center
     * @return the current state of the desired data center
     * @throws InternalException an error occurred locally in processing the request
     * @throws CloudException an error occurred within the cloud provider or the cloud provider did not approve of the request
     */
    public DataCenter getDataCenter(String providerDataCenterId) throws InternalException, CloudException;

    /**
     * Provides the cloud-specific term for a data center (e.g. "availability zone").
     * @param locale the locale into which the term should be translated
     * @return the term for a data center
     */
    public String getProviderTermForDataCenter(Locale locale);
    
    /**
     * Provides the cloud-specific term for a region.
     * @param locale the locale into which the term should be translated
     * @return the term for a region
     */
    public String getProviderTermForRegion(Locale locale);
    
    /**
     * Provides the region matching the specified region ID.
     * @param providerRegionId the provider-specified unique ID that identifies a region for the provider
     * @return the current state of the desired region
     * @throws InternalException an error occurred locally in processing the request
     * @throws CloudException an error occurred within the cloud provider or the cloud provider did not approve of the request
     */
    public Region getRegion(String providerRegionId) throws InternalException, CloudException;

    /**
     * Lists all data centers, active and inactive, available and unavailable, for the specified region.
     * @param providerRegionId the region in which you are searching for data centers
     * @return the complete list of data centers for the region
     * @throws InternalException an error occurred locally in processing the request
     * @throws CloudException an error occurred within the cloud provider or the cloud provider did not approve of the request
     */
    public Collection<DataCenter> listDataCenters(String providerRegionId) throws InternalException, CloudException;
    
    /**
     * Lists all regions, active and inactive, available and unavailable.
     * @return all regions supported for this cloud in any context
     * @throws InternalException an error occurred locally in processing the request
     * @throws CloudException an error occurred within the cloud provider or the cloud provider did not approve of the request
     */
    public Collection<Region> listRegions() throws InternalException, CloudException;
}
