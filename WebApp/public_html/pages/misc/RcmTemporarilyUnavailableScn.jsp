<!--
--------------------------------------------------------
RcmTemporarilyUnavailableScn.jsp
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2009
--------------------------------------------------------
Author Nipun Sutes 12/10/2012
- Change Log--------------------------------------------
## DD/MM/YY -User- -TaskRef- -ShortDescription
--------------------------------------------------------
-->
    
<%@ include file="/pages/misc/RcmInclude.jsp"%>
<%@ page import="com.rclgroup.dolphin.web.common.RcmConstant"%>
<html>
<head>
<title>Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=cssURL%>/sealiner.css">
<script language="Javascript1.2" src="<%=jsURL%>/RutString.js"></script>
<script language="JavaScript1.2" src="<%=jsURL%>/RutDate.js"></script>
<script language="Javascript1.2" src="<%=jsURL%>/RutMessage.js"></script>
<script language="Javascript1.2" src="<%=jsURL%>/RutHelp.js"></script>
<script language="javascript">

</script>

</head>
<body class="BODY" onUnload="javascript:parent.closeChildWindow();">
<%//Begin: Header Section%>
<jsp:include page="<%=headerURL%>" flush="true">
    <jsp:param name="title" value="Page"/>
</jsp:include>
<%//End: Header Section%>

<table border="0" width="100%">
    <tr><td>
        <font size=2>This page is temporarily unavailable.</font>
    </td></tr>
</table>
<%//Begin:Footer Section%>
<jsp:include page="<%=longFooterURL%>" flush="true">
    <jsp:param name="errCode" value="<%=errCode%>"/>
    <jsp:param name="errMsg" value="<%=errMsg%>"/>
    <jsp:param name="msg" value="<%=msg%>"/>
</jsp:include>
<%//End: Footer Section%>

</body>
</html>
