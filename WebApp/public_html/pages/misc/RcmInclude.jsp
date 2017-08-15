<!--
-------------------------------------------------------------------------------------------------------------  
RcmInclude.jsp
-------------------------------------------------------------------------------------------------------------  
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------  
Author Piyapong Ieumwananonthachai 15/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-------------------------------------------------------------------------------------------------------------  
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" session="true" autoFlush ="true" errorPage="/pages/error/RcmErrorPage.jsp"%>
<%@page import = "com.rclgroup.dolphin.web.common.*"%>
<%@page import = "com.rclgroup.dolphin.web.util.*"%>
<%@page import = "com.rclgroup.dolphin.web.model.rcm.*"%>
<%
//Get URLs for this jsp file
final String sealinerPageURL = RcmConstant.SEALINER_PAGE_URL;
final String servURL= RcmConstant.SERV_URL;
final String cssURL = RcmConstant.CSS_URL;
final String jsURL = RcmConstant.JS_URL;
final String imgURL = RcmConstant.IMG_URL;
final String helpURL = RcmConstant.HELP_PAGE_URL;
final String headerURL = RcmConstant.HEADER_FILE_URL;
final String longFooterURL = RcmConstant.LONG_FOOTER_FILE_URL;
final String shortFooterURL = RcmConstant.SHORT_FOOTER_FILE_URL;
final String pageSelectionURL = RcmConstant.PAGE_SELECTION_FILE_URL;
final String searchByURL = RcmConstant.SEARCH_BY_FILE_URL;
final String invoyageSearchURL = RcmConstant.INVOYAGE_SEARCH_FILE_URL;

int errCode = 0;
String errMsg = ""; 
String msg = "";

response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", 0); // prevent caching at the proxy server

//Begin: Check whether session is null or not, and whether userBean is null or not
if(session == null){ // Check if the session == null or expired, user should relogin.
    System.out.println("[RcmInclude.jsp]: A session is null.");
    response.sendRedirect(sealinerPageURL+"/callexp.jsp");
}else if(session.getAttribute("userBean") == null){
    System.out.println("[RcmInclude.jsp]: A userBean in session is null.");
    response.sendRedirect(sealinerPageURL+"/callexp.jsp");
}
//End: Check whether session is null or not, and whether custbean is null or not
//Begin: get userBean
RcmUserBean userBean = (RcmUserBean)session.getAttribute("userBean");
//End: get userBean

if(session.getAttribute("errMsg") != null){
    errMsg = ((String)session.getAttribute("errMsg")).trim();
}
session.removeAttribute("errMsg");
if(session.getAttribute("msg") != null){
    msg = ((String)session.getAttribute("msg")).trim();
}
session.removeAttribute("msg");	
if(errMsg.equals("")){
    errCode = 0;
}else{
    errCode = 1;
}
%>
<!-- <script language="Javascript1.2" src="<%=jsURL%>/jquery.js"></script> -->
