package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;
import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;

import com.rclgroup.dolphin.web.common.RrcStandardUim;
import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsReportDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownCompanyMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMStatusReportMod;
import com.rclgroup.dolphin.web.util.PmsLogUtil;
import com.rclgroup.dolphin.web.util.RutRequest;
import com.rclgroup.dolphin.web.util.RutString;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PmsReportSvc extends RrcStandardSvc {
    final String UI_DATE_FORMAT = "dd/MM/yyyy";
    final String DB_DATE_FORMAT = "yyyyMMdd";
    
    public PmsReportSvc() {
        super();
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        String strPageAction = "";
        String strPart = "";
        long enter_time = new Date().getTime();
        long exit_time = 0L;
        String userID = "";

        try {
            String strTarget = null;

            HttpSession session = request.getSession(false);


            // Clear status message.
            session.setAttribute("statusCode", 0);
            session.setAttribute("statusMessage", "");

            strPageAction = RutString.nullToStr(request.getParameter("pageAction"));

            strPart = RutString.nullToStr(request.getParameter("part"));
            String strReferer = request.getHeader("Referer");
            boolean isAjaxRequest = false;
            String strXRequested = request.getHeader("X-Requested-With");
            if (strXRequested != null && strXRequested.equalsIgnoreCase("XMLHttpRequest")) {
                isAjaxRequest = true;
            }
            String useMultipart = (String) RutRequest.getParameterValueByCode(request, "useMultipart");

            if (RcmConstant.FLAG_YES.equals(useMultipart)) {
                strPageAction = RutRequest.getParameterValueByCode(request, "pageAction");
                strPart = RutRequest.getParameterValueByCode(request, "part");
            }

            // ----------------------------------
            // Parse Request
            // ----------------------------------
            PmsReportUim uim = (PmsReportUim) session.getAttribute("pmsReportUim");
            if (uim == null) {
                uim = new PmsReportUim();

                uim.setRcmConstantDao((RcmConstantDao) getBean("rcmConstantDao"));
                uim.setCamFscDao((CamFscDao) getBean("camFscDao"));

                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
                this.manageUserBean(uim, userBean);

                uim.setPmsCommonDao((PmsCommonDao) getBean("pmsCommonDao"));
                uim.setPmsReportDao((PmsReportDao) getBean("pmsReportDao"));
                uim.setPmsCommon2Dao((PmsCommon2Dao) getBean("pmsCommon2Dao"));
                session.setAttribute("pmsReportUim", uim);
            }
            if (strPart.equalsIgnoreCase("initReport")) {
                userID = uim.getPrsnLogIdOfUser();
                // Get current date
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat(UI_DATE_FORMAT);
                String strInitDate = sdf.format(now);
                // Get All PM Years
                List<CrmDropDownYearMod> yearList = uim.getPmsCommonDao().getYearList(userID);
                // Init PM Period
                Integer[] periodList = new Integer[]{1,2};
                
                //Get Company list
                List<CrmDropDownCompanyMod>  companyList = uim.getPmsCommonDao().getCompanyList(userID);
                
                // Active PM Year/Period
                Map<String,Object> activePMYearMap = uim.getPmsCommonDao().getActivePMYearPeriod(userID);
                Integer nYear = Integer.parseInt(String.valueOf(activePMYearMap.get("P_O_PM_YEAR")));
                Integer nPeriod = Integer.parseInt(String.valueOf(activePMYearMap.get("P_O_PM_PERIOD")));
                
                // Format Initial Report data in JSON
                JSONObject responseJson = new JSONObject();
                JSONArray yearJson = new JSONArray();
                JSONArray periodJson = new JSONArray();
                JSONArray companyJson = new JSONArray();
                responseJson.put("defaultDataDate", strInitDate);
                responseJson.put("defaultPMYear", nYear);
                responseJson.put("defaultPMPeriod", nPeriod);
                responseJson.put("yearList", yearJson);
                responseJson.put("periodList", periodJson);
                responseJson.put("companyList", companyJson);
                for(CrmDropDownYearMod item:yearList) {
                    JSONObject jsonItem = new JSONObject();
                    yearJson.add(item.getValue());
                }
                for(Integer period: periodList) {
                    periodJson.add(period);
                }
                for(CrmDropDownCompanyMod item:companyList) {
                    JSONObject jsonItem = new JSONObject();
                    jsonItem.put("description", item.getValue());
                    jsonItem.put("countryCode", item.getDescription());
                    jsonItem.put("comSeqNo", item.getDescription2());
                    companyJson.add(jsonItem);
                }
                
                // Send response back to page.
                response.setContentType("application/json");
                response.getWriter().println(responseJson.toString());
                
            }
             else if (strPart.equalsIgnoreCase("getStatusReport")) {
                String strUserID = uim.getPrsnLogIdOfUser();
                String strPMKey = (String) context.getAttribute(PmsConstant.PM_KEY);
                String strDate = request.getParameter("date");
                String strPMYear = request.getParameter("pmYear");
                String strPMPeriod = request.getParameter("pmPeriod");
                String strCompanySeqNo = request.getParameter("comSeqNo");

                JSONObject responseJson = new JSONObject();
                JSONArray data = new JSONArray();
                responseJson.put("data", data);

                //       Map<String, Object> outMap = uim.getPmsCommon2Dao().getIndicatorDetail("10514", 1L, "EXECUTIVE");

                //      String strEncypted = uim.getPmsCommon2Dao().getEncryptPMKey(strUserID, strPMKey);
                
               // PmsJobWeightageMod jobWeightage = uim.getPmsCommon2Dao().getJobWeightage("10514", "EXECUTE", 2017, 1);
               
                // Param Date
                Integer nDate = null;
                if (!RutString.isEmptyString(strDate)) {
                    SimpleDateFormat sdf = new SimpleDateFormat(UI_DATE_FORMAT);
                   Date dataDate = sdf.parse(strDate);
                    nDate = Integer.parseInt(new SimpleDateFormat(DB_DATE_FORMAT).format(dataDate));
                }
                // Param PM Year
                Integer nPMYear = null;
                if (!RutString.isEmptyString(strPMYear)) {
                    nPMYear = Integer.parseInt(strPMYear);
                }
                // Param PM Period
                Integer nPMPeriod = null;
                if (!RutString.isEmptyString(strPMPeriod)) {
                    nPMPeriod = Integer.parseInt(strPMPeriod);
                }
                // Param Company Seqno.
                Long nComSeqNo = null;
                if (!RutString.isEmptyString(strCompanySeqNo)) {
                    nComSeqNo = Long.parseLong(strCompanySeqNo);
                }
                try {
                    List<PmsPMStatusReportMod> outList =
                        uim.getPmsReportDao().getPMStatusReport(strUserID, strPMKey, nDate, nPMYear, nPMPeriod,
                                                                nComSeqNo);
                    data = uim.formatStatusReportAsJSON(outList);
                    responseJson.put("data", data);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                response.setContentType("application/json");
                response.getWriter().println(responseJson.toString());
            } 
        } catch (Exception exc) {
            exc.printStackTrace();
            try {
                String errorMsg = PmsLogUtil.getDBSQLExceptionMessage(exc.getMessage());
                responseErrorJSON(response, errorMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Response ERROR in JSON format.
     * @param response
     * @param strErrorMsg
     * @throws IOException
     */
    private void responseErrorJSON(HttpServletResponse response, String strErrorMsg) throws IOException {
        
        int statusCode = PmsConstant.STATUS_ERROR;
        JSONObject responseJson = new JSONObject();
        responseJson.put("statusCode", statusCode);
        responseJson.put("statusMessage", strErrorMsg);
        response.setContentType("application/json");
        response.getWriter().println(responseJson.toString());
    }
    
}
