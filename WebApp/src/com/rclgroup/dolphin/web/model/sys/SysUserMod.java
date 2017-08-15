/*-----------------------------------------------------------------------------------------------------------  
SysUserMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 28/03/2008
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.sys;

import com.rclgroup.dolphin.web.common.RrcStandardMod;


public class SysUserMod extends RrcStandardMod {
    private String userID;
    private String name;
    private String fsc;
    private String org_type;
    private String department;
    private String email;
    private String status;

    public SysUserMod() {
        userID = "";
        name = "";
        fsc = "";        
        org_type = "";
        department = "";
        email = "";
        status = "";
    }
    

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFsc(String fsc) {
        this.fsc = fsc;
    }

    public String getFsc() {
        return fsc;
    }

    public void setOrg_type(String org_type) {
        this.org_type = org_type;
    }

    public String getOrg_type() {
        return org_type;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
