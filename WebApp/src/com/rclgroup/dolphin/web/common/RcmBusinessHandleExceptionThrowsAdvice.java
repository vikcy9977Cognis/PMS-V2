/*-----------------------------------------------------------------------------------------------------------  
RcmBusinessHandleExceptionThrowsAdvice.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 04/03/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.exception.CustomDataAccessException;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;


public class RcmBusinessHandleExceptionThrowsAdvice implements ThrowsAdvice {
    public void afterThrowing(Exception e) throws Throwable {
        this.handleException(e);
    }
    
    private void handleException(Exception e) throws CustomDataAccessException {
        if(e instanceof CustomDataAccessException){
            throw (CustomDataAccessException)e; 
        }else if(e instanceof DataAccessException){
            e.printStackTrace(); 
        }
    }
}

