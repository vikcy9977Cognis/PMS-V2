/*-----------------------------------------------------------------------------------------------------------
PmsReportDao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.model.pms.PmsPMStatusReportMod;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface PmsReportDao {
    /**
     * Return PM Status as a report list
     * @param user_id
     * @param pm_key
     * @param date
     * @param pm_year
     * @param pm_period
     * @param com_seqno
     * @return
     * @throws DataAccessException
     */
    public List<PmsPMStatusReportMod> getPMStatusReport(final String user_id, final String pm_key, final Integer date,
                                                        final Integer pm_year, final Integer pm_period,
                                                        final Long com_seqno) throws DataAccessException;
}
