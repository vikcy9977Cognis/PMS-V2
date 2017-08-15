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

import com.rclgroup.dolphin.web.common.RrcStandardUim;


import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMForm2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMFormDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmFormMod;
import com.rclgroup.dolphin.web.model.pms.PmListMod;
import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;
import com.rclgroup.dolphin.web.model.pms.PmsPmHDRMod;
import com.rclgroup.dolphin.web.util.RutString;



import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.dao.DataAccessException;

public class PmsPMWizardUim extends RrcStandardUim implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long pkEmpSeqNo;
    private String jobBrand;
    private PmsPMForm2Dao pmsPMForm2Dao;
    
    public PmsPMWizardUim() {
        super();
    }
    public PmsPMForm2Dao getPmsPMForm2Dao() {
        return pmsPMForm2Dao;
    }

    public void setPmsPMForm2Dao(PmsPMForm2Dao pmsPMForm2Dao) {
        this.pmsPMForm2Dao = pmsPMForm2Dao;
    }
    
    public List<PmsPMOverallMod> getPMOverall(String pmKey, Long empSeqNo, Integer pmYear) {
        //pmKey = dcspms01
        List<PmsPMOverallMod> list =
            this.pmsPMForm2Dao.getPMOverall(super.getPrsnLogIdOfUser(), pmKey, empSeqNo, pmYear);
        return list;
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
    
    public Long insertPMHeader(final String pmKey, final PmsPmHDRMod pmsHDRMod) throws Exception{
        Long hdrSeqNo = null;
        pmsHDRMod.setEmp_seqno(this.pkEmpSeqNo);
        final Map<String, Object> maps = this.pmsPMForm2Dao.insertPMHeader(super.getPrsnLogIdOfUser(), pmKey, pmsHDRMod);
        final String valid = RutString.nullToStr(maps.get("P_O_VALID"));
        if(!valid.equalsIgnoreCase("Y")){
            throw new Exception(RutString.nullToStr(maps.get("P_O_ERROR_MSG")));
        }
        hdrSeqNo = Long.parseLong(RutString.nullToStr(maps.get("P_O_HDR_SEQNO")));
        return hdrSeqNo;
    }
    
    public Long insertCompanyCoreValue(final String pmKey, final Long hdrSeqNo, final JSONArray jsArray) throws Exception{
        Long dtlSeqNo = null;
    
        final String userID = super.getPrsnLogIdOfUser();
        for(int i=0;i<jsArray.size();i++){
            final JSONObject obj = (JSONObject)jsArray.get(i);
            final Double selfRating = Double.parseDouble(RutString.nullToStr(obj.get("self_rating")));
            final Long indicatorSeqNo = Long.parseLong(RutString.nullToStr(obj.get("ind_seqno").toString()));
            final Map<String, Object> maps = this.pmsPMForm2Dao.insertCompanyCoreValue(userID, pmKey, hdrSeqNo, indicatorSeqNo, selfRating);
            final String valid = RutString.nullToStr(maps.get("P_O_VALID"));
            if(!valid.equalsIgnoreCase("Y")){
                throw new Exception(RutString.nullToStr(maps.get("P_O_ERROR_MSG")));
            }
            dtlSeqNo = Long.parseLong(RutString.nullToStr(maps.get("P_O_DTL_SEQNO")));
        }
        return dtlSeqNo;
    }
    
    public Long insertDepartmentCoreValue(final String pmKey, final Long hdrSeqNo, final JSONArray jsArray) throws Exception{
        Long dtlSeqNo = null;
    
        final String userID = super.getPrsnLogIdOfUser();
        for(int i=0;i<jsArray.size();i++){
            final JSONObject obj = (JSONObject)jsArray.get(i);
            final Double selfRating = Double.parseDouble(RutString.nullToStr(obj.get("self_rating")));
            final Double weightage = Double.parseDouble(RutString.nullToStr(obj.get("weightage")));
            final Long indicatorSeqNo = Long.parseLong(RutString.nullToStr(obj.get("core_value")));
            final Map<String, Object> maps = this.pmsPMForm2Dao.insertDepartmentCoreValue(userID, pmKey, hdrSeqNo, indicatorSeqNo, weightage, selfRating);
            final String valid = RutString.nullToStr(maps.get("P_O_VALID"));
            if(!valid.equalsIgnoreCase("Y")){
                throw new Exception(RutString.nullToStr(maps.get("P_O_ERROR_MSG")));
            }
            dtlSeqNo = Long.parseLong(RutString.nullToStr(maps.get("P_O_DTL_SEQNO")));
        }
        return dtlSeqNo;
    }
    
    public Long insertIndividualKPI(final String pmKey, final Long hdrSeqNo, final JSONArray jsArray) throws Exception{
        Long dtlSeqNo = null;
    
        final String userID = super.getPrsnLogIdOfUser();
        for(int i=0;i<jsArray.size();i++){
            final JSONObject obj = (JSONObject)jsArray.get(i);
            final Double weightage = Double.parseDouble(RutString.nullToStr(obj.get("weightage")));
            final Long bscSeqNo = Long.parseLong(RutString.nullToStr(obj.get("bsc_perspective")));
            final String indDesc = RutString.nullToStr(obj.get("individual_goal"));
            final String indSlab0 = RutString.nullToStr(obj.get("rating_0"));
            final String indSlab1 = RutString.nullToStr(obj.get("rating_1"));
            final String indSlab2 = RutString.nullToStr(obj.get("rating_2"));
            final String indSlab3 = RutString.nullToStr(obj.get("rating_3"));
            final String indSlab4 = RutString.nullToStr(obj.get("rating_4"));
            final String indSlab5 = RutString.nullToStr(obj.get("rating_5"));
            final String result = RutString.nullToStr(obj.get("result"));
            final Double rating = Double.parseDouble(RutString.nullToStr(obj.get("rating")));
            
            final Map<String, Object> maps = this.pmsPMForm2Dao.insertIndividualKPI(userID, pmKey, hdrSeqNo, 
                                                                                    bscSeqNo, indDesc, indSlab0, indSlab1, 
                                                                                    indSlab2, indSlab3, indSlab4, indSlab5, 
                                                                                    weightage, result, rating);
            final String valid = RutString.nullToStr(maps.get("P_O_VALID"));
            if(!valid.equalsIgnoreCase("Y")){
                throw new Exception(RutString.nullToStr(maps.get("P_O_ERROR_MSG")));
            }
            dtlSeqNo = Long.parseLong(RutString.nullToStr(maps.get("P_O_DTL_SEQNO")));
        }
        return dtlSeqNo;
    }
    
    public void calculatePMHeader(final String pmKey, final Long hdrSeqNo) throws Exception{
        final Map<String, Object> maps = this.pmsPMForm2Dao.calculatePM(super.getPrsnLogIdOfUser(), pmKey, hdrSeqNo);
        final String valid = RutString.nullToStr(maps.get("P_O_VALID"));
        if(!valid.equalsIgnoreCase("Y")){
            throw new Exception(RutString.nullToStr(maps.get("P_O_ERROR_MSG")));
        }
    }
    
    public void pmSubmit(final Long hdrSeqNo) throws Exception{
        final Map<String, Object> maps = this.pmsPMForm2Dao.submitPM(super.getPrsnLogIdOfUser(), this.pkEmpSeqNo, hdrSeqNo);
        final String valid = RutString.nullToStr(maps.get("P_O_VALID"));
        if(!valid.equalsIgnoreCase("Y")){
            throw new Exception(RutString.nullToStr(maps.get("P_O_ERROR_MSG")));
        }
    }
    
    public void setPkEmpSeqNo(Long pkEmpSeqNo) {
        this.pkEmpSeqNo = pkEmpSeqNo;
    }

    public Long getPkEmpSeqNo() {
        return pkEmpSeqNo;
    }

    public String getJobBrand() {
        return jobBrand;
    }

    public void setJobBrand(String jobBrand) {
        this.jobBrand = jobBrand;
    }
}
