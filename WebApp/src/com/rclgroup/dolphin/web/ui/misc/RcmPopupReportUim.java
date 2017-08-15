/*--------------------------------------------------------
RcmPopupReportUim.java
--------------------------------------------------------
Copyright RCL Public Co., Ltd. 2009
--------------------------------------------------------
Author Wuttitorn Wuttijiaranai 29/09/2009
- Change Log--------------------------------------------
## DD/MM/YY -User- -TaskRef- -ShortDescription
--------------------------------------------------------
*/
package com.rclgroup.dolphin.web.ui.misc;

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RrcStandardUim;


public class RcmPopupReportUim extends RrcStandardUim {
    private String reportUrl;

    public RcmPopupReportUim() {
        reportUrl = RcmConstant.MISC_PAGE_URL+"/RcmBlankPage.jsp";
    }
    
    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }
    
}
