/*-----------------------------------------------------------------------------------------------------------
PmsReportUim.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.RrcStandardUim;
import com.rclgroup.dolphin.web.dao.pms.PmsCommon2Dao;
import com.rclgroup.dolphin.web.dao.pms.PmsCommonDao;
import com.rclgroup.dolphin.web.dao.pms.PmsReportDao;
import com.rclgroup.dolphin.web.model.pms.PmsPMStatusReportMod;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PmsReportUim extends RrcStandardUim {
    private PmsCommonDao pmsCommonDao;
    private PmsReportDao pmsReportDao;
    private PmsCommon2Dao pmsCommon2Dao;

    public void setPmsCommon2Dao(PmsCommon2Dao pmsCommon2Dao) {
        this.pmsCommon2Dao = pmsCommon2Dao;
    }

    public PmsCommon2Dao getPmsCommon2Dao() {
        return pmsCommon2Dao;
    }


    public PmsReportUim() {
        super();
    }

    public PmsCommonDao getPmsCommonDao() {
        return pmsCommonDao;
    }

    public void setPmsReportDao(PmsReportDao pmsReportDao) {
        this.pmsReportDao = pmsReportDao;
    }

    public PmsReportDao getPmsReportDao() {
        return pmsReportDao;
    }

    public void setPmsCommonDao(PmsCommonDao dao) {
        this.pmsCommonDao = dao;
    }

    /**
     * Format PM Status Report to JSON fomat
     * @param outList
     * @return
     */
    public JSONArray formatStatusReportAsJSON(List<PmsPMStatusReportMod> outList) {
        JSONArray data = new JSONArray();
        JSONObject item;
        
        for (PmsPMStatusReportMod mod : outList) {
            item = new JSONObject();
            item.put("com_seqno", mod.getCom_seqno());
            item.put("com_name", mod.getCom_name());
            item.put("dep_seqno", mod.getDep_seqno());
            item.put("dep_name", mod.getDep_name());
            item.put("pm_new", mod.getPm_new());
            item.put("pm_inprogress", mod.getPm_inprogress());
            item.put("pm_waitlisted1", mod.getPm_waitlisted1());
            item.put("pm_waitlisted2", mod.getPm_waitlisted2());
            item.put("pm_completed", mod.getPm_completed());
            item.put("pm_total", mod.getPm_total());
 
            data.add(item);
        }

        return data;
    }
}
