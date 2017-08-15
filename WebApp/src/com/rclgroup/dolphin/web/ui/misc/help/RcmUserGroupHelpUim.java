/*-----------------------------------------------------------------------------------------------------------  
RcmUserGroupHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Manop Wanngam 27/03/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

 package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RrcStandardHelpUim;
import com.rclgroup.dolphin.web.common.RrcStandardUim;
import com.rclgroup.dolphin.web.dao.sys.SysUserGroupDao;
import java.util.List;

 public class RcmUserGroupHelpUim extends RrcStandardHelpUim{
     private SysUserGroupDao sysUserGroupDao;
     
     public RcmUserGroupHelpUim() {
     }
     
     public void setSysUserGroupDao(SysUserGroupDao sysUserGroupDao) {
         this.sysUserGroupDao = sysUserGroupDao;
     }
     
     public List getListForHelpScreen(String find, String search, String wild, String type) {
         List list = null;
         if(type == null || type.equals("") || type.equalsIgnoreCase(RrcStandardUim.GET_GENERAL)){
             list = sysUserGroupDao.listForHelpScreen(find, search, wild);
         }else if(type.equals(RrcStandardUim.GET_WITH_ACTIVE_STATUS)) {
             final String ACTIVE_STATUS = "A";
             list = sysUserGroupDao.listForHelpScreen(find, search, wild, ACTIVE_STATUS);
         }
         return list;
     }
 }
