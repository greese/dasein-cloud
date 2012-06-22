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

import org.dasein.cloud.AccessControlledService;
import org.dasein.cloud.CloudException;
import org.dasein.cloud.DataFormat;
import org.dasein.cloud.InternalException;
import org.dasein.cloud.identity.ServiceAction;

import javax.annotation.Nonnull;

public interface PushNotificationSupport extends AccessControlledService {
    static public final ServiceAction ANY          = new ServiceAction("PUSH:ANY");

    static public final ServiceAction CREATE_TOPIC = new ServiceAction("PUSH:CREATE_TOPIC");
    static public final ServiceAction GET_TOPIC    = new ServiceAction("PUSH:GET_TOPIC");
    static public final ServiceAction LIST_TOPIC   = new ServiceAction("PUSH:LIST_TOPIC");
    static public final ServiceAction PUBLISH      = new ServiceAction("PUSH:PUBLISH");
    static public final ServiceAction REMOVE_TOPIC = new ServiceAction("PUSH:REMOVE_TOPIC");
    static public final ServiceAction SUBSCRIBE    = new ServiceAction("PUSH:SUBSCRIBE");
    static public final ServiceAction UNSUBSCRIBE  = new ServiceAction("PUSH:UNSUBSCRIBE");

    public String confirmSubscription(String providerTopicId, String token, boolean authenticateUnsubscribe) throws CloudException, InternalException;

    
    public Topic createTopic(String name) throws CloudException, InternalException;
    
    public String getProviderTermForSubscription(Locale locale);
    
    public String getProviderTermForTopic(Locale locale);
    
    public boolean isSubscribed() throws CloudException, InternalException;
    
    public Collection<Subscription> listSubscriptions(String optionalTopicId) throws CloudException, InternalException;
    
    public Collection<Topic> listTopics() throws CloudException, InternalException;

    public String publish(String providerTopicId, String subject, String message) throws CloudException, InternalException;
    
    public void removeTopic(String providerTopicId) throws CloudException, InternalException;
    
    public void subscribe(String providerTopicId, EndpointType endpointType, DataFormat dataFormat, String endpoint) throws CloudException, InternalException;
    
    public void unsubscribe(String providerSubscriptionId) throws CloudException, InternalException;
}
