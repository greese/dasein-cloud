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

package org.dasein.cloud.admin;

import java.util.Collection;
import java.util.Locale;

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

/**
 *  Provides interaction with the pricing and billing mechanics for the cloud. An application
 *  can leverage this service to determine pricing for components it is managing.
 * @author George Reese (george.reese@imaginary.com)
 * @since 2010.11
 * @version 2010.11
 * @version 2012.12 - Added subscription test and service actions
 */
public interface PrepaymentSupport extends AccessControlledService {
    static public final ServiceAction ANY             = new ServiceAction("PPM:ANY");

    static public final ServiceAction GET_OFFERING    = new ServiceAction("PPM:GET_OFFERING");
    static public final ServiceAction GET_PREPAYMENT  = new ServiceAction("PPM:GET_PREPAYMENT");
    static public final ServiceAction LIST_OFFERING   = new ServiceAction("PPM:READ_OFFERING");
    static public final ServiceAction LIST_PREPAYMENT = new ServiceAction("PPM:READ_PREPAYMENT");
    static public final ServiceAction PREPAY          = new ServiceAction("PPM:PREPAY");

    public Offering getOffering(String offeringId) throws InternalException, CloudException;
    
    public Prepayment getPrepayment(String prepaymentId) throws InternalException, CloudException;
    
    public String getProviderTermForOffering(Locale locale);
    
    public String getProviderTermForPrepayment(Locale locale);
    
    public boolean isSubscribed() throws CloudException, InternalException;

    public Collection<Offering> listOfferings() throws InternalException, CloudException;

    public Collection<Prepayment> listPrepayments() throws InternalException, CloudException;

    public String prepay(String offeringId, int count) throws InternalException, CloudException;
}
