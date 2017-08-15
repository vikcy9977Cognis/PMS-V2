/*-----------------------------------------------------------------------------------------------------------  
RrcStandardUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 04/06/09  POR                      Added constant House B/L
02 09/11/09  KIT                      Added constant GET_HELP_V02__WITH_ACTIVE_STATUS
03 19/11/09  POR                      Added constant GET_SRV_VSS_VOY_INVOY 
04 12/11/10  KIT                      Add office login 
05 06/06/11  NIP                      Added getResourceFolderReportConstant function
06 19/07/12  PAN                      Added constant GET_VESSEL_FROM_VSA
07 15/11/12  SUJ                      Update report acess
08 15/11/12  SUJ                     Update report acess # session and report generate no
09 21/06/13  NUTTAPOL                 Change URL pattern adding P_USERNAME and P_START_DATE for capture report access log  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmUserDao;
import com.rclgroup.dolphin.web.model.rcm.RcmReportAccessMod;
import com.rclgroup.dolphin.web.model.rcm.RcmReportServerConfigMod;
import com.rclgroup.dolphin.web.util.RutDate;
import com.rclgroup.dolphin.web.util.RutFlagPage;
import com.rclgroup.dolphin.web.util.RutPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *    A main RCL standard UI model
 */
public class RrcStandardUim {
    public static final String GET_GENERAL = RcmConstant.GET_GENERAL;
    public static final String GET_DISCHARGE = RcmConstant.GET_DISCHARGE;
    public static final String GET_GENERAL_WITH_REGION_COUNTRY = RcmConstant.GET_GENERAL_WITH_REGION_COUNTRY;
    public static final String GET_WITH_ACTIVE_STATUS = RcmConstant.GET_WITH_ACTIVE_STATUS;    
    public static final String GET_WITH_USER_LEVEL_ACTIVE_STATUS = RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS;
    public static final String GET_WITH_USER = RcmConstant.GET_WITH_USER;
    public static final String GET_WITH_FSC = RcmConstant.GET_WITH_FSC;
    public static final String GET_FILTERED_SERVICE = RcmConstant.GET_FILTERED_SERVICE;
    public static final String GET_FILTERED_RESULT  = RcmConstant.GET_FILTERED_RESULT;

    public static final String GET_HOUSEBL_GENERAL = RcmConstant.GET_HOUSEBL_GENERAL; //--##01
    public static final String GET_BL_GENERAL = RcmConstant.GET_BL_GENERAL; //--##01
    public static final String GET_REEFER_GENERAL = RcmConstant.GET_REEFER_GENERAL;
    public static final String GET_REEFER_IMP_GENERAL = RcmConstant.GET_REEFER_IMP_GENERAL;
    public static final String GET_TERMINAL_GENERAL = RcmConstant.GET_TERMINAL_GENERAL;
    public static final String GET_TERMINAL_FILTERED = RcmConstant.GET_TERMINAL_FILTERED;
    public static final String GET_GENERAL_WITH_COMPLETED = RcmConstant.GET_GENERAL_WITH_COMPLETED;
    public static final String GET_SUPPORTED_PORT_GROUP = RcmConstant.GET_SUPPORTED_PORT_GROUP;
    public static final String GET_SRV_VSS_VOY_PORT = RcmConstant.GET_SRV_VSS_VOY_PORT;
    public static final String GET_SRV_VSS_VOY_INVOY= RcmConstant.GET_SRV_VSS_VOY_INVOY;
    public static final String GET_BL_FOR_DG_MANIFEST = RcmConstant.GET_BL_FOR_DG_MANIFEST;
    public static final String GET_VESSEL_FROM_VSA = RcmConstant.GET_VESSEL_FROM_VSA;
    //Help Version 02
    public static final String GET_HELP_V02_GENERAL = RcmConstant.GET_HELP_V02_GENERAL;
    public static final String GET_HELP_V02_WITH_USER_LEVEL_ACTIVE_STATUS = RcmConstant.GET_HELP_V02_WITH_USER_LEVEL_ACTIVE_STATUS;
    public static final String GET_HELP_V02_WITH_FSC = RcmConstant.GET_HELP_V02_WITH_FSC;
    public static final String GET_HELP_V02__WITH_ACTIVE_STATUS = RcmConstant.GET_HELP_V02__WITH_ACTIVE_STATUS;    //##02
    
    public static final String GET_VESSEL_OPR = RcmConstant.GET_VESSEL_OPR;
    
    protected static final short UNCHECKED = 0;
    protected static final short CHECKED_OK = 1;
    protected static final short CHECKED_NOT_OK = -1;
    
    public static final String LEVEL_DEFAULT = "A";
    public static final String LEVEL_LINE = "L";
    public static final String LEVEL_REGION = "R";
    public static final String LEVEL_AGENT = "A";
    public static final int RUTPAGE_VERSION_01 = 1;
    public static final int RUTPAGE_VERSION_02 = 2;
    
    public static final int SELECT_NOT_CHOOSED= -1;
    
    protected RcmConstantDao rcmConstantDao;
    protected RcmUserDao rcmUserDao;
    protected CamFscDao camFscDao;
    
    protected RutPage rutPage;
    protected String level;
    protected String lineCodeOfUser;
    protected String regionCodeOfUser;
    protected String agentCodeOfUser;
    protected String fscCodeOfUser;
    protected String prsnLogIdOfUser;
    protected String fscFromLogin;    
    protected String office;    // ##04
    protected int noOfResultRec;
    protected int rutPageVersion;
    protected boolean isControlFsc;
   // protected int reportGenerateNo;
    
    public RrcStandardUim() { 
        level = LEVEL_DEFAULT;
        lineCodeOfUser = "";
        regionCodeOfUser = "";
        agentCodeOfUser = "";
        fscCodeOfUser = "";
        prsnLogIdOfUser = "";
        noOfResultRec = 1;
        isControlFsc = false;
        office = ""; // ##04
       
       // reportGenerateNo = 0;
      
    }
    
    public void setRcmConstantDao(RcmConstantDao rcmConstantDao) {
        this.rcmConstantDao = rcmConstantDao;
    }
    
    public void setRcmUserDao(RcmUserDao rcmUserDao) {
        this.rcmUserDao = rcmUserDao;
    }
    
    public void setCamFscDao(CamFscDao camFscDao) {
        this.camFscDao = camFscDao;
    }
    
    public CamFscDao getCamFscDao() {
        return this.camFscDao ;
    }
    
    private String getReportStartTime() {
        
       try {
       
           // OC4J Local TimeZone is England, but on DEV, it is Singapore TimeZone. Don't need to convert to Singapore at this step
           DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss aa");
           Date date = new Date();
           return dateFormat.format(date).toString();
           
       } catch (Exception ex) {
           
           return null;
       }
    }

    /**
     * Procedure for creating URL pattern for generating DCS Reports (both PDF and Excel format)
     * Add parameters P_USE_NAME and P_START_DATE in order to capture who/when call reports
     * @return
     */
    public String getReportUrlConstant() {
        RcmReportServerConfigMod reportServerConfigMod = this.rcmConstantDao.getDefaultReportServerConfigMod();
        
        //## 09 Begin
        try {
            //String strReportUrlConstant = "http://"+reportServerConfigMod.getServerName()+":"+reportServerConfigMod.getPortNumber()+"/reports/rwservlet?rclrepenv&report=";
            
            String strLogInID = "DEV_TEAM";
            
            String strReportUrlConstant = "http://"+reportServerConfigMod.getServerName()+":"+reportServerConfigMod.getPortNumber();
            strReportUrlConstant += "/reports/rwservlet?&rclrepenv&P_USER_NAME=" + strLogInID;
            strReportUrlConstant += "&P_USER=" + strLogInID;
            strReportUrlConstant += "&P_U=" + strLogInID;
            strReportUrlConstant += "&P_USER_ID=" + strLogInID;
            strReportUrlConstant += "&P_UNAME=" + strLogInID;
            strReportUrlConstant += "&P_USER_LOGIN_ID=" + strLogInID;
            
            strReportUrlConstant += "&P_START_DATE=" + getReportStartTime();
            strReportUrlConstant += "&report=";
            
            return strReportUrlConstant;
        } catch (Exception exc) {
            
            exc.printStackTrace();
            return "";
        }
        //## 09 End
    }
    
    public String getResourceFolderImageConstant() {
        String resourceImage = "";
        if (rcmConstantDao != null) {
            resourceImage = rcmConstantDao.getConstantValueByKey(RcmConstant.RCM_CONSTANT_KEY_RESOURCE_ROOT_FOLDER_IMAGES);
        }
        return resourceImage;
    }
    
    // ##05 BEGIN
    public String getResourceFolderReportConstant() {
        String resourceImage = "";
        if (rcmConstantDao != null) {
            resourceImage = rcmConstantDao.getConstantValueByKey(RcmConstant.RCM_CONSTANT_KEY_RESOURCE_ROOT_FOLDER_REPORT);
        }
        return resourceImage;
    }
    // ##05 END
    
    public void setLevel(String level,String lineCodeOfUser,String regionCodeOfUser,String agentCodeOfUser,String fscCodeOfUser) {
        this.level = level;
        this.lineCodeOfUser = lineCodeOfUser;
        this.regionCodeOfUser = regionCodeOfUser;
        this.agentCodeOfUser = agentCodeOfUser;
        this.fscCodeOfUser = fscCodeOfUser;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setPrsnLogIdOfUser(String prsnLogIdOfUser) {
        this.prsnLogIdOfUser = prsnLogIdOfUser;
    }

    public String getPrsnLogIdOfUser() {
        return prsnLogIdOfUser;
    }
    
    public void setLineCodeOfUser(String lineCodeOfUser) {
        this.lineCodeOfUser = lineCodeOfUser;
    }

    public String getLineCodeOfUser() {
        return lineCodeOfUser;
    }

    public void setRegionCodeOfUser(String regionCodeOfUser) {
        this.regionCodeOfUser = regionCodeOfUser;
    }

    public String getRegionCodeOfUser() {
        return regionCodeOfUser;
    }

    public void setAgentCodeOfUser(String agentCodeOfUser) {
        this.agentCodeOfUser = agentCodeOfUser;
    }

    public String getAgentCodeOfUser() {
        return agentCodeOfUser;
    }

    public void setFscCodeOfUser(String fscCodeOfUser) {
        this.fscCodeOfUser = fscCodeOfUser;
    }

    public String getFscCodeOfUser() {
        return fscCodeOfUser;
    }
    
    public void setIsControlFsc(String lineCode, String regionCode, String agentCode, String fscCode){
        this.isControlFsc = camFscDao.isControlFsc(lineCode,regionCode,agentCode,fscCode);
    }
    
    public boolean isControlFsc(){
        return isControlFsc;
    }
    
    protected String toDateFormatFromYYYYMMDD(String date){
        if (RutDate.toDateFormatFromYYYYMMDD(date) == null){
            return "";
        }else{
            return RutDate.toDateFormatFromYYYYMMDD(date);        
        }
    }
    
    //begin: function RutFlagPage
    public void addItemInRutFlagPage(RutFlagPage rutFlagPage, int select, Object object) {
        rutFlagPage.addItemByPosition(select, object);
    }

    public void deleteItemInRutFlagPage(RutFlagPage rutFlagPage, List deleteFlagItems) {
        rutFlagPage.deleteItemByList(deleteFlagItems);
    }
    
    public void updateItemInRutFlagPage(RutFlagPage rutFlagPage){
        rutFlagPage.compareItems();
    }
    //end: function RutFlagPage
    
    public RutPage getRutPage() {
        return rutPage;
    }
    
    public void setRutPage(RutPage rutPage) {
        this.rutPage = rutPage;
    }
    
    public void createRutPage(List list,int size,int version){                
        if(list == null) list = new ArrayList();
        if(version == RutPage.VERSION_01){
            rutPage = new RutPage(list);
        }else if(version == RutPage.VERSION_02){
            rutPage = new RutPage(list,size,version);
        }else{
            rutPage = new RutPage();
        }
    }   
    public void createRutPage(int maxItemPerPage,int maxPage,List list,int size,int version){                
        if(list == null) list = new ArrayList();
        if(version == RutPage.VERSION_01){
            rutPage = new RutPage(list);
        }else if(version == RutPage.VERSION_02){
            rutPage = new RutPage(maxItemPerPage,maxPage,list,size,version);
        }else{
            rutPage = new RutPage();
        }
    } 
    public void setNoOfResultRecords(int noOfResultRec) {
        this.noOfResultRec = noOfResultRec;
        this.rutPageVersion = this.RUTPAGE_VERSION_02;
    }

    public int getNoOfResultRecord() {
        return noOfResultRec;
    } 

    public int getRutPageVersion() {
        return rutPageVersion;
    }

    public void setRutPageVersion(int rutPageVersion) {
        this.rutPageVersion = rutPageVersion;
    }

    public void setFscFromLogin(String fscFromLogin) {
        this.fscFromLogin = fscFromLogin;
    }

    public String getFscFromLogin() {
        return fscFromLogin;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOffice() {
        return office;
    }
    
    
    
   
}
