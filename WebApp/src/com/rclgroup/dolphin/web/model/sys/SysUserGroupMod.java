 /*------------------------------------------------------
 SysUserGroupMod.java
 --------------------------------------------------------
 Copyright RCL Public Co., Ltd. 2007
 --------------------------------------------------------
  Author Manop Wanngam 27/03/08
 - Change Log -------------------------------------------
 ## DD/MM/YY –User- -TaskRef- -ShortDescription-
 --------------------------------------------------------
 */

 package com.rclgroup.dolphin.web.model.sys;

 import com.rclgroup.dolphin.web.common.RrcStandardMod;

 public class SysUserGroupMod extends RrcStandardMod {
     private String userGroupCode;
     private String userGroupName;
     private String userGroupStatus;

     public SysUserGroupMod() {
         userGroupCode = "";
         userGroupName = "";
         userGroupStatus = "";
     }

     public String getUserGroupCode() {
         return userGroupCode;
     }

     public void setUserGroupCode(String userGroupCode) {
         this.userGroupCode = userGroupCode;
     }

     public String getUserGroupName() {
         return userGroupName;
     }

     public void setUserGroupName(String userGroupName) {
         this.userGroupName = userGroupName;
     }

     public String getUserGroupStatus() {
         return userGroupStatus;
     }

     public void setUserGroupStatus(String userGroupStatus) {
         this.userGroupStatus = userGroupStatus;
     }
 }
