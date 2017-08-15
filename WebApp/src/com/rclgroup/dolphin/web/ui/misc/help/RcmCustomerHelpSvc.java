/*-----------------------------------------------------------------------------------------------------------  
RcmCustomerHelpSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 15/07/2010
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


public class RcmCustomerHelpSvc extends RrcStandardHelpOptimizeSvc {
    public RcmCustomerHelpSvc() {
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmCustomerHelpSvc][execute]: Started");
        HttpSession session = request.getSession(false);
         
        RcmStandardHelpOptimizeUim uim = (RcmCustomerHelpUim) RutRequest.getSessionObject(session, RrcStandardHelpOptimizeSvc.INSTANCE_NAME_UIM_DEFAULT, RcmCustomerHelpUim.class);
        if (uim == null) {
            uim = new RcmCustomerHelpUim();
        }
         
        this.doExecute(request, uim);
        System.out.println("[RcmCustomerHelpSvc][execute]: Finished");
    }
}
