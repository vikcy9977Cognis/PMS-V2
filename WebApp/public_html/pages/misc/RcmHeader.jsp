<!--
-------------------------------------------------------------------------------------------------------------  
RcmHeader.jsp
-------------------------------------------------------------------------------------------------------------  
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------  
Author Manop Wanngam 22/10/07 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 11/04/08 PIE                       Add user description
02 08/05/08 PIE                       Implement user help 
-------------------------------------------------------------------------------------------------------------  
-->

<%@ page language="java" import="com.rclgroup.dolphin.web.util.RutString, com.rclgroup.dolphin.web.common.RcmConstant, com.rclgroup.dolphin.web.common.RcmUserBean"%>
<%//Begin: Header Section%>
<% 
    String title = request.getParameter("title");
    String hasBack = request.getParameter("hasBack");
    String hasRefresh = request.getParameter("hasRefresh");
    String hasSave = request.getParameter("hasSave");
      
    RcmUserBean userBean = (RcmUserBean)session.getAttribute("userBean");
    String userDesc = RutString.nullToStr(userBean.getDescr());
    String loginId = RutString.nullToStr(userBean.getPrsnLogId());
    String fsc = userBean.getFscCode();
    String line = userBean.getFscLvl1();
    String trade = userBean.getFscLvl2();
    String agent = userBean.getFscLvl3();
    String date = userBean.getFscDateFormat();
    
    String imgURL = RcmConstant.IMG_URL;    
    String helpURL = RcmConstant.HELP_URL;
%>
<script>
var objXmlDoc = new ActiveXObject("Microsoft.XMLDOM");

function verify(){
    if (objXmlDoc.readyState != 4){
        return false;
    }
}

var strFileName = "";

function getFileNameFromNameXml(){
    objXmlDoc.async="false";
    objXmlDoc.onreadystatechange = verify;
    objXmlDoc.load("<%=helpURL%>/name.xml");
    objXmlDocElement=objXmlDoc.documentElement;
    var strTitle="<%=title%>";
    for(var i=0;i<objXmlDocElement.childNodes.length;i++){
        if(trim(objXmlDocElement.childNodes(i).getAttribute('title'))==trim(strTitle)){
            strFileName = objXmlDocElement.childNodes(i).text;
            break;
        }
    }
}

function openHelp() {
    getFileNameFromNameXml();
    var strUrl = "<%=helpURL%>/ApplicationHelp.htm#"+strFileName;
    var objWindow = window.open(strUrl,"Help","width=900,height=600,status=no,resizable=no,top=150,left=150");
    objWindow.focus();
}
</script>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
<tr>
    <td width="50%" class="PageTitle"><%=title%></td>
    <td width="50%" class="PageTitleRight" style="padding-right: 23px;">
        <table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
            <td width="15%" valign="middle" align="right"><font size="1" face="Verdana" color="#FFFFFF"></font></td>
            <td width="70%" valign="middle" align="right"><font size="1" face="Verdana" color="#FFFFFF"><%=RutString.toUpperCase(userDesc+" ("+loginId+")")%>-&nbsp;<%=fsc%>&nbsp;-&nbsp;<%=line%>/<%=trade%>/<%=agent%>&nbsp;<%=date%></font></td>
            <td valign="middle" align="left"><img border="0" src="<%=imgURL%>/imgDivider.gif" height="20"></td>
            <%if ((hasBack != null)&&(hasBack.equalsIgnoreCase("Y"))) {%>		
                <td valign="middle" align="right">
                    <a href="javascript:back();"><img border="0" alt="Back" src="<%=imgURL%>/btnBack.jpg"></a>
                </td>
                <td valign="middle" align="center"><img border="0" src="<%=imgURL%>/imgDivider.gif" height="20"></td>
            <%}%>
            <%if ((hasRefresh != null)&&(hasRefresh.equalsIgnoreCase("Y"))) {%>
                <td valign="middle" align="right">
                    <a href="javascript:refresh();"><img border="0" alt="Refresh" src="<%=imgURL%>/btnRefresh.jpg"></a>
                </td>
                <td valign="middle" align="center"><img border="0" src="<%=imgURL%>/imgDivider.gif" height="20"></td>
            <%}%>
            <%if ((hasSave != null)&&(hasSave.equalsIgnoreCase("Y"))) {%>
                <td valign="middle" align="right">
                    <a href="javascript:save();"><img border="0" alt="Save" src="<%=imgURL%>/btnSave.jpg"></a>
                </td>
                <td valign="middle" align="center"><img border="0" src="<%=imgURL%>/imgDivider.gif" height="20"></td>
            <%}%>
            <td width="50" valign="middle" align="center"><a href="javascript:openHelp()"><img border="0" alt="Help" src="<%=imgURL%>/btnHelp.jpg" width="40" height="16"></a></td>
            <td width="6" valign="middle" align="center"><img border="0" src="<%=imgURL%>/imgDivider.gif" width="12" height="20"></td>						
            <td width="2%"><a href="javascript:window.close()"><img border="0" src="<%=imgURL%>/btnClose.gif" alt="Close" width="16" height="16"></a></td>       
        </tr>
        </table>                                
    </td>
</tr>
</table>
<div style="float:right;margin-right:10px; margin-top:2px; margin-bottom:2px;text-align:right;">
     <span style="vertical-align:super; font-size:10px;"> <a style="FONT-WEIGHT:bold;"> App ver.</a>PMS_WEB_APP_BUILD_01_11_20170630&nbsp;&nbsp;<a>V&nbsp;01.11</a>  </span>
</div>
<%//End: Header Section%>
