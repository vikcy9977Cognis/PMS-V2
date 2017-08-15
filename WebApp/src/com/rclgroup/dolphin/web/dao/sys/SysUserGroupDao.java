/*-----------------------------------------------------------------------------------------------------------  
SysUserGroupDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 30/07/2008
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/ 

package com.rclgroup.dolphin.web.dao.sys;

import java.util.List;

import org.springframework.dao.DataAccessException;


public interface SysUserGroupDao {

    /**
     * check valid of user group code 
     * @param userGroup
     * @return valid of user group code 
     * @throws DataAccessException
     */
    public boolean isValid(String userGroup) throws DataAccessException;

    /**
     * check valid of user group code with status
     * @param userGroup
     * @param status
     * @return valid of user group code with status
     * @throws DataAccessException
     */
    public boolean isValid(String userGroup, String status) throws DataAccessException;

    /**
     * check valid of all user group code
     * @param userGroup
     * @return valid of all user group code
     * @throws DataAccessException
     */
    public boolean isAllValid(String userGroup) throws DataAccessException;

    /**
     * list user group records for help screen
     * @param find
     * @param search
     * @param wild
     * @return list of user group
     * @throws DataAccessException
     */
    public List listForHelpScreen(String find, String search, String wild) throws DataAccessException;

    /**
     * list user group records for help screen with status
     * @param find
     * @param search
     * @param wild
     * @param status
     * @return list of user group with status
     * @throws DataAccessException
     */
    public List listForHelpScreen(String find, String search, String wild, String status) throws DataAccessException;  
    
 }
