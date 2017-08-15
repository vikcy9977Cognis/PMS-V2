/*--------------------------------------------------------
RcmDefaultHelpJdbcDao.java
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2009
--------------------------------------------------------
Author Wuttitorn Wuttijiaranai 25/08/2009
- Change Log--------------------------------------------
## DD/MM/YY -User- -TaskRef- -ShortDescription
--------------------------------------------------------
*/
package com.rclgroup.dolphin.web.dao.rcm;

import com.rclgroup.dolphin.web.common.RrcStandardDao;
import com.rclgroup.dolphin.web.model.rcm.RcmColumnNameShowMod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;


public class RcmDefaultHelpJdbcDao extends RrcStandardDao implements RcmDefaultHelpDao{
    
    protected void initDao() throws Exception {
        super.initDao();
    }
    
    public List findListForHelpScreen(String sqlStatement, List columnName, HashMap columnNameShow) {
        ArrayList list = new ArrayList();
        HashMap bean = null;    
        String column = null;
        String columnValue = null;
        RcmColumnNameShowMod rcmColumnNameShowMod = null;
        
        SqlRowSet rs = getNamedParameterJdbcTemplate().queryForRowSet(sqlStatement, new HashMap());
        while (rs.next()) {
            bean = new HashMap();    
            
            column = null;
            rcmColumnNameShowMod = null;
            for (int i=0;i<columnName.size();i++) {
                column = (String) columnName.get(i);
                rcmColumnNameShowMod = (RcmColumnNameShowMod) columnNameShow.get(column);
                
                if (rcmColumnNameShowMod != null) {
                    if ("STRING".equals(rcmColumnNameShowMod.getDataType()) || "FUNCTION".equals(rcmColumnNameShowMod.getDataType())) {
                        columnValue = rs.getString(column);
                    } else if ("INTEGER".equals(rcmColumnNameShowMod.getDataType())) {
                        columnValue = String.valueOf(rs.getInt(column));
                    } else if ("DOUBLE".equals(rcmColumnNameShowMod.getDataType())) {
                        columnValue = String.valueOf(rs.getDouble(column));
                    }
                } else {
                    columnValue = rs.getString(column);
                }
                
                bean.put(column, columnValue);
            }
            
            list.add(bean);
        }
        
        return list;
    }
    
    public int findCountForHelpScreen(String sqlStatement) {
        return getNamedParameterJdbcTemplate().queryForInt(sqlStatement, new HashMap());
    }

}
