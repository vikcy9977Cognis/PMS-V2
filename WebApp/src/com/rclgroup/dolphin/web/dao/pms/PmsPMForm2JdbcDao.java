/*-----------------------------------------------------------------------------------------------------------
PmsPMForm2JdbcDao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 08/08/15
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.exception.CustomDataAccessException;
import com.rclgroup.dolphin.web.model.pms.PmsAttachFileMod;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMDetailMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;

import com.rclgroup.dolphin.web.model.pms.PmsPmHDRMod;
import com.rclgroup.dolphin.web.model.pms.PmsUserAuthorizeMod;
import com.rclgroup.dolphin.web.util.PmsLogUtil;

import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class PmsPMForm2JdbcDao extends RrcStandardDao implements PmsPMForm2Dao {
    private static final String PRR_PM_OVERALL_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_OVERALL_GET";

    private static final String PRR_PM_HDR_INS_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_HDR_INS";

    private static final String PRR_PM_HDR_UPD_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_HDR_UPD";

    private static final String PRR_PM_HDR_CALC_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_HDR_CALC";

    private static final String PRR_PM_HDR_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_HDR_GET";

    private static final String PRR_PM_PD_IND_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_PD_IND_GET";
    private static final String PRR_PM_COM_IND_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_COM_IND_GET";
    private static final String PRR_PM_DEP_IND_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_DEP_IND_GET";
    private static final String PRR_PM_IDV_IND_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_IDV_IND_GET";

    private static final String PRR_PM_COM_IND_INS_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_COM_IND_INS";
    private static final String PRR_PM_DEP_IND_INS_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_DEP_IND_INS";
    private static final String PRR_PM_IDV_IND_INS_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_IDV_IND_INS";


    private static final String PRR_PM_SUBMIT_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_SUBMIT";
    private static final String PRR_PM_APPROVE_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_APPROVE";
    private static final String PRR_PM_COM_IND_UPD_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_COM_IND_UPD";
    private static final String PRR_PM_DEP_IND_UPD_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_DEP_IND_UPD";
    private static final String PRR_PM_IDV_IND_UPD_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_IDV_IND_UPD";
    private static final String PRR_PM_DEP_IND_DEL_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_DEP_IND_DEL";
    private static final String PRR_PM_IDV_IND_DEL_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_IDV_IND_DEL";

    private static final String PRR_PM_USER_AUTH_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_USER_AUTH_GET";


    private GetPMOverallProcedure getPMOverallProcedure;
    private InsertPMHeaderProcedure insertPMHeaderProcedure;
    private UpdatePMHeaderProcedure updatePMHeaderProcedure;
    private CalculatePMProcedure calculatePMProcedure;
    private GetPMHDRProcedure getPMHDRProcedure;

    private GetIndicatorListProcedure getPresidentDirectiveProcedure;
    private GetIndicatorListProcedure getCompanyCoreValuesProcedure;
    private GetIndicatorListProcedure getDepartmentCoreValuesProcedure;
    private GetIndicatorListProcedure getIndividualKPIsProcedure;

    private InsertCompanyCoreValueProcedue insertCompanyCoreValueProcedue;
    private InsertDepartmentCoreValueProcedure insertDepartmentCoreValueProcedure;
    private InsertIndividualKPIProcedure insertIndividualKPIProcedure;

    private UpdateCompanyCoreValueProcedure updateCompanyCoreValueProcedure;
    private UpdateDepartmentCoreValueProcedure updateDepartmentCoreValueProcedure;
    private UpdateIndividualKPIProcedure updateIndividualKPIProcedure;

    private DeleteDepartmentCoreValueProcedure deleteDepartmentCoreValueProcedure;
    private DeleteIndividualKPIProcedure deleteIndividualKPIProcedure;

    private ApprovePMProcedure approvePMProcedure;
    private SubmitPMProcedure submitPMProcedure;

    private GetUserAuthorizeProcedue getUserAuthorizeProcedue;

    // Attach files
    private GetAttachFileProcedure getAttachFileProcedure;
    private InsertAttachFileProcedure insertAttachFileProcedure;
    private DeleteAttachFileProcedure deleteAttachFileProcedure;

    public PmsPMForm2JdbcDao() {
        super();
    }

    /**
     * @throws Exception
     */
    public void initDao() throws Exception {
        super.initDao();

        getPMOverallProcedure =
                new GetPMOverallProcedure(getJdbcTemplate(), PRR_PM_OVERALL_GET_STORED_PROCEDURE,
                                          new PMOverallRowMapper());
        insertPMHeaderProcedure =
                new InsertPMHeaderProcedure(getJdbcTemplate(),
                                            PRR_PM_HDR_INS_STORED_PROCEDURE);
        updatePMHeaderProcedure =
                new UpdatePMHeaderProcedure(getJdbcTemplate(),
                                            PRR_PM_HDR_UPD_STORED_PROCEDURE);

        calculatePMProcedure =
                new CalculatePMProcedure(getJdbcTemplate(), PRR_PM_HDR_CALC_STORED_PROCEDURE);

        getPMHDRProcedure =
                new GetPMHDRProcedure(getJdbcTemplate(), PRR_PM_HDR_GET_STORED_PROCEDURE,
                                      new PMHDRRowMapper());

        getPresidentDirectiveProcedure =
                new GetIndicatorListProcedure(getJdbcTemplate(),
                                              PRR_PM_PD_IND_GET_STORED_PROCEDURE,
                                              new PMDetailRowMapper());
        getCompanyCoreValuesProcedure =
                new GetIndicatorListProcedure(getJdbcTemplate(),
                                              PRR_PM_COM_IND_GET_STORED_PROCEDURE,
                                              new PMDetailRowMapper());

        getDepartmentCoreValuesProcedure =
                new GetIndicatorListProcedure(getJdbcTemplate(),
                                              PRR_PM_DEP_IND_GET_STORED_PROCEDURE,
                                              new PMDetailRowMapper());

        getIndividualKPIsProcedure =
                new GetIndicatorListProcedure(getJdbcTemplate(),
                                              PRR_PM_IDV_IND_GET_STORED_PROCEDURE,
                                              new PMDetailRowMapper());

        insertCompanyCoreValueProcedue =
                new InsertCompanyCoreValueProcedue(getJdbcTemplate(),
                                                   PRR_PM_COM_IND_INS_STORED_PROCEDURE);

        insertDepartmentCoreValueProcedure =
                new InsertDepartmentCoreValueProcedure(getJdbcTemplate(),
                                                       PRR_PM_DEP_IND_INS_STORED_PROCEDURE);

        insertIndividualKPIProcedure =
                new InsertIndividualKPIProcedure(getJdbcTemplate(),
                                                 PRR_PM_IDV_IND_INS_STORED_PROCEDURE);

        submitPMProcedure =
                new SubmitPMProcedure(getJdbcTemplate(), PRR_PM_SUBMIT_STORED_PROCEDURE);

        approvePMProcedure =
                new ApprovePMProcedure(getJdbcTemplate(), PRR_PM_APPROVE_STORED_PROCEDURE);
        updateCompanyCoreValueProcedure =
                new UpdateCompanyCoreValueProcedure(getJdbcTemplate(),
                                                    PRR_PM_COM_IND_UPD_STORED_PROCEDURE);

        updateDepartmentCoreValueProcedure =
                new UpdateDepartmentCoreValueProcedure(getJdbcTemplate(),
                                                       PRR_PM_DEP_IND_UPD_STORED_PROCEDURE);

        updateIndividualKPIProcedure =
                new UpdateIndividualKPIProcedure(getJdbcTemplate(),
                                                 PRR_PM_IDV_IND_UPD_STORED_PROCEDURE);

        deleteDepartmentCoreValueProcedure =
                new DeleteDepartmentCoreValueProcedure(getJdbcTemplate(),
                                                       PRR_PM_DEP_IND_DEL_STORED_PROCEDURE);

        deleteIndividualKPIProcedure =
                new DeleteIndividualKPIProcedure(getJdbcTemplate(),
                                                 PRR_PM_IDV_IND_DEL_STORED_PROCEDURE);

        getUserAuthorizeProcedue =
                new GetUserAuthorizeProcedue(getJdbcTemplate(),
                                             PRR_PM_USER_AUTH_GET_STORED_PROCEDURE);

        getAttachFileProcedure =
                new GetAttachFileProcedure(getJdbcTemplate(),
                                                   new AttachFileRowMapper());
        insertAttachFileProcedure =
                new InsertAttachFileProcedure(getJdbcTemplate());
        deleteAttachFileProcedure =
                new DeleteAttachFileProcedure(getJdbcTemplate());
    }

    @Override
    public List<PmsPMOverallMod> getPMOverall(String userID, String pmKey,
                                              Long empSeqNo,
                                              Integer pmYear) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_YEAR", pmYear);


        PmsLogUtil.dumpInputParams(mapParams);

        return getPMOverallProcedure.getList(mapParams);
    }

    @Override
    public Map<String, Object> insertPMHeader(String userID, String pmKey,
                                              PmsPmHDRMod pmHDR) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_PM_YEAR", pmHDR.getPm_year());
        mapParams.put("P_I_PM_PERIOD", pmHDR.getPm_period());
        mapParams.put("P_I_EMP_SEQNO", pmHDR.getEmp_seqno());
        mapParams.put("P_I_P3_COMMENT1", pmHDR.getPart3_comment1());
        mapParams.put("P_I_P3_COMMENT2", pmHDR.getPart3_comment2());
        mapParams.put("P_I_P3_COMMENT3", pmHDR.getPart3_comment3());
        mapParams.put("P_I_P3_COMMENT4", pmHDR.getPart3_comment4());

        PmsLogUtil.dumpInputParams(mapParams);

        return insertPMHeaderProcedure.insert(mapParams);
    }

    @Override
    public Map<String, Object> updatePMHeader(String userID, String pmKey,
                                              PmsPmHDRMod pmHDR) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_HDR_SEQNO", pmHDR.getHdr_seqno());
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_PM_YEAR", pmHDR.getPm_year());
        mapParams.put("P_I_PM_PERIOD", pmHDR.getPm_period());
        mapParams.put("P_I_EMP_SEQNO", pmHDR.getEmp_seqno());
        mapParams.put("P_I_P3_COMMENT1", pmHDR.getPart3_comment1());
        mapParams.put("P_I_P3_COMMENT2", pmHDR.getPart3_comment2());
        mapParams.put("P_I_P3_COMMENT3", pmHDR.getPart3_comment3());
        mapParams.put("P_I_P3_COMMENT4", pmHDR.getPart3_comment4());

        PmsLogUtil.dumpInputParams(mapParams);

        return updatePMHeaderProcedure.update(mapParams);
    }

    @Override
    public Map<String, Object> calculatePM(String userID, String pmKey,
                                           Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return calculatePMProcedure.calc(mapParams);
    }

    @Override
    public PmsPmHDRMod getPMHDR(String userID, Long empSeqNo, String pmKey,
                                Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return getPMHDRProcedure.get(mapParams);
    }

    @Override
    public List<PmsPMDetailMod> getPresidentDirectives(String userID,
                                                       Long empSeqNo,
                                                       String pmKey,
                                                       Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return getPresidentDirectiveProcedure.list(mapParams);
    }

    @Override
    public List<PmsPMDetailMod> getCompanyCoreValues(String userID,
                                                     Long empSeqNo,
                                                     String pmKey,
                                                     Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return getCompanyCoreValuesProcedure.list(mapParams);
    }

    @Override
    public Map<String, Object> insertCompanyCoreValue(String userID,
                                                      String pmKey,
                                                      Long pmHDRSeqNo,
                                                      Long indicatorSeqNo,
                                                      Double selfRating) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
        mapParams.put("P_I_IND_SEQNO", indicatorSeqNo);
        mapParams.put("P_I_SELF_RATING", selfRating);

        PmsLogUtil.dumpInputParams(mapParams);

        return insertCompanyCoreValueProcedue.insert(mapParams);
    }

    @Override
    public Map<String, Object> updateCompanyCoreValue(String userID,
                                                      final Long empSeqNo,
                                                      String pmKey,
                                                      PmsPMDetailMod pmDetail) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
        mapParams.put("P_I_IND_SEQNO", pmDetail.getInd_seqno());
        mapParams.put("P_I_SELF_RATING", pmDetail.getInd_self_rating());
        mapParams.put("P_I_RATING", pmDetail.getInd_rating());
        mapParams.put("P_I_MNGR_COMMENT", pmDetail.getRemark());

        PmsLogUtil.dumpInputParams(mapParams);

        return updateCompanyCoreValueProcedure.update(mapParams);
    }

    @Override
    public List<PmsPMDetailMod> getDepartmentCoreValues(String userID,
                                                        Long empSeqNo,
                                                        String pmKey,
                                                        Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return getDepartmentCoreValuesProcedure.list(mapParams);
    }

    @Override
    public Map<String, Object> insertDepartmentCoreValue(String userID,
                                                         String pmKey,
                                                         Long pmHDRSeqNo,
                                                         Long indicatorSeqNo,
                                                         Double weightage,
                                                         Double selfRating) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
        mapParams.put("P_I_IND_SEQNO", indicatorSeqNo);
        mapParams.put("P_I_WEIGHTAGE", weightage);
        mapParams.put("P_I_SELF_RATING", selfRating);

        PmsLogUtil.dumpInputParams(mapParams);

        return insertDepartmentCoreValueProcedure.insert(mapParams);
    }

    @Override
    public Map<String, Object> updateDepartmentCoreValue(String userID,
                                                         Long empSeqNo,
                                                         String pmKey,
                                                         PmsPMDetailMod pmDetail) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
        mapParams.put("P_I_IND_SEQNO", pmDetail.getInd_seqno());
        mapParams.put("P_I_WEIGHTAGE", pmDetail.getInd_weight_percentage());
        mapParams.put("P_I_SELF_RATING", pmDetail.getInd_self_rating());
        mapParams.put("P_I_RATING", pmDetail.getInd_rating());
        mapParams.put("P_I_MNGR_COMMENT", pmDetail.getRemark());

        PmsLogUtil.dumpInputParams(mapParams);

        return updateDepartmentCoreValueProcedure.update(mapParams);

    }

    @Override
    public Map<String, Object> deleteDepartmentCoreValue(String userID,
                                                         final Long empSeqNo,
                                                         String pmKey,
                                                         PmsPMDetailMod pmDetail) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
        mapParams.put("P_I_IND_SEQNO", pmDetail.getInd_seqno());

        PmsLogUtil.dumpInputParams(mapParams);

        return deleteDepartmentCoreValueProcedure.delete(mapParams);
    }

    @Override
    public List<PmsPMDetailMod> getIndividualKPIs(String userID, Long empSeqNo,
                                                  String pmKey,
                                                  Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return getIndividualKPIsProcedure.list(mapParams);
    }

    @Override
    public Map<String, Object> insertIndividualKPI(String userID, String pmKey,
                                                   Long pmHDRSeqNo,
                                                   Long bscSeqNo,
                                                   String indDesc,
                                                   String indSlab0,
                                                   String indSlab1,
                                                   String indSlab2,
                                                   String indSlab3,
                                                   String indSlab4,
                                                   String indSlab5,
                                                   Double weightage,
                                                   String result,
                                                   Double rating) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
        mapParams.put("P_I_BSC_SEQNO", bscSeqNo);
        mapParams.put("P_I_IND_DESC", indDesc);
        mapParams.put("P_I_IND_SLAB_0", indSlab0);
        mapParams.put("P_I_IND_SLAB_1", indSlab1);
        mapParams.put("P_I_IND_SLAB_2", indSlab2);
        mapParams.put("P_I_IND_SLAB_3", indSlab3);
        mapParams.put("P_I_IND_SLAB_4", indSlab4);
        mapParams.put("P_I_IND_SLAB_5", indSlab5);
        mapParams.put("P_I_WEIGHTAGE", weightage);
        mapParams.put("P_I_RESULT", result);
        mapParams.put("P_I_RATING", rating);

        PmsLogUtil.dumpInputParams(mapParams);

        return insertIndividualKPIProcedure.insert(mapParams);

    }

    @Override
    public Map<String, Object> updateIndividualKPI(String userID, String pmKey,
                                                   Long empSeqNo,
                                                   PmsPMDetailMod pmDetail) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_BSC_SEQNO", pmDetail.getBsc_seqno());
        mapParams.put("P_I_IND_DESC", pmDetail.getInd_description());
        mapParams.put("P_I_IND_SLAB_0", pmDetail.getInd_slab_0());
        mapParams.put("P_I_IND_SLAB_1", pmDetail.getInd_slab_1());
        mapParams.put("P_I_IND_SLAB_2", pmDetail.getInd_slab_2());
        mapParams.put("P_I_IND_SLAB_3", pmDetail.getInd_slab_3());
        mapParams.put("P_I_IND_SLAB_4", pmDetail.getInd_slab_4());
        mapParams.put("P_I_IND_SLAB_5", pmDetail.getInd_slab_5());
        mapParams.put("P_I_WEIGHTAGE", pmDetail.getInd_weight_percentage());
        mapParams.put("P_I_RESULT", pmDetail.getInd_result());
        mapParams.put("P_I_RATING", pmDetail.getInd_rating());

        PmsLogUtil.dumpInputParams(mapParams);

        return updateIndividualKPIProcedure.update(mapParams);
    }

    @Override
    public Map<String, Object> deleteIndividualKPI(String userID, String pmKey,
                                                   PmsPMDetailMod pmDetail) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_DTL_SEQNO", pmDetail.getDtl_seqno());
        mapParams.put("P_I_PM_KEY", pmKey);
        mapParams.put("P_I_HDR_SEQNO", pmDetail.getHdr_seqno());
        mapParams.put("P_I_BSC_SEQNO", pmDetail.getBsc_seqno());

        PmsLogUtil.dumpInputParams(mapParams);

        return deleteIndividualKPIProcedure.delete(mapParams);

    }

    @Override
    public Map<String, Object> submitPM(String userID, Long empSeqNo,
                                        Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return submitPMProcedure.submit(mapParams);
    }

    @Override
    public Map<String, Object> approvePM(String userID, Long empSeqNo,
                                         Long pmHDRSeqNo, String approveReject,
                                         String mgrLv1Comment,
                                         String mgrLv2Comment) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
        mapParams.put("P_I_APPROVE_REJECT", approveReject);
        mapParams.put("P_I_MNGR_LV1_COMMENT", mgrLv1Comment);
        mapParams.put("P_I_MNGR_LV2_COMMENT", mgrLv2Comment);

        PmsLogUtil.dumpInputParams(mapParams);

        return approvePMProcedure.approve(mapParams);

    }

    @Override
    public PmsUserAuthorizeMod getUserAuthorize(String userID, Long empSeqNo,
                                                Long pmHDRSeqNo) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_EMP_SEQNO", empSeqNo);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);

        PmsLogUtil.dumpInputParams(mapParams);

        return getUserAuthorizeProcedue.get(mapParams);
    }

    public Map<String, String> insertAttachFile(Integer pmHDRSeqNo,
                                                String fileName,
                                                String filePath,
                                                String userID) {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
        mapParams.put("P_I_FILE_NAME", fileName);
        mapParams.put("P_I_FILE_PATH", filePath);
      
        PmsLogUtil.dumpInputParams(mapParams);
        return insertAttachFileProcedure.insertAttachFile(mapParams);
    }

    protected class InsertAttachFileProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME =
            "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_FILE_INS";

        protected InsertAttachFileProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
         
            declareParameter(new SqlOutParameter("P_O_UPLOAD_HDR_SEQNO",
                                                 OracleTypes.INTEGER));
            declareParameter(new SqlOutParameter("P_O_UPLOAD_DTL_SEQNO",
                                                 OracleTypes.INTEGER));         
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.INTEGER));
            declareParameter(new SqlInOutParameter("P_I_FILE_NAME",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_FILE_PATH",
                                                   OracleTypes.VARCHAR));
            compile();
        }

        protected Map<String, String> insertAttachFile(Map mapParams) {
            Map<String, String> retMap = new HashMap<String, String>();
            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);
                Integer uploadHDRSeqNo =
                    (Integer)outMap.get("P_O_UPLOAD_HDR_SEQNO");
                Integer uploadDTLSeqNo =
                    (Integer)outMap.get("P_O_UPLOAD_DTL_SEQNO");
                retMap.put("P_O_UPLOAD_HDR_SEQNO",
                           RutString.nullToStr(uploadHDRSeqNo));
                retMap.put("P_O_UPLOAD_DTL_SEQNO",
                           RutString.nullToStr(uploadDTLSeqNo));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return retMap;
        }
    }

    public List<PmsAttachFileMod> getAttachFileList(Integer pmHDRSeqNo,
                                                    String userID) {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
        PmsLogUtil.dumpInputParams(mapParams);
        return getAttachFileProcedure.getAttachFile(mapParams);
    }

    protected class GetAttachFileProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME =
            "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_FILE_GET";

        protected GetAttachFileProcedure(JdbcTemplate jdbcTemplate,
                                         RowMapper rowMapper) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR,
                                                   rowMapper));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.INTEGER,
                                                   rowMapper));
          
            compile();
        }

        protected List<PmsAttachFileMod> getAttachFile(Map mapParams) {
            List<PmsAttachFileMod> resultList =
                new ArrayList<PmsAttachFileMod>();
            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);
                resultList = (List<PmsAttachFileMod>)outMap.get("P_O_DATA");
            } catch (Exception ex) {

                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return resultList;
        }
    }

    protected class AttachFileRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int row) throws SQLException {
            PmsAttachFileMod bean = new PmsAttachFileMod();
            bean.setUploadHDRSeqNo(rs.getInt("PK_UPLOAD_HDR_SEQ"));
            bean.setUploadDTLSeqNo(rs.getInt("PK_UPLOAD_DTL_SEQ"));
            bean.setPmHDRSeqNo(rs.getInt("FK_REFERENCE_SEQ"));
            bean.setFileName(RutString.nullToStr(rs.getString("FILE_NAME")));
            bean.setFileFullPath(RutString.nullToStr(rs.getString("ARCHIVED_PATH")));
            bean.setRecordStatus(RutString.nullToStr(rs.getString("RECORD_STATUS")));
            bean.setRecordAddUser(RutString.nullToStr(rs.getString("RECORD_ADD_USER")));
            bean.setRecordAddDate(rs.getTimestamp("RECORD_ADD_DATE"));
            bean.setRecordChangeUser(RutString.nullToStr(rs.getString("RECORD_CHANGE_USER")));
            bean.setRecordChangeDate(rs.getTimestamp("RECORD_CHANGE_DATE"));
            return bean;
        }
    }

    public boolean deleteAttachFile(Integer uploadHDRSeqNo,
                                    Integer uploadDelSeqNo, Integer pmHDRSeqNo,
                                    String userID) {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_UPLOAD_HDR_SEQNO", uploadHDRSeqNo);
        mapParams.put("P_I_UPLOAD_DTL_SEQNO", uploadDelSeqNo);
        mapParams.put("P_I_HDR_SEQNO", pmHDRSeqNo);
     
        PmsLogUtil.dumpInputParams(mapParams);
        return deleteAttachFileProcedure.deleteAttachFile(mapParams);
    }

    protected class DeleteAttachFileProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME =
            "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_FILE_DEL";

        protected DeleteAttachFileProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
            
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_UPLOAD_HDR_SEQNO",
                                                   OracleTypes.INTEGER));
            declareParameter(new SqlInOutParameter("P_I_UPLOAD_DTL_SEQNO",
                                                   OracleTypes.INTEGER));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.INTEGER));
         
            compile();
        }

        protected boolean deleteAttachFile(Map mapParams) {
            boolean isSuccess = false;
            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);
                isSuccess = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return isSuccess;
        }
    }

    private class GetPMOverallProcedure extends StoredProcedure {
        private GetPMOverallProcedure(JdbcTemplate jdbcTemplate, String spName,
                                      RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));

            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR,
                                                   rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR,
                                                   rowMapper));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER,
                                                   rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                   OracleTypes.NUMBER,
                                                   rowMapper));


            compile();
        }

        private List<PmsPMOverallMod> getList(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsPMOverallMod> outList = new ArrayList<PmsPMOverallMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsPMOverallMod>)outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }
    }

    private class PMOverallRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            PmsPMOverallMod mod = new PmsPMOverallMod();
            mod.setEmpSeqNo(Long.valueOf(rs.getLong("FK_EMP_SEQNO")));
            mod.setPmYear(Integer.valueOf(rs.getInt("PM_YEAR")));
            mod.setPmPeriod(Integer.valueOf(rs.getInt("PM_PERIOD")));
            mod.setComRatingPercent(Double.valueOf(rs.getDouble("COM_RATING_PERCENT")));
            mod.setPresidentRatingPercent(Double.valueOf(rs.getDouble("PRESIDENT_RATING_PERCENT")));
            mod.setIndividualRatingPercent(Double.valueOf(rs.getDouble("INDIVIDUAL_RATING_PERCENT")));
            mod.setOverallRatingPercent(Double.valueOf(rs.getDouble("OVERALL_RATING_PERCENT")));
            return mod;
        }
    }

    private class InsertPMHeaderProcedure extends StoredProcedure {
        private InsertPMHeaderProcedure(JdbcTemplate jdbcTemplate,
                                        String spName) {
            super(jdbcTemplate, spName);


            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));


            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));

            declareParameter(new SqlOutParameter("P_O_HDR_SEQNO",
                                                 OracleTypes.NUMBER));

            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT1",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT2",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT3",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT4",
                                                   OracleTypes.VARCHAR));

            compile();
        }

        private Map<String, Object> insert(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class UpdatePMHeaderProcedure extends StoredProcedure {
        private UpdatePMHeaderProcedure(JdbcTemplate jdbcTemplate,
                                        String spName) {
            super(jdbcTemplate, spName);


            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT1",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT2",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT3",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_P3_COMMENT4",
                                                   OracleTypes.VARCHAR));

            compile();
        }

        private Map<String, Object> update(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class CalculatePMProcedure extends StoredProcedure {
        private CalculatePMProcedure(JdbcTemplate jdbcTemplate,
                                     String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));

            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));

            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            compile();
        }

        private Map<String, Object> calc(Map mapParams) {
            Map outMap = new HashMap();
            try {
                outMap = execute(mapParams);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class GetPMHDRProcedure extends StoredProcedure {
        private GetPMHDRProcedure(JdbcTemplate jdbcTemplate, String spName,
                                  RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private PmsPmHDRMod get(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsPmHDRMod> outList = new ArrayList<PmsPmHDRMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsPmHDRMod>)outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList.size() > 0 ? outList.get(0) : null;
        }
    }

    private class PMHDRRowMapper implements RowMapper {
        @Override
        public PmsPmHDRMod mapRow(ResultSet rs, int i) throws SQLException {
            PmsPmHDRMod mod = new PmsPmHDRMod();
            mod.setHdr_seqno(Long.valueOf(rs.getLong("PK_HDR_SEQNO")));
            mod.setPm_year(Integer.valueOf(rs.getInt("PM_YEAR")));
            mod.setPm_period(Integer.valueOf(rs.getInt("PM_PERIOD")));
            mod.setEmp_seqno(Long.valueOf(rs.getLong("FK_EMP_SEQNO")));
            mod.setEmp_id(RutString.nullToStr(rs.getString("EMP_ID")));
            mod.setEmp_name(RutString.nullToStr(rs.getString("EMP_NAME")));
            mod.setJoinDate(rs.getTimestamp("JOIN_DATE"));
            mod.setJoinYear(Integer.valueOf(rs.getInt("JOIN_YEAR")));
            mod.setJoinPeriod(Integer.valueOf(rs.getInt("JOIN_PERIOD")));
            mod.setCom_seqno(Long.valueOf(rs.getLong("FK_COM_SEQNO")));
            mod.setCompanyName(RutString.nullToStr(rs.getString("COM_NAME")));
            mod.setDiv_seqno(Long.valueOf(rs.getLong("FK_DIV_SEQNO")));
            mod.setDivisionName(RutString.nullToStr(rs.getString("DIV_NAME")));
            mod.setDep_seqno(Long.valueOf(rs.getLong("FK_DEP_SEQNO")));
            mod.setDepartmentName(RutString.nullToStr(rs.getString("DEP_NAME")));
            mod.setSec_seqno(Long.valueOf(rs.getLong("FK_SEC_SEQNO")));
            mod.setJob_grade(RutString.nullToStr(rs.getString("JOB_GRADE")));
            mod.setJob_grade_name(RutString.nullToStr(rs.getString("JOB_GRADE_NAME")));
            mod.setJob_brand(RutString.nullToStr(rs.getString("JOB_BRAND")));
            mod.setJob_band_name(RutString.nullToStr(rs.getString("JOB_BRAND_NAME")));
            mod.setManager_lv1(Long.valueOf(rs.getLong("MANAGER_LV1")));
            mod.setApprove_lv1_name(RutString.nullToStr(rs.getString("APPROVE_LV1_NAME")));
            mod.setManager_lv2(Long.valueOf(rs.getLong("MANAGER_LV2")));
            mod.setApprove_lv2_name(RutString.nullToStr(rs.getString("APPROVE_LV2_NAME")));
            mod.setDesignation(RutString.nullToStr(rs.getString("DESIGNATION")));
            mod.setPm_status(RutString.nullToStr(rs.getString("PM_STATUS")));
            mod.setCom_rating(Double.valueOf(rs.getDouble("COM_RATING")));
            mod.setCom_rating_percent(Double.valueOf(rs.getDouble("COM_RATING_PERCENT")));
            mod.setPresident_rating(Double.valueOf(rs.getDouble("PRESIDENT_RATING")));
            mod.setPresident_rating_percent(Double.valueOf(rs.getDouble("PRESIDENT_RATING_PERCENT")));
            mod.setIndividual_rating(Double.valueOf(rs.getDouble("INDIVIDUAL_RATING")));
            mod.setIndividual_rating_percent(Double.valueOf(rs.getDouble("INDIVIDUAL_RATING_PERCENT")));
            mod.setPart3_comment1(RutString.nullToStr(rs.getString("PART3_COMMENT1")));
            mod.setPart3_comment2(RutString.nullToStr(rs.getString("PART3_COMMENT2")));
            mod.setPart3_comment3(RutString.nullToStr(rs.getString("PART3_COMMENT3")));
            mod.setPart3_comment4(RutString.nullToStr(rs.getString("PART3_COMMENT4")));
            mod.setSubmit_by(RutString.nullToStr(rs.getString("SUBMIT_BY")));
            mod.setSubmit_date(rs.getTimestamp("SUBMIT_DATE"));
            mod.setPart4_comment1(RutString.nullToStr(rs.getString("PART4_COMMENT1")));
            mod.setOverall_rating_percent(Double.valueOf(rs.getDouble("OVERALL_RATING_PERCENT")));
            mod.setLv1_approve_by(RutString.nullToStr(rs.getString("LV1_APPROVE_BY")));
            mod.setLv1_approve_date(rs.getTimestamp("LV1_APPROVE_DATE"));
            mod.setPart5_comment1(RutString.nullToStr(rs.getString("PART5_COMMENT1")));
            mod.setLv2_approve_by(RutString.nullToStr(rs.getString("LV2_APPROVE_BY")));
            mod.setLv2_approve_date(rs.getTimestamp("LV2_APPROVE_DATE"));
            mod.setRevision_no(Long.valueOf(rs.getLong("REVISION_NO")));
            mod.setRecordStatus(RutString.nullToStr(rs.getString("RECORD_STATUS")));
            return mod;
        }
    }

    private class GetIndicatorListProcedure extends StoredProcedure {
        private GetIndicatorListProcedure(JdbcTemplate jdbcTemplate,
                                          String spName, RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private List<PmsPMDetailMod> list(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsPMDetailMod> outList = new ArrayList<PmsPMDetailMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsPMDetailMod>)outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }
    }

    private class PMDetailRowMapper implements RowMapper {
        @Override
        public PmsPMDetailMod mapRow(ResultSet rs, int i) throws SQLException {
            PmsPMDetailMod mod = new PmsPMDetailMod();
            mod.setDtl_seqno(Long.valueOf(rs.getLong("PK_DTL_SEQNO")));
            mod.setHdr_seqno(Long.valueOf(rs.getLong("FK_HDR_SEQNO")));
            mod.setPm_dtl_category(RutString.nullToStr(rs.getString("PM_DTL_CATEGORY")));
            mod.setInd_seqno(Long.valueOf(rs.getLong("FK_IND_SEQNO")));
            mod.setInd_name(RutString.nullToStr(rs.getString("IND_NAME")));
            mod.setBsc_name(RutString.nullToStr(rs.getString("BSC_NAME")));
            mod.setBsc_seqno(Long.valueOf(rs.getLong("FK_BSC_SEQNO")));
            mod.setInd_description(RutString.nullToStr(rs.getString("IND_DESCRIPTION")));
            mod.setRemark(RutString.nullToStr(rs.getString("REMARK")));
            mod.setInd_slab_0(RutString.nullToStr(rs.getString("IND_SLAB_0")));
            mod.setInd_slab_1(RutString.nullToStr(rs.getString("IND_SLAB_1")));
            mod.setInd_slab_2(RutString.nullToStr(rs.getString("IND_SLAB_2")));
            mod.setInd_slab_3(RutString.nullToStr(rs.getString("IND_SLAB_3")));
            mod.setInd_slab_4(RutString.nullToStr(rs.getString("IND_SLAB_4")));
            mod.setInd_slab_5(RutString.nullToStr(rs.getString("IND_SLAB_5")));
            mod.setInd_result(RutString.nullToStr(rs.getString("IND_RESULT")));
            mod.setInd_self_rating(Double.valueOf(rs.getDouble("IND_SELF_RATING")));
            mod.setInd_rating(Double.valueOf(rs.getDouble("IND_RATING")));
            mod.setInd_weight_percentage(Double.valueOf(rs.getDouble("IND_WEIGHT_PERCENTAGE")));
            mod.setPrev_ind_self_rating(Double.valueOf(rs.getDouble("PREV_IND_SELF_RATING")));
            mod.setPrev_ind_rating(Double.valueOf(rs.getDouble("PREV_IND_RATING")));
            mod.setPrev_weight_percentage(Double.valueOf(rs.getDouble("PREV_WEIGHT_PERCENTAGE")));
            mod.setRecordStatus(RutString.nullToStr(rs.getString("RECORD_STATUS")));

            return mod;

        }
    }

    private class InsertCompanyCoreValueProcedue extends StoredProcedure {
        private InsertCompanyCoreValueProcedue(JdbcTemplate jdbcTemplate,
                                               String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_DTL_SEQNO",
                                                 OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_SELF_RATING",
                                                   OracleTypes.NUMBER));
            compile();
        }

        private Map<String, Object> insert(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class InsertDepartmentCoreValueProcedure extends StoredProcedure {
        private InsertDepartmentCoreValueProcedure(JdbcTemplate jdbcTemplate,
                                                   String spName) {
            super(jdbcTemplate, spName);


            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_DTL_SEQNO",
                                                 OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_WEIGHTAGE",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_SELF_RATING",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private Map<String, Object> insert(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class InsertIndividualKPIProcedure extends StoredProcedure {
        private InsertIndividualKPIProcedure(JdbcTemplate jdbcTemplate,
                                             String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_DTL_SEQNO",
                                                 OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_BSC_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_DESC",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_0",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_1",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_2",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_3",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_4",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_5",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_WEIGHTAGE",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_RESULT",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_RATING",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private Map<String, Object> insert(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class SubmitPMProcedure extends StoredProcedure {
        private SubmitPMProcedure(JdbcTemplate jdbcTemplate, String spName) {
            super(jdbcTemplate, spName);
            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private Map<String, Object> submit(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class ApprovePMProcedure extends StoredProcedure {
        private ApprovePMProcedure(JdbcTemplate jdbcTemplate, String spName) {
            super(jdbcTemplate, spName);
            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_APPROVE_REJECT",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_MNGR_LV1_COMMENT",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_MNGR_LV2_COMMENT",
                                                   OracleTypes.VARCHAR));

            compile();
        }

        private Map<String, Object> approve(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class UpdateCompanyCoreValueProcedure extends StoredProcedure {
        private UpdateCompanyCoreValueProcedure(JdbcTemplate jdbcTemplate,
                                                String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_DTL_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_SELF_RATING",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_RATING",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_MNGR_COMMENT",
                                                   OracleTypes.VARCHAR));

            compile();
        }

        private Map<String, Object> update(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class UpdateDepartmentCoreValueProcedure extends StoredProcedure {
        private UpdateDepartmentCoreValueProcedure(JdbcTemplate jdbcTemplate,
                                                   String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_DTL_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_WEIGHTAGE",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_SELF_RATING",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_RATING",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_MNGR_COMMENT",
                                                   OracleTypes.VARCHAR));

            compile();
        }

        private Map<String, Object> update(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class UpdateIndividualKPIProcedure extends StoredProcedure {
        private UpdateIndividualKPIProcedure(JdbcTemplate jdbcTemplate,
                                             String spName) {
            super(jdbcTemplate, spName);
            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_DTL_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_BSC_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_DESC",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_0",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_1",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_2",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_3",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_4",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_IND_SLAB_5",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_WEIGHTAGE",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_RESULT",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_RATING",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private Map<String, Object> update(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class DeleteDepartmentCoreValueProcedure extends StoredProcedure {
        private DeleteDepartmentCoreValueProcedure(JdbcTemplate jdbcTemplate,
                                                   String spName) {
            super(jdbcTemplate, spName);
            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_DTL_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_IND_SEQNO",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private Map<String, Object> delete(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class DeleteIndividualKPIProcedure extends StoredProcedure {
        private DeleteIndividualKPIProcedure(JdbcTemplate jdbcTemplate,
                                             String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_DTL_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_BSC_SEQNO",
                                                   OracleTypes.NUMBER));


            compile();
        }

        private Map<String, Object> delete(Map mapParams) {

            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);


            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
            return outMap;
        }

    }

    private class GetUserAuthorizeProcedue extends StoredProcedure {
        private GetUserAuthorizeProcedue(JdbcTemplate jdbcTemplate,
                                         String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VIEW",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_EDIT",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_SUBMIT",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_APPROVE_LV1",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_APPROVE_LV2",
                                                 OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                   OracleTypes.VARCHAR));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                                   OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_HDR_SEQNO",
                                                   OracleTypes.NUMBER));

            compile();
        }

        private PmsUserAuthorizeMod get(Map mapParams) {
            Map outMap = new HashMap();
            PmsUserAuthorizeMod mod = new PmsUserAuthorizeMod();
            List<PmsUserAuthorizeMod> outList =
                new ArrayList<PmsUserAuthorizeMod>();
            try {
                outMap = execute(mapParams);
                //  Map row to entity
                mod.setViewFlag(RutString.nullToStr(outMap.get("P_O_VIEW")));
                mod.setEditFlag(RutString.nullToStr(outMap.get("P_O_EDIT")));
                mod.setSubmitFlag(RutString.nullToStr(outMap.get("P_O_SUBMIT")));
                mod.setApproveLv1Flag(RutString.nullToStr(outMap.get("P_O_APPROVE_LV1")));
                mod.setApproveLv2Flag(RutString.nullToStr(outMap.get("P_O_APPROVE_LV2")));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return mod;
        }
    }


}
