/*-----------------------------------------------------------------------------------------------------------
DndBillingExtractionJdbcDao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2016
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 28/02/16
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

import java.sql.Timestamp;

public class PmsActivityMod extends RrcStandardMod {
    
    private String action;
    private String actionBy;
    private Timestamp actionDate;
    
    public PmsActivityMod() {
        super();
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setActionBy(String actionBy) {
        this.actionBy = actionBy;
    }

    public String getActionBy() {
        return actionBy;
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

}
