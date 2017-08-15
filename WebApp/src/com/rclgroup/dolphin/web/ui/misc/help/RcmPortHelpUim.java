/*-----------------------------------------------------------------------------------------------------------  
RcmPortHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 05/11/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.model.rcm.RcmColumnNameShowMod;
import com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim;
import com.rclgroup.dolphin.web.util.RutString;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


public class RcmPortHelpUim extends RcmStandardHelpOptimizeUim {
    private String usrPermission;
    private String zoneCode;
    private String fscCode;
    private String changeScreen;
    private String tsPolPort;
    private String tsPodPort;
    private String modelId;
    public RcmPortHelpUim() {
        super();
        
        // default sort
        this.setSortBy("PORT_CODE");
        this.setSortIn(RcmConstant.SORT_ASCENDING);
    }
    
    public RcmPortHelpUim(String tsPolPort, String tsPodPort, String modelId) {
        super();
        
        // default sort
        this.setSortBy("PORT_CODE");
        this.setSortIn(RcmConstant.SORT_ASCENDING);
        this.tsPolPort = tsPolPort;
        this.tsPodPort = tsPodPort;
        this.modelId = modelId;
    }
    
    //begin: implement function
    public void manageRequestParameter(HttpServletRequest request) {
        //get request parameter
        String usrPermission = RutString.nullToStr(request.getParameter("usrPerm")).toUpperCase().trim();
        String zoneCode = RutString.nullToStr(request.getParameter("txtZone")).toUpperCase().trim();
        String fscCode = RutString.nullToStr(request.getParameter("hidFsc")).toUpperCase().trim();
        String changeScreen = RutString.nullToStr(request.getParameter("changeScreen")).toUpperCase().trim();
        tsPolPort = RutString.nullToStr(request.getParameter("tsPolPort"));
        tsPodPort = RutString.nullToStr(request.getParameter("tsPodPort"));
        modelId = RutString.nullToStr(request.getParameter("modelId"));
        
        this.usrPermission = usrPermission;
        this.zoneCode = zoneCode;
        this.fscCode = fscCode;
        this.changeScreen = changeScreen;
    }
    
    public String findTitleNameShowByType(String type) {
        return "Port";
    }
    
    public HashMap findColumnNameShowByType(String type) {
        HashMap columnNameShow = new HashMap();
        
        // columenNameShow = Column Text | Size | Align | Data Type [| Data Format Type | Data Format]
        if ("T".equals(changeScreen)) {
            columnNameShow.put("PORT_CODE", new RcmColumnNameShowMod("Port Code|10|left|STRING"));
            columnNameShow.put("PORT_NAME", new RcmColumnNameShowMod("Port Name|17|left|STRING"));
            columnNameShow.put("TQTERM", new RcmColumnNameShowMod("Terminal Code|14|left|STRING"));
            columnNameShow.put("TQTRNM", new RcmColumnNameShowMod("Terminal Name|30|left|STRING"));
        } else {
            columnNameShow.put("PORT_CODE", new RcmColumnNameShowMod("Port Code|15|left|STRING"));
            columnNameShow.put("PORT_GROUP", new RcmColumnNameShowMod("Port Group|15|left|STRING"));
            columnNameShow.put("PORT_NAME", new RcmColumnNameShowMod("Port Name|25|left|STRING"));
            columnNameShow.put("COUNTRY_CODE", new RcmColumnNameShowMod("Country|10|left|STRING"));
            columnNameShow.put("PORT_STATE", new RcmColumnNameShowMod("State|10|left|STRING"));
            columnNameShow.put("PORT_ZONE", new RcmColumnNameShowMod("Zone|15|left|STRING"));
            columnNameShow.put("PORT_TYPE", new RcmColumnNameShowMod("Type|7|left|STRING"));
            columnNameShow.put("PORT_STATUS", new RcmColumnNameShowMod("Status|7|left|STRING"));
        }
        return columnNameShow;
    }
    
    public String[] findReturnValueByType(String type) {
        String[] arrReturnValue = null;
        if (RcmConstant.GET_GENERAL.equals(type)) {
            arrReturnValue = new String[] {"PORT_CODE"};
        } else if (RcmConstant.GET_TERMINAL_GENERAL.equals(type) && "T".equals(changeScreen)) {
            arrReturnValue = new String[] {"PORT_CODE", "TQTERM"};
        } else if (RcmConstant.GET_WITH_ACTIVE_STATUS.equals(type) || "GET_FOR_EMS".equals(type)) {
            arrReturnValue = new String[] {"PORT_CODE", "AREA", "PORT_ZONE", "REGION"};
        } else if (RcmConstant.GET_FILTERED_RESULT.equals(type)) {
            arrReturnValue = new String[] {"PORT_CODE", "PORT_GROUP"};
        }
        return arrReturnValue;
    }
    
    public String findSqlStatementByType(String type) {
        StringBuffer sb = new StringBuffer("");
        if (RcmConstant.GET_GENERAL.equals(type)) {
            sb.append("select distinct PORT_CODE ");
            sb.append(" ,PORT_NAME ");
            sb.append(" ,COUNTRY_CODE ");   
            sb.append(" ,PORT_STATE ");
            sb.append(" ,PORT_ZONE ");
            sb.append(" ,PORT_TYPE ");            
            sb.append(" ,PORT_STATUS "); 
            sb.append(" ,AREA ");
            sb.append(" ,REGION ");
            sb.append("from VR_CAM_PORT ");
            
            StringBuffer sbWhere = new StringBuffer("");
            if (usrPermission != null && !"".equals(usrPermission)) {
                sbWhere.append(" and PCR_RUT_UTILITY.FR_CHK_USR_PERM_PORT('"+usrPermission+"', PORT_CODE) = '"+RcmConstant.FLAG_YES+"' ");
            }
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            sb.append("[order by :sortBy :sortIn] ");
        
        } else if (RcmConstant.GET_WITH_ACTIVE_STATUS.equals(type)) {
            sb.append("select distinct PORT_CODE ");
            sb.append(" ,PORT_NAME ");
            sb.append(" ,COUNTRY_CODE ");
            sb.append(" ,PORT_STATE ");
            sb.append(" ,PORT_ZONE ");
            sb.append(" ,PORT_TYPE ");
            sb.append(" ,PORT_STATUS ");
            sb.append(" ,AREA ");
            sb.append(" ,REGION ");
            sb.append(" ,TQTERM ");
            sb.append(" ,TQTRNM ");
            sb.append("from VR_CAM_PORT ");
            sb.append("where PORT_STATUS = 'A' ");
            
            if (zoneCode != null && !"".equals(zoneCode)) {
                sb.append(" and PORT_ZONE = '"+zoneCode+"' ");
            }
            if (fscCode != null && !"".equals(fscCode)) {
                sb.append(" and FSC = '"+fscCode+"' ");
            }
            
            sb.append(" [and :columnName :conditionWild :columnFind] ");
            sb.append("[order by :sortBy :sortIn] ");
            
        } else if (RcmConstant.GET_TERMINAL_GENERAL.equals(type)) {
            String line = this.getLineCodeOfUser();
            String trade = this.getRegionCodeOfUser();
            String agent = this.getAgentCodeOfUser();
            String fsc = this.getFscCodeOfUser();
            
            sb.append("select distinct PORT_CODE ");
            sb.append(" ,PORT_NAME ");
            sb.append(" ,TQTERM ");   
            sb.append(" ,TQTRNM ");
            sb.append("from VR_CAM_FSC_PORT_TERMINAL ");

            StringBuffer sbWhere = new StringBuffer("");
            if (line != null && !"".equals(line)) {
                sbWhere.append(" and LINE = '"+line+"' ");
            }
            if (trade != null && !"".equals(trade)) {
                sbWhere.append(" and TRADE = '"+trade+"' ");
            }
            if (agent != null && !"".equals(agent)) {
                sbWhere.append(" and AGENT = '"+agent+"' ");
            }
            if (fsc != null && !"".equals(fsc)) {
                sbWhere.append(" and FSC = '"+fsc+"' ");
            }
            
            if (sbWhere != null && sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4));
                sb.append(" [and :columnName :conditionWild :columnFind] ");
            } else {
                sb.append("[where :columnName :conditionWild :columnFind] ");
            }
            sb.append("[order by :sortBy :sortIn] ");
            
        } else if (RcmConstant.GET_FILTERED_RESULT.equals(type)) {
//                        sb.append("select distinct PORT_CODE ");
//                        sb.append(" ,PORT_NAME ");
//                        sb.append(" ,COUNTRY_CODE ");   
//                        sb.append(" ,PORT_STATE ");
//                        sb.append(" ,PORT_ZONE ");
//                        sb.append(" ,PORT_TYPE ");            
//                        sb.append(" ,PORT_STATUS "); 
//                        sb.append(" ,AREA ");
//                        sb.append(" ,REGION ");            
//                        sb.append("from VR_CAM_PORT ");
//                        sb.append("where PORT_STATUS = 'A' ");
//                        sb.append(" [and :columnName :conditionWild :columnFind] ");
//                        
//                        if(tsPolPort != null){
//                            sb.append(" and PORT_CODE <> '"+tsPolPort+"' ");
//                        } 
//                        
//                        if(tsPodPort != null){
//                            sb.append(" and PORT_CODE <> '"+tsPodPort+"' ");
//                        }
//                        
//                        sb.append(" [order by :sortBy :sortIn] "); 
                sb.append(" select PORT_CODE, PORT_GROUP, PORT_NAME, COUNTRY_CODE, PORT_STATE, PORT_ZONE, PORT_TYPE, PORT_STATUS, AREA, REGION from ( ");  
                 sb.append(" ( ");
                 sb.append(" select distinct PORT_CODE ");
                 sb.append(" ,null as PORT_GROUP");
                 sb.append(" ,PORT_NAME ");
                 sb.append(" ,COUNTRY_CODE ");   
                 sb.append(" ,PORT_STATE ");
                 sb.append(" ,PORT_ZONE ");
                 sb.append(" ,PORT_TYPE ");            
                 sb.append(" ,PORT_STATUS "); 
                 sb.append(" ,AREA ");
                 sb.append(" ,REGION ");            
                 sb.append(" from VR_CAM_PORT ");
                 sb.append(" where PORT_STATUS = 'A' ");
                 //sb.append(" [and :columnName :conditionWild :columnFind] ");
                 
                 if(tsPolPort != null){
                     sb.append(" and PORT_CODE <> '"+tsPolPort+"' ");
                 } 
                 
                 if(tsPodPort != null){
                     sb.append(" and PORT_CODE <> '"+tsPodPort+"' ");
                 }
                 
                 //sb.append(" [order by :sortBy :sortIn] "); 
                 sb.append(" ) ");
                 sb.append(" union ");
                 sb.append(" ( ");
                 sb.append("     select distinct ");
                 sb.append("     null as PORT_CODE ");
                 sb.append("     ,h.port_grp_cde as PORT_GROUP");
                 sb.append("     ,null as PORT_NAME ");
                 sb.append("     ,COUNTRY_CDE as COUNTRY_CODE ");   
                 sb.append("     ,null as PORT_STATE ");
                 sb.append("     ,null as PORT_ZONE ");
                 sb.append("     ,'G' as PORT_TYPE ");            
                 sb.append("     ,h.RECORD_STATUS as PORT_STATUS "); 
                 sb.append("     ,null as AREA ");
                 sb.append("     ,null as REGION ");            
                 sb.append("     from port_group_header h,port_group_details d ");
                 sb.append(" where h.port_grp_cde in ( select fk_port_group_code from bsa_supported_port_group where fk_bsa_model_id = " +modelId+ " and fk_port_group_code = h.port_grp_cde ) ");
                 //sb.append(" [and :columnName :conditionWild :columnFind] ");
                 sb.append("     and h.port_grp_cde = d.port_grp_cde(+) "); 
                 sb.append("     and record_status = 'A' ");
                 sb.append("     and exists (select 'Y' from vr_cam_port where PORT_STATUS = 'A'");
                 sb.append("     and port_code = port");
                 if(tsPolPort != null){
                     sb.append(" and PORT_CODE <> '"+tsPolPort+"' ");
                 } 
                 
                 if(tsPodPort != null){
                     sb.append(" and PORT_CODE <> '"+tsPodPort+"' ");
                 }
                 
                 
                 sb.append(" ) ) )");
                sb.append(" [where :columnName :conditionWild :columnFind] ");
                sb.append(" [order by :sortBy :sortIn] ");
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
    
    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getFscCode() {
        return fscCode;
    }

    public void setFscCode(String fscCode) {
        this.fscCode = fscCode;
    }
    
    public String getChangeScreen() {
        return changeScreen;
    }

    public void setChangeScreen(String changeScreen) {
        this.changeScreen = changeScreen;
    }
}
