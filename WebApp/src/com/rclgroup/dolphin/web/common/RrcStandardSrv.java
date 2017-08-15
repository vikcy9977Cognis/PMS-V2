/*-----------------------------------------------------------------------------------------------------------  
RrcStandardSrv.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 22/11/10 Wut                       Add feature for upload file to Server
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.util.RutRequest;
import com.rclgroup.dolphin.web.util.RutString;

import java.io.UnsupportedEncodingException;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *    A main RCL standard servlet
 */
public class RrcStandardSrv extends RrcGenericSrv {
    
    private HashMap hParameter = null;
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("[RrcStandardSrv][service]: Started");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(false);
        try {
            //Begin: Check whether session is null or not, and whether userBean is null or not
            if (session == null) { // Check if the session == null or expired, user should relogin.
                System.out.println("[RrcStandardSrv]: A session is null.");
                response.sendRedirect(RcmConstant.SEALINER_PAGE_URL+"/callexp.jsp");
                return;
            } else if (session.getAttribute("userBean") == null) {
                System.out.println("[RrcStandardSrv]: A userBean in session is null.");
                response.sendRedirect(RcmConstant.SEALINER_PAGE_URL+"/callexp.jsp");
                return;
            }
            //End: Check whether session is null or not, and whether userBean is null or not
            
            //Begin: Upload file by multipart
            this.hParameter = null;
            request.removeAttribute(RcmConstant.PARAMETER_NAME_FORM_MULTIPART);
            if (request != null && ServletFileUpload.isMultipartContent(request)) {
                this.hParameter = RutRequest.loadParameterMultipart(request);
                request.setAttribute(RcmConstant.PARAMETER_NAME_FORM_MULTIPART, this.hParameter);
                
                String useMultipart = (String) this.getParameterValueByCode(request, "useMultipart");
                useMultipart = (RutString.isEmptyString(useMultipart) || !RcmConstant.FLAG_YES.equals(useMultipart)) ? RcmConstant.FLAG_NO : RcmConstant.FLAG_YES;
                
                // clear request attribute
                if (RcmConstant.FLAG_NO.equals(useMultipart)) {
                    this.hParameter = null;
                    request.removeAttribute(RcmConstant.PARAMETER_NAME_FORM_MULTIPART);
                }
            }
            //End: Upload file by multipart
            
            String serviceName = (String) this.getParameterValueByCode(request, "service");
            System.out.println("[RrcStandardSrv][service]: service: " + serviceName);
            Class cls = Class.forName("com.rclgroup.dolphin.web." + serviceName);
            RrcStandardSvc rrcStandardSvc = (RrcStandardSvc) cls.newInstance();
            rrcStandardSvc.setServletContext(sc);
            RriStandardSvc service = rrcStandardSvc;
            service.execute(request, response, sc);
            
            String target = (String) request.getAttribute("target");           
            System.out.println("[RrcStandardSrv][service]: Finished");
            if (target != null) {
                redirect(response, target);                        
            }
            
        } catch (Exception ex) {
            session.setAttribute("exception", ex);
            System.err.println("[RrcStandardSrv][service]: Exception thrown");
            ex.printStackTrace();
            redirect(response, RcmConstant.ERR_PAGE_URL + "/RcmErrorPage.jsp");
        }
    }
    
    public Object getParameterValueByCode(HttpServletRequest request, String parameterCode) {
        return RutRequest.getParameterValueByCode(request, this.hParameter, parameterCode);
    }

    public HashMap getHParameter() {
        return hParameter;
    }

    public void setHParameter(HashMap hParameter) {
        this.hParameter = hParameter;
    }
}

