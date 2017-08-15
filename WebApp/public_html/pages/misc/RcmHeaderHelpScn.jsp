<!--
-------------------------------------------------------------------------------------------------------------  
RcmHeaderHelpScn.jsp
-------------------------------------------------------------------------------------------------------------  
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------  
Author Manop Wanngam 22/10/07  
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-------------------------------------------------------------------------------------------------------------  
-->

<%@ page language="java" import="com.rclgroup.dolphin.web.common.RcmConstant"%>
<%    
    String helpURL= RcmConstant.HELP_URL;
    String fileName = request.getParameter("fileName");
    String helpId = request.getParameter("helpId");

    if ((fileName!=null) && !fileName.equals("")) {
        response.sendRedirect(helpURL+"/"+fileName);
    } else {
        out.println("Help File for this screen has not been specified yet<br>");
        if (helpId != null) {
            //out.println("HELP ID= <B>"+helpId+"</b>" );
        } else {
            //out.println("HELP ID= <B>Help ID not specified</b>" );
        }
    }
%>
