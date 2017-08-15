/*-----------------------------------------------------------------------------------------------------------  
RcmFlagObjectMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Author Wuttitorn Wuttijiaranai 03/12/09 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

import java.io.Serializable;


public class RcmFlagObjectMod implements Serializable {
    
    private String keyString;
    private String operationFlag;
    private int seqNo;
    
    public RcmFlagObjectMod() {
        setKeyString("");
        setOperationFlag("");
        setSeqNo(0);
    }

    public RcmFlagObjectMod(String keyString, String operationFlag, int seqNo) {
        this.keyString = keyString;
        this.operationFlag = operationFlag;
        this.seqNo = seqNo;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
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
}
