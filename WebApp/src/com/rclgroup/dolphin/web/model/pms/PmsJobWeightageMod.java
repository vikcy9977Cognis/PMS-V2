
/*-----------------------------------------------------------------------------------------------------------  
JobWeightageMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2015 
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/03/17
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/  

package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmsJobWeightageMod extends RrcStandardMod{
    private Integer pmYear;
    private Integer pmPeriod;
    private String  jobBrand;
    private String description;
    private Double comWeightage;
    private Double pdWeightage;
    private Double indKPIWeightage;
    
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

    public void setJobBrand(String jobBrand) {
        this.jobBrand = jobBrand;
    }

    public String getJobBrand() {
        return jobBrand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setComWeightage(Double comWeightage) {
        this.comWeightage = comWeightage;
    }

    public Double getComWeightage() {
        return comWeightage;
    }

    public void setPdWeightage(Double pdWeightage) {
        this.pdWeightage = pdWeightage;
    }

    public Double getPdWeightage() {
        return pdWeightage;
    }

    public void setIndKPIWeightage(Double indKPIWeightage) {
        this.indKPIWeightage = indKPIWeightage;
    }

    public Double getIndKPIWeightage() {
        return indKPIWeightage;
    }
    
    public PmsJobWeightageMod() {
        super();
    }
}
