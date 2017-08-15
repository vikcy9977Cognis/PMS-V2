<!--
-------------------------------------------------------------------------------------------------------------  
RcmPageSelection.jsp
-------------------------------------------------------------------------------------------------------------  
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------  
Author Piyapong Ieumwananonthachai 08/11/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 01/07/09 WUT                       Added for supporting be invisibled on link page, goto page by using submit form
02 26/01/11 DHR         610           Form's Target set to null.
-------------------------------------------------------------------------------------------------------------  
-->

<%@ page language="java" import="java.util.*,com.rclgroup.dolphin.web.util.*,com.rclgroup.dolphin.web.common.RcmConstant"%>
<%//Begin: Page Selection%>
<%
    RutPage rutPage = (RutPage)request.getAttribute("rutPage");
    rutPage = (rutPage == null) ? new RutPage() : rutPage;
    int pageNo = rutPage.getPageNo();
    int numOfPages = rutPage.getNumOfPages();
    int pageSetNo = rutPage.getPageSetNo();
    int maxPagesPerPageSet = rutPage.getMaxPagesPerPageSet();
    boolean isLastPageSet = rutPage.isLastPageSet(pageSetNo);
    String linkPage = RutString.nullToStr(request.getParameter("linkPage")); 
    String hasCopy = request.getParameter("hasCopy");
    String hasDelete = request.getParameter("hasDelete");
    String hasEdit = request.getParameter("hasEdit");
    
    // BEGIN #01
    /** 
     * declare variables 
     * showLinkPage - set visible or invisible link page
     * useForm - flag string be used to check goto page
     **/
    String showLinkPage = request.getParameter("showLinkPage");
    String useForm = RutString.getParameterToString(request, "useForm", "N", RutString.CASE_TYPE_UPPER);
    
    // format: button name | button value | javascript method 
    String hasAddition1 = request.getParameter("hasAddition1"); 
    String hasAddition2 = request.getParameter("hasAddition2");
    String hasAddition3 = request.getParameter("hasAddition3");
    
    // prepare: showLinkPage, strUrl, strLink
    showLinkPage = (RcmConstant.SHOW_LINK_PAGE_INVISIBLE.equals(showLinkPage)) ? RcmConstant.SHOW_LINK_PAGE_INVISIBLE : RcmConstant.SHOW_LINK_PAGE_VISIBLE;
    String strUrl = "";
    String strLink = "";
    // END #01
%>
<table border="0" width="100%" cellspacing="0" cellpadding="0">
    <tr>
        <td width="100%" height="7" class="TableLeftSubBtn">
            <p align="center">
                <b>
                        &nbsp;
                    <%
                        if (RcmConstant.SHOW_LINK_PAGE_VISIBLE.equals(showLinkPage)) {
                            if(numOfPages != 1){
                                if(pageSetNo>1) {
                                    // BEGIN #01
                                    strUrl = linkPage + "&pageAction=linkPage&pageNo="+((pageSetNo - 2) * maxPagesPerPageSet + 1)+"&pageClick=P";
                                    if ("Y".equals(useForm)) {
                                        strLink = "<a href=\"javascript: linkPage_submitByForm('"+strUrl+"')\" class=\"pageNumber\">&lt;&lt;Prev</a>";
                                    } else {
                                        strLink = "<a href=\""+strUrl+"\" class=\"pageNumber\">&lt;&lt;Prev</a>";
                                    }
                                    // END #01
                    %>
                                    <%=strLink%>
                    <%
                                }
                                
                                List pageList = null;
                                try {
                                    pageList = rutPage.getPageNoList(pageSetNo,new ArrayList()); 
                                } catch(Exception e) {}
                                
                                if (pageList != null && pageList.size() > 0) {
                                    for (int i=0;i<pageList.size();i++) {
                                        int curPageNo = ((Integer)pageList.get(i)).intValue();
                                        
                                        // BEGIN #01
                                        strUrl = linkPage + "&pageAction=linkPage&pageNo="+curPageNo;
                                        if ("Y".equals(useForm)) {
                                            strLink = "<a href=\"javascript: linkPage_submitByForm('"+strUrl+"')\" class=\"pageNumber\">"+curPageNo+"</a>";
                                        } else {
                                            strLink = "<a href=\""+strUrl+"\" class=\"pageNumber\">"+curPageNo+"</a>";
                                        }
                                        // END #01
                                        
                                        if(curPageNo == pageNo){
                        %>
                                            <a class="pageNumber">[</a><%=strLink%><a class="pageNumber">]</a>
                        <%
                                        } else {
                        %>
                                            <%=strLink%>
                        <%
                                        }//end if
                                    }//end for
                                } //end if (pageList != null && pageList.size() > 0) {
                                
                                if (!isLastPageSet) {
                                    // BEGIN #01
                                    strUrl = linkPage + "&pageAction=linkPage&pageNo="+(pageSetNo * maxPagesPerPageSet + 1)+"&pageClick=N";
                                    if ("Y".equals(useForm)) {
                                        strLink = "<a href=\"javascript: linkPage_submitByForm('"+strUrl+"')\" class=\"pageNumber\">Next&gt;&gt;</a>";
                                    } else {
                                        strLink = "<a href=\""+strUrl+"\" class=\"pageNumber\">Next&gt;&gt;</a>";
                                    }
                                    // END #01
                    %>
                                    <%=strLink%>
                    <%
                                }//end if
                            }//end if
                        } //end if (RcmConstant.SHOW_LINK_PAGE_VISIBLE.equals(showLinkPage)) {
                    %>
                </b>
            </p>
        </td>
        
        <!-- Button Addition 1 -->
        <%if ((hasAddition1 != null)&&(!hasAddition1.equals(""))) {%>
        <%
            String[] btnOption = null;
            try {
                btnOption = hasAddition1.split("\\|");
                if (btnOption != null && btnOption.length == 3) {
        %>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" <%=btnOption[1]%> " name="<%=btnOption[0]%>" class="FormBtnClr" onClick="<%=btnOption[2]%>"></input></TD>
        <%
                } else if (btnOption != null && btnOption.length == 4) {
        %>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" <%=btnOption[1]%> " name="<%=btnOption[0]%>" class="FormBtnClr" onClick="<%=btnOption[2]%>" <%=btnOption[3]%>></input></TD>
        <%
                }
            } catch(Exception e) { }
        %>
        <%}%>
        
        <!-- Button Addition 2 -->
        <%if ((hasAddition2 != null)&&(!hasAddition2.equals(""))) {%>
        <%
            String[] btnOption = null;
            try {
                btnOption = hasAddition2.split("\\|");
                if (btnOption != null && btnOption.length == 3) {
        %>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" <%=btnOption[1]%> " name="<%=btnOption[0]%>" class="FormBtnClr" onClick="<%=btnOption[2]%>"></input></TD>
        <%
                } else if (btnOption != null && btnOption.length == 4) {
        %>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" <%=btnOption[1]%> " name="<%=btnOption[0]%>" class="FormBtnClr" onClick="<%=btnOption[2]%>" <%=btnOption[3]%>></input></TD>
        <%
                }
            } catch(Exception e) { }
        %>
        <%}%>
        
        <!-- Button Addition 3 -->
        <%if ((hasAddition3 != null)&&(!hasAddition3.equals(""))) {%>
        <%
            String[] btnOption = null;
            try {
                btnOption = hasAddition3.split("\\|");
                if (btnOption != null && btnOption.length == 3) {
        %>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" <%=btnOption[1]%> " name="<%=btnOption[0]%>" class="FormBtnClr" onClick="<%=btnOption[2]%>"></input></TD>
        <%
                } else if (btnOption != null && btnOption.length == 4) {
        %>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" <%=btnOption[1]%> " name="<%=btnOption[0]%>" class="FormBtnClr" onClick="<%=btnOption[2]%>" <%=btnOption[3]%>></input></TD>
        <%
                }
            } catch(Exception e) { }
        %>
        <%}%>
        
        <%if ((hasCopy != null)&&(hasCopy.equalsIgnoreCase("Y"))) {%>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" Copy " name="btnCopy" class="FormBtnClr" onClick="copy();"></input></TD>
        <%}%>
        <%if ((hasDelete != null)&&(hasDelete.equalsIgnoreCase("Y"))) {%>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" Delete " name="btnDelete" class="FormBtnClr" onClick="deletion();"></input></TD>
        <%}%>
        <%if ((hasEdit != null)&&(hasEdit.equalsIgnoreCase("Y"))) {%>
        <td class="TableLeftSubBtn" align="right" vAlign="middle" width="100%" height="22"><input type="button" value=" Edit " name="btnEdit" class="FormBtnClr" onClick="edit();"></input></TD>
        <%}%>
    </tr>
</table>
<%//End: Page Selection%>

<% // BEGIN #01 %>
<% if ("Y".equals(useForm)) { %>

<script language="javascript">
//<!--

function linkPage_submitByForm(strUrl) {
    try {
        if (strUrl != null && strUrl != "") {
            var frm = document.forms[0];
            frm.action = strUrl;
            frm.pageAction.value = "linkPage";
            frm.target = ""; //##02
            frm.submit();
        }
        
    } catch(e) {
        alert("Error RcmPageSelection: linkPage_submitByForm");
    }
}

//-->
</script>
    
<% } %>
<% // END #01 %>
