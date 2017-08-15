package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

import java.sql.Timestamp;

public class PmsPMDetailMod extends RrcStandardMod {
    private Long dtl_seqno;
    private Long hdr_seqno;
    private String pm_dtl_category;
    private Long ind_seqno;
    private String ind_name;
    private Long bsc_seqno;
    private String bsc_name;
    private String ind_description;
    private String remark;
    private String ind_slab_0;
    private String ind_slab_1;
    private String ind_slab_2;
    private String ind_slab_3;
    private String ind_slab_4;
    private String ind_slab_5;
    private String ind_result;
    private Double ind_self_rating;
    private Double ind_rating;
    private Double ind_weight_percentage;
    private Double prev_ind_self_rating;
    private Double prev_ind_rating;
    private Double prev_weight_percentage;
    private String record_status;
    private Timestamp record_add_date;
    private String record_add_user;
    private Timestamp record_change_date;
    private String record_change_user;

    public PmsPMDetailMod() {
        super();
    }

    public void setInd_slab_0(String ind_slab_0) {
        this.ind_slab_0 = ind_slab_0;
    }

    public String getInd_slab_0() {
        return ind_slab_0;
    }

    public void setBsc_name(String bsc_name) {
        this.bsc_name = bsc_name;
    }

    public String getBsc_name() {
        return bsc_name;
    }
    public void setInd_name(String ind_name) {
        this.ind_name = ind_name;
    }

    public String getInd_name() {
        return ind_name;
    }

    public void setDtl_seqno(Long dtl_seqno) {
        this.dtl_seqno = dtl_seqno;
    }

    public Long getDtl_seqno() {
        return dtl_seqno;
    }

    public void setHdr_seqno(Long hdr_seqno) {
        this.hdr_seqno = hdr_seqno;
    }

    public Long getHdr_seqno() {
        return hdr_seqno;
    }

    public void setPm_dtl_category(String pm_dtl_category) {
        this.pm_dtl_category = pm_dtl_category;
    }

    public String getPm_dtl_category() {
        return pm_dtl_category;
    }

    public void setInd_seqno(Long ind_seqno) {
        this.ind_seqno = ind_seqno;
    }

    public Long getInd_seqno() {
        return ind_seqno;
    }

    public void setBsc_seqno(Long bsc_seqno) {
        this.bsc_seqno = bsc_seqno;
    }

    public Long getBsc_seqno() {
        return bsc_seqno;
    }

    public void setInd_description(String ind_description) {
        this.ind_description = ind_description;
    }

    public String getInd_description() {
        return ind_description;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setInd_slab_1(String ind_slab_1) {
        this.ind_slab_1 = ind_slab_1;
    }

    public String getInd_slab_1() {
        return ind_slab_1;
    }

    public void setInd_slab_2(String ind_slab_2) {
        this.ind_slab_2 = ind_slab_2;
    }

    public String getInd_slab_2() {
        return ind_slab_2;
    }

    public void setInd_slab_3(String ind_slab_3) {
        this.ind_slab_3 = ind_slab_3;
    }

    public String getInd_slab_3() {
        return ind_slab_3;
    }

    public void setInd_slab_4(String ind_slab_4) {
        this.ind_slab_4 = ind_slab_4;
    }

    public String getInd_slab_4() {
        return ind_slab_4;
    }

    public void setInd_slab_5(String ind_slab_5) {
        this.ind_slab_5 = ind_slab_5;
    }

    public String getInd_slab_5() {
        return ind_slab_5;
    }

    public void setInd_result(String ind_result) {
        this.ind_result = ind_result;
    }

    public String getInd_result() {
        return ind_result;
    }

    public void setInd_self_rating(Double ind_self_rating) {
        this.ind_self_rating = ind_self_rating;
    }

    public Double getInd_self_rating() {
        return ind_self_rating;
    }

    public void setInd_rating(Double ind_rating) {
        this.ind_rating = ind_rating;
    }

    public Double getInd_rating() {
        return ind_rating;
    }

    public void setInd_weight_percentage(Double ind_weight_percentage) {
        this.ind_weight_percentage = ind_weight_percentage;
    }

    public Double getInd_weight_percentage() {
        return ind_weight_percentage;
    }

    public void setPrev_ind_self_rating(Double prev_ind_self_rating) {
        this.prev_ind_self_rating = prev_ind_self_rating;
    }

    public Double getPrev_ind_self_rating() {
        return prev_ind_self_rating;
    }

    public void setPrev_ind_rating(Double prev_ind_rating) {
        this.prev_ind_rating = prev_ind_rating;
    }

    public Double getPrev_ind_rating() {
        return prev_ind_rating;
    }

    public void setPrev_weight_percentage(Double prev_weight_percentage) {
        this.prev_weight_percentage = prev_weight_percentage;
    }

    public Double getPrev_weight_percentage() {
        return prev_weight_percentage;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_add_date(Timestamp record_add_date) {
        this.record_add_date = record_add_date;
    }

    public Timestamp getRecord_add_date() {
        return record_add_date;
    }

    public void setRecord_add_user(String record_add_user) {
        this.record_add_user = record_add_user;
    }

    public String getRecord_add_user() {
        return record_add_user;
    }

    public void setRecord_change_date(Timestamp record_change_date) {
        this.record_change_date = record_change_date;
    }

    public Timestamp getRecord_change_date() {
        return record_change_date;
    }

    public void setRecord_change_user(String record_change_user) {
        this.record_change_user = record_change_user;
    }

    public String getRecord_change_user() {
        return record_change_user;
    }
}
