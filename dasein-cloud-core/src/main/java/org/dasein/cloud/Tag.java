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

package org.dasein.cloud;

import java.io.Serializable;

public class Tag implements Comparable<Tag>, Serializable {
    private static final long serialVersionUID = 235659192731551401L;
    
    private String key;
    private String value;
    
    public Tag() { }

    public Tag(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }
    
    public int compareTo(Tag tag) {
        int x = key.compareTo(tag.key);
        
        if( x != 0 ) {
            return x;
        }
        if( value == null ) {
            return "".compareTo(tag.value == null ? "" : tag.value);
        }
        else {
            return value.compareTo(tag.value == null ? "" : tag.value);
        }
    }
    
    public boolean equals(Object ob) {
        if( ob == null ) {
            return false;
        }
        if( ob == this ) {
            return true;
        }
        Tag tag = (Tag)ob;
        if( !(key == null ? "" : key).equals(tag.key == null ? "" : tag.key) ) {
            return false;
        }
        return (value == null ? "" : value).equals(tag.value == null ? "" : tag.value);
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public int hashCode() {
        return toString().hashCode();
    }
    
    public String toString() {
        return (key + "=" + value);
    }
}
