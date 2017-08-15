<!--
--------------------------------------------------------
InvoiceHistoricalStatusReportScn.jsp
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
--------------------------------------------------------
Author Kitti Pongsirisakun
- Change Log--------------------------------------------
## DD/MM/YY -User- -TaskRef- -ShortDescription
01 27/04/2010   KIT    Created
--------------------------------------------------------
-->
    
<%@ include file="/pages/misc/RcmInclude.jsp"%>
<%@ page import="com.rclgroup.dolphin.web.ui.far.FarInvoiceChargeCodeMonitoringUim"%>
<html>
<head>
<jsp:useBean id="FarInvoiceChargeCodeMonitoringUim" class="com.rclgroup.dolphin.web.ui.far.FarInvoiceChargeCodeMonitoringUim" scope="session"/>
<title>Invoice Charge Code Monitoring Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=cssURL%>/sealiner.css">
<script language="JavaScript1.2" src="<%=jsURL%>/RutDate.js"></script>
<script language="Javascript1.2" src="<%=jsURL%>/RutMessage.js"></script>
<script language="Javascript1.2" src="<%=jsURL%>/RutHelp.js"></script>
<script language="JavaScript1.2" src="<%=jsURL%>/RutDisable.js"></script>

<script language="javascript">
function popupFsc() {
    var intWidth = 600;
    var intHeight = 430;
    var strParam = "&usrPerm=<%=FarInvoiceChargeCodeMonitoringUim.getPermissionUser()%>";
    var strRetName = "||txtFsc";
    
    var strUrl = "<%=servURL%>/RrcStandardSrv?service=ui.misc.help.RcmFscHelpSvc&type=<%=RrcStandardUim.GET_WITH_USER_LEVEL_ACTIVE_STATUS%>&pageAction=new&formName=frmFar&retName="+strRetName+strParam;
    openHelpList(strUrl,intWidth,intHeight);
}

function popupChargeCode() {

    var invoiceType = document.frmFar.invoiceType;
    var invoiceTypeSelected = "";
    var strParam;
    
    invoiceTypeSelected = "";
    for(var i = 0; i < invoiceType.length; i++) {
        if(invoiceType[i].checked) {
            invoiceTypeSelected = invoiceType[i].value;
        }
    }
    
    strParam ="&invoiceType=" + invoiceTypeSelected;

    openHelpList('<%=servURL%>/RrcStandardSrv?service=ui.misc.help.RcmChargeCodeHelpSvc&type=<%=RrcStandardUim.GET_GENERAL%>&pageAction=new&formName=frmFar&retName=txtChargeCode||' + strParam);
}

function clearChargeCode() {

    document.frmFar.txtChargeCode.value = "";
}

function go(strReportFormat){

    var strErrMsg = "";
    var intErrCode = 1;     
    var dateDiff;

    var strFSC = document.frmFar.txtFsc.value ;
    var strFrom = document.frmFar.txtInvoiceCreatedFrom.value ;  
    var strTo = document.frmFar.txtInvoiceCreatedTo.value ;  
    var strChargeCode = document.frmFar.txtChargeCode.value ;
    
    
    //Check Errocode must be input
    if((strFSC==null || strFSC=='')) {
        strErrMsg = "Issue FSC can not be blank.";
    }else if ((strFrom == null || strFrom == '')){       
        strErrMsg = "Invoice Created From can not be blank.";
    }else if ((strTo == null || strTo == '')){       
        strErrMsg = "Invoice Created To can not be blank.";
    }else if ((strChargeCode == null || strChargeCode == '')) {
       strErrMsg = "Charge Code cannot be blank.";
    }
    
    if(strErrMsg != ""){
        showBarMessage('messagetd',strErrMsg,intErrCode)
        return false;
    }
    
    // when all input filled, vaildate date diff
    var arrFrom = strFrom.split('/');
    var arrTo = strTo.split('/'); 
    var strFrom = new Date(arrFrom[1] + '/' + arrFrom[0] + '/' + arrFrom[2]); 
    var strTo = new Date(arrTo[1] + '/' + arrTo[0] + '/' + arrTo[2]); 

    dateDiff = (parseInt(strTo - strFrom) / (24*3600*1000)) + 1;
    
    if (dateDiff > 31) {
        strErrMsg = "Invoice Created Duration is limited for 31 days.";
    }
    
     if (dateDiff < 0) {
        strErrMsg = "Invoice Created To must be greater than Invoice Created From.";
    } 
        
    if(strErrMsg == ""){
        document.frmFar.pageAction.value = "generate";
        document.frmFar.reportFormat.value = strReportFormat;
        document.frmFar.submit();   
    }else{
        showBarMessage('messagetd',strErrMsg,intErrCode);
        return false;
    }
}

function resetScreen(){
    document.frmFar.pageAction.value = "new";
    document.frmFar.submit();
}

</script>

</head>

<body class="BODY" onUnload="javascript:parent.closeChildWindow();">

<%//Begin: Header Section%>
<jsp:include page="<%=headerURL%>" flush="true">
    <jsp:param name="title" value="Invoice Charge Code Monitoring Report"/>
</jsp:include>
<%//End: Header Section%>

<form name="frmFar" method="POST" action= "<%=servURL%>/RrcStandardSrv">

<input type="hidden" name="service" value="ui.far.FarInvoiceChargeCodeMonitoringSvc">
<input type="hidden" name="pageAction" value="">
<input type="hidden" name="reportFormat" value="">
<input type="hidden" name="errCode" value="">
<input type="hidden" name="errMsg" value="">

<table border="0" width="100%" cellspacing="0" cellpadding="0" >
    <tr>
        <td width="100%" valign="middle" align="left" class="TableLeftText">Invoice Charge Code Monitoring Report</td>
    </tr>
</table>

<table border="0" width="100%">
    <tr>
        <td width="5%" class="TableLeftMaint" height="21" nowrap>Issue FSC : </td>
        <td width="8%" class="TableLeftWhite" height="21" nowrap>
                <input type="text" name="txtFsc" maxlength="8" size="20" value="<jsp:getProperty name="FarInvoiceChargeCodeMonitoringUim" property="fsc"/>" class="FormTextBoxReq"></input>
                <input type="button" value=". . ." name="btnFsc" class="FormBtnClrHelp"  onclick="popupFsc();"></input>
        </td>
        
        <td width="5%" class="TableLeftMaint" height="21" nowrap>Invoice Created From :</td>
        <td width="8%" class="TableLeftWhite" height="21" nowrap>
            <input class="<%=FarInvoiceChargeCodeMonitoringUim.getStyleSheetDte()%>"
                   maxlength="10" size="15" name="txtInvoiceCreatedFrom"
                   value='<jsp:getProperty name="FarInvoiceChargeCodeMonitoringUim" property="createdFrm"/>'
                   onkeypress="dateFormat(this,this.value,event,false,1,document.all('messagetd'));"
                   onclick="this.select();"
                   onblur="dateFormat(this,this.value,event,true,1,document.all('messagetd'));"/>
            <a href="#" onClick="showCalendar('frmFar.txtInvoiceCreatedFrom', '', '', '1')"><img border="0" src="<%=imgURL%>/btnCalendar.gif" width="29" height="18"/></a>
        </td>
        
        <td width="5%" class="TableLeftMaint" height="21" nowrap>To : </td>
        <td width="8%" class="TableLeftWhite" height="21" nowrap>
            <input class="<%=FarInvoiceChargeCodeMonitoringUim.getStyleSheetDte()%>"
                   maxlength="10" size="15" name="txtInvoiceCreatedTo"
                   value='<jsp:getProperty name="FarInvoiceChargeCodeMonitoringUim" property="createdTo"/>'
                   onkeypress="dateFormat(this,this.value,event,false,1,document.all('messagetd'));"
                   onclick="this.select();"
                   onblur="dateFormat(this,this.value,event,true,1,document.all('messagetd'));"/>
             <a href="#" onClick="showCalendar('frmFar.txtInvoiceCreatedTo', '', '', '1')"><img border="0" src="<%=imgURL%>/btnCalendar.gif"  width="29" height="18"/></a>
        </td> 
        <td width="5%" class="TableLeftMaint" height="21">&nbsp;</td>
        <td width="5%" class="TableLeftWhite" height="21">&nbsp;</td>
    </tr>               

    <tr>
        <td width="100%" valign="middle" align="left" class="TableLeftText" colspan="8">Charge Code</td>
    </tr>
    
    <tr>
    
        <td width="5%" class="TableLeftMaint" height="21" nowrap>Invoice Type: </td>
        <td width="8%" class="TableLeftWhite" height="21" nowrap>
            <input type="radio" name ="invoiceType" value = "M" onclick="clearChargeCode();"            
            <%if (FarInvoiceChargeCodeMonitoringUim.getInvoiceBy().equals("M")){out.print("checked");}%>>Miscellaneous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="radio" name ="invoiceType" value = "D" onclick="clearChargeCode();"            
            <%if (FarInvoiceChargeCodeMonitoringUim.getInvoiceBy().equals("D")){out.print("checked");}%>>Document&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </td>
        
        <td width="5%" class="TableLeftMaint" height="21" nowrap>Charge Code : </td>
        <td width="8%" class="TableLeftWhite" height="21" nowrap>
                <input type="text" name="txtChargeCode" maxlength="8" size="20" value="<jsp:getProperty name="FarInvoiceChargeCodeMonitoringUim" property="chargeCode"/>" class="FormTextBoxReq"></input>
                <input type="button" value=". . ." name="btnChargeCode" class="FormBtnClrHelp"  onclick="popupChargeCode();"></input>
        </td>
    </tr>
</table>

<table border="0" width="100%" cellspacing="0" cellpadding="0">
<tr align="right">
    <td width="100%" class="TableLeftSubBtn" >
        <input type="button" value=" Generate PDF " name="btnPdf" class="FormBtnClr" onClick="go('pdf');"></input>
       <input type="button" value=" Generate Excel " name="btnExcel" class="FormBtnClr" onClick="go('SPREADSHEET');"></input>
        <input type="button" value="   Reset   " name="btnReset" class="FormBtnClr" onClick="resetScreen();"></input>
    </td>
</tr>
</table>


<iframe src="<jsp:getProperty name="FarInvoiceChargeCodeMonitoringUim" property="reportUrl"/>" width="100%" height="81.7%" frameborder="0" name="fraReport"></iframe>
</form>
<%//Begin:Footer Section%>
<jsp:include page="<%=longFooterURL%>" flush="true">
    <jsp:param name="errCode" value="<%=errCode%>"/>
    <jsp:param name="errMsg" value="<%=errMsg%>"/>
    <jsp:param name="msg" value="<%=msg%>"/>
</jsp:include>
<%//End: Footer Section%>
</body>
</html>
