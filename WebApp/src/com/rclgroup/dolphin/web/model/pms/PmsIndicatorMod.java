
/*-----------------------------------------------------------------------------------------------------------  
PmsIndicatorMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2015 
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/03/17
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/  

package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmsIndicatorMod extends RrcStandardMod {
    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setBscSeqNo(Long bscSeqNo) {
        this.bscSeqNo = bscSeqNo;
    }

    public Long getBscSeqNo() {
        return bscSeqNo;
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

    public void setWeightage(Double weightage) {
        this.weightage = weightage;
    }

    public Double getWeightage() {
        return weightage;
    }

    public void setSlab0(String slab0) {
        this.slab0 = slab0;
    }

    public String getSlab0() {
        return slab0;
    }

    public void setSlab1(String slab1) {
        this.slab1 = slab1;
    }

    public String getSlab1() {
        return slab1;
    }

    public void setSlab2(String slab2) {
        this.slab2 = slab2;
    }

    public String getSlab2() {
        return slab2;
    }

    public void setSlab3(String slab3) {
        this.slab3 = slab3;
    }

    public String getSlab3() {
        return slab3;
    }

    public void setSlab4(String slab4) {
        this.slab4 = slab4;
    }

    public String getSlab4() {
        return slab4;
    }

    public void setSlab5(String slab5) {
        this.slab5 = slab5;
    }

    public String getSlab5() {
        return slab5;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName;
    }

    public String getBscName() {
        return bscName;
    }
    private Long seqNo;
    private String name;
    private String description;
    private String category;
    private Long bscSeqNo;
    private String bscName;
    private Integer pmYear;
    private Integer pmPeriod;
    private Double weightage;
    private String slab0;
    private String slab1;
    private String slab2;
    private String slab3;
    private String slab4;
    private String slab5;
    private String result;
    private String rating;
    
    public PmsIndicatorMod() {
        super();
    }

}
