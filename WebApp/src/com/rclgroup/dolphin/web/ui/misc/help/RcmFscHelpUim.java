/*-----------------------------------------------------------------------------------------------------------  
RcmFscHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 05/11/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.model.rcm.RcmColumnNameShowMod;
import com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim;
import com.rclgroup.dolphin.web.util.RutString;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class RcmFscHelpUim extends RcmStandardHelpOptimizeUim {
    private String region;
    private String agent;
    private String usrPermission;
    
    public RcmFscHelpUim() {
        super();
        
        // default sort
        this.setSortBy("LINE_CODE");
        this.setSortIn(RcmConstant.SORT_ASCENDING);
    }
    
    //begin: implement function
    public void manageRequestParameter(HttpServletRequest request) {
        //get request parameter
        String usrPermission = RutString.nullToStr(request.getParameter("usrPerm")).toUpperCase().trim();
         
        this.usrPermission = usrPermission;
    }
    
    public void manageSessionParameter(HttpSession session) {
        // get session request
        if (RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type) && RutString.isEmptyString(this.usrPermission)) {
            RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
            
            this.usrPermission = this.getPermissionUserCode(userBean);
        }
    }
     
    public String findTitleNameShowByType(String type) {
        return "FSC";
    }
    
    public HashMap findColumnNameShowByType(String type) {
        HashMap columnNameShow = new HashMap();
        // columenNameShow = Column Text | Size | Align | Data Type [| Data Format Type | Data Format]
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)) {
            columnNameShow.put("FSC_CODE", new RcmColumnNameShowMod("FSC Code|11|left|STRING"));
            columnNameShow.put("FSC_NAME", new RcmColumnNameShowMod("FSC Name|22|left|STRING"));
            columnNameShow.put("LINE_CODE", new RcmColumnNameShowMod("Line Code|11|left|STRING"));
            columnNameShow.put("REGION_CODE", new RcmColumnNameShowMod("Region Code|12|left|STRING"));
            columnNameShow.put("AGENT_CODE", new RcmColumnNameShowMod("Agent Code|12|left|STRING"));
            columnNameShow.put("STATUS", new RcmColumnNameShowMod("Status|12|left|FUNCTION|com.rclgroup.dolphin.web.util.RutCodeDescription|getStatusDesc"));
        }
        return columnNameShow;
    }
     
    public String[] findReturnValueByType(String type) {
        String[] arrReturnValue = null;
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)) {
            arrReturnValue = new String[] {"REGION_CODE", "AGENT_CODE", "FSC_CODE"};
        }
        return arrReturnValue;
    }
     
    public String findSqlStatementByType(String type) {
        StringBuffer sb = new StringBuffer("");
        
        System.out.println("[RcmFscHelpUim][findSqlStatementByType] type:"+type);
        
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)) {
            sb.append("select FSC_CODE ");
            sb.append(" ,FSC_NAME ");
            sb.append(" ,LINE_CODE ");
            sb.append(" ,REGION_CODE ");
            sb.append(" ,AGENT_CODE ");
            sb.append(" ,STATUS ");
            sb.append("from VR_CAM_FSC ");
            
            StringBuffer sbWhere = new StringBuffer("");
            if (RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)) {
                sbWhere.append(" and STATUS = '"+RcmConstant.RECORD_STATUS_ACTIVE+"' ");
            }
            if (usrPermission != null && !"".equals(usrPermission)) {
                sbWhere.append(" and PCR_RUT_UTILITY.FR_CHK_USR_PERM_FSC('"+usrPermission+"', FSC_CODE) = '"+RcmConstant.FLAG_YES+"' ");
            }
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            
            sb.append("[order by :sortBy :sortIn] ");            
        }
        System.out.println("[RcmFscHelpUim][findSqlStatementByType] FSCquery:"+sb.toString());
        return sb.toString();
    }
    //end: implement function
    
    public String getUsrPermission() {
        return usrPermission;
    }
    
    public void setUsrPermission(String usrPermission) {
        this.usrPermission = usrPermission;
    }    
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    public String getAgent() {
        return agent;
    }
    
    public void setAgent(String agent) {
        this.agent = agent;
    }
    
}
