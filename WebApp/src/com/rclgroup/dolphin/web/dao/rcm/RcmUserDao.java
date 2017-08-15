/*-----------------------------------------------------------------------------------------------------------  
RcmUserDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 03/04/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.common.RriStandardDao;
import com.rclgroup.dolphin.web.model.rcm.RcmAuthenticationMod;
import com.rclgroup.dolphin.web.model.rcm.RcmUserMod;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface RcmUserDao extends RriStandardDao {
    public boolean isValid(String prsnLogId) throws DataAccessException;
    
    public RcmUserMod findByKeyPrsnLogId(String prsnLogId) throws DataAccessException;
    
    public List getMenuAuthentication(String userId) throws DataAccessException; 
}
