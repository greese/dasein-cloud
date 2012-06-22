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

public enum DatabaseEngine {
    MYSQL, MYSQL50, MYSQL51, MYSQL55, ORACLE11G, ORACLE11GX, ORACLE11GEX;
    
    public String getVersion() {
        switch( this ) {
            case MYSQL: return "5.5";
            case MYSQL50: return "5.0";
            case MYSQL51: return "5.1";
            case MYSQL55: return "5.5";
            case ORACLE11G: return "11g (standard)"; 
            case ORACLE11GX: return "11g (standard, byol)";
            case ORACLE11GEX: return "11g (enterprise, byol)";
        }
        return null;
    }
    
    public boolean isMySQL() {
        return (equals(MYSQL) || equals(MYSQL50) || equals(MYSQL51) || equals(MYSQL55));
    }
    
    public String toString() {
        switch( this ) {
            case MYSQL: return "MySQL (Any)";
            case MYSQL50: return "MySQL 5.0";
            case MYSQL51: return "MySQL 5.1";
            case MYSQL55: return "MySQL 5.5";
            case ORACLE11G: return "Oracle 11g Standard";
            case ORACLE11GX: return "Oracle 11g Standard (BYOL)";
            case ORACLE11GEX: return "Oracle 11g Enterprise";
        }
        return super.toString();
    }
}
