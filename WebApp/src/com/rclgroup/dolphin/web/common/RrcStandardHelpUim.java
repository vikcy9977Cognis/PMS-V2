/*-----------------------------------------------------------------------------------------------------------  
RrcStandardHelpUim.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 09/11/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 25/08/09 WUT                       Adjust new help screen
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.util.RutPage;

import java.util.ArrayList;
import java.util.List;


public abstract class RrcStandardHelpUim extends RrcStandardUim {
    protected static final String FIND_DEFAULT = "";
    protected static final String SEARCH_DEFAULT = "";
    protected static final String WILD_DEFAULT = "ON";
    protected static final int VERSION_01 = 1;
    protected static final int VERSION_02 = 2;

    protected RutPage rutPage;
    protected String formName;
    protected String retName;
    protected String retFunc;
    protected String find;
    protected String search;
    protected String wild;
    protected String type;
    protected int helpVersion;
    protected int noOfResultRec;
    
    // ##01 BEGIN
    private String sortBy;
    private String sortIn;
    // ##01 END
    
    public RrcStandardHelpUim(){
        rutPage = null;
        formName = "";
        retName = "";
        retFunc = "";
        find = FIND_DEFAULT;
        search = SEARCH_DEFAULT;
        wild = WILD_DEFAULT;   
        type = "";
        helpVersion = VERSION_01;
        
        // ##01 BEGIN
        sortBy = "";
        sortIn = RcmConstant.SORT_ASCENDING;
        // ##01 END
    }

    public RutPage getRutPage() {
        return rutPage;
    }
    
    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setRetName(String retName) {
        this.retName = retName;
    }

    public String getRetName() {
        return retName;
    }
    
    public void setRetFunc(String retFunc) {
        this.retFunc = retFunc;
    }

    public String getRetFunc() {
        return retFunc;
    }
    
    public void setFind(String find) {
        this.find = find;
    }

    public String getFind() {
        return find;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setWild(String wild) {
        this.wild = wild;
    }

    public String getWild() {
        return wild;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortIn() {
        return sortIn;
    }

    public void setSortIn(String sortIn) {
        this.sortIn = sortIn;
    }
    
    public void createRutPage(){
//        if(rutPage==null){
            rutPage = new RutPage();
//        }
        System.out.println("[RrcStandardHelpUim][createRutPage]");
        List list = getListForHelpScreen(find,search,wild,type);
        System.out.println("[RrcStandardHelpUim][createRutPage] List:"+list.size());
        if(list == null) list = new ArrayList();
        if(this.helpVersion == this.VERSION_01){
            rutPage = new RutPage(list);
        }else{
            rutPage = new RutPage(list,this.noOfResultRec,this.VERSION_02);
        }
    }

    public void queryDataByRutPage(){
        if(rutPage==null){
            rutPage = new RutPage();
        }
        List list = getListForHelpScreen(find,search,wild,type);
        if(list == null) list = new ArrayList();
        if(this.helpVersion == this.VERSION_02){
            rutPage.setItems(list);
        }
    }

    public abstract List getListForHelpScreen(String find,String search,String wild,String type);

    public void setNoOfResultRecords(int noOfResultRec) {
        this.noOfResultRec = noOfResultRec;
        this.helpVersion = this.VERSION_02;
    }

    public int getNoOfResultRecords() {
        return noOfResultRec;
    }
    
}

