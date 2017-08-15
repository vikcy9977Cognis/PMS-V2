 /*--------------------------------------------------------
 RcmPopupReportSvc.java
 --------------------------------------------------------
 Copyright RCL Public Co., Ltd. 2009
 --------------------------------------------------------
 Author Wuttitorn Wuttijiaranai 29/09/2009
 - Change Log--------------------------------------------
 ## DD/MM/YY -User- -TaskRef- -ShortDescription

 --------------------------------------------------------
 */
package com.rclgroup.dolphin.web.ui.misc;

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.util.RutCodeDescription;
import com.rclgroup.dolphin.web.util.RutString;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RcmPopupReportSvc extends RrcStandardSvc {
    public RcmPopupReportSvc() {
        super();
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmPopupReportSvc][execute]: Started");
        String target = RcmConstant.ERR_PAGE_URL + "/RcmErrorPage.jsp";
        
        StringBuffer errorMsgBuffer = new StringBuffer();
        HttpSession session = request.getSession(false); 
        RcmPopupReportUim uim = (RcmPopupReportUim) session.getAttribute("rcmPopupReportUim");
        if (uim == null) {
            uim = new RcmPopupReportUim(); 
        }
        
        String pageAction = RutString.getParameterToString(request, "pageAction", "default", RutString.CASE_TYPE_NORMAL);
        System.out.println("[RcmPopupReportSvc][execute]: pageAction = "+pageAction);
        
        RutString.nullToStr(request.getParameter("pageAction"));
        if (pageAction.equalsIgnoreCase("default")) {
            String reportUrl = RutString.getParameterToString(request, "reportUrl");
            System.out.println("[RcmPopupReportSvc][execute]: reportUrl = "+reportUrl);
            
            if (RutString.isEmptyString(reportUrl)) {
                errorMsgBuffer.append(getErrorMessage("RCM.INTERNAL_OCCURS_ERROR"));
            } else {
                uim.setReportUrl(reportUrl);                  
            }
            
            System.out.println("[RcmPopupReportSvc][execute]: errMsg = " + errorMsgBuffer.toString());
            if (!errorMsgBuffer.toString().equals("")) {
                session.setAttribute("errMsg", errorMsgBuffer.toString());
            }
            
            session.setAttribute("rcmPopupReportUim", uim);          
            request.setAttribute("target", RcmConstant.MISC_PAGE_URL +  "/RcmPopupReportScn.jsp");
        
        } else {
            request.setAttribute("target", target);
        }
        
        System.out.println("[RcmPopupReportSvc][execute]: target = "+target);
        System.out.println("[RcmPopupReportSvc][execute]: Finished");
    }   
    
}
