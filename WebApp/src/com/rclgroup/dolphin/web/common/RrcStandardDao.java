/*-----------------------------------------------------------------------------------------------------------  
RrcStandardDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 04/03/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.model.rcm.RcmSearchMod;
import com.rclgroup.dolphin.web.util.RutDatabase;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Map;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;


public class RrcStandardDao extends NamedParameterJdbcDaoSupport {
    protected RcmUserBean rcmUserBean;
    
    protected void initDao() throws Exception {
        super.initDao();
        RrcStandardSQLErrorCodesTranslator translator = new RrcStandardSQLErrorCodesTranslator(); 
        translator.setDataSource(getDataSource()); 
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.setExceptionTranslator(translator);
        setJdbcTemplate(jdbcTemplate);
    }
    
    public void setRcmUserBean(RcmUserBean rcmUserBean){
        this.rcmUserBean = rcmUserBean;    
    }
    
    protected RcmUserBean getRcmUserBean(){
        return rcmUserBean;    
    }
    
    protected String getUserId(){
        return rcmUserBean.getPrsnLogId();
    }
    
    protected String createSqlSearchCriteria(RcmSearchMod searchMod, Map columnMap) {
        String sqlCriteria = "";
        String sqlWildWithUpperCase = "";
        String sqlSortByIn = "";
        String find = RutString.changeQuoteForSqlStatement(searchMod.getFind());
        String findIn = searchMod.getFindIn();
        String status = searchMod.getStatus();
        String sortBy = searchMod.getSortBy();
        String sortByIn = searchMod.getSortByIn();
        String wild = searchMod.getWild();

        if (wild.equalsIgnoreCase("ON")) {
            sqlWildWithUpperCase = "LIKE '%" + find.toUpperCase() + "%' ";
        } else {
            sqlWildWithUpperCase = "= '" + find.toUpperCase() + "' ";
        }

        if (sortByIn.equalsIgnoreCase("ASC")) {
            sqlSortByIn = "ASC";
        } else if (sortByIn.equalsIgnoreCase("DESC")) {
            sqlSortByIn = "DESC";
        }
        
        sqlCriteria += " WHERE 1 = 1";
        if(find.trim().length() != 0){
            String sqlWild = sqlWildWithUpperCase;    
            sqlCriteria += " AND UPPER(" + (String)columnMap.get(findIn) + ") " + sqlWild;
        }
        
        if(status.equalsIgnoreCase("ALL")){
        }else if(status.equalsIgnoreCase("ACTIVE")){
            sqlCriteria += " AND RECORD_STATUS = 'A'";
        }else if(status.equalsIgnoreCase("SUSPENDED")){
            sqlCriteria += " AND RECORD_STATUS = 'S'";
        }
        
        if(sortBy.trim().length() != 0){
            sqlCriteria += "ORDER BY " + (String)columnMap.get(sortBy) + " " + sqlSortByIn;                   
        }
        return sqlCriteria;
    } 
    
    public StringBuffer addSqlForNewHelp(StringBuffer sql, int startRow, int endRow, String selectColumn) {
        return new StringBuffer(RutDatabase.optimizeSQL(sql.toString(), startRow, endRow));
    }
    
    public StringBuffer getNumberOfAllData(StringBuffer sql){
        return new StringBuffer(RutDatabase.optimizeCountRecordSQL(sql.toString()));
    }
    
    /*public StringBuffer addSqlForNewHelp(StringBuffer sql, int rowAt, int rowTo, String selectColumn) {
        if (rowAt == 0 && rowTo == 0) {
            return sql;
        } else {
            StringBuffer newSql = new StringBuffer();
            newSql.append("select "+selectColumn+" from ( ");
            newSql.append("     select "+selectColumn+" ");
            newSql.append("         ,rownum as REC_ROW ");
            newSql.append("     from ( "+sql.toString()+" ) ");
            newSql.append(" ) ");
            newSql.append("where REC_ROW >= "+rowAt+" and REC_ROW <= "+rowTo);
            return newSql;                
        }
    }
         
    public StringBuffer getNumberOfAllData(StringBuffer sql){
        StringBuffer newSql = new StringBuffer();
        newSql.append("select count(1) from ( "+sql.toString()+" )");        
        return newSql;
    }*/
    
    protected class ArraySqlTypeValue extends AbstractSqlTypeValue{ 
        private String typeName;
        private Object[] values;

        public ArraySqlTypeValue(String typeName,Object[] values){
            this.typeName = typeName;
            this.values=values;
        }

        protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor(this.typeName,con);
            return new ARRAY(desc, con, values);
        }
    }
}
