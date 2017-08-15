package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;

import com.rclgroup.dolphin.web.util.PmsLogUtil;

import com.rclgroup.dolphin.web.util.RutString;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class PmsCommon2JdbcDao extends RrcStandardDao implements PmsCommon2Dao {
    private static final String PRR_JOB_WEIGHTAGE_LIST_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_COMMON.PRR_JOB_WEIGHTAGE_LIST";

    private static final String PRR_INDICATOR_LIST_STORED_PROCEDURE = "VASAPPS.PCR_PMS_COMMON.PRR_INDICATOR_LIST";

    private static final String PRR_JOB_WEIGHTAGE_GET_STORED_PROCEDURE = "VASAPPS.PCR_PMS_COMMON.PRR_JOB_WEIGHTAGE_GET";

    private static final String PRR_PM_ACT_GET_STORED_PROCEDURE = "VASAPPS.PCR_PMS_COMMON.PRR_PM_ACT_GET";

    private static final String PRR_PM_YEAR_ACTIVE_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_COMMON.PRR_PM_YEAR_ACTIVE_GET";
    private static final String PRR_INDICATOR_DTL_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_COMMON.PRR_INDICATOR_DTL_GET";
    
    private static final String PRR_ENCRYPT_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_COMMON.PRR_ENCRYPT";
    
    
    private GetJobWeightageListProcedure getJobWeightageListProcedure;
    private GetIndicatorListProcedure getIndicatorListProcedure;
    private GetJobWeightageProcedure getJobWeightageProcedure;
    private GetActivityListProcedure getActivityListProcedure;
    private GetActivePMYearPeriodProcedure getActivePMYearPeriodProcedure;
    private GetIndicatorDetailProcedure getIndicatorDetailProcedure;
    private GetEncryptPMKeyProcedure getEncryptPMKeyProcedure;
    /**
     * @throws Exception
     */
    public void initDao() throws Exception {
        super.initDao();

        getJobWeightageListProcedure =
            new GetJobWeightageListProcedure(getJdbcTemplate(), PRR_JOB_WEIGHTAGE_LIST_STORED_PROCEDURE,
                                             new JobWeightageRowMapper());

        getJobWeightageProcedure =
            new GetJobWeightageProcedure(getJdbcTemplate(), PRR_JOB_WEIGHTAGE_GET_STORED_PROCEDURE,
                                         new JobWeightageRowMapper());

        getIndicatorListProcedure =
            new GetIndicatorListProcedure(getJdbcTemplate(), PRR_INDICATOR_LIST_STORED_PROCEDURE,
                                          new IndicatorRowMapper());
        getActivityListProcedure =
            new GetActivityListProcedure(getJdbcTemplate(), PRR_PM_ACT_GET_STORED_PROCEDURE, new PMActivityRowMapper());
        getActivePMYearPeriodProcedure =
            new GetActivePMYearPeriodProcedure(getJdbcTemplate(), PRR_PM_YEAR_ACTIVE_GET_STORED_PROCEDURE);
        
        getIndicatorDetailProcedure = new GetIndicatorDetailProcedure(getJdbcTemplate(), PRR_INDICATOR_DTL_GET_STORED_PROCEDURE);
        
        getEncryptPMKeyProcedure = new GetEncryptPMKeyProcedure(getJdbcTemplate(), PRR_ENCRYPT_STORED_PROCEDURE);
    }


    @Override
    public List<PmsJobWeightageMod> getJobWeightageList(String userID, Integer pmYear,
                                                        Integer pmPeriod) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_YEAR", pmYear);
        mapParams.put("P_I_PM_PERIOD", pmPeriod);

        PmsLogUtil.dumpInputParams(mapParams);

        return getJobWeightageListProcedure.getList(mapParams);
    }

    @Override
    public List<PmsIndicatorMod> getIndicatorList(String userID, Integer pmYear, Integer pmPeriod,
                                                  String category) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_YEAR", pmYear);
        mapParams.put("P_I_PM_PERIOD", pmPeriod);
        mapParams.put("P_I_CATEGORY", category);

        PmsLogUtil.dumpInputParams(mapParams);

        return getIndicatorListProcedure.getList(mapParams);
    }

    @Override
    public PmsJobWeightageMod getJobWeightage(String userID, String jobBrand, Integer pmYear,
                                              Integer pmPeriod) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_JOB_BRAND", jobBrand);
        mapParams.put("P_I_PM_YEAR", pmYear);
        mapParams.put("P_I_PM_PERIOD", pmPeriod);

        PmsLogUtil.dumpInputParams(mapParams);

        return getJobWeightageProcedure.get(mapParams);

    }


    @Override
    public Map<String, Object> getActivePMYearPeriod(String userID) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);

        PmsLogUtil.dumpInputParams(mapParams);

        return getActivePMYearPeriodProcedure.get(mapParams);
    }

    @Override
    public Map<String, Object> getIndicatorDetail(String user_id, Long ind_seqno,
                                                  String job_brand) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", user_id);
        mapParams.put("P_I_IND_SEQNO", ind_seqno);
        mapParams.put("P_I_JOB_BRAND", job_brand);
        PmsLogUtil.dumpInputParams(mapParams);
        
        return getIndicatorDetailProcedure.get(mapParams);
    }

    @Override
    public String getEncryptPMKey(String userID, String pmKey) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_VAL", pmKey);
        
        PmsLogUtil.dumpInputParams(mapParams);
        
        return getEncryptPMKeyProcedure.get(mapParams);
    }

    private class GetJobWeightageListProcedure extends StoredProcedure {
        private GetJobWeightageListProcedure(JdbcTemplate jdbcTemplate, String spName,
                                             JobWeightageRowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA", OracleTypes.CURSOR, rowMapper));

            declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR", OracleTypes.NUMBER, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD", OracleTypes.NUMBER, rowMapper));

            compile();

        }


        private List<PmsJobWeightageMod> getList(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsJobWeightageMod> outList = new ArrayList<PmsJobWeightageMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsJobWeightageMod>) outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }
    }

    private class GetJobWeightageProcedure extends StoredProcedure {
        private GetJobWeightageProcedure(JdbcTemplate jdbcTemplate, String spName, JobWeightageRowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA", OracleTypes.CURSOR, rowMapper));

            declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_JOB_BRAND", OracleTypes.VARCHAR, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR", OracleTypes.NUMBER, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD", OracleTypes.NUMBER, rowMapper));

            compile();

        }

        private PmsJobWeightageMod get(Map mapParams) {
            Map outMap = new HashMap();
            PmsJobWeightageMod outMod = null;
            List<PmsJobWeightageMod> outList = new ArrayList<PmsJobWeightageMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsJobWeightageMod>) outMap.get("P_O_DATA");
                if (outList.size() > 0) {
                    outMod = outList.get(0);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outMod;
        }

    }

    private class JobWeightageRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {

            PmsJobWeightageMod mod = new PmsJobWeightageMod();
            if (RutString.nullToStr(rs.getInt("PM_YEAR")) != "") {
                mod.setPmYear(Integer.valueOf(rs.getInt("PM_YEAR")));
            }
            if (RutString.nullToStr(rs.getInt("PM_PERIOD")) != "") {
                mod.setPmPeriod(Integer.valueOf(rs.getInt("PM_PERIOD")));
            }
            mod.setJobBrand(RutString.nullToStr(rs.getString("JOB_BRAND")));
            mod.setDescription(RutString.nullToStr(rs.getString("DESCRIPTION")));
            mod.setComWeightage(Double.valueOf(rs.getDouble("COM_WEIGHTAGE")));
            mod.setPdWeightage(Double.valueOf(rs.getDouble("PD_WEIGHTAGE")));
            mod.setIndKPIWeightage(Double.valueOf(rs.getDouble("IND_KPI_WEIGHTAGE")));
            return mod;
        }
    }

    private class GetIndicatorListProcedure extends StoredProcedure {
        private GetIndicatorListProcedure(JdbcTemplate jdbcTemplate, String spName, RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA", OracleTypes.CURSOR, rowMapper));

            declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR", OracleTypes.NUMBER, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD", OracleTypes.NUMBER, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_CATEGORY", OracleTypes.VARCHAR, rowMapper));

            compile();
        }

        private List<PmsIndicatorMod> getList(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsIndicatorMod> outList = new ArrayList<PmsIndicatorMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsIndicatorMod>) outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }
    }

    private class IndicatorRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            PmsIndicatorMod mod = new PmsIndicatorMod();
            mod.setSeqNo(Long.valueOf(rs.getLong("PK_IND_SEQNO")));
            mod.setName(RutString.nullToStr(rs.getString("IND_NAME")));
            mod.setDescription(RutString.nullToStr(rs.getString("DESCRIPTION")));
            mod.setCategory(RutString.nullToStr(rs.getString("IND_CATEGORY")));
            mod.setBscSeqNo(Long.valueOf(rs.getLong("FK_BSC_SEQNO")));
            mod.setBscName(RutString.nullToStr(rs.getString("BSC_NAME")));
            mod.setPmYear(Integer.valueOf(rs.getInt("PM_YEAR")));
            mod.setPmPeriod(Integer.valueOf(rs.getInt("PM_PERIOD")));
            mod.setWeightage(Double.valueOf(rs.getDouble("IND_WEIGHTAGE")));
            mod.setSlab1(RutString.nullToStr(rs.getString("IND_SLAB_1")));
            mod.setSlab2(RutString.nullToStr(rs.getString("IND_SLAB_2")));
            mod.setSlab3(RutString.nullToStr(rs.getString("IND_SLAB_3")));
            mod.setSlab4(RutString.nullToStr(rs.getString("IND_SLAB_4")));
            mod.setSlab5(RutString.nullToStr(rs.getString("IND_SLAB_5")));
            mod.setResult(RutString.nullToStr(rs.getString("IND_RESULT")));
            mod.setRating(RutString.nullToStr(rs.getString("IND_RATING")));
            return mod;
        }
    }

    private class GetActivityListProcedure extends StoredProcedure {
        private GetActivityListProcedure(JdbcTemplate jdbcTemplate, String spName, RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA", OracleTypes.CURSOR, rowMapper));

            declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO", OracleTypes.NUMBER, rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR", OracleTypes.NUMBER, rowMapper));

            compile();
        }

        private List<PmsActivityMod> getList(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsActivityMod> outList = new ArrayList<PmsActivityMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsActivityMod>) outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }
    }

    private class PMActivityRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            PmsActivityMod mod = new PmsActivityMod();
            mod.setAction(RutString.nullToStr(rs.getString("ACTION")));
            mod.setActionBy(RutString.nullToStr(rs.getString("ACTION_BY")));
            mod.setActionDate(rs.getTimestamp("ACTION_DATE"));
            return mod;
        }
    }

    private class GetActivePMYearPeriodProcedure extends StoredProcedure {
        public GetActivePMYearPeriodProcedure(JdbcTemplate jdbcTemplate, String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID", OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG", OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_PM_YEAR", OracleTypes.NUMBER));
            declareParameter(new SqlOutParameter("P_O_PM_PERIOD", OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR));
            compile();
        }

        private Map<String, Object> get(Map mapParams) {
            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outMap;
        }
    }

    private class GetIndicatorDetailProcedure extends StoredProcedure{
        
        private GetIndicatorDetailProcedure(JdbcTemplate jdbcTemplate, String spName) {
        super(jdbcTemplate, spName);
        
            declareParameter(new SqlOutParameter("P_O_DATA", OracleTypes.CURSOR, new RowMapper() {

                @Override
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    Map<String,Object> resultMap = new HashMap<String,Object>();
                    resultMap.put("PK_IDN_SEQNO", rs.getLong("PK_IND_SEQNO"));
                    resultMap.put("DEFINITION", RutString.nullToStr(rs.getString("DEFINITION")));
                    resultMap.put("DESCRIPTION1", RutString.nullToStr(rs.getString("DESCRIPTION1")));
                    resultMap.put("DESCRIPTION2", RutString.nullToStr(rs.getString("DESCRIPTION2")));
                    resultMap.put("DESCRIPTION3", RutString.nullToStr(rs.getString("DESCRIPTION3")));
                    
                    return resultMap;
                }
            }));
             declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR));
             declareParameter(new SqlInOutParameter("P_I_IND_SEQNO", OracleTypes.NUMBER));
             declareParameter(new SqlInOutParameter("P_I_JOB_BRAND", OracleTypes.VARCHAR));
             
             compile();
        }

        private Map<String, Object> get(Map mapParams) {
            Map outMap = new HashMap();
            List<Map<String, Object>> outList = new ArrayList<Map<String, Object>>();
            
            try {
                outMap = execute(mapParams);
                outList = (List<Map<String, Object>>) outMap.get("P_O_DATA");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList.size() > 0?outList.get(0):null;
        }
    }

    private class GetEncryptPMKeyProcedure extends StoredProcedure{
        private GetEncryptPMKeyProcedure(JdbcTemplate jdbcTemplate, String spName) {
            super(jdbcTemplate, spName);
            
            declareParameter(new SqlOutParameter("P_O_ENCRYPT_VAL", OracleTypes.VARCHAR));
            
             declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR));
             declareParameter(new SqlInOutParameter("P_I_VAL", OracleTypes.VARCHAR));
        }

        private String get(Map mapParams) {
            Map outMap = new HashMap();
            String strResult = "";
            try {
                outMap = execute(mapParams);
                strResult = (String) outMap.get("P_O_ENCRYPT_VAL");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return strResult;
        }
    }
}
