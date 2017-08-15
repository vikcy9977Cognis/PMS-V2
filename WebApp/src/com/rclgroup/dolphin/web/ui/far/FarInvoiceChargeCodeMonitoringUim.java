package com.rclgroup.dolphin.web.ui.far;

import com.rclgroup.dolphin.web.common.RrcStandardUim;
import com.rclgroup.dolphin.web.util.RutString;

public class FarInvoiceChargeCodeMonitoringUim extends RrcStandardUim {

    public static final String STATUS_FSC_ACTIVE = "A";
    public static final String STATUS_CHARGE_CODE_ACTIVE = "A";
    public static final String REQ = "FormTextBoxReq";
    public static final String MISCELLANEOUS = "M";
    public static final String DOCUMENT = "D";
    
    private String fsc;
    private short fscOk;
    private String permissionUser;
    private String reportUrl;
    private String createdFrm;
    private String createdTo;
    
    private String styleSheetDte;
    
    private String invoiceBy;
    private String chargeCode;
    private short chargeCodeOk;
    
    private String reportFormat;
    
    private String userCode;
    
    public FarInvoiceChargeCodeMonitoringUim() {
    
        this.fsc = "";
        this.permissionUser = "";
        this.reportUrl = "";
        this.createdFrm = "";
        this.createdTo= "";
        
        this.styleSheetDte = REQ;
        this.invoiceBy = MISCELLANEOUS;
        
        this.chargeCode = "";
        
        this.userCode = "";
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }
    
    public String getFsc() {
        return fsc;
    }

    public void setFsc(String fsc) {
        this.fsc = fsc.toUpperCase();
        if (!RutString.isEmptyString(fsc) && this.fsc.length() > 0) {
            boolean isValid = 
                camFscDao.isValid(fsc, this.STATUS_FSC_ACTIVE);
            if (isValid) {
                fscOk = CHECKED_OK;
            } else {
                fscOk = CHECKED_NOT_OK;
            }
        }
    }

    public boolean isFscOk() {
        if (!fsc.equals("")) {
            if (fscOk == CHECKED_OK) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }
    
    public String getPermissionUser() {
        return permissionUser;
    }

    public void setPermissionUser(String permissionUser) {
        this.permissionUser = permissionUser;
    }
    
    public void setCreatedFrm(String createdFrm) {
        this.createdFrm = createdFrm;        
    }

    public String getCreatedFrm() {
        return createdFrm;
    }
    
    public void setCreatedTo(String createdTo) {
        this.createdTo = createdTo;        
    }

    public String getCreatedTo() {
        return createdTo;
    }
    
    public void setStyleSheetDte(String styleSheetDte) {
        this.styleSheetDte = styleSheetDte;
    }

    public String getStyleSheetDte() {
        return styleSheetDte;
    }
    
    public void setInvoiceBy(String invoiceBy) {
        this.invoiceBy = invoiceBy;
    }

    public String getInvoiceBy() {
        return invoiceBy;
    }
    
    public boolean isChargeCodeOk() {
        if (!chargeCode.equals("")) {
            if (chargeCodeOk == CHECKED_OK) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
    
    public void setChargeCode(String chargeCode) {
    
        this.chargeCode = chargeCode;
        
        if (!RutString.isEmptyString(chargeCode) && this.chargeCode.length() > 0) {
            boolean isValid = camFscDao.isValidChargeCode(chargeCode, this.invoiceBy ,this.STATUS_CHARGE_CODE_ACTIVE);
            if (isValid) {
                chargeCodeOk = CHECKED_OK;
            } else {
                chargeCodeOk = CHECKED_NOT_OK;
            }
        }
    }

    public String getChargeCode() {
        return chargeCode;
    }
    
    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public String getReportFormat() {
        return reportFormat;
    }
}
