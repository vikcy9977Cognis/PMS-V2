/*-----------------------------------------------------------------------------------------------------------
CrmSaleLeadCommonJdbcDao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 08/08/15
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.common.RcmUserBean;
import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.common.RriStandardDao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.mapper.ValueDescriptionRowMapper;
import com.rclgroup.dolphin.web.exception.CustomDataAccessException;


import com.rclgroup.dolphin.web.model.misc.CrmDropDownCompanyMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;


import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;
import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsEmpPerfRes;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;
import com.rclgroup.dolphin.web.util.PmsLogUtil;
import com.rclgroup.dolphin.web.util.RutString;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
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

public class PmsCommonJdbcDao extends RrcStandardDao implements PmsCommonDao {
    private static final String PMS_GET_JOB_BRAND_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_JOB_BRAND_LIST";
    private static final String PMS_GET_JOB_GRADE_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_JOB_GRADE_LIST";
    private static final String PMS_GET_DIVISION_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_DIVISION_LIST";
    private static final String PMS_GET_COMPANY_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_COMPANY_LIST";
  private static final String PMS_GET_DEPARTMENT_STORED_PROCEDURE =
    "VASAPPS.PCR_PMS_COMMON.PRR_DEPARTMENT_LIST";
  private static final String PMS_GET_SECTION_STORED_PROCEDURE =
    "VASAPPS.PCR_PMS_COMMON.PRR_SECTION_LIST";
  private static final String PMS_GET_YEAR_STORED_PROCEDURE =
    "VASAPPS.PCR_PMS_COMMON.PRR_PM_YEAR_LIST";
  private static final String PMS_GET_PROFILE_GET_PROCEDURE =
    "VASAPPS.PCR_PMS_COMMON.PRR_EMP_PROFILE_GET";
  //
  private static final String PRR_EMP_PERF_RES_GET_STORED_PROCEDURE =
    "VASAPPS.PCR_PMS_COMMON.PRR_EMP_PERF";
  

  private static final String PRR_PM_ACT_GET_STORED_PROCEDURE =
    "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_ACT_GET";
  

    private static final String PRR_JOB_WEIGHTAGE_LIST_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_JOB_WEIGHTAGE_LIST";
    
    private static final String PRR_INDICATOR_LIST_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_INDICATOR_LIST";
    
    private static final String PRR_JOB_WEIGHTAGE_GET_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_COMMON.PRR_JOB_WEIGHTAGE_GET";
    
    private static final String PRR_PM_YEAR_ACTIVE_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_COMMON.PRR_PM_YEAR_ACTIVE_GET";
    
    private static final String PRR_REPORT_SERVER_GET_STORED_PROCEDURE =
        "VASAPPS.PCR_PMS_COMMON.PRR_REPORT_SERVER_GET";
    
    private GetJobWeightageListProcedure getJobWeightageListProcedure;
    private GetIndicatorListProcedure getIndicatorListProcedure;
    private GetJobWeightageProcedure getJobWeightageProcedure;
    
    // private GetPMListProcedure getPmListProcedure;
    private GetDropDownValuesProcedure   getJobBrandListProcedure;
    private GetDropDownValuesProcedure   getJobGradeListProcedure;
    private GetDropDownDivisionProcedure getDivisionListProcedure;
    private GetDropDownCompanyProcedure   getCompanyListProcedure;
    private GetDropDownDepartmentProcedure  getDepartmentListProcedure;
    private GetDropDownSectionProcedure     getSectionListProcedure;
    private GetDropDownYearProcedure     getYearListProcedure;
    private GetProfileGetProcedure     getProfileGetProcedure;
    private GetEmpPerfResProcedure getEmpPerfResProcedure;
    private GetActivityListProcedure getActivityListProcedure;
    private GetActivePMYearPeriodProcedure getActivePMYearPeriodProcedure;
    
    private GetReportURLProcedure getReportURLProcedure;
    
    public PmsCommonJdbcDao() {
        super();

    }

    /**
     * @throws Exception
     */
    public void initDao() throws Exception {
        super.initDao();
        getYearListProcedure   =
            new GetDropDownYearProcedure(getJdbcTemplate(),
                                           PMS_GET_YEAR_STORED_PROCEDURE,
                                           new PmsYearMapper());
        getJobBrandListProcedure =
                new GetDropDownValuesProcedure(getJdbcTemplate(),
                                               PMS_GET_JOB_BRAND_STORED_PROCEDURE,
                                               new PmsJobBrandMapper());
      
        getJobGradeListProcedure   =
              new GetDropDownValuesProcedure(getJdbcTemplate(),
                                             PMS_GET_JOB_GRADE_STORED_PROCEDURE,
                                             new ValueDescriptionRowMapper("GRADE_CODE" ,"GRADE_NAME" ) );
        getCompanyListProcedure   =
            new GetDropDownCompanyProcedure(getJdbcTemplate(),
                                           PMS_GET_COMPANY_STORED_PROCEDURE,
                                           new PmsCompanyMapper() );
        getDivisionListProcedure =
              new GetDropDownDivisionProcedure(getJdbcTemplate(),
                                             PMS_GET_DIVISION_STORED_PROCEDURE,
                                             new ValueDescriptionRowMapper("FK_DIV_SEQNO" ,"DIV_NAME" ) );
        getDepartmentListProcedure =
            new GetDropDownDepartmentProcedure(getJdbcTemplate(),
                                           PMS_GET_DEPARTMENT_STORED_PROCEDURE,
                                           new ValueDescriptionRowMapper("FK_DEP_SEQNO" ,"DEP_NAME" ) );
        getSectionListProcedure =
          new GetDropDownSectionProcedure(getJdbcTemplate(),
                                         PMS_GET_SECTION_STORED_PROCEDURE,
                                         new ValueDescriptionRowMapper("FK_SEC_SEQNO" ,"SEC_NAME" ) );
        
      getProfileGetProcedure      =
      new GetProfileGetProcedure(getJdbcTemplate(),
                                         PMS_GET_PROFILE_GET_PROCEDURE,
                                         new PmsProfileMapper() );
      
      getEmpPerfResProcedure = new GetEmpPerfResProcedure(getJdbcTemplate(), PRR_EMP_PERF_RES_GET_STORED_PROCEDURE
                                                                , new EmpPerfResRowMapper());
        
        
      
      getActivityListProcedure = new GetActivityListProcedure(getJdbcTemplate(), PRR_PM_ACT_GET_STORED_PROCEDURE
                                                              , new PMActivityRowMapper());
      
      
        getJobWeightageListProcedure = new GetJobWeightageListProcedure(getJdbcTemplate(),PRR_JOB_WEIGHTAGE_LIST_STORED_PROCEDURE,
                                                                    new JobWeightageRowMapper());
        
        getJobWeightageProcedure = new GetJobWeightageProcedure(getJdbcTemplate(), PRR_JOB_WEIGHTAGE_GET_STORED_PROCEDURE
                                                                , new JobWeightageRowMapper());
        
        getIndicatorListProcedure = new GetIndicatorListProcedure(getJdbcTemplate(), PRR_INDICATOR_LIST_STORED_PROCEDURE,
                                                                  new IndicatorRowMapper());
        getActivePMYearPeriodProcedure =
            new GetActivePMYearPeriodProcedure(getJdbcTemplate(), PRR_PM_YEAR_ACTIVE_GET_STORED_PROCEDURE);
        
        getReportURLProcedure = new GetReportURLProcedure(getJdbcTemplate(), PRR_REPORT_SERVER_GET_STORED_PROCEDURE);
        
    }
    /*
     *     public Integer findBLList(String pol, String pod, Integer cutOffDate,
                              String blNo, String bookingNo,
                              Integer issueDateFrom, Integer issueDateTo,
                              String sortBy, String sortDirection,
                              Integer pageRows, Integer pageNo,
                              List<CrmSaleLeadBLMod> outList) {
        Map mapParams = new HashMap();
        mapParams.put("P_I_PAGE_ROWS", pageRows);
        mapParams.put("P_I_PAGE_NO", pageNo);
        mapParams.put("P_I_POL", pol);
        mapParams.put("P_I_POD", pod);
        mapParams.put("P_I_CUTOFF_DATE", cutOffDate);
        mapParams.put("P_I_BL_NO", blNo);
        mapParams.put("P_I_BOOKING_NO", bookingNo);
        mapParams.put("P_I_ISSUE_DATE_FROM", issueDateFrom);
        mapParams.put("P_I_ISSUE_DATE_TO", issueDateTo);
        mapParams.put("P_I_SORT", sortBy);
        mapParams.put("P_I_ASC_DESC", sortDirection);
        CrmSaleLeadLogUtil.dumpInputParams(mapParams);
        return findStoredProcedure.getList(mapParams, outList);
    }
     *
     *
     * */


    //    public List  getPmList(String loginID,String pmKey ,Integer pageRows,Integer pageNo,String sortBy,
    //                           String sortDirection,String empId,Integer pmYear,Integer pmPeriod,
    //                           String pmStatus,String pmEmpName ,Integer pmCompany,Integer pmDivision,Integer pmDepartment,Integer pmSection,
    //                            String pmJobGrade,String pmJobBrand ,String pmDesignation,Integer pmMngId) {
    //      Map<String, Object> mapParams = new HashMap<String, Object>();
    //
    //
    //      mapParams.put("P_I_USER_ID", loginID);
    //      mapParams.put("P_I_PM_KEY", pmKey);
    //      mapParams.put("P_I_PAGE_ROWS", pageRows);
    //      mapParams.put("P_I_PAGE_NO", pageNo);
    //
    //      mapParams.put("P_I_SORT", sortBy);
    //      mapParams.put("P_I_ASC_DESC", sortDirection);
    //      mapParams.put("P_I_EMP_ID", empId);
    //      mapParams.put("P_I_PM_YEAR", pmYear);
    //      mapParams.put("P_I_PM_PERIOD", pmPeriod);
    //      mapParams.put("P_I_PM_STATUS", pmStatus);
    //      mapParams.put("P_I_EMP_NAME", pmEmpName);
    //      mapParams.put("P_I_COMPANY", pmCompany);
    //      mapParams.put("P_I_DIVISION", pmDivision);
    //      mapParams.put("P_I_DEPARTMENT", pmDepartment);
    //
    //      mapParams.put("P_I_SECTION", pmSection);
    //      mapParams.put("P_I_JOB_GRADE", pmJobGrade);
    //      mapParams.put("P_I_JOB_BRAND", pmJobBrand);
    //
    //      mapParams.put("P_I_DESIGNATION", pmDesignation);
    //      mapParams.put("P_I_MNGR_ID", pmMngId);
    //
    //
    //       PmsLogUtil.dumpInputParams(mapParams);
    //
    ////      return getRegionListProcedure.getList(mapParams, outList);
    //         return getPmListProcedure.getList(mapParams);
    //
    // //  return null;
    //    }
  
    /**
     * get job Brand for dropdown
     * @param  CrmDropDownItemMod
     * @return CrmDropDownItemMod
     */
    public List<CrmDropDownItemMod> getJobBrandList(String userID) {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        PmsLogUtil.dumpInputParams(mapParams);

        return getJobBrandListProcedure.getDropDownList(mapParams);

    }
    
  /**
       * get job Grade for dropdown
       * @param  CrmDropDownItemMod
       * @return CrmDropDownItemMod
       */
      public List<CrmDropDownItemMod> getJobGradeList(String userID) {
          Map mapParams = new HashMap();
          mapParams.put("P_I_USER_ID", userID);
          PmsLogUtil.dumpInputParams(mapParams);

          return getJobGradeListProcedure.getDropDownList(mapParams);

      }
  
      /**
       * get job Grade for dropdown
       * @param  CrmDropDownItemMod
       * @return CrmDropDownItemMod
       */
      public List<CrmDropDownYearMod> getYearList(String userID) {
          Map mapParams = new HashMap();
          mapParams.put("P_I_USER_ID", userID);
          PmsLogUtil.dumpInputParams(mapParams);

          return getYearListProcedure.getDropDownList(mapParams);

      }

    public List<CrmDropDownItemMod> getSectionList(String userID,
                                                   Integer comSeqno,
                                                   Integer divSeqno,
                                                   Integer depSeqno) {
      Map mapParams = new HashMap();
      mapParams.put("P_I_USER_ID", userID);
      mapParams.put("P_I_COM_SEQNO", comSeqno);
      mapParams.put("P_I_DIV_SEQNO", divSeqno);
      mapParams.put("P_I_DEP_SEQNO", depSeqno);
      
      
      

      PmsLogUtil.dumpInputParams(mapParams);

      return getSectionListProcedure.getDropDownList(mapParams);
    }

    public List<CrmDropDownItemMod> getDepartmentList(String userID,
                                                      Integer comSeqno,
                                                      Integer divSeqno) {
      Map mapParams = new HashMap();
      mapParams.put("P_I_USER_ID", userID);
      mapParams.put("P_I_COM_SEQNO", comSeqno);
      mapParams.put("P_I_DIV_SEQNO", divSeqno);
      PmsLogUtil.dumpInputParams(mapParams);
      return getDepartmentListProcedure.getDropDownList(mapParams);
    }

    public List<CrmDropDownItemMod> getDivisionList(String userID,
                                                    Integer comSeqno) {
      Map mapParams = new HashMap();
      mapParams.put("P_I_USER_ID", userID);
      mapParams.put("P_I_COM_SEQNO", comSeqno);
      
      PmsLogUtil.dumpInputParams(mapParams);
      return getDivisionListProcedure.getDropDownList(mapParams);
    }

    public List<CrmDropDownCompanyMod> getCompanyList(String userID) {
      Map mapParams = new HashMap();
      mapParams.put("P_I_USER_ID", userID);
      PmsLogUtil.dumpInputParams(mapParams);
      return getCompanyListProcedure.getDropDownList(mapParams);
    }

    public List<CrmProfileMod> getProfileType(String userID) {
      Map mapParams = new HashMap();
      mapParams.put("P_I_USER_ID", userID);
      PmsLogUtil.dumpInputParams(mapParams);
        List<CrmProfileMod>  result = getProfileGetProcedure.getType(mapParams);
        return result;
    }

 
    
    public List<PmsEmpPerfRes> getEmpPerfResData(int year, int comp, int dept, String jobBand , int period, String compRange, String indKPIRange) throws DataAccessException {
        Map mapParams = new HashMap();
        System.out.println("getEmpPerfResData :-"+year+comp+" "+dept+" "+ jobBand+" "+period+" "+compRange+" "+indKPIRange);
        mapParams.put("P_I_YEAR", year);
        mapParams.put("P_I_COMP_SEQ", comp);
        mapParams.put("P_I_PM_DEPT_SEQ", dept);
        mapParams.put("P_I_JOB_BRAND", jobBand);
        mapParams.put("P_I_PERIOD", period);
        mapParams.put("P_I_COMP_RANGE", compRange);
        mapParams.put("P_I_INDKPI_RANGE", indKPIRange);
                
        PmsLogUtil.dumpInputParams(mapParams);
      
        List<PmsEmpPerfRes>  result = getEmpPerfResProcedure.getListData(mapParams);
     
        return result;
        
    }

 
  public List<PmsActivityMod> getActivityListData(String userID, Long empSeqNo,
                                              Integer pmYear) throws DataAccessException {
      Map mapParams = new HashMap();
      mapParams.put("P_I_USER_ID", userID);
      mapParams.put("P_I_EMP_SEQNO", empSeqNo);
      mapParams.put("P_I_PM_YEAR", pmYear);
     
              
      PmsLogUtil.dumpInputParams(mapParams);
    
      List<PmsActivityMod>  result=getActivityListProcedure.getListData(mapParams);
   
      return result;
      
  }

    @Override
    public Map<String, Object> getActivePMYearPeriod(String userID) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);

        PmsLogUtil.dumpInputParams(mapParams);

        return getActivePMYearPeriodProcedure.get(mapParams);
    }

    @Override
    public String getReportURL() throws DataAccessException {
        Map mapParams = new HashMap();
        PmsLogUtil.dumpInputParams(mapParams);
        return getReportURLProcedure.getURL(mapParams);
    }
    //VASAPPS.PCR_PMS_COMMON.PRR_JOB_GRADE_LIST
    //    public List getCompanyList(String loginID, String PK_COM_SEQNO,
    //                               Integer pageRows, Integer pageNo, String sortBy,
    //                               String sortDirection, String empId,
    //                               Integer pmYear, Integer pmPeriod,
    //                               String pmStatus, String pmEmpName,
    //                               Integer pmCompany, Integer pmDivision,
    //                               Integer pmDepartment, Integer pmSection,
    //                               String pmJobGrade, String pmJobBrand,
    //                               String pmDesignation, Integer pmMngId) {
    //        return Collections.emptyList();
    //    }
    //
    //  protected class GetDropDownValuesProcedure extends StoredProcedure {
    //      protected GetDropDownValuesProcedure(JdbcTemplate jdbcTemplate,
    //                                           String spName,
    //                                           RowMapper rowMapper) {
    //          super(jdbcTemplate, spName);
    //          declareParameter(new SqlOutParameter("P_O_DATA",
    //                                               OracleTypes.CURSOR,
    //                                               rowMapper));
    //          compile();
    //      }
    //
    //    private class GetPMListProcedure extends StoredProcedure {
    //          private static final String STORED_PROCEDURE_NAME =
    //              "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_LIST";
    //
    //        /**
    //         * @param jdbcTemplate
    //         * @param rowMapper
    //         */
    //        protected GetPMListProcedure(JdbcTemplate jdbcTemplate,RowMapper rowMapper) {
    //              super(jdbcTemplate, STORED_PROCEDURE_NAME);
    //              declareParameter(new SqlOutParameter("P_O_DATA",
    //                                                   OracleTypes.CURSOR,
    //                                                   rowMapper));
    //            declareParameter(new SqlOutParameter("P_O_ROW_TOTAL",
    //                                                 OracleTypes.INTEGER));
    //              declareParameter(new SqlInOutParameter("P_I_USER_ID",
    //                                                     OracleTypes.VARCHAR,
    //                                                     rowMapper));
    //            declareParameter(new SqlOutParameter("P_O_DATA",
    //                                                            OracleTypes.CURSOR,
    //                                                            rowMapper));
    //                       declareParameter(new SqlOutParameter("P_O_PAGE_TOTAL",
    //                                                            OracleTypes.INTEGER));
    //                       declareParameter(new SqlInOutParameter("P_I_SL_REF_NO",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_SL_DATE_FROM",
    //                                                              OracleTypes.INTEGER,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_SL_DATE_TO",
    //                                                              OracleTypes.INTEGER,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_SL_STATUS",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_INIT_FSC",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_INIT_SALE",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_REC_FSC",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_REC_SALE",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_POL",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_POD",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_USER_ID",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_SORT",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_ASC_DESC",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_PAGE_NO",
    //                                                              OracleTypes.INTEGER,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_PAGE_ROWS",
    //                                                              OracleTypes.INTEGER,
    //                                                              rowMapper));
    //                       declareParameter(new SqlInOutParameter("P_I_BL_NO",
    //                                                              OracleTypes.VARCHAR,
    //                                                              rowMapper));
    //
    //              compile();
    //          }
    //
    //          protected List  getList(Map mapParams ) {
    //               List resultList =
    //                  new ArrayList ();
    //              Map outMap = new HashMap();
    //              try {
    //                  outMap = execute(mapParams);
    //                  resultList = (List)outMap.get("P_O_DATA");
    //
    //              } catch (Exception ex) {
    //
    //                  ex.printStackTrace();
    //                  throw new CustomDataAccessException(ex.getMessage());
    //              }
    //              return resultList;
    //          }
    //      }
//GetDropDownDivisionProcedure
         protected class GetDropDownCompanyProcedure extends StoredProcedure {
        protected GetDropDownCompanyProcedure(JdbcTemplate jdbcTemplate,
                                             String spName,
                                             RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
              declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            compile();
        }

        protected List<CrmDropDownCompanyMod> getDropDownList(Map mapParams) {
            Map outMap = new HashMap();
            List<CrmDropDownCompanyMod> resultList =
                new ArrayList<CrmDropDownCompanyMod>();
            try {

                outMap = execute(mapParams);
                resultList = (List<CrmDropDownCompanyMod>)outMap.get("P_O_DATA");
                return resultList;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
        }
    }
  
       protected class GetDropDownDivisionProcedure extends StoredProcedure {
        protected GetDropDownDivisionProcedure(JdbcTemplate jdbcTemplate,
                                             String spName,
                                             RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_COM_SEQNO",
                                               OracleTypes.NUMBER,
                                               rowMapper));
            compile();
        }

        protected List<CrmDropDownItemMod> getDropDownList(Map mapParams) {
            Map outMap = new HashMap();
            List<CrmDropDownItemMod> resultList =
                new ArrayList<CrmDropDownItemMod>();
            try {

                outMap = execute(mapParams);
                resultList = (List<CrmDropDownItemMod>)outMap.get("P_O_DATA");
                return resultList;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
        }
    }
      
  protected class   GetDropDownSectionProcedure  extends StoredProcedure {
   protected   GetDropDownSectionProcedure (JdbcTemplate jdbcTemplate,
                                        String spName,
                                        RowMapper rowMapper) {
       super(jdbcTemplate, spName);

       declareParameter(new SqlOutParameter("P_O_DATA",
                                            OracleTypes.CURSOR,
                                            rowMapper));
       declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                            OracleTypes.VARCHAR,
                                            rowMapper));
       declareParameter(new SqlInOutParameter("P_I_COM_SEQNO",
                                          OracleTypes.NUMBER,
                                          rowMapper));
       declareParameter(new SqlInOutParameter("P_I_DIV_SEQNO",
                                        OracleTypes.NUMBER,
                                        rowMapper));
       declareParameter(new SqlInOutParameter("P_I_DEP_SEQNO",
                                      OracleTypes.NUMBER,
                                      rowMapper));
     
     
     


     

       compile();
   }

   protected List<CrmDropDownItemMod> getDropDownList(Map mapParams) {
       Map outMap = new HashMap();
       List<CrmDropDownItemMod> resultList =
           new ArrayList<CrmDropDownItemMod>();
       try {

           outMap = execute(mapParams);
           resultList = (List<CrmDropDownItemMod>)outMap.get("P_O_DATA");
           return resultList;
       } catch (Exception ex) {
           ex.printStackTrace();
           throw new CustomDataAccessException(ex.getMessage());
       }
   }
  }
  //
  protected class   GetDropDownYearProcedure  extends StoredProcedure {
   protected   GetDropDownYearProcedure (JdbcTemplate jdbcTemplate,
                                        String spName,
                                        RowMapper rowMapper) {
       super(jdbcTemplate, spName);

       declareParameter(new SqlOutParameter("P_O_DATA",
                                            OracleTypes.CURSOR,
                                            rowMapper));
       declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                            OracleTypes.VARCHAR,
                                            rowMapper));
       compile();
   }

   protected List<CrmDropDownYearMod> getDropDownList(Map mapParams) {
       Map outMap = new HashMap();
       List<CrmDropDownYearMod> resultList =
           new ArrayList<CrmDropDownYearMod>();
       try {

           outMap = execute(mapParams);
           resultList = (List<CrmDropDownYearMod>)outMap.get("P_O_DATA");
           return resultList;
       } catch (Exception ex) {
           ex.printStackTrace();
           throw new CustomDataAccessException(ex.getMessage());
       }
   }
  }
  
  protected class   GetProfileGetProcedure  extends StoredProcedure {
   protected   GetProfileGetProcedure (JdbcTemplate jdbcTemplate,
                                        String spName,
                                        RowMapper rowMapper) {
     super(jdbcTemplate, spName);

     declareParameter(new SqlOutParameter("P_O_VALID",
                                          OracleTypes.VARCHAR,
                                          rowMapper));
     declareParameter(new SqlOutParameter("P_O_ERROR_MSG",
                                          OracleTypes.VARCHAR,
                                          rowMapper));
     declareParameter(new SqlOutParameter("P_O_DATA",
                                          OracleTypes.CURSOR,
                                          rowMapper));
     declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                          OracleTypes.VARCHAR,
                                          rowMapper));
     compile();
       
   }

   protected List<CrmProfileMod> getType(Map mapParams) {
       Map outMap = new HashMap();
       List<CrmProfileMod> userLevel;
       try {
            outMap = execute(mapParams);
           String valid=(String)outMap.get("P_O_VALID");
        
           if(valid.equals("N")){
             String error=(String)outMap.get("P_O_ERROR_MSG");
             throw new CustomDataAccessException(error );
           }
           else{
               userLevel = (List<CrmProfileMod>)outMap.get("P_O_DATA");
           
           }
           
           
        // P_O_DATA  VASAPPS.PCR_PMS_COMMON.CR_EMP OUT N/A
           
           
           return userLevel;
       } catch (Exception ex) {
           ex.printStackTrace();
           throw new CustomDataAccessException(ex.getMessage());
       }
   }
  }

  protected class   GetDropDownDepartmentProcedure  extends StoredProcedure {
   protected   GetDropDownDepartmentProcedure (JdbcTemplate jdbcTemplate,
                                        String spName,
                                        RowMapper rowMapper) {
       super(jdbcTemplate, spName);

       declareParameter(new SqlOutParameter("P_O_DATA",
                                            OracleTypes.CURSOR,
                                            rowMapper));
       declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                            OracleTypes.VARCHAR,
                                            rowMapper));
       declareParameter(new SqlInOutParameter("P_I_COM_SEQNO",
                                          OracleTypes.NUMBER,
                                          rowMapper));
       declareParameter(new SqlInOutParameter("P_I_DIV_SEQNO",
                                        OracleTypes.NUMBER,
                                        rowMapper));
     
     

       compile();
   }

   protected List<CrmDropDownItemMod> getDropDownList(Map mapParams) {
       Map outMap = new HashMap();
       List<CrmDropDownItemMod> resultList =
           new ArrayList<CrmDropDownItemMod>();
       try {

           outMap = execute(mapParams);
           resultList = (List<CrmDropDownItemMod>)outMap.get("P_O_DATA");
           return resultList;
       } catch (Exception ex) {
           ex.printStackTrace();
           throw new CustomDataAccessException(ex.getMessage());
       }
   }
  }

  protected class GetDropDownValuesProcedure extends StoredProcedure {
        protected GetDropDownValuesProcedure(JdbcTemplate jdbcTemplate,
                                             String spName,
                                             RowMapper rowMapper) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
              declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            compile();
        }

        protected List<CrmDropDownItemMod> getDropDownList(Map mapParams) {
            Map outMap = new HashMap();
            List<CrmDropDownItemMod> resultList =
                new ArrayList<CrmDropDownItemMod>();
            try {

                outMap = execute(mapParams);
                resultList = (List<CrmDropDownItemMod>)outMap.get("P_O_DATA");
                return resultList;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
        }
    }
  
    
    private class GetEmpPerfResProcedure extends StoredProcedure {
        private GetEmpPerfResProcedure(JdbcTemplate jdbcTemplate, String spName, RowMapper rowMapper) {
            super(jdbcTemplate, spName);
            
            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
            
            declareParameter(new SqlInOutParameter("P_I_YEAR",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_COMP_SEQ",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_DEPT_SEQ",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_JOB_BRAND",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper)); 
            declareParameter(new SqlInOutParameter("P_I_PERIOD",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_COMP_RANGE",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_INDKPI_RANGE",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));                   

            compile();
        }

        
        
        private List<PmsEmpPerfRes> getListData(Map mapParams) {
            Map outMap = new HashMap();
            System.out.println("getListData---getEmpPerfResData :-"+mapParams);
            List<PmsEmpPerfRes> outList = new ArrayList<PmsEmpPerfRes>();
            try {
                outMap = execute(mapParams);
                System.out.println(execute(mapParams));
                outList = (List<PmsEmpPerfRes>)outMap.get("P_O_DATA");
                return outList;
              
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }   
    }

    private class EmpPerfResRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            PmsEmpPerfRes res = new PmsEmpPerfRes();
            System.out.println("Inside EmpPerfResRowMapper:- "+res);
            res.setYear(rs.getInt("PM_YEAR"));
            res.setPeriod(rs.getInt("PM_PERIOD"));
            res.setEmpId(rs.getInt("EMP_ID"));
            res.setEmpName(RutString.nullToStr(rs.getString("EMP_NM")));
            res.setJobBand(RutString.nullToStr(rs.getString("JOB_BRAND")));
            res.setCompetencyPercent1H(RutString.nullToStr(rs.getString("COM_RATING_PERCENT_1H")));
            res.setCompetencyPercent2H(RutString.nullToStr(rs.getString("COM_RATING_PERCENT_2H")));
            res.setIndKPIPercent1H(RutString.nullToStr(rs.getString("INDIVIDUAL_RATING_PERCEN_1H")));
            res.setIndKPIPercent2H(RutString.nullToStr(rs.getString("INDIVIDUAL_RATING_PERCEN_2H")));
            return res;
        }
    }
    

  private class GetActivityListProcedure extends StoredProcedure {
      private GetActivityListProcedure(JdbcTemplate jdbcTemplate, String spName, RowMapper rowMapper) {
          super(jdbcTemplate, spName);
          
          declareParameter(new SqlOutParameter("P_O_DATA",
                                               OracleTypes.CURSOR,
                                               rowMapper));
          
          declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                               OracleTypes.VARCHAR,
                                               rowMapper));
          declareParameter(new SqlInOutParameter("P_I_EMP_SEQNO",
                                               OracleTypes.NUMBER,
                                               rowMapper));
          declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                               OracleTypes.NUMBER,
                                               rowMapper));
          
          compile();
      }

      
      
      private List<PmsActivityMod> getListData(Map mapParams) {
          Map outMap = new HashMap();
          List<PmsActivityMod> outList = new ArrayList<PmsActivityMod>();
          try {
              outMap = execute(mapParams);
              outList = (List<PmsActivityMod>)outMap.get("P_O_DATA");
              return outList;
            
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          return outList;
      }   
  }

    protected class PmsJobBrandMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int row) throws SQLException {
            CrmDropDownItemMod item = new CrmDropDownItemMod();
            item.setValue((RutString.nullToStr(rs.getString("BRAND_CODE"))));
            item.setDescription((RutString.nullToStr(rs.getString("BRAND_NAME"))));
            return item;
        }
    }
  protected class PmsYearMapper implements RowMapper {

      public Object mapRow(ResultSet rs, int row) throws SQLException {
          CrmDropDownYearMod item = new CrmDropDownYearMod();
          item.setValue((RutString.nullToStr(rs.getString("PM_YEAR"))));
         
          return item;
      }
  }
    
  protected class PmsProfileMapper implements RowMapper {

      public Object mapRow(ResultSet rs, int row) throws SQLException {
          CrmProfileMod item = new CrmProfileMod();
           item.setPkEmpSeqno((RutString.nullToStr(rs.getInt("PK_EMP_SEQNO"))));
           item.setEmpId((RutString.nullToStr(rs.getInt("EMP_ID"))));
           item.setEmpName((RutString.nullToStr(rs.getString("EMP_NAME"))));
           item.setJoinDate((RutString.nullToStr(rs.getString("JOIN_DATE"))));
           item.setFkComSeqno((RutString.nullToStr(rs.getString("FK_COM_SEQNO"))));
        item.setComName((RutString.nullToStr(rs.getString("COM_NAME"))));
        item.setFkDivSeqno((RutString.nullToStr(rs.getString("FK_DIV_SEQNO"))));
        item.setDivName((RutString.nullToStr(rs.getString("DIV_NAME"))));
        item.setFkDepSeqno((RutString.nullToStr(rs.getString("FK_DEP_SEQNO"))));
        item.setDepName((RutString.nullToStr(rs.getString("DEP_NAME"))));
        item.setFkSecSeqno((RutString.nullToStr(rs.getString("FK_SEC_SEQNO"))));
        item.setSecName((RutString.nullToStr(rs.getString("SEC_NAME"))));
        item.setJobGrade((RutString.nullToStr(rs.getString("JOB_GRADE"))));
        item.setJobGradeName((RutString.nullToStr(rs.getString("JOB_GRADE_NAME"))));
        item.setJobBrand((RutString.nullToStr(rs.getString("JOB_BRAND"))));
        item.setJobBrandName((RutString.nullToStr(rs.getString("JOB_BRAND_NAME"))));
        item.setManagerLv1((RutString.nullToStr(rs.getString("MANAGER_LV1"))));
        item.setManagerLv1Name((RutString.nullToStr(rs.getString("MANAGER_LV1_NAME"))));
        item.setManagerLv2((RutString.nullToStr(rs.getString("MANAGER_LV2"))));
        item.setManagerLv2Name((RutString.nullToStr(rs.getString("MANAGER_LV2_NAME"))));
        item.setDesignation((RutString.nullToStr(rs.getString("DESIGNATION"))));
        item.setUserId((RutString.nullToStr(rs.getString("USER_ID"))));
        item.setUserLevel((RutString.nullToStr(rs.getString("USER_LEVEL"))));
        item.setMyStaffCount((RutString.nullToStr(rs.getString("MY_STAFF_COUNT"))));
        item.setFlagCreate((RutString.nullToStr(rs.getString("FLAG_CREATE"))));
        item.setExpYear(rs.getInt("EXP_YEAR"));
        item.setExpMonth(rs.getInt("EXP_MONTH"));
//         
          return item;
      }
  }
    
  protected class PmsCompanyMapper implements RowMapper {
/*
 * "Return fields:
PK_COM_SEQNO
COM_NAME
COUNTRY"
 * */
      public Object mapRow(ResultSet rs, int row) throws SQLException {
          CrmDropDownCompanyMod item = new CrmDropDownCompanyMod();
          item.setValue((RutString.nullToStr(rs.getString("COM_NAME"))));
          item.setDescription((RutString.nullToStr(rs.getString("COUNTRY"))));
          item.setDescription2((RutString.nullToStr(rs.getString("PK_COM_SEQNO"))));
          return item;
      }
  }

   
  private class PMActivityRowMapper implements RowMapper {
      @Override
      public Object mapRow(ResultSet rs, int i) throws SQLException {
          PmsActivityMod mod = new PmsActivityMod();
          mod.setAction(RutString.nullToStr(rs.getString("ACTION")));
          mod.setActionBy(RutString.nullToStr(rs.getString("ACTION_BY")));
          mod.setActionDate(rs.getTimestamp("ACTION_DATE"));         
          return mod;
      }
  }
  
     
    @Override
    public List<PmsJobWeightageMod> getJobWeightageList(String userID, Integer pmYear,
                                                     Integer pmPeriod) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_YEAR", pmYear);
        mapParams.put("P_I_PM_PERIOD", pmPeriod);

        PmsLogUtil.dumpInputParams(mapParams);

        return getJobWeightageListProcedure.getList(mapParams);
    }

    @Override
    public List<PmsIndicatorMod> getIndicatorList(String userID, Integer pmYear, Integer pmPeriod,
                                                  String category) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_PM_YEAR", pmYear);
        mapParams.put("P_I_PM_PERIOD", pmPeriod);
        mapParams.put("P_I_CATEGORY", category);
        
        PmsLogUtil.dumpInputParams(mapParams);

        return getIndicatorListProcedure.getList(mapParams);
    }

    @Override
    public PmsJobWeightageMod getJobWeightage(String userID, String jobBrand, Integer pmYear,
                                                  Integer pmPeriod) throws DataAccessException {
        Map mapParams = new HashMap();
        mapParams.put("P_I_USER_ID", userID);
        mapParams.put("P_I_JOB_BRAND", jobBrand);
        mapParams.put("P_I_PM_YEAR", pmYear);
        mapParams.put("P_I_PM_PERIOD", pmPeriod);
                
        PmsLogUtil.dumpInputParams(mapParams);

        return getJobWeightageProcedure.get(mapParams);
    }


    private class GetJobWeightageListProcedure extends StoredProcedure {
        private GetJobWeightageListProcedure(JdbcTemplate jdbcTemplate, String spName,
                                             JobWeightageRowMapper rowMapper) {
            super(jdbcTemplate, spName);
            
            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
        
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            
            compile();
            
        }

       
        private List<PmsJobWeightageMod> getList(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsJobWeightageMod> outList = new ArrayList<PmsJobWeightageMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsJobWeightageMod>)outMap.get("P_O_DATA");
              
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        } 
    }
    private class GetJobWeightageProcedure extends StoredProcedure {
        private GetJobWeightageProcedure(JdbcTemplate jdbcTemplate, String spName,
                                             JobWeightageRowMapper rowMapper) {
            super(jdbcTemplate, spName);
            
            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
        
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_JOB_BRAND",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            
            compile();
            
        }

        private PmsJobWeightageMod get(Map mapParams) {
            Map outMap = new HashMap();
            PmsJobWeightageMod result = new PmsJobWeightageMod();
            try {
                outMap = execute(mapParams);
                result = (PmsJobWeightageMod)outMap.get("P_O_DATA");
              
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }   
        
    }

    private class JobWeightageRowMapper implements RowMapper{
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            
            PmsJobWeightageMod mod = new PmsJobWeightageMod();
            if (RutString.nullToStr (rs.getInt("PM_YEAR")) != "") {
            mod.setPmYear(Integer.valueOf(rs.getInt("PM_YEAR")));
            }
            if (RutString.nullToStr (rs.getInt("PM_PERIOD")) != "") {
                mod.setPmPeriod(Integer.valueOf(rs.getInt("PM_PERIOD")));
            }
            mod.setJobBrand(RutString.nullToStr(rs.getString("JOB_BRAND")));
            mod.setDescription(RutString.nullToStr(rs.getString("DESCRIPTION")));
            mod.setComWeightage(Double.valueOf(rs.getDouble("COM_WEIGHTAGE")));
            mod.setPdWeightage(Double.valueOf(rs.getDouble("PD_WEIGHTAGE")));
            mod.setIndKPIWeightage(Double.valueOf(rs.getDouble("IND_KPI_WEIGHTAGE")));
            return mod;
        }
    }

    private class GetIndicatorListProcedure extends StoredProcedure {
        private GetIndicatorListProcedure(JdbcTemplate jdbcTemplate, String spName, RowMapper rowMapper) {
            super(jdbcTemplate, spName);
            
            declareParameter(new SqlOutParameter("P_O_DATA",
                                                 OracleTypes.CURSOR,
                                                 rowMapper));
            
            declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_PM_PERIOD",
                                                 OracleTypes.NUMBER,
                                                 rowMapper));
            declareParameter(new SqlInOutParameter("P_I_CATEGORY",
                                                 OracleTypes.VARCHAR,
                                                 rowMapper));
            
            compile();
        }

        private List<PmsIndicatorMod> getList(Map mapParams) {
            Map outMap = new HashMap();
            List<PmsIndicatorMod> outList = new ArrayList<PmsIndicatorMod>();
            try {
                outMap = execute(mapParams);
                outList = (List<PmsIndicatorMod>)outMap.get("P_O_DATA");
              
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outList;
        }   
    }

    private class IndicatorRowMapper implements RowMapper {
        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            PmsIndicatorMod mod = new PmsIndicatorMod();
            mod.setSeqNo(Long.valueOf(rs.getLong("PK_IND_SEQNO")));
            mod.setName(RutString.nullToStr(rs.getString("IND_NAME")));
            mod.setDescription(RutString.nullToStr(rs.getString("DESCRIPTION")));
            mod.setCategory(RutString.nullToStr(rs.getString("IND_CATEGORY")));
            mod.setBscSeqNo(Long.valueOf(rs.getLong("FK_BSC_SEQNO")));
            mod.setBscName(RutString.nullToStr(rs.getString("BSC_NAME")));
            mod.setPmYear(Integer.valueOf(rs.getInt("PM_YEAR")));
            mod.setPmPeriod(Integer.valueOf(rs.getInt("PM_PERIOD")));
            mod.setWeightage(Double.valueOf(rs.getDouble("IND_WEIGHTAGE")));
            mod.setSlab0(RutString.nullToStr(rs.getString("IND_SLAB_0")));
            mod.setSlab1(RutString.nullToStr(rs.getString("IND_SLAB_1")));
            mod.setSlab2(RutString.nullToStr(rs.getString("IND_SLAB_2")));
            mod.setSlab3(RutString.nullToStr(rs.getString("IND_SLAB_3")));
            mod.setSlab4(RutString.nullToStr(rs.getString("IND_SLAB_4")));
            mod.setSlab5(RutString.nullToStr(rs.getString("IND_SLAB_5")));
            mod.setResult(RutString.nullToStr(rs.getString("IND_RESULT")));
            mod.setRating(RutString.nullToStr(rs.getString("IND_RATING")));
            return mod;
        }
    }
    
    private class GetActivePMYearPeriodProcedure extends StoredProcedure {
        public GetActivePMYearPeriodProcedure(JdbcTemplate jdbcTemplate, String spName) {
            super(jdbcTemplate, spName);

            declareParameter(new SqlOutParameter("P_O_VALID", OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_ERROR_MSG", OracleTypes.VARCHAR));
            declareParameter(new SqlOutParameter("P_O_PM_YEAR", OracleTypes.NUMBER));
            declareParameter(new SqlOutParameter("P_O_PM_PERIOD", OracleTypes.NUMBER));
            declareParameter(new SqlInOutParameter("P_I_USER_ID", OracleTypes.VARCHAR));

            compile();
        }

        private Map<String, Object> get(Map mapParams) {
            Map outMap = new HashMap();

            try {
                outMap = execute(mapParams);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return outMap;
        }
    }
    
    private class GetReportURLProcedure extends StoredProcedure{
        private GetReportURLProcedure(JdbcTemplate jdbcTemplate, String spName) {
            super(jdbcTemplate, spName);
            declareParameter(new SqlOutParameter("P_O_SERVER_PATH",
                                                 OracleTypes.VARCHAR
                                                 ));
            compile();
            
        }
        private String getURL(Map mapParams) {
            Map outMap = new HashMap();
           
            try {

                outMap = execute(mapParams);
                String strUrl = RutString.nullToStr(outMap.get("P_O_SERVER_PATH"));
                return strUrl;
                
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new CustomDataAccessException(ex.getMessage());
            }
        }
    }
}

