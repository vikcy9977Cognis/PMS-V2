package com.rclgroup.dolphin.web.model.pms;

public class PmsEmpPerfRes {
    
    private int year;
    private int period;
    private int empId;
    private String empName;
    private String jobBand;
    private String competencyPercent1H;
    private String competencyPercent2H;
    private String indKPIPercent1H;
    private String indKPIPercent2H;
    
    public PmsEmpPerfRes() {
        super();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setJobBand(String jobBand) {
        this.jobBand = jobBand;
    }

    public String getJobBand() {
        return jobBand;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setCompetencyPercent1H(String competencyPercent1H) {
        this.competencyPercent1H = competencyPercent1H;
    }

    public String getCompetencyPercent1H() {
        return competencyPercent1H;
    }

    public void setCompetencyPercent2H(String competencyPercent2H) {
        this.competencyPercent2H = competencyPercent2H;
    }

    public String getCompetencyPercent2H() {
        return competencyPercent2H;
    }

    public void setIndKPIPercent1H(String indKPIPercent1H) {
        this.indKPIPercent1H = indKPIPercent1H;
    }

    public String getIndKPIPercent1H() {
        return indKPIPercent1H;
    }

    public void setIndKPIPercent2H(String indKPIPercent2H) {
        this.indKPIPercent2H = indKPIPercent2H;
    }

    public String getIndKPIPercent2H() {
        return indKPIPercent2H;
    }
}
