/*-----------------------------------------------------------------------------------------------------------  
RcmAttributeSessionDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 23/06/10
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/  

package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardMod;
import com.rclgroup.dolphin.web.common.RriStandardDao;

import org.springframework.dao.DataAccessException;


public interface RcmAttributeSessionDao extends RriStandardDao {

    /**
     * @param mod
     * @return whether insertion is successful
     * @throws DataAccessException
     */ 
    public boolean insertAttributeSession(RrcStandardMod mod) throws DataAccessException;
    
    /**
     * @param mod
     * @return whether save is successful
     * @throws DataAccessException
     */ 
    public boolean saveAttributeSession(RrcStandardMod mod) throws DataAccessException;
    
    /**
     * @param mod
     * @return whether delete is successful
     * @throws DataAccessException
     */ 
    public boolean deleteAttributeSession(RrcStandardMod mod) throws DataAccessException;
    
    /**
     * @param mod
     * @return whether delete is successful
     * @throws DataAccessException
     */ 
    public boolean clearAttributeSession(RrcStandardMod mod) throws DataAccessException;
    
}


