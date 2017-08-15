/*-----------------------------------------------------------------------------------------------------------  
RutFormatting.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 21/04/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class RutFormatting {
    public RutFormatting() {
    }
    
    public static String getDecimalFormat(Object value, String pattern){
        DecimalFormat df = null;
        try {
            df = (DecimalFormat)NumberFormat.getInstance(RutLocale.getDefaultLocale());
        }catch (ClassCastException e) {
            System.err.println(e);
        }
        df.applyPattern(pattern);
        return df.format(value);
    }
}
