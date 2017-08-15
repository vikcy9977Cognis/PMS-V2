/*-----------------------------------------------------------------------------------------------------------  
RutOperationFlagManager.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description
01 14/10/09 WUT                       Tuning performance
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import com.rclgroup.dolphin.web.model.rcm.RcmFlagObjectMod;
import com.rclgroup.dolphin.web.model.rcm.RcmModifiedObjectMod;

import java.lang.reflect.Constructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RutOperationFlagManager {
    private static RutOperationFlagManager me;
    
    public static final String OPERATION_FLAG_INSERT = "I"; // This status shows that this record will be inserted
    public static final String OPERATION_FLAG_UPDATE = "U"; // This status shows that this record will be updated
    public static final String OPERATION_FLAG_DELETE = "D"; // This status shows that this record will be deleted
    public static final String OPERATION_FLAG_MODIFY = "M"; // This status shows that this record has alrady been modified
    
    //begin: function
    /*private void debugObject(HashMap hBean) {
        try {
            if (hBean != null && hBean.size() > 0) {
                System.out.println("### debugObject : hBean.size() = "+hBean.size());
            
                List lstKey = new ArrayList(hBean.keySet());
                for (int i=0;i<lstKey.size();i++) {
                    String key = String.valueOf(lstKey.get(i));
                    String tmp = (String) hBean.get(key);
                    System.out.println("### debugObject : key = "+key+" : flag = "+tmp);
                }
            }    
        } catch(Exception e) { }
    }*/
    
    public RutOperationFlagManager() {
        super();        
    }
    
    public static RutOperationFlagManager getInstance() {
        if (me == null) {
            me = new RutOperationFlagManager();
        }
        return me;
    }
    
    public void initialFlag(List items, HashMap prevItems, HashMap flagItems) {
        if (prevItems != null && flagItems != null && items != null && items.size() > 0) { //if 1
            Class cls = items.get(0).getClass();
            Constructor clsConstructor = null;
            
            String strId = null;
            for (int i=0;i<items.size();i++) { //for 1
                strId = RutString.getObjectId(items.get(i));
                
                try {
                    clsConstructor = cls.getConstructor(new Class[]{cls});
                    
                    // initial previous items & flag items
                    prevItems.put(strId, clsConstructor.newInstance(new Object[] { items.get(i) }));
                    flagItems.put(strId, new RcmFlagObjectMod(strId, RutOperationFlagManager.OPERATION_FLAG_MODIFY, i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } //end for 1
        } //end if 1
    }
    
    public void compareFlagElement(List items, HashMap prevItems, HashMap flagItems) {
        // clear all flag items
        String strKey = null;
        List arKey = new ArrayList(flagItems.keySet());
        for (int i=0;i<arKey.size();i++) {
            strKey = (String) arKey.get(i);
            flagItems.put(strKey, new RcmFlagObjectMod(strKey, RutOperationFlagManager.OPERATION_FLAG_DELETE, i)); // set delete flag
        }
        
        // compare items
        Object currBean = null;
        Object prevBean = null;
        RcmFlagObjectMod flagMod = null;
        for (int i=0;i<items.size();i++) { //for 1
            currBean = items.get(i);
            strKey = RutString.getObjectId(currBean);
            
            if (prevItems.containsKey(strKey)) { //if 1
                prevBean = prevItems.get(strKey);
                if (prevBean != null && prevBean.equals(currBean)) { //if 2
                    flagItems.put(strKey, new RcmFlagObjectMod(strKey, RutOperationFlagManager.OPERATION_FLAG_MODIFY, i)); // set update flag
                } else {
                    flagItems.put(strKey, new RcmFlagObjectMod(strKey, RutOperationFlagManager.OPERATION_FLAG_UPDATE, i)); // set modify flag
                } //end if 2
            } else {
                flagItems.put(strKey, new RcmFlagObjectMod(strKey, RutOperationFlagManager.OPERATION_FLAG_INSERT, i)); // set insert flag
            } //end if 1
        } //end for 1
    }
    
    public List findExistedItems(List items, HashMap flagItems, Class className) {
        List modifiedItems = new ArrayList();
        RcmModifiedObjectMod modifiedMod = null;
         
        String strKey = null;
        String operationFlag = null;
        Object bean = null;
        RcmFlagObjectMod flagMod = null;
         
        //begin: convert List to HashMap
        HashMap hBean = new HashMap();
        for (int i=0;i<items.size();i++) {
            bean = items.get(i);
            strKey = RutString.getObjectId(bean);
            hBean.put(strKey, bean);
        }
        //end: convert List to HashMap
         
        List arBean = new ArrayList(flagItems.keySet());
        for (int i=0;i<arBean.size();i++) { //for 1
            strKey = (String) arBean.get(i);
            
            if (flagItems.containsKey(strKey) && flagItems.get(strKey) instanceof RcmFlagObjectMod) {
                flagMod = (RcmFlagObjectMod) flagItems.get(strKey);    
                
                if (flagMod != null) { //if 1
                    operationFlag = flagMod.getOperationFlag();
                    modifiedMod = null;
                    
                    bean = null;
                    if (RutOperationFlagManager.OPERATION_FLAG_INSERT.equals(operationFlag) 
                        || RutOperationFlagManager.OPERATION_FLAG_UPDATE.equals(operationFlag)
                        || RutOperationFlagManager.OPERATION_FLAG_MODIFY.equals(operationFlag)) 
                    {
                        bean = hBean.get(strKey);
                    } else {
                        continue;
                    }
                     
                    if (bean != null) {
                        modifiedMod = new RcmModifiedObjectMod(operationFlag, flagMod.getSeqNo(), bean, className);
                        modifiedItems.add(modifiedMod);
                    }    
                } //end if 1
            }
        } //end for 1
         
        return modifiedItems;
    }
    
    public List findModifiedItems(List items, HashMap prevItems, HashMap flagItems, Class className) {
        List modifiedItems = new ArrayList();
        RcmModifiedObjectMod modifiedMod = null;
        
        String strKey = null;
        String operationFlag = null;
        Object bean = null;
        RcmFlagObjectMod flagMod = null;
        
        //begin: convert List to HashMap
        HashMap hBean = new HashMap();
        for (int i=0;i<items.size();i++) {
            bean = items.get(i);
            strKey = RutString.getObjectId(bean);
            hBean.put(strKey, bean);
        }
        //end: convert List to HashMap
        
        List arBean = new ArrayList(flagItems.keySet());
        for (int i=0;i<arBean.size();i++) { //for 1
            strKey = (String) arBean.get(i);
            
            if (flagItems.containsKey(strKey) && flagItems.get(strKey) instanceof RcmFlagObjectMod) {
                flagMod = (RcmFlagObjectMod) flagItems.get(strKey);    
                
                if (flagMod != null) { //if 1
                    operationFlag = flagMod.getOperationFlag();
                    modifiedMod = null;
                    
                    if (RutOperationFlagManager.OPERATION_FLAG_INSERT.equals(operationFlag) || RutOperationFlagManager.OPERATION_FLAG_UPDATE.equals(operationFlag)) {
                        bean = hBean.get(strKey);
                    } else if (RutOperationFlagManager.OPERATION_FLAG_DELETE.equals(operationFlag)) {
                        bean = prevItems.get(strKey);
                    } else {
                        continue;
                    }
                    
                    if (bean != null) {
                        modifiedMod = new RcmModifiedObjectMod(operationFlag, flagMod.getSeqNo(), bean, className);
                        modifiedItems.add(modifiedMod);
                    }    
                } //end if 1
            }
        } //end for 1
        
        return modifiedItems;
    }
    
}
