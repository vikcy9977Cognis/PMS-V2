package com.rclgroup.dolphin.web.model.misc;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class CrmProfileMod extends  RrcStandardMod {
  private String pkEmpSeqno;
  private String empId;
  private String empName;
  private String joinDate;
  private String fkComSeqno;
  private String comName;
  private String fkDivSeqno;
  private String divName;
  private String fkDepSeqno;
  private String depName;
  private String fkSecSeqno;
  private String secName;
  private String jobGrade;
  private String jobGradeName;
  private String jobBrand;
  private String jobBrandName;
  private String managerLv1;
  private String managerLv1Name;
  private String managerLv2;
  private String managerLv2Name;
  private String designation;
  private String userId;
  private String userLevel;
  private String myStaffCount ;
  private String flagCreate;
  private Integer expYear;
  private Integer expMonth;

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public CrmProfileMod() {
        super();
    }

    public CrmProfileMod(String pkEmpSeqno, String empId, String empName,
                         String joinDate, String fkComSeqno, String comName,
                         String fkDivSeqno, String divName, String fkDepSeqno,
                         String depName, String fkSecSeqno, String secName,
                         String jobGrade, String jobGradeName, String jobBrand,
                         String jobBrandName, String managerLv1,
                         String managerLv1Name, String managerLv2,
                         String managerLv2Name, String designation,
                         String userId, String userLevel, String myStaffCount,
                         String flagCreate) {
        super();
        this.pkEmpSeqno = pkEmpSeqno;
        this.empId = empId;
        this.empName = empName;
        this.joinDate = joinDate;
        this.fkComSeqno = fkComSeqno;
        this.comName = comName;
        this.fkDivSeqno = fkDivSeqno;
        this.divName = divName;
        this.fkDepSeqno = fkDepSeqno;
        this.depName = depName;
        this.fkSecSeqno = fkSecSeqno;
        this.secName = secName;
        this.jobGrade = jobGrade;
        this.jobGradeName = jobGradeName;
        this.jobBrand = jobBrand;
        this.jobBrandName = jobBrandName;
        this.managerLv1 = managerLv1;
        this.managerLv1Name = managerLv1Name;
        this.managerLv2 = managerLv2;
        this.managerLv2Name = managerLv2Name;
        this.designation = designation;
        this.userId = userId;
        this.userLevel = userLevel;
        this.myStaffCount = myStaffCount;
        this.flagCreate = flagCreate;
    }

    public void setPkEmpSeqno(String pkEmpSeqno) {
        this.pkEmpSeqno = pkEmpSeqno;
    }

    public String getPkEmpSeqno() {
        return pkEmpSeqno;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setFkComSeqno(String fkComSeqno) {
        this.fkComSeqno = fkComSeqno;
    }

    public String getFkComSeqno() {
        return fkComSeqno;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComName() {
        return comName;
    }

    public void setFkDivSeqno(String fkDivSeqno) {
        this.fkDivSeqno = fkDivSeqno;
    }

    public String getFkDivSeqno() {
        return fkDivSeqno;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getDivName() {
        return divName;
    }

    public void setFkDepSeqno(String fkDepSeqno) {
        this.fkDepSeqno = fkDepSeqno;
    }

    public String getFkDepSeqno() {
        return fkDepSeqno;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepName() {
        return depName;
    }

    public void setFkSecSeqno(String fkSecSeqno) {
        this.fkSecSeqno = fkSecSeqno;
    }

    public String getFkSecSeqno() {
        return fkSecSeqno;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getSecName() {
        return secName;
    }

    public void setJobGrade(String jobGrade) {
        this.jobGrade = jobGrade;
    }

    public String getJobGrade() {
        return jobGrade;
    }

    public void setJobGradeName(String jobGradeName) {
        this.jobGradeName = jobGradeName;
    }

    public String getJobGradeName() {
        return jobGradeName;
    }

    public void setJobBrand(String jobBrand) {
        this.jobBrand = jobBrand;
    }

    public String getJobBrand() {
        return jobBrand;
    }

    public void setJobBrandName(String jobBrandName) {
        this.jobBrandName = jobBrandName;
    }

    public String getJobBrandName() {
        return jobBrandName;
    }

    public void setManagerLv1(String managerLv1) {
        this.managerLv1 = managerLv1;
    }

    public String getManagerLv1() {
        return managerLv1;
    }

    public void setManagerLv1Name(String managerLv1Name) {
        this.managerLv1Name = managerLv1Name;
    }

    public String getManagerLv1Name() {
        return managerLv1Name;
    }

    public void setManagerLv2(String managerLv2) {
        this.managerLv2 = managerLv2;
    }

    public String getManagerLv2() {
        return managerLv2;
    }

    public void setManagerLv2Name(String managerLv2Name) {
        this.managerLv2Name = managerLv2Name;
    }

    public String getManagerLv2Name() {
        return managerLv2Name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setMyStaffCount(String myStaffCount) {
        this.myStaffCount = myStaffCount;
    }

    public String getMyStaffCount() {
        return myStaffCount;
    }

    public void setFlagCreate(String flagCreate) {
        this.flagCreate = flagCreate;
    }

    public String getFlagCreate() {
        return flagCreate;
    }
}
