/*-----------------------------------------------------------------------------------------------------------
PmsPMStatusReportMod.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmsPMStatusReportMod extends RrcStandardMod {
    private Long com_seqno;
    private String com_name;
    private Long dep_seqno;
    private String dep_name;
    private Integer pm_new;
    private Integer pm_inprogress;
    private Integer pm_waitlisted1;
    private Integer pm_waitlisted2;
    private Integer pm_completed;
    private Integer pm_total;


    public PmsPMStatusReportMod() {
        super();
    }

    public void setCom_seqno(Long com_seqno) {
        this.com_seqno = com_seqno;
    }

    public Long getCom_seqno() {
        return com_seqno;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setDep_seqno(Long dep_seqno) {
        this.dep_seqno = dep_seqno;
    }

    public Long getDep_seqno() {
        return dep_seqno;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setPm_new(Integer pm_new) {
        this.pm_new = pm_new;
    }

    public Integer getPm_new() {
        return pm_new;
    }

    public void setPm_inprogress(Integer pm_inprogress) {
        this.pm_inprogress = pm_inprogress;
    }

    public Integer getPm_inprogress() {
        return pm_inprogress;
    }

    public void setPm_waitlisted1(Integer pm_waitlisted1) {
        this.pm_waitlisted1 = pm_waitlisted1;
    }

    public Integer getPm_waitlisted1() {
        return pm_waitlisted1;
    }

    public void setPm_waitlisted2(Integer pm_waitlisted2) {
        this.pm_waitlisted2 = pm_waitlisted2;
    }

    public Integer getPm_waitlisted2() {
        return pm_waitlisted2;
    }

    public void setPm_completed(Integer pm_completed) {
        this.pm_completed = pm_completed;
    }

    public Integer getPm_completed() {
        return pm_completed;
    }

    public void setPm_total(Integer pm_total) {
        this.pm_total = pm_total;
    }

    public Integer getPm_total() {
        return pm_total;
    }

}
