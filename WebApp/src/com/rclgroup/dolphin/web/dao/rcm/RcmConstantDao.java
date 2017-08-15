package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.model.rcm.RcmReportServerConfigMod;

import org.springframework.dao.DataAccessException;


public interface RcmConstantDao {

    public RcmReportServerConfigMod getDefaultReportServerConfigMod();
        
    public String getConstantValueByKey(String constantKey) throws DataAccessException;
    
}
