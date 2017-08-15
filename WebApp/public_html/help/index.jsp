<%@ page language="java"%>
<%
String file = request.getParameter("filename"); 
if(file.equals("defaulthelp.jsp")) {
	file = "";
} 
response.sendRedirect("ApplicationHelp.htm#"+file);
%>
