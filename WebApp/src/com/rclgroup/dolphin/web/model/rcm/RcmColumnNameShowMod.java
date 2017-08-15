/*-----------------------------------------------------------------------------------------------------------  
RcmColumnNameShowMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Author Wuttitorn Wuttijiaranai 26/08/09 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

import java.io.Serializable;


public class RcmColumnNameShowMod implements Serializable {
    
    private String columnNameShow;
    private String columnText;
    private String columnSize;
    private String columnAlign;
    private String dataType;
    private String className;
    private String methodName;
    private String format;
    
    public RcmColumnNameShowMod() {
        columnNameShow = "";
        columnText = "";
        columnSize = "";
        columnAlign = "";
        dataType = "";
        className = "";
        methodName = "";
        format = "";
    }
    
    public RcmColumnNameShowMod(String columnNameShowValue) {
        try {
            columnNameShow = columnNameShowValue;
            
            // format columenNameShowValue : Column Text | Size | Align | Data Type [| Class Name | Method Name]
            // format columenNameShowValue : Column Text | Size | Align | Data Type [| Data Format ]
            String[] tmp = columnNameShowValue.split("\\|");
            if (tmp != null && tmp.length == 6) {
                columnText = tmp[0];
                columnSize = tmp[1];
                columnAlign = tmp[2];
                dataType = tmp[3];
                className = tmp[4];
                methodName = tmp[5];
            } else if (tmp != null && tmp.length == 5) {
                columnText = tmp[0];
                columnSize = tmp[1];
                columnAlign = tmp[2];
                dataType = tmp[3];
                format = tmp[4];
            } else if (tmp != null && tmp.length == 4) {
                columnText = tmp[0];
                columnSize = tmp[1];
                columnAlign = tmp[2];
                dataType = tmp[3];
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("[RcmColumnNameShowMod][execute]: Error create constructor");
        }
    }
    
    public String getColumnNameShow() {
        return columnNameShow;
    }

    public void setColumnNameShow(String columnNameShow) {
        this.columnNameShow = columnNameShow;
    }

    public String getColumnText() {
        return columnText;
    }

    public void setColumnText(String columnText) {
        this.columnText = columnText;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public String getColumnAlign() {
        return columnAlign;
    }

    public void setColumnAlign(String columnAlign) {
        this.columnAlign = columnAlign;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
