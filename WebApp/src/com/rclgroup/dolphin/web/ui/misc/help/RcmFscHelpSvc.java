/*-----------------------------------------------------------------------------------------------------------  
RcmFscHelpSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 09/11/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RrcStandardHelpOptimizeSvc;
import com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim;
import com.rclgroup.dolphin.web.util.RutRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RcmFscHelpSvc extends RrcStandardHelpOptimizeSvc {
    public RcmFscHelpSvc() {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmFscHelpSvc][execute]: Started");
        HttpSession session = request.getSession(false);
         
        RcmStandardHelpOptimizeUim uim = (RcmFscHelpUim) RutRequest.getSessionObject(session, RrcStandardHelpOptimizeSvc.INSTANCE_NAME_UIM_DEFAULT, RcmFscHelpUim.class);
        if (uim == null) {
            uim = new RcmFscHelpUim();
        }
        
        this.doExecute(request, uim);
        System.out.println("[RcmFscHelpSvc][execute]: Finished");
    }
}
