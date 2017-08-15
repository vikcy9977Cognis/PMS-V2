
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" session="true" autoFlush ="true" errorPage="/pages/error/RcmErrorPage.jsp"%>
<%@page import = "com.rclgroup.dolphin.web.common.*"%>
 <%
    String sessionExpireUrl = RcmConstant.SEALINER_PAGE_URL+"/callexp.jsp";
%>

 <script type="text/javascript">
 var sessionExpireUrl = '<%=sessionExpireUrl%>';
 var check_session;
 var strCheckKeepAliveUrl = '<c:url value="/RrcStandardSrv?service=ui.pms.PmsKeepAliveSvc&part=checkKeepAlive"/>';
function CheckKeepAliveSession() {
		jQuery.ajax({
				type: "POST",
				url: strCheckKeepAliveUrl,
				cache: false,
                                 beforeSend: function(xhr) {
                                    xhr.setRequestHeader("ajax_poll", "Y");
                                  },
				success: function(jsonResponse){
					if (jsonResponse.statusCode != 0) {
                                            // Redirect to session expired page.
                                           window.location.href = sessionExpireUrl;
                                        }
				}
		});
}
check_session = setInterval(CheckKeepAliveSession, 2*60000); // Check keep alive every 2 minutes
 </script>