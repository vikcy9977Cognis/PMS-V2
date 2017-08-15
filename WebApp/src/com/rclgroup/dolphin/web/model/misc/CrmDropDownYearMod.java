/*-----------------------------------------------------------------------------------------------------------  
CrmDropDownItemMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2015 
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 08/08/15
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------*/
package com.rclgroup.dolphin.web.model.misc;
import com.rclgroup.dolphin.web.common.RrcStandardMod;

public class CrmDropDownYearMod extends  RrcStandardMod {
    private String value;
 
    
    public CrmDropDownYearMod() {
     
        super();
        
    }
    public CrmDropDownYearMod(String strValue) {
        value = strValue;
        
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

  
}

