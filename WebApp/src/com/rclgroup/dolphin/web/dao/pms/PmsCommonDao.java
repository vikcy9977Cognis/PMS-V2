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


import com.rclgroup.dolphin.web.model.misc.CrmDropDownCompanyMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;

import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;

import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;

import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;

import com.rclgroup.dolphin.web.model.pms.PmsEmpPerfRes;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;

import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;
 
public interface PmsCommonDao {
    
  
  
  public List<CrmDropDownItemMod> getJobBrandList(String userID);
  
  public List<CrmDropDownItemMod> getJobGradeList(String userID);
  
  public List<CrmDropDownYearMod> getYearList(String userID);
  
  public List<CrmDropDownItemMod> getSectionList(String userID,Integer comSeqno,Integer divSeqno,Integer depSeqno);
  
  public List<CrmDropDownItemMod> getDepartmentList(String userID,Integer comSeqno,Integer divSeqno );
  
  public List<CrmDropDownItemMod> getDivisionList(String userID,Integer comSeqno  );
  
  public List<CrmDropDownCompanyMod> getCompanyList(String userID);
  
  public List<CrmProfileMod> getProfileType(String userID);
  /**
       * Get PM Activity for particular User in PM Year
       * @param userID
       * @param empSeqNo
       * @param pmYear
       * @return
       * @throws DataAccessException
       */
   public List<PmsActivityMod> getActivityListData(final String userID,
                                               final Long empSeqNo,
                                               final Integer pmYear) ;
//  /**
//   * Get list of ports.
//   * @return
//   * @throws DataAccessException
//   */
//  public List getPmList(String loginID,String pmKey ,Integer pageRows,Integer pageNo,String sortBy,
//                           String sortDirection,String empId,Integer pmYear,Integer pmPeriod,
//                           String pmStatus,String pmEmpName ,Integer pmCompany,Integer pmDivision,Integer pmDepartment,Integer pmSection,
//                            String pmJobGrade,String pmJobBrand ,String pmDesignation,Integer pmMngId) throws DataAccessException;
//  
//  public List<CrmDropDownItemMod> getjobBrandList()  throws DataAccessException;
//  /**
//   * Get list of company.
//   * @return
//   * @throws DataAccessException
//   */
//  //PK_COM_SEQNO
//  //                                  COM_NAME
//  //                                  COUNTRY
//  public List getCompanyList(String loginID,String PK_COM_SEQNO ,Integer pageRows,Integer pageNo,String sortBy,
//                           String sortDirection,String empId,Integer pmYear,Integer pmPeriod,
//                           String pmStatus,String pmEmpName ,Integer pmCompany,Integer pmDivision,Integer pmDepartment,Integer pmSection,
//                            String pmJobGrade,String pmJobBrand ,String pmDesignation,Integer pmMngId) throws DataAccessException;
  
  

/**
     * List job weightage by year/period 
     * @param userID
     * @param pmYear
     * @param pmPeriod
     * @return
     * @throws DataAccessException
     */
public List<PmsJobWeightageMod> getJobWeightageList(final String userID,
                                                 final Integer pmYear,
                                                 final Integer pmPeriod) throws DataAccessException;
 
 /**
     * List indicator in particular category
     * @param userID
     * @param pmYear
     * @param pmPeriod
     * @param category
     * @return
     * @throws DataAccessException
     */
 public List<PmsIndicatorMod> getIndicatorList(final String userID,
                                                 final Integer pmYear,
                                                 final Integer pmPeriod,
                                                final String category) throws DataAccessException;

/**
     * Get Weightage for particular Job Brand.
     * @param userID
     * @param jobBrand
     * @param pmYear
     * @param pmPeriod
     * @return
     * @throws DataAccessException
     */
 public PmsJobWeightageMod getJobWeightage(final String userID,
                                                  final String jobBrand,
                                                     final Integer pmYear,
                                                     final Integer pmPeriod) throws DataAccessException;

//  

/**
     * Get active PM year/period by system date.
     * @param userID
     * @return
     * @throws DataAccessException
     */
 public Map<String,Object> getActivePMYearPeriod(final String userID) throws DataAccessException;

    /**
     * Return PMS Report URL.
     * @return
     * @throws DataAccessException
     */
    public String getReportURL() throws DataAccessException;

    public List<PmsEmpPerfRes> getEmpPerfResData(int year, int comp, int dept,
                                                 String jobBand, int period, String compRange, String indKPIRange);
}
