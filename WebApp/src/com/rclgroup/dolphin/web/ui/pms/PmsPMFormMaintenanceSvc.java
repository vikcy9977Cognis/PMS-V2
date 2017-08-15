package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;
import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;

import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMForm2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMFormDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;
import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmListMod;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;
import com.rclgroup.dolphin.web.model.pms.PmsPmHDRMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PmsPMFormMaintenanceSvc extends RrcStandardSvc {
    public PmsPMFormMaintenanceSvc() {
        super();
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        try {

            String strTarget = null;
            HttpSession session = request.getSession(false);
            // <-- Update last access time
            boolean isAjaxPoll = "Y".equalsIgnoreCase(request.getHeader(PmsConstant.AJAX_POLL_KEY));

            if (session != null && !isAjaxPoll) {
                session.setAttribute(PmsConstant.LAST_ACCESS_TIME_KEY, new Date().getTime());
            }
            // -->
            // Clear status message.
            session.setAttribute("statusCode", 0);
            session.setAttribute("statusMessage", "");
            //String strPageAction = RutString.nullToStr(request.getParameter("pageAction"));
            String strPart = RutString.nullToStr(request.getParameter("part"));

            PmsCommonUim uim = (PmsCommonUim)session.getAttribute("pmsCommonUim");
            if (uim == null) {
                uim = new PmsCommonUim();
                uim.setRcmConstantDao((RcmConstantDao)getBean("rcmConstantDao"));
                uim.setCamFscDao((CamFscDao)getBean("camFscDao"));
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean)session.getAttribute("userBean");
                this.manageUserBean(uim, userBean);
                uim.setPmsCommonDao((PmsCommonDao)getBean("pmsCommonDao"));
                String strPermissionUser = this.getPermissionUserCode(uim);
                session.setAttribute("pmsCommonUim", uim);
            }
            PmsPMMaintenanceUim pmsMaintenanceUim = (PmsPMMaintenanceUim)session.getAttribute("pmsMaintenanceUim");
            if (pmsMaintenanceUim == null) {
                pmsMaintenanceUim = new PmsPMMaintenanceUim();
                pmsMaintenanceUim.setRcmConstantDao((RcmConstantDao)getBean("rcmConstantDao"));
                pmsMaintenanceUim.setCamFscDao((CamFscDao)getBean("camFscDao"));
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean)session.getAttribute("userBean");
                this.manageUserBean(pmsMaintenanceUim, userBean);
                pmsMaintenanceUim.setPmsPMForm2Dao((PmsPMForm2Dao)getBean("pmsPMForm2Dao"));
                pmsMaintenanceUim.setPmsCommon2Dao((PmsCommon2Dao)getBean("pmsCommon2Dao"));
                
                String strPkEmpSeqNo = null;
                final List<CrmProfileMod> crmProfilModList = uim.getProfileType();
                if(crmProfilModList != null && crmProfilModList.size() > 0){
                    strPkEmpSeqNo = crmProfilModList.get(0).getPkEmpSeqno();
                }
                pmsMaintenanceUim.setPkEmpSeqNo(Long.valueOf(strPkEmpSeqNo));
                
                String strPermissionUser = this.getPermissionUserCode(pmsMaintenanceUim);
                session.setAttribute("pmsMaintenanceUim", pmsMaintenanceUim);

            }

            //get User Authorize
            if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_GET_USR_AUTH)) {
                try {
//                    String strEmpSeqNo = request.getParameter("empSeqNo");
                    String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
                    //                    empSeqNo=80&pmHDRSeqNo=51
//                    String strEmpSeqNo = null;
//                    final List<CrmProfileMod> crmProfilModList = uim.getProfileType();
//                    if(crmProfilModList != null && crmProfilModList.size() > 0){
//                        strEmpSeqNo = crmProfilModList.get(0).getPkEmpSeqno();
//                    }
//                    
                    JSONObject responseJson = new JSONObject();
                    JSONObject data =
                        pmsMaintenanceUim.getUserAuthorizeAsJSON(pmsMaintenanceUim.getPkEmpSeqNo(), new Long(strPmHDRSeqNo).longValue());

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", data);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_PM_HEADER)) { // Load pm Header
                try {
                    String strEmpSeqNo = request.getParameter("empSeqNo");
                    String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
                    String pmKey = (String)context.getAttribute(PmsConstant.PM_KEY);
                    JSONObject responseJson = new JSONObject();
                    PmsPmHDRMod pmHDRMod =
                        pmsMaintenanceUim.getPMHeaderAsJSON(pmsMaintenanceUim.getPkEmpSeqNo(), pmKey, new Long(strPmHDRSeqNo).longValue());
                    
                    JSONObject data = pmsMaintenanceUim.formatPMHeaderAsJSON(pmHDRMod);

//                    final List<CrmProfileMod> crmProfilModList = uim.getPmsCommonDao().getProfileType(String.valueOf(pmHDRMod.getEmp_seqno()));
//                    if(crmProfilModList != null && crmProfilModList.size() > 0){
//                        
//                        int expMonth = crmProfilModList.get(0).getExpMonth();
//                        int expYear = crmProfilModList.get(0).getExpYear();
//                        data.put("expMonth", expMonth);
//                        data.put("expYear", expYear);
//                    }
                    
                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", data);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_JOB_BRAND_WEIGHTAGE)) { // Load pm Header
                try {


                    JSONObject responseJson = new JSONObject();

                    JSONArray data = pmsMaintenanceUim.getJobBrandWeightageAsJSON();

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", data);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_COMPANY_AND_DEPARTMENT_CORE_VALUE)) {
                try {
                    String strEmpSeqNo = request.getParameter("empSeqNo");
                    String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
                    //                    String pmKey = "dcspms01";
                    String pmKey = (String)context.getAttribute(PmsConstant.PM_KEY);

                    JSONObject responseJson = new JSONObject();
                    List<PmsIndicatorMod> departIndList = pmsMaintenanceUim.getIndicatorList("DEPARTMENT");
                    JSONArray departmentIndicatorJsonArr = uim.formatIndigatorAsJSON(departIndList);

                    JSONArray companyArr =
                        pmsMaintenanceUim.getCompanyCoreValueAsJSON(pmsMaintenanceUim.getPkEmpSeqNo(), pmKey,
                                                                    new Long(strPmHDRSeqNo).longValue());
                    JSONArray departmentArr =
                        pmsMaintenanceUim.getDepartmentCoreValueAsJSON(pmsMaintenanceUim.getPkEmpSeqNo(), pmKey,
                                                                       new Long(strPmHDRSeqNo).longValue());

                    JSONObject data = new JSONObject();
                    data.put("departmentIndcators", departmentIndicatorJsonArr);
                    data.put("companyCoreValue", companyArr);
                    data.put("departmentCoreValue", departmentArr);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", data);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_PRESIDENT_DIRECTIVE)) {
                try {
                    String strEmpSeqNo = request.getParameter("empSeqNo");
                    String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
                    //                    String pmKey = "dcspms01";
                    String pmKey = (String)context.getAttribute(PmsConstant.PM_KEY);
                    //                    JSONObject data = new JSONObject();
                    JSONObject responseJson = new JSONObject();
                    JSONArray pdJsonArr =
                        pmsMaintenanceUim.getPDIndAsJSON(pmsMaintenanceUim.getPkEmpSeqNo(), pmKey, new Long(strPmHDRSeqNo).longValue());

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", pdJsonArr);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_INDIVIDUAL_KPI)) {
                try {
                    String strEmpSeqNo = request.getParameter("empSeqNo");
                    String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
                    //                    String pmKey = "dcspms01";
                    String pmKey = (String)context.getAttribute(PmsConstant.PM_KEY);
                    //                    JSONObject data = new JSONObject();
                    JSONObject responseJson = new JSONObject();
                    JSONArray pdJsonArr =
                        pmsMaintenanceUim.getIndividualKPIsAsJSON(pmsMaintenanceUim.getPkEmpSeqNo(), pmKey,
                                                                  new Long(strPmHDRSeqNo).longValue());

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", pdJsonArr);
                    List<PmsIndicatorMod> bscIndList = pmsMaintenanceUim.getIndicatorList("BSC");
                    JSONArray bscIndicatorJsonArr = uim.formatIndigatorAsJSON(bscIndList);
                    responseJson.put("bscIndicators", bscIndicatorJsonArr);

                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_INDICATOR_DETAIL)) {
                try {
                    String strIndSeqNo = request.getParameter("indSeqNo");
                    String strJobBrand = request.getParameter("jobBrand");

                    //                    String pmKey = "dcspms01";
                    //                    JSONObject data = new JSONObject();
                    JSONObject responseJson = new JSONObject();
                    JSONObject retIndDetail =
                        pmsMaintenanceUim.getIndicatorDetailAsJSON(new Long(strIndSeqNo).longValue(), strJobBrand);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", retIndDetail);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    int totalRowCount = 0;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_MAINTENANCE_LOAD_OVERALL)) {
                try {
                    String strEmpSeqNo = request.getParameter("empSeqNo");
                    String strJobBrand = request.getParameter("jobBrand");

                    String pmKey = (String)context.getAttribute(PmsConstant.PM_KEY);

                    JSONObject responseJson = new JSONObject();
                    JSONObject overallJson =
                        pmsMaintenanceUim.getOverallPMAsJSON(strJobBrand, Long.valueOf(strEmpSeqNo), pmKey);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", overallJson);

                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JSONObject responseJson = new JSONObject();
                    responseJson.put("data", "");
                    responseJson.put("valid", "N");
                    responseJson.put("msg", ex.getMessage());
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase("maintenance_edit_save")) {
                pmsMaintenanceUim.save(request, response, context ,uim);
               
            } else if (strPart.equalsIgnoreCase("maintenance_edit_submit")) {
                pmsMaintenanceUim.submit(request, response, context ,uim);
               
            } else if (strPart.equalsIgnoreCase("maintenance_edit_approve_reject")) {
                pmsMaintenanceUim.approveAndReject(request, response, context ,uim);
               
            }else if (strPart.equalsIgnoreCase("maintenance_get_attach_file")) {
                pmsMaintenanceUim.getAttachFileList(request, response, context ,uim);
               
            }else if (strPart.equalsIgnoreCase("maintenance_insert_attach_file")) {
                pmsMaintenanceUim.insertAttachFileList(request, response, context ,uim);
               
            }else if (strPart.equalsIgnoreCase("maintenance_add_attach_file")) {
                pmsMaintenanceUim.addAttachFileList(request, response, context ,uim);
               
            }else if (strPart.equalsIgnoreCase("maintenance_remove_attach_file")) {
                pmsMaintenanceUim.removeAttachFileList(request, response, context ,uim);
               
            }else if (strPart.equalsIgnoreCase("maintenance_download_attach_file")) {
                pmsMaintenanceUim.dowloadAttachFile(request, response, context ,uim);
               
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
