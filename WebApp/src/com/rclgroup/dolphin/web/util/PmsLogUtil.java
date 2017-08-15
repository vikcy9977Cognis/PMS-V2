package com.rclgroup.dolphin.web.util;

import java.sql.SQLException;

import java.util.Iterator;
import java.util.Map;

public class PmsLogUtil {
    public static boolean isEnableDumpParams = true;

    public PmsLogUtil() {
        super();
    }

    public static void dumpInputParams(Map<String, Object> mapParams) {
            if (isEnableDumpParams && mapParams != null) {
                Iterator<String> itor = mapParams.keySet().iterator();
                String key = "";
                String methodName =
                    Thread.currentThread().getStackTrace()[2].getMethodName();
                System.out.println("****** " + methodName + "() *****");
                while (itor.hasNext()) {
                    key = itor.next();
                    if (key.equals("P_I_PM_KEY")) {
                        String strTmpKey = (String) mapParams.get(key);
                       strTmpKey = strTmpKey.replaceAll(".", "x");
                        System.out.println(key + ":=" + strTmpKey);
                    } else {
                        System.out.println(key + ":=" + mapParams.get(key));
                    }
                }
            }
    }

    public static String getDBSQLExceptionMessage( String strErrorMsg) {

     //   String strErrorMsg = ex.getMessage();
    //    for (Throwable e : ex) {
    //        if (e instanceof SQLException) {
                // if (ex instanceof SQLException) {
                 if (strErrorMsg.contains("ORA-")) {
                     int pos = strErrorMsg.indexOf(":");
                     strErrorMsg = strErrorMsg.substring(pos + 1);
                      pos = strErrorMsg.indexOf(";");
                     strErrorMsg = strErrorMsg.substring(0, pos);

                 }
       //         strErrorMsg = e.getMessage();
       //     }
      //  }
        return strErrorMsg;
    }

}
