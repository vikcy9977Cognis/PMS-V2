package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmCriteria   extends  RrcStandardMod {
    public PmCriteria() {
        super();
    }
  
    // P_I_USER_ID VARCHAR2(200);
    // P_I_PM_KEY VARCHAR2(200);
    // P_I_PAGE_ROWS NUMBER;
    // P_I_PAGE_NO NUMBER;
    // P_I_SORT VARCHAR2(200);
    // P_I_ASC_DESC VARCHAR2(200);
    // P_I_EMP_ID NUMBER;
    // P_I_PM_YEAR NUMBER;
    // P_I_PM_PERIOD NUMBER;
    // P_I_PM_STATUS VARCHAR2(200);
    // P_I_EMP_NAME VARCHAR2(200);
    // P_I_COMPANY NUMBER;
    // P_I_DIVISION NUMBER;
    // P_I_DEPARTMENT NUMBER;
    // P_I_SECTION NUMBER;
    // P_I_JOB_GRADE VARCHAR2(200);
    // P_I_JOB_BRAND VARCHAR2(200);
    // P_I_DESIGNATION VARCHAR2(200);
    // P_I_MNGR_ID NUMBER;
  private int pageNo;
  private int iDisplayLength;
  private String   defaultSortBy;
  private String   defaultSortDirection;
  private String   userId;
  private String   pmKey;
  private Long   empId;
  private Integer   pmYear;
  private Integer   pmPeriod;
  private String   pmStatus;
  private String   empName;
  private Integer   company;
  private Integer   division;
  private Integer   department;
  private Integer   section;
  private String   jobGrade;
  private String   jobBrand;
  private String   designation;
  private Long   mngrId;


    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setIDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getIDisplayLength() {
        return iDisplayLength;
    }

    public void setDefaultSortBy(String defaultSortBy) {
        this.defaultSortBy = defaultSortBy;
    }

    public String getDefaultSortBy() {
        return defaultSortBy;
    }

    public void setDefaultSortDirection(String defaultSortDirection) {
        this.defaultSortDirection = defaultSortDirection;
    }

    public String getDefaultSortDirection() {
        return defaultSortDirection;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPmKey(String pmKey) {
        this.pmKey = pmKey;
    }

    public String getPmKey() {
        return pmKey;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setPmYear(Integer pmYear) {
        this.pmYear = pmYear;
    }

    public Integer getPmYear() {
        return pmYear;
    }

    public void setPmPeriod(Integer pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public Integer getPmPeriod() {
        return pmPeriod;
    }

    public void setPmStatus(String pmStatus) {
        this.pmStatus = pmStatus;
    }

    public String getPmStatus() {
        return pmStatus;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getCompany() {
        return company;
    }

    public void setDivision(Integer division) {
        this.division = division;
    }

    public Integer getDivision() {
        return division;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getSection() {
        return section;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public void setJobBrand(String jobBrand) {
        this.jobBrand = jobBrand;
    }

    public String getJobBrand() {
        return jobBrand;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setMngrId(Long mngrId) {
        this.mngrId = mngrId;
    }

    public Long getMngrId() {
        return mngrId;
    }
}
