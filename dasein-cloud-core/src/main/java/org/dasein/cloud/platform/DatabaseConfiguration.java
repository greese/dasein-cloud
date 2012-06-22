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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.dasein.cloud.CloudException;
import org.dasein.cloud.InternalException;

public class DatabaseConfiguration implements Map<String,ConfigurationParameter> {
    private String description;
    private DatabaseEngine engine;
    private String name;
    private String providerConfigurationId;
    private RelationalDatabaseSupport services;
    
    public DatabaseConfiguration(RelationalDatabaseSupport services, DatabaseEngine engine, String configurationId, String name, String description) {
        this.services = services;
        this.providerConfigurationId = configurationId;
        this.engine = engine;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public void clear() {
        try {
            services.resetConfiguration(providerConfigurationId);
        }
        catch( CloudException e ) {
            throw new RuntimeException(e);
        }
        catch( InternalException e ) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean containsKey(Object key) {
        return (get(key) != null);
    }
    
    @Override
    public boolean containsValue(Object value) {
        for( ConfigurationParameter parameter : values() ) {
            if( value == null && parameter.getValue() == null ) {
                return true;
            }
            if( value != null && parameter.getValue() != null && value.equals(parameter.getValue()) ) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Set<java.util.Map.Entry<String,ConfigurationParameter>> entrySet() {
        HashSet<java.util.Map.Entry<String,ConfigurationParameter>> set = new HashSet<java.util.Map.Entry<String,ConfigurationParameter>>();
        
        for( ConfigurationParameter cfg : values() ) {
            set.add(cfg);
        }
        return set;
    }
    
    @Override
    public ConfigurationParameter get(Object key) {
        for( ConfigurationParameter cfg : values() ) {
            if( cfg.getKey().equals(key) ) {
                return cfg;
            }
        }
        return null;
    }
    
    public DatabaseEngine getEngine() {
        return engine;
    }
    
    @Override
    public boolean isEmpty() {
        for( ConfigurationParameter cfg : values() ) {
            if( cfg != null ) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Set<String> keySet() {
        HashSet<String> set = new HashSet<String>();
        
        for( ConfigurationParameter cfg : values() ) {
            set.add(cfg.getKey());
        }
        return set;
    }
    
    @Override
    public ConfigurationParameter put(String key, ConfigurationParameter value) {
        try {
            services.updateConfiguration(providerConfigurationId, value);
        }
        catch( CloudException e ) {
            throw new RuntimeException(e);
        }
        catch( InternalException e ) {
            throw new RuntimeException(e);
        }
        return value;
    }
    
    @Override
    public void putAll(Map<? extends String,? extends ConfigurationParameter> items) {
        Collection<? extends ConfigurationParameter> values = items.values();
        ConfigurationParameter[] parameters = values.toArray(new ConfigurationParameter[values.size()]);

        try {
            services.updateConfiguration(providerConfigurationId, parameters);
        }
        catch( CloudException e ) {
            throw new RuntimeException(e);
        }
        catch( InternalException e ) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public ConfigurationParameter remove(Object key) {
        try {
            services.resetConfiguration(providerConfigurationId, (String)key);
        }
        catch( CloudException e ) {
            throw new RuntimeException(e);
        }
        catch( InternalException e ) {
            throw new RuntimeException(e);
        }        
        return null;
    }
    
    @Override
    public int size() {
        return values().size();
    }
    
    @Override
    public Collection<ConfigurationParameter> values() {
        try {
            return services.listParameters(providerConfigurationId);
        }
        catch( CloudException e ) {
            throw new RuntimeException(e);
        }
        catch( InternalException e ) {
            throw new RuntimeException(e);
        }
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getProviderConfigurationId() {
        return providerConfigurationId;
    }
}
