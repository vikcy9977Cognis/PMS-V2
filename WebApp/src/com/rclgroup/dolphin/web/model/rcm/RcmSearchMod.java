/*-----------------------------------------------------------------------------------------------------------  
RcmSearchMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 03/04/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

public class RcmSearchMod {
    private String find;
    private String findIn;
    private String status; 
    private String sortBy; 
    private String sortByIn;
    private String wild;

    public RcmSearchMod(String find, String findIn, String status, String sortBy, String sortByIn, String wild) {
        this.find = find;
        this.findIn = findIn;
        this.status = status;
        this.sortBy = sortBy;
        this.sortByIn = sortByIn;
        this.wild = wild;
    }
    
    public RcmSearchMod() {
        this("","","","","","");
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getFind() {
        return find;
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

    public void setWild(String wild) {
        this.wild = wild;
    }

    public String getWild() {
        return wild;
    }
}
