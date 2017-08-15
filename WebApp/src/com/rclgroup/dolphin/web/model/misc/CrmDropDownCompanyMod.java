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

public class CrmDropDownCompanyMod extends  RrcStandardMod {
    private String value;
    private String description2;
    private String description;
    
    public CrmDropDownCompanyMod() {
     
        super();
        
    }
    public CrmDropDownCompanyMod(String strValue, String strDescription ,String strDescription2) {
        value = strValue;
        description = strDescription;
        description2= strDescription2;
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

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription2() {
        return description2;
    }
}

