<!--
-------------------------------------------------------------------------------------------------------------  
RcmInvoyageSearchScn.jsp
-------------------------------------------------------------------------------------------------------------  
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------  
Author Wuttitorn Wuttijiaranai 18/10/10
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-------------------------------------------------------------------------------------------------------------  
-->

<%@ include file="/pages/misc/RcmInclude.jsp"%>
<%@ page language="java" import="java.util.*,com.rclgroup.dolphin.web.util.*,com.rclgroup.dolphin.web.common.RcmConstant"%>
<%@ page language="java" import="com.rclgroup.dolphin.web.common.RcmUserBean"%>
<%
    // get session
    String sessionId = RutString.nullToStr(request.getParameter("sessionId"));
    String sessionName = RutString.nullToStr(request.getParameter("sessionName"));
    String sessionClass = RutString.nullToStr(request.getParameter("sessionClass"));
    String formName = RutString.nullToStr(request.getParameter("formName"));
    String cmbSearchBy = RutString.nullToStr(request.getParameter("cmbNameSearchBy"));
    String altInputDefault = RutString.nullToStr(request.getParameter("altInputDefault"));
    String altInputInvoyage = RutString.nullToStr(request.getParameter("altInputInvoyage"));
    
    // declare object class
    Object object = (Object) session.getAttribute(sessionName);
    Class dbClass = Class.forName(sessionClass);
    
    String userrPermission = null;
    String strReadonlyPort = "";
    String strDisablePort = "";
    try {
        // Manage the attributes of User: line, region, agent, fsc
        RcmUserBean userBeanTemp = (RcmUserBean) session.getAttribute("userBean");
        if (userBeanTemp != null) {
            String line = userBeanTemp.getFscLvl1();
            String region = userBeanTemp.getFscLvl2();
            String agent = userBeanTemp.getFscLvl3();
            String fsc = userBeanTemp.getFscCode();
            
            if (region.equals("*")) {
                userrPermission = "L:" + line;
            } else if (agent.equals("***")) {
                userrPermission = "R:" + line + "|" + region;
            } else {
                boolean isControlFsc = RutString.getValueObjectByParameterBoolean(object, dbClass, "controlFsc");
                if (isControlFsc) {
                    userrPermission = "A:" + line + "|" + region + "|" + agent;
                } else {
                    userrPermission = "F:" + fsc;
                    strReadonlyPort = "readonly";
                    strDisablePort = "disabled";
                }
            }
        }
        
    } catch(Exception e) {
        e.printStackTrace();
    }
    
    // get input name
    String namePort = RutString.nullToStr(request.getParameter("txtNamePort"));
    String nameVessel = RutString.nullToStr(request.getParameter("txtNameVessel"));
    String nameVoyage = RutString.nullToStr(request.getParameter("txtNameVoyage"));
    String nameEta = RutString.nullToStr(request.getParameter("txtNameEta"));
    
    String strCalendarExtra = "";
    if (!RutString.isEmptyString(formName) && !RutString.isEmptyString(nameEta)) {
        strCalendarExtra = "onClick=\"showCalendar('"+formName+"."+nameEta+"', '', '', '1')\"";
    }
    
    // get input value
    String strValuePort = RutString.nullToStr(request.getParameter("valuePort"));
    String strValueVessel = RutString.nullToStr(request.getParameter("valueVessel"));
    String strValueVoyage = RutString.nullToStr(request.getParameter("valueVoyage"));
    String strValueEta = RutString.nullToStr(request.getParameter("valueEta"));
    
    String valuePort = null;
    String valueVessel = null;
    String valueVoyage = null;
    String valueEta = null;
    try {
        String separate = "\\|";
        String[] strTemp = null;
        
        valuePort = RutString.getValueObjectByParameter(object, dbClass, strValuePort);
        valueVessel = RutString.getValueObjectByParameter(object, dbClass, strValueVessel);
        valueVoyage = RutString.getValueObjectByParameter(object, dbClass, strValueVoyage);
        
        // get date
        strTemp = strValueEta.split(separate);
        if (strTemp.length == 1) {
            valueEta = RutString.getValueObjectByParameter(object, dbClass, strValueEta);
        } else {
            valueEta = RutString.getValueObjectByParameter(object, dbClass, strTemp[0]);
            
            int nameIndex = strTemp[1].indexOf("Date");
            if (nameIndex != -1) {
                valueEta = RutString.nullToStr(RutDate.toDateFormatFromYYYYMMDD(valueEta));
            }
        }
    } catch(Exception e) { }
%>
<tr>
    <td class="TableLeftMaint" height="21" nowrap>Port : </td>
    <td class="TableLeftWhite" height="21" nowrap>
        <input alt="<%=altInputInvoyage%>" type="text" name="<%=namePort%>" value="<%=valuePort%>" size="10" class="FormTextBox" onchange="rcmInvoyageSearch_setInvoyagePortName()" <%=strReadonlyPort%>></input>
        <input alt="<%=altInputInvoyage%>" type="button" value=". . ." name="btnInvoyagePort" class="FormBtnClrHelp" onclick="rcmInvoyageSearch_popupInvoyagePort();" <%=strDisablePort%>></input>
    </td>
    <td class="TableLeftMaint" height="21" nowrap>Vessel : </td>
    <td class="TableLeftWhite" height="21" nowrap>
        <input alt="<%=altInputInvoyage%>" type="text" name="<%=nameVessel%>" size="10" value="<%=valueVessel%>" class="FormTextBox"></input>
        <input alt="<%=altInputInvoyage%>" type="button" value=". . ." name="btnInvoyageVessel" class="FormBtnClrHelp" onclick="rcmInvoyageSearch_popupInvoyageVesselSchedule();"></input>
    </td>
    <td class="TableLeftMaint" height="21" nowrap>In Voyage# : </td>
    <td class="TableLeftWhite" height="21" nowrap>
        <input alt="<%=altInputInvoyage%>" type="text" name="<%=nameVoyage%>" size="10" value="<%=valueVoyage%>" class="FormTextBox"></input>
        <input alt="<%=altInputInvoyage%>" type="button" value=". . ." name="btnInvoyageNo" class="FormBtnClrHelp" onclick="rcmInvoyageSearch_popupInvoyageVesselSchedule();"></input>
    </td>
    <td class="TableLeftMaint" height="21" nowrap>ETA for <span id="_InVoyage_Port"><%=valuePort%></span> : </td>
    <td class="TableLeftWhite" height="21" nowrap>
        <input alt="<%=altInputInvoyage%>" class="FormTextBox" maxlength="10" size="10" name="<%=nameEta%>" id="<%=nameEta%>" value="<%=valueEta%>" onKeyPress="dateFormat(this,this.value,event,false,1,document.all('messagetd'));" onClick="this.select();" onBlur="dateFormat(this,this.value,event,true,1,document.all('messagetd'));">
        <a href="#" <%=strCalendarExtra%>><img border="0" src="<%=imgURL%>/btnCalendar.gif" width="29" height="18" id="imgDateFrom"></a>
    </td>
    <td class="TableLeftMaint" height="21" nowrap>
        <input alt="<%=altInputInvoyage%>" type="button" value="InVoyage Browser" name="btnInvoyage" class="FormBtnClr" onClick="rcmInvoyageSearch_popupInvoyageBrowser()"></input>
    </td>
</tr>

<script language="javascript">
//<!--

function rcmInvoyageSearch_popupInvoyagePort() {
    var intWidth = 560;
    var intHeight = 430;
    var strParam = "&usrPerm=<%=userrPermission%>";
    var strRetName = "<%=namePort%>";
    var strRetFunction = "rcmInvoyageSearch_setInvoyagePortName()";
    
    var strUrl = "<%=servURL%>/RrcStandardSrv?service=ui.misc.help.RcmPortHelpSvc&type=<%=RrcStandardUim.GET_GENERAL%>&pageAction=new&formName=<%=formName%>&retName="+strRetName+strParam+"&retFunc="+strRetFunction;
    openHelpList(strUrl,intWidth,intHeight);
}

function rcmInvoyageSearch_setInvoyagePortName() {
    try {
        $("#_InVoyage_Port").text(trim(getObject("<%=formName%>", "<%=namePort%>").value).toUpperCase());
    } catch(er) { }
}

function rcmInvoyageSearch_chkSearchBasic() {
    var strErrMsg = "";
    var strInvoyagePort = trim(getObject("<%=formName%>", "<%=namePort%>").value);
    
    <% if (!RutString.isEmptyString(cmbSearchBy)) { %>
        var strSearchBy = selectValue(getObject("<%=formName%>", "<%=cmbSearchBy%>"));
        if (strErrMsg == "" && strSearchBy == "") {
            strErrMsg = "<%=new RutMessage().getErrorMessage("RCM.INVOYAGE_BROWSER.FIND.EXCEPTION_SEARCH_BY_REQUIRED")%>";
        }
    <% } %>
    if (strErrMsg == "" && strInvoyagePort == "") {
        strErrMsg = "<%=new RutMessage().getErrorMessage("RCM.INVOYAGE_BROWSER.FIND.EXCEPTION_PORT_REQUIRED")%>";
    }
    
    return strErrMsg;
}

function rcmInvoyageSearch_popupInvoyageVesselSchedule() {
    var strErrMsg = rcmInvoyageSearch_chkSearchBasic();
    var intErrCode = 1;
    
    if (strErrMsg == "") {
        var intWidth = 560;
        var intHeight = 430;
        var strRetName = "|<%=nameVessel%>|||<%=nameVoyage%>|<%=nameEta%>";
        
        var strUrl = "<%=servURL%>/RrcStandardSrv?service=ui.misc.help.RcmVesselScheduleHelpSvc&type=<%=RrcStandardUim.GET_SRV_VSS_VOY_INVOY%>&pageAction=new&formName=<%=formName%>&retName="+strRetName;
        openHelpList(strUrl,intWidth,intHeight);
    } else {
        showBarMessage('messagetd', strErrMsg, intErrCode);
    }
}

function rcmInvoyageSearch_popupInvoyageBrowser() {
    var strErrMsg = rcmInvoyageSearch_chkSearchBasic();
    var intErrCode = 1;
    var strInvoyagePort = trim(getObject("<%=formName%>", "<%=namePort%>").value);
    var strInvoyageVessel = trim(getObject("<%=formName%>", "<%=nameVessel%>").value);
    var strInvoyageVoyno = trim(getObject("<%=formName%>", "<%=nameVoyage%>").value);
    var strInvoyageEtaDate = trim(getObject("<%=formName%>", "<%=nameEta%>").value);
    
    if (strErrMsg == "" && strInvoyageVoyno == "") {
        strErrMsg = "<%=new RutMessage().getErrorMessage("RCM.INVOYAGE_BROWSER.FIND.EXCEPTION_INVOYAGE_NO_REQUIRED")%>";
    }    
    
    if (strErrMsg == "") {
        var intWidth = 700;
        var intHeight = 430;
        var strParam = "&usrPerm=<%=userrPermission%>";
        var strSessionId = "<%=sessionId%>";
        
        if (strInvoyagePort != "") {
            strParam += "&invoyagePort="+strInvoyagePort;
        }
        if (strInvoyageVessel != "") {
            strParam += "&invoyageVessel="+strInvoyageVessel;
        }
        if (strInvoyageVoyno != "") {
            strParam += "&invoyageVoyage="+strInvoyageVoyno;
        }
        if (strInvoyageEtaDate != "") {
            strParam += "&invoyageEta="+strInvoyageEtaDate;
        }
        if (strSessionId != "") {
            strParam += "&sessionId="+strSessionId;
        }
        
        var strUrl = "<%=servURL%>/RrcStandardSrv?service=ui.misc.help.RcmInvoyageBrowserHelpSvc&type=<%=RrcStandardUim.GET_GENERAL%>&pageAction=new&formName=<%=formName%>"+strParam;
        openHelpList(strUrl,intWidth,intHeight);
    } else {
        showBarMessage('messagetd', strErrMsg, intErrCode);
    }
}

//-->
</script>
