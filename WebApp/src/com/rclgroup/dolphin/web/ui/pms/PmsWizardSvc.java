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

import java.io.BufferedReader;

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
import org.json.simple.parser.JSONParser;

public class PmsWizardSvc extends RrcStandardSvc {
    public PmsWizardSvc() {
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
            // Clear status message.
            session.setAttribute("statusCode", 0);
            session.setAttribute("statusMessage", "");
            //String strPageAction = RutString.nullToStr(request.getParameter("pageAction"));
            String strPart = RutString.nullToStr(request.getParameter("part"));

            PmsCommonUim uim = (PmsCommonUim) session.getAttribute("pmsCommonUim");
            if (uim == null) {
                uim = new PmsCommonUim();
                uim.setRcmConstantDao((RcmConstantDao) getBean("rcmConstantDao"));
                uim.setCamFscDao((CamFscDao) getBean("camFscDao"));
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
                this.manageUserBean(uim, userBean);
                uim.setPmsCommonDao((PmsCommonDao) getBean("pmsCommonDao"));
                uim.setPmsCommon2Dao((PmsCommon2Dao) getBean("pmsCommon2Dao"));
                String strPermissionUser = this.getPermissionUserCode(uim);
                session.setAttribute("pmsCommonUim", uim);
            }
            
            if(uim.getPmsCommon2Dao()==null){
                uim.setPmsCommon2Dao((PmsCommon2Dao) getBean("pmsCommon2Dao"));
            }
            PmsPMUim uimPMForm = (PmsPMUim) session.getAttribute("pmsPMUim");
            if (uimPMForm == null) {
                uimPMForm = new PmsPMUim();
                uimPMForm.setRcmConstantDao((RcmConstantDao) getBean("rcmConstantDao"));
                uimPMForm.setCamFscDao((CamFscDao) getBean("camFscDao"));
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
                this.manageUserBean(uimPMForm, userBean);
                uimPMForm.setPmsPMFormDao((PmsPMFormDao) getBean("pmsPMFormDao"));
                uimPMForm.setPmsPMForm2Dao((PmsPMForm2Dao) getBean("pmsPMForm2Dao"));
                //uim.setPmsCommonDao((PmsCommonDao)getBean("pmsCommonDao"));
                String strPermissionUser = this.getPermissionUserCode(uimPMForm);
                session.setAttribute("pmsPMUim", uimPMForm);

            }
            PmsPMWizardUim uimPMWizard = (PmsPMWizardUim) session.getAttribute("pmsPMWizardUim");
            if (uimPMWizard == null) {
                uimPMWizard = new PmsPMWizardUim();
                uimPMWizard.setRcmConstantDao((RcmConstantDao) getBean("rcmConstantDao"));
                uimPMWizard.setCamFscDao((CamFscDao) getBean("camFscDao"));
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");
                this.manageUserBean(uimPMWizard, userBean);
                uimPMWizard.setPmsPMForm2Dao((PmsPMForm2Dao) getBean("pmsPMForm2Dao"));
                //uim.setPmsCommonDao((PmsCommonDao)getBean("pmsCommonDao"));
                String strPermissionUser = this.getPermissionUserCode(uimPMWizard);
                session.setAttribute("pmsPMWizardUim", uimPMWizard);

            }
            //get appraisee tab1
            if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_APPRAISEE)) {
                try {
                    final Map<String, Object> pmYearPeriods = uim.getActivePMYearPeriod();
                    Integer year = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_YEAR")));
                    Integer period = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_PERIOD")));
                    final List<CrmProfileMod> crmProfilModList = uim.getProfileType();
                    final List<PmsJobWeightageMod> pmsJobWeightageList = uim.getJobWeightageList(year, period);
                    
                    if(crmProfilModList!=null&&crmProfilModList.size()>0){
                        uimPMWizard.setPkEmpSeqNo(Long.parseLong(crmProfilModList.get(0).getPkEmpSeqno()));
                        uimPMWizard.setJobBrand(crmProfilModList.get(0).getJobBrand());
                    }

                    JSONObject responseJson = new JSONObject();
                    JSONArray crmProfileJson = uim.formatProfileTypeAsJSON(crmProfilModList);
                    JSONArray pmsJobWeightageJson = uim.formatJobWeightageAsJSON(pmsJobWeightageList);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", null);
                    responseJson.put("pm_year", year);
                    responseJson.put("pm_period", period);
                    responseJson.put("crm_profile", crmProfileJson);
                    responseJson.put("job_weightage", pmsJobWeightageJson);
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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_COMPETENCY)) {
                try {
                    final Map<String, Object> pmYearPeriods = uim.getActivePMYearPeriod();
                    Integer year = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_YEAR")));
                    Integer period = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_PERIOD")));
                    final List<PmsIndicatorMod> indicatorCompanyList = uim.getIndicatorList(year, period, "COMPANY");
                    final List<PmsIndicatorMod> indicatorDepartmentList = uim.getIndicatorList(year, period, "DEPARTMENT");

                    JSONObject responseJson = new JSONObject();
                    JSONArray indicatorCompanyJson = uim.formatIndigatorAsJSON(indicatorCompanyList);
                    JSONArray indicatorDepartmentJson = uim.formatIndigatorAsJSON(indicatorDepartmentList);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", null);
                    responseJson.put("indicator_company", indicatorCompanyJson);
                    responseJson.put("indicator_department", indicatorDepartmentJson);
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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_PRESIDENT_DIRECTIVE)) {
                try {
                    final Map<String, Object> pmYearPeriods = uim.getActivePMYearPeriod();
                    Integer year = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_YEAR")));
                    Integer period = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_PERIOD")));
                    final List<PmsIndicatorMod> indicatorPresidentList = uim.getIndicatorList(year, period, "PRESIDENT");
                    final List<PmsIndicatorMod> indicatorBSCList = uim.getIndicatorList(year, period, "BSC");

                    JSONObject responseJson = new JSONObject();
                    JSONArray indicatorPresidentJson = uim.formatIndigatorAsJSON(indicatorPresidentList);
                    JSONArray indicatorBSCJson = uim.formatIndigatorAsJSON(indicatorBSCList);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", null);
                    responseJson.put("indicator_presient", indicatorPresidentJson);
                    responseJson.put("indicator_bsc", indicatorBSCJson);
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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_OVERALL)) {
                try {
                    final Map<String, Object> pmYearPeriods = uim.getActivePMYearPeriod();
                    Integer year = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_YEAR")));
                    Integer period = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_PERIOD")));
                    final String pmKey = (String) context.getAttribute(PmsConstant.PM_KEY);
                    final PmsJobWeightageMod jobWeightage = uim.getJobWeightage(uimPMWizard.getJobBrand(), year, period);
                    final List<PmsPMOverallMod> list = uimPMWizard.getPMOverall(pmKey, uimPMWizard.getPkEmpSeqNo(), year);

                    JSONObject responseJson = new JSONObject();
                    JSONArray json = uimPMWizard.formatPMOveralltoJSON(list);

                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", null);
                    responseJson.put("pm_job_weightage", uim.formatJobWeightagetoJSON(jobWeightage));
                    responseJson.put("pm_overall", json);
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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_CONFIRM)) {
                try {
                    final Map<String, Object> pmYearPeriods = uim.getActivePMYearPeriod();
                    Integer year = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_YEAR")));
                    Integer period = Integer.parseInt(String.valueOf(pmYearPeriods.get("P_O_PM_PERIOD")));
                    final String pmKey = (String) context.getAttribute(PmsConstant.PM_KEY);
                    JSONObject responseJson = new JSONObject();

                    JSONObject jsonRequestObj = (JSONObject)request.getSession().getAttribute(PmsConstant.PART_ACTION_WIZARD_DATA);
                    //Insert PM Header
                    final PmsPmHDRMod hdrMod = this.getPmsPmHDRMod((JSONObject)jsonRequestObj.get("performanceProgress"), year, period);
                    final Long hdrSeqNo = uimPMWizard.insertPMHeader(pmKey, hdrMod);
                    
                    //Insert company core value
                    JSONObject com = (JSONObject)jsonRequestObj.get("completecyProfile");
                    JSONArray companyCoreValue = (JSONArray)com.get("companyCoreValue");
                    uimPMWizard.insertCompanyCoreValue(pmKey, hdrSeqNo, companyCoreValue);                    
                    //Insert department core value
                    JSONArray departmentCoreValue = (JSONArray)com.get("departmentCoreValue");
                    uimPMWizard.insertDepartmentCoreValue(pmKey, hdrSeqNo, departmentCoreValue);

                    //Insert Individual KPI
                    JSONObject president = (JSONObject)jsonRequestObj.get("presidentDirective");
                    JSONArray individualKPIs = (JSONArray)president.get("individualKPIs");
                    uimPMWizard.insertIndividualKPI(pmKey, hdrSeqNo, individualKPIs);
                    
                    uimPMWizard.calculatePMHeader(pmKey, hdrSeqNo);
                    final String action = (String)jsonRequestObj.get("action");
                    if(action.equalsIgnoreCase("submit")){
                        uimPMWizard.pmSubmit(hdrSeqNo);
                    }
                    request.getSession().removeAttribute(PmsConstant.PART_ACTION_WIZARD_DATA);
                    
                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", jsonRequestObj);
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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_SET_DATA)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    StringBuffer sb = new StringBuffer();
                    BufferedReader bufferedReader = request.getReader();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line.trim());
                    }
                    JSONParser jsonParse = new JSONParser();
                    JSONObject jsonRequestObj = (JSONObject)jsonParse.parse(sb.toString());
                    
                    request.getSession().setAttribute(PmsConstant.PART_ACTION_WIZARD_DATA, jsonRequestObj);
                    
                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", jsonRequestObj);
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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_WIZARD_GET_DATA)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    
                    JSONObject jsonRequestObj = (JSONObject)request.getSession().getAttribute(PmsConstant.PART_ACTION_WIZARD_DATA);
                    
                    responseJson.put("valid", "V");
                    responseJson.put("msg", "");
                    responseJson.put("data", jsonRequestObj);
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
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    
    private PmsPmHDRMod getPmsPmHDRMod(final JSONObject obj, final Integer pmYear, final Integer pmPeriod){
        final PmsPmHDRMod mod = new PmsPmHDRMod();
        if(obj!=null){
            mod.setPm_year(pmYear);
            mod.setPm_period(pmPeriod);
            mod.setPart3_comment1(RutString.nullToStr(obj.get("reason")));
            mod.setPart3_comment2(RutString.nullToStr(obj.get("barriers")));
            mod.setPart3_comment3(RutString.nullToStr(obj.get("indicate_learning")));
            mod.setPart3_comment4(RutString.nullToStr(obj.get("appraisee_comment")));
        }
        
        return mod;
    }
    
//    public static void main(String[] args){
//        String strJson = "{\"weightageByJobBand\":[{\"job_band\":\"President\",\"pd\":\"100%\",\"completency\":0,\"$$hashKey\":\"00K\",\"individual_kpis\":0},{\"job_band\":\"MC,SVP,EVP\",\"pd\":\"50%\",\"completency\":100,\"$$hashKey\":\"00L\",\"individual_kpis\":50},{\"job_band\":\"VP,GM,RH\",\"pd\":\"25%\",\"completency\":100,\"$$hashKey\":\"00M\",\"individual_kpis\":75},{\"job_band\":\"AGM,MGR,LH\",\"pd\":\"0%\",\"completency\":100,\"$$hashKey\":\"00N\",\"individual_kpis\":100},{\"job_band\":\"ASSIST MGR,EXEC\",\"pd\":\"0%\",\"completency\":100,\"$$hashKey\":\"00O\",\"individual_kpis\":100},{\"job_band\":\"Support\",\"pd\":\"0%\",\"completency\":100,\"$$hashKey\":\"00P\",\"individual_kpis\":100}],\"presidentDirective\":{\"presidentDirective\":[{\"rating_1\":\"\",\"weightage\":30,\"rating_0\":\"\",\"company_goal\":\"EBIDA\",\"sum_rate\":0.9,\"rating\":3,\"$$hashKey\":\"047\",\"result\":\"\",\"bsc_perspective\":\"EBIDA\",\"rating_5\":\"\",\"rating_4\":\"\",\"rating_3\":\"\",\"rating_2\":\"\"},{\"rating_1\":\"\",\"weightage\":25,\"rating_0\":\"\",\"company_goal\":\"Operating Excellence\",\"sum_rate\":0.75,\"rating\":3,\"$$hashKey\":\"048\",\"result\":\"\",\"bsc_perspective\":\"Operating Excellence\",\"rating_5\":\"\",\"rating_4\":\"\",\"rating_3\":\"\",\"rating_2\":\"\"},{\"rating_1\":\"\",\"weightage\":25,\"rating_0\":\"\",\"company_goal\":\"IT Compatible\",\"sum_rate\":0.75,\"rating\":3,\"$$hashKey\":\"049\",\"result\":\"\",\"bsc_perspective\":\"IT Compatible\",\"rating_5\":\"\",\"rating_4\":\"\",\"rating_3\":\"\",\"rating_2\":\"\"},{\"rating_1\":\"\",\"weightage\":20,\"rating_0\":\"\",\"company_goal\":\"Human Capital\",\"sum_rate\":0.6,\"rating\":3,\"$$hashKey\":\"04A\",\"result\":\"\",\"bsc_perspective\":\"Human Capital\",\"rating_5\":\"\",\"rating_4\":\"\",\"rating_3\":\"\",\"rating_2\":\"\"}],\"individualKPIs\":[{\"rating_1\":0,\"weightage\":50,\"individual_goal\":\"\",\"rating_0\":0,\"sum_rate\":0.75,\"rating\":\"1.5\",\"$$hashKey\":\"04F\",\"result\":0,\"bsc_perspective\":\"28\",\"rating_5\":0,\"rating_4\":0,\"rating_3\":0,\"rating_2\":0},{\"rating_1\":0,\"weightage\":40,\"individual_goal\":\"\",\"rating_0\":0,\"sum_rate\":2.55,\"rating\":\"4.5\",\"$$hashKey\":\"05D\",\"result\":0,\"bsc_perspective\":\"27\",\"rating_5\":0,\"rating_4\":0,\"rating_3\":0,\"rating_2\":0}],\"total_president_weightage\":100,\"total_individual_rate\":2.55,\"total_individual_weightage\":90,\"total_president_rate\":3,\"bscPerspective\":[{\"id\":26,\"$$hashKey\":\"04I\",\"value\":\"Financial\"},{\"id\":27,\"$$hashKey\":\"04J\",\"value\":\"Internal Process\"},{\"id\":28,\"$$hashKey\":\"04K\",\"value\":\"People Capability\"}]},\"appraiseeDetail\":{\"employee_id\":\"12965\",\"grade\":\"\",\"counter_siging_supervisor\":\"Twinchok Tanthuwanit\",\"performance_appraisal\":\"1H Y2017\",\"name\":\"DEV_TEAM\",\"position_title\":\"EXECUTIVE\",\"date_joined\":\"2017-03-02T03:49:24.000Z\",\"department\":\"Administrator VN RCV (Ho Chi Minh) - Vendor Sourcing & System Support \",\"completency_behavior_level\":\"\",\"supervisor\":\"Li Xiao Long\"},\"departmentCoreValues\":[{\"id\":52,\"$$hashKey\":\"02T\",\"value\":\"Business Orientation\"},{\"id\":53,\"$$hashKey\":\"02U\",\"value\":\"Concern for Quality\"},{\"id\":54,\"$$hashKey\":\"02V\",\"value\":\"Developing Others\"},{\"id\":55,\"$$hashKey\":\"02W\",\"value\":\"Innovation\"},{\"id\":56,\"$$hashKey\":\"02X\",\"value\":\"Leadership\"},{\"id\":57,\"$$hashKey\":\"02Y\",\"value\":\"Managing Performance\"},{\"id\":58,\"$$hashKey\":\"02Z\",\"value\":\"Relationship Building\"},{\"id\":59,\"$$hashKey\":\"030\",\"value\":\"Thinking skills\"}],\"counterSigning\":{\"comment\":\"dddd\"},\"completecyProfile\":{\"total_company_weightage\":60,\"overall_rate\":0,\"total_weightage\":60,\"total_rate\":0,\"companyCoreValue\":[{\"weightage\":12,\"self_rating\":0,\"core_value\":\"Accountability\",\"rating\":0,\"$$hashKey\":\"00Y\",\"supervisor_comment\":\"\"},{\"weightage\":12,\"self_rating\":0,\"core_value\":\"Teamwork\",\"rating\":0,\"$$hashKey\":\"00Z\",\"supervisor_comment\":\"\"},{\"weightage\":12,\"self_rating\":0,\"core_value\":\"Result Orientation Performance\",\"rating\":0,\"$$hashKey\":\"010\",\"supervisor_comment\":\"\"},{\"weightage\":12,\"self_rating\":0,\"core_value\":\"Customer Focus\",\"rating\":0,\"$$hashKey\":\"011\",\"supervisor_comment\":\"\"},{\"weightage\":12,\"self_rating\":0,\"core_value\":\"Loyalty Integrity\",\"rating\":0,\"$$hashKey\":\"012\",\"supervisor_comment\":\"\"}],\"departmentCoreValue\":[{\"weightage\":0,\"self_rating\":\"2\",\"core_value\":\"54\",\"rating\":0,\"$$hashKey\":\"018\",\"supervisor_comment\":\"\"},{\"weightage\":0,\"self_rating\":\"3.5\",\"core_value\":\"57\",\"rating\":0,\"$$hashKey\":\"03K\",\"supervisor_comment\":\"\"}],\"total_department_weightage\":0},\"performanceScaleDef\":[{\"definition\":\"Exceeds all expectations   makes positive contributions   adds value beyond scope\",\"title\":\"Outstanding (85% & above)\",\"$$hashKey\":\"06C\"},{\"definition\":\"Meets all expectations   exceeds in most areas   adds value beyond scope\",\"title\":\"Very Good (70% - 84%)\",\"$$hashKey\":\"06D\"},{\"definition\":\"Meets all expectations   exceeds in some areas\",\"title\":\"Good (60% - 69%)\",\"$$hashKey\":\"06E\"},{\"definition\":\"Just able to meet expectations & required standards\",\"title\":\"Fair (50% - 59%)\",\"$$hashKey\":\"06F\"},{\"definition\":\"Unable to meet expectations & required standards\",\"title\":\"Poor (49% & below)\",\"$$hashKey\":\"06G\"}],\"ratingScale\":[{\"rating\":5,\"definition\":\"Exceptional\",\"$$hashKey\":\"008\"},{\"rating\":4,\"definition\":\"Exceeded Expectations\",\"$$hashKey\":\"009\"},{\"rating\":3,\"definition\":\"Meets Expectations\",\"$$hashKey\":\"00A\"},{\"rating\":2,\"definition\":\"Improvement Needed\",\"$$hashKey\":\"00B\"},{\"rating\":1,\"definition\":\"Unacceptable\",\"$$hashKey\":\"00C\"},{\"rating\":0,\"definition\":\"Under Performed\",\"$$hashKey\":\"00D\"}],\"ratingValues\":[0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5],\"action\":\"save\",\"performanceProgress\":{\"reason\":\"a\",\"indicate_learning\":\"c\",\"barriers\":\"b\",\"appraisee_comment\":\"d\"},\"overallPerformance\":{\"fullYear\":2017,\"month\":\"1\",\"jobWeightage\":{\"indKPIWeightage\":0,\"overallWeightage\":0,\"pdWeightage\":0,\"comWeightage\":0},\"individual\":{\"overall\":0,\"period2\":\"-\",\"period1\":0},\"year\":\"0\",\"competency\":{\"overall\":0,\"period2\":\"-\",\"period1\":0},\"overall\":{\"overall\":0,\"period2\":\"-\",\"period1\":0},\"comment\":\"\",\"president\":{\"overall\":0,\"period2\":\"-\",\"period1\":0}}}";
//
//        try{
//            JSONParser jsonParse = new JSONParser();
//            JSONObject json = (JSONObject)jsonParse.parse(strJson);
//            System.out.println(json.get("action"));
//            JSONObject com = (JSONObject)json.get("completecyProfile");
//            JSONArray arr = (JSONArray)com.get("companyCoreValue");
//            for(int i=0;i<arr.size();i++){
//                final JSONObject obj = (JSONObject)arr.get(i);
//                System.out.println(obj.get("self_rating"));
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
