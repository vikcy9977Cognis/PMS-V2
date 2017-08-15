/*-----------------------------------------------------------------------------------------------------------  
RrcStandardSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 04/03/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 19/11/09 WUT                       Added function for get collection FSC and permission user
02 13/10/10 WUT                       Added function for get default port
03 12/11/10 KIT                       Added office user
04 16/11/10 WUT
05 15/11/12 SUJ                       Update report acess
06 20/11/12 SUJ                       Update report acess
07 27/11/12 SUJ                       Update report acess
07 05/12/12 SUJ                       Update report acess
08 10/12/12 SUJ                       Update report acess
08 13/12/12 SUJ                       Update report acess
09 07/03/06 SUJ                       add user parameter and addition param
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.util.RutErrorMessage;
import com.rclgroup.dolphin.web.util.RutMessage;
import com.rclgroup.dolphin.web.util.RutString;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public abstract class RrcStandardSvc implements RriStandardSvc{  
    ServletContext servletContext;
    
    public RrcStandardSvc() {
    }
    
    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
    }
    
    private ApplicationContext getApplicationContext(ServletContext servletContext) {
       return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    public Object getBean(String beanName) {
        return getApplicationContext(servletContext).getBean(beanName);
    }
   
    //this method can be used only for getting only xxxDao class
    public Object getDao(HttpServletRequest request, String beanName) {
        HttpSession session = request.getSession(false);
        RcmUserBean rcmUserBean = (RcmUserBean) session.getAttribute("userBean");
        Object object = getApplicationContext(servletContext).getBean(beanName);
        RriStandardDao rriStandardDao = (RriStandardDao) object;
        rriStandardDao.setRcmUserBean(rcmUserBean);
        return object;
    }
    
    public String getMessage(String code, Object[] args, Locale locale){
        return new RutMessage().getMessage(code,args,locale);
    }
    
    public String getMessage(String code, Object[] args){
        return new RutMessage().getMessage(code,args);
    }
    
    public String getMessage(String code){
        return new RutMessage().getMessage(code);
    }
    
    public String getErrorMessage(String code, Object[] args, Locale locale){
        return new RutMessage().getErrorMessage(code,args,locale);
    }
    
    public String getErrorMessage(String code, Object[] args){
        return new RutMessage().getErrorMessage(code,args);
    }
    
    public String getErrorMessage(String code){
        return new RutMessage().getErrorMessage(code);
    }
    
    // ##01 BEGIN
    public void manageUserBean(RrcStandardUim uim, RcmUserBean userBean) {
        if (userBean != null) {
            String line = userBean.getFscLvl1();
            String region = userBean.getFscLvl2();
            String agent = userBean.getFscLvl3();
            String fsc = userBean.getFscCode();
            String userLogin = RutString.toUrlEncoding(userBean.getPrsnLogId());
            String fscFromLogin = userBean.getFscCode();
            
            uim.setPrsnLogIdOfUser(userLogin);
            uim.setFscFromLogin(fscFromLogin);
            uim.setIsControlFsc(line,region,agent,fsc); 
            uim.setLineCodeOfUser(line);
            uim.setOffice(fsc); // ##03
            if (region.equals("*")) { //check whether user is in line level
                uim.setLevel(RrcStandardUim.LEVEL_LINE,line,"","","");
                uim.setRegionCodeOfUser("");
                uim.setAgentCodeOfUser("");
                uim.setFscCodeOfUser("");
            } else if(agent.equals("***")) { //check whether user is in region level
                uim.setLevel(RrcStandardUim.LEVEL_REGION,line,region,"","");
                uim.setRegionCodeOfUser(region);
                uim.setAgentCodeOfUser("");
                uim.setFscCodeOfUser("");
            } else if(uim.isControlFsc()) { //check whether user is in agent level
                uim.setLevel(RrcStandardUim.LEVEL_AGENT,line,region,agent,"");
            } else {
                uim.setLevel(RrcStandardUim.LEVEL_AGENT,line,region,agent,fsc);
            }    
        }
    }
    
    public String getCollFsc(RrcStandardUim uim) {
        String collFsc = "";
        if (uim != null) {
            String line = uim.getLineCodeOfUser();
            String region = uim.getRegionCodeOfUser();
            String agent = uim.getAgentCodeOfUser();
            String fsc = uim.getFscCodeOfUser();
            
            if (!RutString.isEmptyString(fsc)) {
                collFsc = fsc;
            } else if (!RutString.isEmptyString(agent)) {
                collFsc = agent;
            } else if (!RutString.isEmptyString(region)) {
                collFsc = line + region;
            } else if (!RutString.isEmptyString(line)) {
                collFsc = line;
            }
        } 
        return collFsc;
    }
    
    public String getPermissionUserCode(RrcStandardUim uim) {
        String permCode = "";
        if (uim != null) {
            String line = uim.getLineCodeOfUser();
            String region = uim.getRegionCodeOfUser();
            String agent = uim.getAgentCodeOfUser();
            String fsc = uim.getFscCodeOfUser();
            
            if (!RutString.isEmptyString(fsc)) {
                permCode = "F:" + fsc;
            } else if (!RutString.isEmptyString(agent)) {
                permCode = "A:" + line + "|" + region + "|" + agent;
            } else if (!RutString.isEmptyString(region)) {
                permCode = "R:" + line + "|" + region;
            } else if (!RutString.isEmptyString(line)) {
                permCode = "L:" + line;
            }
        }
        return permCode;
    }
    // ##01 END

    // ##02 BEGIN
    public String getDefaultPort(RcmUserBean userBean) {
        if (userBean != null && !"R".equalsIgnoreCase(userBean.getFscLvl1())) {
            return userBean.getPort();
        } else {
            return "";
        }
    }
    // ##02 END
    
    // ##04 BEGIN
    public boolean getErrorMessageStandard(RutErrorMessage message, StringBuffer errorMessage) {
        boolean isExit = false;
        if (message != null && errorMessage != null) {
            if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_UPDATE_CONCURRENCY)) {
                errorMessage = new StringBuffer();
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_UPDATE_CONCURRENCY",message.getArgumentList().toArray()));
                isExit = true;
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_DELETE_CONCURRENCY)) {
                errorMessage = new StringBuffer();
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_DELETE_CONCURRENCY",message.getArgumentList().toArray()));
                isExit = true;
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_OBJECT_NOT_FOUND)) {
                errorMessage = new StringBuffer();
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_OBJECT_NOT_FOUND",message.getArgumentList().toArray()));
                isExit = true;
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_CHILD_RECORD_NOT_EXIST)) {
                errorMessage = new StringBuffer();
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_CHILD_RECORD_NOT_EXIST",message.getArgumentList().toArray()));
                isExit = true;
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_RECORD_STATUS_REQUIRED)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_RECORD_STATUS_REQUIRED",message.getArgumentList().toArray()));
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_RECORD_STATUS_NOT_IN_RANGE)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_RECORD_STATUS_NOT_IN_RANGE",message.getArgumentList().toArray()));
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_RECORD_ADD_USER_REQUIRED)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_RECORD_ADD_USER_REQUIRED",message.getArgumentList().toArray()));
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_RECORD_ADD_DATE_REQUIRED)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_RECORD_ADD_DATE_REQUIRED",message.getArgumentList().toArray()));
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_RECORD_CHANGE_USER_REQUIRED)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_RECORD_CHANGE_USER_REQUIRED",message.getArgumentList().toArray()));
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_RECORD_CHANGE_DATE_REQUIRED)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_RECORD_CHANGE_DATE_REQUIRED",message.getArgumentList().toArray()));
            } else if (message.getErrorCode().equals(RcmConstant.EXCEPTION_RCM_VALUE_DUPULICATE_RECORD)) {
                errorMessage.append(getErrorMessage("RCM.MAINTENANCE.SAVE.EXCEPTION_RCM_VALUE_DUPULICATE_RECORD",message.getArgumentList().toArray()));
            } else {
                //oracle errors that be caught: ORA-XXXXX
                errorMessage.append(getErrorMessage(message.getErrorCode(),message.getArgumentList().toArray()));
            }
        }
        return isExit;
    }
    // ##04 END
        
     public String getReportStartTime() {
         
        try {
        
            // OC4J Local TimeZone is England, but on DEV, it is Singapore TimeZone. Don't need to convert to Singapore at this step
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd KK:mm:ss aa");
            Date date = new Date();
            
            System.out.println(date.toString());
            System.out.println(dateFormat.format(date).toString());            
            
            return dateFormat.format(date).toString();
            
        } catch (Exception ex) {
            
            return null;
        }
     }
     
     public String getReportUrlAdditionAndUserParam(RrcStandardUim uim, boolean isUseUserLoginData){
     
        StringBuffer reportUrlBf = new StringBuffer();
        
        reportUrlBf.append( "&"+RcmConstant.P_START_DATE_PARAM+"="+getReportStartTime());
        
        if(isUseUserLoginData){
             if (!RutString.isEmptyString(uim.getPrsnLogIdOfUser())) {
                 reportUrlBf.append( "&"+RcmConstant.USER_LOGIN_ID_PARAM+"="+uim.getPrsnLogIdOfUser());
             }
             
             if (!RutString.isEmptyString(uim.getLineCodeOfUser())) {
                 reportUrlBf.append( "&"+RcmConstant.USER_LOGIN_LINE_PARAM+"="+uim.getLineCodeOfUser());
             }
             
             if (!RutString.isEmptyString(uim.getRegionCodeOfUser())) {
                 reportUrlBf.append( "&"+RcmConstant.USER_LOGIN_TRADE_PARAM+"="+uim.getRegionCodeOfUser());
             }
             
             if (!RutString.isEmptyString(uim.getAgentCodeOfUser())) {
                 reportUrlBf.append( "&"+RcmConstant.USER_LOGIN_AGENT_PARAM+"="+uim.getAgentCodeOfUser());
             }
             
             if (!RutString.isEmptyString(uim.getFscCodeOfUser())&& !uim.getFscCodeOfUser().equals("R")) {
                 reportUrlBf.append( "&"+RcmConstant.USER_LOGIN_FSC_PARAM+"="+uim.getFscCodeOfUser());
             }
        }
        
       return reportUrlBf.toString();

     }
}
