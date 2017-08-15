/*-----------------------------------------------------------------------------------------------------------
RcmLoginSvc.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------
 Author Piyapong Ieumwananonthachai 09/11/07
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
01 07/04/08 PIE                       Modify to work in RCLWebApp.
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.dao.rcm.RcmUserDao;
import com.rclgroup.dolphin.web.model.rcm.RcmUserMod;
import com.rclgroup.dolphin.web.util.RutString;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RcmLoginSvc extends RrcStandardSvc {
    private static final String PASS_THROUGH = "VKIn:UCV]DI6uX";

    public RcmLoginSvc() {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmLoginSvc][execute]: Started");
        HttpSession session = request.getSession(true);
        String userId = RutString.nullToStr(request.getParameter("userId"));
        String passThrough =PASS_THROUGH;// RutString.nullToStr(request.getParameter("passThrough"));
        String target = RutString.nullToStr(request.getParameter("linkPage"));
        String pageAction = RutString.nullToStr(request.getParameter("pageAction"));
        
   //     String role = RutString.nullToStr(request.getParameter("role"));
        /*userId = "DEV_TEAM";
        passThrough = "VKIn:UCV]DI6uX";
        pageAction = "new";
        target = "/RrcStandardSrv?service=ui.bkg.BkgBookingAvailabilityReportSvc";*/
        
        //userId = "DEV_TEAM";
     //   userId = "TEST02";  
     //     userId = "WUTKIT1";
   //  userId = "NUOKAN1"; // Employee
  //  userId = "NIPPUT1";   // Employee
     //userId = "NIRCAM1"; // MgrLv1
    //    userId = "SOMEIA1"; // MgrLv2
        userId = "SUPAMN1"; // Admin
    // userId = "CHAABB1R";
              passThrough = "VKIn:UCV]DI6uX";
                pageAction = "new";
               // target = "/RrcStandardSrv?service=" + RutString.nullToStr(request.getParameter("select"));
             target = "/RrcStandardSrv?service=ui.pms.PmsHomeSvc";
        target = RcmConstant.SERV_URL + target;
        System.out.println("[RcmLoginSvc][execute]: target: " + target);
        if (!passThrough.equals(PASS_THROUGH)) {
            request.setAttribute("target", RcmConstant.ERR_PAGE_URL + "/RcmErrorPage.jsp");
            System.out.println("[RcmLoginSvc][execute]: passthrough is not passed");
            System.out.println("[RcmLoginSvc][execute]: Finished");
            return;
        }

        RcmUserMod userMod = new RcmUserMod();
        RcmUserBean userBean = new RcmUserBean();

        RcmUserDao rcmUserDao = (RcmUserDao)getBean("rcmUserDao");
      // Den, Comment this
        userMod = rcmUserDao.findByKeyPrsnLogId(userId);
      //
        
//        userMod = new RcmUserMod();
//        userMod.setPrsnLogId(userId);
//        userMod.setDescr("Dev User");
//        userMod.setFscCode("BKK");
//        userMod.setCountry("TH");
        //userMod.setPort("Port1");
//        userMod.setFscLvl1("BKK");
//        userMod.setFscLvl2("BKK");
//        userMod.setFscLvl3("BKK");
//        userMod.setFscDateFormat("1");

        // -->
//<-- TODO: Uncoment this line when finish test Login PMS
        userBean.setPrsnLogId(userMod.getPrsnLogId());
       // userBean.setPrsnLogId("12833");  
   //userBean.setPrsnLogId("CHAABB1R"); // Login Mgr Level1.
//-->
     
           userBean.setDescr(userMod.getDescr());
        userBean.setFscCode(userMod.getFscCode());
        userBean.setCountry(userMod.getCountry());
        userBean.setPort(userMod.getPort());
        userBean.setFscLvl1(userMod.getFscLvl1());
        userBean.setFscLvl2(userMod.getFscLvl2());
        userBean.setFscLvl3(userMod.getFscLvl3());
        userBean.setFscName(userMod.getFscName());
        if (userMod.getFscDateFormat() == "1") {
            userBean.setFscDateFormat("DD/MM/YYYY");
        } else {
            userBean.setFscDateFormat("MM/DD/YYYY");
        }
      // Den, Comment  
    //    userBean.setMenuAuthentication(rcmUserDao.getMenuAuthentication(userBean.getPrsnLogId()));
    // 
        
        session.setAttribute("userBean", userBean);
        request.setAttribute("target", target + "&pageAction=" + pageAction);
   //    request.setAttribute("target", target + "&pageAction=" + pageAction + "&role=" + role);
        System.out.println("[RcmLoginSvc][execute]: Finished");
    }
}
