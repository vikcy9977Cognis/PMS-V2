/*-----------------------------------------------------------------------------------------------------------  
RcmCustomerHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 15/07/2010
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 27/05/13 NUTTHA1                   Add condition for gathering customer as either Shipper or Consignee  
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


public class RcmCustomerHelpUim extends RcmStandardHelpOptimizeUim {
    private String usrPermission;

    public RcmCustomerHelpUim() {
        super();
        
        // default sort
        this.setSortBy("CUSTOMER_CODE");
        this.setSortIn(RcmConstant.SORT_ASCENDING);
    }
    
    //begin: implement function
    public void manageRequestParameter(HttpServletRequest request) {
        //get request parameter
        String usrPermission = RutString.nullToStr(request.getParameter("usrPerm")).toUpperCase().trim();
         
        this.usrPermission = usrPermission;
        
        //#01 
        String type = RutString.nullToStr(request.getParameter("type")).toUpperCase().trim();
        if ("GET_SHIPPER".equals(type) || ("GET_CONSIGNEE".equals(type) )) {
             this.setSortBy("CUCUST");
             this.setSortIn(RcmConstant.SORT_ASCENDING);
        }
    }
    
    public void manageSessionParameter(HttpSession session) {
        //get session parameter    
        if (!RutString.isEmptyString(this.usrPermission)) {
             RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
             
             this.usrPermission = this.getPermissionUserCode(userBean);
        }
        
        
    }
     
    public String findTitleNameShowByType(String type) {
    
        if ("GET_SHIPPER".equals(type)) //#01
            return "Shipper";
        else if ("GET_CONSIGNEE".equals(type)) //#01
            return "Consignee";
        else
            return "Customer";
    }
     
    public HashMap findColumnNameShowByType(String type) {
        HashMap columnNameShow = new HashMap();
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_HELP_V02_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)){
            // columenNameShow = Column Text | Size | Align | Data Type [| Data Format Type | Data Format]
            columnNameShow.put("CUSTOMER_CODE", new RcmColumnNameShowMod("Customer Code|5|left|STRING"));
            columnNameShow.put("DESCRIPTION", new RcmColumnNameShowMod("Description|10|left|STRING"));
            columnNameShow.put("ADDRESS", new RcmColumnNameShowMod("Address|13|left|STRING"));
            columnNameShow.put("CITY_NAME", new RcmColumnNameShowMod("City Name|5|left|STRING"));
            columnNameShow.put("STATE_CODE", new RcmColumnNameShowMod("State Code|3|left|STRING"));
            columnNameShow.put("STATE_NAME", new RcmColumnNameShowMod("State Name|7|left|STRING"));
            columnNameShow.put("ZIP_CODE", new RcmColumnNameShowMod("Zip Code|5|left|STRING"));
            columnNameShow.put("TELEPHONE", new RcmColumnNameShowMod("Telephone|5|left|STRING"));
            columnNameShow.put("FAX_NO", new RcmColumnNameShowMod("Fax No.|5|left|STRING"));
            columnNameShow.put("EMAIL", new RcmColumnNameShowMod("Email|5|left|STRING"));
            columnNameShow.put("COUNTRY_CODE", new RcmColumnNameShowMod("Country Code|5|left|STRING"));
            columnNameShow.put("COUNTRY_NAME", new RcmColumnNameShowMod("Country Name|7|left|STRING"));
        } else if (RcmConstant.GET_GENERAL_WITH_COMPLETED.equals(type)) {
            columnNameShow.put("CUSTOMER_CODE", new RcmColumnNameShowMod("Customer Code|5|left|STRING"));
            columnNameShow.put("DESCRIPTION", new RcmColumnNameShowMod("Description|10|left|STRING"));
            columnNameShow.put("ADDRESS", new RcmColumnNameShowMod("Address|13|left|STRING"));
            columnNameShow.put("CITY_NAME", new RcmColumnNameShowMod("City Name|5|left|STRING"));
            columnNameShow.put("STATE_CODE", new RcmColumnNameShowMod("State Code|3|left|STRING"));
            columnNameShow.put("STATE_NAME", new RcmColumnNameShowMod("State Name|7|left|STRING"));
            columnNameShow.put("ZIP_CODE", new RcmColumnNameShowMod("Zip Code|5|left|STRING"));
            columnNameShow.put("TELEPHONE", new RcmColumnNameShowMod("Telephone|5|left|STRING"));
            columnNameShow.put("FAX_NO", new RcmColumnNameShowMod("Fax No.|5|left|STRING"));
            columnNameShow.put("EMAIL", new RcmColumnNameShowMod("Email|5|left|STRING"));
            columnNameShow.put("COUNTRY_CODE", new RcmColumnNameShowMod("Country Code|5|left|STRING"));
            columnNameShow.put("COUNTRY_NAME", new RcmColumnNameShowMod("Country Name|7|left|STRING"));
            columnNameShow.put("CONSOLIDATED", new RcmColumnNameShowMod("Cosolidated Code|5|left|STRING"));                
        } else if("GET_SLOT_CUST".equals(type)){
            columnNameShow.put("CUSTOMER_CODE", new RcmColumnNameShowMod("Customer Code|5|left|STRING"));
            columnNameShow.put("DESCRIPTION", new RcmColumnNameShowMod("Description|10|left|STRING"));            
        } else if ("GET_SHIPPER".equals(type) || "GET_CONSIGNEE".equals(type)) {
            columnNameShow.put("cucust", new RcmColumnNameShowMod("Customer Code|5|left|STRING"));
            columnNameShow.put("cuname", new RcmColumnNameShowMod("Description|20|left|STRING"));   
            
        }
        
        return columnNameShow;
    }
     
    public String[] findReturnValueByType(String type) {
        String[] arrReturnValue = null;
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_HELP_V02_GENERAL.equals(type) || RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)) {
            arrReturnValue = new String[] {"CUSTOMER_CODE"};
        } else if (RcmConstant.GET_WITH_USER.equals(type) || "GET_SLOT_CUST".equals(type)) { //#01
            arrReturnValue = new String[] {"CUSTOMER_CODE", "DESCRIPTION"};
        } else if (RcmConstant.GET_GENERAL_WITH_COMPLETED.equals(type)) {
            arrReturnValue = new String[] {"CUSTOMER_CODE", "CONSOLIDATED", "DESCRIPTION", "CITY_NAME", "STATE_CODE", "ZIP_CODE", "COUNTRY_CODE"};
        } else if  ("GET_SHIPPER".equals(type) || "GET_CONSIGNEE".equals(type))
            //arrReturnValue = new String[] {"cucust", "cuname"};
             arrReturnValue = new String[] {"cucust"};
        return arrReturnValue;
    }
     
    public String findSqlStatementByType(String type) {
        StringBuffer sb = new StringBuffer("");
        
        if (RcmConstant.GET_GENERAL.equals(type) || RcmConstant.GET_WITH_USER.equals(type) || RcmConstant.GET_GENERAL_WITH_COMPLETED.equals(type)) {
            sb.append("select distinct CUSTOMER_CODE ");
            sb.append(" ,DESCRIPTION ");
            sb.append(" ,ADDRESS ");
            sb.append(" ,CITY_NAME "); 
            sb.append(" ,STATE_CODE "); 
            sb.append(" ,STATE_NAME "); 
            sb.append(" ,ZIP_CODE "); 
            sb.append(" ,TELEPHONE "); 
            sb.append(" ,FAX_NO "); 
            sb.append(" ,EMAIL "); 
            sb.append(" ,COUNTRY_CODE "); 
            sb.append(" ,COUNTRY_NAME ");
            sb.append(" ,CONSOLIDATED "); 
            sb.append("from VR_CRM_CUST_WITH_USER_LEVEL ");
            
            StringBuffer sbWhere = new StringBuffer("");
            if (!RcmConstant.GET_GENERAL_WITH_COMPLETED.equals(type)) {
                if (usrPermission != null && !"".equals(usrPermission)) {
                    sbWhere.append(" and PCR_RUT_UTILITY.FR_CHK_USR_PERM_FSC('"+usrPermission+"', FSC) = '"+RcmConstant.FLAG_YES+"' ");
                }    
            }
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            
            sb.append("[order by :sortBy :sortIn] ");
        
        } else if (RcmConstant.GET_HELP_V02_GENERAL.equals(type)) {
            sb.append("select distinct CUSTOMER_CODE ");
            sb.append(" ,DESCRIPTION ");
            sb.append(" ,ADDRESS ");
            sb.append(" ,CITY_NAME "); 
            sb.append(" ,STATE_CODE "); 
            sb.append(" ,STATE_NAME "); 
            sb.append(" ,ZIP_CODE "); 
            sb.append(" ,TELEPHONE "); 
            sb.append(" ,FAX_NO "); 
            sb.append(" ,EMAIL "); 
            sb.append(" ,COUNTRY_CODE "); 
            sb.append(" ,COUNTRY_NAME "); 
            sb.append("from VR_CRM_CUSTOMER_GENERAL ");
            sb.append("[where :columnName :conditionWild :columnFind] ");
            sb.append("[order by :sortBy :sortIn] ");
        
        } else if (RcmConstant.GET_WITH_USER_LEVEL_ACTIVE_STATUS.equals(type)) {
            sb.append("select distinct CUSTOMER_CODE ");
            sb.append(" ,DESCRIPTION ");
            sb.append(" ,ADDRESS1 as ADDRESS ");
            /*sb.append(" ,ADDRESS2 ");
            sb.append(" ,ADDRESS3 ");
            sb.append(" ,ADDRESS4 ");*/
            sb.append(" ,CITY_NAME "); 
            sb.append(" ,STATE_CODE "); 
            sb.append(" ,STATE_NAME "); 
            sb.append(" ,ZIP_CODE "); 
            sb.append(" ,TELEPHONE "); 
            sb.append(" ,FAX_NO "); 
            sb.append(" ,EMAIL "); 
            sb.append(" ,COUNTRY_CODE "); 
            sb.append(" ,COUNTRY_NAME "); 
            sb.append("from VR_DIM_BANKGUARANTEE ");
            
            StringBuffer sbWhere = new StringBuffer("");
            if (usrPermission != null && !"".equals(usrPermission)) {
                sbWhere.append(" and PCR_RUT_UTILITY.FR_CHK_USR_PERM_FSC('"+usrPermission+"', RECEIVED_FSC) = '"+RcmConstant.FLAG_YES+"' ");
            }
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            
            sb.append("[order by :sortBy :sortIn] ");
        } else if ("GET_SLOT_CUST".equals(type)){
            sb.append("select distinct CUSTOMER_CODE ");
            sb.append(" ,DESCRIPTION ");            
            sb.append("from VR_CRM_CUST_WITH_USER_LEVEL ");
            
            StringBuffer sbWhere = new StringBuffer("");
            if (!RcmConstant.GET_GENERAL_WITH_COMPLETED.equals(type)) {
                if (usrPermission != null && !"".equals(usrPermission)) {
                    sbWhere.append(" and PCR_RUT_UTILITY.FR_CHK_USR_PERM_FSC('"+usrPermission+"', FSC) = '"+RcmConstant.FLAG_YES+"' ");
                }    
            }
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            
            sb.append("[order by :sortBy :sortIn] ");
        } else if("GET_SHIPPER".equals(type) || "GET_CONSIGNEE".equals(type)){ //#01
            
            String sqlStmt = "select distinct cucust, cuname";
            sqlStmt += " from itp010 ";
            sqlStmt += " where curcst='A'";
            
            sb.append(sqlStmt);
            sb.append(" [and :columnName :conditionWild :columnFind]");
            sb.append(" [order by :sortBy :sortIn]");
            
        } else if (!RcmConstant.GET_GENERAL_WITH_COMPLETED.equals(type)) {
            sb.append("select distinct CUSTOMER_CODE ");
            sb.append(" ,DESCRIPTION ");
            sb.append(" ,ADDRESS ");
            sb.append(" ,CITY_NAME "); 
            sb.append(" ,STATE_CODE "); 
            sb.append(" ,STATE_NAME "); 
            sb.append(" ,ZIP_CODE "); 
            sb.append(" ,TELEPHONE "); 
            sb.append(" ,FAX_NO "); 
            sb.append(" ,EMAIL "); 
            sb.append(" ,COUNTRY_CODE "); 
            sb.append(" ,COUNTRY_NAME ");
            sb.append(" ,CONSOLIDATED "); 
            sb.append("from VR_CRM_CUST_WITH_USER_LEVEL ");
            
            StringBuffer sbWhere = new StringBuffer("");
            if (usrPermission != null && !"".equals(usrPermission)) {
                sbWhere.append(" and PCR_RUT_UTILITY.FR_CHK_USR_PERM_FSC('"+usrPermission+"', FSC) = '"+RcmConstant.FLAG_YES+"' ");
            }    
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            
            sb.append("[order by :sortBy :sortIn] ");
        }  
        
        return sb.toString();
    }
    //end: implement function

    public String getUsrPermission() {
        return usrPermission;
    }
    
    public void setUsrPermission(String usrPermission) {
        this.usrPermission = usrPermission;
    }
}
