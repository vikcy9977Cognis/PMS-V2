/*-----------------------------------------------------------------------------------------------------------  
RutDatabase.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 03/06/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 24/08/09 WUT                       Added optimizeSQL method
02 21/07/11 API                       Added formate date
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import com.rclgroup.dolphin.web.common.RcmConstant;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class RutDatabase {
    public RutDatabase() {
        super();
    }

    /**
     * @param s
     * @return
     */
    public static Integer integerToDb(String s) {
        return integerToDb(s, 0);
    }

    /**
     * @param s
     * @return
     */
    public static BigDecimal bigDecimalToDb(String s) {
        return bigDecimalToDb(s, 0.00);
    }

    /**
     * @param s
     * @return
     */
    public static String stringToDb(String s) {
        return stringToDb(s, "");
    }
    
    /**
     * Returns follow as _default integer, if the input string is empty, otherwise the input string
     * @param s
     * @param _default
     * @return
     */
    public static Integer integerToDb(String s, int _default) {
        Integer outValue = null;
        try {
            outValue = (!RutString.isEmptyString(s)) ? new Integer(s) : new Integer(_default);    
        } catch(Exception e) {
            outValue = new Integer(_default);
        }
        return outValue;
    }
    
    /**
     * Returns follow as _default double, if the input string is empty, otherwise the input string
     * @param s
     * @param _default
     * @return
     */
    public static BigDecimal bigDecimalToDb(String s, double _default) {
        BigDecimal outValue = null;
        try {
            outValue = (!RutString.isEmptyString(s)) ? new BigDecimal(s) : new BigDecimal(_default);    
        } catch(Exception e) {
            outValue = new BigDecimal(_default);
        }
        return outValue;
    }
    
    /**
     * Returns follow as date datatype, if the input string is not empty, otherwise the return null
     * @param s
     * @return
     */
    public static Date dateToDb(String s) {
        Date outValue = null;
        try {
            outValue = (!RutString.isEmptyString(s)) ? RutDate.getSqlDateFormatFromYYYYMMDD(s) : null;    
        } catch(Exception e) {
            outValue = null;
        }
        return outValue;
    }
    
    /**
     * Returns follow as _default string, if the input string is empty, otherwise the input string
     * @param s
     * @param _default
     * @return
     */
    public static String stringToDb(String s, String _default) {
        return ((RutString.isEmptyString(s)) ? _default : s.trim());
    }

    /**
     * @param map
     * @param field
     * @return
     */
    public static int dbToInteger(Map map, String field) {
        return dbToInteger(map, field, 0);
    }
    
    /**
     * @param map
     * @param field
     * @return
     */
    public static long dbToLong(Map map, String field) {
        return dbToLong(map, field, 0);
    }

    /**
     * @param map
     * @param field
     * @return
     */
    public static double dbToDouble(Map map, String field) {
        return dbToDouble(map, field, 0.00);
    }

    /**
     * @param map
     * @param field
     * @return
     */
    public static String dbToString(Map map, String field) {
        return dbToString(map, field, "");
    }

    /**
     * @param map
     * @param field
     * @return
     */
    public static Timestamp dbToTimestamp(Map map, String field) {
        return dbToTimestamp(map, field, new Timestamp(Calendar.getInstance().getTime().getTime()));
    }
    
    /**
     * @param map
     * @param field
     * @return
     */
    public static Date dbToDate(Map map, String field) {
        return dbToDate(map, field, new Date(Calendar.getInstance().getTime().getTime()));
    }

    /**
     * @param map
     * @param field
     * @param _default
     * @return
     */
    public static int dbToInteger(Map map, String field, int _default) {
        int outValue = 0;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = ((Integer) map.get(field)).intValue();
            } else {
                outValue = _default;
            }
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }

    /**
     * @param map
     * @param field
     * @param _default
     * @return
     */
    public static long dbToLong(Map map, String field, long _default) {
        long outValue = 0;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = ((Long) map.get(field)).longValue();
            } else {
                outValue = _default;
            }
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }

    /**
     * @param map
     * @param field
     * @param _default
     * @return
     */
    public static double dbToDouble(Map map, String field, double _default) {
        double outValue = 0.00;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = ((Double) map.get(field)).doubleValue();
            } else {
                outValue = _default;
            }
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }

    /**
     * @param map
     * @param field
     * @param _default
     * @return
     */
    public static BigDecimal dbToBigDecimal(Map map, String field, double _default) {
        BigDecimal outValue = null;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = (BigDecimal) map.get(field);
            } else {
                outValue = new BigDecimal(_default);
            }
        } catch(Exception e) {
            outValue = new BigDecimal(_default);
        }
        return outValue;
    }

    /**
     * @param map
     * @param field
     * @param _default
     * @return
     */
    public static String dbToString(Map map, String field, String _default) {
        String outValue = null;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = (String) map.get(field);
                outValue = (RutString.isEmptyString(outValue)) ? _default : outValue;
            } else {
                outValue = _default;
            }
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }

    /**
     * @param map
     * @param field
     * @param _default
     * @return
     */
    public static Timestamp dbToTimestamp(Map map, String field, Timestamp _default) {
        Timestamp outValue = null;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = (Timestamp) map.get(field);
            } else {
                outValue = _default;
            }
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }
    
    public static Date dbToDate(Map map, String field, Date _default) {
        Date outValue = null;
        try {
            if (map != null && map.size() > 0 && !RutString.isEmptyString(field)) {
                outValue = (Date) map.get(field);
            } else {
                outValue = _default;
            }
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }
    
    public static String dbToStrInteger(Map map, String field, int _default) {
        return String.valueOf(dbToInteger(map, field, _default)).toString();
    }
    
    public static String dbToStrDouble(Map map, String field, double _default) {
        return String.valueOf(dbToDouble(map, field, _default)).toString();
    }
    
    public static String dbToStrBigDecimal(Map map, String field, double _default) {
        return String.valueOf(dbToBigDecimal(map, field, _default)).toString();
    }
    
    public static String dbToStrInteger(Map map, String field) {
        return dbToStrInteger(map, field, 0);
    }
    
    public static String dbToStrDouble(Map map, String field) {
        return dbToStrDouble(map, field, 0.00);
    }
    
    public static String dbToStrBigDecimal(Map map, String field) {
        return dbToStrBigDecimal(map, field, 0.00);
    }
    
    public static String dbToStrDate(Map map, String field) {
        String retValue = null;
        try {
            dbToDate(map, field, null);
        } catch(Exception e) {
            retValue = "";
        }
        return retValue;
    }
    
    /**
     * @param sql
     * @return
     */
    public static String upperCaseCommandSQL(String sql) {
        String sqlNew = "";
        if (!RutString.isEmptyString(sql)) {
            sqlNew = sql.trim();
            sqlNew = sqlNew.replaceAll(" from ", " FROM ");
            sqlNew = sqlNew.replaceAll(" From ", " FROM ");
            sqlNew = sqlNew.replaceAll(" distinct ", " DISTINCT ");
            sqlNew = sqlNew.replaceAll(" Distinct ", " DISTINCT ");
            sqlNew = sqlNew.replaceAll("select ", "SELECT ");
            sqlNew = sqlNew.replaceAll("Select ", "SELECT ");
            sqlNew = sqlNew.replaceAll("order by ", "ORDER BY ");
            sqlNew = sqlNew.replaceAll("order By ", "ORDER BY ");
            sqlNew = sqlNew.replaceAll("Order by ", "ORDER BY ");
            sqlNew = sqlNew.replaceAll("Order By ", "ORDER BY ");
            sqlNew = sqlNew.replaceAll("group by ", "GROUP BY ");
            sqlNew = sqlNew.replaceAll("group By ", "GROUP BY ");
            sqlNew = sqlNew.replaceAll("Group by ", "GROUP BY ");
            sqlNew = sqlNew.replaceAll("Group By ", "GROUP BY ");
            sqlNew = sqlNew.replaceAll("having ", "HAVING ");
            sqlNew = sqlNew.replaceAll("Having ", "HAVING ");
        }
        return sqlNew;
    }

    /**
     * @param sql
     * @return
     */
    public static String optimizeCountRecordSQL(String sql) {
        String sqlNew = "";
        String sqlQuery = upperCaseCommandSQL(sql);
        System.out.println("[RutDatabase][optimizeCountRecordSQL] sql:"+sqlQuery);
        
        if(!sql.equals("") || sql != null){
            int firstFrom = sqlQuery.indexOf(" FROM ");
            int firstDistinct = sqlQuery.indexOf(" DISTINCT ");
            
            StringBuffer sb = new StringBuffer();
            if (firstDistinct != -1 && firstDistinct < firstFrom) {
                sb.append("select count(1) as CNT_RECORD from ( "+sqlQuery+" )");
            } else {
                int lastSelect = sqlQuery.lastIndexOf("SELECT ");
                int lastOrder = sqlQuery.lastIndexOf("ORDER BY ");
                int lastGroupBy = sqlQuery.lastIndexOf("GROUP BY ");
                int lastHaving = sqlQuery.lastIndexOf("HAVING ");
                
                sb.append("select count(1) as CNT_RECORD ");
                if (lastHaving != -1 && lastHaving > lastSelect) {
                    sb.append("select count(1) as CNT_RECORD from ( "+sqlQuery+" )");
                } else if (lastGroupBy != -1 && lastGroupBy > lastSelect) {
                    sb.append("from " + sqlQuery.substring((firstFrom + " FROM ".length()), (lastGroupBy - 1)));
                } else if (lastOrder != -1 && lastOrder > lastSelect) {
                    sb.append("from " + sqlQuery.substring((firstFrom + " FROM ".length()), (lastOrder - 1)));
                } else {
                    sb.append("from " + sqlQuery.substring((firstFrom + " FROM ".length())));    
                }
            }        
            sqlNew = sb.toString();
            System.out.println("[RutDatabase][optimizeCountRecordSQL] sqlNew:"+sqlNew);
        }
        return sqlNew;
    }

    /**
     * @param sql
     * @param startRow
     * @param endRow
     * @return
     */
    public static String optimizeSQL(String sql, int startRow, int endRow) {
        String sqlNew = "";
        if (startRow == 0 && endRow == 0) {
            sqlNew = sql;
        } else {
            String strColumn = convertSqlStatementToStringColumn(sql);
            String strColumnName = convertStringColumnToString(strColumn);
            String strCoulmnNameDate = convertStringColumnToFormatDate(strColumnName);
            
            StringBuffer sb = new StringBuffer();
            sb.append("select "+strCoulmnNameDate+" ");
          //  sb.append("select "+strColumnName+" ");
            sb.append("from ( ");
            sb.append("     select rownum as RECORD_NUMBER, "+strColumnName+" ");
            sb.append("     from ( "+sql.toString()+" ) ");
            sb.append(" ) ");
            
            StringBuffer sbWhere = new StringBuffer();
            if (startRow != 0) {
                sbWhere.append(" and RECORD_NUMBER >= "+startRow+" ");
            }
            if (endRow != 0) {
                sbWhere.append(" and RECORD_NUMBER <= "+endRow+" ");
            }
            
            if (sbWhere.length() > 0) {
                sb.append("where "+sbWhere.substring(4, sbWhere.length()));
            }
            
            sqlNew = sb.toString();
        }
        return sqlNew;
    }
    
    public static String convertSqlStatementToStringColumn(String sqlStatement) {
        String sqlQuery = upperCaseCommandSQL(sqlStatement);
        int firstSelect = sqlQuery.indexOf("SELECT ");
        int firstFrom = sqlQuery.indexOf(" FROM ");
        
        return sqlQuery.substring((firstSelect + "SELECT ".length()), firstFrom);
    }
    
    public static List convertSqlStatementToListColumn(String sqlStatement) {
        String sqlColumn = convertSqlStatementToStringColumn(sqlStatement);
        
        return convertStringColumnToListColumn(sqlColumn);
    }
    
    public static List convertStringColumnToListColumn(String strColumn) {
        List lsColumn = new Vector();
        int firstSpace = 0;
        int firstPoint = 0;
        Stack stack = new Stack();
        String strOrigin = null;
        String[] openCodition = null;
        String[] closeCodition = null;
        String column = null;
        String cols[] = strColumn.split("\\,");
        for (int i=0;i<cols.length;i++) {
            strOrigin = cols[i].trim();
            
            // find "("
            openCodition = strOrigin.split("\\(");
            if (openCodition.length > 1) {
                for (int x=1;x<openCodition.length;x++) {
                    stack.push("PARENTH");
                }
            }
            
            // find ")"
            closeCodition = strOrigin.split("\\)");
            if (closeCodition.length > 1) {
                for (int x=1;x<closeCodition.length;x++) {
                    stack.pop();
                }
            }
            
            if (stack.size() == 0) {
                column = cols[i].trim();
                firstSpace = column.lastIndexOf(" ");
                column = column.substring(firstSpace + 1);
                
                firstPoint = column.lastIndexOf(".");
                column = column.substring(firstPoint + 1);
                
                // add column name to list column
                lsColumn.add(column);
            }
        }
        return lsColumn;
    }
    
    public static String convertStringColumnToString(String strColumn) {
        StringBuffer sb = new StringBuffer();
        int firstSpace = 0;
        int firstPoint = 0;
        Stack stack = new Stack();
        String strOrigin = null;
        String[] openCodition = null;
        String[] closeCodition = null;
        String column = null;
        String cols[] = strColumn.split("\\,");
        for (int i=0;i<cols.length;i++) {
            strOrigin = cols[i].trim();
            
            // find "("
            openCodition = strOrigin.split("\\(");
            if (openCodition.length > 1) {
                for (int x=1;x<openCodition.length;x++) {
                    stack.push("PARENTH");
                }
            }
            
            // find ")"
            closeCodition = strOrigin.split("\\)");
            if (closeCodition.length > 1) {
                for (int x=1;x<closeCodition.length;x++) {
                    stack.pop();
                }
            }
            
            if (stack.size() == 0) {
                column = cols[i].trim();
                firstSpace = column.lastIndexOf(" ");
                column = column.substring(firstSpace + 1);
                
                firstPoint = column.lastIndexOf(".");
                column = column.substring(firstPoint + 1);
                
                // add column name to sb
                sb.append(column + ",");
            }
        }
        
        String sql = "";
        if (sb.length() > 0) {
            sql = sb.substring(0, (sb.length() - 1));
        }
        return sql;
    }
    
   // Begin #02 
    public static String convertStringColumnToFormatDate(String strColumn) {
        StringBuffer sb = new StringBuffer();
        int firstSpace = 0;
        int firstPoint = 0;
        Stack stack = new Stack();
        String strOrigin = null;
        String[] openCodition = null;
        String[] closeCodition = null;
        String column = null;
        String cols[] = strColumn.split("\\,");
        for (int i=0;i<cols.length;i++) {
            strOrigin = cols[i].trim();
            
            // find "("
            openCodition = strOrigin.split("\\(");
            if (openCodition.length > 1) {
                for (int x=1;x<openCodition.length;x++) {
                    stack.push("PARENTH");
                }
            }
            
            // find ")"
            closeCodition = strOrigin.split("\\)");
            if (closeCodition.length > 1) {
                for (int x=1;x<closeCodition.length;x++) {
                    stack.pop();
                }
            }
            
            if (stack.size() == 0) {
                column = cols[i].trim();
                firstSpace = column.lastIndexOf(" ");
                column = column.substring(firstSpace + 1);
                
                firstPoint = column.lastIndexOf(".");
                column = column.substring(firstPoint + 1);
                
                // add column name to sb
                if(column.equalsIgnoreCase("ETA") 
                ||column.equalsIgnoreCase("ETD") 
                ||column.equalsIgnoreCase("ATA")
                ||column.equalsIgnoreCase("ATD")
                )
                
                {
                    sb.append("PCR_RUT_UTILITY.FR_RCM_GET_DATE_FORMAT_SHOW (" + column + ") as " + column + ",");
                }else{
                sb.append(column + ",");
                }
            }
        }
        
        String sql = "";
        if (sb.length() > 0) {
            sql = sb.substring(0, (sb.length() - 1));
        }
        return sql;
    }
    // End #02 
    
    
    
    public static String makeSqlStatementByParameter(String sqlStatement, String columnName, String columnFind, String conditionWild, String sortBy, String sortIn) {
        String sql = sqlStatement;
        
        System.out.println("[RutDatabase] [makeSqlStatementByParameter]: sqlStatement="+sqlStatement);
        System.out.println("[RutDatabase] [makeSqlStatementByParameter]: columnName="+columnName);
        System.out.println("[RutDatabase] [makeSqlStatementByParameter]: columnFind="+columnFind);
        
        // generate where clause
        if (!RutString.isEmptyString(columnName) && !RutString.isEmptyString(columnFind)) {
            
            if (sql.indexOf("[where") != -1) {
//                sql = sql.replaceFirst("\\[where", "where");
                sql = sql.replaceAll("\\[where", "where");
            } else if (sql.indexOf("[and") != -1) {
//                sql = sql.replaceFirst("\\[and", "and");
                sql = sql.replaceAll("\\[and", "and");
            }
            if (sql.indexOf(":columnFind]") != -1) {
//                sql = sql.replaceFirst(":columnFind\\]", ":columnFind");
                sql = sql.replaceAll(":columnFind\\]", ":columnFind");
            }
            
            if (RcmConstant.WILD_DEFAULT.equals(conditionWild)) {
//                sql = sql.replaceFirst(":columnName", columnName);
//                sql = sql.replaceFirst(":conditionWild", "like");  
//                sql = sql.replaceFirst(":columnFind", "'%"+columnFind+"%'");
                 sql = sql.replaceAll(":columnName", columnName);
                 sql = sql.replaceAll(":conditionWild", "like");  
                 sql = sql.replaceAll(":columnFind", "'%"+columnFind+"%'");
            } else {
//                sql = sql.replaceFirst(":columnName", columnName);
//                sql = sql.replaceFirst(":conditionWild", "=");  
//                sql = sql.replaceFirst(":columnFind", "'"+columnFind+"'");
                 sql = sql.replaceAll(":columnName", columnName);
                 sql = sql.replaceAll(":conditionWild", "=");  
                 sql = sql.replaceAll(":columnFind", "'"+columnFind+"'");
            }
            
        } else {
            //begin: remove where clause
            int firstClause = (sql.indexOf("[where") != -1) ? sql.indexOf("[where") : sql.indexOf("[and"); 
            int lastClause = sql.indexOf(":columnFind]");
                       
            if (firstClause != -1 && lastClause != -1) {
                sql = sql.substring(0, (firstClause - 1)) + sql.substring((lastClause + ":columnFind]".length()));
            }
            
        }
        
        // generate order clause
        if (!RutString.isEmptyString(sortBy) && !RutString.isEmptyString(sortIn)) {
            
            if (sql.indexOf("[order") != -1) {
//                sql = sql.replaceFirst("\\[order", "order");
                sql = sql.replaceAll("\\[order", "order");
            }
            if (sql.indexOf(":sortIn]") != -1) {
//                sql = sql.replace(":sortIn\\]", ":sortIn");
                sql = sql.replaceAll(":sortIn\\]", ":sortIn");
            }
            
            if (RcmConstant.SORT_ASCENDING.equals(sortIn)) {
//                sql = sql.replaceFirst(":sortBy", sortBy);
//                sql = sql.replaceFirst(":sortIn", "asc");  
                sql = sql.replaceAll(":sortBy", sortBy);
                sql = sql.replaceAll(":sortIn", "asc");  
            } else {
//                sql = sql.replaceFirst(":sortBy", sortBy);
//                sql = sql.replaceFirst(":sortIn", "desc");  
                sql = sql.replaceAll(":sortBy", sortBy);
                sql = sql.replaceAll(":sortIn", "desc"); 
            }
        } else {
            //begin: remove order clause
            int firstClause = sql.indexOf("[order"); 
            int lastClause = sql.indexOf(":sortIn]");
                        
            if (firstClause != -1 && lastClause != -1) {
                sql = sql.substring(0, (firstClause - 1)) + sql.substring((lastClause + ":sortIn]".length()));
            }
            //end: remove order clause
        }
        System.out.println("--makeSqlStatementByParameter-- :"+sql);
        return sql;
    }
    
    /*public static void main(String[] arg) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append("    vsd.BILL_TO_PARTY as TEST_COLUMN ");
        sql.append("    ,vsd.BILL_TO_PARTY_NAME as XXX ");
        sql.append("    ,vsd.BILL_NUM ");
        sql.append("    ,decode(vh.PORT_OF_DISCHARGE, 'SGSIN', 'I',         decode(vh.PORT_OF_LOAD, 'SGSIN', 'O', 'X')) as IB_OB_XT ");
        sql.append("    ,nvl(vh.REMARKS, '') as REMARKS ");
        sql.append("    ,'xx' NO_AS ");
        sql.append("    ,nvl(vsd.DESCRIPTION, xx, yy, zz) nono_AS ");
        sql.append("    ,nvl(vsd.DESCRIPTION, xx, yy, zz) as nono22_AS ");
        sql.append("from VR_FAR_STATEMENT_BY_DETAIL vsd ");
        sql.append("where rownum <= 100 ");
        int startRow = 101;
        int endRow = 200;
        
        String outValue = optimizeSQL(sql.toString(), startRow, endRow);
        System.out.println("outValue = "+outValue);
        
    }*/
    
}
