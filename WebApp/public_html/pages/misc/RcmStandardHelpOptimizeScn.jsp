<!--
--------------------------------------------------------
RcmStandardHelpOptimizeScn.jsp
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
--------------------------------------------------------
Author Wuttitorn Wuttijiaranai 28/04/2009
- Change Log -------------------------------------------
## DD/MM/YY –User- -TaskRef- -ShortDescription-
--------------------------------------------------------
-->

<%@ include file="/pages/misc/RcmInclude.jsp"%>
<%@ page import="java.util.*,com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim,com.rclgroup.dolphin.web.model.rcm.RcmColumnNameShowMod"%>
<html>
<head>
<jsp:useBean id="rcmStandardHelpOptimizeUim" class="com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim" scope="session"/>
<title><%=rcmStandardHelpOptimizeUim.getTitleNameShow()%> Help</title>
<%
    List columnName = rcmStandardHelpOptimizeUim.getColumnName();
    HashMap columnNameShow = rcmStandardHelpOptimizeUim.getColumnNameShow();
    
    String column = null;
    RcmColumnNameShowMod rcmColumnNameShowMod = null;
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=cssURL%>/sealiner.css">
<script language="Javascript1.2" src="<%=jsURL%>/RutMessage.js"></script>
<script language="Javascript1.2" src="<%=jsURL%>/RutHelp.js"></script>
<script language="Javascript1.2">
//<!--

function callBackRetrunValue(strRetValue) {
    var strFrmName = "<%=rcmStandardHelpOptimizeUim.getFormName()%>";
    var strRetField = "<%=rcmStandardHelpOptimizeUim.getRetName()%>";
    var strRetFunction = "<%=rcmStandardHelpOptimizeUim.getRetFunc()%>";
    
    if (strFrmName != "" && strRetField != "") {
        document.frmHelp.optSelect.value = strRetValue;
        setValue(strFrmName, strRetField, strRetFunction);
    }
}

//-->    
</script>

</head>

<body onLoad="checkParent()" topmargin="0" leftmargin="0" bgcolor="white">
<form name="frmHelp" method="post" action= "<%=servURL%>/RrcStandardSrv">
<input type="hidden" name="service" value="<%=rcmStandardHelpOptimizeUim.getService()%>">
<input type="hidden" name="firstTime" value="<%=RcmConstant.FLAG_NO%>">
<input type="hidden" name="pageAction" value="search">
<input type="hidden" name="errCode" value="">
<input type="hidden" name="errMsg" value="">
<input type="hidden" name="optSelect" value="">

<table width="100%" border="0" cellspacing="0" cellpadding="0" height="400">
    <tr>
        <td height="10">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" height="5">
            <tr>
                <td class="PageTitle"><%=rcmStandardHelpOptimizeUim.getTitleNameShow()%> Lookup</td>
            </tr>
            </table>        
            <table border="0" cellpadding="0" cellspacing="1" width="100%">
            <tr>
                <td class="TableLeftMaint" width="15%">&nbsp;Find</td>
                <td class="TableLeftWhite" width="25%" height="24">
                    <input type="text" name="txtFind" size="20" maxlength="60" value="<jsp:getProperty name="rcmStandardHelpOptimizeUim" property="find"/>" class="FormTextBox"></td>
                <td class="TableLeftMaint" width="10%">&nbsp;in</td>
                <td class="TableLeftWhite" width="25%" height="24">
                    <p align="left">
                    <select size="1" name="cmbSearch" class="FormTextBox">
                        <option value="Select one ..." <%if(rcmStandardHelpOptimizeUim.getSearch().equals("")) { %>selected <%} %>>Select one ...</option>
                        <%
                            for (int i=0;i<columnName.size();i++) {
                                column = (String) columnName.get(i);
                                rcmColumnNameShowMod = (RcmColumnNameShowMod) columnNameShow.get(column);
                                
                                if (rcmColumnNameShowMod != null) {
                                    %><option value="<%=column%>" <%if(rcmStandardHelpOptimizeUim.getSearch().equals(column)) { %>selected <%} %>><%=rcmColumnNameShowMod.getColumnText()%></option><%                                    
                                }
                            }
                        %>
                    </select>
                    </p>
                </td>
                <td class="TableLeftWhite" width="25%"><input type="button" class="FormBtnClr" value="Search" name="btnSearch" onClick="search('<%=servURL%>/RrcStandardSrv?pageAction=search');">&nbsp;&nbsp;<input type="button" class="FormBtnClr"  value="Reset" name="btnReset" onClick="clickReset();"></td>
            </tr>
            <tr>
                <td class="TableLeftMaint">&nbsp;Sort by</td>
                <td class="TableLeftWhite" height="24">
                    <p align="left">
                    <select size="1" name="cmbSortBy" class="FormTextBox">
                        <option value="Select one ..." <%if(rcmStandardHelpOptimizeUim.getSortBy().equals("")) { %>selected <%} %>>Select one ...</option>
                        <%
                            for (int i=0;i<columnName.size();i++) {
                                column = (String) columnName.get(i);
                                rcmColumnNameShowMod = (RcmColumnNameShowMod) columnNameShow.get(column);
                                
                                if (rcmColumnNameShowMod != null) {
                                    %><option value="<%=column%>" <%if(rcmStandardHelpOptimizeUim.getSortBy().equals(column)) { %>selected <%} %>><%=rcmColumnNameShowMod.getColumnText()%></option><%                                    
                                }
                            }
                        %>
                    </select>
                    </p>    
                </td>
                <td class="TableLeftMaint">&nbsp;in</td>
                <td class="TableLeftWhite" height="24">
                    <p align="left">
                    <select size="1" name="cmbSortIn" class="FormTextBox">
                        <option value="<%=RcmConstant.SORT_ASCENDING%>" <%if(rcmStandardHelpOptimizeUim.getSortIn().equals(RcmConstant.SORT_ASCENDING)) { %>selected <%} %>>Ascending</option>
                        <option value="<%=RcmConstant.SORT_DESCENDING%>" <%if(rcmStandardHelpOptimizeUim.getSortIn().equals(RcmConstant.SORT_DESCENDING)) { %>selected <%} %>>Descending</option>
                    </select>
                    </p>
                </td>
                <td class="TableLeftWhite">
                    <input type="checkbox" name="chkWild" value="ON" <%if(rcmStandardHelpOptimizeUim.getWild().equals("ON")) { %>checked <%} %>>
                    <font face="Verdana" size="1">Wild Card&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
            </tr>
            </table>        
        </td>
    </tr>
    <tr>
        <td align="left" valign="top" height="300">
            <%
                RutPage rutPage = rcmStandardHelpOptimizeUim.getRutPage();
                if ((rutPage != null) && (rutPage.getNumOfPages()!=0)) {
            %>
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td width="50%" class="TableLeftText" height="20"><%=rcmStandardHelpOptimizeUim.getTitleNameShow()%> List</td>
                <td width="50%" class="TableLeftText" align="right"></td>
            </tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="1" width="100%">
            <tr>
                <td class="TableLeftSub" height="20" width="3%">Seq#</td>
                <%
                    for (int i=0;i<columnName.size();i++) {
                        column = (String) columnName.get(i);
                        rcmColumnNameShowMod = (RcmColumnNameShowMod) columnNameShow.get(column);
                        
                        if (rcmColumnNameShowMod != null) {
                            %><td class="TableLeftSub" height="20" width="<%=rcmColumnNameShowMod.getColumnSize()%>%"><%=rcmColumnNameShowMod.getColumnText()%></td><%
                        }
                    }
                %>
            </tr>
            <tbody>
                <%
                    int seqNo = 0;
                    List listResult = rutPage.getItemList(rutPage.getPageNo(), new ArrayList());
                    
                    String retrunValue = null;
                    HashMap bean = null;
                    for (int x=0;x<listResult.size();x++) {
                        bean = (HashMap) listResult.get(x);
                        
                        if (bean != null) {
                                retrunValue = RutString.changeQuoteForHtml(rcmStandardHelpOptimizeUim.makeReturnValueByElement(bean));
                            %>
                              <tr class="TableLeftWhite" id="row<%=x%>" onclick="highlightRadioTD('row<%=x%>')" ondblclick="callBackRetrunValue('<%=retrunValue%>')">
                                <td height="20" align="center">
                                    <%=String.valueOf(rcmStandardHelpOptimizeUim.displaySeqNo(x, rutPage)) %>
                                </td>
                            <%
                            for (int i=0;i<columnName.size();i++) {
                                column = (String) columnName.get(i);
                                rcmColumnNameShowMod = (RcmColumnNameShowMod) columnNameShow.get(column);
                                
                                if (rcmColumnNameShowMod != null) {
                                    %><td height="20" align="<%=rcmColumnNameShowMod.getColumnAlign()%>"><%=rcmStandardHelpOptimizeUim.displayColumnValue((String)bean.get(column), rcmColumnNameShowMod)%></td><%
                                }
                            }
                            %></tr><%
                        }
                    } //end for
                %>
            </tbody>
            </table>
            <%//Begin:Page Selection Section%>
            <%
                    request.setAttribute("rutPage", rutPage);
                    String linkPage = servURL + "/RrcStandardSrv?service=" + rcmStandardHelpOptimizeUim.getService();
            %>
            <jsp:include page="<%=pageSelectionURL%>" flush="true">
                <jsp:param name="linkPage" value="<%=linkPage%>"/>
                <jsp:param name="useForm" value="<%=RcmConstant.FLAG_YES%>" />
            </jsp:include>
            <%//End:Page Selection Section%>
            <%    
                } //end if
            %>        
        </td>
    </tr>
</table>
</form>
</body>
<%//Begin:Footer Section%>
    <jsp:include page="<%=shortFooterURL%>" flush="true">
        <jsp:param name="errCode" value="<%=errCode%>"/>
        <jsp:param name="errMsg" value="<%=errMsg%>"/>
        <jsp:param name="msg" value="<%=msg%>"/>
    </jsp:include>
<%//End: Footer Section%>
</html>

