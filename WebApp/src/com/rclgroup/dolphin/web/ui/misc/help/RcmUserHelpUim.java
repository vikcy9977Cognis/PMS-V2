/*-----------------------------------------------------------------------------------------------------------  
RcmUserHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Sopon Dee-udomvongsa 28/03/2008
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/ 

package com.rclgroup.dolphin.web.ui.misc.help;

import com.rclgroup.dolphin.web.common.RrcStandardHelpUim;
import com.rclgroup.dolphin.web.common.RrcStandardUim;
import com.rclgroup.dolphin.web.dao.sys.SysUserDao;
import com.rclgroup.dolphin.web.model.rcm.RcmSearchMod;
import com.rclgroup.dolphin.web.util.RutPage;

import java.util.List;

/**
 * UI model: RCM: User Help Screen 
 *
 */
 
public class RcmUserHelpUim extends RrcStandardHelpUim {
    static final String FIND_IN_DEFAULT = "";
    static final String FIND_IN_USER_ID = "I";
    static final String FIND_IN_NAME = "N";
    static final String FIND_IN_FSC = "F";
    static final String FIND_IN_ORGANIZATION_TYPE = "O";
    static final String FIND_IN_DEPARTMENT = "D";
    static final String FIND_IN_EMAIL = "E";
    
    static final String STATUS_DEFAULT = "ACTIVE";
    static final String STATUS_ALL = "ALL";
    static final String STATUS_ACTIVE = "ACTIVE";    
    static final String STATUS_SUSPENDED = "SUSPENDED";
    
    static final String SORT_BY_DEFAULT = "I";
    static final String SORT_BY_USER_ID = "I";
    static final String SORT_BY_NAME = "N";
    static final String SORT_BY_FSC = "F";
    static final String SORT_BY_ORGANIZATION_TYPE = "O";
    static final String SORT_BY_DEPARTMENT = "D";
    static final String SORT_BY_EMAIL = "E";
    
    static final String SORT_BY_IN_DEFAULT = "ASC";
    static final String SORT_BY_IN_ASC = "ASC";
    static final String SORT_BY_IN_DESC = "DESC";
    
    static final String WILD_DEFAULT = "ON";
    
    static final int SELECT_DEFAULT = 0;
    private SysUserDao sysUserDao;
    private String find;
    private String findIn;
    private String status;
    private String sortBy;
    private String sortByIn;
    private RutPage rutPage;
    private int select;
    
    public RcmUserHelpUim() {
        find = "";
        findIn = FIND_IN_DEFAULT;
        status = STATUS_DEFAULT;
        sortBy = SORT_BY_DEFAULT;
        sortByIn = SORT_BY_IN_DEFAULT;
        rutPage = null;
        select = SELECT_DEFAULT;
    }
    
    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public void setFindIn(String findIn) {
        this.findIn = findIn;
    }

    public String getFindIn() {
        return findIn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortByIn(String sortByIn) {
        this.sortByIn = sortByIn;
    }

    public String getSortByIn() {
        return sortByIn;
    }

    public void setRutPage(RutPage rutPage) {
        this.rutPage = rutPage;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }
    
    public List getListForHelpScreen(String find, String search, String wild, String type) {
        List list = null;
        if(type == null || type.equals("") || type.equalsIgnoreCase(RrcStandardUim.GET_GENERAL)){
            list = sysUserDao.listForHelpScreen(find,search,wild);
        }
        return list;
    }
    
//    public void createRutPage(){
//        List list = getItemList();
//        if(list == null) list = new ArrayList();
//        if(list.size()==0){
//            rutPage = null;
//        }else{
//            rutPage = new RutPage(list);
//        }
//        
//    }
    
//    public List getItemList() {
//        RcmSearchMod searchMod = new RcmSearchMod(find,findIn,status,sortBy,sortByIn,WILD_DEFAULT);
//        List list = sysUserDao.listForHelpScreen(searchMod);
//        return list;
//    }

}
