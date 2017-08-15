/*-----------------------------------------------------------------------------------------------------------  
RcmChargeCodeHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description
01 10/05/13  Leena      STR            Added type GET_STR_CHG_CODE for STR module
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

public class RcmChargeCodeHelpUim extends RcmStandardHelpOptimizeUim {
    
    private String usrPermission;
    private String invoiceType = "";
    
    public RcmChargeCodeHelpUim() {
        super();
    }
    
    public void manageRequestParameter(HttpServletRequest request) {
        
        //get request parameter        
        this.invoiceType = RutString.nullToStr(request.getParameter("invoiceType")).toUpperCase().trim();
        
        // default sort
        
        if (invoiceType.equals("M") || invoiceType.equals(""))
            this.setSortBy("fgcode");
        else if (invoiceType.equals("D"))
            this.setSortBy("frcode");
        
        this.setSortIn(RcmConstant.SORT_ASCENDING);
    }
    
    public void manageSessionParameter(HttpSession session) {
        // get session request
        if (RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type) && RutString.isEmptyString(this.usrPermission)) {
            RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
            
            this.usrPermission = this.getPermissionUserCode(userBean);
        }
    }
    
    public String findTitleNameShowByType(String type) {
        return "Charge Code";
    }
    
    public HashMap findColumnNameShowByType(String type) {
        HashMap columnNameShow = new HashMap();
        // columenNameShow = Column Text | Size | Align | Data Type [| Data Format Type | Data Format]
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type) || RcmConstant.GET_STR_CHG_CODE.equals(type)) {
        
            if (invoiceType.equals("M") || invoiceType.equals("")) {   
                columnNameShow.put("fgcode", new RcmColumnNameShowMod("Charge Code|11|left|STRING"));
                columnNameShow.put("fgdesc", new RcmColumnNameShowMod("Description|35|left|STRING"));
            } else if (invoiceType.equals("D"))  {
                columnNameShow.put("frcode", new RcmColumnNameShowMod("Charge Code|11|left|STRING"));
                columnNameShow.put("frlgds", new RcmColumnNameShowMod("Description|35|left|STRING"));
            }    
        }
        return columnNameShow;
    }
    
    public String[] findReturnValueByType(String type) {
        String[] arrReturnValue = null;
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type) || RcmConstant.GET_STR_CHG_CODE.equals(type)) {
        
            if (invoiceType.equals("M") || invoiceType.equals(""))    
                arrReturnValue = new String[] {"fgcode", "fgdesc"};
            else if (invoiceType.equals("D"))     
                arrReturnValue = new String[] {"frcode", "frlgds"};
      
        }
        return arrReturnValue;
    }
    
    public String findSqlStatementByType(String type) {
    
        StringBuffer sb = new StringBuffer("");
        String sql = "";
        if(RcmConstant.GET_STR_CHG_CODE.equals(type)){
            sql = " select fgcode, fgdesc " +
                " from itp092 " +
                " where chrg_ctg_cd = 'EM' " +
                " and chrg_grp_cd in ('EO','CY') "+
                " and fgrcst = 'A' ";
        }else if (invoiceType.equals("M") || invoiceType.equals(""))
            sql = " select fgcode, fgdesc " +
                " from itp092 " +
                " where chrg_grp_cd = 'MI' " +
                "     and fgrcst = 'A' ";
        else if (invoiceType.equals("D"))
            
            sql = " select frcode, frlgds " +
                " from itp090 " +
                " where frrcst = 'A' ";
                
        System.out.println("[RcmChargeCodeHelpUim][findSqlStatementByType] type:"+type);
        
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type) || RcmConstant.GET_STR_CHG_CODE.equals(type) ) {
            sb.append(sql);
            sb.append(" [and :columnName :conditionWild :columnFind] ");
        }
        
        sb.append("[order by :sortBy :sortIn] ");    
        
        System.out.println("[RcmFscHelpUim][findSqlStatementByType] FSCquery:"+sb.toString());
        return sb.toString();
    }
}
