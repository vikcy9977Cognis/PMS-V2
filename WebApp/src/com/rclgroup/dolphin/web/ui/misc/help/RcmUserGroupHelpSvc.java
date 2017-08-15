/*-----------------------------------------------------------------------------------------------------------  
RcmUserGroupHelpSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Manop Wanngam 27/03/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

 package com.rclgroup.dolphin.web.ui.misc.help;

 import com.rclgroup.dolphin.web.common.RrcStandardHelpSvc;
 import com.rclgroup.dolphin.web.common.RrcStandardHelpUim;

import com.rclgroup.dolphin.web.dao.sys.SysUserGroupDao;

import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;

 public class RcmUserGroupHelpSvc extends RrcStandardHelpSvc {
     public RcmUserGroupHelpSvc() {
     }

     public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
         System.out.println("[RcmUserGroupHelpSvc][execute]: Started");
         HttpSession session=request.getSession(false);
         String helpUiModInstanceName = "rcmUserGroupHelpUim";
         String helpScnName = "/RcmUserGroupHelpScn.jsp?service=ui.misc.help.RcmUserGroupHelpSvc";
         
         RrcStandardHelpUim uim = (RcmUserGroupHelpUim)session.getAttribute(helpUiModInstanceName);
         RcmUserGroupHelpUim newRcmUserGroupHelpUim = new RcmUserGroupHelpUim();
         
         newRcmUserGroupHelpUim.setSysUserGroupDao((SysUserGroupDao)getBean("sysUserGroupDao"));
         RrcStandardHelpUim newUim = new RcmUserGroupHelpUim();
         
         helpProcess(request, helpUiModInstanceName, helpScnName, uim, newUim);
         System.out.println("[RcmUserGroupHelpSvc][execute]: Finished");
     }
 }
