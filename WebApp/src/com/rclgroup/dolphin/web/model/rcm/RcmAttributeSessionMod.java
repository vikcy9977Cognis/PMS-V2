/*-----------------------------------------------------------------------------------------------------------  
RcmAttributeSessionMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Author Wuttitorn Wuttijiaranai 23/06/10
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardMod;


public class RcmAttributeSessionMod extends RrcStandardMod {
    
    private String sessionId;
    private String moduleCode;
    private String keyValue;
    private String value01;
    private String value02;
    private String value03;
    private String value04;
    private String value05;
    private String value06;
    private String value07;
    private String value08;
    private String value09;
    private String value10;
    
    public RcmAttributeSessionMod() {
        sessionId = "";
        moduleCode = "";
        keyValue = "";
        value01 = "";
        value02 = "";
        value03 = "";
        value04 = "";
        value05 = "";
        value06 = "";
        value07 = "";
        value08 = "";
        value09 = "";
        value10 = "";
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getValue01() {
        return value01;
    }

    public void setValue01(String value01) {
        this.value01 = value01;
    }

    public String getValue02() {
        return value02;
    }

    public void setValue02(String value02) {
        this.value02 = value02;
    }

    public String getValue03() {
        return value03;
    }

    public void setValue03(String value03) {
        this.value03 = value03;
    }

    public String getValue04() {
        return value04;
    }

    public void setValue04(String value04) {
        this.value04 = value04;
    }

    public String getValue05() {
        return value05;
    }

    public void setValue05(String value05) {
        this.value05 = value05;
    }

    public String getValue06() {
        return value06;
    }

    public void setValue06(String value06) {
        this.value06 = value06;
    }

    public String getValue07() {
        return value07;
    }

    public void setValue07(String value07) {
        this.value07 = value07;
    }

    public String getValue08() {
        return value08;
    }

    public void setValue08(String value08) {
        this.value08 = value08;
    }

    public String getValue09() {
        return value09;
    }

    public void setValue09(String value09) {
        this.value09 = value09;
    }

    public String getValue10() {
        return value10;
    }

    public void setValue10(String value10) {
        this.value10 = value10;
    }
}
