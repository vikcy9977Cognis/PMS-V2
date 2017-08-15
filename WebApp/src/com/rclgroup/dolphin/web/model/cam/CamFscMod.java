/*------------------------------------------------------
CamFscMod.java
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
--------------------------------------------------------
Author Piyapong Ieumwananonthachai 24/10/2007  
- Change Log -------------------------------------------
## DD/MM/YY –User- -TaskRef- -ShortDescription-
--------------------------------------------------------
*/

package com.rclgroup.dolphin.web.model.cam;

import com.rclgroup.dolphin.web.common.RrcStandardMod;


public class CamFscMod extends RrcStandardMod {
    private String lineCode;
    private String regionCode;
    private String agentCode;
    private String fscCode;
    private String fscName;
    private boolean isControlFsc;
    private String status;
    private String officalNumber;
    private String companyName;

    public CamFscMod() {
        lineCode = "";
        regionCode = "";
        agentCode = "";
        fscCode = "";
        fscName = "";
        officalNumber = "";
        companyName = "";
        isControlFsc = false;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setFscCode(String fscCode) {
        this.fscCode = fscCode;
    }

    public String getFscCode() {
        return fscCode;
    }

    public void setFscName(String fscName) {
        this.fscName = fscName;
    }

    public String getFscName() {
        return fscName;
    }

    public void setIsControlFsc(boolean isControlFsc) {
        this.isControlFsc = isControlFsc;
    }

    public boolean isControlFsc() {
        return isControlFsc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setOfficalNumber(String officalNumber) {
        this.officalNumber = officalNumber;
    }

    public String getOfficalNumber() {
        return officalNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}


