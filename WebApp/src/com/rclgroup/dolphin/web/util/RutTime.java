/*-----------------------------------------------------------------------------------------------------------  
RutTime.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07 
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import java.util.Calendar;

/**
 *    Time utility
 *
 */
public class RutTime {
    public RutTime() {
    }
    //=======================BEGIN TIME SUPPORT ==========================================

    /**
     * Returns a new calendar instance with the given time
     * @param hour
     * @param minute
     * @param second
     * @param millisec
     * @return
     */
    public static Calendar getTimeInstance(int hour, int minute, int second, 
                                           int millisec) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, 0, 1, hour, minute, second);
        cal.set(Calendar.MILLISECOND, millisec);
        return cal;
    }
    
    /**
     * Returns a new calendar with time 00:00:00.0000
     * @return
     */
    public static Calendar getNullTime() {
        return getTimeInstance(0, 0, 0, 0);
    }

    /**
     * Returns true, if the given time is 00:00:00.00000
     * @param cal
     * @return
     */
    public static boolean isNullTime(Calendar cal) {
        if (cal == null)
            return true;
        if (cal.get(Calendar.HOUR) != 0)
            return false;
        if (cal.get(Calendar.MINUTE) != 0)
            return false;
        if (cal.get(Calendar.SECOND) != 0)
            return false;
        if (cal.get(Calendar.MILLISECOND) != 0)
            return false;
        return true;
    }

    /**
     * Returns true, if the given date is 01.01.0001
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean timeEquals(Calendar cal1, Calendar cal2) {
        if (cal1.get(Calendar.HOUR) != cal2.get(Calendar.HOUR))
            return false;
        if (cal1.get(Calendar.MINUTE) != cal2.get(Calendar.MINUTE))
            return false;
        if (cal1.get(Calendar.SECOND) != cal2.get(Calendar.SECOND))
            return false;
        if (cal1.get(Calendar.MILLISECOND) != cal2.get(Calendar.MILLISECOND))
            return false;
        return true;
    }

    /** 
     * Returns the JDBC time string for a given calendar
     * @param cal A calendar time to be converted to a string
     * @return The time in the format HH:MM:SS.mmmm
     */
    public static String getJdbcTimeString(Calendar cal) {
        Integer iH = new Integer(100 + cal.get(Calendar.HOUR));
        Integer iM = new Integer(100 + cal.get(Calendar.MINUTE));
        Integer iS = new Integer(100 + cal.get(Calendar.SECOND));
        Integer iMs = new Integer(10000 + cal.get(Calendar.MILLISECOND));
        String sH = iH.toString().substring(1, 3);
        String sM = iM.toString().substring(1, 3);
        String sD = iS.toString().substring(1, 3);
        String sMs = iMs.toString().substring(1, 5);
        return sH + ":" + sM + ":" + sD + "." + sMs + "00000";
    }

    /**
     * Converts a calendar object into an sql time object
     * @param cal
     * @return
     */
    public static java.sql.Time getSqlTime(Calendar cal) {
        return java.sql.Time.valueOf(getJdbcTimeString(cal));
    }
    //=======================END TIME SUPPORT ==========================================

    //=======================BEGIN TIMESTAMP SUPPORT ==========================================

    /** 
     * Returns a new calendar instance with the given timestamp
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param millisec
     * @return
     */
    public static Calendar getTimestampInstance(int year, int month, int day, 
                                                int hour, int minute, 
                                                int second, int millisec) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, millisec);
        return cal;
    }

    /**
     * Returns a new calendar with timestamp 01-01-0001 00:00:00.0000
     * @return
     */
    public static Calendar getNullTimestamp() {
        return getTimestampInstance(1, 1, 1, 0, 0, 0, 0);
    }

    /**
     * Returns true, if the given timestamp is 01-01-0001 00:00:00.00000
     * @param cal
     * @return
     */
    public static boolean isNullTimestamp(Calendar cal) {
        if (cal == null)
            return true;
        if (cal.get(Calendar.YEAR) != 1)
            return false;
        if (cal.get(Calendar.MONTH) != 1)
            return false;
        if (cal.get(Calendar.DATE) != 1)
            return false;
        if (cal.get(Calendar.HOUR) != 0)
            return false;
        if (cal.get(Calendar.MINUTE) != 0)
            return false;
        if (cal.get(Calendar.SECOND) != 0)
            return false;
        if (cal.get(Calendar.MILLISECOND) != 0)
            return false;
        return true;
    }

    /**
     * Returns true, if the given timestamps are equal
     * @param cal1
     * @param cal2
     * @return
     */
    public static boolean timestampEquals(Calendar cal1, Calendar cal2) {
        if ((cal1 == null) && (cal2 == null))
            return true; //both are null
        if (cal1 == null)
            return false;
        if (cal2 == null)
            return false;
        if (cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR))
            return false;
        if (cal1.get(Calendar.MONTH) != cal2.get(Calendar.MONTH))
            return false;
        if (cal1.get(Calendar.DATE) != cal2.get(Calendar.DATE))
            return false;
        if (cal1.get(Calendar.HOUR) != cal2.get(Calendar.HOUR))
            return false;
        if (cal1.get(Calendar.MINUTE) != cal2.get(Calendar.MINUTE))
            return false;
        if (cal1.get(Calendar.SECOND) != cal2.get(Calendar.SECOND))
            return false;
        if (cal1.get(Calendar.MILLISECOND) != cal2.get(Calendar.MILLISECOND))
            return false;
        return true;
    }

    /**
     * Returns a Calendar object initialized with current timestamp
     * @return
     */
    public static Calendar now() {
        Calendar result = Calendar.getInstance();
        result.setTime(new java.util.Date(System.currentTimeMillis()));
        return result;
    }

    /**
     * Returns true, if the given string is a null string or an empty string
     * @param str
     * @return
     */
    public static boolean isNullString(String str) {
        if (str == null)
            return true;
        if (str.equals(""))
            return true;
        return false;
    }

    /**
     * Returns true, if both strings are null or empty
     * @param str1
     * @param str2
     * @return
     */
    public static boolean stringEquals(String str1, String str2) {
        if ((str1 == null) && (str2 == null))
            return true;
        if ((str1 == null) && (str2.equals("")))
            return true;
        if ((str2 == null) && (str1.equals("")))
            return true;
        if ((str1 == null) || (str2 == null))
            return false;
        if (str1.equals(str2))
            return true;
        return false;
    }

    /** 
     * Returns the JDBC timestamp string for a given calendar
     * @param cal A calendar timestamp to be converted to a string
     * @return The timestamp in the format YYYY-MM-DD HH:MM:SS.mmmm
     */
    public static String getJdbcTimestampString(Calendar cal) {
        String dt = RutDate.getJdbcDateString(cal);
        String tm = getJdbcTimeString(cal);
        return dt + " " + tm;
    }

    /**
     * @param cal
     * @return
     */
    public static java.sql.Timestamp getSqlTimestamp(Calendar cal) {
        return new java.sql.Timestamp((cal.getTime()).getTime());
    }

    /** 
     * Returns a Calendar object based on a given SQL timestamp
     * @param ts A sql.Timestamp to be converted to a Calendar
     * @return The Calendar
     */
    public static Calendar getCalTimestamp(java.sql.Timestamp ts) {
        if (ts == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(ts.getTime()));
        return cal;
    }

    /**
     * @param ots
     * @return
     */
    public static Calendar getCalTimestamp(Object ots) {
        if (ots == null) {
            return null;
        } else if (ots instanceof java.sql.Timestamp) {
            return getCalTimestamp((java.sql.Timestamp)ots);
        } else
            return null;
    }

    //=======================END TIMESTAMP SUPPORT ==========================================
}
