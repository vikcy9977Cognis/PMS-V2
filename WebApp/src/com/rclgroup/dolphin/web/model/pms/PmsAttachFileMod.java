/*-----------------------------------------------------------------------------------------------------------
PmsAttachFileMod.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------
 Author Khomsun H. 12/05/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
*/
package com.rclgroup.dolphin.web.model.pms;

import com.rclgroup.dolphin.web.common.RrcStandardMod;

import java.sql.Timestamp;

public class PmsAttachFileMod extends RrcStandardMod{
    private Integer uploadHDRSeqNo;
    private Integer uploadDTLSeqNo;
    private Integer pmHDRSeqNo;
    private String fileName;
    private String fileFullPath;
    private Integer fileSizeKB;
    
    private String recordStatus;
    private String recordAddUser;
    private Timestamp recordAddDate;
    private String recordChangeUser;
    private Timestamp recordChangeDate;
    
    public PmsAttachFileMod() {
        super();
    }

    public void setUploadHDRSeqNo(Integer uploadHDRSeqNo) {
        this.uploadHDRSeqNo = uploadHDRSeqNo;
    }

    public Integer getUploadHDRSeqNo() {
        return uploadHDRSeqNo;
    }

    public void setUploadDTLSeqNo(Integer uploadDTLSeqNo) {
        this.uploadDTLSeqNo = uploadDTLSeqNo;
    }

    public Integer getUploadDTLSeqNo() {
        return uploadDTLSeqNo;
    }

    public void setPmHDRSeqNo(Integer pmHDRSeqNo) {
        this.pmHDRSeqNo = pmHDRSeqNo;
    }

    public Integer getPmHDRSeqNo() {
        return pmHDRSeqNo;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileFullPath(String fileFullPath) {
        this.fileFullPath = fileFullPath;
    }

    public String getFileFullPath() {
        return fileFullPath;
    }

    public void setFileSizeKB(Integer fileSizeKB) {
        this.fileSizeKB = fileSizeKB;
    }

    public Integer getFileSizeKB() {
        return fileSizeKB;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordAddUser(String recordAddUser) {
        this.recordAddUser = recordAddUser;
    }

    public String getRecordAddUser() {
        return recordAddUser;
    }

    public void setRecordAddDate(Timestamp recordAddDate) {
        this.recordAddDate = recordAddDate;
    }

    public Timestamp getRecordAddDate() {
        return recordAddDate;
    }

    public void setRecordChangeUser(String recordChangeUser) {
        this.recordChangeUser = recordChangeUser;
    }

    public String getRecordChangeUser() {
        return recordChangeUser;
    }

    public void setRecordChangeDate(Timestamp recordChangeDate) {
        this.recordChangeDate = recordChangeDate;
    }

    public Timestamp getRecordChangeDate() {
        return recordChangeDate;
    }
}
