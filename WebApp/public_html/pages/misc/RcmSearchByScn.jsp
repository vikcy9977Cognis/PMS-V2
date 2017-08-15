<!--
-------------------------------------------------------------------------------------------------------------  
RcmSearchByScn.jsp
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
<%
    // get session
    String formName = RutString.nullToStr(request.getParameter("formName"));
    String altInputDefault = RutString.nullToStr(request.getParameter("altInputDefault"));
    String altInputInvoyage = RutString.nullToStr(request.getParameter("altInputInvoyage"));
    
    // get input name
    String cmbSearchBy = RutString.nullToStr(request.getParameter("cmbNameSearchBy"));
    String valueSearchBy = RutString.nullToStr(request.getParameter("valueSearchBy"));
    
    // get callback function [optional]
    String callFunction = RutString.nullToStr(request.getParameter("callbackFunction"));
%>
<tr>
    <td class="TableLeftMaint" height="21">Search By : </td>
    <td class="TableLeftWhite" height="21" nowrap>
        <select size="1" name="<%=cmbSearchBy%>" class="FormDropDown" onchange="rcmSearchBy_changeSearchBy()">
            <option value="<%=RcmConstant.SEARCH_BY_DEFAULT%>" <%if(valueSearchBy.equals(RcmConstant.SEARCH_BY_DEFAULT)) {%>selected <%}%>>Default...</option>
            <option value="<%=RcmConstant.SEARCH_BY_INVOY%>" <%if(valueSearchBy.equals(RcmConstant.SEARCH_BY_INVOY)) {%>selected <%}%>>Invoyage#</option>
        </select>
    </td>
    <td colspan="7" class="TableLeftMaint" height="21">&nbsp;</td>
</tr>

<script language="javascript">
//<!--

function rcmSearchBy_changeSearchBy() {
    var strSearchBy = selectValue(getObject("<%=formName%>", "<%=cmbSearchBy%>"));
    if (strSearchBy == '') {
        $("input[alt='<%=altInputDefault%>'], img[alt='<%=altInputDefault%>'], select[alt='<%=altInputDefault%>']").attr("disabled", false);
        $("input[alt='<%=altInputInvoyage%>'], img[alt='<%=altInputInvoyage%>']").attr("disabled", true);
    } else {
        $("input[alt='<%=altInputDefault%>'], img[alt='<%=altInputDefault%>'], select[alt='<%=altInputDefault%>']").attr("disabled", true);
        $("input[alt='<%=altInputInvoyage%>'], img[alt='<%=altInputInvoyage%>']").attr("disabled", false);
    }
    
    try {
        var strCallFunction = "<%=callFunction%>";
        if (strCallFunction != "" && strCallFunction != undefined) {
            eval(strCallFunction);
        }
    } catch(er) { }
}

//-->
</script>
