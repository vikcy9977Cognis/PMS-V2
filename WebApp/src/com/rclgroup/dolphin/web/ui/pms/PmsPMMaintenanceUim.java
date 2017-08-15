/*-----------------------------------------------------------------------------------------------------------
LoaCommonUim.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2016
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 28/02/16
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;
import com.rclgroup.dolphin.web.common.RrcStandardUim;


import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMForm2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMFormDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;
import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmFormMod;
import com.rclgroup.dolphin.web.model.pms.PmListMod;
import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsAttachFileMod;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMDetailMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;
import com.rclgroup.dolphin.web.model.pms.PmsPmHDRMod;
import com.rclgroup.dolphin.web.model.pms.PmsUserAuthorizeMod;
import com.rclgroup.dolphin.web.util.PmsFileUtil;
import com.rclgroup.dolphin.web.util.RutDate;
import com.rclgroup.dolphin.web.util.RutRequest;
import com.rclgroup.dolphin.web.util.RutString;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

import org.springframework.dao.DataAccessException;

public class PmsPMMaintenanceUim extends RrcStandardUim implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userLevel;
    private Long empID;
    private Long pkEmpSeqNo;
    private String mngrID;
    private PmsCommon2Dao pmsCommon2Dao;
    private PmsPMForm2Dao pmsPMForm2Dao;

    List<PmsAttachFileMod> attachFileList;
    HashMap<String, FileItem> fileItemMap = new HashMap<String, FileItem>();

    public PmsPMMaintenanceUim() {
        super();
    }

    public PmsCommon2Dao getPmsCommon2Dao() {
        return pmsCommon2Dao;
    }

    public void setPmsCommon2Dao(PmsCommon2Dao pmsCommon2Dao) {
        this.pmsCommon2Dao = pmsCommon2Dao;
    }

    public void setPmsPMForm2Dao(PmsPMForm2Dao pmsPMForm2Dao) {
        this.pmsPMForm2Dao = pmsPMForm2Dao;
    }

    public PmsPMForm2Dao getPmsPMForm2Dao() {
        return pmsPMForm2Dao;
    }

    public JSONObject getUserAuthorizeAsJSON(Long empSeqNo, Long pmHDRSeqNo) throws DataAccessException {

        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        PmsUserAuthorizeMod userAuthMod =
            this.pmsPMForm2Dao.getUserAuthorize(userID, empSeqNo, pmHDRSeqNo); //this.pmsPMForm2Dao.getUserAuthorize(userID, empSeqNo, pmHDRSeqNo)
        JSONObject ret = new JSONObject();
        ret.put("viewFlag", userAuthMod.getViewFlag());
        ret.put("editFlag", userAuthMod.getEditFlag());
        ret.put("submitFlag", userAuthMod.getSubmitFlag());
        ret.put("approveLv1Flag", userAuthMod.getApproveLv1Flag());
        ret.put("approveLv2Flag", userAuthMod.getApproveLv2Flag());
        return ret;

    }

    public PmsPmHDRMod getPMHeaderAsJSON(Long empSeqNo, String pmKey, Long pmHDRSeqNo) throws DataAccessException {

        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        PmsPmHDRMod pmHDR = this.pmsPMForm2Dao.getPMHDR(userID, empSeqNo, pmKey, pmHDRSeqNo);
        //        JSONObject ret = new JSONObject();
        //        ret.put("hdrSeqNo", pmHDR.getHdr_seqno());
        //        //        ret.put("pmStatus", pmHDR.getPm_status());
        //        ret.put("pmPeriod", pmHDR.getPm_period());
        //        ret.put("pmYear", pmHDR.getPm_year());
        //        ret.put("empName", pmHDR.getEmp_name());
        //        ret.put("empSeqNo", pmHDR.getEmp_seqno());
        //        ret.put("empId", pmHDR.getEmp_id());
        //        //        ret.put("designation", pmHDR.getDesignation());
        //        ret.put("joinDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getJoinDate()));
        //        ret.put("joinYear", pmHDR.getJoinYear());
        //        ret.put("joinPeriod", pmHDR.getJoinYear());
        //        ret.put("comSeqNo", pmHDR.getCom_seqno());
        //        ret.put("companyName", pmHDR.getCompanyName());
        //        ret.put("depSeqNo", pmHDR.getDep_seqno());
        //        ret.put("departmentName", pmHDR.getDepartmentName());
        //        ret.put("secSeqNo", pmHDR.getSec_seqno());
        //        ret.put("sectionName", pmHDR.getSectionName());
        //        ret.put("jobGrade", pmHDR.getJob_grade());
        //        ret.put("jobGradeName", pmHDR.getJob_grade_name());
        //        ret.put("jobBrand", pmHDR.getJob_brand());
        //        ret.put("jobBrandName", pmHDR.getJob_band_name());
        //
        //        ret.put("managerLv1", pmHDR.getManager_lv1());
        //        ret.put("approveLv1Name", pmHDR.getApprove_lv1_name());
        //        ret.put("managerLv2", pmHDR.getManager_lv2());
        //        ret.put("approveLv2Name", pmHDR.getApprove_lv2_name());
        //        ret.put("designation", pmHDR.getDesignation());
        //        ret.put("pmStatus", pmHDR.getPm_status());
        //        ret.put("comRating", pmHDR.getCom_rating());
        //        ret.put("comRatingPercent", pmHDR.getCom_rating_percent());
        //        ret.put("presidentRating", pmHDR.getPresident_rating());
        //        ret.put("presidentRatingPercent", pmHDR.getPresident_rating_percent());
        //        ret.put("individualRating", pmHDR.getIndividual_rating());
        //        ret.put("individualRatingPercent", pmHDR.getIndividual_rating_percent());
        //
        //        ret.put("part3Comment1", pmHDR.getPart3_comment1());
        //        ret.put("part3Comment2", pmHDR.getPart3_comment2());
        //        ret.put("part3Comment3", pmHDR.getPart3_comment3());
        //        ret.put("part3Comment4", pmHDR.getPart3_comment4());
        //        ret.put("submitBy", pmHDR.getSubmit_by());
        //        ret.put("submitDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getSubmit_date()));
        //
        //        ret.put("part4Comment1", pmHDR.getPart4_comment1());
        //
        //        ret.put("overallRating", pmHDR.getOverall_rating());
        //        ret.put("overallRatingPercent", pmHDR.getOverall_rating_percent());
        //
        //        ret.put("lv1ApproveBy", pmHDR.getLv1_approve_by());
        //        ret.put("lv1ApproveDate",  RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getLv1_approve_date()) );
        //
        //        ret.put("part5Comment1", pmHDR.getPart5_comment1());
        //
        //        ret.put("lv2ApproveBy", pmHDR.getLv2_approve_by());
        //        ret.put("lv2ApproveDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getLv2_approve_date()));
        //
        //        ret.put("revisionNo", pmHDR.getRevision_no());
        //        ret.put("recordStatus", pmHDR.getRecordStatus());
        //
        //        ret.put("divSeqno", pmHDR.getDiv_seqno());
        //        ret.put("divisionName", pmHDR.getDivisionName());
        //
        //        ret.put("performanceAppraisals",
        //                pmHDR.getPm_period() + "H Y" + pmHDR.getPm_year()); //  PM_PERIOD +'H'+ ' ' + 'Y' + PM_YEAR
        //        ret.put("designationAndDepartment",
        //                RutString.nullToStr(pmHDR.getDesignation())   + "/" + RutString.nullToStr(pmHDR.getCompanyName())   + "/" + RutString.nullToStr( pmHDR.getDivisionName())  + "/" +
        //                RutString.nullToStr(pmHDR.getDepartmentName())  + "/" + RutString.nullToStr(pmHDR.getSectionName()) );

        return pmHDR;
    }

    public JSONObject formatPMHeaderAsJSON(PmsPmHDRMod pmHDR) throws DataAccessException {

        //        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        //        PmsPmHDRMod pmHDR = this.pmsPMForm2Dao.getPMHDR(userID, empSeqNo, pmKey, pmHDRSeqNo);
        JSONObject ret = new JSONObject();
        ret.put("hdrSeqNo", pmHDR.getHdr_seqno());
        //        ret.put("pmStatus", pmHDR.getPm_status());
        ret.put("pmPeriod", pmHDR.getPm_period());
        ret.put("pmYear", pmHDR.getPm_year());
        ret.put("empName", pmHDR.getEmp_name());
        ret.put("empSeqNo", pmHDR.getEmp_seqno());
        ret.put("empId", pmHDR.getEmp_id());
        //        ret.put("designation", pmHDR.getDesignation());
        ret.put("joinDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getJoinDate()));
        ret.put("joinYear", pmHDR.getJoinYear());
        ret.put("joinPeriod", pmHDR.getJoinPeriod());
        ret.put("comSeqNo", pmHDR.getCom_seqno());
        ret.put("companyName", pmHDR.getCompanyName());
        ret.put("depSeqNo", pmHDR.getDep_seqno());
        ret.put("departmentName", pmHDR.getDepartmentName());
        ret.put("secSeqNo", pmHDR.getSec_seqno());
        ret.put("sectionName", pmHDR.getSectionName());
        ret.put("jobGrade", pmHDR.getJob_grade());
        ret.put("jobGradeName", pmHDR.getJob_grade_name());
        ret.put("jobBrand", pmHDR.getJob_brand());
        ret.put("jobBrandName", pmHDR.getJob_band_name());

        ret.put("managerLv1", pmHDR.getManager_lv1());
        ret.put("approveLv1Name", pmHDR.getApprove_lv1_name());
        ret.put("managerLv2", pmHDR.getManager_lv2());
        ret.put("approveLv2Name", pmHDR.getApprove_lv2_name());
        ret.put("designation", pmHDR.getDesignation());
        ret.put("pmStatus", pmHDR.getPm_status());
        ret.put("comRating", pmHDR.getCom_rating());
        ret.put("comRatingPercent", pmHDR.getCom_rating_percent());
        ret.put("presidentRating", pmHDR.getPresident_rating());
        ret.put("presidentRatingPercent", pmHDR.getPresident_rating_percent());
        ret.put("individualRating", pmHDR.getIndividual_rating());
        ret.put("individualRatingPercent", pmHDR.getIndividual_rating_percent());

        ret.put("part3Comment1", pmHDR.getPart3_comment1());
        ret.put("part3Comment2", pmHDR.getPart3_comment2());
        ret.put("part3Comment3", pmHDR.getPart3_comment3());
        ret.put("part3Comment4", pmHDR.getPart3_comment4());
        ret.put("submitBy", pmHDR.getSubmit_by());
        ret.put("submitDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getSubmit_date()));

        ret.put("part4Comment1", pmHDR.getPart4_comment1());

        ret.put("overallRating", pmHDR.getOverall_rating());
        ret.put("overallRatingPercent", pmHDR.getOverall_rating_percent());

        ret.put("lv1ApproveBy", pmHDR.getLv1_approve_by());
        ret.put("lv1ApproveDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getLv1_approve_date()));

        ret.put("part5Comment1", pmHDR.getPart5_comment1());

        ret.put("lv2ApproveBy", pmHDR.getLv2_approve_by());
        ret.put("lv2ApproveDate", RutDate.getDefaultDateStringFromJdbcDate(pmHDR.getLv2_approve_date()));

        ret.put("revisionNo", pmHDR.getRevision_no());
        ret.put("recordStatus", pmHDR.getRecordStatus());

        ret.put("divSeqno", pmHDR.getDiv_seqno());
        ret.put("divisionName", pmHDR.getDivisionName());

        ret.put("performanceAppraisals",
                pmHDR.getPm_period() + "H Y" + pmHDR.getPm_year()); //  PM_PERIOD +'H'+ ' ' + 'Y' + PM_YEAR
        ret.put("designationAndDepartment",
                RutString.nullToStr(pmHDR.getDesignation()) + "/" + RutString.nullToStr(pmHDR.getCompanyName()) + "/" +
                RutString.nullToStr(pmHDR.getDivisionName()) + "/" + RutString.nullToStr(pmHDR.getDepartmentName()) +
                "/" + RutString.nullToStr(pmHDR.getSectionName()));

        return ret;
    }


    public JSONArray getJobBrandWeightageAsJSON() throws DataAccessException {

        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        Map<String, Object> activePMYearPeriod = this.pmsCommon2Dao.getActivePMYearPeriod(userID);
        Integer pmYear = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_YEAR")));
        Integer pmPeriod = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_PERIOD")));
        List<PmsJobWeightageMod> pmsJobWeightageList =
            this.getPmsCommon2Dao().getJobWeightageList(userID, pmYear, pmPeriod);

        return this.formatJobWeightageAsJSONArray(pmsJobWeightageList);
    }


    public List<PmsIndicatorMod> getIndicatorList(String category) {
        //        String userID = "10514"; // fix code for test log id
        String userID = this.getPrsnLogIdOfUser();
        Map<String, Object> activePMYearPeriod = this.pmsCommon2Dao.getActivePMYearPeriod(userID);
        Integer pmYear = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_YEAR")));
        Integer pmPeriod = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_PERIOD")));
        final List<PmsIndicatorMod> resultList =
            this.pmsCommon2Dao.getIndicatorList(userID, pmYear, pmPeriod, category);

        return resultList;
    }

    public JSONArray getPDIndAsJSON(Long empSeqNo, String pmKey, Long pmHDRSeqNo) throws DataAccessException {

        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        List<PmsPMDetailMod> pds = this.pmsPMForm2Dao.getPresidentDirectives(userID, empSeqNo, pmKey, pmHDRSeqNo);
        return this.formatPmsPMDetailModAsJSON(pds);


    }

    public JSONArray getIndividualKPIsAsJSON(Long empSeqNo, String pmKey, Long pmHDRSeqNo) throws DataAccessException {

        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        List<PmsPMDetailMod> pds = this.pmsPMForm2Dao.getIndividualKPIs(userID, empSeqNo, pmKey, pmHDRSeqNo);
        return this.formatPmsPMDetailModAsJSON(pds);

    }

    public JSONArray formatIndigatorAsJSON(List<PmsIndicatorMod> mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final PmsIndicatorMod mod = mods.get(idx);
            item = new JSONObject();

            item.put("bscName", mod.getBscName());
            item.put("bscSeqNo", mod.getBscSeqNo());
            item.put("category", mod.getCategory());
            item.put("description", mod.getDescription());
            item.put("name", mod.getName());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
            item.put("rating", mod.getRating());
            item.put("result", mod.getResult());
            item.put("seqNo", mod.getSeqNo());
            item.put("slab0", mod.getSlab0());
            item.put("slab1", mod.getSlab1());
            item.put("slab2", mod.getSlab2());
            item.put("slab3", mod.getSlab3());
            item.put("slab4", mod.getSlab4());
            item.put("slab5", mod.getSlab5());
            item.put("weightage", mod.getWeightage());

            data.add(item);
        }
        return data;
    }


    public JSONArray getCompanyCoreValueAsJSON(long empSeqNo, String pmKey,
                                               long pmHDRSeqNo) throws DataAccessException {
        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        List<PmsPMDetailMod> companyCoreModList =
            this.getPmsPMForm2Dao().getCompanyCoreValues(userID, empSeqNo, pmKey, pmHDRSeqNo);


        return this.formatCompanyAndDepartmentCoreJSON(companyCoreModList);
    }

    public JSONArray getDepartmentCoreValueAsJSON(long empSeqNo, String pmKey,
                                                  long pmHDRSeqNo) throws DataAccessException {
        JSONArray data = new JSONArray();
        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id

        List<PmsPMDetailMod> departmentCoreModList =
            this.getPmsPMForm2Dao().getDepartmentCoreValues(userID, empSeqNo, pmKey, pmHDRSeqNo);

        return this.formatCompanyAndDepartmentCoreJSON(departmentCoreModList);
    }

    public JSONObject getIndicatorDetailAsJSON(long indSeqNo, String jobBrand) {
        JSONObject data = new JSONObject();
        String userID = this.getPrsnLogIdOfUser();
        //        String userID = "10514"; // fix code for test log id
        //        if(jobBrand != null && jobBrand.trim().length() <= 0 ){
        //
        //        }
        Map dltInd = this.getPmsCommon2Dao().getIndicatorDetail(userID, indSeqNo, jobBrand);
        //
        //        List<PmsPMDetailMod> departmentCoreModList = this.getPmsPMForm2Dao().getDepartmentCoreValues(userID, empSeqNo, pmKey, pmHDRSeqNo);
        //
        return formatDetailIndicator(dltInd);
    }

    public JSONObject getOverallPMAsJSON(String jobBrand, long empSeqNo, String pmKey) {
        //        String userID = "10514"; // fix code for test log id
        String userID = this.getPrsnLogIdOfUser();
        Map<String, Object> activePMYearPeriod = this.pmsCommon2Dao.getActivePMYearPeriod(userID);
        Integer pmYear = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_YEAR")));
        Integer pmPeriod = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_PERIOD")));
        //        jobBrand = "EXECUTE" ;  // fix for test
        PmsJobWeightageMod jobWeightage = this.pmsCommon2Dao.getJobWeightage(userID, jobBrand, pmYear, pmPeriod);
        List<PmsPMOverallMod> overallList = this.pmsPMForm2Dao.getPMOverall(userID, pmKey, empSeqNo, pmYear);

        JSONObject ret = new JSONObject();
        JSONObject jobWeightageJson = formatJobWeightagetoJSONObj(jobWeightage);
        JSONArray overallJsonArr = formatPMOveralltoJSON(overallList);

        ret.put("jobWeightage", jobWeightageJson);
        ret.put("overall", overallJsonArr);
        ret.put("currentPmYear", pmYear);

        return ret;
    }

    void save(HttpServletRequest request, HttpServletResponse response, ServletContext context,
              PmsCommonUim uim) throws IOException {
        try {
            String userID = this.getPrsnLogIdOfUser();
            String pmKey = (String)context.getAttribute(PmsConstant.PM_KEY);
            //            String strEmpSeqNo = request.getParameter("empSeqNo");
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
            //            String strPkEmpSeqNo = null;
            //            final List<CrmProfileMod> crmProfilModList = uim.getProfileType();
            //            if(crmProfilModList != null && crmProfilModList.size() > 0){
            //                strEmpSeqNo = crmProfilModList.get(0).getPkEmpSeqno();
            //            }

            long empSeqNo = this.getPkEmpSeqNo();
            long pmHDRSeqNo = new Long(strPmHDRSeqNo).longValue();
            StringBuffer sb = new StringBuffer();
            BufferedReader bufferedReader = request.getReader();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line.trim());
            }
            JSONParser jsonParse = new JSONParser();
            JSONObject jsonRequestObj = (JSONObject)jsonParse.parse(sb.toString());

            Map<String, Object> activePMYearPeriod = this.pmsCommon2Dao.getActivePMYearPeriod(userID);
            Integer pmYear = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_YEAR")));
            Integer pmPeriod = Integer.parseInt(String.valueOf(activePMYearPeriod.get("P_O_PM_PERIOD")));

            JSONObject pmHeaderJSON = (JSONObject)jsonRequestObj.get("pmHeader");
            if (pmHeaderJSON != null) {
                PmsPmHDRMod pmHdrmod = this.pmsPMForm2Dao.getPMHDR(userID, empSeqNo, pmKey, pmHDRSeqNo);
                pmHdrmod.setPart3_comment1((String)pmHeaderJSON.get("part3Comment1"));
                pmHdrmod.setPart3_comment2((String)pmHeaderJSON.get("part3Comment2"));
                pmHdrmod.setPart3_comment3((String)pmHeaderJSON.get("part3Comment3"));
                pmHdrmod.setPart3_comment4((String)pmHeaderJSON.get("part3Comment4"));
                Map<String, Object> updPMHdr = this.pmsPMForm2Dao.updatePMHeader(userID, pmKey, pmHdrmod);

                if (updPMHdr.get("P_O_VALID").toString().equals("N")) {
                    JSONObject responseJson = new JSONObject();
                    responseJson.put("valid", "N");
                    responseJson.put("msg", updPMHdr.get("P_O_ERROR_MSG").toString());
                    response.setContentType("application/json");
                    response.getWriter().println(responseJson.toString());
                    return;
                }
            }

            JSONArray companyCoreValuesJSONArr = (JSONArray)jsonRequestObj.get("companyCoreValues");
            if (companyCoreValuesJSONArr != null && companyCoreValuesJSONArr.size() > 0) {
                Iterator<JSONObject> itor = companyCoreValuesJSONArr.iterator();
                while (itor.hasNext()) {
                    JSONObject item = itor.next();
                    PmsPMDetailMod modDetail = new PmsPMDetailMod();
                    modDetail.setDtl_seqno(Long.parseLong(String.valueOf(item.get("dtlSeqNo"))));
                    modDetail.setHdr_seqno(Long.parseLong(String.valueOf(item.get("hdrSeqNo"))));
                    modDetail.setInd_seqno(Long.parseLong(String.valueOf(item.get("indSeqNo"))));
                    modDetail.setInd_self_rating(Double.parseDouble(String.valueOf(item.get("indSelfRating"))));
                    modDetail.setInd_rating(Double.parseDouble(String.valueOf(item.get("indRating"))));
                    modDetail.setRemark((String)item.get("remark"));

                    Map<String, Object> updRet =
                        this.pmsPMForm2Dao.updateCompanyCoreValue(userID, empSeqNo, pmKey, modDetail);
                    if (updRet.get("P_O_VALID").toString().equals("N")) {
                        JSONObject responseJson = new JSONObject();
                        responseJson.put("valid", "N");
                        responseJson.put("msg", updRet.get("P_O_ERROR_MSG").toString());
                        response.setContentType("application/json");
                        response.getWriter().println(responseJson.toString());
                        return;
                    }
                }
            }

            //departmentCoreValues
            JSONArray departmentCoreValueJSONArr = (JSONArray)jsonRequestObj.get("departmentCoreValues");
            if (departmentCoreValueJSONArr != null && departmentCoreValueJSONArr.size() > 0) {
                Iterator<JSONObject> itor = departmentCoreValueJSONArr.iterator();
                while (itor.hasNext()) {
                    JSONObject item = itor.next();
                    String action = (String)item.get("action");

                    Map<String, Object> ret;
                    if (action.equalsIgnoreCase("add")) {
                        long indicatorSeqNo = Long.parseLong((String)item.get("indSeqNo"));
                        double weightage = Double.valueOf(String.valueOf(item.get("indWeightPercentage")));
                        double selfRating = Double.valueOf(String.valueOf(item.get("indSelfRating")));

                        ret =
this.pmsPMForm2Dao.insertDepartmentCoreValue(userID, pmKey, pmHDRSeqNo, indicatorSeqNo, weightage, selfRating);
                    } else if (action.equalsIgnoreCase("upd")) {

                        PmsPMDetailMod modDetail = new PmsPMDetailMod();

                        String strIndSeqNo = String.valueOf(item.get("indSeqNo"));
                        String strDtlSeqNo = String.valueOf(item.get("dtlSeqNo"));
                        String strHdrSeqNo = String.valueOf(item.get("hdrSeqNo"));
                        modDetail.setDtl_seqno((Long)item.get("dtlSeqNo"));
                        modDetail.setHdr_seqno((Long)item.get("hdrSeqNo"));
                        modDetail.setInd_seqno(Long.parseLong(strIndSeqNo));
                        modDetail.setInd_weight_percentage(Double.parseDouble(String.valueOf(item.get("indWeightPercentage"))));
                        modDetail.setInd_self_rating(Double.parseDouble(String.valueOf(item.get("indSelfRating"))));
                        modDetail.setInd_rating(Double.parseDouble(String.valueOf(item.get("indRating"))));
                        modDetail.setRemark(RutString.nullToStr(item.get("remark")));

                        ret = this.pmsPMForm2Dao.updateDepartmentCoreValue(userID, empSeqNo, pmKey, modDetail);

                        //
                        //                        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
                        //                        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
                        //                        mapParams.put("P_I_IND_SEQNO", pmDetail.getInd_seqno());
                        //                        mapParams.put("P_I_WEIGHTAGE", pmDetail.getInd_weight_percentage());
                        //                        mapParams.put("P_I_SELF_RATING", pmDetail.getInd_self_rating());
                        //                        mapParams.put("P_I_MNGR_COMMENT", pmDetail.getRemark());
                    } else if (action.equalsIgnoreCase("del")) {

                        PmsPMDetailMod modDetail = new PmsPMDetailMod();

                        String strDtlSeqNo = String.valueOf(item.get("dtlSeqNo"));
                        String strHdrSeqNo = String.valueOf(item.get("hdrSeqNo"));
                        String strIndSeqNo = String.valueOf(item.get("indSeqNo"));

                        modDetail.setDtl_seqno(Long.parseLong(strDtlSeqNo));
                        modDetail.setHdr_seqno(Long.parseLong(strHdrSeqNo));
                        modDetail.setInd_seqno(Long.parseLong(strIndSeqNo));

                        ret = this.pmsPMForm2Dao.deleteDepartmentCoreValue(userID, empSeqNo, pmKey, modDetail);
                    } else {
                        // action not match
                        continue;
                    }


                    if (ret.get("P_O_VALID").toString().equals("N")) {
                        JSONObject responseJson = new JSONObject();
                        responseJson.put("valid", "N");
                        responseJson.put("msg", ret.get("P_O_ERROR_MSG").toString());
                        response.setContentType("application/json");
                        response.getWriter().println(responseJson.toString());
                        return;
                    }
                }
            }

            JSONArray individualKPIJSONArr = (JSONArray)jsonRequestObj.get("individualKPIs");
            if (individualKPIJSONArr != null && individualKPIJSONArr.size() > 0) {
                Iterator<JSONObject> itor = individualKPIJSONArr.iterator();
                while (itor.hasNext()) {
                    JSONObject item = itor.next();
                    String action = (String)item.get("action");

                    Map<String, Object> ret;
                    if (action.equalsIgnoreCase("add")) {
                        long bscSeqNo = Long.parseLong((String)item.get("bscSeqNo"));
                        String indDesc = (String)item.get("indDescription");
                        String indSlab0 = (String)item.get("indSlab0");
                        String indSlab1 = (String)item.get("indSlab1");
                        String indSlab2 = (String)item.get("indSlab2");
                        String indSlab3 = (String)item.get("indSlab3");
                        String indSlab4 = (String)item.get("indSlab4");
                        String indSlab5 = (String)item.get("indSlab5");
                        double weightage = Double.parseDouble(String.valueOf(item.get("indWeightPercentage")));

                        String result = (String)item.get("indResult");
                        double rating = Double.parseDouble(String.valueOf(item.get("indRating")));

                        ret =
this.pmsPMForm2Dao.insertIndividualKPI(userID, pmKey, pmHDRSeqNo, bscSeqNo, indDesc, indSlab0, indSlab1, indSlab2,
                                       indSlab3, indSlab4, indSlab5, weightage, result, rating);
                    } else if (action.equalsIgnoreCase("upd")) {

                        PmsPMDetailMod modDetail = new PmsPMDetailMod();
                        String strDtlSeqNo = String.valueOf(item.get("dtlSeqNo"));
                        String strHdrSeqNo = String.valueOf(item.get("hdrSeqNo"));
                        String strBscSeqNo = String.valueOf(item.get("bscSeqNo"));


                        modDetail.setDtl_seqno(Long.parseLong(strDtlSeqNo));
                        modDetail.setHdr_seqno(Long.parseLong(strHdrSeqNo));
                        modDetail.setBsc_seqno(Long.parseLong(strBscSeqNo));
                        //                        modDetail.set(Long.parseLong( strBscSeqNo ));
                        modDetail.setInd_description((String)item.get("indDescription"));
                        modDetail.setInd_slab_0((String)item.get("indSlab0"));
                        modDetail.setInd_slab_1((String)item.get("indSlab1"));
                        modDetail.setInd_slab_2((String)item.get("indSlab2"));
                        modDetail.setInd_slab_3((String)item.get("indSlab3"));
                        modDetail.setInd_slab_4((String)item.get("indSlab4"));
                        modDetail.setInd_slab_5((String)item.get("indSlab5"));
                        modDetail.setInd_weight_percentage(Double.parseDouble(String.valueOf(item.get("indWeightPercentage"))));
                        modDetail.setInd_result((String)item.get("indResult"));
                        modDetail.setInd_rating(Double.parseDouble(String.valueOf(item.get("indRating"))));

                        ret = this.pmsPMForm2Dao.updateIndividualKPI(userID, pmKey, empSeqNo, modDetail);
                    } else if (action.equalsIgnoreCase("del")) {

                        PmsPMDetailMod modDetail = new PmsPMDetailMod();

                        String strDtlSeqNo = String.valueOf(item.get("dtlSeqNo"));
                        String strHdrSeqNo = String.valueOf(item.get("hdrSeqNo"));
                        String strBscSeqNo = String.valueOf(item.get("bscSeqNo"));


                        modDetail.setDtl_seqno(Long.parseLong(strDtlSeqNo));
                        modDetail.setHdr_seqno(Long.parseLong(strHdrSeqNo));
                        modDetail.setInd_seqno(Long.parseLong(strBscSeqNo));


                        //                        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
                        //                        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
                        //                        mapParams.put("P_I_BSC_SEQNO", pmDetail.getBsc_seqno());

                        ret = this.pmsPMForm2Dao.deleteIndividualKPI(userID, pmKey, modDetail);
                    } else {
                        // action not match
                        continue;
                    }


                    if (ret.get("P_O_VALID").toString().equals("N")) {
                        JSONObject responseJson = new JSONObject();
                        responseJson.put("valid", "N");
                        responseJson.put("msg", ret.get("P_O_ERROR_MSG").toString());
                        response.setContentType("application/json");
                        response.getWriter().println(responseJson.toString());
                        return;
                    }
                }
            }

            JSONArray attachFileListJSONArr = (JSONArray)jsonRequestObj.get("attachFileList");
            if (attachFileListJSONArr != null && attachFileListJSONArr.size() > 0) {
                Iterator<JSONObject> itor = attachFileListJSONArr.iterator();
                while (itor.hasNext()) {
                    JSONObject item = itor.next();
                    String action = (String)item.get("action");

                    Map<String, String> ret;
                    if (action.equalsIgnoreCase("add")) {
                        String keyFile = String.valueOf(item.get("fileIndex"));
                        FileItem fileItem = (FileItem)fileItemMap.get(keyFile);
                        String strUploadPath = context.getInitParameter(PmsConstant.UPLOAD_ATTACHFILE_PATH);
                        String strFilename = fileItem.getName();
                        if(strFilename.lastIndexOf("\\") >= 0){
                            strFilename = strFilename.substring(strFilename.lastIndexOf("\\")+1) ;
                        }
                    
                        String fullpath = strUploadPath;
                        // verify file path
                        if (!fullpath.endsWith(File.separator)) {
                            fullpath += File.separator;
                        }
                        File dir = new File(fullpath+pmHDRSeqNo);
                        if(!dir.exists()){
                            if(dir.mkdir()){
                                
                            }
                        }
                        fullpath += pmHDRSeqNo+File.separator;
                        
                        fullpath += strFilename;
                        FileOutputStream out = null;
                        InputStream in = null;
                        try {
                            in = fileItem.getInputStream();
                            out = new FileOutputStream(fullpath);
                            int length = 0;
                            byte buffer[] = new byte[1024];

                            while ((length = in.read(buffer)) > 0) {
                                out.write(buffer, 0, length);
                            }

                            out.flush();
                        } finally {
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (Exception ex) {

                                }
                            }
                            if (out != null) {
                                try {
                                    out.close();
                                } catch (Exception ex) {
                                }
                            }
                        }

                        ret =
this.pmsPMForm2Dao.insertAttachFile(Integer.valueOf((int)pmHDRSeqNo), strFilename, fullpath, userID);
                        ret.put("P_O_VALID", ret.get("P_O_UPLOAD_DTL_SEQNO") != null ? "Y" : "N");
                    } else if (action.equalsIgnoreCase("del")) {
                        String strUploadHDRSeqNo = String.valueOf(item.get("uploadHDRSeqNo"));
                        String strUploadDTLSeqNo = String.valueOf(item.get("uploadDTLSeqNo"));
                        String strFileFullPath = String.valueOf(item.get("fileFullPath"));

                        //     item.put("uploadHDRSeqNo", mod.getUploadHDRSeqNo());
                        // item.put("uploadDTLSeqNo", mod.getUploadDTLSeqNo());
                        ret = new HashMap<String, String>();
                        boolean retBol =
                            this.pmsPMForm2Dao.deleteAttachFile(Integer.valueOf(strUploadHDRSeqNo), Integer.valueOf(strUploadDTLSeqNo),
                                                                Integer.valueOf(strPmHDRSeqNo), userID);
                        File file = new File(strFileFullPath);
                        if (file.exists()) {
                            file.delete();
                        }
                        ret.put("P_O_VALID", retBol ? "Y" : "N");
                    } else {
                        // action not match
                        continue;
                    }


                    if (ret.get("P_O_VALID").toString().equals("N")) {
                        JSONObject responseJson = new JSONObject();
                        responseJson.put("valid", "N");
                        responseJson.put("msg", ret.get("P_O_ERROR_MSG").toString());
                        response.setContentType("application/json");
                        response.getWriter().println(responseJson.toString());
                        return;
                    }
                }
            }


            Map<String, Object> calPMHdr = this.pmsPMForm2Dao.calculatePM(userID, pmKey, pmHDRSeqNo);
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", calPMHdr.get("P_O_VALID"));
            responseJson.put("msg", calPMHdr.get("P_O_ERROR_MSG"));
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }

    void submit(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                PmsCommonUim uim) throws IOException {
        try {

            String userID = this.getPrsnLogIdOfUser();
            String strEmpSeqNo = request.getParameter("empSeqNo");
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");

            long empSeqNo = new Long(strEmpSeqNo).longValue();
            long pmHDRSeqNo = new Long(strPmHDRSeqNo).longValue();

            Map<String, Object> submitPMHdr = this.pmsPMForm2Dao.submitPM(userID, empSeqNo, pmHDRSeqNo);
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", submitPMHdr.get("P_O_VALID"));
            responseJson.put("msg", submitPMHdr.get("P_O_ERROR_MSG"));
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }

    void approveAndReject(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                          PmsCommonUim uim) throws IOException {
        try {

            String userID = this.getPrsnLogIdOfUser();
            //            String strEmpSeqNo = request.getParameter("empSeqNo");
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");

            //            long empSeqNo = new Long(strEmpSeqNo).longValue();
            long pmHDRSeqNo = new Long(strPmHDRSeqNo).longValue();

            StringBuffer sb = new StringBuffer();
            BufferedReader bufferedReader = request.getReader();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line.trim());
            }
            JSONParser jsonParse = new JSONParser();
            JSONObject jsonRequestObj = (JSONObject)jsonParse.parse(sb.toString());

            String approveReject = (String)jsonRequestObj.get("approveReject");
            String mgrLv1Comment = (String)jsonRequestObj.get("mgrLv1Comment");
            String mgrLv2Comment = (String)jsonRequestObj.get("mgrLv2Comment");

            Map<String, Object> approveRejectPMHdr =
                this.pmsPMForm2Dao.approvePM(userID, this.pkEmpSeqNo, pmHDRSeqNo, approveReject.toUpperCase(),
                                             mgrLv1Comment, mgrLv2Comment);
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", approveRejectPMHdr.get("P_O_VALID"));
            responseJson.put("msg", approveRejectPMHdr.get("P_O_ERROR_MSG"));
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }

    void getAttachFileList(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                           PmsCommonUim uim) throws IOException {
        try {

            String userID = this.getPrsnLogIdOfUser();
            //            String strEmpSeqNo = request.getParameter("empSeqNo");
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
            //            long empSeqNo = new Long(strEmpSeqNo).longValue();
            int pmHDRSeqNo = Integer.parseInt(strPmHDRSeqNo);
            List<PmsAttachFileMod> attachFileList = this.pmsPMForm2Dao.getAttachFileList(pmHDRSeqNo, userID);

            JSONArray data = formatPMSAttachFileJSON(attachFileList);
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "V");
            responseJson.put("msg", "");
            responseJson.put("data", data);
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());


        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }

    void addAttachFileList(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                           PmsCommonUim uim) throws IOException {
        try {
            JSONObject responseJson = new JSONObject(); 
            
            List<String> allowExtensions  = 
                     new ArrayList<String>(Arrays.asList(new String[]{"pdf", "xlsx", "xls" , "docx", "doc"}));

            
            String userID = this.getPrsnLogIdOfUser();
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
            int pmHDRSeqNo = Integer.parseInt(strPmHDRSeqNo);
            FileItem fileItem = (FileItem)RutRequest.getParameterOjbectByCode(request, "file");
            // List<PmsAttachFileMod> attachFileList = this.pmsPMForm2Dao.getAttachFileList(pmHDRSeqNo, userID);
            //            if(fileItemList == null ){
            //                fileItemList = new ArrayList<FileItem>();
            //            }
            String fileName = fileItem.getName();
            if(fileName.lastIndexOf("\\") >= 0){
                fileName = fileName.substring(fileName.lastIndexOf("\\")+1) ;
            }
            if(fileName.lastIndexOf('.') < 0 ){
                responseJson.put("valid", "N");
                responseJson.put("msg", "Unknow file extension.");

                response.setContentType("text/plain");
                response.getWriter().println(responseJson.toString());
                return;
            }
            String ext = fileName.toLowerCase().substring(fileName.lastIndexOf('.')+1);
            if( !allowExtensions.contains(ext) ){
                responseJson.put("valid", "N");
                responseJson.put("msg", "Invalid upload file extension. Allows upload only (.pdf,xls,xlsx,doc,docx) file");
                response.setContentType("text/plain");
                response.getWriter().println(responseJson.toString());
                return;
            }
            
         //   int nFilesizeMB = (int)(fileItem.getSize() / Math.pow(1024, 2) );
         int nFilesizeKB = (int)(fileItem.getSize() / 1024.0);
         //   if( nFilesizeMB > 1 ){
            if (nFilesizeKB > 1024) {
                responseJson.put("valid", "N");
                responseJson.put("msg", "Cannot upload file size more than 1 MBytes");
                response.setContentType("text/plain");
                response.getWriter().println(responseJson.toString());
                return;
            }
            
            String keyName = pmHDRSeqNo + "_" + fileItem.getName();
            fileItemMap.put(keyName, fileItem);
            //            fileItemList.add(fileItem);
            JSONObject data = new JSONObject();
            //            item.put("uploadHDRSeqNo",);
            //            item.put("uploadDTLSeqNo", mod.getUploadDTLSeqNo());
            //            item.put("pmHDRSeqNo", mod.getPmHDRSeqNo());
            data.put("fileIndex", keyName);
            
            data.put("fileName", fileName);
            //            data.put("fileFullPath", mod.getFileFullPath());
            data.put("fileSizeKB", String.format("%.02f", fileItem.getSize() / 1024.0));
            //            item.put("recordChangeDate", mod.getRecordChangeDate());

            
            responseJson.put("valid", "V");
            responseJson.put("msg", "");
            responseJson.put("data", data);
//            response.setContentType("application/json");
//            response.getWriter().println(responseJson.toString());
            response.setContentType("text/plain");
            response.getWriter().println(responseJson.toString());
//            response.getWriter().println("<pre>"+responseJson.toString()+"</pre>");

        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
//            response.setContentType("application/json");
//            response.getWriter().println(responseJson.toString());
            response.setContentType("text/plain");
            response.getWriter().println(responseJson.toString());

        }
    }

    void removeAttachFileList(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                              PmsCommonUim uim) throws IOException {
        try {

            String userID = this.getPrsnLogIdOfUser();
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
            int pmHDRSeqNo = Integer.parseInt(strPmHDRSeqNo);
            String keyName = request.getParameter("keyName");
            //            FileItem fileItem =(FileItem)RutRequest.getParameterOjbectByCode(request, "file");
            // List<PmsAttachFileMod> attachFileList = this.pmsPMForm2Dao.getAttachFileList(pmHDRSeqNo, userID);
            if (fileItemMap.get(keyName) == null) {
                fileItemMap.remove(keyName);
            }

            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "V");
            responseJson.put("msg", "");
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());


        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }


    void insertAttachFileList(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                              PmsCommonUim uim) throws IOException {
        try {

            String userID = this.getPrsnLogIdOfUser();
            //            String strEmpSeqNo = request.getParameter("empSeqNo");
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
            //            long empSeqNo = new Long(strEmpSeqNo).longValue();
            int pmHDRSeqNo = Integer.parseInt(strPmHDRSeqNo);

            FileItem fileItem = (FileItem)RutRequest.getParameterOjbectByCode(request, "file");
            //            this.pmsPMForm2Dao.insertAttachFile(pmHDRSeqNo, fileItem.getName(), fileItem., userID)

            List<PmsAttachFileMod> attachFileList = this.pmsPMForm2Dao.getAttachFileList(pmHDRSeqNo, userID);

            JSONArray data = formatPMSAttachFileJSON(attachFileList);
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "V");
            responseJson.put("msg", "");
            responseJson.put("data", data);
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());


        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }

    void dowloadAttachFile(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                           PmsCommonUim uim) throws IOException {
        try {
            HttpSession session = request.getSession(false);
            String userID = this.getPrsnLogIdOfUser();
            //            String strEmpSeqNo = request.getParameter("empSeqNo");
            String strPmHDRSeqNo = request.getParameter("pmHDRSeqNo");
            //            long empSeqNo = new Long(strEmpSeqNo).longValue();
            int pmHDRSeqNo = Integer.parseInt(strPmHDRSeqNo);
            String fileFullPath = request.getParameter("fileFullPath");
            String fileName = request.getParameter("fileName");
            response.setHeader("Content-disposition", "attachment;filename=\"" +fileName+"\"");
            response.addHeader("Content-Type", "application/octet-stream");
            OutputStream out = response.getOutputStream();
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(fileFullPath));

                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }

            } finally {
                // close file streams
                if (in != null) {
                    in.close();
                }

            }
            out.flush();
            out.close();


        } catch (Exception ex) {
            ex.printStackTrace();
            JSONObject responseJson = new JSONObject();
            responseJson.put("valid", "N");
            responseJson.put("msg", ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().println(responseJson.toString());

        }
    }

    public JSONArray formatPMSAttachFileJSON(final List<PmsAttachFileMod> list) {
        JSONArray data = new JSONArray();
        JSONObject item;
        //        private String recordStatus;
        //        private String recordAddUser;
        //        private Timestamp recordAddDate;
        //        private String recordChangeUser;
        //        private Timestamp recordChangeDate;
        for (int idx = 0; idx < list.size(); idx++) {
            PmsAttachFileMod mod = list.get(idx);

            File f = new File(mod.getFileFullPath());

            item = new JSONObject();
            item.put("uploadHDRSeqNo", mod.getUploadHDRSeqNo());
            item.put("uploadDTLSeqNo", mod.getUploadDTLSeqNo());
            item.put("pmHDRSeqNo", mod.getPmHDRSeqNo());
            item.put("fileName", mod.getFileName());
            item.put("fileFullPath", mod.getFileFullPath());
            if (f.isFile() && f.exists()) {
                item.put("fileSizeKB", String.format("%.02f", f.length() / 1024.0));
                item.put("recordChangeDate", RutDate.getDefaultDateStringFromJdbcDate(new Date(f.lastModified())));
            }


            data.add(item);
        }

        return data;
    }


    public JSONArray formatPMOveralltoJSON(final List<PmsPMOverallMod> list) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < list.size(); idx++) {
            PmsPMOverallMod mod = list.get(idx);
            item = new JSONObject();
            item.put("comRatingPercent", mod.getComRatingPercent());
            item.put("empSeqNo", mod.getEmpSeqNo());
            item.put("individualRatingPercent", mod.getIndividualRatingPercent());
            item.put("overallRatingPercent", mod.getOverallRatingPercent());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
            item.put("presidentRatingPercent", mod.getPresidentRatingPercent());

            data.add(item);
        }

        return data;
    }

    public JSONObject formatDetailIndicator(Map map) {
        JSONObject data = new JSONObject();
        //        PK_IND_SEQNO
        //        DEFINITION
        //        DESCRIPTION1
        //        DESCRIPTION2
        //        DESCRIPTION3

        data.put("indSeqNo", map.get("PK_IND_SEQNO"));
        data.put("definition", map.get("DEFINITION"));
        data.put("description1", map.get("DESCRIPTION1"));
        data.put("description2", map.get("DESCRIPTION2"));
        data.put("description3", map.get("DESCRIPTION3"));
        return data;
    }

    public JSONArray formatCompanyAndDepartmentCoreJSON(List mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final PmsPMDetailMod mod = (PmsPMDetailMod)mods.get(idx);
            item = new JSONObject();
            item.put("dtlSeqNo", mod.getDtl_seqno());
            item.put("indSeqNo", mod.getInd_seqno());
            item.put("hdrSeqNo", mod.getHdr_seqno());
            item.put("indName", mod.getInd_name());
            item.put("remark", mod.getRemark());
            item.put("indWeightPercentage", mod.getInd_weight_percentage());
            item.put("indSelfRating", mod.getInd_self_rating());
            item.put("indRating", mod.getInd_rating());

            data.add(item);
        }
        return data;
    }


    public JSONArray formatJobWeightageAsJSONArray(List<PmsJobWeightageMod> mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final PmsJobWeightageMod mod = mods.get(idx);
            item = new JSONObject();

            item.put("comWeightage", mod.getComWeightage());
            item.put("description", mod.getDescription());
            item.put("indKPIWeightage", mod.getIndKPIWeightage());
            item.put("jobBrand", mod.getJobBrand());
            item.put("pdWeightage", mod.getPdWeightage());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());

            data.add(item);
        }
        return data;
    }

    public JSONObject formatJobWeightagetoJSONObj(final PmsJobWeightageMod mod) {
        JSONObject item = new JSONObject();
        if (mod != null) {
            item.put("comWeightage", mod.getComWeightage());
            item.put("description", mod.getDescription());
            item.put("indKPIWeightage", mod.getIndKPIWeightage());
            item.put("jobBrand", mod.getJobBrand());
            item.put("pdWeightage", mod.getPdWeightage());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
        }


        return item;
    }

    public JSONArray formatPmsPMDetailModAsJSON(List<PmsPMDetailMod> mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final PmsPMDetailMod mod = mods.get(idx);
            item = new JSONObject();

            item.put("dtlSeqNo", mod.getDtl_seqno());
            item.put("hdrSeqNo", mod.getHdr_seqno());
            item.put("pmDtlCategory", mod.getPm_dtl_category());
            item.put("indSeqNo", mod.getInd_seqno());
            item.put("indName", mod.getInd_name());
            item.put("bscName", mod.getBsc_name());
            item.put("bscSeqNo", mod.getBsc_seqno());
            item.put("indDescription", mod.getInd_description());
            item.put("remark", mod.getRemark());
            item.put("indSlab0", mod.getInd_slab_0());
            item.put("indSlab1", mod.getInd_slab_1());
            item.put("indSlab2", mod.getInd_slab_2());
            item.put("indSlab3", mod.getInd_slab_3());
            item.put("indSlab4", mod.getInd_slab_4());
            item.put("indSlab5", mod.getInd_slab_5());
            item.put("indResult", mod.getInd_result());
            item.put("indSelfResult", mod.getInd_self_rating());
            item.put("indRating", mod.getInd_rating());
            item.put("indWeightPercentage", mod.getInd_weight_percentage());
            item.put("prevIndSelfRating", mod.getPrev_ind_self_rating());
            item.put("prevIndRating", mod.getPrev_ind_rating());
            item.put("prevWeightPercentage", mod.getPrev_weight_percentage());
            item.put("recordStatus", mod.getRecord_status());
            item.put("recordAddDate", RutDate.getDefaultDateStringFromTimestamp(mod.getRecord_add_date()));
            item.put("recordChangeUser", mod.getRecord_change_user());
            item.put("recordChangeDate", RutDate.getDefaultDateStringFromTimestamp(mod.getRecord_change_date()));
            data.add(item);
        }
        return data;
    }


    public JSONArray formatProfileTypeAsJSON(List<CrmProfileMod> mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final CrmProfileMod mod = mods.get(idx);
            item = new JSONObject();

            item.put("comName", mod.getComName());
            item.put("depName", mod.getDepName());
            item.put("designation", mod.getDesignation());
            item.put("divName", mod.getDivName());
            item.put("empId", mod.getEmpId());
            item.put("empName", mod.getEmpName());
            item.put("fkComSeqno", mod.getFkComSeqno());
            item.put("fkDepSeqno", mod.getFkDepSeqno());
            item.put("fkDivSeqno", mod.getFkDivSeqno());
            item.put("fkSecSeqno", mod.getFkSecSeqno());
            item.put("flagCreate", mod.getFlagCreate());
            item.put("jobBrand", mod.getJobBrand());
            item.put("jobBrandName", mod.getJobBrandName());
            item.put("jobGrade", mod.getJobGrade());
            item.put("jobGradeName", mod.getJobGradeName());
            item.put("joinDate", mod.getJoinDate());
            item.put("managerLv1", mod.getManagerLv1());
            item.put("managerLv1Name", mod.getManagerLv1Name());
            item.put("managerLv2", mod.getManagerLv2());
            item.put("managerLv2Name", mod.getManagerLv2Name());
            item.put("myStaffCount", mod.getMyStaffCount());
            item.put("pkEmpSeqno", mod.getPkEmpSeqno());
            item.put("secName", mod.getSecName());
            item.put("userId", mod.getUserId());
            item.put("userLevel", mod.getUserLevel());

            data.add(item);
        }
        return data;
    }

    //
    //    /*public JSONArray formatCustomerListAsJSON(List<LoaMod> customerList,
    //                                              Integer nStartRowNo) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNo;
    //        for (int idx = 0; idx < customerList.size(); idx++) {
    //            LoaMod mod = customerList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put(("customerCode"), mod.getCustomerCode());
    //            item.put("customerName", mod.getCustomerName());
    //            item.put("country", mod.getCountry());
    //            data.add(item);
    //        }
    //        return data;
    //    }*/
    //    /**
    //       *  Return  Job Brand  List.
    //       * @return
    //       */
    //     public JSONObject getPMList(PmCriteria pmCriteria) throws DataAccessException {
    //
    //         List <PmListMod> outList = new ArrayList<PmListMod>();
    //        pmCriteria.setUserId(this.getPrsnLogIdOfUser());
    //
    //        // pmCriteria.setUserId(this.getPrsnLogIdOfUser());
    //        //this.get
    //        //    P_I_USER_ID := '10492';
    //        //      P_I_PM_KEY := NULL;
    //        //      P_I_PAGE_ROWS := 10;
    //        //      P_I_PAGE_NO := 1;
    //        //      P_I_SORT := 'PH.PM_YEAR';
    //        //      P_I_ASC_DESC := 'ASC';
    //        //      P_I_EMP_ID := 70;
    //        //      P_I_PM_YEAR := 2017;
    //         int rowTotal=  this.pmsPMFormDao.getPMList( pmCriteria , outList  );
    //
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //        int startRow=((pmCriteria.getPageNo()-1) *  pmCriteria.getIDisplayLength())+1;
    //
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            PmListMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", startRow + idx);
    //          item.put(("pkhdrseqno"), mod.getPkhdrseqno());
    //            item.put(("pmYear"), mod.getPmyear());
    //            item.put("pmPeriod", mod.getPmperiod());
    //            item.put("fkempseqno", mod.getFkempseqno());
    //            item.put("managerlv1", mod.getManagerlv1());
    //            item.put("managerlv2", mod.getManagerlv2());
    //            item.put("fkcomseqno", mod.getFkcomseqno());
    //            item.put("comname", mod.getComname());
    //            item.put("fkdivseqno", mod.getFkdivseqno());
    //          item.put("divname", mod.getDivname());
    //          item.put("fkdepseqno", mod.getFkdepseqno());
    //          item.put("depname", mod.getDepname());
    //          item.put("fksecseqno", mod.getFksecseqno());
    //          item.put("secname", mod.getSecname());
    //            item.put("empname", mod.getEmpname());
    //            item.put("designation", mod.getDesignation());
    //          item.put("pmstatus", mod.getPmstatus());
    ////            if(mod.getPmstatus()!=null){
    ////                if(mod.getPmstatus().equals("N")){
    ////                  item.put("pmstatus", "New");
    ////                }else if(mod.getPmstatus().equals("I")){
    ////                  item.put("pmstatus", "In Progress");
    ////                }else if(mod.getPmstatus().equals("W")){
    ////                  item.put("pmstatus", "Waitlisted 1");
    ////                }else if(mod.getPmstatus().equals("V")){
    ////                  item.put("pmstatus", "Waitlisted 2");
    ////
    ////                }else if(mod.getPmstatus().equals("C")){
    ////                  item.put("pmstatus", "Completed");
    ////
    ////                }
    ////            }else{
    ////                  item.put("pmstatus", mod.getPmstatus());
    ////            }
    ////
    //
    //            item.put("jobGrade", mod.getJobgrade());
    //            item.put("jobgradename", mod.getJobgradename());
    //           item.put("jobbrand", mod.getJobbrand());
    //           item.put("jobbrandname", mod.getJobbrandname());
    //            item.put("approveLv1Name", mopprovelv1name());
    //          item.put("lv1approveby", mod.getLv1approveby());
    //
    //            item.put("approveLv2Name", mod.getApprovelv2name());
    //          item.put("lv2approveby", mod.getLv2approveby());
    //          item.put("mystaffcount", mod.getMystaffcount());
    //
    //            data.add(item);
    //        }
    //        item = new JSONObject();
    //        item.put("rowTotal",rowTotal);
    //        item.put("rowStart",startRow);
    //        item.put("dataTable",data);
    //
    //        return item;
    //      }
    //
    //
    //

    //    /**
    //       *  Return Currency List.
    //       * @return
    //       */
    //      public List<ValueDescriptionDropDownItemMod> getCurrencyList() {
    //          List<ValueDescriptionDropDownItemMod> countryList =
    //              this.loaCommonDao.getCurrencyList();
    //
    //          List<ValueDescriptionDropDownItemMod> resultList =
    //              new ArrayList<ValueDescriptionDropDownItemMod>();
    //          resultList.add(new ValueDescriptionDropDownItemMod("",
    //                                                       "All")); // Add empty item
    //          resultList.addAll(countryList);
    //
    //          return resultList;
    //      }
    //    /**
    //     * Format Region as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatRegionListAsJSON(List<LoaRegionMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaRegionMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("region", mod.getRegion_code());
    //            item.put("region_name", mod.getRegion_name());
    //            item.put("fsc", mod.getFsc());
    //            item.put("port", mod.getPort());
    //            item.put("point", mod.getPoint());
    //            item.put("terminal", mod.getTerminal());
    //            item.put("depot", mod.getDepot());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    //    /**
    //    * Format Fsc as JSON array list
    //    * @param outList
    //    * @param nStartRowNum
    //    * @return
    //    */
    //    JSONArray formatFscListAsJSON(List<LoaFscMod> outList,
    //                                    int nStartRowNum) {
    //       JSONArray data = new JSONArray();
    //       JSONObject item;
    //
    //       int rowno = nStartRowNum;
    //       for (int idx = 0; idx < outList.size(); idx++) {
    //           LoaFscMod mod = outList.get(idx);
    //           item = new JSONObject();
    //           item.put("rowNo", rowno + idx);
    //           item.put("region", mod.getRegion());
    //           item.put("fsc", mod.getFsc());
    //           item.put("fsc_name", mod.getFsc_name());
    //           item.put("port", mod.getPort());
    //           item.put("point", mod.getPoint());
    //           item.put("terminal", mod.getTerminal());
    //           item.put("depot", mod.getDepot());
    //           data.add(item);
    //       }
    //       return data;
    //    }
    //    /**
    //    * Format Port as JSON array list
    //    * @param outList
    //    * @param nStartRowNum
    //    * @return
    //    */
    //    JSONArray formatPortListAsJSON(List<LoaPortMod> outList,
    //                                    int nStartRowNum) {
    //       JSONArray data = new JSONArray();
    //       JSONObject item;
    //
    //       int rowno = nStartRowNum;
    //       for (int idx = 0; idx < outList.size(); idx++) {
    //           LoaPortMod mod = outList.get(idx);
    //           item = new JSONObject();
    //           item.put("rowNo", rowno + idx);
    //           item.put("region", mod.getRegion());
    //           item.put("fsc", mod.getFsc());
    //           item.put("port", mod.getPort());
    //           item.put("port_name", LoaUtil.removeSpecialChars( mod.getPort_name()));
    //           item.put("point", mod.getPoint());
    //           item.put("terminal", mod.getTerminal());
    //           item.put("depot", mod.getDepot());
    //           data.add(item);
    //       }
    //       return data;
    //    }
    //    /**
    //    * Format Terminal as JSON array list
    //    * @param outList
    //    * @param nStartRowNum
    //    * @return
    //    */
    //    JSONArray formatTerminalListAsJSON(List<LoaTerminalMod> outList,
    //                                    int nStartRowNum) {
    //       JSONArray data = new JSONArray();
    //       JSONObject item;
    //
    //       int rowno = nStartRowNum;
    //       for (int idx = 0; idx < outList.size(); idx++) {
    //           LoaTerminalMod mod = outList.get(idx);
    //           item = new JSONObject();
    //           item.put("rowNo", rowno + idx);
    //           item.put("region", mod.getRegion());
    //           item.put("fsc", mod.getFsc());
    //           item.put("port", mod.getPort());
    //           item.put("point", mod.getPoint());
    //           item.put("terminal", mod.getTerminal());
    //           item.put("terminal_name", mod.getTerminal_name());
    //           item.put("depot", mod.getDepot());
    //           data.add(item);
    //       }
    //       return data;
    //    }
    //    /**
    //     * Format Point as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatPointListAsJSON(List<LoaPointMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaPointMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("region", mod.getRegion());
    //            item.put("fsc", mod.getFsc());
    //            item.put("port", mod.getPort());
    //            item.put("point", mod.getPoint());
    //            item.put("point_name", mod.getPoint_name());
    //            item.put("terminal", mod.getTerminal());
    //            item.put("depot", mod.getDepot());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    //    /**
    //     * Format Depot as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatDepotListAsJSON(List<LaDepotMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaDepotMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("region", mod.getRegion());
    //            item.put("fsc", mod.getFsc());
    //            item.put("port", mod.getPort());
    //            item.put("point", mod.getPoint_code());
    //            item.put("terminal", mod.getTerminal());
    //            item.put("depot", mod.getDepot());
    //            item.put("depot_name", mod.getDepot_name());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    //    /**
    //     * Format User as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatUserListAsJSON(List<LoaUserMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaUserMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("fsc", mod.getFsc_code());
    //            item.put("userID", mod.getUser_id());
    //            item.put("userName", mod.getUser_name());
    //            data.add(item);
    //        }
    //        return data;
    //    }


    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setMngrID(String mngrID) {
        this.mngrID = mngrID;
    }

    public String getMngrID() {
        return mngrID;
    }


    public void setPkEmpSeqNo(Long pkEmpSeqNo) {
        this.pkEmpSeqNo = pkEmpSeqNo;
    }

    public Long getPkEmpSeqNo() {
        return pkEmpSeqNo;
    }

}
