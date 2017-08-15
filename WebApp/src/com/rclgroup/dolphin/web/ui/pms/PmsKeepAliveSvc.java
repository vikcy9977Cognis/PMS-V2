/*-----------------------------------------------------------------------------------------------------------
PmsKeepAliveSvc.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------
 Author Khomsun H. 24/03/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;

import com.rclgroup.dolphin.web.util.RutString;

import java.io.IOException;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public class PmsKeepAliveSvc extends RrcStandardSvc {
    public PmsKeepAliveSvc() {
        super();
    }

    public void execute(HttpServletRequest request,
                        HttpServletResponse response, ServletContext context) {
    
        HttpSession session = request.getSession(false);
        try {

            String strPart = RutString.nullToStr(request.getParameter("part"));
            boolean isSessionExpired = false;
            // ----------------------------------
            // Parse Request
            // ----------------------------------

            if (strPart.equalsIgnoreCase("checkKeepAlive")) {

                // Manage the attributes of User: line, region, agent, fsc
                if (session == null) {
                    isSessionExpired = true;
                } else {
                    RcmUserBean userBean =
                        (RcmUserBean)session.getAttribute("userBean");
                    if (userBean == null) {
                        isSessionExpired = true;
                    } else {
                        // Check Last access time
                        int maxInactiveInterval =
                            session.getMaxInactiveInterval();
                        Long lastAccessTime =
                            (Long)session.getAttribute(PmsConstant.LAST_ACCESS_TIME_KEY);
                        Long currentTime = new Date().getTime();
                        if (lastAccessTime != null) {
                            Long nTimeElapse =
                                getDiffInMins(lastAccessTime, currentTime);
                            if ( (nTimeElapse * 60) >= maxInactiveInterval) {
                                isSessionExpired = true;
                                System.out.println("Session expired time elapse " +
                                                   nTimeElapse.intValue() +
                                                   " minutes");
                            }
                        }
                    }
                }
                JSONObject responseJson = new JSONObject();
                responseJson.put("statusCode",
                                 isSessionExpired ? PmsConstant.STATUS_ERROR : PmsConstant.STATUS_SUCCESS);
                responseJson.put("statusMessage", "");
                response.setContentType("application/json");
                response.getWriter().println(responseJson.toString());

            }
        } catch (Exception exc) {
            exc.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("statusCode", PmsConstant.STATUS_ERROR);
            responseJson.put("statusMessage", "");
            response.setContentType("application/json");
            try {
                response.getWriter().println(responseJson.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private Long getDiffInMins(Long lastAccessTime, Long currentTime) {
        Long diff = currentTime - lastAccessTime;
        long diffMinutes = diff / (60 * 1000); // In Minutes
        return diffMinutes;
    }
}
