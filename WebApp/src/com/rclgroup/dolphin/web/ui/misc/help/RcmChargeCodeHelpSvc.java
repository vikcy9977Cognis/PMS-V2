package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RrcStandardHelpOptimizeSvc;
import com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim;
import com.rclgroup.dolphin.web.util.RutRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RcmChargeCodeHelpSvc extends RrcStandardHelpOptimizeSvc {
    public RcmChargeCodeHelpSvc() {
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmChargeCodeHelpSvc][execute]: Started");
        HttpSession session = request.getSession(false);
         
        RcmStandardHelpOptimizeUim uim = (RcmChargeCodeHelpUim) RutRequest.getSessionObject(session, RrcStandardHelpOptimizeSvc.INSTANCE_NAME_UIM_DEFAULT, RcmChargeCodeHelpUim.class);
        if (uim == null) {
            uim = new RcmChargeCodeHelpUim();
        }
        
        this.doExecute(request, uim);
        System.out.println("[RcmChargeCodeHelpSvc][execute]: Finished");
    }
}
