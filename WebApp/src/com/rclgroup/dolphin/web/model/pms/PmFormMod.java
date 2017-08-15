package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmFormMod  extends  RrcStandardMod {
    public PmFormMod() {
        super();
    }
    private String pkHdrSeqno ;
  private String pmYear ;
  private String pmPeriod ;
  private String fkEmpSeqno ;
  private String managerLv1 ;
  private String managerLv2 ;
  private String empName ;
  private String fkComSeqno ;
  private String comName ;
  private String fkDivSeqno ;
  private String divName ;
  private String fkDepSeqno ;
  private String depName ;
  private String fkSecSeqno ;
  private String secName ;
  private String jobGrade ;
  private String jobGradeName ;
  private String jobBrand ;
  private String jobBrandName ;
  private String designation ;
  private String pmStatus ;
  private String lv1ApproveBy ;
  private String approveLv1Name ;
  private String lv2ApproveBy ;
  private String approveLv2Name ;
  private String myStaffCount ;

    public PmFormMod(String pkHdrSeqno, String pmYear, String pmPeriod,
                     String fkEmpSeqno, String managerLv1, String managerLv2,
                     String empName, String fkComSeqno, String comName,
                     String fkDivSeqno, String divName, String fkDepSeqno,
                     String depName, String fkSecSeqno, String secName,
                     String jobGrade, String jobGradeName, String jobBrand,
                     String jobBrandName, String designation, String pmStatus,
                     String lv1ApproveBy, String approveLv1Name,
                     String lv2ApproveBy, String approveLv2Name,
                     String myStaffCount) {
        super();
        this.pkHdrSeqno = pkHdrSeqno;
        this.pmYear = pmYear;
        this.pmPeriod = pmPeriod;
        this.fkEmpSeqno = fkEmpSeqno;
        this.managerLv1 = managerLv1;
        this.managerLv2 = managerLv2;
        this.empName = empName;
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
        this.designation = designation;
        this.pmStatus = pmStatus;
        this.lv1ApproveBy = lv1ApproveBy;
        this.approveLv1Name = approveLv1Name;
        this.lv2ApproveBy = lv2ApproveBy;
        this.approveLv2Name = approveLv2Name;
        this.myStaffCount = myStaffCount;
    }

    public void setPkHdrSeqno(String pkHdrSeqno) {
        this.pkHdrSeqno = pkHdrSeqno;
    }

    public String getPkHdrSeqno() {
        return pkHdrSeqno;
    }

    public void setPmYear(String pmYear) {
        this.pmYear = pmYear;
    }

    public String getPmYear() {
        return pmYear;
    }

    public void setPmPeriod(String pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public String getPmPeriod() {
        return pmPeriod;
    }

    public void setFkEmpSeqno(String fkEmpSeqno) {
        this.fkEmpSeqno = fkEmpSeqno;
    }

    public String getFkEmpSeqno() {
        return fkEmpSeqno;
    }

    public void setManagerLv1(String managerLv1) {
        this.managerLv1 = managerLv1;
    }

    public String getManagerLv1() {
        return managerLv1;
    }

    public void setManagerLv2(String managerLv2) {
        this.managerLv2 = managerLv2;
    }

    public String getManagerLv2() {
        return managerLv2;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
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

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setPmStatus(String pmStatus) {
        this.pmStatus = pmStatus;
    }

    public String getPmStatus() {
        return pmStatus;
    }

    public void setLv1ApproveBy(String lv1ApproveBy) {
        this.lv1ApproveBy = lv1ApproveBy;
    }

    public String getLv1ApproveBy() {
        return lv1ApproveBy;
    }

    public void setApproveLv1Name(String approveLv1Name) {
        this.approveLv1Name = approveLv1Name;
    }

    public String getApproveLv1Name() {
        return approveLv1Name;
    }

    public void setLv2ApproveBy(String lv2ApproveBy) {
        this.lv2ApproveBy = lv2ApproveBy;
    }

    public String getLv2ApproveBy() {
        return lv2ApproveBy;
    }

    public void setApproveLv2Name(String approveLv2Name) {
        this.approveLv2Name = approveLv2Name;
    }

    public String getApproveLv2Name() {
        return approveLv2Name;
    }

    public void setMyStaffCount(String myStaffCount) {
        this.myStaffCount = myStaffCount;
    }

    public String getMyStaffCount() {
        return myStaffCount;
    }
}
