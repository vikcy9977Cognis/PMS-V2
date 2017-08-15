/*-----------------------------------------------------------------------------------------------------------
PmsReportJdbcDao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.pms.PmsPMStatusReportMod;
import com.rclgroup.dolphin.web.util.PmsLogUtil;

import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
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

public class PmsReportJdbcDao extends RrcStandardDao implements PmsReportDao {
    private static final String PRR_PM_STATUS_REPORT_STORED_PROCEDURE = "VASAPPS.PCR_PMS_REPORT.PRR_PM_STATUS_REPORT";

  
    private GetPMStatusReportProcedure getPMStatusReportProcedure;
    
    public PmsReportJdbcDao() {
        super();
    }

    @Override
    protected void initDao() throws Exception {
        super.initDao();
        getPMStatusReportProcedure = new GetPMStatusReportProcedure(getJdbcTemplate(),
                                                                    PRR_PM_STATUS_REPORT_STORED_PROCEDURE,
                                                                    new PMStatusReportRowMapper());
    }

    @Override
    public List<PmsPMStatusReportMod> getPMStatusReport(String user_id, String pm_key, Integer date, Integer pm_year,
                                                        Integer pm_period, Long com_seqno) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", user_id);
        mapParams.put("P_I_PM_KEY", pm_key);
        mapParams.put("P_I_DATE", date);
        mapParams.put("P_I_PM_YEAR", pm_year);
        mapParams.put("P_I_PM_PERIOD", pm_period);
        mapParams.put("P_I_COM_SEQNO", com_seqno);
        PmsLogUtil.dumpInputParams(mapParams);
        
        return getPMStatusReportProcedure.get(mapParams);
    }

    private class GetPMStatusReportProcedure extends StoredProcedure {
        private GetPMStatusReportProcedure(JdbcTemplate jdbcTemplate, String spName, RowMapper rowMapper) {
           super(jdbcTemplate, spName);
            declareParameter(new SqlOutParameter("P_O_DATA", OracleTypes.CURSOR, rowMapper));
             declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR));
             declareParameter(new SqlInOutParameter("P_I_PM_KEY", OracleTypes.VARCHAR));
             declareParameter(new SqlInOutParameter("P_I_DATE", OracleTypes.NUMBER));
             declareParameter(new SqlInOutParameter("P_I_PM_YEAR", OracleTypes.NUMBER));
             declareParameter(new SqlInOutParameter("P_I_PM_PERIOD", OracleTypes.NUMBER));
             declareParameter(new SqlInOutParameter("P_I_COM_SEQNO", OracleTypes.NUMBER));
        
        compile();
                                                                                              
        }

        private List<PmsPMStatusReportMod> get(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsPMStatusReportMod> outList = new ArrayList<PmsPMStatusReportMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsPMStatusReportMod>) outMap.get("P_O_DATA");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }
    }

    private class PMStatusReportRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            PmsPMStatusReportMod mod = new PmsPMStatusReportMod();
            mod.setCom_seqno(Long.valueOf(rs.getLong("PK_COM_SEQNO")));
            mod.setCom_name(RutString.nullToStr(rs.getString("COM_NAME")));
            mod.setDep_seqno(Long.valueOf(rs.getLong("PK_DEP_SEQNO")));
            mod.setDep_name(RutString.nullToStr(rs.getString("DEP_NAME")));
            mod.setPm_new(Integer.valueOf(rs.getInt("PM_NEW")));
            mod.setPm_inprogress(Integer.valueOf(rs.getInt("PM_INPROGRESS")));
            mod.setPm_waitlisted1(Integer.valueOf(rs.getInt("PM_WAITLISTED1")));
            mod.setPm_waitlisted2(Integer.valueOf(rs.getInt("PM_WAITLISTED2")));
            mod.setPm_completed(Integer.valueOf(rs.getInt("PM_COMPLETED")));
            mod.setPm_total(Integer.valueOf(rs.getInt("PM_TOTAL")));
            return mod;
        }
    }
}
