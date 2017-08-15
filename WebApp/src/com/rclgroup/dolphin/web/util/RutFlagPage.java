/*-----------------------------------------------------------------------------------------------------------  
RutFlagPage.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description
01 14/10/09 WUT                       Tuning performance
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import com.rclgroup.dolphin.web.common.RcmConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class RutFlagPage extends RutPage {
    private List existItems;
    private List modifiedItems;
    private HashMap prevItems;
    private HashMap flagItems;
    private Class className;
    private RutOperationFlagManager manager;
    
    public RutFlagPage(int maxItemsPerPage, int maxPagesPerPageSet, List items, Class className) {
        super(maxItemsPerPage, maxPagesPerPageSet, items);
        manager = RutOperationFlagManager.getInstance();
        
        //begin: initial
        existItems = new ArrayList();
        modifiedItems = new ArrayList();
        prevItems = new HashMap();
        flagItems = new HashMap();
        this.className = className;
        manager.initialFlag(items, prevItems, flagItems);
        //end: initial
    }
    
    public void setExistItems(List existItems) {
        this.existItems = existItems;
    }
     
    public List getExistItems() {
        return existItems;
    }
    
    public void setModifiedItems(List modifiedItems) {
        this.modifiedItems = modifiedItems;
    }
    
    public List getModifiedItems() {
        return modifiedItems;
    }
    
    public void setPrevItems(HashMap prevItems) {
        this.prevItems = prevItems;
    }
    
    public HashMap getPrevItems() {
        return prevItems;
    }
    
    public void setFlagItems(HashMap flagItems) {
        this.flagItems = flagItems;
    }
    
    public HashMap getFlagItems() {
        return flagItems;
    } 
    
    public void setClassName(Class className) {
        this.className = className;
    }
    
    public Class getClassName() {
        return className;
    } 
    
    public void addItemByObject(Object item) {
        this.addItemByPosition(items.size(), item);
    }
    
    public void addItemByPosition(int position, Object item) {
        int intPosition = RcmConstant.SELECT_NOT_CHOOSED;
        if (position == RcmConstant.SELECT_NOT_CHOOSED) {
            intPosition = items.size();
        } else {
            intPosition = (position < 0 || (position + 1) > items.size()) ? items.size() : (position + 1);
        }
        
        items.add(intPosition, item);
        calculatePageAndPageSet(items.size(), this.maxItemsPerPage, this.maxPagesPerPageSet);
    }
    
    public void deleteItemByPosition(int position) {
        if (items != null && items.size() > 0 && position != -1 && position < items.size()) {
            items.remove(position);
        }
    }
    
    public void deleteItemByList(List lstRemoveItems) {
        if (lstRemoveItems != null && lstRemoveItems.size() > 0) { //if 1
            //sorting descending
            Collections.sort(lstRemoveItems, new SortByNumber());
            
            int idxRemove = -1;
            for (int i=0;i<lstRemoveItems.size();i++) {
                idxRemove = RutString.toInteger((String) lstRemoveItems.get(i), -1);
                
                if (idxRemove != -1 && idxRemove < items.size()) {
                    items.remove(idxRemove); //remove object
                }
            }
        } //end if 1
        calculatePageAndPageSet(items.size(), this.maxItemsPerPage, this.maxPagesPerPageSet);
    }
    
    public class SortByNumber implements Comparator {
        public int compare(Object obj1, Object obj2) {
            int sdif = 0;
            if (obj1 != null && obj1 instanceof String && obj2 != null && obj2 instanceof String) {
                int bean1 = RutString.toInteger((String) obj1);
                int bean2 = RutString.toInteger((String) obj2);
                
                sdif = new Integer(bean2).compareTo(new Integer(bean1));
            }
            return sdif;
        }
    }
    
    public void compareItems() {
        manager.compareFlagElement(items, prevItems, flagItems);
        this.existItems = manager.findExistedItems(items, flagItems, className);
        this.modifiedItems = manager.findModifiedItems(items, prevItems, flagItems, className);
    }
    
}
