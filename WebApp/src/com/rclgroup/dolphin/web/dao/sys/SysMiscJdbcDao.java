
 /*
 --------------------------------------------------------
 SysMiscJdbcDao.java
 --------------------------------------------------------
 Copyright RCL Public Co., Ltd. 2008
 --------------------------------------------------------
 Author Watcharee Choknakawaro 05/10/2009
 - Change Log--------------------------------------------
 ## DD/MM/YY -User- -TaskRef- -ShortDescription
  --------------------------------------------------------
 */
 package com.rclgroup.dolphin.web.dao.sys;

import com.rclgroup.dolphin.web.common.RrcStandardDao;


import com.rclgroup.dolphin.web.util.RutDate;
import com.rclgroup.dolphin.web.util.RutString;


import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class SysMiscJdbcDao extends RrcStandardDao implements SysMiscDao{

     public SysMiscJdbcDao() {
         super();
     }
     
     protected void initDao() throws Exception {
         super.initDao();
     }

     public int getDateDiff(String dateFrom,String dateTo) throws DataAccessException{
         System.out.println("[SysMiscJdbcDao][getDateDiff]: Started");        
         int noOfDays = 0;
         dateFrom = RutDate.dateToStr(RutString.nullToStr(dateFrom));
         dateTo = RutDate.dateToStr(RutString.nullToStr(dateTo));
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT TRUNC(to_date('"+ dateTo +"','YYYYMMDD')-to_date('"+dateFrom+"','YYYYMMDD')) as dateDiff ");
         sql.append("FROM Dual");
         System.out.println(sql.toString());
         HashMap map = new HashMap();
         map.put("dateFrom",dateFrom);
         map.put("dateTo",dateTo);
         SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),map);
         if(rs.next()) {
             noOfDays = rs.getInt("dateDiff");
         }         
         System.out.println("[SysMiscJdbcDao][getDateDiff]: Finished");
         return noOfDays;         


 }    
}
