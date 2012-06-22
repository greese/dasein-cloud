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

import java.io.Serializable;

public class KeyValuePair implements Serializable, Comparable<KeyValuePair> {
    private static final long serialVersionUID = -642968616323073047L;
    
    private String key;
    private String value;
    
    public KeyValuePair() { }
    
    public KeyValuePair(String key, String value) {
        this.setKey(key);
        this.setValue(value);
    }

    public int compareTo(KeyValuePair other) {
        int x = getKey().compareTo(other.getKey());
        
        if( x != 0 ) {
            return x;
        }
        return getValue().compareTo(other.getValue());
    }
    
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
        KeyValuePair other = (KeyValuePair)ob;
        
        if( !getKey().equals(other.getKey()) ) {
            return false;
        }
        if( getValue() == null ) {
            return (other.getValue() == null);
        }
        else if( other.getValue() == null ) {
            return false;
        }
        return getValue().equals(other.getValue());
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
    private transient volatile int hashCode = -1;
    
    public int hashCode() {
        if( hashCode == -1 ) {
            hashCode = toString().hashCode();
        }
        return hashCode;
    }
    
    public String toString() {
        return (key + "=" + value);
    }
}
