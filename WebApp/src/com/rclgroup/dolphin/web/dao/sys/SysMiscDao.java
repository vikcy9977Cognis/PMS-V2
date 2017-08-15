
 /*-----------------------------------------------------------------------------------------------------------  
 SysMiscDao.java
 ------------------------------------------------------------------------------------------------------------- 
 Copyright RCL Public Co., Ltd. 2007 
 -------------------------------------------------------------------------------------------------------------
 Author Wacharee Choknakawaro 05/10/2009
 - Change Log ------------------------------------------------------------------------------------------------  
 ## DD/MM/YY -User-     -TaskRef-      -Short Description  
 -----------------------------------------------------------------------------------------------------------*/ 

 package com.rclgroup.dolphin.web.dao.sys;



import org.springframework.dao.DataAccessException;


 public interface SysMiscDao {

      /**
      * find Date Diff by using key as DateFrom,DateTo
      * @param dateFrom
      * @param dateTo
      * @return number of days
      * @throws DataAccessException
      */
     public int getDateDiff(String dateFrom, String dateTo) throws DataAccessException;
         

 }
