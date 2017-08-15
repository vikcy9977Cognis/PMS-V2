<!--
-------------------------------------------------------------------------------------------------------------  
RcmLongDescFooter.jsp
-------------------------------------------------------------------------------------------------------------  
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------  
Author Manop Wanngam 15/10/07  
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-------------------------------------------------------------------------------------------------------------  
-->

<%@ page language="java" import="com.rclgroup.dolphin.web.common.RcmConstant"%>
<%//Begin: Footer Section%>
<%
    String imgURL = RcmConstant.IMG_URL;
    int errCode = 0;
    if (request.getParameter("errCode") != null) {
        errCode = Integer.parseInt(request.getParameter("errCode"));
    }
    String errMsg = request.getParameter("errMsg");
    String msg = request.getParameter("msg");
%>
<div id="divStayTopLeft" style="position:absolute;visibility: hidden; bottom:0;width:100%">


    <table border="0" width="100%" cellspacing="1" cellpadding="4">
    <tr>
        <td width="3%" bgcolor="#D4D0C8" >
            <p align="center">
            <img border="0" src="<%=imgURL%>/imgError.gif" width="16" height="16">
        </td>
        <td width="97%" bgcolor="#D4D0C8" valign="middle" align="left" id="messagetd" style="font-family:Verdana;font-size:8pt;">
        <p align="left">
            <%if(errCode == 0) {%>
                <font color=black>
            <%}else if (errCode == 1){%>
                <font color=red>
            <%}%>
            <%errCode = 0;%>
            <% if(errMsg.equals("") && msg.equals("")) { %>
                Ready
            <%} else if(!errMsg.equals("") && msg.equals("")){%>
                <%=errMsg%>
            <%} else if(errMsg.equals("") && !msg.equals("")){%>
                <%=msg%>
            <%} else if(!errMsg.equals("") && !msg.equals("")){%>
                <%=errMsg%>
            <%}%>
            </font>
        </td>
    </tr>
    </table>

 </div>
 
<!--
<div style="position:absolute;top:expression(document.body.clientHeight-60);">
    <div class="footerBox">
        <table border="0" width="100%" height="1" cellspacing="0"
               cellpadding="0">
            <tr>
                <td width="100%" height="1" valign="middle" align="left">
                    <hr noshade="noshade" size="1" color="#C0C0C0"></hr>
                </td>
            </tr>
            <tr>
                <td width="100%" height="1" valign="middle">
                    <p style="margin-left: 0.1in; margin-right: 0.1in"
                       align="center">
                        <font size="1" face="Verdana" color="#999999">
                            &nbsp;Copyright &copy; 2007 
                            <b>Regional Container Lines Public Company Limited.</b>
                            All rights reserved. &nbsp;Best viewed with Internet
                            Explorer 5.5 and above in 1280 X 1024
                            &nbsp;resolution and above.
                        </font>
                    </p>
                </td>
            </tr>
        </table>
    </div>
</div>
-->
<%//End: Footer Section%>

