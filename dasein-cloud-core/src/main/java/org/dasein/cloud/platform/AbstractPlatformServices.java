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

import javax.annotation.Nullable;

public abstract class AbstractPlatformServices implements PlatformServices {

    @Override
    public @Nullable CDNSupport getCDNSupport() {
        return null;
    }

    @Override
    public @Nullable KeyValueDatabaseSupport getKeyValueDatabaseSupport() {
        return null;
    }

    @Override
    public @Nullable MessageQueueSupport getMessageQueueSupport() {
        return null;
    }

    @Override
    public @Nullable PushNotificationSupport getPushNotificationSupport() {
        return null;
    }

    @Override
    public @Nullable RelationalDatabaseSupport getRelationalDatabaseSupport() {
        return null;
    }

    @Override
    public boolean hasCDNSupport() {
        return (getCDNSupport() != null);
    }

    @Override
    public boolean hasKeyValueDatabaseSupport() {
        return (getKeyValueDatabaseSupport() != null);
    }

    @Override
    public boolean hasMessageQueueSupport() {
        return (getMessageQueueSupport() != null);
    }

    @Override
    public boolean hasPushNotificationSupport() {
        return (getPushNotificationSupport() != null);
    }

    @Override
    public boolean hasRelationalDatabaseSupport() {
        return (getRelationalDatabaseSupport() != null);
    }

}
