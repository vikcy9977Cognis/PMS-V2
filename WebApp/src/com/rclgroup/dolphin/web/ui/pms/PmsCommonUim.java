/*-----------------------------------------------------------------------------------------------------------
LoaCommonUim.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2016
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 28/02/16
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.RrcStandardUim;


import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownCompanyMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.misc.CrmProfileMod;
import com.rclgroup.dolphin.web.model.pms.PmFormMod;
import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsEmpPerfRes;
import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;
import com.rclgroup.dolphin.web.util.RutString;


import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.math.BigDecimal ;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PmsCommonUim extends RrcStandardUim implements Serializable {
    private static final long serialVersionUID = 1L;
    private PmsCommonDao pmsCommonDao;
    private PmsCommon2Dao pmsCommon2Dao;

    public PmsCommonUim() {
        super();
    }

    public PmsCommonDao getPmsCommonDao() {
        return pmsCommonDao;
    }

    public void setPmsCommonDao(PmsCommonDao pmsCommonDao) {
        this.pmsCommonDao = pmsCommonDao;
    }

    /**
     *  Return  Job Brand  List.
     * @return
     */
    public List<CrmProfileMod> getProfileType() {
        //need change
        List<CrmProfileMod> resultList = this.pmsCommonDao.getProfileType(this.getPrsnLogIdOfUser());


        return resultList;
    }


  /**
   *  Return  year and period
   * @return HashMap year and value
   */
  public Map getActiveYearPeriod() {
       HashMap hm = new HashMap();
        // Put elements to the map
        //need change
        // List
        Map<String,Object> mapData=this.pmsCommon2Dao.
            getActivePMYearPeriod(this.getPrsnLogIdOfUser());
    
        BigDecimal yearBigDecimal = (BigDecimal)mapData.get("P_O_PM_YEAR") ;
        BigDecimal periodBigDecimal = (BigDecimal)mapData.get("P_O_PM_PERIOD") ;
        
        hm.put("year", yearBigDecimal.intValue());
        hm.put("period", periodBigDecimal.intValue());
      return hm;
  }
    public JSONArray formatProfileTypeAsJSON(List<CrmProfileMod> mods) throws ParseException {
        JSONArray data = new JSONArray();
        JSONObject item;

        final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        final SimpleDateFormat sfToStr = new SimpleDateFormat("dd/MM/yyyy");
        for (int idx = 0; idx < mods.size(); idx++) {
            final CrmProfileMod mod = mods.get(idx);
            item = new JSONObject();
            
            String joinDate = "";
            final String joinDateStr = mod.getJoinDate();
            if(joinDateStr!=null&&joinDateStr.length()>0){            
                final Date date = sf.parse(joinDateStr);
                joinDate = sfToStr.format(date);
            }
            
            item.put("comName", mod.getComName());
            item.put("depName", mod.getDepName());
            item.put("designation", mod.getDesignation());
            item.put("divName", mod.getDivName());
            item.put("empId", mod.getEmpId());
            item.put("empName", mod.getEmpName());
            item.put("fkComSeqno", mod.getFkComSeqno());
            item.put("fkDepSeqno", mod.getFkDepSeqno());
            item.put("fkDivSeqno", mod.getFkDivSeqno());
            item.put("fkSecSeqno", mod.getFkSecSeqno());
            item.put("flagCreate", mod.getFlagCreate());
            item.put("jobBrand", mod.getJobBrand());
            item.put("jobBrandName", mod.getJobBrandName());
            item.put("jobGrade", mod.getJobGrade());
            item.put("jobGradeName", mod.getJobGradeName());
            item.put("joinDate", joinDate);
            item.put("managerLv1", mod.getManagerLv1());
            item.put("managerLv1Name", mod.getManagerLv1Name());
            item.put("managerLv2", mod.getManagerLv2());
            item.put("managerLv2Name", mod.getManagerLv2Name());
            item.put("myStaffCount", mod.getMyStaffCount());
            item.put("pkEmpSeqno", mod.getPkEmpSeqno());
            item.put("secName", mod.getSecName());
            item.put("userId", mod.getUserId());
            item.put("userLevel", mod.getUserLevel());
            item.put("expYear", mod.getExpYear());
            item.put("expMonth", mod.getExpMonth());
            
            data.add(item);
        }
        return data;
    }

    /*public JSONArray formatCustomerListAsJSON(List<LoaMod> customerList,
                                              Integer nStartRowNo) {
        JSONArray data = new JSONArray();
        JSONObject item;

        int rowno = nStartRowNo;
        for (int idx = 0; idx < customerList.size(); idx++) {
            LoaMod mod = customerList.get(idx);
            item = new JSONObject();
            item.put("rowNo", rowno + idx);
            item.put(("customerCode"), mod.getCustomerCode());
            item.put("customerName", mod.getCustomerName());
            item.put("country", mod.getCountry());
            data.add(item);
        }
        return data;
    }*/

    public List<PmsJobWeightageMod>  getJobWeightageList(final Integer pmYear, final Integer pmPeriod) {
        List<PmsJobWeightageMod>  resultList=
              this.pmsCommonDao.getJobWeightageList(this.getPrsnLogIdOfUser(), pmYear, pmPeriod);
        
        return resultList;
    }

    public JSONArray formatJobWeightageAsJSON(List<PmsJobWeightageMod> mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final PmsJobWeightageMod mod = mods.get(idx);
            item = new JSONObject();
            
            item.put("comWeightage", mod.getComWeightage());
            item.put("description", mod.getDescription());
            item.put("indKPIWeightage", mod.getIndKPIWeightage());
            item.put("jobBrand", mod.getJobBrand());
            item.put("pdWeightage", mod.getPdWeightage());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
            
            data.add(item);
        }
        return data;
    }

    /**
     *  Return  Job Brand  List.
     * @return
     */
    public List<CrmDropDownItemMod> getJobBrandList() {

        List<CrmDropDownItemMod> jobBrandList = this.pmsCommonDao.getJobBrandList(this.getPrsnLogIdOfUser());
        List<CrmDropDownItemMod> resultList;
        resultList = new ArrayList<CrmDropDownItemMod>();
        resultList.add(new CrmDropDownItemMod("", "All")); // Add empty item
        resultList.addAll(jobBrandList);
        return resultList;
    }

    /**
     *  Return  Job Grade  List.
     * @return
     */
    public List<CrmDropDownItemMod> getJobGradeList() {
        List<CrmDropDownItemMod> jobGradeList = this.pmsCommonDao.getJobGradeList(this.getPrsnLogIdOfUser());
        List<CrmDropDownItemMod> resultList;
        resultList = new ArrayList<CrmDropDownItemMod>();
        resultList.add(new CrmDropDownItemMod("", "All")); // Add empty item
        resultList.addAll(jobGradeList);
        return resultList;
    }

    /**
     *  Return  Job Year.
     * @return
     */


    public List<CrmDropDownYearMod> getYearList() {
        List<CrmDropDownYearMod> jobGradeList = this.pmsCommonDao.getYearList(this.getPrsnLogIdOfUser());
        List<CrmDropDownYearMod> resultList;
        resultList = new ArrayList<CrmDropDownYearMod>();
        resultList.add(new CrmDropDownYearMod("All")); // Add empty item
        resultList.addAll(jobGradeList);
        return resultList;
    }


    //    /**
    //       *  Return Currency List.
    //       * @return
    //       */
    //      public List<ValueDescriptionDropDownItemMod> getCurrencyList() {
    //          List<ValueDescriptionDropDownItemMod> countryList =
    //              this.loaCommonDao.getCurrencyList();
    //
    //          List<ValueDescriptionDropDownItemMod> resultList =
    //              new ArrayList<ValueDescriptionDropDownItemMod>();
    //          resultList.add(new ValueDescriptionDropDownItemMod("",
    //                                                       "All")); // Add empty item
    //          resultList.addAll(countryList);
    //
    //          return resultList;
    //      }
    //    /**
    //     * Format Region as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatRegionListAsJSON(List<LoaRegionMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaRegionMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("region", mod.getRegion_code());
    //            item.put("region_name", mod.getRegion_name());
    //            item.put("fsc", mod.getFsc());
    //            item.put("port", mod.getPort());
    //            item.put("point", mod.getPoint());
    //            item.put("terminal", mod.getTerminal());
    //            item.put("depot", mod.getDepot());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    //    /**
    //    * Format Fsc as JSON array list
    //    * @param outList
    //    * @param nStartRowNum
    //    * @return
    //    */
    //    JSONArray formatFscListAsJSON(List<LoaFscMod> outList,
    //                                    int nStartRowNum) {
    //       JSONArray data = new JSONArray();
    //       JSONObject item;
    //
    //       int rowno = nStartRowNum;
    //       for (int idx = 0; idx < outList.size(); idx++) {
    //           LoaFscMod mod = outList.get(idx);
    //           item = new JSONObject();
    //           item.put("rowNo", rowno + idx);
    //           item.put("region", mod.getRegion());
    //           item.put("fsc", mod.getFsc());
    //           item.put("fsc_name", mod.getFsc_name());
    //           item.put("port", mod.getPort());
    //           item.put("point", mod.getPoint());
    //           item.put("terminal", mod.getTerminal());
    //           item.put("depot", mod.getDepot());
    //           data.add(item);
    //       }
    //       return data;
    //    }
    //    /**
    //    * Format Port as JSON array list
    //    * @param outList
    //    * @param nStartRowNum
    //    * @return
    //    */
    //    JSONArray formatPortListAsJSON(List<LoaPortMod> outList,
    //                                    int nStartRowNum) {
    //       JSONArray data = new JSONArray();
    //       JSONObject item;
    //
    //       int rowno = nStartRowNum;
    //       for (int idx = 0; idx < outList.size(); idx++) {
    //           LoaPortMod mod = outList.get(idx);
    //           item = new JSONObject();
    //           item.put("rowNo", rowno + idx);
    //           item.put("region", mod.getRegion());
    //           item.put("fsc", mod.getFsc());
    //           item.put("port", mod.getPort());
    //           item.put("port_name", LoaUtil.removeSpecialChars( mod.getPort_name()));
    //           item.put("point", mod.getPoint());
    //           item.put("terminal", mod.getTerminal());
    //           item.put("depot", mod.getDepot());
    //           data.add(item);
    //       }
    //       return data;
    //    }
    //    /**
    //    * Format Terminal as JSON array list
    //    * @param outList
    //    * @param nStartRowNum
    //    * @return
    //    */
    //    JSONArray formatTerminalListAsJSON(List<LoaTerminalMod> outList,
    //                                    int nStartRowNum) {
    //       JSONArray data = new JSONArray();
    //       JSONObject item;
    //
    //       int rowno = nStartRowNum;
    //       for (int idx = 0; idx < outList.size(); idx++) {
    //           LoaTerminalMod mod = outList.get(idx);
    //           item = new JSONObject();
    //           item.put("rowNo", rowno + idx);
    //           item.put("region", mod.getRegion());
    //           item.put("fsc", mod.getFsc());
    //           item.put("port", mod.getPort());
    //           item.put("point", mod.getPoint());
    //           item.put("terminal", mod.getTerminal());
    //           item.put("terminal_name", mod.getTerminal_name());
    //           item.put("depot", mod.getDepot());
    //           data.add(item);
    //       }
    //       return data;
    //    }
    //    /**
    //     * Format Point as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatPointListAsJSON(List<LoaPointMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaPointMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("region", mod.getRegion());
    //            item.put("fsc", mod.getFsc());
    //            item.put("port", mod.getPort());
    //            item.put("point", mod.getPoint());
    //            item.put("point_name", mod.getPoint_name());
    //            item.put("terminal", mod.getTerminal());
    //            item.put("depot", mod.getDepot());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    //    /**
    //     * Format Depot as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatDepotListAsJSON(List<LoaDepotMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaDepotMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("region", mod.getRegion());
    //            item.put("fsc", mod.getFsc());
    //            item.put("port", mod.getPort());
    //            item.put("point", mod.getPoint_code());
    //            item.put("terminal", mod.getTerminal());
    //            item.put("depot", mod.getDepot());
    //            item.put("depot_name", mod.getDepot_name());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    //    /**
    //     * Format User as JSON array list
    //     * @param outList
    //     * @param nStartRowNum
    //     * @return
    //     */
    //    JSONArray formatUserListAsJSON(List<LoaUserMod> outList,
    //                                     int nStartRowNum) {
    //        JSONArray data = new JSONArray();
    //        JSONObject item;
    //
    //        int rowno = nStartRowNum;
    //        for (int idx = 0; idx < outList.size(); idx++) {
    //            LoaUserMod mod = outList.get(idx);
    //            item = new JSONObject();
    //            item.put("rowNo", rowno + idx);
    //            item.put("fsc", mod.getFsc_code());
    //            item.put("userID", mod.getUser_id());
    //            item.put("userName", mod.getUser_name());
    //            data.add(item);
    //        }
    //        return data;
    //    }
    public List<PmsIndicatorMod> getIndicatorList(Integer pmYear, Integer pmPeriod, String category) {

        final List<PmsIndicatorMod> resultList = this.pmsCommonDao.getIndicatorList(super.getPrsnLogIdOfUser(), pmYear, pmPeriod, category);

        return resultList;
    }

    public JSONArray formatIndigatorAsJSON(List<PmsIndicatorMod> mods) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < mods.size(); idx++) {
            final PmsIndicatorMod mod = mods.get(idx);
            item = new JSONObject();
            
            item.put("bscName", mod.getBscName());
            item.put("bscSeqNo", mod.getBscSeqNo());
            item.put("category", mod.getCategory());
            item.put("description", mod.getDescription());
            item.put("name", mod.getName());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
            item.put("rating", mod.getRating());
            item.put("result", mod.getResult());
            item.put("seqNo", mod.getSeqNo());
            item.put("slab0", mod.getSlab0());
            item.put("slab1", mod.getSlab1());
            item.put("slab2", mod.getSlab2());
            item.put("slab3", mod.getSlab3());
            item.put("slab4", mod.getSlab4());
            item.put("slab5", mod.getSlab5());
            item.put("weightage", mod.getWeightage());
            
            data.add(item);
        }
        return data;
    }
    
    public Map<String, Object> getActivePMYearPeriod(){
        return this.pmsCommonDao.getActivePMYearPeriod(super.getPrsnLogIdOfUser());
    }

    public PmsCommon2Dao getPmsCommon2Dao() {
        return pmsCommon2Dao;
    }

    public void setPmsCommon2Dao(PmsCommon2Dao pmsCommon2Dao) {
        this.pmsCommon2Dao = pmsCommon2Dao;
    }

  
 
  
 
  /**
     *  Return  Job Year.
     * @return
     */
  
  public List<CrmDropDownCompanyMod> getCompanyList() {
        List<CrmDropDownCompanyMod> resultList = this.pmsCommonDao.getCompanyList(this.getPrsnLogIdOfUser() );
       
  
   
      return resultList;
  }
  
  /**
     *  Return  
     * @return
     */
  
  public List<CrmDropDownItemMod> getDivisionList( Integer comSeqno) {
        List<CrmDropDownItemMod> resultList = this.pmsCommonDao.getDivisionList(this.getPrsnLogIdOfUser(),comSeqno);
       
  
   
      return resultList;
  }
  /**
     *  Return  
     * @return
     */
  
  public List<CrmDropDownItemMod> getDepartmentList( Integer comSeqno ,Integer divSeqno) {
        List<CrmDropDownItemMod> resultList = this.pmsCommonDao.getDepartmentList(this.getPrsnLogIdOfUser(), comSeqno, divSeqno);
       
  
   
      return resultList;
  }
  /**
     *  Return  
     * @return
     */
  
  public List<CrmDropDownItemMod> getSectionList( Integer comSeqno ,Integer divSeqno,Integer depSeqno) {
        List<CrmDropDownItemMod> resultList = this.pmsCommonDao.getSectionList(this.getPrsnLogIdOfUser(), comSeqno, divSeqno, depSeqno);
       
  
   
      return resultList;
  }
  

//    /**
//       *  Return Currency List.
//       * @return
//       */
//      public List<ValueDescriptionDropDownItemMod> getCurrencyList() {
//          List<ValueDescriptionDropDownItemMod> countryList =
//              this.loaCommonDao.getCurrencyList();
//
//          List<ValueDescriptionDropDownItemMod> resultList =
//              new ArrayList<ValueDescriptionDropDownItemMod>();
//          resultList.add(new ValueDescriptionDropDownItemMod("",
//                                                       "All")); // Add empty item
//          resultList.addAll(countryList);
//
//          return resultList;
//      }
//    /**
//     * Format Region as JSON array list
//     * @param outList
//     * @param nStartRowNum
//     * @return
//     */
//    JSONArray formatRegionListAsJSON(List<LoaRegionMod> outList,
//                                     int nStartRowNum) {
//        JSONArray data = new JSONArray();
//        JSONObject item;
//
//        int rowno = nStartRowNum;
//        for (int idx = 0; idx < outList.size(); idx++) {
//            LoaRegionMod mod = outList.get(idx);
//            item = new JSONObject();
//            item.put("rowNo", rowno + idx);
//            item.put("region", mod.getRegion_code());
//            item.put("region_name", mod.getRegion_name());
//            item.put("fsc", mod.getFsc());
//            item.put("port", mod.getPort());
//            item.put("point", mod.getPoint());
//            item.put("terminal", mod.getTerminal());
//            item.put("depot", mod.getDepot());
//            data.add(item);
//        }
//        return data;
//    }
//    /**
//    * Format Fsc as JSON array list
//    * @param outList
//    * @param nStartRowNum
//    * @return
//    */
//    JSONArray formatFscListAsJSON(List<LoaFscMod> outList,
//                                    int nStartRowNum) {
//       JSONArray data = new JSONArray();
//       JSONObject item;
//
//       int rowno = nStartRowNum;
//       for (int idx = 0; idx < outList.size(); idx++) {
//           LoaFscMod mod = outList.get(idx);
//           item = new JSONObject();
//           item.put("rowNo", rowno + idx);
//           item.put("region", mod.getRegion());           
//           item.put("fsc", mod.getFsc());
//           item.put("fsc_name", mod.getFsc_name());
//           item.put("port", mod.getPort());
//           item.put("point", mod.getPoint());
//           item.put("terminal", mod.getTerminal());
//           item.put("depot", mod.getDepot());
//           data.add(item);
//       }
//       return data;
//    }
//    /**
//    * Format Port as JSON array list
//    * @param outList
//    * @param nStartRowNum
//    * @return
//    */
//    JSONArray formatPortListAsJSON(List<LoaPortMod> outList,
//                                    int nStartRowNum) {
//       JSONArray data = new JSONArray();
//       JSONObject item;
//
//       int rowno = nStartRowNum;
//       for (int idx = 0; idx < outList.size(); idx++) {
//           LoaPortMod mod = outList.get(idx);
//           item = new JSONObject();
//           item.put("rowNo", rowno + idx);
//           item.put("region", mod.getRegion());          
//           item.put("fsc", mod.getFsc());
//           item.put("port", mod.getPort());
//           item.put("port_name", LoaUtil.removeSpecialChars( mod.getPort_name()));
//           item.put("point", mod.getPoint());
//           item.put("terminal", mod.getTerminal());
//           item.put("depot", mod.getDepot());
//           data.add(item);
//       }
//       return data;
//    }
//    /**
//    * Format Terminal as JSON array list
//    * @param outList
//    * @param nStartRowNum
//    * @return
//    */
//    JSONArray formatTerminalListAsJSON(List<LoaTerminalMod> outList,
//                                    int nStartRowNum) {
//       JSONArray data = new JSONArray();
//       JSONObject item;
//
//       int rowno = nStartRowNum;
//       for (int idx = 0; idx < outList.size(); idx++) {
//           LoaTerminalMod mod = outList.get(idx);
//           item = new JSONObject();
//           item.put("rowNo", rowno + idx);
//           item.put("region", mod.getRegion());          
//           item.put("fsc", mod.getFsc());
//           item.put("port", mod.getPort());
//           item.put("point", mod.getPoint());
//           item.put("terminal", mod.getTerminal());
//           item.put("terminal_name", mod.getTerminal_name());
//           item.put("depot", mod.getDepot());
//           data.add(item);
//       }
//       return data;
//    }
//    /**
//     * Format Point as JSON array list
//     * @param outList
//     * @param nStartRowNum
//     * @return
//     */
//    JSONArray formatPointListAsJSON(List<LoaPointMod> outList,
//                                     int nStartRowNum) {
//        JSONArray data = new JSONArray();
//        JSONObject item;
//
//        int rowno = nStartRowNum;
//        for (int idx = 0; idx < outList.size(); idx++) {
//            LoaPointMod mod = outList.get(idx);
//            item = new JSONObject();
//            item.put("rowNo", rowno + idx);
//            item.put("region", mod.getRegion());          
//            item.put("fsc", mod.getFsc());
//            item.put("port", mod.getPort());
//            item.put("point", mod.getPoint());
//            item.put("point_name", mod.getPoint_name());
//            item.put("terminal", mod.getTerminal());
//            item.put("depot", mod.getDepot());
//            data.add(item);
//        }
//        return data;
//    }
//    /**
//     * Format Depot as JSON array list
//     * @param outList
//     * @param nStartRowNum
//     * @return
//     */
//    JSONArray formatDepotListAsJSON(List<LoaDepotMod> outList,
//                                     int nStartRowNum) {
//        JSONArray data = new JSONArray();
//        JSONObject item;
//
//        int rowno = nStartRowNum;
//        for (int idx = 0; idx < outList.size(); idx++) {
//            LoaDepotMod mod = outList.get(idx);
//            item = new JSONObject();
//            item.put("rowNo", rowno + idx);
//            item.put("region", mod.getRegion());            
//            item.put("fsc", mod.getFsc());
//            item.put("port", mod.getPort());
//            item.put("point", mod.getPoint_code());
//            item.put("terminal", mod.getTerminal());
//            item.put("depot", mod.getDepot());
//            item.put("depot_name", mod.getDepot_name());
//            data.add(item);
//        }
//        return data;
//    }
//    /**
//     * Format User as JSON array list
//     * @param outList
//     * @param nStartRowNum
//     * @return
//     */
//    JSONArray formatUserListAsJSON(List<LoaUserMod> outList,
//                                     int nStartRowNum) {
//        JSONArray data = new JSONArray();
//        JSONObject item;
//
//        int rowno = nStartRowNum;
//        for (int idx = 0; idx < outList.size(); idx++) {
//            LoaUserMod mod = outList.get(idx);
//            item = new JSONObject();
//            item.put("rowNo", rowno + idx);            
//            item.put("fsc", mod.getFsc_code());                                
//            item.put("userID", mod.getUser_id());
//            item.put("userName", mod.getUser_name());
//            data.add(item);
//        }
//        return data;
//    }

   public JSONArray  getEmpPerfResAsJSON(int year, int comp, int dept, String jobBand , int period, String compRange, String indKPIRange) {
    List<PmsEmpPerfRes>  resultList =   this.pmsCommonDao.getEmpPerfResData(year, comp,dept, jobBand, period, compRange, indKPIRange);
    JSONArray data = new JSONArray();
    JSONObject item;
    System.out.print(" -- -- - - " + resultList.size());
   

    
    for (int idx = 0; idx < resultList.size(); idx++) {
        PmsEmpPerfRes res = resultList.get(idx);
        item = new JSONObject();
    
        item.put(("year"), res.getYear());
        item.put("period", res.getPeriod());
        item.put("empId", res.getEmpId());
        item.put("empName", res.getEmpName());
        item.put("jobBand", res.getJobBand());
        item.put("competencyPercent1H", res.getCompetencyPercent1H());
        item.put("competencyPercent2H", res.getCompetencyPercent2H());
        item.put("averageCompetencyPercent", (Integer.parseInt(res.getCompetencyPercent1H()) +Integer.parseInt(res.getCompetencyPercent2H()))/2);        
        item.put("indKPIPercent1H", res.getIndKPIPercent1H());
        item.put("indKPIPercent2H", res.getIndKPIPercent2H());
        item.put("averageIndKPIPercent", (Integer.parseInt(res.getIndKPIPercent1H()) +Integer.parseInt(res.getIndKPIPercent1H()))/2);        
        
        String resComp1H = calcResult(res.getCompetencyPercent1H());
        item.put("resComp1H", resComp1H);
        
        String resComp2H = calcResult(res.getCompetencyPercent2H());
        item.put("resComp2H", resComp2H);
        
        String resCompAvg = calcResult(item.get("averageCompetencyPercent").toString());
        item.put("resCompAvg", resCompAvg);
        
        String resIndKPI1H = calcResult(res.getIndKPIPercent1H());
        item.put("resIndKPI1H", resIndKPI1H);
        
        String resIndKPI2H = calcResult(res.getIndKPIPercent2H());
        item.put("resIndKPI2H", resIndKPI2H);
        
        String resIndKPIAvg = calcResult(item.get("averageIndKPIPercent").toString());
        item.put("resIndKPIAvg", resIndKPIAvg);
        
        data.add(item);
    }
    System.out.print(" -- -- - - " + data);
    return data;
     
  }

     public JSONArray  getActivityListAsJSON(Long empSeqNo,Integer pmYear) {
      List<PmsActivityMod>  resultList =   this.pmsCommonDao.getActivityListData( this.getPrsnLogIdOfUser(), empSeqNo,  pmYear );
    JSONArray data = new JSONArray();
    JSONObject item;
    System.out.print(" -- -- - - " + resultList.size());
   

    
    for (int idx = 0; idx < resultList.size(); idx++) {
        PmsActivityMod mod = resultList.get(idx);
        item = new JSONObject();
    
        item.put(("action"), mod.getAction());
        item.put("actionBy", mod.getActionBy());
        item.put("actionDate", mod.getActionDate()+"");
        data.add(item);
    }
    System.out.print(" -- -- - - " + data);
    return data;
     
  }
    
    public PmsJobWeightageMod getJobWeightage(final String jobBrand, Integer pmYear,
                                              Integer pmPeriod){
        final PmsJobWeightageMod mod = this.pmsCommon2Dao.getJobWeightage(super.getPrsnLogIdOfUser(), jobBrand, pmYear, pmPeriod);
        return mod;
    }

    public JSONObject formatJobWeightagetoJSON(final PmsJobWeightageMod mod) {
        JSONObject item = new JSONObject();
        if(mod!=null){
            item.put("comWeightage", mod.getComWeightage());
            item.put("description", mod.getDescription());
            item.put("indKPIWeightage", mod.getIndKPIWeightage());
            item.put("jobBrand", mod.getJobBrand());
            item.put("pdWeightage", mod.getPdWeightage());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
        }

        return item;
    }

    private String calcResult(String percentage) {
        
        int per = Integer.parseInt(percentage);
       
        if(per >= 85 && per<=100)
            return "Outstanding";
        else if(per >= 70 && per<=84)
            return "Very Good";
        else if(per >= 60 && per<=69)
            return "Good";
        else if(per >= 50 && per<=59)
            return "Fair";
        else if(per >= 0 && per<=49)
            return "Poor";
        return "";
    }
}
