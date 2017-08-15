package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;
import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;

import com.rclgroup.dolphin.web.common.RrcStandardUim;
import com.rclgroup.dolphin.web.dao.cam.CamFscDao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMFormDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownCompanyMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;
import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmListMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.io.IOException;

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

public class PmsPMFormSvc extends RrcStandardSvc {
    public PmsPMFormSvc() {
        super();
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        try {

            String strTarget = null;
            HttpSession session = request.getSession(false);
            
            // <-- Update last access time
            boolean isAjaxPoll =
                "Y".equalsIgnoreCase(request.getHeader(PmsConstant.AJAX_POLL_KEY));

            if (session != null && !isAjaxPoll) {
                session.setAttribute(PmsConstant.LAST_ACCESS_TIME_KEY,
                                     new Date().getTime());
            }
            // -->
            
            // Clear status message.
            session.setAttribute("statusCode", 0);
            session.setAttribute("statusMessage", "");
            //String strPageAction = RutString.nullToStr(request.getParameter("pageAction"));
            String strPart = RutString.nullToStr(request.getParameter("part"));
        System.out.println("** part=" + strPart);
            //            PmsCommonUim uim =
            //                (PmsCommonUim)session.getAttribute("pmsCommonUim");
            //              if (uim == null) {
            //                uim = new PmsCommonUim();
            //              pmsCommon2PMUim.setRcmConstantDao((RcmConstantDao)getBean("rcmConstantDao"));
            //              pmsCommon2PMUim.setCamFscDao((CamFscDao)getBean("camFscDao"));
            //                // Manage the attributes of User: line, region, agent, fsc
            //                RcmUserBean userBean =
            //                    (RcmUserBean)session.getAttribute("userBean");
            //                this.manageUserBean(uim, userBean);
            //                pmsCommon2PMUim.setPmsCommonDao((PmsCommonDao)getBean("pmsCommonDao"));
            //                pmsCommon2PMUim.setPmsCommon2Dao((PmsCommon2Dao)getBean("pmsCommon2Dao"));
            //                String strPermissionUser = this.getPermissionUserCode(uim);
            //                session.setAttribute("pmsCommonUim", uim);
            //              }
            PmsCommonUim pmsCommon2PMUim = (PmsCommonUim) session.getAttribute("pmsCommon2PMUim");
            if (pmsCommon2PMUim == null) {
                pmsCommon2PMUim = new PmsCommonUim();
                pmsCommon2PMUim.setRcmConstantDao((RcmConstantDao) getBean("rcmConstantDao"));
                pmsCommon2PMUim.setCamFscDao((CamFscDao) getBean("camFscDao"));
                // Manage the attributes of User: line, region, agent, fsc
                RcmUserBean userBean = (RcmUserBean) session.getAttribute("userBean");

                this.manageUserBean(pmsCommon2PMUim, userBean);
                pmsCommon2PMUim.setPmsCommonDao((PmsCommonDao) getBean("pmsCommonDao"));
                pmsCommon2PMUim.setPmsCommon2Dao((PmsCommon2Dao) getBean("pmsCommon2Dao"));
                String strPermissionUser = this.getPermissionUserCode(pmsCommon2PMUim);
                session.setAttribute("pmsCommon2PMUim", pmsCommon2PMUim);
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
                uimPMForm.setPmsCommonDao((PmsCommonDao) getBean("pmsCommonDao"));
                String strPermissionUser = this.getPermissionUserCode(uimPMForm);
                session.setAttribute("pmsPMUim", uimPMForm);

            }
            //getPmList
            if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADMYSTAFF)) {
                try {
                    Map hm = pmsCommon2PMUim.getActiveYearPeriod();
                    int yearaActive = (Integer) hm.get("year");
                    int periodActive = (Integer) hm.get("period");
                    String selectedYearStr = RutString.nullToStr((String) request.getParameter("selectedYear"));
                    if (selectedYearStr.equals("")) {
                        selectedYearStr = yearaActive + "";
                    }
                    String pmperiod = RutString.nullToStr((String) request.getParameter("pmperiod"));
                    if (pmperiod.equals("")) {
                        pmperiod = periodActive + "";
                    }

                    String selectedStatus = RutString.nullToStr((String) request.getParameter("selectedStatus"));
                    String findName = RutString.nullToStr((String) request.getParameter("findName"));
                    String division = RutString.nullToStr((String) request.getParameter("division"));
                    String company = RutString.nullToStr((String) request.getParameter("company"));
                    String department = RutString.nullToStr((String) request.getParameter("department"));
                    String section = RutString.nullToStr((String) request.getParameter("section"));
                    String jobGrade = RutString.nullToStr((String) request.getParameter("jobGrade"));
                    String jobBrand = RutString.nullToStr((String) request.getParameter("jobBrand"));
                    String sortBy = RutString.nullToStr((String) request.getParameter("cmbSortBy"));
                    String sortDirection = RutString.nullToStr((String) request.getParameter("cmbSortDirection"));
                    String displayLength = RutString.nullToStr((String) request.getParameter("displayLength"));
                    String pageNo = RutString.nullToStr((String) request.getParameter("pageNo"));
                    String mngId = RutString.nullToStr((String) request.getParameter("mngId"));

                    String findDesignation = RutString.nullToStr((String) request.getParameter("findDesignation"));


                    PmCriteria pmCriteria = new PmCriteria();
                    if (!selectedYearStr.equals("")) {
                        pmCriteria.setPmYear(Integer.parseInt(selectedYearStr));
                    }
                    if (!pmperiod.equals("")) {
                        pmCriteria.setPmPeriod(Integer.parseInt(pmperiod));
                    } else {
                        pmCriteria.setPmPeriod(1);
                    }
                    pmCriteria.setPmStatus(selectedStatus);
                    String pmKey = (String) context.getAttribute(PmsConstant.PM_KEY);
                    pmCriteria.setPmKey(pmKey);
                    
                    if (!division.equals("")) {
                        pmCriteria.setDivision(Integer.parseInt(division));
                    }

                    if (!company.equals("")) {
                        pmCriteria.setCompany(Integer.parseInt(company));
                    }
                    if (!department.equals("")) {
                        pmCriteria.setDepartment(Integer.parseInt(department));
                    }
                    if (!section.equals("")) {
                        pmCriteria.setSection(Integer.parseInt(section));
                    }
                    if (displayLength.equals("")) {
                        pmCriteria.setIDisplayLength(10);
                    } else {
                        pmCriteria.setIDisplayLength(Integer.parseInt(displayLength));
                    }
                    if (pageNo.equals("")) {
                        pmCriteria.setPageNo(1);
                    } else {
                        pmCriteria.setPageNo(Integer.parseInt(pageNo));
                    }
                    if (sortBy.equals("")) {
                        pmCriteria.setDefaultSortBy("PH.PM_YEAR");
                    } else {
                        pmCriteria.setDefaultSortBy(sortBy);
                    }
                    if (sortDirection.equals("")) {
                        pmCriteria.setDefaultSortDirection("ASC");
                    } else {
                        pmCriteria.setDefaultSortDirection(sortDirection);
                    }
                    if (!mngId.equals("")) {
                        pmCriteria.setMngrId(Long.parseLong(mngId));
                    }

                    pmCriteria.setEmpId(null);
                    pmCriteria.setJobGrade(jobGrade);
                    pmCriteria.setJobBrand(jobBrand);
                    pmCriteria.setDesignation(findDesignation);
                    pmCriteria.setEmpName(findName);

                    //                            pmCriteria.setDefaultSortBy(sortBy);
                    //                            pmCriteria.setDefaultSortDirection(sortDirection);
                    //            pmCriteria.setDefaultSortDirection(sortDirection);
                    

                   

                    JSONObject responseJson = new JSONObject();
                    JSONObject data = uimPMForm.getPMList(pmCriteria);
                    uimPMForm.setPmCriteria(pmCriteria);

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
            }else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_EMPPERFRES)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    //System.out.println("RequesterSelected Yer is:= "+request.getParameter("selectedYearEmpPerfRes"));
                    System.out.println("RequesterSelected Yer is:= "+request.getParameter("selectedYear"));
                    
                    int year = Integer.parseInt(request.getParameter("selectedYear"));
                    int company;
                    if(request.getParameter("company").equalsIgnoreCase("") || request.getParameter("company").equalsIgnoreCase("ALL"))
                      company =-1;
                    else
                     company = Integer.parseInt(request.getParameter("company"));
                    int dept;
                    if(request.getParameter("department").equalsIgnoreCase("") || request.getParameter("department").equalsIgnoreCase("ALL"))
                        dept=-1;
                     else
                         dept= Integer.parseInt(request.getParameter("department"));
                    
                    String jobBand = RutString.nullToStr((String) request.getParameter("jobBrand"));
                    String compRange = RutString.nullToStr((String) request.getParameter("resByCompetency"));
                    String indKPIRange = RutString.nullToStr((String) request.getParameter("resByIndKPIs"));
                    
                    JSONArray data =
                     pmsCommon2PMUim.getEmpPerfResAsJSON(year,company,dept,jobBand,0, compRange,indKPIRange);

                    responseJson.put("data", data);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    responseJson.put("data", "");
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADACTIVITIES)) {
                try {


                    //                            pmCriteria.setDefaultSortBy(sortBy);
                    //                            pmCriteria.setDefaultSortDirection(sortDirection);
                    //            pmCriteria.setDefaultSortDirection(sortDirection);

                    context.getAttribute(PmsConstant.PM_KEY);
                    String year = RutString.nullToStr((String) request.getParameter("year"));
                    if (year.equals("")) {
                        Map hm = pmsCommon2PMUim.getActiveYearPeriod();
                        // Map getActive()

                        int yearaActive = (Integer) hm.get("year");
                        //int periodActive= (Integer)hm.get("period");
                        year = yearaActive + "";
                    }
                    JSONObject responseJson = new JSONObject();
                    //getActivityList JSONArray
                    JSONArray data =
                     pmsCommon2PMUim.getActivityListAsJSON(uimPMForm.getPkEmpSeqNo(), Integer.parseInt(year));

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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADMYPM)) {
                try {

                    String selectedYearStr = RutString.nullToStr((String) request.getParameter("selectedYear"));

                    String pmperiod = RutString.nullToStr((String) request.getParameter("pmperiod"));
                    String selectedStatus = RutString.nullToStr((String) request.getParameter("selectedStatus"));
                    String findName = RutString.nullToStr((String) request.getParameter("findName"));
                    String division = RutString.nullToStr((String) request.getParameter("division"));
                    String company = RutString.nullToStr((String) request.getParameter("company"));
                    String department = RutString.nullToStr((String) request.getParameter("department"));
                    String section = RutString.nullToStr((String) request.getParameter("section"));
                    String jobGrade = RutString.nullToStr((String) request.getParameter("jobGrade"));
                    String jobBrand = RutString.nullToStr((String) request.getParameter("jobBrand"));
                    String sortBy = RutString.nullToStr((String) request.getParameter("cmbSortBy"));
                    String sortDirection = RutString.nullToStr((String) request.getParameter("cmbSortDirection"));
                    String displayLength = RutString.nullToStr((String) request.getParameter("displayLength"));
                    String pageNo = RutString.nullToStr((String) request.getParameter("pageNo"));


                    String findDesignation = RutString.nullToStr((String) request.getParameter("findDesignation"));


                    PmCriteria pmCriteria = new PmCriteria();
                    if (!selectedYearStr.equals("")) {
                        pmCriteria.setPmYear(Integer.parseInt(selectedYearStr));
                    }
                    if (!pmperiod.equals("")) {
                        pmCriteria.setPmPeriod(Integer.parseInt(pmperiod));
                    }
                    pmCriteria.setPmStatus(selectedStatus);
                    String pmKey = (String) context.getAttribute(PmsConstant.PM_KEY);

                    pmCriteria.setPmKey(pmKey);
                    if (!division.equals("")) {
                        pmCriteria.setDivision(Integer.parseInt(division));
                    }

                    if (!company.equals("")) {
                        pmCriteria.setCompany(Integer.parseInt(company));
                    }
                    if (!department.equals("")) {
                        pmCriteria.setDepartment(Integer.parseInt(department));
                    }
                    if (!section.equals("")) {
                        pmCriteria.setSection(Integer.parseInt(section));
                    }
                    if (displayLength.equals("")) {
                        pmCriteria.setIDisplayLength(10);
                    } else {
                        pmCriteria.setIDisplayLength(Integer.parseInt(displayLength));
                    }
                    if (pageNo.equals("")) {
                        pmCriteria.setPageNo(1);
                    } else {
                        pmCriteria.setPageNo(Integer.parseInt(pageNo));
                    }
                    if (sortBy.equals("")) {
                        pmCriteria.setDefaultSortBy("PH.PM_YEAR");
                    } else {
                        pmCriteria.setDefaultSortBy((sortBy));
                    }
                    if (sortDirection.equals("")) {
                        pmCriteria.setDefaultSortDirection("ASC");
                    } else {
                        pmCriteria.setDefaultSortDirection((sortDirection));
                    }


                    pmCriteria.setEmpId(uimPMForm.getPkEmpSeqNo());
                    pmCriteria.setJobGrade(jobGrade);
                    pmCriteria.setJobBrand(jobBrand);
                    pmCriteria.setDesignation(findDesignation);
                    pmCriteria.setEmpName(findName);
                    //                            pmCriteria.setDefaultSortBy(sortBy);
                    //                            pmCriteria.setDefaultSortDirection(sortDirection);
                    //            pmCriteria.setDefaultSortDirection(sortDirection);


                    //String pmKey = (String) context.getAttribute(PmsConstant.PM_KEY);

                    pmCriteria.setPmKey(pmKey);
                    JSONObject responseJson = new JSONObject();
                    JSONObject data = uimPMForm.getPMList(pmCriteria);


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
            } else
            //start loading all data in this page
            if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADPROFILE)) {
                try {

                    // Map getActive()
                    //Map getActive()
                    String selectedYearStr = RutString.nullToStr((String) request.getParameter("selectedYear"));

                    String pmperiod = RutString.nullToStr((String) request.getParameter("pmperiod"));
                    Map hm = pmsCommon2PMUim.getActiveYearPeriod();
                    int yearaActive = (Integer) hm.get("year");
                    int periodActive = (Integer) hm.get("period");

                    PmsPMUim uimPMFormTmp = (PmsPMUim) session.getAttribute("pmsPMUim");
                    String userLevel;
                    String empId;
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item = new JSONObject();
                    List<CrmProfileMod> list = pmsCommon2PMUim.getProfileType();
                    Calendar now = Calendar.getInstance(); // Gets the current date and time
                    // String yearToday = now.get(Calendar.YEAR)+"";
                    for (CrmProfileMod crmProfileMod : list) {
                        item = new JSONObject();
                        userLevel = crmProfileMod.getUserLevel();
                        empId = crmProfileMod.getEmpId();

                        uimPMFormTmp.setEmpID(Long.parseLong(empId));
                        uimPMFormTmp.setUserLevel(userLevel);
                        uimPMFormTmp.setPkEmpSeqNo(Long.parseLong(crmProfileMod.getPkEmpSeqno()));
                        item.put("empId", crmProfileMod.getEmpId());
                        item.put("empName", crmProfileMod.getEmpName());
                        item.put("designation", crmProfileMod.getDesignation());
                        item.put("jobGradeName", crmProfileMod.getJobGradeName());
                        item.put("jobBrandName", crmProfileMod.getJobBrandName());
                        item.put("jobGrade", crmProfileMod.getJobGrade());
                        item.put("jobBrand", crmProfileMod.getJobBrand());
                        String department =
                            crmProfileMod.getComName() + "/" + crmProfileMod.getDivName() + "/" +
                            crmProfileMod.getDepName() + "/" + crmProfileMod.getSecName();
                        item.put("department", department);
                        item.put("year", selectedYearStr + '/' + pmperiod);
                        item.put("addNewPm", crmProfileMod.getFlagCreate());
                        item.put("selectedYear", selectedYearStr);
                        item.put("pmperiod", pmperiod);

                        //need change
                        item.put("yearActive", yearaActive + "");
                        item.put("periodActive", periodActive);
                        item.put("mngId", crmProfileMod.getPkEmpSeqno());

                        //                    Department
                        //                    Year
                        //                    Status
                        item.put("flagCreate", crmProfileMod.getFlagCreate());
                        item.put("userLevel", crmProfileMod.getUserLevel());
                        item.put("myStaffCount", crmProfileMod.getMyStaffCount());
                        data.add(item);
                    }
                    session.setAttribute("pmsPMUim", uimPMFormTmp);
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
            }
            //            else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADMYSTAFF)) {
            //              try {
            //                String selectedYearStr =
            //                    RutString.nullToStr((String)request.getParameter("selectedYear"));
            //
            //                String pmperiod =
            //                    RutString.nullToStr((String)request.getParameter("pmperiod"));
            //                String selectedStatus =
            //                    RutString.nullToStr((String)request.getParameter("selectedStatus"));
            //                String findName =
            //                    RutString.nullToStr((String)request.getParameter("findName"));
            //                String division =
            //                    RutString.nullToStr((String)request.getParameter("division"));
            //                String company =
            //                    RutString.nullToStr((String)request.getParameter("company"));
            //                String department =
            //                    RutString.nullToStr((String)request.getParameter("department"));
            //                String section =
            //                    RutString.nullToStr((String)request.getParameter("section"));
            //                String jobGrade =
            //                    RutString.nullToStr((String)request.getParameter("jobGrade"));
            //                String jobBrand =
            //                    RutString.nullToStr((String)request.getParameter("jobBrand"));
            //                String sortBy =
            //                    RutString.nullToStr((String)request.getParameter("cmbSortBy"));
            //                String sortDirection =
            //                    RutString.nullToStr((String)request.getParameter("cmbSortDirection"));
            //                String findDesignation =
            //                        RutString.nullToStr((String)request.getParameter("findDesignation"));
            //                PmCriteria pmCriteria = new PmCriteria();
            //                if(!selectedYearStr.equals("")){
            //                  pmCriteria.setPmYear(Integer.parseInt (selectedYearStr) );
            //                }
            //                if(!pmperiod.equals("")){
            //                  pmCriteria.setPmPeriod(Integer.parseInt (pmperiod) );
            //                }
            //                pmCriteria.setPmStatus(selectedStatus);
            //                if(!division.equals("")){
            //                  pmCriteria.setDivision(Integer.parseInt (division) );
            //                }
            //
            //                if(!company.equals("")){
            //                  pmCriteria.setCompany(Integer.parseInt (company) );
            //                }
            //                if(!department.equals("")){
            //                  pmCriteria.setDepartment(Integer.parseInt (department) );
            //                }
            //                if(!section.equals("")){
            //                  pmCriteria.setSection(Integer.parseInt (section) );
            //                }
            //                pmCriteria.setJobGrade(jobGrade);
            //                pmCriteria.setJobGrade(jobBrand);
            //                pmCriteria.setDesignation(findDesignation);
            //                pmCriteria.setEmpName(findName);
            //                pmCriteria.setDefaultSortBy(sortBy);
            //                pmCriteria.setDefaultSortDirection(sortDirection);
            //
            //                JSONObject responseJson = new JSONObject();
            //                JSONArray data = new JSONArray();
            //                data= uimPMForm.getPMList(pmCriteria);
            //                responseJson.put("data", data);
            //                response.setContentType("application/json");
            //                response.getWriter().println(responseJson.toString());
            //              } catch (Exception ex) {
            //                  ex.printStackTrace();
            //                  int totalRowCount = 0;
            //                  JSONObject responseJson = new JSONObject();
            //                  JSONArray data = new JSONArray();
            //                  JSONObject item;
            //                  responseJson.put("data", "");
            //                  response.setContentType("application/json");
            //                  response.getWriter().println(responseJson.toString());
            //              }
            //            }
            //
            else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADMYPM)) {
                try {
                    String selectedYearStr = RutString.nullToStr((String) request.getParameter("selectedYear"));

                    String pmperiod = RutString.nullToStr((String) request.getParameter("pmperiod"));

                    String sortBy = RutString.nullToStr((String) request.getParameter("cmbSortBy"));
                    String sortDirection = RutString.nullToStr((String) request.getParameter("cmbSortDirection"));
                    String displayLenghtList = RutString.nullToStr((String) request.getParameter("displayLenghtList"));
                    //PAGE_NO
                    String pageNo = RutString.nullToStr((String) request.getParameter("pageNo"));

                    PmCriteria pmCriteria = new PmCriteria();
                    if (!selectedYearStr.equals("")) {
                        pmCriteria.setPmYear(Integer.parseInt(selectedYearStr));
                    }
                    if (!pmperiod.equals("")) {
                        pmCriteria.setPmPeriod(Integer.parseInt(pmperiod));
                    }
                    if (!pageNo.equals("")) {
                        pmCriteria.setPmPeriod(Integer.parseInt(pageNo));
                    }
                    // P_I_SORT := 'PH.PM_YEAR';
                    // P_I_ASC_DESC := 'ASC';
                    pmCriteria.setDefaultSortBy("PH.PM_YEAR");
                    pmCriteria.setDefaultSortDirection("ASC");
                    if (displayLenghtList.equals("")) {
                        pmCriteria.setIDisplayLength(10);
                    } else {
                        pmCriteria.setIDisplayLength(Integer.parseInt(displayLenghtList));
                    }

                    JSONObject responseJson = new JSONObject();
                    JSONObject data = new JSONObject();
                    String pmKey = (String) context.getAttribute(PmsConstant.PM_KEY);
                    pmCriteria.setPmKey(pmKey);
                    data = uimPMForm.getPMList(pmCriteria);
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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADJOBGRADE)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    List<CrmDropDownItemMod> list = pmsCommon2PMUim.getJobGradeList();
                    for (CrmDropDownItemMod crmDropDownItemMod : list) {
                        item = new JSONObject();
                        item.put("value", crmDropDownItemMod.getValue());
                        item.put("des", crmDropDownItemMod.getDescription());
                        data.add(item);
                    }
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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADCOMPANY)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    //userLevel
                    //                  String userLevel= uimPMForm.getUserLevel();
                    //                  if(userLevel.equals("")){
                    //                    n('ADMIN','HR')
                    //
                    //                  }
                  //  uimPMForm.setUserLevel("ADMIN");
                    if (uimPMForm.getUserLevel().equals("ADMIN") || uimPMForm.getUserLevel().equals("HR")) {
                        item = new JSONObject();
                        item.put("value", "All");
                        item.put("des", "");
                        item.put("des2", "");
                        data.add(item);
                    }

                    List<CrmDropDownCompanyMod> list = pmsCommon2PMUim.getCompanyList();
                    for (CrmDropDownCompanyMod crmDropDownItemMod : list) {
                        item = new JSONObject();
                        item.put("value", crmDropDownItemMod.getValue());
                        item.put("des", crmDropDownItemMod.getDescription());
                        item.put("des2", crmDropDownItemMod.getDescription2());
                        data.add(item);
                    }
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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADDIVISION)) {
                try {
                    String comSeqno = RutString.nullToStr((String) request.getParameter("comSeqno"));
                    Integer comSeqnoInt = null;
                    if (!comSeqno.equals("")) {
                        comSeqnoInt = Integer.parseInt(comSeqno);
                    }
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;

                    if (uimPMForm.getUserLevel().equals("ADMIN") || uimPMForm.getUserLevel().equals("HR")) {
                        // if( !uimPMForm.getUserLevel().equals("ADMIN")  && !uimPMForm.getUserLevel().equals("HR")  ){
                        item = new JSONObject();
                        item.put("value", "");
                        item.put("des", "All");
                        data.add(item);
                    }

                    List<CrmDropDownItemMod> list = pmsCommon2PMUim.getDivisionList(comSeqnoInt);
                    for (CrmDropDownItemMod crmDropDownItemMod : list) {
                        item = new JSONObject();
                        item.put("value", crmDropDownItemMod.getValue());
                        item.put("des", crmDropDownItemMod.getDescription());

                        data.add(item);
                    }
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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADDEPARTMENT)) {
                try {
                    String comSeqno = RutString.nullToStr((String) request.getParameter("comSeqno"));
                    Integer comSeqnoInt = null;
                    if (!comSeqno.equals("")) {
                        comSeqnoInt = Integer.parseInt(comSeqno);
                    }
                    String divSeqno = RutString.nullToStr((String) request.getParameter("divSeqno"));
                    Integer divSeqnoInt = null;
                    if (!divSeqno.equals("")) {
                        divSeqnoInt = Integer.parseInt(divSeqno);
                    }

                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                 //  uimPMForm.setUserLevel("ADMIN");
                    //if( !uimPMForm.getUserLevel().equals("ADMIN")  && !uimPMForm.getUserLevel().equals("HR")  ){
                    if (uimPMForm.getUserLevel().equals("ADMIN") || uimPMForm.getUserLevel().equals("HR")) {

                        item = new JSONObject();
                        item.put("value", "");
                        item.put("des", "All");
                        data.add(item);
                    }

                    List<CrmDropDownItemMod> list = pmsCommon2PMUim.getDepartmentList(comSeqnoInt, divSeqnoInt);
                    for (CrmDropDownItemMod crmDropDownItemMod : list) {
                        item = new JSONObject();
                        item.put("value", crmDropDownItemMod.getValue());
                        item.put("des", crmDropDownItemMod.getDescription());

                        data.add(item);
                    }
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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADSECTION)) {
                try {
                    String comSeqno = RutString.nullToStr((String) request.getParameter("comSeqno"));
                    Integer comSeqnoInt = null;
                    if (!comSeqno.equals("")) {
                        comSeqnoInt = Integer.parseInt(comSeqno);
                    }
                    String divSeqno = RutString.nullToStr((String) request.getParameter("divSeqno"));
                    Integer divSeqnoInt = null;
                    if (!divSeqno.equals("")) {
                        divSeqnoInt = Integer.parseInt(divSeqno);
                    }
                    String depSeqno = RutString.nullToStr((String) request.getParameter("depSeqno"));
                    Integer depSeqnoInt = null;
                    if (!depSeqno.equals("")) {
                        depSeqnoInt = Integer.parseInt(depSeqno);
                    }

                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    if (uimPMForm.getUserLevel().equals("ADMIN") || uimPMForm.getUserLevel().equals("HR")) {

                        item = new JSONObject();
                        item.put("value", "");
                        item.put("des", "All");
                        data.add(item);
                    }
                    List<CrmDropDownItemMod> list =
                        pmsCommon2PMUim.getSectionList(comSeqnoInt, divSeqnoInt, depSeqnoInt);
                    for (CrmDropDownItemMod crmDropDownItemMod : list) {
                        if (!crmDropDownItemMod.getValue().equals("")) {
                            item = new JSONObject();
                            item.put("value", crmDropDownItemMod.getValue());
                            item.put("des", crmDropDownItemMod.getDescription());

                            data.add(item);
                        }
                    }
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
            }

            else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADJOBBRAND)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    List<CrmDropDownItemMod> list = pmsCommon2PMUim.getJobBrandList();
                    for (CrmDropDownItemMod crmDropDownItemMod : list) {
                        item = new JSONObject();
                        item.put("value", crmDropDownItemMod.getValue());
                        item.put("des", crmDropDownItemMod.getDescription());
                        data.add(item);
                    }
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
            } else if (strPart.equalsIgnoreCase(PmsConstant.PART_ACTION_PMFORM_LOADYEAR)) {
                try {
                    JSONObject responseJson = new JSONObject();
                    JSONArray data = new JSONArray();
                    JSONObject item;
                    List<CrmDropDownYearMod> list = pmsCommon2PMUim.getYearList();
                    for (CrmDropDownYearMod crmDropDownYearMod : list) {
                        item = new JSONObject();
                        item.put("value", crmDropDownYearMod.getValue());
                        item.put("des", crmDropDownYearMod.getValue());
                        data.add(item);
                    }
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
            } else if (strPart.equalsIgnoreCase("exportExcel")) {
                PmCriteria criteria = uimPMForm.getPmCriteria();
                if (criteria != null) {
                    String reportURL = uimPMForm.getPmsCommonDao().getReportURL();
                    String strPMKey = (String) context.getAttribute(PmsConstant.PM_KEY);
                    String strSort = criteria.getDefaultSortBy();
                    String strSortAscDesc = criteria.getDefaultSortDirection();
                    Long nEmpSeqNo = criteria.getEmpId();
                    Integer nPMYear = criteria.getPmYear();
                    Integer nPMPeriod = criteria.getPmPeriod();
                    String strPMStatus = criteria.getPmStatus();
                    String strEmpName = criteria.getEmpName();
                    Long nCompanySeqNo = null;
                    if (criteria.getCompany() != null) {
                        nCompanySeqNo = new Long(criteria.getCompany());
                    }
                    Long nDivSeqNo = null;
                    if (criteria.getDivision() != null) {
                        nDivSeqNo = new Long(criteria.getDivision());
                    }
                    Long nDevSeqNo = null;
                    if (criteria.getDepartment() != null) {
                        nDevSeqNo = new Long(criteria.getDepartment());
                    }
                    Long nSecSeqNo = null;
                    if (criteria.getSection() != null) {
                        nSecSeqNo = new Long(criteria.getSection());
                    }
                    String strJobGrade = criteria.getJobGrade();
                    
                    String strJobBrand = criteria.getJobBrand();
                    
                    String strDesignation = criteria.getDesignation();
                    Long nMngrSeqNo = null;
                    if (criteria.getMngrId() != null) {
                        nMngrSeqNo = new Long(criteria.getMngrId());
                    }
                    exportExcel(uimPMForm, request, response, context, reportURL, strPMKey, strSort, strSortAscDesc,
                                nEmpSeqNo, nPMYear, nPMPeriod, strPMStatus, strEmpName, nCompanySeqNo, nDivSeqNo,
                                nDevSeqNo, nSecSeqNo, strJobGrade, strJobBrand, strDesignation, nMngrSeqNo);
                } else {
                    // Error no search criteria found
                    String errorMsg = "No PM Search my staff found";
                    System.err.println(errorMsg);
                    String statusMessage = "Export Excel error cause:" + errorMsg;
                    int statusCode = PmsConstant.STATUS_ERROR;

                    JSONObject responseJson = new JSONObject();
                    responseJson.put("statusCode", statusCode);
                    responseJson.put("statusMessage", statusMessage);
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                }
            }


        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Export Excel Report.
     * @param uim
     * @param request
     * @param response
     * @param context
     * @throws IOException
     */
    private void exportExcel(RrcStandardUim uim, HttpServletRequest request, HttpServletResponse response,
                             ServletContext context, final String reportURL, final String strPMKey,
                             final String strSort, final String strSortAscDesc, final Long nEmpSeqNo,
                             final Integer nPMYear, final Integer nPMPeriod, final String strPMStatus,
                             final String strEmpName, final Long nCompanySeqNo, final Long nDivSeqNo,
                             final Long nDevSeqNo, final Long nSecSeqNo, final String nJobGrade, final String nJobBrand,
                             final String strDesignation, final Long nMngrSeqNo) throws IOException {
        HttpSession session = request.getSession(false);
        String strReportName = "PMS01.rdf";
        String strReportFormat = "SPREADSHEET";
        String strReportUrl = reportURL;

        try {
            String strUserID = uim.getPrsnLogIdOfUser();

            StringBuilder sb = new StringBuilder();
            sb.append(strReportUrl);
            sb.append("report=").append(strReportName);
            sb.append("&desformat=").append(strReportFormat);
            sb.append("&P_I_USER_ID=").append(RutString.nullToStr(strUserID));
            sb.append("&P_I_PM_KEY=").append(RutString.nullToStr(strPMKey));
            sb.append("&P_I_SORT=").append(RutString.nullToStr(strSort));
            sb.append("&P_I_ASC_DESC=").append(RutString.nullToStr(strSortAscDesc));
            sb.append("&P_I_EMP_ID=").append(RutString.nullToStr(nEmpSeqNo));
            sb.append("&P_I_PM_YEAR=").append(RutString.nullToStr(nPMYear));
            sb.append("&P_I_PM_PERIOD=").append(RutString.nullToStr(nPMPeriod));
            sb.append("&P_I_PM_STATUS=").append(RutString.nullToStr(strPMStatus));
            sb.append("&P_I_EMP_NAME=").append(RutString.nullToStr(strEmpName));
            sb.append("&P_I_COMPANY=").append(RutString.nullToStr(nCompanySeqNo));
            sb.append("&P_I_DIVISION=").append(RutString.nullToStr(nDivSeqNo));
            sb.append("&P_I_DEPARTMENT=").append(RutString.nullToStr(nDevSeqNo));
            sb.append("&P_I_SECTION=").append(RutString.nullToStr(nSecSeqNo));
            sb.append("&P_I_JOB_GRADE=").append(RutString.nullToStr(nJobGrade));
            sb.append("&P_I_JOB_BRAND=").append(RutString.nullToStr(nJobBrand));
            sb.append("&P_I_DESIGNATION=").append(RutString.nullToStr(strDesignation));
            sb.append("&P_I_MNGR_ID=").append(RutString.nullToStr(nMngrSeqNo));

            JSONObject responseJson = new JSONObject();
            responseJson.put("statusCode", PmsConstant.STATUS_SUCCESS);
            responseJson.put("statusMessage", sb.toString());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());
        } catch (Exception ex) {
            String strErrorMsg = ex.getMessage();

            String statusMessage = "Export Excel error cause:" + strErrorMsg;
            int statusCode = PmsConstant.STATUS_ERROR;

            JSONObject responseJson = new JSONObject();
            responseJson.put("statusCode", statusCode);
            responseJson.put("statusMessage", statusMessage);
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());
        }
    }
}
