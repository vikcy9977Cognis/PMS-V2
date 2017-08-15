/*-----------------------------------------------------------------------------------------------------------  
RcmUserBean.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
 Author Sopon Dee-udomvongsa 09/11/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.model.esm.EsmMenuTreeMod;
import com.rclgroup.dolphin.web.model.rcm.RcmAuthenticationMod;

import com.rclgroup.dolphin.web.util.RutString;

import java.util.ArrayList;
import java.util.List;

/**
 *    RCLUserBean is class for Carrier User
 */
public class RcmUserBean {    
    private String prsnLogId;
    private String personCd;
    private String descr;
    private String pin;
    private String pswdExp;
    private String pswdExpDays;
    private String chngPswd;
    private String inhtAllwd;
    private String passEffDt;
    private String passExpDt;
    private String funcLvl;
    private String fscCode;
    private String fscName;
    private String fscLvl1; 
    private String fscLvl2;
    private String fscLvl3;
    private String deptCode;
    private String titleCode;
    private String loginCount;
    private String orgCode;
    private String orgType;
    private String lastPasswordChangedDate;
    private String shareFsc;
    private String isLocked;
    private String isGroupAuth;
    private String emailId1;
    private String emailId2;
    private String rcst;
    private String fscDateFormat;
    private String addr1;
    private String addr2;
    private String addr3;
    private String addr4;
    private String city;
    private String state;
    private String zipPostal1;
    private String phNo;
    private String faxNo;
    private String smcurr;
    private String sccurr;
    private String vendorName;
    private String language;
    private String timeZone;
    private String country;
    private String port;
    
    private List menuAuthentication;

    public RcmUserBean(){
        prsnLogId = "";
        personCd = "";
        descr = "";
        pin = "";
        pswdExp = "";
        pswdExpDays = "";
        chngPswd = "";
        inhtAllwd = "";
        passEffDt = "";
        passExpDt = "";
        funcLvl = "";
        fscCode = "";
        fscName = "";
        fscLvl1 = ""; 
        fscLvl2 = "";
        fscLvl3 = "";
        deptCode = "";
        titleCode = "";
        loginCount = "";
        orgCode = "";
        orgType = "";
        lastPasswordChangedDate = "";
        shareFsc = "";
        isLocked = "";
        isGroupAuth = "";
        emailId1 = "";
        emailId2 = "";
        rcst = "";
        fscDateFormat = "";
        addr1 = "";
        addr2 = "";
        addr3 = "";
        addr4 = "";
        city = "";
        state = "";
        zipPostal1 = "";
        phNo = "";
        faxNo = "";
        smcurr = "";
        sccurr = "";
        vendorName = "";
        language = "";
        timeZone = "";
        country = "";
        port = "";
        menuAuthentication = new ArrayList();
    }

    public void setPrsnLogId(String prsnLogId) {
        this.prsnLogId = prsnLogId;
    }

    public String getPrsnLogId() {
        return prsnLogId;
    }

    public void setPersonCd(String personCd) {
        this.personCd = personCd;
    }

    public String getPersonCd() {
        return personCd;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPswdExp(String pswdExp) {
        this.pswdExp = pswdExp;
    }

    public String getPswdExp() {
        return pswdExp;
    }

    public void setPswdExpDays(String pswdExpDays) {
        this.pswdExpDays = pswdExpDays;
    }

    public String getPswdExpDays() {
        return pswdExpDays;
    }

    public void setChngPswd(String chngPswd) {
        this.chngPswd = chngPswd;
    }

    public String getChngPswd() {
        return chngPswd;
    }

    public void setInhtAllwd(String inhtAllwd) {
        this.inhtAllwd = inhtAllwd;
    }

    public String getInhtAllwd() {
        return inhtAllwd;
    }

    public void setPassEffDt(String passEffDt) {
        this.passEffDt = passEffDt;
    }

    public String getPassEffDt() {
        return passEffDt;
    }

    public void setPassExpDt(String passExpDt) {
        this.passExpDt = passExpDt;
    }

    public String getPassExpDt() {
        return passExpDt;
    }

    public void setFuncLvl(String funcLvl) {
        this.funcLvl = funcLvl;
    }

    public String getFuncLvl() {
        return funcLvl;
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

    public void setFscLvl1(String fscLvl1) {
        this.fscLvl1 = fscLvl1;
    }

    public String getFscLvl1() {
        return fscLvl1;
    }

    public void setFscLvl2(String fscLvl2) {
        this.fscLvl2 = fscLvl2;
    }

    public String getFscLvl2() {
        return fscLvl2;
    }

    public void setFscLvl3(String fscLvl3) {
        this.fscLvl3 = fscLvl3;
    }

    public String getFscLvl3() {
        return fscLvl3;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setLastPasswordChangedDate(String lastPasswordChangedDate) {
        this.lastPasswordChangedDate = lastPasswordChangedDate;
    }

    public String getLastPasswordChangedDate() {
        return lastPasswordChangedDate;
    }

    public void setShareFsc(String shareFsc) {
        this.shareFsc = shareFsc;
    }

    public String getShareFsc() {
        return shareFsc;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsGroupAuth(String isGroupAuth) {
        this.isGroupAuth = isGroupAuth;
    }

    public String getIsGroupAuth() {
        return isGroupAuth;
    }

    public void setEmailId1(String emailId1) {
        this.emailId1 = emailId1;
    }

    public String getEmailId1() {
        return emailId1;
    }

    public void setEmailId2(String emailId2) {
        this.emailId2 = emailId2;
    }

    public String getEmailId2() {
        return emailId2;
    }

    public void setRcst(String rcst) {
        this.rcst = rcst;
    }

    public String getRcst() {
        return rcst;
    }

    public void setFscDateFormat(String fscDateFormat) {
        this.fscDateFormat = fscDateFormat;
    }

    public String getFscDateFormat() {
        return fscDateFormat;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }

    public String getAddr3() {
        return addr3;
    }

    public void setAddr4(String addr4) {
        this.addr4 = addr4;
    }

    public String getAddr4() {
        return addr4;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZipPostal1(String zipPostal1) {
        this.zipPostal1 = zipPostal1;
    }

    public String getZipPostal1() {
        return zipPostal1;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setSmcurr(String smcurr) {
        this.smcurr = smcurr;
    }

    public String getSmcurr() {
        return smcurr;
    }

    public void setSccurr(String sccurr) {
        this.sccurr = sccurr;
    }

    public String getSccurr() {
        return sccurr;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setMenuAuthentication(List menuAuthentication) {
        this.menuAuthentication = menuAuthentication;
    }

    public List getMenuAuthentication() {
        return menuAuthentication;
    }
    
    public RcmAuthenticationMod getAuthentication(String menuId){
        RcmAuthenticationMod authenticationMod = new RcmAuthenticationMod();
        EsmMenuTreeMod menuTreeMod = null;
        for(int i=0;i<menuAuthentication.size();i++){
            menuTreeMod = (EsmMenuTreeMod)menuAuthentication.get(i);
            if(menuTreeMod.getMenuId().equalsIgnoreCase(menuId)){
                authenticationMod.setMenuId(RutString.nullToStr(menuTreeMod.getMenuId()));
                authenticationMod.setIsVisible(RutString.nullToStr(menuTreeMod.getMenuItemVisible()).equals("Y"));
                authenticationMod.setIsCreate(RutString.nullToStr(menuTreeMod.getScCreate()).equals("Y"));
                authenticationMod.setIsRead(RutString.nullToStr(menuTreeMod.getScRead()).equals("Y"));
                authenticationMod.setIsUpdate(RutString.nullToStr(menuTreeMod.getScUpdate()).equals("Y"));
                authenticationMod.setIsDelete(RutString.nullToStr(menuTreeMod.getScDelete()).equals("Y"));
                return authenticationMod;
            }
        }
        return authenticationMod;
    }
}
