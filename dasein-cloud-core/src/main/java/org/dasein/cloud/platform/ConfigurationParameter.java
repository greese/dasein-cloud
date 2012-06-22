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

import java.util.Map;

public class ConfigurationParameter implements Map.Entry<String,ConfigurationParameter> {
    private boolean applyImmediately;
    private String  dataType;
    private String  description;
    private String  key;
    private boolean modifiable;
    private Object  parameter;
    private String  validation;
    
    public ConfigurationParameter() { }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public ConfigurationParameter getValue() {
        return this;
    }
    
    @Override
    public ConfigurationParameter setValue(ConfigurationParameter value) {
        return this;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setApplyImmediately(boolean applyImmediately) {
        this.applyImmediately = applyImmediately;
    }

    public boolean isApplyImmediately() {
        return applyImmediately;
    }

    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public boolean isModifiable() {
        return modifiable;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getValidation() {
        return validation;
    }
}
