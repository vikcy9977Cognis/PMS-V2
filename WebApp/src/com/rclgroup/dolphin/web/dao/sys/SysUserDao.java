 /*-----------------------------------------------------------------------------------------------------------  
 SysUserDao.java
 ------------------------------------------------------------------------------------------------------------- 
 Copyright RCL Public Co., Ltd. 2007 
 -------------------------------------------------------------------------------------------------------------
 Author Sopon Dee-udomvongsa 28/03/2008
 - Change Log ------------------------------------------------------------------------------------------------  
 ## DD/MM/YY -User-     -TaskRef-      -Short Description  
 -----------------------------------------------------------------------------------------------------------*/ 

 package com.rclgroup.dolphin.web.dao.sys;

 import com.rclgroup.dolphin.web.model.sys.SysUserMod;

 import java.util.List;

 import org.springframework.dao.DataAccessException;


 public interface SysUserDao {

     /**
      * check valid of user id
      * @param userID
      * @return valid of user id
      * @throws DataAccessException
      */
     public boolean isValid(String userID) throws DataAccessException;
     
     /**
      * check active status of user id
      * @param userID
      * @return whether user id is active
      * @throws DataAccessException
      */
     public boolean isActive(String userID) throws DataAccessException;
     
     /**
      * list user records for help screen
      * @param find
      * @param search
      * @param wild
      * @return list of user
      * @throws DataAccessException
      */
     public List listForHelpScreen(String find, String search, String wild) throws DataAccessException;

     /**
      * find user by using key as user id
      * @param code
      * @return user id, name, fsc, organization type, department, email and status
      * @throws DataAccessException
      */
     public SysUserMod findByKeyCode(String code) throws DataAccessException;
         

 }
