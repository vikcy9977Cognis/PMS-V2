/*-----------------------------------------------------------------------------------------------------------  
RrcStandardMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
 Author Piyapong Ieumwananonthachai 10/10/07 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import java.io.Serializable;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * Generic abstract class that is used by all models
 */
public abstract class RrcStandardMod implements Serializable {
    public static final String RECORD_STATUS_ACTIVE = "A";

    protected String recordStatus;
    protected String recordAddUser;
    protected String recordChangeUser;
    
    public RrcStandardMod(String recordStatus, String recordAddUser, String recordChangeUser) {
        this.recordStatus = recordStatus;
        this.recordAddUser = recordAddUser;
        this.recordChangeUser = recordChangeUser;
    }

    public RrcStandardMod() {
        this(RECORD_STATUS_ACTIVE, "", "");
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordAddUser(String recordAddUser) {
        this.recordAddUser = recordAddUser;
    }

    public String getRecordAddUser() {
        return recordAddUser;
    }

    public void setRecordChangeUser(String recordChangeUser) {
        this.recordChangeUser = recordChangeUser;
    }

    public String getRecordChangeUser() {
        return recordChangeUser;
    }

}

