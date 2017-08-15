package com.rclgroup.dolphin.web.model.esm;

public class EsmMenuTreeMod {
    private String levelId;
    private String menuId;
    private String programId;
    private String seqId;
    private String descr;
    private String winNm;
    private String libNm;
    private String moduleNm;
    private String MenuType;
    private String progSource;
    private String imageNm;
    private String UrlNm;
    private String scCreate;
    private String scRead;
    private String scUpdate;
    private String scDelete;
    private String menuItemVisible;
    private int dataStatus;
    private boolean isDeleteRow;

    public EsmMenuTreeMod() {
        scCreate = "N";
        scRead = "N";
        scUpdate = "N";
        scDelete = "N";
        menuItemVisible = "N";
        dataStatus = 1;
        isDeleteRow = false;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setWinNm(String winNm) {
        this.winNm = winNm;
    }

    public String getWinNm() {
        return winNm;
    }

    public void setLibNm(String libNm) {
        this.libNm = libNm;
    }

    public String getLibNm() {
        return libNm;
    }

    public void setModuleNm(String moduleNm) {
        this.moduleNm = moduleNm;
    }

    public String getModuleNm() {
        return moduleNm;
    }

    public void setMenuType(String menuType) {
        this.MenuType = menuType;
    }

    public String getMenuType() {
        return MenuType;
    }

    public void setProgSource(String progSource) {
        this.progSource = progSource;
    }

    public String getProgSource() {
        return progSource;
    }

    public void setImageNm(String imageNm) {
        this.imageNm = imageNm;
    }

    public String getImageNm() {
        return imageNm;
    }

    public void setUrlNm(String urlNm) {
        this.UrlNm = urlNm;
    }

    public String getUrlNm() {
        return UrlNm;
    }

    public void setScCreate(String scCreate) {
        this.scCreate = scCreate;
    }

    public String getScCreate() {
        return scCreate;
    }

    public void setScRead(String scRead) {
        this.scRead = scRead;
    }

    public String getScRead() {
        return scRead;
    }

    public void setScUpdate(String scUpdate) {
        this.scUpdate = scUpdate;
    }

    public String getScUpdate() {
        return scUpdate;
    }

    public void setScDelete(String scDelete) {
        this.scDelete = scDelete;
    }

    public String getScDelete() {
        return scDelete;
    }

    public void setMenuItemVisible(String menuItemVisible) {
        this.menuItemVisible = menuItemVisible;
    }

    public String getMenuItemVisible() {
        return menuItemVisible;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setIsDeleteRow(boolean isDeleteRow) {
        this.isDeleteRow = isDeleteRow;
    }

    public boolean isDeleteRow() {
        return isDeleteRow;
    }
}
