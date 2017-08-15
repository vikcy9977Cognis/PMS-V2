package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.rcm.RcmReportServerConfigMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;


public class RcmConstantJdbcDao extends RrcStandardDao implements RcmConstantDao {

    public RcmConstantJdbcDao() {
    }
    
    protected void initDao() throws Exception {
        super.initDao();
    }
    
    public RcmReportServerConfigMod getDefaultReportServerConfigMod() {
        RcmReportServerConfigMod reportServerConfigMod = null;
        
        StringBuffer sql = new StringBuffer();
        sql.append("select SERVER_NAME ");
        sql.append("    ,PORT_NUMBER ");
        sql.append("from VR_RCM_REPORT_SERVER_CONFIG ");  
        
        try {
            reportServerConfigMod = (RcmReportServerConfigMod) getNamedParameterJdbcTemplate().queryForObject(
                sql.toString(),
                new HashMap(),
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        RcmReportServerConfigMod bean = new RcmReportServerConfigMod();
                        bean.setServerName(RutString.nullToStr(rs.getString("SERVER_NAME")));
                        bean.setPortNumber(RutString.nullToStr(rs.getString("PORT_NUMBER")));
                        return bean;
                    }
                }
            );
        } catch (EmptyResultDataAccessException e) {
            reportServerConfigMod = null;
        }
        
        return reportServerConfigMod;
    }

    public String getConstantValueByKey(String constantKey) throws DataAccessException {
        String constantValue = null;
        
        StringBuffer sql = new StringBuffer();
        sql.append("select CONSTANT_VALUE ");
        sql.append("from VR_RCM_CONSTANT ");
        sql.append("where CONSTANT_KEY = :constantKey ");
        try {
            constantValue = (String) getNamedParameterJdbcTemplate().queryForObject(
                sql.toString(),
                Collections.singletonMap("constantKey", constantKey),
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return RutString.nullToStr(rs.getString("CONSTANT_VALUE"));
                    }
                }
            );
        } catch (EmptyResultDataAccessException e) {
            constantValue = "";
        }
        
        return constantValue;
    }

}
