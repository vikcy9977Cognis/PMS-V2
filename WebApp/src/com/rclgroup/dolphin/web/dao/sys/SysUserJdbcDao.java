/*-----------------------------------------------------------------------------------------------------------  
SysUserJdbcDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 28/03/2008 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.sys;

import com.rclgroup.dolphin.web.common.RrcStandardDao;

import com.rclgroup.dolphin.web.model.sys.SysUserMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class SysUserJdbcDao extends RrcStandardDao implements SysUserDao {

    public SysUserJdbcDao() {
    }
    
    protected void initDao() throws Exception {
        super.initDao();
    }

    public boolean isValid(String userID) {
        System.out.println("[SysUserDao][isValid]: Started");
        boolean isValid = false;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * ");
        sql.append("FROM VR_SYS_USER ");
        sql.append("WHERE USER_ID = :userID");
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),
            Collections.singletonMap("userID", userID));
        if(rs.next()) {
            isValid = true;
        } else { 
            isValid = false;
        }
        System.out.println("[SysUserDao][isValid]: Finished");
        return isValid;
    }
    
    public boolean isActive(String userID) {
        System.out.println("[SysUserDao][isActive]: Started");
        boolean isActive = false;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * ");
        sql.append("FROM VR_SYS_USER ");
        sql.append("WHERE USER_ID = :userID ");
        sql.append("AND STATUS = \'Active\' ");
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),
            Collections.singletonMap("userID", userID));
        if(rs.next()) {
            isActive = true;
        } else { 
            isActive = false;
        }
        System.out.println("[SysUserDao][isActive]: Finished");
        return isActive;
    }

    public List listForHelpScreen(String find, String search, String wild) throws DataAccessException{
        String sqlCriteria = createSqlCriteria(find, search, wild);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT USER_ID ");
        sql.append("      ,NAME ");
        sql.append("      ,FSC ");
        sql.append("      ,ORGANIZATION_TYPE ");
        sql.append("      ,DEPARTMENT ");
        sql.append("      ,EMAIL ");
        sql.append("      ,STATUS ");
        sql.append("FROM VR_SYS_USER ");
        sql.append(sqlCriteria);
        return getNamedParameterJdbcTemplate().query(
                   sql.toString(),
                   new HashMap(),
                   new RowModMapper());
    }
    
    private String createSqlCriteria(String find, String search, String wild) {
        String sqlCriteria = "";
        String sqlWild = "";
        String sqlWildWithUpperCase = "";
        
        if(wild.equalsIgnoreCase("ON")){
            sqlWild = "LIKE '%" + find + "%' ";
        }else{
            sqlWild = "= '" + find + "' ";
        }
                
        if(wild.equalsIgnoreCase("ON")){
            sqlWildWithUpperCase = "LIKE '%" + find.toUpperCase() + "%' ";
        }else{
            sqlWildWithUpperCase = "= '" + find.toUpperCase() + "' ";
        }
        
        if(find.trim().length() == 0){
        }else{
            if(search.equalsIgnoreCase("I")){
                sqlCriteria = "WHERE USER_ID " + sqlWildWithUpperCase;
            }else if(search.equalsIgnoreCase("N")){
                sqlCriteria = "WHERE UPPER(NAME) " + sqlWildWithUpperCase;
            }else if(search.equalsIgnoreCase("F")){
                sqlCriteria = "WHERE FSC " + sqlWildWithUpperCase;
            }else if(search.equalsIgnoreCase("O")){
                sqlCriteria = "WHERE UPPER(ORGANIZATION_TYPE) " + sqlWildWithUpperCase;
            }else if(search.equalsIgnoreCase("D")){
                sqlCriteria = "WHERE DEPARTMENT " + sqlWildWithUpperCase;
            }else if(search.equalsIgnoreCase("E")){
                sqlCriteria = "WHERE UPPER(EMAIL) " + sqlWildWithUpperCase;
            }else if(search.equalsIgnoreCase("S")){
                sqlCriteria = "WHERE UPPER(STATUS) " + sqlWildWithUpperCase;
            }        
        }
        return sqlCriteria;
    }  

    public SysUserMod findByKeyCode(String code) {
        SysUserMod userMod = null;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT USER_ID ");
                    sql.append("      ,NAME ");
                    sql.append("      ,FSC ");
                    sql.append("      ,ORGANIZATION_TYPE ");
                    sql.append("      ,DEPARTMENT ");
                    sql.append("      ,EMAIL ");
                    sql.append("      ,STATUS ");
                    sql.append("FROM VR_SYS_USER ");
                    sql.append("WHERE USER_ID = :code");
        try{
            userMod = (SysUserMod)getNamedParameterJdbcTemplate().queryForObject(
                   sql.toString(),
                   Collections.singletonMap("code", code),
                   new RowModMapper());
        }catch (EmptyResultDataAccessException e) {
            userMod = null;
        }                    
                    
        return userMod;
    }

    protected class RowModMapper implements RowMapper{
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return moveDbToModel(rs);
        }
    } 
    
    private SysUserMod moveDbToModel(ResultSet rs) throws SQLException {
        SysUserMod user = new SysUserMod();
        user.setUserID(RutString.nullToStr(rs.getString("USER_ID")));
        user.setName(RutString.nullToStr(rs.getString("NAME")));
        user.setFsc(RutString.nullToStr(rs.getString("FSC")));
        user.setOrg_type(RutString.nullToStr(rs.getString("ORGANIZATION_TYPE")));
        user.setDepartment(RutString.nullToStr(rs.getString("DEPARTMENT")));
        user.setEmail(RutString.nullToStr(rs.getString("EMAIL")));
        user.setStatus(RutString.nullToStr(rs.getString("STATUS")));

        return user;
    }
}
