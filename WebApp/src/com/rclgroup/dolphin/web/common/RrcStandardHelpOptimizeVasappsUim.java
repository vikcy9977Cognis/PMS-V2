/*
 * -----------------------------------------------------------------------------
 * RrcStandardHelpOptimizeVasappsUim.java
 * -----------------------------------------------------------------------------
 * Copyright RCL Public Co., Ltd. 2007 
 * -----------------------------------------------------------------------------
 * Author Dhruv Parekh 06/09/2012
 * ------- Change Log ----------------------------------------------------------
 * ##   DD/MM/YY    -User-      -TaskRef-      -Short Description  
 * -----------------------------------------------------------------------------
 */
 
package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.dao.rcm.RcmDefaultHelpDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmDefaultVasappsHelpDao;

import com.rclgroup.dolphin.web.model.rcm.RcmColumnNameShowMod;
import com.rclgroup.dolphin.web.util.RutDatabase;
import com.rclgroup.dolphin.web.util.RutFormatting;
import com.rclgroup.dolphin.web.util.RutPage;

import com.rclgroup.dolphin.web.util.RutString;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

public abstract class RrcStandardHelpOptimizeVasappsUim extends RrcStandardHelpUim {
    private String titleName;
    private String titleNameShow;
    private List columnName;
    private HashMap columnNameShow;
    private String[] arrReturnValue;
    private String pageTarget;
    
    private String service;
    private String sqlStatement;
    private String sqlStatementForUse;
    private RcmDefaultVasappsHelpDao rcmDefaultVasappsHelpDao;
    
    public RrcStandardHelpOptimizeVasappsUim() {
        this.titleName = "";
        this.titleNameShow = "";
        this.columnName = new Vector();
        this.columnNameShow = new HashMap();
        this.arrReturnValue = null;
        this.pageTarget = RcmConstant.MISC_PAGE_URL + "/RcmStandardHelpOptimizeVasappsScn.jsp";
        this.service = "";
        this.sqlStatement = "";
        this.sqlStatementForUse = "";
    }
    
    public void setRcmDefaultVasappsHelpDao(RcmDefaultVasappsHelpDao rcmDefaultVasappsHelpDao) {
        this.rcmDefaultVasappsHelpDao = rcmDefaultVasappsHelpDao;
    }
    
    public List getListForHelpScreen(String find,String search,String wild,String type) {
        return null;
    }
    
    public void createRutPage() {        
        this.sqlStatementForUse = RutDatabase.makeSqlStatementByParameter(sqlStatement, this.getSearch(), this.getFind(), this.getWild(), this.getSortBy(), this.getSortIn());
        
        rutPage = (rutPage == null) ? new RutPage() : rutPage;
        String sqlForCount = RutDatabase.optimizeCountRecordSQL(sqlStatementForUse);
        int size = rcmDefaultVasappsHelpDao.findCountForHelpScreen(sqlForCount);
        
        String sqlForList = RutDatabase.optimizeSQL(sqlStatementForUse, rutPage.calculateRowAt(), rutPage.calculateRowTo());
        List list = rcmDefaultVasappsHelpDao.findListForHelpScreen(sqlForList, columnName, columnNameShow);
        list = (list == null) ? new ArrayList() : list;
        
        rutPage = new RutPage(list, size, this.VERSION_02);
    }
    
    public void queryDataByRutPage() {
        String sqlForList = RutDatabase.optimizeSQL(sqlStatementForUse, rutPage.calculateRowAt(), rutPage.calculateRowTo());
        List list = rcmDefaultVasappsHelpDao.findListForHelpScreen(sqlForList, columnName, columnNameShow);
        list = (list == null) ? new ArrayList() : list;
        
        rutPage = (rutPage == null) ? new RutPage() : rutPage;
        rutPage.setItems(list);
    }
    
    public List queryDataByParameter() {
        this.sqlStatementForUse = RutDatabase.makeSqlStatementByParameter(sqlStatement, this.getSearch(), this.getFind(), this.getWild(), this.getSortBy(), this.getSortIn());
        
        String sqlForList = RutDatabase.optimizeSQL(sqlStatementForUse, 0, 0);
        List list = rcmDefaultVasappsHelpDao.findListForHelpScreen(sqlForList, columnName, columnNameShow);
        list = (list == null) ? new ArrayList() : list;
        
        return list;
    }
    
    public abstract void manageRequestParameter(HttpServletRequest request);

    public abstract String findTitleNameShowByType(String type);
    
    public abstract String findSqlStatementByType(String type);
    
    public abstract HashMap findColumnNameShowByType(String type);
    
    public abstract String[] findReturnValueByType(String type);
    
    public String displayColumnValue(String value, RcmColumnNameShowMod rcmColumnNameShowMod) {
        String outValue = null;
        try {
            if ("STRING".equals(rcmColumnNameShowMod.getDataType())) {
                outValue = value;
            } else if ("INTEGER".equals(rcmColumnNameShowMod.getDataType())) {
                outValue = value;
            } else if ("DOUBLE".equals(rcmColumnNameShowMod.getDataType()) && !RutString.isEmptyString(rcmColumnNameShowMod.getFormat())) {
                outValue = RutFormatting.getDecimalFormat(value, rcmColumnNameShowMod.getFormat());
            } else if ("FUNCTION".equals(rcmColumnNameShowMod.getDataType()) && !RutString.isEmptyString(rcmColumnNameShowMod.getClassName()) && !RutString.isEmptyString(rcmColumnNameShowMod.getMethodName())) {
                String className = rcmColumnNameShowMod.getClassName();
                String methodName = rcmColumnNameShowMod.getMethodName();
                try {
                    Class dbClass = Class.forName(className);
                    Method subMethod = dbClass.getMethod(methodName, new Class[] {String.class});
                    outValue = (String) subMethod.invoke(dbClass.newInstance(), new String[] {value});
                } catch(Exception e) {
                    e.printStackTrace();
                    outValue = value;
                }
            } else {
                outValue = value;
            }
        } catch(Exception e) {
            e.printStackTrace();
            outValue = value;
        }
        outValue = (outValue == null) ? "" : outValue;
        return outValue;
    }
    
    public int displaySeqNo(int count, RutPage rutPage) {
        int seqNo = count;
        if (rutPage != null) {
            try {
                seqNo = count + ((rutPage.getPageNo() - 1) * rutPage.getMaxItemsPerPage()) + 1;
            } catch(Exception e) {
                e.printStackTrace();
                seqNo = count + 1;
            }
        }
        return seqNo;
    }
    
    public String makeReturnValueByElement(HashMap hValue) {
        String returnValue = "";
        
        if (hValue != null && hValue.size() > 0 && arrReturnValue != null && arrReturnValue.length > 0) { //if 1
            String value = null;
            for (int i=0;i<arrReturnValue.length;i++) { //for 1
                
                if (!RutString.isEmptyString(arrReturnValue[i])) { //if 2
                    value = (String) hValue.get(arrReturnValue[i]);
                } else {
                    value = "";
                } //end if 2
                value = (value == null) ? "" : value.trim();
                
                returnValue += value + "|";
                
            } //end for 1
        } //end if 1
        
        if (returnValue.length() > 0) {
            returnValue = returnValue.substring(0, returnValue.length() - 1);
        }
        
        return returnValue;
    }
    //end: function
    
     public String getService() {
         return service;
     }

     public void setService(String service) {
         this.service = service;
     }
     
     public String getSqlStatement() {
         return sqlStatement;
     }

     public void setSqlStatement(String sqlStatement) {
         this.sqlStatement = sqlStatement;
     }
     
     public String getSqlStatementForUse() {
         return sqlStatementForUse;
     }

     public void setSqlStatementForUse(String sqlStatementForUse) {
         this.sqlStatementForUse = sqlStatementForUse;
     }
     
     public String getTitleName() {
         return titleName;
     }

     public void setTitleName(String titleName) {
         this.titleName = titleName;
     }
     
     public String getTitleNameShow() {
         return titleNameShow;
     }

     public void setTitleNameShow(String titleNameShow) {
         this.titleNameShow = titleNameShow;
     }
     
     public String getPageTarget() {
         return pageTarget;
     }

     public void setPageTarget(String pageTarget) {
         this.pageTarget = pageTarget;
     }
     
     public List getColumnName() {
         return columnName;
     }

     public void setColumnName(List columnName) {
         this.columnName = columnName;
     }

     public HashMap getColumnNameShow() {
         return columnNameShow;
     }

     public void setColumnNameShow(HashMap columnNameShow) {
         this.columnNameShow = columnNameShow;
     }
     
     public String[] getArrReturnValue() {
         return arrReturnValue;
     }
     
     public void setArrReturnValue(String[] arrReturnValue) {
         this.arrReturnValue = arrReturnValue;
     }
}
