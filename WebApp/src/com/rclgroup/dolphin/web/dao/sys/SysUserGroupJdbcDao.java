 /*------------------------------------------------------
 SysUserGroupJdbcDao.java
 --------------------------------------------------------
 Copyright RCL Public Co., Ltd. 2007
 --------------------------------------------------------
 Author Manop Wanngam 27/03/08
 - Change Log -------------------------------------------
 ## DD/MM/YY –User- -TaskRef- -ShortDescription-
 --------------------------------------------------------
 */

 package com.rclgroup.dolphin.web.dao.sys;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.sys.SysUserGroupMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class SysUserGroupJdbcDao extends RrcStandardDao implements SysUserGroupDao {
     
     public SysUserGroupJdbcDao() {
     }
     
     protected void initDao() throws Exception {
         super.initDao();
     }
     
     public boolean isValid(String userGroup) throws DataAccessException {
         System.out.println("[SysUserGroupJdbcDao][isValid]: Started");
         boolean isValid = false;
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT USER_GROUP_CODE ");
         sql.append("FROM VR_SYS_USER_GROUP ");
         sql.append("WHERE USER_GROUP_CODE = :userGroup ");
         SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),
             Collections.singletonMap("userGroup", userGroup));
         if(rs.next()) {
             isValid = true;
         } else { 
             isValid = false;
         }         
         System.out.println("[SysUserGroupJdbcDao][isValid]: Finished");
         return isValid;
     } 
     
     public boolean isValid(String userGroup, String status) throws DataAccessException {
         System.out.println("[SysUserGroupJdbcDao][isValid]: With status: Started");
         boolean isValid = false;
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT USER_GROUP_CODE ");
         sql.append("FROM VR_SYS_USER_GROUP ");
         sql.append("WHERE USER_GROUP_CODE = :userGroup ");
         sql.append("AND USER_GROUP_STATUS = :status ");
         HashMap map = new HashMap();
         map.put("userGroup",userGroup);
         map.put("status",status);
         SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),map);
         if(rs.next()) {
             isValid = true;
         } else { 
             isValid = false;
         }
         System.out.println("[SysUserGroupJdbcDao][isValid]: With status: Finished");
         return isValid;
     }  
     
     public boolean isAllValid(String userGroup) throws DataAccessException {
         System.out.println("[SysUserGroupJdbcDao][isAllValid]: Started");
         boolean isValid = false;
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT USER_GROUP_CODE ");
         sql.append("FROM VR_SYS_USER_GROUP ");
         sql.append("WHERE USER_GROUP_CODE IN :userGroup ");
         SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),
             Collections.singletonMap("userGroup", userGroup));
         if(rs.next()) {
             isValid = true;
         } else { 
             isValid = false;
         }             
         System.out.println("[SysUserGroupJdbcDao][isAllValid]: Finished");
         return isValid;
     }       
     
     public List listForHelpScreen(String find, String search, String wild) throws DataAccessException {
         System.out.println("[SysUserGroupJdbcDao][listForHelpScreen]: Started");
         String sqlCriteria = createSqlCriteria(find, search, wild);
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT USER_GROUP_CODE ");
         sql.append("      ,USER_GROUP_NAME ");
         sql.append("      ,USER_GROUP_STATUS ");
         sql.append("FROM VR_SYS_USER_GROUP ");
         sql.append(sqlCriteria);
         System.out.println("[SysUserGroupJdbcDao][listForHelpScreen]: SQL: " + sql.toString());
         System.out.println("[SysUserGroupJdbcDao][listForHelpScreen]: Finished");
         return getNamedParameterJdbcTemplate().query(
                    sql.toString(),
                    new HashMap(),
                    new RowModMapper());
     }
     
     private String createSqlCriteria(String find, String search, String wild) {
         String sqlCriteria = "";
         String sqlWild = "";

         if(wild.equalsIgnoreCase("ON")){
             sqlWild = "LIKE '%" + find.toUpperCase() + "%' ";
         }else{
             sqlWild = "= '" + find.toUpperCase() + "' ";
         }
         if(find.trim().length() == 0){
         }else{
             if(search.equalsIgnoreCase("C")){
                 sqlCriteria = "WHERE USER_GROUP_CODE " + sqlWild;
             }else if(search.equalsIgnoreCase("N")){
                 sqlCriteria = "WHERE USER_GROUP_NAME " + sqlWild;
             }else if(search.equalsIgnoreCase("S")){
                 sqlCriteria = "WHERE USER_GROUP_STATUS " + sqlWild;
             }
         }
         return sqlCriteria;
     }     
     
     public List listForHelpScreen(String find, String search, String wild, String status) throws DataAccessException {
         System.out.println("[SysUserGroupJdbcDao][listForHelpScreen]: With status: Started");
         String sqlCriteria = createSqlCriteriaWithStatus(find, search, wild);
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT USER_GROUP_CODE ");
         sql.append("      ,USER_GROUP_NAME ");
         sql.append("      ,USER_GROUP_STATUS ");
         sql.append("FROM VR_SYS_USER_GROUP ");
         sql.append("WHERE USER_GROUP_STATUS = :status ");
         sql.append(sqlCriteria);
         System.out.println("[SysUserGroupJdbcDao][listForHelpScreen]: SQL: " + sql.toString());
         System.out.println("[SysUserGroupJdbcDao][listForHelpScreen]: With status: Finished");
         return getNamedParameterJdbcTemplate().query(
                    sql.toString(),
                    Collections.singletonMap("status", status),
                    new RowModMapper());
     }
     
     private String createSqlCriteriaWithStatus(String find, String search, String wild) {
         String sqlCriteria = "";
         String sqlWild = "";

         if(wild.equalsIgnoreCase("ON")){
             sqlWild = "LIKE '%" + find.toUpperCase() + "%' ";
         }else{
             sqlWild = "= '" + find.toUpperCase() + "' ";
         }
         if(find.trim().length() == 0){
         }else{
             if(search.equalsIgnoreCase("C")){
                 sqlCriteria = "AND USER_GROUP_CODE " + sqlWild;
             }else if(search.equalsIgnoreCase("N")){
                 sqlCriteria = "AND USER_GROUP_NAME " + sqlWild;
             }else if(search.equalsIgnoreCase("S")){
                 sqlCriteria = "AND USER_GROUP_STATUS " + sqlWild;
             }
         }
         return sqlCriteria;
     }      
     
     protected class RowModMapper implements RowMapper{
         public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
             return moveDbToModelForHelp(rs);
         }
     } 
     
     private SysUserGroupMod moveDbToModelForHelp(ResultSet rs) throws SQLException {
         SysUserGroupMod userGroup = new SysUserGroupMod();
         userGroup.setUserGroupCode(RutString.nullToStr(rs.getString("USER_GROUP_CODE")));   
         userGroup.setUserGroupName(RutString.nullToStr(rs.getString("USER_GROUP_NAME")));            
         userGroup.setUserGroupStatus(RutString.nullToStr(rs.getString("USER_GROUP_STATUS")));

         return userGroup;
     }     
 }
