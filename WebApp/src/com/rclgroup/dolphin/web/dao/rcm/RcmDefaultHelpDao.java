/*--------------------------------------------------------
RcmDefaultHelpDao.java
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2009
--------------------------------------------------------
Author Wuttitorn Wuttijiaranai 25/08/2009
- Change Log--------------------------------------------
## DD/MM/YY -User- -TaskRef- -ShortDescription
--------------------------------------------------------
*/
package com.rclgroup.dolphin.web.dao.rcm;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;


public interface RcmDefaultHelpDao {

    /**
     * @param sqlStatement
     * @param columnName
     * @param columnNameShow
     * @return
     * @throws DataAccessException
     */
    public List findListForHelpScreen(String sqlStatement, List columnName, HashMap columnNameShow) throws DataAccessException;

    /**
     * @param sqlStatement
     * @return
     * @throws DataAccessException
     */
    public int findCountForHelpScreen(String sqlStatement) throws DataAccessException;
    
}
