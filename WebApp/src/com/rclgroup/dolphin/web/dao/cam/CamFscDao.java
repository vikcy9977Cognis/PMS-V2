/*-----------------------------------------------------------------------------------------------------------  
CamFscDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 29/04/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description 
01 29/04/08  SPD                       Change to new framework
02 11/05/09  WAC                       Added getPortPoint method
-----------------------------------------------------------------------------------------------------------*/ 

package com.rclgroup.dolphin.web.dao.cam;

import com.rclgroup.dolphin.web.model.cam.CamFscMod;

import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface CamFscDao {

    /**
     * check valid of fsc code 
     * @param fscCode
     * @param status
     * @return valid of fsc code 
     * @throws DataAccessException
     */
    public boolean isValid(String fscCode,String status) throws DataAccessException;
    
    
    /**
     * check valid of fsc code 
     * @param fscCode
     * @param status
     * @return valid of fsc code 
     * @throws DataAccessException
     */
    public boolean isValidChargeCode(String chargeCode, String invoiceBy, String status) throws DataAccessException;
    
    /**
     * check valid of Region code 
     * @param regionCode
     * @param status
     * @return valid of region code 
     * @throws DataAccessException
     */
    public boolean isRegionValid(String regionCode,String status) throws DataAccessException;

    /**
     * check control fsc
     * @param line
     * @param region
     * @param agent
     * @param fsc
     * @return control fsc
     * @throws DataAccessException
     */
    public boolean isControlFsc(String line,String region,String agent,String fsc) throws DataAccessException;

    /**
     * list fsc records for help screen
     * @param find
     * @param search
     * @param wild
     * @return list of fsc
     * @throws DataAccessException
     */
    public List listForHelpScreen(String find,String search,String wild) throws DataAccessException;

    /**
     * list fsc records for help screen with status
     * @param find
     * @param search
     * @param wild
     * @param status
     * @return list of fsc with status
     * @throws DataAccessException
     */
    public List listForHelpScreen(String find,String search,String wild, String status) throws DataAccessException;

    /**
     * list fsc records with user level for help screen
     * @param find
     * @param search
     * @param wild
     * @param lineCode
     * @param regionCode
     * @param agentCode
     * @param status
     * @return list of fsc with user level
     * @throws DataAccessException
     */
    public List listForHelpScreenWithUserLevel(String find,String search,String wild,String lineCode,String regionCode,String agentCode,String status) throws DataAccessException;
    
    /**
     * @param fscCode fscCode
     * @return vessel model of vessel code
     * @throws DataAccessException
     */
    public CamFscMod findByKeyfscCode(String fscCode) throws DataAccessException ;
    /**
     * get PortPoint
     * @param fsc
     * @return PortPoint
     * @throws DataAccessException
     */
    public String getPortPoint(String fsc) throws DataAccessException;
    
    public Map<String,String> getUserLoginDataLevel3(String userId, String fsc) throws DataAccessException;
}


