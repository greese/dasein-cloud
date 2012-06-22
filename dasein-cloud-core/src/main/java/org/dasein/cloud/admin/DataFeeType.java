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

/**
 * <p>
 * Identifies what kind of fee is in force for data storage or transfer.
 * </p>
 * @author George Reese @ <a href="http://www.enstratus.com">enStratus</a>
 */
public enum DataFeeType {
    /**
     * Fee for storage of data inside the cloud storage system.
     */
    CLOUD_STORAGE,
    /**
     * Fee for allocation of block storage.
     */
    BLOCK_STORAGE,
    /**
     * Fee for space consumed by snapshots.
     */
    SNAPSHOT,
    /**
     * Fee for space consumed by backups.
     */
    BACKUP,
    /**
     * Fee for data transfer between points inside the cloud.
     */
    INTERNAL_DATA_XFER,
    /**
     * Fee for any data transfer out of the cloud.
     */
    EXTERNAL_DATA_XFER,
    /**
     * Fee for data transfer between regions in the same cloud.
     */
    REGIONAL_DATA_XFER
}
