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

public class CrmDropDownItemMod extends  RrcStandardMod {
    private String value;
    private String description;
    
    public CrmDropDownItemMod() {
     
        super();
        
    }
    public CrmDropDownItemMod(String strValue, String strDescription) {
        value = strValue;
        description = strDescription;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

