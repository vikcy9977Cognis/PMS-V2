package com.rclgroup.dolphin.web.model.rcm;

public class RcmReportAccessMod {
    public RcmReportAccessMod() {
    }
    
    private String userLoginId;
    private String sessionId;
    private String reportCode;
    private String level;
    private String region;
    private String agent;
    private String fsc;
    //private int reportGenerateNo;


    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAgent() {
        return agent;
    }

    public void setFsc(String fsc) {
        this.fsc = fsc;
    }

    public String getFsc() {
        return fsc;
    }

    /*public void setReportGenerateNo(int reportGenerateNo) {
        this.reportGenerateNo = reportGenerateNo;
    }

    public int getReportGenerateNo() {
        return reportGenerateNo;
    }
    */
}
