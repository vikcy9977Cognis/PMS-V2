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


import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMForm2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsPMFormDao;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownItemMod;
import com.rclgroup.dolphin.web.model.misc.CrmDropDownYearMod;
import com.rclgroup.dolphin.web.model.pms.PmCriteria;
import com.rclgroup.dolphin.web.model.pms.PmFormMod;
import com.rclgroup.dolphin.web.model.pms.PmListMod;
import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;
import com.rclgroup.dolphin.web.util.RutString;



import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.dao.DataAccessException;

public class PmsPMUim extends RrcStandardUim implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userLevel;
    private Long empID;
    private Long pkEmpSeqNo;
    private String mngrID ;
    private PmsCommonDao pmsCommonDao;
    private PmsPMFormDao pmsPMFormDao;
    private PmsPMForm2Dao pmsPMForm2Dao;
    private  PmCriteria pmCriteria;
    
    public PmsPMUim() {
        super();
    }
    public PmsCommonDao getPmsCommonDao() {
        return pmsCommonDao;
    }

    public void setPmsCommonDao(PmsCommonDao pmsCommonDao) {
        this.pmsCommonDao = pmsCommonDao;
    }
    public void setPmsPMFormDao(PmsPMFormDao pmsPMFormDao) {
        this.pmsPMFormDao = pmsPMFormDao;
    }
  
    public PmsPMFormDao getPmsPMFormDao() {
        return pmsPMFormDao;
    }
    public PmsPMForm2Dao getPmsPMForm2Dao() {
        return pmsPMForm2Dao;
    }

    public void setPmsPMForm2Dao(PmsPMForm2Dao pmsPMForm2Dao) {
        this.pmsPMForm2Dao = pmsPMForm2Dao;
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
    /**
       *  Return  Job Brand  List.
       * @return
       */
     public JSONObject getPMList(PmCriteria pmCriteria) throws DataAccessException {
         
         List <PmListMod> outList = new ArrayList<PmListMod>();
        pmCriteria.setUserId(this.getPrsnLogIdOfUser());
        
        // pmCriteria.setUserId(this.getPrsnLogIdOfUser());
        //this.get
        //    P_I_USER_ID := '10492';
        //      P_I_PM_KEY := NULL;
        //      P_I_PAGE_ROWS := 10;
        //      P_I_PAGE_NO := 1;
        //      P_I_SORT := 'PH.PM_YEAR';
        //      P_I_ASC_DESC := 'ASC';
        //      P_I_EMP_ID := 70;
        //      P_I_PM_YEAR := 2017;
      
         int rowTotal=  this.pmsPMFormDao.getPMList( pmCriteria , outList  );
      
        JSONArray data = new JSONArray();
        JSONObject item;
        int startRow=((pmCriteria.getPageNo()-1) *  pmCriteria.getIDisplayLength())+1;
        
        for (int idx = 0; idx < outList.size(); idx++) {
            PmListMod mod = outList.get(idx);
            item = new JSONObject();
            item.put("rowNo", startRow + idx);
          item.put(("pkhdrseqno"), mod.getPkhdrseqno());
            item.put(("pmYear"), mod.getPmyear());
            item.put("pmPeriod", mod.getPmperiod());
            item.put("fkempseqno", mod.getFkempseqno());
         
            item.put("managerlv1", mod.getManagerlv1());
            item.put("managerlv2", mod.getManagerlv2());
            item.put("fkcomseqno", mod.getFkcomseqno());
            item.put("comname", mod.getComname());
            item.put("fkdivseqno", mod.getFkdivseqno());
          item.put("divname", mod.getDivname());
          item.put("fkdepseqno", mod.getFkdepseqno());
          item.put("depname", mod.getDepname());
          item.put("fksecseqno", mod.getFksecseqno());
          item.put("secname", mod.getSecname());
            item.put("empname", mod.getEmpname());
            item.put("designation", mod.getDesignation());
          item.put("pmstatus", mod.getPmstatus());
//            if(mod.getPmstatus()!=null){
//                if(mod.getPmstatus().equals("N")){
//                  item.put("pmstatus", "New");
//                }else if(mod.getPmstatus().equals("I")){
//                  item.put("pmstatus", "In Progress");
//                }else if(mod.getPmstatus().equals("W")){
//                  item.put("pmstatus", "Waitlisted 1");
//                }else if(mod.getPmstatus().equals("V")){
//                  item.put("pmstatus", "Waitlisted 2");                 
//                                   
//                }else if(mod.getPmstatus().equals("C")){
//                  item.put("pmstatus", "Completed");                 
//                                   
//                }
//            }else{
//                  item.put("pmstatus", mod.getPmstatus());
//            }
//            
           
            item.put("jobGrade", mod.getJobgrade());
            item.put("jobgradename", mod.getJobgradename());
           item.put("jobbrand", mod.getJobbrand());
           item.put("jobbrandname", mod.getJobbrandname());
            item.put("approveLv1Name", mod.getApprovelv1name());
          item.put("lv1approveby", mod.getLv1approveby());
          
            item.put("approveLv2Name", mod.getApprovelv2name());
          item.put("lv2approveby", mod.getLv2approveby());
          item.put("mystaffcount", mod.getMystaffcount());
          
            data.add(item);
        }
        item = new JSONObject();
        item.put("rowTotal",rowTotal);
        item.put("rowStart",startRow);
        item.put("dataTable",data);
        
        return item;
      }

    public List<PmsPMOverallMod> getPMOverall(String pmKey, Long empSeqNo, Integer pmYear) {
        //pmKey = dcspms01
        List<PmsPMOverallMod> list =
            this.pmsPMForm2Dao.getPMOverall(super.getPrsnLogIdOfUser(), pmKey, empSeqNo, pmYear);
        return list;
    }

    public JSONArray formatPMOveralltoJSON(final List<PmsPMOverallMod> list) {
        JSONArray data = new JSONArray();
        JSONObject item;

        for (int idx = 0; idx < list.size(); idx++) {
            PmsPMOverallMod mod = list.get(idx);
            item = new JSONObject();
            item.put("comRatingPercent", mod.getComRatingPercent());
            item.put("empSeqNo", mod.getEmpSeqNo());
            item.put("individualRatingPercent", mod.getIndividualRatingPercent());
            item.put("overallRatingPercent", mod.getOverallRatingPercent());
            item.put("pmPeriod", mod.getPmPeriod());
            item.put("pmYear", mod.getPmYear());
            item.put("presidentRatingPercent", mod.getPresidentRatingPercent());

            data.add(item);
        }

        return data;
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


    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setEmpID(Long empID) {
        this.empID = empID;
    }

    public Long getEmpID() {
        return empID;
    }

    public void setMngrID(String mngrID) {
        this.mngrID = mngrID;
    }

    public String getMngrID() {
        return mngrID;
    }


    public void setPkEmpSeqNo(Long pkEmpSeqNo) {
        this.pkEmpSeqNo = pkEmpSeqNo;
    }

    public Long getPkEmpSeqNo() {
        return pkEmpSeqNo;
    }

    public PmCriteria getPmCriteria() {
        return pmCriteria;
    }

    public void setPmCriteria(PmCriteria pmCriteria) {
        this.pmCriteria = pmCriteria;
    }
}
