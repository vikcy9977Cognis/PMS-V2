/*-----------------------------------------------------------------------------------------------------------  
RcmPortHelpSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 05/11/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardHelpOptimizeSvc;
import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim;
import com.rclgroup.dolphin.web.util.RutRequest;
import com.rclgroup.dolphin.web.util.RutString;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RcmPortHelpSvc extends RrcStandardHelpOptimizeSvc {
    public RcmPortHelpSvc() {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmPortHelpSvc][execute]: Started");
        HttpSession session = request.getSession(false);
        String tsPolPort = request.getParameter("tsPolPort");
        String tsPodPort = request.getParameter("tsPodPort");
        String modelId = request.getParameter("modelId");
        if(tsPolPort == null){
             tsPolPort = (String) session.getAttribute("tsPolPort");
        } else{
            session.setAttribute("tsPolPort", tsPolPort);
        }
        
        if(tsPodPort == null){
             tsPodPort = (String) session.getAttribute("tsPodPort");
        } else{
            session.setAttribute("tsPodPort", tsPodPort);
        }
        
        RcmStandardHelpOptimizeUim uim = (RcmPortHelpUim) RutRequest.getSessionObject(session, RrcStandardHelpOptimizeSvc.INSTANCE_NAME_UIM_DEFAULT, RcmPortHelpUim.class);
        
        if (uim == null) {
            uim = new RcmPortHelpUim(tsPolPort, tsPodPort,modelId);
            
            System.out.println("[RcmPortHelpSvc][execute]: 1.tsPolPort="+tsPolPort);
            System.out.println("[RcmPortHelpSvc][execute]: 1.tsPodPort="+tsPodPort);
            System.out.println("[RcmPortHelpSvc][execute]: 1.modelId="+modelId);
            //begin: customize for port help
            String changeScreen = RutString.nullToStr(request.getParameter("changeScreen")).toUpperCase().trim();
            if ("T".equals(changeScreen)) {
                uim.setCamFscDao((CamFscDao)getBean("camFscDao"));
                
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
                this.manageUserBean(uim, userBean);    
            } 
            //end: customize for port help
        }
        
        this.doExecute(request, uim);
        System.out.println("[RcmPortHelpSvc][execute]: Finished");
    }
}
