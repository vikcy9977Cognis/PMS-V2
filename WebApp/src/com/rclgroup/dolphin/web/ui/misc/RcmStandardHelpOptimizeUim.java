/*-----------------------------------------------------------------------------------------------------------  
RcmStandardHelpOptimizeUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 01/09/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.misc;

import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardHelpOptimizeSvc;
import com.rclgroup.dolphin.web.common.RrcStandardHelpOptimizeUim;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RcmStandardHelpOptimizeUim extends RrcStandardHelpOptimizeUim {
    public RcmStandardHelpOptimizeUim() {
        super();
    }
    
    public String getPermissionUserCode(RcmUserBean userBean) {
        String permCode = "";
        if (userBean != null) {
            String line = userBean.getFscLvl1();
            String region = userBean.getFscLvl2();
            String agent = userBean.getFscLvl3();
            String fsc = userBean.getFscCode();
            
            if (region.equals("*")) { //check whether user is in line level
                permCode = "L:" + line;
            } else if (agent.equals("***")) { //check whether user is in region level
                permCode = "R:" + line + "|" + region;
            } else {
                permCode = "F:" + fsc;
            }
        }
        return permCode;
    }
    
    //begin: implement function
    public void manageRequestParameter(HttpServletRequest request) {
        //To do get request parameter
    }
    
    public void manageSessionParameter(HttpSession session) {
        //To do get session parameter
    }
    
    public void manageDao(RrcStandardHelpOptimizeSvc bean) {
        //To do get dao object
    }
    
    public void doBeforeOptional(RrcStandardHelpOptimizeSvc bean, HttpServletRequest request, HttpSession session) {
        //To do before [optional]
    }
    
    public void managePageActionOptional(String pageAction, RrcStandardHelpOptimizeSvc bean, HttpServletRequest request, HttpSession session) {
        //To do page action [optional]
    }
    
    public void manageLinkPageOptional(RrcStandardHelpOptimizeSvc bean, HttpServletRequest request) {
        //To do link page [optional]
    }
    
    public String findTitleNameShowByType(String type) {
        return "";
    }
    
    public HashMap findColumnNameShowByType(String type) {
        return null;
    }
    
    public String[] findReturnValueByType(String type) {
        return null;
    }
    
    public String findSqlStatementByType(String type) {
        return null;
    }
    //end: implement function
    
}
