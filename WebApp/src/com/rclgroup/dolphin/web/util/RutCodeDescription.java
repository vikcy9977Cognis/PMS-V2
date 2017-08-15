/*-----------------------------------------------------------------------------------------------------------  
RutCodeDescription.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 21/05/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-       -Short Description  
01 25/05/11 NIP        PD_CR_240211-02 Cargo Manifest for Phillipines.
02 09/03/12 NIP        CR_53,087       Delivery Order Print for FSC CMB of SRI LANKA
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import com.rclgroup.dolphin.web.common.RcmConstant;


public class RutCodeDescription {
    public RutCodeDescription() {
        super();
    }
    
    /**
      * get the description of flag
      * @param flag
      * @return description of flag
      */
    public static String getFlagYesNoDesc(String flag) {
        if (RcmConstant.FLAG_YES.equals(flag)) {
            return "Yes";
        } else if (RcmConstant.FLAG_NO.equals(flag)) {
            return "No";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of COC/SOC
     * @param cocSoc
     * @return description of COC/SOC
     */
    public static String getCocSocDesc(String cocSoc) {
        if (RcmConstant.BOOKING_TYPE_COC.equals(cocSoc)) {
            return "COC";    
        } else if (RcmConstant.BOOKING_TYPE_SOC.equals(cocSoc)) {
            return "SOC";
        } else if (RcmConstant.BOOKING_TYPE_ALL.equals(cocSoc)) {
            return "All";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of direction by direction code
     * @param directionCode
     * @return description of direction
     */
    public static String getDirectionDesc(String directionCode) {
        if (RcmConstant.DIRECTION_DEFAULT.equals(directionCode)) {
            return "";    
        } else if (RcmConstant.DIRECTION_NORTH.equals(directionCode)) {
            return "North";
        } else if (RcmConstant.DIRECTION_SOUTH.equals(directionCode)) {
            return "South";
        } else if (RcmConstant.DIRECTION_EAST.equals(directionCode)) {
            return "East";
        } else if (RcmConstant.DIRECTION_WEST.equals(directionCode)) {
            return "West";
        } else if (RcmConstant.DIRECTION_ROUND.equals(directionCode)) {
            return "Round";
        } else if (RcmConstant.DIRECTION_NORTH_EAST.equals(directionCode)) {
            return "North East";
        } else if (RcmConstant.DIRECTION_NORTH_WEST.equals(directionCode)) {
            return "North West";
        } else if (RcmConstant.DIRECTION_SOUTH_EAST.equals(directionCode)) {
            return "South East";
        } else if (RcmConstant.DIRECTION_SOUTH_WEST.equals(directionCode)) {
            return "South West";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of record status
     * @param recordStatusCode
     * @return description of record status
     */
    public static String getStatusDesc(String recordStatusCode) {
        if (RcmConstant.RECORD_STATUS_ACTIVE.equals(recordStatusCode)) {
            return "Active";    
        } else if (RcmConstant.RECORD_STATUS_SUSPENDED.equals(recordStatusCode)) {
            return "Suspended";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of B/L Id
     * @param blId
     * @return description of B/L Id
     */
    public static String getBlIdDesc(String blId) {
        if ("F".equals(blId)) {
            return "Forwarder";
        } else if ("T".equals(blId)) {
            return "Combined Transport";
        } else if ("E".equals(blId)) {
            return "eBL";
        } else if ("P".equals(blId)) {
            return "Split B/L";
        } else if ("U".equals(blId)) {
            return "Multi-Part B/L";
        } else if ("W".equals(blId)) {
            return "Switch B/L";
        } else if ("C".equals(blId)) {
            return "Combined Booking";
        } else if ("X".equals(blId)) {
            return "Split + Multi-Part B/L";
        } else if ("H".equals(blId)) {
            return "Hitchment B/L";
        } else if ("R".equals(blId)) {
            return "RMM B/L";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of B/L status
     * @param blStatus
     * @return description of B/L status
     */
    public static String getBlStatusDesc(String blStatus) {
        if ("1".equals(blStatus)) {
            return "Entry";    
        } else if ("2".equals(blStatus)) {
            return "Confirmed";
        } else if ("3".equals(blStatus)) {
            return "Printed";
        } else if ("4".equals(blStatus)) {
            return "Manifested";
        } else if ("5".equals(blStatus)) {
            return "Invoiced";
        } else if ("6".equals(blStatus)) {
            return "Waitlisted";
        } else if ("7".equals(blStatus)) {
            return "Multipart B/L";
        } else if ("9".equals(blStatus)) {
            return "Cancelled";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of B/L type
     * @param blType
     * @return description of B/L type
     */
    public static String getBlTypeDesc(String blType) {
        if ("T".equals(blType)) {
            return "Through";    
        } else if ("M".equals(blType)) {
            return "Memo";
        } else if ("S".equals(blType)) {
            return "Sea way";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of service type
     * @param serviceType
     * @return description of service type
     */
    public static String getServiceTypeDesc(String serviceType) {
        if (RcmConstant.SERVICE_TYPE_REPORT.equals(serviceType)) {
            return "Report";    
        } else if (RcmConstant.SERVICE_TYPE_SCRIPT.equals(serviceType)) {
            return "Script";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of schedule type
     * @param scheduleType
     * @return description of schedule type
     */
    public static String getScheduleTypeDesc(String scheduleType) {
        if (RcmConstant.SCHEDULE_TYPE_NOW.equals(scheduleType)) {
            return "Now (Offline)";    
        } else if (RcmConstant.SCHEDULE_TYPE_OVER_NIGHT.equals(scheduleType)) {
            return "Over Night";
        } else if (RcmConstant.SCHEDULE_TYPE_OVER_WEEKEND.equals(scheduleType)) {
            return "Over Weekend";
        } else if (RcmConstant.SCHEDULE_TYPE_HOURLY.equals(scheduleType)) {
            return "Hourly";
        } else if (RcmConstant.SCHEDULE_TYPE_DAILY.equals(scheduleType)) {
            return "Daily";
        } else if (RcmConstant.SCHEDULE_TYPE_WEEKLY.equals(scheduleType)) {
            return "Weekly";
        } else if (RcmConstant.SCHEDULE_TYPE_MONTHLY.equals(scheduleType)) {
            return "Monthly";
        } else if (RcmConstant.SCHEDULE_TYPE_YEARLY.equals(scheduleType)) {
            return "Yearly";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of simulation
     * @param simulationCode
     * @return description of simulation
     */
    public static String getSimulationDesc(String simulationCode) {
        if ("A".equals(simulationCode)) {
            return "All";    
        } else if ("S".equals(simulationCode)) {
            return "Simulation";
        } else if ("R".equals(simulationCode)) {
            return "Real";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of inbound/outbound/cross trade code
     * @param inbOubXt
     * @return description of inbound/outbound/cross trade
     */
    public static String getInbOubXtDesc(String inbOubXt) {
        if ("A".equals(inbOubXt)) {
            return "All";
        } else if ("I".equals(inbOubXt) || "I_P".equals(inbOubXt)) {
            return "Inbound";
        } else if ("O".equals(inbOubXt)) {
            return "Outbound";
        } else if ("X".equals(inbOubXt) || "X_P".equals(inbOubXt)) {
            return "Cross Trade";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of bill status code
     * @param billStatus
     * @return description of bill status code
     */
    public static String getBillStatusDesc(String billStatus) {
        if ("P".equals(billStatus)) {
            return "Printed";
        } else if ("C".equals(billStatus)) {
            return "Cancelled";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of module code
     * @param moduleCode
     * @return description of module code
     */
    public static String getModuleNameDesc(String moduleCode) {
        if ("BKG".equals(moduleCode)) {
            return "Booking";
        } else if ("BSA".equals(moduleCode)) {
            return "Base Space Allocation";
        } else if ("CAM".equals(moduleCode)) {
            return "Cross Application";
        } else if ("CA".equals(moduleCode)) {
            return "Cross Application";
        } else if ("CRM".equals(moduleCode)) {
            return "Customer Relationship Management";
        } else if ("CTF".equals(moduleCode)) {
            return "Contracts & Tariff";
        } else if ("DGS".equals(moduleCode)) {
            return "Dangerous Cargo System";
        } else if ("DND".equals(moduleCode)) {
            return "Demurrage And Detention";
        } else if ("DEX".equals(moduleCode)) {
            return "Documentation (Export)";
        } else if ("DIM".equals(moduleCode)) {
            return "Documentation (Import & Transshipment)";
        } else if ("DIM_DEX".equals(moduleCode)) {
            return "Documentation (Import & Export)";
        } else if ("EDI".equals(moduleCode)) {
            return "Electronic Data Interchange";
        } else if ("ELS".equals(moduleCode)) {
            return "Equipment Lease";
        } else if ("EMS".equals(moduleCode)) {
            return "Equipment Management System";
        } else if ("ESM".equals(moduleCode)) {
            return "Enterprise Security Manager";
        } else if ("FNA".equals(moduleCode)) {
            return "Forecast and Allocation";
        } else if ("FAP".equals(moduleCode)) {
            return "Finance Accounts Payable";
        } else if ("FAR".equals(moduleCode)) {
            return "Finance Account Receivable";
        } else if ("FGL".equals(moduleCode)) {
            return "Finance General Ledger";
        } else if ("FMR".equals(moduleCode)) {
            return "Finance Management Reports";
        } else if ("FPR".equals(moduleCode)) {
            return "Finance Proforma";
        } else if ("FMS".equals(moduleCode)) {
            return "Forecast Management System";
        } else if ("IJS".equals(moduleCode)) {
            return "Inter Modal Job Order System";
        } else if ("MNR".equals(moduleCode)) {
            return "Maintenance & Repair";
        } else if ("QTN".equals(moduleCode)) {
            return "Tariff & Quotation";
        } else if ("SBL".equals(moduleCode)) {
            return "Sublease";
        } else if ("SCP".equals(moduleCode)) {
            return "Service Compliance (Claims)";
        } else if ("SHS".equals(moduleCode)) {
            return "Ship Husbandry System";
        } else if ("SMS".equals(moduleCode)) {
            return "Slot Management System";
        } else if ("SPS".equals(moduleCode)) {
            return "Ship Planning System";
        } else if ("STR".equals(moduleCode)) {
            return "Depot Storage";
        } else if ("TOS".equals(moduleCode)) {
            return "Terminal Operations";
        } else if ("VMS".equals(moduleCode)) {
            return "Vendor Management System";
        } else if ("VSS".equals(moduleCode)) {
            return "Vessel Scheduling System";
        } else if ("YDM".equals(moduleCode)) {
            return "Yield Management";
        } else if ("EZL".equals(moduleCode)) {
            return "Easy Load List";
        } else if ("VAS".equals(moduleCode)) {
            return "Value Added Service";
        } else {
            return "";
        }
    }
    
    /**
     * get the description of screen code
     * @param screenCode
     * @return description of screen code
     */
    public static String getScreenNameDesc(String screenCode) {
        if ("DIM111".equals(screenCode)) {
            return "Delivery Order Print";
        } else if ("DIM128".equals(screenCode)) {
            return "Arrival Notice";
        // ##01 BEGIN
        } else if ("DIMCGF".equals(screenCode)) {
            return "Manifest Print";
        } else if ("DEXCGF".equals(screenCode)) {
            return "Manifest Print";
        // ##01 END
        // ##02 BEGIN
         } else if ("DIM111CMB".equals(screenCode)) {
             return "Delivery Order Print for CMB";
         } else if ("DIM111CMB".equals(screenCode)) {
             return "Delivery Order Print";
         // ##02 END
        } else {
            return "";
        }
    }
    
    /**
      * get the description of utility dtl code
      * @param utilityCode
      * @return description of utility dtl code
      */
    public static String getUtilityDetailNameDesc(String utilityCode) {
        if ("DIM111_HEADER".equals(utilityCode)) {
            return "Delivery Order (Header)";
        } else if ("DIM111_PRINTCLAUSE".equals(utilityCode)) {
            return "Delivery Order (Print Clause)";
        } else if ("DIM111_SIGNATURE".equals(utilityCode)) {
            return "Delivery Order (Signature)";
        } else if ("DIM128_HEADER".equals(utilityCode)) {
            return "Arrival Notice (Header)";
        } else if ("DIM128_PRINTCLAUSE".equals(utilityCode)) {
            return "Arrival Notice (Print Clause)";
        } else if ("DIM128_SIGNATURE".equals(utilityCode)) {
            return "Arrival Notice (Signature)";
        } else if ("TOS123_HEADER".equals(utilityCode)) {
                return "Job Order (Header)";  
        } else {
            return "";
        }
    }
    
    /**
      * get the description of utility type
      * @param utilityType
      * @return description of utility type
      */
    public static String getUtilityTypeDesc(String utilityType) {
        if (RcmConstant.UTILITY_TYPE_FILE.equals(utilityType)) {
            return "Upload File";
        } else if (RcmConstant.UTILITY_TYPE_TEXT.equals(utilityType)) {
            return "Text Message";
        } else {
            return "";
        }
    }
    
    public static String getScheduleSpecialDayDesc(String specialDay) {
        if (RcmConstant.SCHEDULE_SPECIAL_DAY_FIRST_DAY.equals(specialDay)) {
            return "First day";    
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_LAST_DAY.equals(specialDay)) {
            return "Last day";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_PREVIOUS_DAY.equals(specialDay)) {
            return "Previous day";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_CURRENT_DAY.equals(specialDay)) {
            return "Current day";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_NEXT_DAY.equals(specialDay)) {
            return "Next day";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_PREVIOUS_WEEK_START.equals(specialDay)) {
            return "Previous week - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_PREVIOUS_WEEK_END.equals(specialDay)) {
            return "Previous week - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_CURRENT_WEEK_START.equals(specialDay)) {
            return "Current week - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_CURRENT_WEEK_END.equals(specialDay)) {
            return "Current week - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_NEXT_WEEK_START.equals(specialDay)) {
            return "Next week - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_DAY_NEXT_WEEK_END.equals(specialDay)) {
            return "Next week - End";
        } else {
            return "";
        }
    }
    
    public static String getScheduleSpecialMonthDesc(String specialMonth) {
        if (RcmConstant.SCHEDULE_SPECIAL_MONTH_FIRST_MONTH.equals(specialMonth)) {
            return "First month";    
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_LAST_MONTH.equals(specialMonth)) {
            return "Last month";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_PREVIOUS_MONTH.equals(specialMonth)) {
            return "Previous month";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_CURRENT_MONTH.equals(specialMonth)) {
            return "Current month";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_NEXT_MONTH.equals(specialMonth)) {
            return "Next month";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_FIRST_QUARTER_START.equals(specialMonth)) {
            return "First quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_FIRST_QUARTER_END.equals(specialMonth)) {
            return "First quarter - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_SECOND_QUARTER_START.equals(specialMonth)) {
            return "Second quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_SECOND_QUARTER_END.equals(specialMonth)) {
            return "Second quarter - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_THIRD_QUARTER_START.equals(specialMonth)) {
            return "Third quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_THIRD_QUARTER_END.equals(specialMonth)) {
            return "Third quarter - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_FOURTH_QUARTER_START.equals(specialMonth)) {
            return "Fourth quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_FOURTH_QUARTER_END.equals(specialMonth)) {
            return "Fourth quarter - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_PREVIOUS_QUARTER_START.equals(specialMonth)) {
            return "Previous quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_PREVIOUS_QUARTER_END.equals(specialMonth)) {
            return "Previous quarter - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_CURRENT_QUARTER_START.equals(specialMonth)) {
            return "Current quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_CURRENT_QUARTER_END.equals(specialMonth)) {
            return "Current quarter - End";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_NEXT_QUARTER_START.equals(specialMonth)) {
            return "Next quarter - Start";
        } else if (RcmConstant.SCHEDULE_SPECIAL_MONTH_NEXT_QUARTER_END.equals(specialMonth)) {
            return "Next quarter - End";
        } else {
            return "";
        }
    }
    
    public static String getScheduleSpecialYearDesc(String specialYear) {
        if (RcmConstant.SCHEDULE_SPECIAL_YEAR_PREVIOUS_YEAR.equals(specialYear)) {
            return "Previous year";    
        } else if (RcmConstant.SCHEDULE_SPECIAL_YEAR_CURRENT_YEAR.equals(specialYear)) {
            return "Current year";
        } else if (RcmConstant.SCHEDULE_SPECIAL_YEAR_NEXT_YEAR.equals(specialYear)) {
            return "Next year";
        } else {
            return "";
        }
    }
    
    public static String getLogStatusDesc(String logStatus) {
        if (RcmConstant.LOG_STATUS_PENDING.equals(logStatus)) {
            return "Pending";    
        } else if (RcmConstant.LOG_STATUS_SUCCEEDED.equals(logStatus)) {
            return "Succeeded";
        } else if (RcmConstant.LOG_STATUS_FAILED.equals(logStatus)) {
            return "Failed";
        } else if (RcmConstant.LOG_STATUS_CANCELLED.equals(logStatus)) {
            return "Cancelled";            
        } else {
            return "";
        }
    }
    
}
