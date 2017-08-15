/*-----------------------------------------------------------------------------------------------------------  
CrmSaleLeadDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2015 
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 08/08/15
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/  


package com.rclgroup.dolphin.web.dao.pms;

 
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;

import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmFormMod;

import com.rclgroup.dolphin.web.model.pms.PmListMod;

import java.util.List;

import org.springframework.dao.DataAccessException;
 
public interface PmsPMFormDao {
    
//  P_I_USER_ID VARCHAR2(200);
//  P_I_PM_KEY VARCHAR2(200);
//  P_I_PAGE_ROWS NUMBER;
//  P_I_PAGE_NO NUMBER;
//  P_I_SORT VARCHAR2(200);
//  P_I_ASC_DESC VARCHAR2(200);
//  P_I_EMP_ID NUMBER;
//  P_I_PM_YEAR NUMBER;
//  P_I_PM_PERIOD NUMBER;
//  P_I_PM_STATUS VARCHAR2(200);
//  P_I_EMP_NAME VARCHAR2(200);
//  P_I_COMPANY NUMBER;
//  P_I_DIVISION NUMBER;
//  P_I_DEPARTMENT NUMBER;
//  P_I_SECTION NUMBER;
//  P_I_JOB_GRADE VARCHAR2(200);
//  P_I_JOB_BRAND VARCHAR2(200);
//  P_I_DESIGNATION VARCHAR2(200);
//  P_I_MNGR_ID NUMBER;
       
  
 public Integer getPMList(PmCriteria pmCriteria , List <PmListMod> outList);
  
    
}
