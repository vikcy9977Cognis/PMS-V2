/*-----------------------------------------------------------------------------------------------------------  
RcmAuthenticationMod.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 30/10/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.model.rcm;

public class RcmAuthenticationMod {
    String menuId;
    boolean isVisible;
    boolean isCreate;
    boolean isRead;
    boolean isUpdate;
    boolean isDelete;
    
    public RcmAuthenticationMod() {
        menuId = "";
        isVisible = false;
        isCreate = false;
        isRead = false;
        isUpdate = false;
        isDelete = false;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsCreate(boolean isCreate) {
        this.isCreate = isCreate;
    }

    public boolean isCreate() {
        return isCreate;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isDelete() {
        return isDelete;
    }
}
