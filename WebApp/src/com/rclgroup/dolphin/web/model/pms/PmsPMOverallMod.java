package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmsPMOverallMod extends RrcStandardMod {
   private Long empSeqNo;
   private Integer pmYear;
   private Integer pmPeriod;
   private Double comRatingPercent;
   private Double presidentRatingPercent;
   private Double individualRatingPercent;
   private Double overallRatingPercent;

    public void setEmpSeqNo(Long empSeqNo) {
        this.empSeqNo = empSeqNo;
    }

    public Long getEmpSeqNo() {
        return empSeqNo;
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

    public void setComRatingPercent(Double comRatingPercent) {
        this.comRatingPercent = comRatingPercent;
    }

    public Double getComRatingPercent() {
        return comRatingPercent;
    }

    public void setPresidentRatingPercent(Double presidentRatingPercent) {
        this.presidentRatingPercent = presidentRatingPercent;
    }

    public Double getPresidentRatingPercent() {
        return presidentRatingPercent;
    }

    public void setIndividualRatingPercent(Double individualRatingPercent) {
        this.individualRatingPercent = individualRatingPercent;
    }

    public Double getIndividualRatingPercent() {
        return individualRatingPercent;
    }

    public void setOverallRatingPercent(Double overallRatingPercent) {
        this.overallRatingPercent = overallRatingPercent;
    }

    public Double getOverallRatingPercent() {
        return overallRatingPercent;
    }

    public PmsPMOverallMod() {
        super();
    }

 
}
