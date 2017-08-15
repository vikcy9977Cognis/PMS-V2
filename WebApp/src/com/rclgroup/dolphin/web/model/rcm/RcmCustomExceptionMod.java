/*-----------------------------------------------------------------------------------------------------------  
RcmCustomExceptionMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 28/02/2011
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardMod;


public class RcmCustomExceptionMod extends RrcStandardMod {
    
    private String errorCode;
    private String messageCode;
        
    public RcmCustomExceptionMod() {
        super();
        this.errorCode = "";
        this.messageCode = "";
    }

    public RcmCustomExceptionMod(String errorCode, String messageCode) {
        super();
        this.errorCode = errorCode;
        this.messageCode = messageCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
