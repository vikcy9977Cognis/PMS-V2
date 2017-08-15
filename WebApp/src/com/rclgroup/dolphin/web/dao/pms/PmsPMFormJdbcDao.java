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
import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmFormMod;
import com.rclgroup.dolphin.web.model.pms.PmListMod;
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

public class PmsPMFormJdbcDao extends RrcStandardDao implements PmsPMFormDao {
    private static final String PMS_GET_PM_LIST_STORED_PROCEDURE =
      "VASAPPS.PCR_PMS_PM_FORM.PRR_PM_LIST";
    
 
    // private GetPMListProcedure getPmListProcedure;
//    private GetDropDownValuesProcedure   getJobBrandListProcedure;
//    private GetDropDownValuesProcedure   getJobGradeListProcedure;
//    private GetDropDownDivisionProcedure getDivisionListProcedure;
//    private GetDropDownCompanyProcedure   getCompanyListProcedure;
//    private GetDropDownDepartmentProcedure  getDepartmentListProcedure;
//    private GetDropDownSectionProcedure     getSectionListProcedure;
     private GetPMListProcedure     getPMListProcedure;
    public PmsPMFormJdbcDao() {
        super();

    }

    /**
     * @throws Exception
     */
    public void initDao() throws Exception {
        super.initDao();
      getPMListProcedure      =
      new GetPMListProcedure(getJdbcTemplate(),
                                         PMS_GET_PM_LIST_STORED_PROCEDURE,
                                         new PmsListMapper() );
    }
    
  public Integer getPMList(PmCriteria pmCriteria , List <PmListMod> outList) throws DataAccessException {
      Map<String, Object> mapParams = new HashMap<String, Object>();
//    P_I_USER_ID := '10492';
//      P_I_PM_KEY := NULL;
//      P_I_PAGE_ROWS := 10;
//      P_I_PAGE_NO := 1;
//      P_I_SORT := 'PH.PM_YEAR';
//      P_I_ASC_DESC := 'ASC';
//      P_I_EMP_ID := 70;
//      P_I_PM_YEAR := 2017;
      
 
              mapParams.put("P_I_USER_ID", pmCriteria.getUserId());
              mapParams.put("P_I_PM_KEY", pmCriteria.getPmKey());
              mapParams.put("P_I_PAGE_ROWS", pmCriteria.getIDisplayLength());
              mapParams.put("P_I_PAGE_NO", pmCriteria.getPageNo());
        
              mapParams.put("P_I_SORT", pmCriteria.getDefaultSortBy());
              mapParams.put("P_I_ASC_DESC", pmCriteria.getDefaultSortDirection());
              mapParams.put("P_I_EMP_ID", pmCriteria.getEmpId());
              mapParams.put("P_I_PM_YEAR", pmCriteria.getPmYear());
              mapParams.put("P_I_PM_PERIOD",  pmCriteria.getPmPeriod());
              mapParams.put("P_I_PM_STATUS",  pmCriteria.getPmStatus() );
              mapParams.put("P_I_EMP_NAME",  pmCriteria.getEmpName() );
              mapParams.put("P_I_COMPANY",  pmCriteria.getCompany() );
              mapParams.put("P_I_DIVISION",  pmCriteria.getDivision() );
              mapParams.put("P_I_DEPARTMENT", pmCriteria.getDepartment() );
        
              mapParams.put("P_I_SECTION",  pmCriteria.getSection() );
              mapParams.put("P_I_JOB_GRADE",  pmCriteria.getJobGrade() );
              mapParams.put("P_I_JOB_BRAND",  pmCriteria.getJobBrand());
        
              mapParams.put("P_I_DESIGNATION", pmCriteria.getDesignation());
              mapParams.put("P_I_MNGR_ID",pmCriteria.getMngrId())  ;
        
        
               PmsLogUtil.dumpInputParams(mapParams);
        
              return getPMListProcedure.getList(mapParams ,outList);

     
  }

 
        private class GetPMListProcedure extends StoredProcedure {
             
            protected GetPMListProcedure(JdbcTemplate jdbcTemplate,
                                        String spName,
                                        RowMapper rowMapper) {
                  super(jdbcTemplate, PMS_GET_PM_LIST_STORED_PROCEDURE);
                  declareParameter(new SqlOutParameter("P_O_DATA",
                                                       OracleTypes.CURSOR,
                                                       rowMapper));
                declareParameter(new SqlOutParameter("P_O_ROW_TOTAL",
                                                     OracleTypes.INTEGER));
                  declareParameter(new SqlInOutParameter("P_I_USER_ID",
                                                         OracleTypes.VARCHAR,
                                                         rowMapper));
                declareParameter(new SqlInOutParameter("P_I_PM_KEY",
                                                       OracleTypes.VARCHAR,
                                                       rowMapper));
                declareParameter(new SqlInOutParameter("P_I_PAGE_ROWS",
                                                       OracleTypes.INTEGER,
                                                       rowMapper));
                declareParameter(new SqlInOutParameter("P_I_PAGE_NO",
                                                       OracleTypes.INTEGER,
                                                       rowMapper));
                declareParameter(new SqlInOutParameter("P_I_SORT",
                                                       OracleTypes.VARCHAR,
                                                       rowMapper));
                //	VARCHAR2(200)	IN	PH.PM_YEAR
                //P_I_PAGE_ROWS	NUMBER	IN	10
                          
                declareParameter(new SqlInOutParameter("P_I_ASC_DESC",
                                                       OracleTypes.VARCHAR,
                                                       rowMapper));
                
                           declareParameter(new SqlInOutParameter("P_I_EMP_ID",
                                                                  OracleTypes.NUMBER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_PM_YEAR",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_PM_PERIOD",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_PM_STATUS",
                                                                  OracleTypes.VARCHAR,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_EMP_NAME",
                                                                  OracleTypes.VARCHAR,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_COMPANY",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_DIVISION",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_DEPARTMENT",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_SECTION",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_JOB_GRADE",
                                                                  OracleTypes.VARCHAR,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_JOB_BRAND",
                                                                  OracleTypes.VARCHAR,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_DESIGNATION",
                                                                  OracleTypes.VARCHAR,
                                                                  rowMapper));
                           declareParameter(new SqlInOutParameter("P_I_MNGR_ID",
                                                                  OracleTypes.INTEGER,
                                                                  rowMapper));
                          
    
                  compile();
              }

      


        private Integer getList(Map mapParams, List<PmListMod> outList) {
                Map outMap = new HashMap();
                Integer nTotalRows = 0;
                try {
                    outMap = execute(mapParams);
                    List<PmListMod> tmpList = (List<PmListMod>)outMap.get("P_O_DATA");

                    nTotalRows =
                            ((Integer)outMap.get("P_O_ROW_TOTAL")).intValue();
                    if (nTotalRows > 0) {
                        if (outList != null) {
                            outList.clear();
                            outList.addAll(tmpList);
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    //  throw new CustomDataAccessException(ex.getMessage());
                }
                return nTotalRows;
            }
            
            
          }

  protected class PmsListMapper implements RowMapper {

      public Object mapRow(ResultSet rs, int row) throws SQLException {
          PmListMod item = new PmListMod();
        item.setPkhdrseqno((RutString.nullToStr(rs.getString("PK_HDR_SEQNO"))));
        item.setPmyear((RutString.nullToStr(rs.getString("PM_YEAR"))));
        item.setPmperiod((RutString.nullToStr(rs.getString("PM_PERIOD"))));
        item.setFkempseqno((RutString.nullToStr(rs.getString("FK_EMP_SEQNO"))));
        item.setManagerlv1((RutString.nullToStr(rs.getString("MANAGER_LV1"))));
        item.setManagerlv2((RutString.nullToStr(rs.getString("MANAGER_LV2"))));
        item.setEmpname((RutString.nullToStr(rs.getString("EMP_NAME"))));
        item.setFkcomseqno((RutString.nullToStr(rs.getString("FK_COM_SEQNO"))));
        item.setComname((RutString.nullToStr(rs.getString("COM_NAME"))));
        item.setFkdivseqno((RutString.nullToStr(rs.getString("FK_DIV_SEQNO"))));
        item.setDivname((RutString.nullToStr(rs.getString("DIV_NAME"))));
        item.setFkdepseqno((RutString.nullToStr(rs.getString("FK_DEP_SEQNO"))));
        item.setDepname((RutString.nullToStr(rs.getString("DEP_NAME"))));
        item.setFksecseqno((RutString.nullToStr(rs.getString("FK_SEC_SEQNO"))));
        item.setSecname((RutString.nullToStr(rs.getString("SEC_NAME"))));
        item.setJobgrade((RutString.nullToStr(rs.getString("JOB_GRADE"))));
        item.setJobgradename((RutString.nullToStr(rs.getString("JOB_GRADE_NAME"))));
        item.setJobbrand((RutString.nullToStr(rs.getString("JOB_BRAND"))));
        item.setJobbrandname((RutString.nullToStr(rs.getString("JOB_BRAND_NAME"))));
        item.setDesignation((RutString.nullToStr(rs.getString("DESIGNATION"))));
        item.setPmstatus((RutString.nullToStr(rs.getString("PM_STATUS"))));
        item.setLv1approveby((RutString.nullToStr(rs.getString("LV1_APPROVE_BY"))));
        item.setApprovelv1name((RutString.nullToStr(rs.getString("APPROVE_LV1_NAME"))));
        item.setLv2approveby((RutString.nullToStr(rs.getString("LV2_APPROVE_BY"))));
        item.setApprovelv2name((RutString.nullToStr(rs.getString("APPROVE_LV2_NAME"))));
        item.setMystaffcount((RutString.nullToStr(rs.getString("MY_STAFF_COUNT"))));
//         
          return item;
      }
  }
 
}

