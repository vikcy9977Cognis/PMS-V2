/*-----------------------------------------------------------------------------------------------------------  
RcmAttributeSessionJdbcDao.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 23/06/10
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.common.RrcStandardMod;
import com.rclgroup.dolphin.web.model.rcm.RcmAttributeSessionMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class RcmAttributeSessionJdbcDao extends RrcStandardDao implements RcmAttributeSessionDao {
    private InsertStoreProcedure insertStoreProc;
    private SaveStoreProcedure saveStoreProc;
    private DeleteStoreProcedure deleteStoreProc;
    private ClearStoreProcedure clearStoreProc;
    
    public RcmAttributeSessionJdbcDao() {
        super();
    }
    
    protected void initDao() throws Exception {
        super.initDao();
        insertStoreProc = new InsertStoreProcedure(getJdbcTemplate());
        saveStoreProc = new SaveStoreProcedure(getJdbcTemplate());
        deleteStoreProc = new DeleteStoreProcedure(getJdbcTemplate());
        clearStoreProc = new ClearStoreProcedure(getJdbcTemplate());
    }
    
    public boolean insertAttributeSession(RrcStandardMod mod) throws DataAccessException {
        return insertStoreProc.insert(mod);
    }
    
    public boolean saveAttributeSession(RrcStandardMod mod) throws DataAccessException {
        return saveStoreProc.save(mod);
    }
    
    public boolean deleteAttributeSession(RrcStandardMod mod) throws DataAccessException {
        return deleteStoreProc.delete(mod);
    }
    
    public boolean clearAttributeSession(RrcStandardMod mod) throws DataAccessException {
        return clearStoreProc.clear(mod);
    }
    
    protected class InsertStoreProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME = "PCR_RUT_UTILITY.PRR_INS_ATTRIBUTE_SESSION";
        
        protected InsertStoreProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
            declareParameter(new SqlParameter("p_session", Types.VARCHAR));
            declareParameter(new SqlParameter("p_module", Types.VARCHAR));
            declareParameter(new SqlParameter("p_key_value", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_01", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_02", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_03", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_04", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_05", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_06", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_07", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_08", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_09", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_10", Types.VARCHAR));
            declareParameter(new SqlParameter("p_username", Types.VARCHAR));
            compile();
        }
        
        protected boolean insert(RrcStandardMod mod) {
            return insert(mod, mod);
        }
        
        protected boolean insert(final RrcStandardMod inputMod, RrcStandardMod outputMod) {
            boolean isSuccess = false; 
            if (inputMod instanceof RcmAttributeSessionMod && outputMod instanceof RcmAttributeSessionMod) {
                RcmAttributeSessionMod aInputMod = (RcmAttributeSessionMod) inputMod;
                
                Map inParameters = new HashMap();
                inParameters.put("p_session", RutString.nullToStr(aInputMod.getSessionId()));
                inParameters.put("p_module", RutString.nullToStr(aInputMod.getModuleCode()));
                inParameters.put("p_key_value", RutString.nullToStr(aInputMod.getKeyValue()));
                inParameters.put("p_value_01", RutString.nullToStr(aInputMod.getValue01()));
                inParameters.put("p_value_02", RutString.nullToStr(aInputMod.getValue02()));
                inParameters.put("p_value_03", RutString.nullToStr(aInputMod.getValue03()));
                inParameters.put("p_value_04", RutString.nullToStr(aInputMod.getValue04()));
                inParameters.put("p_value_05", RutString.nullToStr(aInputMod.getValue05()));
                inParameters.put("p_value_06", RutString.nullToStr(aInputMod.getValue06()));
                inParameters.put("p_value_07", RutString.nullToStr(aInputMod.getValue07()));
                inParameters.put("p_value_08", RutString.nullToStr(aInputMod.getValue08()));
                inParameters.put("p_value_09", RutString.nullToStr(aInputMod.getValue09()));
                inParameters.put("p_value_10", RutString.nullToStr(aInputMod.getValue10()));
                inParameters.put("p_username", RutString.nullToStr(aInputMod.getRecordAddUser()));
                
                /*System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_session = "+inParameters.get("p_session"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_module = "+inParameters.get("p_module"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_key_value = "+inParameters.get("p_key_value"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_01 = "+inParameters.get("p_value_01"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_02 = "+inParameters.get("p_value_02"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_03 = "+inParameters.get("p_value_03"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_04 = "+inParameters.get("p_value_04"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_05 = "+inParameters.get("p_value_05"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_06 = "+inParameters.get("p_value_06"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_07 = "+inParameters.get("p_value_07"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_08 = "+inParameters.get("p_value_08"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_09 = "+inParameters.get("p_value_09"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_value_10 = "+inParameters.get("p_value_10"));
                System.out.println("[RcmAttributeSessionJdbcDao][InsertStoreProcedure][insert]: p_username = "+inParameters.get("p_username"));*/
                
                Map outParameters = execute(inParameters);
                if (outParameters.size() > 0) {
                    isSuccess = true;
                }
            }
            return isSuccess;
        }
    }
    
    protected class SaveStoreProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME = "PCR_RUT_UTILITY.PRR_SAV_ATTRIBUTE_SESSION";
        
        protected SaveStoreProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
            declareParameter(new SqlParameter("p_session", Types.VARCHAR));
            declareParameter(new SqlParameter("p_module", Types.VARCHAR));
            declareParameter(new SqlParameter("p_key_value", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_01", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_02", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_03", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_04", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_05", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_06", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_07", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_08", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_09", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value_10", Types.VARCHAR));
            declareParameter(new SqlParameter("p_username", Types.VARCHAR));
            compile();
        }
        
        protected boolean save(RrcStandardMod mod) {
            return save(mod, mod);
        }
        
        protected boolean save(final RrcStandardMod inputMod, RrcStandardMod outputMod) {
            boolean isSuccess = false; 
            if (inputMod instanceof RcmAttributeSessionMod && outputMod instanceof RcmAttributeSessionMod) {
                RcmAttributeSessionMod aInputMod = (RcmAttributeSessionMod) inputMod;
                
                Map inParameters = new HashMap();
                inParameters.put("p_session", RutString.nullToStr(aInputMod.getSessionId()));
                inParameters.put("p_module", RutString.nullToStr(aInputMod.getModuleCode()));
                inParameters.put("p_key_value", RutString.nullToStr(aInputMod.getKeyValue()));
                inParameters.put("p_value_01", RutString.nullToStr(aInputMod.getValue01()));
                inParameters.put("p_value_02", RutString.nullToStr(aInputMod.getValue02()));
                inParameters.put("p_value_03", RutString.nullToStr(aInputMod.getValue03()));
                inParameters.put("p_value_04", RutString.nullToStr(aInputMod.getValue04()));
                inParameters.put("p_value_05", RutString.nullToStr(aInputMod.getValue05()));
                inParameters.put("p_value_06", RutString.nullToStr(aInputMod.getValue06()));
                inParameters.put("p_value_07", RutString.nullToStr(aInputMod.getValue07()));
                inParameters.put("p_value_08", RutString.nullToStr(aInputMod.getValue08()));
                inParameters.put("p_value_09", RutString.nullToStr(aInputMod.getValue09()));
                inParameters.put("p_value_10", RutString.nullToStr(aInputMod.getValue10()));
                inParameters.put("p_username", RutString.nullToStr(aInputMod.getRecordAddUser()));
                
                /*System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_session = "+inParameters.get("p_session"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_module = "+inParameters.get("p_module"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_key_value = "+inParameters.get("p_key_value"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_01 = "+inParameters.get("p_value_01"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_02 = "+inParameters.get("p_value_02"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_03 = "+inParameters.get("p_value_03"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_04 = "+inParameters.get("p_value_04"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_05 = "+inParameters.get("p_value_05"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_06 = "+inParameters.get("p_value_06"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_07 = "+inParameters.get("p_value_07"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_08 = "+inParameters.get("p_value_08"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_09 = "+inParameters.get("p_value_09"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_value_10 = "+inParameters.get("p_value_10"));
                System.out.println("[RcmAttributeSessionJdbcDao][SaveStoreProcedure][save]: p_username = "+inParameters.get("p_username"));*/
                
                Map outParameters = execute(inParameters);
                if (outParameters.size() > 0) {
                    isSuccess = true;
                }
            }
            return isSuccess;
        }
    }
 
    protected class DeleteStoreProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME = "PCR_RUT_UTILITY.PRR_DEL_ATTRIBUTE_SESSION";
        
        protected DeleteStoreProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
            declareParameter(new SqlParameter("p_session", Types.VARCHAR));
            declareParameter(new SqlParameter("p_module", Types.VARCHAR));
            compile();
        }
        
        protected boolean delete(RrcStandardMod mod) {
            return delete(mod, mod);
        }
        
        protected boolean delete(final RrcStandardMod inputMod, RrcStandardMod outputMod) {
            boolean isSuccess = false; 
            if (inputMod instanceof RcmAttributeSessionMod && outputMod instanceof RcmAttributeSessionMod) {
                RcmAttributeSessionMod aInputMod = (RcmAttributeSessionMod) inputMod;
                
                Map inParameters = new HashMap();
                inParameters.put("p_session", RutString.nullToStr(aInputMod.getSessionId()));
                inParameters.put("p_module", RutString.nullToStr(aInputMod.getModuleCode()));
                
                /*System.out.println("[RcmAttributeSessionJdbcDao][DeleteStoreProcedure][delete]: p_session = "+inParameters.get("p_session"));
                System.out.println("[RcmAttributeSessionJdbcDao][DeleteStoreProcedure][delete]: p_module = "+inParameters.get("p_module"));*/
                
                Map outParameters = execute(inParameters);
                if (outParameters.size() > 0) {
                    isSuccess = true;
                }
            }
            return isSuccess;
        }
    }
    
    protected class ClearStoreProcedure extends StoredProcedure {
        private static final String STORED_PROCEDURE_NAME = "PCR_RUT_UTILITY.PRR_CLR_ATTRIBUTE_SESSION";
        
        protected ClearStoreProcedure(JdbcTemplate jdbcTemplate) {
            super(jdbcTemplate, STORED_PROCEDURE_NAME);
            declareParameter(new SqlParameter("p_session", Types.VARCHAR));
            declareParameter(new SqlParameter("p_module", Types.VARCHAR));
            compile();
        }
        
        protected boolean clear(RrcStandardMod mod) {
            return clear(mod, mod);
        }
        
        protected boolean clear(final RrcStandardMod inputMod, RrcStandardMod outputMod) {
            boolean isSuccess = false; 
            if (inputMod instanceof RcmAttributeSessionMod && outputMod instanceof RcmAttributeSessionMod) {
                RcmAttributeSessionMod aInputMod = (RcmAttributeSessionMod) inputMod;
                
                Map inParameters = new HashMap();
                inParameters.put("p_session", RutString.nullToStr(aInputMod.getSessionId()));
                inParameters.put("p_module", RutString.nullToStr(aInputMod.getModuleCode()));
                
                /*System.out.println("[RcmAttributeSessionJdbcDao][ClearStoreProcedure][clear]: p_session = "+inParameters.get("p_session"));
                System.out.println("[RcmAttributeSessionJdbcDao][ClearStoreProcedure][clear]: p_module = "+inParameters.get("p_module"));*/
                
                Map outParameters = execute(inParameters);
                if (outParameters.size() > 0) {
                    isSuccess = true;
                }
            }
            return isSuccess;
        }
    }
       
}
