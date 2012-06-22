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

package org.dasein.cloud.network;

import java.io.Serializable;

/**
 * <p>
 * Represents a single record within a DNS zone. A zone like <i>imaginary.com</i> may have any number of
 * records of different types associated with it (for example, A records pointing host names to IP addresses,
 * CNAME records pointing host names to other host names, MX records driving mail routing, etc.).
 * </p>
 * <p>
 * Operations on these objects do not travel to the cloud. You must call operations on {@link DNSSupport}
 * to effect any changes in the cloud.
 * </p>
 * @author George Reese (george.reese@enstratus.com)
 */
public class DNSRecord implements Serializable {
    private static final long serialVersionUID = -8497137234556104567L;
    
    private String        name;
    private String        providerZoneId;
    private int           ttl;
    private DNSRecordType type;
    private String[]      values;
    
    public DNSRecord() { }

    /**
     * The name of this record.
     * @return the name of the record
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the record name.
     * @param name the name of the record
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides the TTL (time-to-live) for the record.
     * @return the record TTL
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * Sets the record TTL (time-to-live).
     * @param ttl the record TTL
     */
    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    /**
     * Provides the record type.
     * @return the record type
     */
    public DNSRecordType getType() {
        return type;
    }

    /**
     * Sets the record type.
     * @param type the record type
     */
    public void setType(DNSRecordType type) {
        this.type = type;
    }

    /**
     * Provides one or more values associated with this record.
     * @return the values associated with this record
     */
    public String[] getValues() {
        return values;
    }

    /**
     * Sets the values associated with this record.
     * @param values the values associated with this record
     */
    public void setValues(String[] values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        if( !getClass().getName().equals(ob.getClass().getName()) ) {
            return false;
        }
        DNSRecord other = (DNSRecord)ob;
        
        if( !name.equals(other.name) ) {
            return false;
        }
        if( !type.equals(other.type) ) {
            return false;
        }
        if( other.ttl != ttl ) {
            return false;
        }
        if( values == null || values.length == 0 ) {
            return (other.values == null || other.values.length == 0);
        }
        if( other.values == null || other.values.length == 0 ) {
            return (values == null || values.length == 0);
        }
        if( values.length != other.values.length ) {
            return false;
        }
        for( int i=0; i<values.length; i++ ) {
            if( !values[i].equals(other.values[i]) ) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        str.append(type.toString() + " " + ttl);
        if( values != null ) {
            for( String value : values ) {
                str.append(" ");
                str.append(value);
            }
        }
        return str.toString();
    }

    /**
     * Sets the unique identifier within the cloud provider referencing the zone to which this record belongs.
     * @param providerZoneId the unique ID of the zone to which this record belongs
     */
    public void setProviderZoneId(String providerZoneId) {
        this.providerZoneId = providerZoneId;
    }

    /**
     * Provides the unique ID within the cloud provider referencing the zone to which this record belongs.
     * @return the unique ID of the zone to which this record belongs
     */
    public String getProviderZoneId() {
        return providerZoneId;
    }
}
