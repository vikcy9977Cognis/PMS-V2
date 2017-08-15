/*-----------------------------------------------------------------------------------------------------------  
RcmUserHelpSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 28/03/2008
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RrcStandardHelpSvc;
import com.rclgroup.dolphin.web.common.RrcStandardHelpUim;
import com.rclgroup.dolphin.web.dao.sys.SysUserDao;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *    Service: RCM: User Help Screen 
 *
 */

public class RcmUserHelpSvc extends RrcStandardHelpSvc {
    public RcmUserHelpSvc() {
    }
     public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmUserHelpSvc][execute]: Started");
        HttpSession session=request.getSession(false);
        String helpUiModInstanceName = "rcmUserHelpUim";
        String helpScnName = "/RcmUserHelpScn.jsp?service=ui.misc.help.RcmUserHelpSvc";
        
        RrcStandardHelpUim uim = (RcmUserHelpUim)session.getAttribute(helpUiModInstanceName);
        RcmUserHelpUim newRcmUserHelpUim = new RcmUserHelpUim();
        
        newRcmUserHelpUim.setSysUserDao((SysUserDao)getBean("sysUserDao"));
        RrcStandardHelpUim newUim = newRcmUserHelpUim;
        
        helpProcess(request, helpUiModInstanceName, helpScnName, uim, newUim);
        System.out.println("[RcmUserHelpSvc][execute]: Finished");
    }
}
