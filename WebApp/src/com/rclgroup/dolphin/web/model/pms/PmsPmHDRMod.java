/*-----------------------------------------------------------------------------------------------------------  
PmsPMForm2JdbcDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2015 
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/15
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/  

package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

import java.sql.Timestamp;

import java.util.Date;

public class PmsPmHDRMod extends RrcStandardMod {
    
    private Long hdr_seqno ;
    private Integer pm_year ;
    private Integer pm_period ;
    private Long emp_seqno ;
    private String emp_id;
    private String emp_name;

    
    private Timestamp joinDate;
    private Integer joinYear;
    private Integer joinPeriod;
    
    private Long com_seqno ;
    private String companyName;
    
    private Long dep_seqno ;
    private String departmentName;
    
    private Long sec_seqno ;
    private String sectionName;
    
    private String job_grade ;
    private String job_grade_name;
    
    private String job_brand ;
    private String job_band_name;
    
    private Long manager_lv1 ;
    private String approve_lv1_name;
    
    private Long manager_lv2 ;
    private String approve_lv2_name;
    
    private String designation ;
    private String pm_status ;
    private Double com_rating ;
    private Double com_rating_percent ;
    private Double president_rating ;
    private Double president_rating_percent ;
    private Double individual_rating ;
    private Double individual_rating_percent ;
    private String part3_comment1 ;
    private String part3_comment2 ;
    private String part3_comment3 ;
    private String part3_comment4 ;
    private String submit_by ;
    private Timestamp submit_date ;
    private String part4_comment1 ;
    private Double overall_rating ;
    private Double overall_rating_percent ;
    private String lv1_approve_by ;
    private Timestamp lv1_approve_date ;
    private String part5_comment1 ;
    private String lv2_approve_by ;
    private Timestamp lv2_approve_date ;
    private Long revision_no ;
    private String record_status ;
    
    private Long div_seqno ;
    private String divisionName;
    
    public PmsPmHDRMod() {
        super();
    }
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setHdr_seqno(Long hdr_seqno) {
        this.hdr_seqno = hdr_seqno;
    }

    public Long getHdr_seqno() {
        return hdr_seqno;
    }

    public void setPm_year(Integer pm_year) {
        this.pm_year = pm_year;
    }

    public Integer getPm_year() {
        return pm_year;
    }

    public void setPm_period(Integer pm_period) {
        this.pm_period = pm_period;
    }

    public Integer getPm_period() {
        return pm_period;
    }

    public void setEmp_seqno(Long emp_seqno) {
        this.emp_seqno = emp_seqno;
    }

    public Long getEmp_seqno() {
        return emp_seqno;
    }

    public void setCom_seqno(Long com_seqno) {
        this.com_seqno = com_seqno;
    }

    public Long getCom_seqno() {
        return com_seqno;
    }

    public void setDep_seqno(Long dep_seqno) {
        this.dep_seqno = dep_seqno;
    }

    public Long getDep_seqno() {
        return dep_seqno;
    }

    public void setSec_seqno(Long sec_seqno) {
        this.sec_seqno = sec_seqno;
    }

    public Long getSec_seqno() {
        return sec_seqno;
    }

    public void setJob_grade(String job_grade) {
        this.job_grade = job_grade;
    }

    public String getJob_grade() {
        return job_grade;
    }

    public void setJob_brand(String job_brand) {
        this.job_brand = job_brand;
    }

    public String getJob_brand() {
        return job_brand;
    }

    public void setManager_lv1(Long manager_lv1) {
        this.manager_lv1 = manager_lv1;
    }

    public Long getManager_lv1() {
        return manager_lv1;
    }

    public void setManager_lv2(Long manager_lv2) {
        this.manager_lv2 = manager_lv2;
    }

    public Long getManager_lv2() {
        return manager_lv2;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setPm_status(String pm_status) {
        this.pm_status = pm_status;
    }

    public String getPm_status() {
        return pm_status;
    }

    public void setCom_rating(Double com_rating) {
        this.com_rating = com_rating;
    }

    public Double getCom_rating() {
        return com_rating;
    }

    public void setCom_rating_percent(Double com_rating_percent) {
        this.com_rating_percent = com_rating_percent;
    }

    public Double getCom_rating_percent() {
        return com_rating_percent;
    }

    public void setPresident_rating(Double president_rating) {
        this.president_rating = president_rating;
    }

    public Double getPresident_rating() {
        return president_rating;
    }

    public void setPresident_rating_percent(Double president_rating_percent) {
        this.president_rating_percent = president_rating_percent;
    }

    public Double getPresident_rating_percent() {
        return president_rating_percent;
    }

    public void setIndividual_rating(Double individual_rating) {
        this.individual_rating = individual_rating;
    }

    public Double getIndividual_rating() {
        return individual_rating;
    }

    public void setIndividual_rating_percent(Double individual_rating_percent) {
        this.individual_rating_percent = individual_rating_percent;
    }

    public Double getIndividual_rating_percent() {
        return individual_rating_percent;
    }

    public void setPart3_comment1(String part3_comment1) {
        this.part3_comment1 = part3_comment1;
    }

    public String getPart3_comment1() {
        return part3_comment1;
    }

    public void setPart3_comment2(String part3_comment2) {
        this.part3_comment2 = part3_comment2;
    }

    public String getPart3_comment2() {
        return part3_comment2;
    }

    public void setPart3_comment3(String part3_comment3) {
        this.part3_comment3 = part3_comment3;
    }

    public String getPart3_comment3() {
        return part3_comment3;
    }

    public void setPart3_comment4(String part3_comment4) {
        this.part3_comment4 = part3_comment4;
    }

    public String getPart3_comment4() {
        return part3_comment4;
    }

    public void setSubmit_by(String submit_by) {
        this.submit_by = submit_by;
    }

    public String getSubmit_by() {
        return submit_by;
    }

    public void setSubmit_date(Timestamp submit_date) {
        this.submit_date = submit_date;
    }

    public Date getSubmit_date() {
        return submit_date;
    }

    public void setPart4_comment1(String part4_comment1) {
        this.part4_comment1 = part4_comment1;
    }

    public String getPart4_comment1() {
        return part4_comment1;
    }

    public void setOverall_rating(Double overall_rating) {
        this.overall_rating = overall_rating;
    }

    public Double getOverall_rating() {
        return overall_rating;
    }

    public void setOverall_rating_percent(Double overall_rating_percent) {
        this.overall_rating_percent = overall_rating_percent;
    }

    public Double getOverall_rating_percent() {
        return overall_rating_percent;
    }

    public void setLv1_approve_by(String lv1_approve_by) {
        this.lv1_approve_by = lv1_approve_by;
    }

    public String getLv1_approve_by() {
        return lv1_approve_by;
    }

    public void setLv1_approve_date(Timestamp lv1_approve_date) {
        this.lv1_approve_date = lv1_approve_date;
    }

    public Timestamp getLv1_approve_date() {
        return lv1_approve_date;
    }

    public void setPart5_comment1(String part5_comment1) {
        this.part5_comment1 = part5_comment1;
    }

    public String getPart5_comment1() {
        return part5_comment1;
    }

    public void setLv2_approve_by(String lv2_approve_by) {
        this.lv2_approve_by = lv2_approve_by;
    }

    public String getLv2_approve_by() {
        return lv2_approve_by;
    }

    public void setLv2_approve_date(Timestamp lv2_approve_date) {
        this.lv2_approve_date = lv2_approve_date;
    }

    public Date getLv2_approve_date() {
        return lv2_approve_date;
    }

    public void setRevision_no(Long revision_no) {
        this.revision_no = revision_no;
    }

    public Long getRevision_no() {
        return revision_no;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setDiv_seqno(Long div_seqno) {
        this.div_seqno = div_seqno;
    }

    public Long getDiv_seqno() {
        return div_seqno;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinYear(Integer joinYear) {
        this.joinYear = joinYear;
    }

    public Integer getJoinYear() {
        return joinYear;
    }

    public void setJoinPeriod(Integer joinPeriod) {
        this.joinPeriod = joinPeriod;
    }

    public Integer getJoinPeriod() {
        return joinPeriod;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setJob_grade_name(String job_grade_name) {
        this.job_grade_name = job_grade_name;
    }

    public String getJob_grade_name() {
        return job_grade_name;
    }

    public void setJob_band_name(String job_band_name) {
        this.job_band_name = job_band_name;
    }

    public String getJob_band_name() {
        return job_band_name;
    }

    public void setApprove_lv1_name(String approve_lv1_name) {
        this.approve_lv1_name = approve_lv1_name;
    }

    public String getApprove_lv1_name() {
        return approve_lv1_name;
    }

    public void setApprove_lv2_name(String approve_lv2_name) {
        this.approve_lv2_name = approve_lv2_name;
    }

    public String getApprove_lv2_name() {
        return approve_lv2_name;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
