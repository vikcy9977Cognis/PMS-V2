/*-----------------------------------------------------------------------------------------------------------  
RcmUserJdbcDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 03/04/08
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.esm.EsmMenuTreeMod;
import com.rclgroup.dolphin.web.model.rcm.RcmUserMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class RcmUserJdbcDao extends RrcStandardDao implements RcmUserDao {
    public RcmUserJdbcDao() {
    }
    
    protected void initDao() throws Exception {
        super.initDao();
    }
    
    public boolean isValid(String prsnLogId) throws DataAccessException{
        boolean isValid = false;
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT PRSN_LOG_ID ");
        sql.append("FROM VR_RCM_USER ");
        sql.append("WHERE PRSN_LOG_ID = :prsnLogId "); 
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sql.toString(),
            Collections.singletonMap("prsnLogId", prsnLogId));
        if(rs.next()) {
            isValid = true;
        } else { 
            isValid = false;
        }
        return true;
    }
    
    public RcmUserMod findByKeyPrsnLogId(String prsnLogId) throws DataAccessException {
        RcmUserMod userMod = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select PRSN_LOG_ID ");
        sql.append("      ,PERSON_CD ");
        sql.append("      ,DESCR ");
        sql.append("      ,PIN ");
        sql.append("      ,PSWD_EXP ");
        sql.append("      ,PSWD_EXP_DAYS ");
        sql.append("      ,CHNG_PSWD ");
        sql.append("      ,INHT_ALLWD ");
        sql.append("      ,PASS_EFF_DT ");
        sql.append("      ,PASS_EXP_DT ");
        sql.append("      ,FUNC_LVL ");
        sql.append("      ,FSC_CODE ");
        sql.append("      ,FSC_NAME ");
        sql.append("      ,FSC_LVL1 "); 
        sql.append("      ,FSC_LVL2 ");
        sql.append("      ,FSC_LVL3 ");
        sql.append("      ,DEPT_CODE ");
        sql.append("      ,TITLE_CODE ");
        sql.append("      ,LOGIN_COUNT ");
        sql.append("      ,ORG_CODE ");
        sql.append("      ,ORG_TYPE ");
        sql.append("      ,LAST_PASSWORD_CHANGED_DATE ");
        sql.append("      ,SHARE_FSC ");
        sql.append("      ,IS_LOCKED ");
        sql.append("      ,IS_GROUP_AUTH ");
        sql.append("      ,EMAIL_ID1 ");
        sql.append("      ,EMAIL_ID2 ");
        sql.append("      ,RCST ");
        sql.append("      ,FSC_DATE_FORMAT ");
        sql.append("      ,ADDR1 ");
        sql.append("      ,ADDR2 ");
        sql.append("      ,ADDR3 ");
        sql.append("      ,ADDR4 ");
        sql.append("      ,CITY ");
        sql.append("      ,STATE ");
        sql.append("      ,ZIP_POSTAL1 ");
        sql.append("      ,PH_NO ");
        sql.append("      ,FAX_NO ");
        sql.append("      ,SMCURR ");
        sql.append("      ,SCCURR ");
        sql.append("      ,VENDOR_NAME ");
        sql.append("      ,LANGUAGE ");
        sql.append("      ,TIMEZONE ");
        sql.append("      ,COUNTRY ");
        sql.append("      ,PORT ");
        sql.append("from RCLAPPS.VR_RCM_USER ");
        sql.append("where PRSN_LOG_ID = :prsnLogId ");   
        try{
            userMod = (RcmUserMod)getNamedParameterJdbcTemplate().queryForObject(
                   sql.toString(),
                   Collections.singletonMap("prsnLogId", prsnLogId),
                   new RowMapper(){
                       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                           RcmUserMod userMod = new RcmUserMod();
                           userMod.setPrsnLogId(RutString.trim(rs.getString("PRSN_LOG_ID")));
                           userMod.setPersonCd(RutString.trim(rs.getString("PERSON_CD")));
                           userMod.setDescr(RutString.trim(rs.getString("DESCR")));
                           userMod.setPin(RutString.trim(rs.getString("PIN")));
                           userMod.setPswdExp(RutString.trim(rs.getString("PSWD_EXP")));
                           userMod.setPswdExpDays(RutString.trim(rs.getString("PSWD_EXP_DAYS")));
                           userMod.setChngPswd(RutString.trim(rs.getString("CHNG_PSWD")));
                           userMod.setInhtAllwd(RutString.trim(rs.getString("INHT_ALLWD")));
                           userMod.setPassEffDt(RutString.trim(rs.getString("PASS_EFF_DT")));
                           userMod.setPassExpDt(RutString.trim(rs.getString("PASS_EXP_DT")));
                           userMod.setFuncLvl(RutString.trim(rs.getString("FUNC_LVL")));
                           userMod.setFscCode(RutString.trim(rs.getString("FSC_CODE")));
                           userMod.setFscName(RutString.trim(rs.getString("FSC_NAME")));
                           userMod.setFscLvl1(RutString.trim(rs.getString("FSC_LVL1")));
                           userMod.setFscLvl2(RutString.trim(rs.getString("FSC_LVL2")));
                           userMod.setFscLvl3(RutString.trim(rs.getString("FSC_LVL3")));
                           userMod.setDeptCode(RutString.trim(rs.getString("DEPT_CODE")));
                           userMod.setTitleCode(RutString.trim(rs.getString("TITLE_CODE")));
                           userMod.setLoginCount(RutString.trim(rs.getString("LOGIN_COUNT")));
                           userMod.setOrgCode(RutString.trim(rs.getString("ORG_CODE")));
                           userMod.setOrgType(RutString.trim(rs.getString("ORG_TYPE")));
                           userMod.setLastPasswordChangedDate(RutString.trim(rs.getString("LAST_PASSWORD_CHANGED_DATE")));
                           userMod.setShareFsc(RutString.trim(rs.getString("SHARE_FSC")));
                           userMod.setIsLocked(RutString.trim(rs.getString("IS_LOCKED")));
                           userMod.setIsGroupAuth(RutString.trim(rs.getString("IS_GROUP_AUTH")));
                           userMod.setEmailId1(RutString.trim(rs.getString("EMAIL_ID1")));
                           userMod.setEmailId2(RutString.trim(rs.getString("EMAIL_ID2")));
                           userMod.setRcst(RutString.trim(rs.getString("RCST")));
                           userMod.setFscDateFormat(RutString.trim(rs.getString("FSC_DATE_FORMAT")));
                           userMod.setAddr1(RutString.trim(rs.getString("ADDR1")));
                           userMod.setAddr3(RutString.trim(rs.getString("ADDR2")));
                           userMod.setAddr3(RutString.trim(rs.getString("ADDR3")));
                           userMod.setAddr4(RutString.trim(rs.getString("ADDR4")));
                           userMod.setCity(RutString.trim(rs.getString("CITY")));
                           userMod.setState(RutString.trim(rs.getString("STATE")));
                           userMod.setZipPostal1(RutString.trim(rs.getString("ZIP_POSTAL1")));
                           userMod.setPhNo(RutString.trim(rs.getString("PH_NO")));
                           userMod.setFaxNo(RutString.trim(rs.getString("FAX_NO")));
                           userMod.setSmcurr(RutString.trim(rs.getString("SMCURR")));
                           userMod.setSccurr(RutString.trim(rs.getString("SCCURR")));
                           userMod.setVendorName(RutString.trim(rs.getString("VENDOR_NAME")));
                           userMod.setLanguage(RutString.trim(rs.getString("LANGUAGE")));
                           userMod.setTimeZone(RutString.trim(rs.getString("TIMEZONE")));
                           userMod.setCountry(RutString.trim(rs.getString("COUNTRY")));  
                           userMod.setPort(RutString.trim(rs.getString("PORT")));  
                           return userMod;
                       }
                   });
            System.out.println("Vikas:- "+userMod );
        }catch (EmptyResultDataAccessException e) {
            userMod = null;
        }
        return userMod;
    }
    
    public List getMenuAuthentication(String userId) throws DataAccessException{
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT LEVEL_ID ");
        sql.append("      ,MENU_ID ");
        sql.append("      ,PROGRAM_ID ");
        sql.append("      ,SEQ_ID ");
        sql.append("      ,DESCR ");
        sql.append("      ,WIN_NM ");
        sql.append("      ,LIB_NM ");
        sql.append("      ,MODULE_NM ");
        sql.append("      ,MENU_TYPE ");
        sql.append("      ,PROG_SOURCE ");
        sql.append("      ,IMAGE_NM ");
        sql.append("      ,URL_NM ");
        sql.append("      ,NVL( ");
        sql.append("           ( ");
        sql.append("            SELECT MAX(DECODE(MENU_ITEM_VISIBLE,'Y','V','A')) "); 
        sql.append("                || MAX(DECODE(SC_CREATE,'Y','C','A')) "); 
        sql.append("                || MIN(DECODE(SC_READ,'Y','R','A')) ");
        sql.append("                || MAX(DECODE(SC_UPDATE,'Y','U','A')) "); 
        sql.append("                || MAX(DECODE(SC_DELETE,'Y','D','A')) ");  
        sql.append("            FROM SC_ROLE_PRIV_TEMPL ");  
        sql.append("            WHERE ROLE_CD IN ( ");
        sql.append("                              SELECT ROLE_CD "); 
        sql.append("                              FROM PR_PRSN_ROLE "); 
        sql.append("                              WHERE PRSN_LOG_ID = :userId ");
        sql.append("                             ) ");
        sql.append("              AND MENU_ID = MENU_TREE.MENU_ID "); 
        sql.append("              AND PROGRAM_ID = MENU_TREE.PROGRAM_ID ");
        sql.append("           ) ");
        sql.append("           ,'NEW' ");
        sql.append("          ) AS AUTHORITY ");
        sql.append("FROM VR_RCM_MENU_TREE MENU_TREE ");   
        ////
        System.out.println("[RcmUserJdbcDao][getMenuAuthentication]:sql: ["+sql.toString()+"]");
        System.out.println("[RcmUserJdbcDao][getMenuAuthentication]:userId: "+userId);
        ////
        return getNamedParameterJdbcTemplate().query(
                   sql.toString(),
                   Collections.singletonMap("userId", userId),
                   new RowMapper(){
                       public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                           EsmMenuTreeMod menuTreeMod = new EsmMenuTreeMod();
                           menuTreeMod.setLevelId(RutString.nullToStr(rs.getString("LEVEL_ID")));
                           menuTreeMod.setMenuId(RutString.nullToStr(rs.getString("MENU_ID")));
                           menuTreeMod.setProgramId(RutString.nullToStr(rs.getString("PROGRAM_ID")));
                           menuTreeMod.setSeqId(RutString.nullToStr(rs.getString("SEQ_ID")));
                           menuTreeMod.setDescr(RutString.nullToStr(rs.getString("DESCR")));
                           menuTreeMod.setWinNm(RutString.nullToStr(rs.getString("WIN_NM")));
                           menuTreeMod.setLibNm(RutString.nullToStr(rs.getString("LIB_NM")));
                           menuTreeMod.setModuleNm(RutString.nullToStr(rs.getString("MODULE_NM")));
                           menuTreeMod.setMenuType(RutString.nullToStr(rs.getString("MENU_TYPE")));
                           menuTreeMod.setProgSource(RutString.nullToStr(rs.getString("PROG_SOURCE")));             
                           menuTreeMod.setImageNm(RutString.nullToStr(rs.getString("IMAGE_NM")));
                           menuTreeMod.setUrlNm(RutString.nullToStr(rs.getString("URL_NM")));
                           String authority = RutString.nullToStr(rs.getString("AUTHORITY"));
                           if(authority == null || authority.equals("NEW")){
                               menuTreeMod.setMenuItemVisible("N");
                               menuTreeMod.setScCreate("N");
                               menuTreeMod.setScRead("N");
                               menuTreeMod.setScUpdate("N");
                               menuTreeMod.setScDelete("N");
                               menuTreeMod.getClass();
                               menuTreeMod.setDataStatus(0);
                               menuTreeMod.setIsDeleteRow(false);
                           }else{
                               if(authority.indexOf("V") != -1)
                                   menuTreeMod.setMenuItemVisible("Y");
                               if(authority.indexOf("C") != -1)
                                   menuTreeMod.setScCreate("Y");
                               if(authority.indexOf("U") != -1)
                                   menuTreeMod.setScUpdate("Y");
                               if(authority.indexOf("D") != -1)
                                   menuTreeMod.setScDelete("Y");
                               if(authority.indexOf("V") != -1 && authority.indexOf("U") == -1)
                                   menuTreeMod.setScRead("Y");
                               else if(authority.indexOf("R") != -1)
                                   menuTreeMod.setScRead("Y");
                               menuTreeMod.getClass();
                               menuTreeMod.setDataStatus(1);
                               menuTreeMod.setIsDeleteRow(false);
                           }
                           return menuTreeMod;
                       }
                   });
    }
}

