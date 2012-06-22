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

/**
 * <p>
 *  Represents the legal context of a given region of data centers. Given knowledge about
 *  the region in which resources are being used, an application can make determinations on
 *  how data from those resources may be shared.
 * </p>
 * @author George Reese @ enStratus (http://www.enstratus.com)
 */
public enum Jurisdiction {
    /**
     * Australia
     */
    AU,
    /**
     * Brazil
     */
    BR,
    /**
     * Canada
     */
    CA,
    /**
     * Switzerland
     */
    CH,
    /**
     * China
     */
    CN,
    /**
     * Hong Kong
     */
    HK,
    /**
     * India 
     */
    IN,
    /**
     * Japan
     */
    JP,
    /**
     * The European Union
     */
    EU,
    /**
     * The United States of America
     */
    US,
    /** 
     * Singapore
     */
    SG,
    /*
     * Israel
     */
    IL,
    /**
     * Mexico
     */
    MX,
    /**
     * Korea
     */
    KR,
    /**
     * Russia
     */
    RU,
    /**
     * Taiwan
     */
    TW,
    /**
     * South Africa
     */
    ZA,
    /**
     * Other jurisdiction
     */
    OTHER,
    ;
}
