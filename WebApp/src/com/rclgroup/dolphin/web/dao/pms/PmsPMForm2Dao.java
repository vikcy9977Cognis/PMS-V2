/*-----------------------------------------------------------------------------------------------------------
PmsPMForm2Dao.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2015
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 03/07/17
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.dao.pms;

import com.rclgroup.dolphin.web.model.pms.PmsAttachFileMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMDetailMod;
import com.rclgroup.dolphin.web.model.pms.PmsPMOverallMod;

import com.rclgroup.dolphin.web.model.pms.PmsPmHDRMod;

import com.rclgroup.dolphin.web.model.pms.PmsUserAuthorizeMod;

import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface PmsPMForm2Dao {
    /**
     * Get Overall PM in particular year
     * @param userID
     * @param pmKey
     * @param empSeqNo
     * @param pmYear
     * @return
     * @throws DataAccessException
     */
    public List<PmsPMOverallMod> getPMOverall(final String userID, final String pmKey, final Long empSeqNo,
                                              final Integer pmYear) throws DataAccessException;

    /**
     * Insert PM Form Header
     * @param userID
     * @param pmHDR
     * @param pmKey
     * @return
     * @throws DataAccessException
     */
    public Map<String, Object> insertPMHeader(final String userID, final String pmKey,
                                              final PmsPmHDRMod pmHDR) throws DataAccessException;

    /**
     * Update PM Form Header
     * @param userID
     * @param pmHDR
     * @param pmKey
     * @return
     * @throws DataAccessException
     */
    public Map<String, Object> updatePMHeader(final String userID, final String pmKey,
                                              final PmsPmHDRMod pmHDR) throws DataAccessException;
    /**
     * Get PM Header entry by seq.
     * @param userID
     * @param empSeqNo
     * @param pmKey
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public PmsPmHDRMod getPMHDR(final String userID,
                                final Long empSeqNo,
                                final String pmKey,
                                final Long pmHDRSeqNo) throws DataAccessException;
    /**
     * Calculate PM form and update summary to PM form header.
     * @param userID
     * @param pmKey
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public Map<String, Object> calculatePM(final String userID, final String pmKey,
                                           final Long pmHDRSeqNo) throws DataAccessException;

    /**
     * Get Lis of President Directives
     * @param userID
     * @param empSeqNo
     * @param pmKey
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public List<PmsPMDetailMod> getPresidentDirectives(final String userID, final Long empSeqNo, final String pmKey,
                                                       final Long pmHDRSeqNo) throws DataAccessException;

    /**
     * Get Lis of Company Core Values
     * @param userID
     * @param empSeqNo
     * @param pmKey
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public List<PmsPMDetailMod> getCompanyCoreValues(final String userID, final Long empSeqNo, final String pmKey,
                                                     final Long pmHDRSeqNo) throws DataAccessException;
    
    /**
     * Insert a Company Core Value
     * @param userID
     * @param pmKey
     * @param pmHDRSeqNo
     * @param indicatorSeqNo
     * @param selfRating
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> insertCompanyCoreValue(final String userID,
                                                     final String pmKey,
                                                     final Long pmHDRSeqNo,
                                                     final Long indicatorSeqNo,                                
                                                     final Double selfRating) throws DataAccessException;

    /**
     * Update particular Company Core Value
     * @param userID
     * @param pmKey
     * @param pmDetail
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> updateCompanyCoreValue(final String userID,
                                                     final Long empSeqNo,
                                                  final String pmKey,
                                                  final PmsPMDetailMod pmDetail
                                                  ) throws DataAccessException;
    /**
     * Get Lis of Department Core Values
     * @param userID
     * @param empSeqNo
     * @param pmKey
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public List<PmsPMDetailMod> getDepartmentCoreValues(final String userID, final Long empSeqNo, final String pmKey,
                                                        final Long pmHDRSeqNo) throws DataAccessException;
    
    /**
     * Insert Department Core Value
     * @param userID
     * @param pmKey
     * @param pmHDRSeqNo
     * @param indicatorSeqNo
     * @param weightage
     * @param selfRating
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> insertDepartmentCoreValue(final String userID,
                                                     final String pmKey,
                                                     final Long pmHDRSeqNo,
                                                     final Long indicatorSeqNo,
                                                     final Double weightage,
                                                     final Double selfRating) throws DataAccessException;

    /**
     * Update Department Core Value
     * @param userID
     * @param empSeqNo
     * @param pmKey
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> updateDepartmentCoreValue(final String userID,                                                                                                         
                                                     final Long empSeqNo,
                                                     final String pmKey,
                                                     final PmsPMDetailMod pmDetail
                                                   ) throws DataAccessException;

    /**
     * Delete Department Core Value
     * @param userID
     * @param pmKey
     * @param pmDetail
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> deleteDepartmentCoreValue(final String userID,
                                                        final Long empSeqNo,
                                                        final String pmKey, final PmsPMDetailMod pmDetail)
                        throws DataAccessException;
    /**
     * Get Lis of Individual KPIs
     * @param userID
     * @param empSeqNo
     * @param pmKey
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public List<PmsPMDetailMod> getIndividualKPIs(final String userID, final Long empSeqNo, final String pmKey,
                                                  final Long pmHDRSeqNo) throws DataAccessException;
    
    /**
     * Insert Individual KPI.
     * @param userID
     * @param pmKey
     * @param pmHDRSeqNo
     * @param bscSeqNo
     * @param indDesc
     * @param indSlab0
     * @param indSlab1
     * @param indSlab2
     * @param indSlab3
     * @param indSlab4
     * @param indSlab5
     * @param weightage
     * @param result
     * @param rating
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> insertIndividualKPI(final String userID,
                                                  final String pmKey,
                                                  final Long pmHDRSeqNo,
                                                  final Long bscSeqNo,
                                                  final String indDesc,
                                                  final String indSlab0,
                                                  final String indSlab1,
                                                  final String indSlab2,
                                                  final String indSlab3,
                                                  final String indSlab4,
                                                  final String indSlab5,
                                                  final Double weightage,
                                                  final String result,
                                                  final Double rating) throws DataAccessException;
    /**
     * Update Individual KPI
     * @param userID
     * @param pmKey
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> updateIndividualKPI(final String userID,                                                
                                                  final String pmKey,
                                                  final Long empSeqNo,
                                                  final PmsPMDetailMod pmDetail
                                                  ) throws DataAccessException;
    /**
     * Delete Individual KPI
     * @param userID
     * @param pmKey
     * @param pmDetail
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> deleteIndividualKPI(final String userID, final String pmKey, final PmsPMDetailMod pmDetail)
                        throws DataAccessException;
    /**
     * Submit PM Form
     * @param userID
     * @param emSeqNo
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> submitPM(final String userID,
                                       final Long empSeqNo,
                                       final Long pmHDRSeqNo)
                        throws DataAccessException;
    /**
     * Approve or Reject PM 
     * @param userID
     * @param emSeqNo
     * @param pmHDRSeqNo
     * @param approveReject
     * @param mgrLv1Comment
     * @param mgrLv2Comment
     * @return
     * @throws DataAccessException
     */
    public Map<String,Object> approvePM(final String userID,
                                        final Long emSeqNo,
                                        final Long pmHDRSeqNo,
                                        final String approveReject,
                                        final String mgrLv1Comment,
                                        final String mgrLv2Comment)
                        throws DataAccessException;
    /**
     * Get User Authorize
     * @param userID
     * @param empSeqNo
     * @param pmHDRSeqNo
     * @return
     * @throws DataAccessException
     */
    public PmsUserAuthorizeMod getUserAuthorize(final String userID,
                                                final Long empSeqNo,
                                                final Long pmHDRSeqNo) throws DataAccessException;
    
    /**
     * Insert a new attachment file for particular Sale Lead
     * @param pmHDRSeqNo
     * @param fileName
     * @param filePath
     * @param userID
     * @return
     * @throws DataAccessException
     */
    public Map<String, String> insertAttachFile(final Integer pmHDRSeqNo,
                                                        final String fileName,
                                                        final String filePath,
                                                        final String userID) throws DataAccessException;
    /**
     * Get attachment file for particular Sale Lead
     * @param pmHDRSeqNo
     * @param userID
     * @return
     * @throws DataAccessException
     */
    public List<PmsAttachFileMod> getAttachFileList(final Integer pmHDRSeqNo,
                                                                     final String userID) throws DataAccessException;
    /**
     * Delete a attach file from particular Sale Lead
     * @param uploadHDRSeqNo
     * @param uploadDelSeqNo
     * @param pmHDRSeqNo
     * @param userID
     * @return
     * @throws DataAccessException
     */
    public boolean deleteAttachFile(final Integer uploadHDRSeqNo,
                                            final Integer uploadDelSeqNo,
                                            final Integer pmHDRSeqNo,
                                            final String userID) throws DataAccessException;
}
