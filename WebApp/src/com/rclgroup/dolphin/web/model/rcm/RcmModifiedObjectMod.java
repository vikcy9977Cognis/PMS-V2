/*-----------------------------------------------------------------------------------------------------------  
RcmModifiedObjectMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Author Wuttitorn Wuttijiaranai 14/10/09 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

import java.io.Serializable;

import java.lang.reflect.Method;


public class RcmModifiedObjectMod implements Serializable {
    
    private String operationFlag;
    private int seqNo;
    private Object objectMod;
    
    public RcmModifiedObjectMod() {
        operationFlag = "";
        seqNo = 0;
        objectMod = null;
    }

    public RcmModifiedObjectMod(String operationFlag, int seqNo, Object objectMod, Class className) {
        try {
            if (className != null) {
                this.operationFlag = operationFlag;
                this.seqNo = seqNo;
                
                try {
                    Method subMethod = className.getMethod("cloneObject", new Class[] { java.lang.Object.class });
                    this.objectMod = subMethod.invoke(className.newInstance(), new Object[] { objectMod });
                } catch(Exception e) {
                    e.printStackTrace();
                    this.objectMod = className.cast(objectMod);
                }
                
            } else {
                this.operationFlag = operationFlag;
                this.seqNo = seqNo;
                this.objectMod = objectMod;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getOperationFlag() {
        return operationFlag;
    }

    public void setOperationFlag(String operationFlag) {
        this.operationFlag = operationFlag;
    }
    
    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public Object getObjectMod() {
        return objectMod;
    }

    public void setObjectMod(Object objectMod) {
        this.objectMod = objectMod;
    }
}
