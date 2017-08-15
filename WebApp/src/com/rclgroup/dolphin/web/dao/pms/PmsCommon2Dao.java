/*-----------------------------------------------------------------------------------------------------------
PmsCommon2Dao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.model.pms.PmsActivityMod;
import com.rclgroup.dolphin.web.model.pms.PmsJobWeightageMod;

import com.rclgroup.dolphin.web.model.pms.PmsIndicatorMod;

import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface PmsCommon2Dao {

    /**
     * List job weightage by year/period
     * @param userID
     * @param pmYear
     * @param pmPeriod
     * @return
     * @throws DataAccessException
     */
    public List<PmsJobWeightageMod> getJobWeightageList(final String userID, final Integer pmYear,
                                                        final Integer pmPeriod) throws DataAccessException;

    /**
     * List indicator in particular category
     * @param userID
     * @param pmYear
     * @param pmPeriod
     * @param category
     * @return
     * @throws DataAccessException
     */
    public List<PmsIndicatorMod> getIndicatorList(final String userID, final Integer pmYear, final Integer pmPeriod,
                                                  final String category) throws DataAccessException;

    /**
     * Get Weightage for particular Job Brand.
     * @param userID
     * @param jobBrand
     * @param pmYear
     * @param pmPeriod
     * @return
     * @throws DataAccessException
     */
    public PmsJobWeightageMod getJobWeightage(final String userID, final String jobBrand, final Integer pmYear,
                                              final Integer pmPeriod) throws DataAccessException;


    /**
     * Get active PM year/period by system date.
     * @param userID
     * @return
     * @throws DataAccessException
     */
    public Map<String, Object> getActivePMYearPeriod(final String userID) throws DataAccessException;
/**
     * Get Indicator Detail.
     * @param user_id
     * @param ind_seqno
     * @param job_brand
     * @return
     * @throws DataAccessException
     */
    public Map<String, Object> getIndicatorDetail(final String user_id, final Long ind_seqno,
                                                  final String job_brand) throws DataAccessException;
    
    /**
     * Get encrypted PM Key in string format.
     * @param userID
     * @param pmKey
     * @return
     * @throws DataAccessException
     */
    public String getEncryptPMKey(final String userID, final String pmKey) throws DataAccessException;
}
