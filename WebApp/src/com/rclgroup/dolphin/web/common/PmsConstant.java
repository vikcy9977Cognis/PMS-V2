/*-----------------------------------------------------------------------------------------------------------
PmsConstant.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2016
-------------------------------------------------------------------------------------------------------------
Author Khomsun Hima 01/12/16
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

public class PmsConstant {
    public static final String PAGE_ACTION_NEW = "new";
    public static final String PAGE_ACTION_EDIT = "edit";
    public static final String PAGE_ACTION_SEARCH = "Search";
    public static final String PAGE_ACTION_RESET = "Reset";
    public static final String PAGE_ACTION_SAVE = "save";
    public static final String PAGE_ACTION_BACK = "back";

    // AJAX Return status code.
    public static final int STATUS_ERROR = 1;
    public static final int STATUS_SUCCESS = 0;

    // Date Format
    public static final String PAGE_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DB_DATE_FORMAT = "yyyyMMdd";

    // Pagination
    public static final int PAGING_DEFAULT_DISPLAY_LENGTH = 5;

    // Check Session Expired Variables
    public static final String LAST_ACCESS_TIME_KEY = "last_access_time";
    public static final String AJAX_POLL_KEY = "ajax_poll";

    //  Summary Employee PMS Status Actions
    public static final String PART_ACTION_SREPORT_LOADTABLE = "sreport_loadtable";
    public static final String PART_ACTION_SREPORT_SEARCHTABLE = "sreport_searchtable";

    //search form
    public static final String PART_ACTION_PMFORM_LOADACTIVITIES = "pmform_loadactivities";
    public static final String PART_ACTION_PMFORM_LOADPROFILE = "pmform_loadprofile";
    public static final String PART_ACTION_PMFORM_LOADMYSTAFF = "pmform_loadmystaff";
   public static final String  PART_ACTION_PMFORM_EMPPERFRES = "pmform_loadempperfres";    
    public static final String PART_ACTION_PMFORM_LOADMYPM = "pmform_loadmypm";
    public static final String PART_ACTION_PMFORM_LOADJOBGRADE = "pmform_loadjobgrade";
    public static final String PART_ACTION_PMFORM_LOADJOBBRAND = "pmform_loadjobbrand";
    public static final String PART_ACTION_PMFORM_LOADCOMPANY = "pmform_loadcompany";
    public static final String PART_ACTION_PMFORM_LOADSTATUS = "pmform_loadstatus";
    public static final String PART_ACTION_PMFORM_LOADDIVISION = "pmform_loaddivision";
    public static final String PART_ACTION_PMFORM_LOADSECTION = "pmform_loadsection";
    public static final String PART_ACTION_PMFORM_LOADDEPARTMENT = "pmform_loaddepartant";
    public static final String PART_ACTION_PMFORM_LOADYEAR = "pmform_loadyear";

    //Wizard
    public static final String PART_ACTION_WIZARD_APPRAISEE = "wizard_appraisee";
    public static final String PART_ACTION_WIZARD_COMPETENCY = "wizard_competency";
    public static final String PART_ACTION_WIZARD_PRESIDENT_DIRECTIVE = "wizard_president_directive";
    public static final String PART_ACTION_WIZARD_PERFORMANCE_PROGRESS = "wizard_performance_progress";
    public static final String PART_ACTION_WIZARD_OVERALL = "wizard_overall";
    public static final String PART_ACTION_WIZARD_COUNTING_SIGNING = "wizard_counting_signing";
    public static final String PART_ACTION_WIZARD_CONFIRM = "wizard_confirm";
    public static final String PART_ACTION_WIZARD_DATA = "wizard_data";
    public static final String PART_ACTION_WIZARD_GET_DATA = "wizard_get_data";
    public static final String PART_ACTION_WIZARD_SET_DATA = "wizard_set_data";

    //Form Maintenance
    public static final String PART_ACTION_MAINTENANCE_GET_USR_AUTH = "maintenance_get_user_authorize";
    public static final String PART_ACTION_MAINTENANCE_LOAD_PM_HEADER = "maintenance_load_pm_header";
    public static final String PART_ACTION_MAINTENANCE_LOAD_JOB_BRAND_WEIGHTAGE =
        "maintenance_load_job_brand_weightage";
    public static final String PART_ACTION_MAINTENANCE_LOAD_COMPANY_AND_DEPARTMENT_CORE_VALUE =
        "maintenance_load_company_and_department_core_value";
    public static final String PART_ACTION_MAINTENANCE_LOAD_PRESIDENT_DIRECTIVE =
        "maintenance_load_president_directive";
    public static final String PART_ACTION_MAINTENANCE_LOAD_INDIVIDUAL_KPI = "maintenance_load_individual_kpi";
    public static final String PART_ACTION_MAINTENANCE_LOAD_OVERALL = "maintenance_load_overall";
    public static final String PART_ACTION_MAINTENANCE_LOAD_INDICATOR_DETAIL = "maintenance_load_indicator_detail";
    public static final String PART_ACTION_MAINTENANCE_GET_ATTACH_FILE = "maintenance_load_get_attach_file";

    // Upload file context parameters
    public static final String UPLOAD_ATTACHFILE_PATH = "uploadFilePath";
    
    public static final String PM_KEY = "PM_KEY";
    public static final Object PMS_VERSION = "01.11";


    public PmsConstant() {
        super();
    }
}
