/*-----------------------------------------------------------------------------------------------------------  
CamFscJdbcDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 24/04/2008
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 12/05/2009 WAC                       Added getPortPoint method
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.cam;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.cam.CamFscMod;
import com.rclgroup.dolphin.web.util.RutString;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class CamFscJdbcDao extends RrcStandardDao implements CamFscDao {
   
    public CamFscJdbcDao() {
    }
   
    protected void initDao() throws Exception {
        super.initDao();
    }
    
    public boolean isValid(String fscCode,String status) throws DataAccessException {
        System.out.println("[CamFscDao][isValid]: With status: Started");
        boolean isValid = false;
         
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LINE_CODE ");
        sql.append("      ,REGION_CODE ");
        sql.append("      ,AGENT_CODE ");
        sql.append("      ,FSC_CODE ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE 1 = 1 ");
        if((fscCode!=null)&&(!fscCode.trim().equals(""))){
            sql.append("  AND FSC_CODE = :fscCode ");
        }
        sql.append("  AND STATUS = :status "); 
        HashMap map = new HashMap();
        map.put("fscCode",fscCode);
        map.put("status",status);
        System.out.println("[CamFscDao][isValid]: SQL: " + sql.toString());
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),map);
        if(rs.next()) { 
            isValid = true;
        } else { 
            isValid = false;
        }        
        System.out.println("[CamFscDao][isValid]: With status: Finished");
        return isValid;
    }
    
    public boolean isValidChargeCode(String chargeCode, String invoiceBy, String status) throws DataAccessException {
        System.out.println("[CamFscDao][isValidChargeCode]: With status: Started");
        boolean isValid = false;
         
        StringBuffer sql = new StringBuffer();
        
        sql.append(" select charge_code ");
        sql.append(" from (select frcode as charge_code from itp090 ");
        sql.append(" where frcode = :charge_code ");
        sql.append(" and frrcst = :status ");
        sql.append(" and 'D' = :invoiceBy ");
        sql.append(" union ");
        sql.append(" select fgcode as charge_code from itp092 ");
        sql.append(" where fgcode = :charge_code ");
        sql.append(" and chrg_grp_cd = 'MI' ");
        sql.append(" and 'M' = :invoiceBy ");
        sql.append(" and fgrcst= :status) ");
        
        HashMap map = new HashMap();
        map.put("charge_code", chargeCode);
        map.put("invoiceBy", invoiceBy);
        map.put("status",status);
        
        System.out.println("[CamFscDao][isValidChargeCode]: SQL: " + sql.toString());
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),map);
        if(rs.next()) { 
            isValid = true;
        } else { 
            isValid = false;
        }        
        System.out.println("[CamFscDao][isValidChargeCode]: With status: Finished");
        return isValid;
    }
    
    public boolean isRegionValid(String regionCode,String status) throws DataAccessException {
        System.out.println("[CamFscDao][isValid]: With status: Started");
        boolean isValid = false;
        
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LINE_CODE ");
        sql.append("      ,REGION_CODE ");
        sql.append("      ,AGENT_CODE ");
        sql.append("      ,FSC_CODE ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE 1 = 1 ");
        if((regionCode!=null)&&(!regionCode.trim().equals(""))){
            sql.append("  AND REGION_CODE = :regionCode ");
        }
        sql.append("  AND STATUS = :status "); 
        HashMap map = new HashMap();
        map.put("regionCode",regionCode);
        map.put("status",status);
        System.out.println("[CamFscDao][isValid]: SQL: " + sql.toString());
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),map);
        if(rs.next()) {
            isValid = true;
        } else { 
            isValid = false;
        }
        System.out.println("[CamFscDao][isValid]: With status: Finished");
        return isValid;
    }
    
    public boolean isControlFsc(String line,String region,String agent,String fsc) throws DataAccessException {
        System.out.println("[CamFscDao][isControlFsc]: Started");
        boolean isControlFsc = false;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT CONTROL_FSC ");
        sql.append("FROM VR_CAM_FSC ");           
        sql.append("WHERE LINE_CODE = :line ");
        sql.append("  AND REGION_CODE = :region ");
        sql.append("  AND AGENT_CODE = :agent ");
        sql.append("  AND FSC_CODE = :fsc ");
        System.out.println("[CamFscDao][isControlFsc]: SQL: " + sql.toString());
        HashMap map = new HashMap();
        map.put("line",line);
        map.put("region",region);
        map.put("agent",agent);
        map.put("fsc",fsc);
//        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),map);
//        if(rs.next()) {
//            String controlFsc = RutString.trim(rs.getString("CONTROL_FSC"));
//            if(controlFsc.equalsIgnoreCase("Y")){
//                isControlFsc = true;
//            }
//        }
        System.out.println("[CamFscDao][isControlFsc]: Finished");
        return true;
    }
    
    public List listForHelpScreen(String find,String search,String wild) throws DataAccessException {
        System.out.println("[CamFscDao][listForHelpScreen]: Started");
        String sqlCriteria = createSqlCriteria(find,search,wild);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LINE_CODE ");
        sql.append("      ,REGION_CODE ");
        sql.append("      ,AGENT_CODE ");
        sql.append("      ,FSC_CODE ");
        sql.append("      ,FSC_NAME ");
        sql.append("      ,STATUS ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE 1 = 1 ");
        sql.append(sqlCriteria);            
        sql.append("ORDER BY LINE_CODE ");
        sql.append("        ,REGION_CODE ");
        sql.append("        ,AGENT_CODE ");
        sql.append("        ,FSC_CODE ");
        System.out.println("[CamFscDao][listForHelpScreen]: SQL: " + sql.toString());
        System.out.println("[CamFscDao][listForHelpScreen]: Finished");
        return getNamedParameterJdbcTemplate().query(
                   sql.toString(),
                   new HashMap(),
                    new RowMapper(){
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            CamFscMod fsc = new CamFscMod();
                            fsc.setLineCode(RutString.nullToStr(rs.getString("LINE_CODE")));
                            fsc.setRegionCode(RutString.nullToStr(rs.getString("REGION_CODE")));
                            fsc.setAgentCode(RutString.nullToStr(rs.getString("AGENT_CODE")));
                            fsc.setFscCode(RutString.nullToStr(rs.getString("FSC_CODE")));
                            fsc.setFscName(RutString.nullToStr(rs.getString("FSC_NAME")));
                            fsc.setStatus(RutString.nullToStr(rs.getString("STATUS")));  
                            return fsc;
                        }
                    });
    }
    
    public List listForHelpScreen(String find,String search,String wild, String status) throws DataAccessException {
        System.out.println("[CamFscDao][listForHelpScreen]: Started");
        String sqlCriteria = createSqlCriteria(find,search,wild);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LINE_CODE ");
        sql.append("      ,REGION_CODE ");
        sql.append("      ,AGENT_CODE ");
        sql.append("      ,FSC_CODE ");
        sql.append("      ,FSC_NAME ");
        sql.append("      ,STATUS ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE 1 = 1 ");
        sql.append("  AND STATUS = :status ");
        sql.append(sqlCriteria);            
        sql.append("ORDER BY LINE_CODE ");
        sql.append("        ,REGION_CODE ");
        sql.append("        ,AGENT_CODE ");
        sql.append("        ,FSC_CODE ");
        System.out.println("[CamFscDao][listForHelpScreen]: SQL: " + sql.toString());
        System.out.println("[CamFscDao][listForHelpScreen]: Finished");
        return getNamedParameterJdbcTemplate().query(
                   sql.toString(),
                   Collections.singletonMap("status", status),
                    new RowMapper(){
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            CamFscMod fsc = new CamFscMod();
                            fsc.setLineCode(RutString.nullToStr(rs.getString("LINE_CODE")));
                            fsc.setRegionCode(RutString.nullToStr(rs.getString("REGION_CODE")));
                            fsc.setAgentCode(RutString.nullToStr(rs.getString("AGENT_CODE")));
                            fsc.setFscCode(RutString.nullToStr(rs.getString("FSC_CODE")));
                            fsc.setFscName(RutString.nullToStr(rs.getString("FSC_NAME")));
                            fsc.setStatus(RutString.nullToStr(rs.getString("STATUS")));  
                            return fsc;
                        }
                    });
    }    
    public CamFscMod findByKeyfscCode(String fscCode) throws DataAccessException {
        System.out.println("[CamFscDao][listForHelpScreen]: Started");
        CamFscMod fsc = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LINE_CODE ");
        sql.append("      ,REGION_CODE ");
        sql.append("      ,AGENT_CODE ");
        sql.append("      ,FSC_CODE ");
        sql.append("      ,FSC_NAME ");
        sql.append("      ,STATUS ");
        sql.append("      ,OFFICAL_NUMBER ");
        sql.append("      ,COMPANY_NAME ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE 1 = 1 ");
        sql.append("  AND TRIM(STATUS) = 'A' ");            
        sql.append("  AND FSC_CODE = :fscCode ");
        
        System.out.println("[CamFscDao][findByKeyfscCode]: SQL: " + sql.toString());
        System.out.println("[CamFscDao][findByKeyfscCode]: Finished");
        try{
            fsc = (CamFscMod)getNamedParameterJdbcTemplate().queryForObject(
                   sql.toString(),
                   Collections.singletonMap("fscCode", fscCode),
            new RowMapper(){
                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CamFscMod fsc = new CamFscMod();
                    fsc.setLineCode(RutString.nullToStr(rs.getString("LINE_CODE")));
                    fsc.setRegionCode(RutString.nullToStr(rs.getString("REGION_CODE")));
                    fsc.setAgentCode(RutString.nullToStr(rs.getString("AGENT_CODE")));
                    fsc.setFscCode(RutString.nullToStr(rs.getString("FSC_CODE")));
                    fsc.setFscName(RutString.nullToStr(rs.getString("FSC_NAME")));
                    fsc.setStatus(RutString.nullToStr(rs.getString("STATUS")));
                    fsc.setOfficalNumber(RutString.nullToStr(rs.getString("OFFICAL_NUMBER")));
                    fsc.setCompanyName(RutString.nullToStr(rs.getString("COMPANY_NAME")));
                    return fsc; 
                }
            });
        }catch (EmptyResultDataAccessException e) {
            fsc = null;
        }
        return fsc;
    }    

    private String createSqlCriteria(String find, String search, String wild) {
        String sqlCriteria = "";
        String sqlWild = "";

        if (wild.equalsIgnoreCase("ON")) {
            sqlWild = "LIKE '%" + find.toUpperCase() + "%' ";
        } else {
            sqlWild = "= '" + find.toUpperCase() + "' ";
        }
        if (find.trim().length() == 0) {
        } else {
            if (search.equalsIgnoreCase("L")) {
                sqlCriteria = "AND LINE_CODE " + sqlWild;
            } else if (search.equalsIgnoreCase("R")) {
                sqlCriteria = "AND REGION_CODE " + sqlWild;
            } else if (search.equalsIgnoreCase("A")) {
                sqlCriteria = "AND AGENT_CODE " + sqlWild;
            } else if (search.equalsIgnoreCase("F")) {
                sqlCriteria = "AND FSC_CODE " + sqlWild;
            }  else if (search.equalsIgnoreCase("N")) {
                sqlCriteria = "AND FSC_NAME " + sqlWild;
            } else if (search.equalsIgnoreCase("S")) {
                sqlCriteria = "AND STATUS " + sqlWild;
            }
        }
        return sqlCriteria;
    }
    
    public List listForHelpScreenWithUserLevel(String find,String search,String wild,String lineCode,String regionCode,String agentCode,String status) throws DataAccessException {
        System.out.println("[CamFscDao][listForHelpScreenWithUserLevel]: Started");
        String sqlCriteria = createSqlCriteriaForHelpScreenWithUserLevel(find,search,wild);
        StringBuffer sql = new StringBuffer();  
        sql.append("SELECT LINE_CODE ");
        sql.append("      ,REGION_CODE ");
        sql.append("      ,AGENT_CODE ");
        sql.append("      ,FSC_CODE ");
        sql.append("      ,FSC_NAME ");
        sql.append("      ,STATUS ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE 1 = 1 ");
        sql.append("  AND STATUS = :status "); 
        if((lineCode!=null)&&(!lineCode.trim().equals(""))){
            sql.append("  AND LINE_CODE = :lineCode "); 
        }
        if((regionCode!=null)&&(!regionCode.trim().equals(""))){
            sql.append("  AND REGION_CODE = :regionCode "); 
        }
        if((agentCode!=null)&&(!agentCode.trim().equals(""))){
            sql.append("  AND AGENT_CODE = :agentCode "); 
        }
        sql.append(sqlCriteria);            
        sql.append("ORDER BY LINE_CODE ");
        sql.append("        ,REGION_CODE ");
        sql.append("        ,AGENT_CODE ");
        sql.append("        ,FSC_CODE ");
        System.out.println("[CamFscDao][listForHelpScreenWithUserLevel]: SQL: " + sql.toString());
        HashMap map = new HashMap();
        map.put("lineCode",lineCode);
        map.put("regionCode",regionCode);
        map.put("agentCode",agentCode);
        map.put("status",status);
        System.out.println("[CamFscDao][listForHelpScreenWithUserLevel]: Finished");
        return getNamedParameterJdbcTemplate().query(
                   sql.toString(),
                   map,
                   new RowMapper(){
                       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                           CamFscMod fsc = new CamFscMod();
                           fsc.setLineCode(RutString.nullToStr(rs.getString("LINE_CODE")));
                           fsc.setRegionCode(RutString.nullToStr(rs.getString("REGION_CODE")));
                           fsc.setAgentCode(RutString.nullToStr(rs.getString("AGENT_CODE")));
                           fsc.setFscCode(RutString.nullToStr(rs.getString("FSC_CODE")));
                           fsc.setFscName(RutString.nullToStr(rs.getString("FSC_NAME")));
                           fsc.setStatus(RutString.nullToStr(rs.getString("STATUS")));  
                           return fsc;
                       }
                   });
    }
    
    private String createSqlCriteriaForHelpScreenWithUserLevel(String find, String search, String wild) {
        String sqlCriteria = "";
        String sqlWild = "";

        if (wild.equalsIgnoreCase("ON")) {
            sqlWild = "LIKE '%" + find.toUpperCase() + "%' ";
        } else {
            sqlWild = "= '" + find.toUpperCase() + "' ";
        }
        if (find.trim().length() == 0) {
        } else {
            if (search.equalsIgnoreCase("L")) {
                sqlCriteria = "AND LINE_CODE " + sqlWild;
            } else if (search.equalsIgnoreCase("R")) {
                sqlCriteria = "AND REGION_CODE " + sqlWild;
            } else if (search.equalsIgnoreCase("A")) {
                sqlCriteria = "AND AGENT_CODE " + sqlWild;
            } else if (search.equalsIgnoreCase("F")) {
                sqlCriteria = "AND FSC_CODE " + sqlWild;
            }  else if (search.equalsIgnoreCase("N")) {
                sqlCriteria = "AND FSC_NAME " + sqlWild;
            } else if (search.equalsIgnoreCase("S")) {
                sqlCriteria = "AND STATUS " + sqlWild;
            }
        }
        return sqlCriteria;
    }
    
    public Map<String,String> getUserLoginDataLevel3(String userId, String fsc) throws DataAccessException{
    
        StringBuffer sb = new StringBuffer();
       // sb.append(" ");
       Map rsMap = new HashMap();
       
           if(!RutString.isEmptyString(fsc)){
            sb.append(" select distinct country,area_code,zone_code ");
            sb.append(" from VR_RCM_USER, VR_SYS_GEO ge ");
            sb.append(" WHERE PRSN_LOG_ID ='"+userId+"' ");
            sb.append("           AND POINT_CODE = country||'"+fsc+"' and ge.fsc_code='"+fsc+"' ");
            
           }else{
               
                sb.append(" select distinct country,'' area_code, '' zone_code"); 
                sb.append(" from VR_RCM_USER ");
                sb.append(" WHERE PRSN_LOG_ID ='"+userId+"' ");
           }
           
        System.out.println("[CamCountryJdbcDao][getUserLoginLevel3]: sql "+sb.toString());
           
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sb.toString(),new HashMap());
        if(rs.next()) {
           // rsMap = new HashMap();
            rsMap.put("country",RutString.nullToStr(rs.getString("country")));
            rsMap.put("area",RutString.nullToStr(rs.getString("area_code")));
            rsMap.put("zone",RutString.nullToStr(rs.getString("zone_code")));
        }else{
            sb = new StringBuffer();
            sb.append(" select distinct country,'' area_code, '' zone_code"); 
            sb.append(" from VR_RCM_USER ");
            sb.append(" WHERE PRSN_LOG_ID ='"+userId+"' ");
            
            rs = getNamedParameterJdbcTemplate().queryForRowSet(sb.toString(),new HashMap());
            
            if(rs.next()) {
               // rsMap = new HashMap();
                rsMap.put("country",RutString.nullToStr(rs.getString("country")));
                rsMap.put("area",RutString.nullToStr(rs.getString("area_code")));
                rsMap.put("zone",RutString.nullToStr(rs.getString("zone_code")));
            }
            
        }
        
        return rsMap;
    }
    
    protected class RowModMapper implements RowMapper{
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return moveDbToModelForHelp(rs);
        }
    } 
    
    private CamFscMod moveDbToModelForHelp(ResultSet rs) throws SQLException {
        CamFscMod fsc = new CamFscMod();
        fsc.setLineCode(RutString.nullToStr(rs.getString("LINE_CODE")));
        fsc.setRegionCode(RutString.nullToStr(rs.getString("REGION_CODE")));
        fsc.setAgentCode(RutString.nullToStr(rs.getString("AGENT_CODE")));
        fsc.setFscCode(RutString.nullToStr(rs.getString("FSC_CODE")));
        fsc.setFscName(RutString.nullToStr(rs.getString("FSC_NAME")));
        fsc.setStatus(RutString.nullToStr(rs.getString("STATUS")));
        fsc.setOfficalNumber(RutString.nullToStr(rs.getString("OFFICAL_NUMBER")));
        fsc.setCompanyName(RutString.nullToStr(rs.getString("COMPANY_NAME")));
        return fsc;
    }   
    public String getPortPoint(String fsc){
        System.out.println("[CamFscDao][getPortPoint]: Started");
        String portPoint = "";
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT POINT_CODE ");
        sql.append("FROM VR_CAM_FSC ");
        sql.append("WHERE FSC_CODE = :fsc");
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),
            Collections.singletonMap("fsc", fsc.toUpperCase()));
        if(rs.next()) {
            portPoint = RutString.nullToStr(rs.getString("POINT_CODE"));
        }
        System.out.println("[CamFscDao][getPortPoint]: Finished");
        return portPoint;

    }
}


