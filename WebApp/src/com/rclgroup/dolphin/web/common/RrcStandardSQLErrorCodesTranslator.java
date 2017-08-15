/*-----------------------------------------------------------------------------------------------------------  
RrcStandardSQLErrorCodesTranslator.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 03/04/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/
 
package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.exception.CustomDataAccessException;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;


public class RrcStandardSQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlex) {
        if (sqlex.getErrorCode() == 20001) {
            String errorMessage = sqlex.getMessage().toString();
            String customErrorString = errorMessage.substring(errorMessage.indexOf("ORA-")+11,errorMessage.indexOf("\n")).trim();

            return new CustomDataAccessException(customErrorString,sqlex); 
        }
        return null;
    }
} 
