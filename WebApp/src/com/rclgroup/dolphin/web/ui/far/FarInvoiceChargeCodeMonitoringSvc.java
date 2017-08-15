package com.rclgroup.dolphin.web.ui.far;

/*--------------------------------------------------------
FarInvoiceChargeCodeMonitoringSvc.java
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2009
--------------------------------------------------------
Author Nuttapol Thanasrisatit
- Change Log--------------------------------------------
## DD/MM/YY    -User-       -TaskRef- -ShortDescription
01 17/01/2013  NUTTHA1      Created
--------------------------------------------------------
*/

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;

import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmUserDao;
import com.rclgroup.dolphin.web.util.RutString;

import java.util.Collections;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class FarInvoiceChargeCodeMonitoringSvc extends RrcStandardSvc {
    

    public FarInvoiceChargeCodeMonitoringSvc() {
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
    
        System.out.println("[FarInvoiceChargeCodeMonitoringSvc][execute]: Started");
        String target = RcmConstant.ERR_PAGE_URL+ "/RcmErrorPage.jsp";

        String reportName = "FAR122";
        
        StringBuffer errorMsgBuffer = new StringBuffer();
        String pageAction = RutString.nullToStr(request.getParameter("pageAction"));
        
        HttpSession session = request.getSession(false);
        FarInvoiceChargeCodeMonitoringUim uim = (FarInvoiceChargeCodeMonitoringUim)session.getAttribute("FarInvoiceChargeCodeMonitoringUim");
        System.out.println("[FarInvoiceChargeCodeMonitoringSvc][execute]");
        
        if(pageAction.equalsIgnoreCase("new")){
        
            session.removeAttribute("FarInvoiceChargeCodeMonitoringUim");
            uim = new FarInvoiceChargeCodeMonitoringUim();    
            
            uim.setRcmConstantDao((RcmConstantDao)getBean("rcmConstantDao"));
            uim.setRcmUserDao((RcmUserDao)getBean("rcmUserDao"));
            uim.setCamFscDao((CamFscDao)getBean("camFscDao"));
            
            RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
            this.manageUserBean(uim,userBean);
            uim.setUserCode(userBean.getPrsnLogId());
            uim.setFsc(userBean.getFscCode());
            
            String permissionUser = this.getPermissionUserCode(uim);
            System.out.println("[FarInvoiceChargeCodeMonitoringSvc][execute]: permissionUser = " + permissionUser);  
            uim.setPermissionUser(permissionUser);
        
            session.setAttribute("FarInvoiceChargeCodeMonitoringUim",uim);
            
            target = RcmConstant.FAR_PAGE_URL+"/FarInvoiceChargeCodeMonitoringReportScn.jsp";   
            
        } else if (pageAction.equalsIgnoreCase("generate")) {
            
            session.setAttribute("FarInvoiceChargeCodeMonitoringUim",uim);            
            
            String issueFSC = RutString.getParameterToStringUpper(request,"txtFsc");
            String invoiceCreatedFrom = RutString.getParameterToStringUpper(request,"txtInvoiceCreatedFrom");
            String invoiceCreatedTo = RutString.getParameterToStringUpper(request,"txtInvoiceCreatedTo");
            String chargeCode = RutString.getParameterToStringUpper(request,"txtChargeCode");              
            String invoiceBy = RutString.getParameterToStringUpper(request,"invoiceType"); 
            String reportFormat = RutString.getParameterToString(request, "reportFormat", RcmConstant.REPORT_FORMAT_PDF, RutString.CASE_TYPE_UPPER);
            
            uim.setFsc(issueFSC);
            uim.setCreatedFrm(invoiceCreatedFrom);
            uim.setCreatedTo(invoiceCreatedTo);
            uim.setInvoiceBy(invoiceBy);
            uim.setChargeCode(chargeCode);
            uim.setReportFormat(reportFormat);
        
            if(!uim.getFsc().equals("") && !uim.isFscOk()){
                errorMsgBuffer.append("* Invalid FSC Code"); 
            } else if (!uim.getChargeCode().equals("") && !uim.isChargeCodeOk()){
                errorMsgBuffer.append("* Invalid Charge Code"); 
            }
            
            if(!errorMsgBuffer.toString().equals("")){
                uim.setReportUrl(RcmConstant.MISC_PAGE_URL+"/RcmBlankPage.jsp");
                session.setAttribute("errMsg",errorMsgBuffer.toString());
            } else {
            
                if(RcmConstant.REPORT_FORMAT_EXCEL.equals(uim.getReportFormat())){
                        reportName = reportName+"_EXCEL";
                }
    
                String reportUrl = uim.getReportUrlConstant();
                
                reportUrl += reportName+".rdf&desformat="+uim.getReportFormat();
                
                reportUrl  +=  "&P_U="+ uim.getUserCode()
                                         + "&P_CHARGE_CODE="+ chargeCode
                                         + "&P_CHARGE_CODE_TYPE="+ invoiceBy
                                         + "&P_FSC="+ issueFSC 
                                         + "&P_INV_CREATED_FROM="+ invoiceCreatedFrom
                                         + "&P_INV_CREATED_TO="+ invoiceCreatedTo;
                                         
                reportUrl += "&P_START_DATE="+ getReportStartTime();
            
                uim.setReportUrl(reportUrl);               
            }
            
            session.setAttribute("FarInvoiceChargeCodeMonitoringUim",uim);
            target = RcmConstant.FAR_PAGE_URL+"/FarInvoiceChargeCodeMonitoringReportScn.jsp";  
            
        } else {
            
            target = RcmConstant.ERR_PAGE_URL + "/RcmErrorPage.jsp"; 
        }
        
        // set attribure in session
        if(uim != null){
            session.setAttribute("FarInvoiceChargeCodeMonitoringUim",uim);
        }
        request.setAttribute("target",target);
        System.out.println("[FarInvoiceChargeCodeMonitoringUim][execute]: Finished ");
    }    
   
}
