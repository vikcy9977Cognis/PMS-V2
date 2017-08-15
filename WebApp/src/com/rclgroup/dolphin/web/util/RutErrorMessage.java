/*-----------------------------------------------------------------------------------------------------------  
RutErrorMessage.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import java.util.ArrayList;
import java.util.List;

public class RutErrorMessage {
    private String errorCode;
    private List argumentList;
    
    public RutErrorMessage(String errorCode, List argumentList) {
        this.errorCode = errorCode;
        this.argumentList = new ArrayList(argumentList);
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setArgumentList(List argumentList) {
        this.argumentList = argumentList;
    }

    public List getArgumentList() {
        return argumentList;
    }
}
