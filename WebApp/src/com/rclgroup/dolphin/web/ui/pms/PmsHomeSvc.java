/*-----------------------------------------------------------------------------------------------------------
PmsHomeSvc.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------
 Author Khomsun H. 24/03/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;
import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;
import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.exception.CustomDataAccessException;
import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.SQLException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PmsHomeSvc extends RrcStandardSvc{
    public PmsHomeSvc() {
        super();
    }
    public void execute(HttpServletRequest request,
                        HttpServletResponse response, ServletContext context) {
        String strTarget = null;
        HttpSession session = request.getSession(false); 
        String strUserID = "";
        try {
             session = request.getSession(false); 
            // <-- Update last access time
            boolean isAjaxPoll =
                "Y".equalsIgnoreCase(request.getHeader(PmsConstant.AJAX_POLL_KEY));

            if (session != null && !isAjaxPoll) {
                session.setAttribute(PmsConstant.LAST_ACCESS_TIME_KEY,
                                     new Date().getTime());
            }
            // -->
            
            // Clear status message.
            session.setAttribute("statusCode", PmsConstant.STATUS_SUCCESS);
            session.setAttribute("statusMessage", "");
            
            String strPageAction = RutString.nullToStr(request.getParameter("pageAction"));
            
            String strPart = RutString.nullToStr(request.getParameter("part"));
            
 
           
          PmsCommonUim uim =
              (PmsCommonUim)session.getAttribute("pmsCommonUim");
          if (uim == null || strPageAction.equalsIgnoreCase("new")) {
              uim = new PmsCommonUim();
              
            uim.setRcmConstantDao((RcmConstantDao)getBean("rcmConstantDao"));
            uim.setCamFscDao((CamFscDao)getBean("camFscDao"));
              // Manage the attributes of User: line, region, agent, fsc
              RcmUserBean userBean =
                  (RcmUserBean)session.getAttribute("userBean");
              this.manageUserBean(uim, userBean);

              uim.setPmsCommonDao((PmsCommonDao)getBean("pmsCommonDao"));

              String strPermissionUser = this.getPermissionUserCode(uim);
              //   uim.setUserID(uim.getPrsnLogIdOfUser());
              //                   uim.setFscDateFormat(userBean.getFscDateFormat());
              session.setAttribute("pmsCommonUim", uim);      
              strUserID = uim.getPrsnLogIdOfUser();
              
          }
         // uim.getJobBrandList();
            if (strPageAction.equalsIgnoreCase("new")) {
                // Clear Session UIM
                session.removeAttribute("pmsPMUim");
                session.removeAttribute("pmsReportUim");
                session.removeAttribute("pmsPMWizardUim");
                session.removeAttribute("pmsMaintenanceUim");
               // Get Employee profile
                final List<CrmProfileMod> crmProfilModList = uim.getProfileType();
                if (crmProfilModList !=null && crmProfilModList.size() > 0) {
                    session.setAttribute("employeeProfile", crmProfilModList.get(0));
                    strTarget = RcmConstant.PMS_PAGE_URL + "/PmsHomeScn.jsp";
                } else {
                    // Error Employee Profile not found
                    // Clear status message.
                    session.setAttribute("statusCode", PmsConstant.STATUS_ERROR);
                    session.setAttribute("statusMessage", "Could not found user ID:"+ strUserID +".Please contact Administrator or HR.");
                     strTarget = RcmConstant.PMS_PAGE_URL + "/PmsErrorScn.jsp";
                    
                }       
            } else if (strPageAction.equalsIgnoreCase("maintenance")) {
                // Get Employee profile
                 final List<CrmProfileMod> crmProfilModList = uim.getProfileType();
                 if (crmProfilModList !=null && crmProfilModList.size() > 0) {
                     session.setAttribute("employeeProfile", crmProfilModList.get(0));
                     strTarget = RcmConstant.PMS_PAGE_URL + "/PmsHomeScn.jsp#maintenance";
                 } else {
                     // Error Employee Profile not found
                     // Clear status message.
                     session.setAttribute("statusCode", PmsConstant.STATUS_ERROR);
                     session.setAttribute("statusMessage", "Could not found user ID:"+ strUserID +".Please contact Administrator or HR.");
                      strTarget = RcmConstant.PMS_PAGE_URL + "/PmsErrorScn.jsp";                     
                 }       
            }
        }catch (Exception exc) {
            exc.printStackTrace();
            if (exc instanceof CustomDataAccessException) {
                session.setAttribute("statusMessage", "Could not found user ID:"+ strUserID +".Please contact Administrator or HR.");
            } else {
                session.setAttribute("statusMessage", "Intenal server error, Please contact Administrator. Cause: " + exc.getMessage());     
            }
            session.setAttribute("statusCode", PmsConstant.STATUS_ERROR);           
            strTarget = RcmConstant.PMS_PAGE_URL + "/PmsErrorScn.jsp";            
        }
        request.setAttribute("target", strTarget);
    }
}
