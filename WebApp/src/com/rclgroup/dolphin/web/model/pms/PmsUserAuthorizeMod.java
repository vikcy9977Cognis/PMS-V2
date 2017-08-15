package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class PmsUserAuthorizeMod extends RrcStandardMod{
    private String viewFlag;
    private String editFlag;
    private String submitFlag;
    private String approveLv1Flag;
    private String approveLv2Flag;
    
    public void setViewFlag(String viewFlag) {
        this.viewFlag = viewFlag;
    }

    public String getViewFlag() {
        return viewFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setSubmitFlag(String submitFlag) {
        this.submitFlag = submitFlag;
    }

    public String getSubmitFlag() {
        return submitFlag;
    }

    public void setApproveLv1Flag(String approveLv1Flag) {
        this.approveLv1Flag = approveLv1Flag;
    }

    public String getApproveLv1Flag() {
        return approveLv1Flag;
    }

    public void setApproveLv2Flag(String approveLv2Flag) {
        this.approveLv2Flag = approveLv2Flag;
    }

    public String getApproveLv2Flag() {
        return approveLv2Flag;
    }
    
    
    
    public PmsUserAuthorizeMod() {
        super();
    }
}
