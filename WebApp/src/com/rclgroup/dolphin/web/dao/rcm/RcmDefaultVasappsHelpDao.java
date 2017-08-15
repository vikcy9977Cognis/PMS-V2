/*
 * -----------------------------------------------------------------------------
 * RcmDefaultVasappsHelpDao.java
 * -----------------------------------------------------------------------------
 * Copyright RCL Public Co., Ltd. 2007 
 * -----------------------------------------------------------------------------
 * Author Dhruv Parekh 06/09/2012
 * ------- Change Log ----------------------------------------------------------
 * ##   DD/MM/YY    -User-      -TaskRef-      -Short Description  
 * -----------------------------------------------------------------------------
 */
 
package com.rclgroup.dolphin.web.dao.rcm;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface RcmDefaultVasappsHelpDao {
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
