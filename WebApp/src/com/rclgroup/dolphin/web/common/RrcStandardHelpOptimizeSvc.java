/*-----------------------------------------------------------------------------------------------------------  
RrcStandardHelpOptimizeSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 25/08/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RrcStandardSvc;
import com.rclgroup.dolphin.web.dao.rcm.RcmConstantDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmDefaultHelpDao;
import com.rclgroup.dolphin.web.dao.rcm.RcmUserDao;
import com.rclgroup.dolphin.web.ui.misc.RcmStandardHelpOptimizeUim;
import com.rclgroup.dolphin.web.util.RutDatabase;
import com.rclgroup.dolphin.web.util.RutPage;
import com.rclgroup.dolphin.web.util.RutString;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class RrcStandardHelpOptimizeSvc extends RrcStandardSvc {
    public RrcStandardHelpOptimizeSvc() {
    }
    
    public static final String INSTANCE_NAME_UIM_DEFAULT = "rcmStandardHelpOptimizeUim";
    
    public abstract void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception;
    
    public void doExecute(HttpServletRequest request, RcmStandardHelpOptimizeUim uim) throws Exception {
        System.out.println("[RrcStandardHelpOptimizeSvc][execute]: Started");
        HttpSession session = request.getSession(false);
        String target = "";
        
        //begin: validate first time
        String firstTime = RutString.getParameterToString(request, "firstTime", RcmConstant.FLAG_YES, RutString.CASE_TYPE_UPPER);
        if (RcmConstant.FLAG_YES.equals(firstTime)) {
            
            // set Dao
            uim.setRcmConstantDao((RcmConstantDao)getBean("rcmConstantDao"));
            uim.setRcmUserDao((RcmUserDao)getBean("rcmUserDao"));
            uim.setRcmDefaultHelpDao((RcmDefaultHelpDao)getBean("rcmDefaultHelpDao"));
            
            // manager Dao object
            uim.manageDao(this);
            
            String service = RutString.nullToStr(request.getParameter("service"));
            String formName = RutString.nullToStr(request.getParameter("formName"));
            String retName = RutString.nullToStr(request.getParameter("retName"));
            String retFunc = RutString.nullToStr(request.getParameter("retFunc"));
            String titleName = RutString.nullToStr(request.getParameter("titleName"));
            String type = RutString.nullToStr(request.getParameter("type"));
            
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: service = "+service);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: formName = "+formName);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: retName = "+retName);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: retFunc = "+retFunc);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: titleName = "+titleName);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: type = "+type);
            
            // set variable [1]
            uim.setService(service);
            uim.setFormName(formName);
            uim.setRetName(retName);
            uim.setRetFunc(retFunc);
            uim.setTitleName(titleName);
            uim.setType(type);
            
            // get request parameter
            uim.manageRequestParameter(request);
            
            // manager session parameter
            uim.manageSessionParameter(session);
            
            // find title name for showing on screen
            String titleNameShow = uim.findTitleNameShowByType(type);
            
            // find SQL statement
            String sqlStatement = uim.findSqlStatementByType(type);
            List columnName = RutDatabase.convertSqlStatementToListColumn(sqlStatement);
            
            // find Column name
            HashMap columnNameShow = uim.findColumnNameShowByType(type);
            
            // find Return value
            String[] arrReturnValue = uim.findReturnValueByType(type);
            
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: titleNameShow = "+titleNameShow);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: sqlStatement = "+sqlStatement);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: columnName = "+columnName);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: columnNameShow = "+columnNameShow);
            System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: arrReturnValue = "+arrReturnValue);
            
            // set variable [2]
            uim.setService(service);
            uim.setFormName(formName);
            uim.setRetName(retName);
            uim.setRetFunc(retFunc);
            uim.setTitleName(titleName);
            uim.setType(type);
            uim.setTitleNameShow(titleNameShow);
            uim.setSqlStatement(sqlStatement);
            uim.setColumnName(columnName);
            uim.setColumnNameShow(columnNameShow);
            uim.setArrReturnValue(arrReturnValue);
            
            // do before [optional]
            uim.doBeforeOptional(this, request, session);// this statement will clear old data of session_id of InVoyage Browser(clear in rcm_attribute_session table) 
        }
        //end: validate first time
        
        // get request parameter
        String pageAction = RutString.getParameterToString(request, "pageAction");
        if (pageAction.equalsIgnoreCase("new")) {
            uim.setFind("");
            uim.setSearch("");
            uim.setWild(RrcStandardHelpOptimizeUim.WILD_DEFAULT);
            
        } else {
            String find = RutString.nullToStr(request.getParameter("txtFind")).toUpperCase();
            String search = RutString.nullToStr(request.getParameter("cmbSearch"));
            String sortBy = RutString.nullToStr(request.getParameter("cmbSortBy"));
            String sortIn = RutString.nullToStr(request.getParameter("cmbSortIn"));
            String wild = RutString.nullToStr(request.getParameter("chkWild"));
            
            search = (search == null || "Select one ...".equals(search)) ? "" : search;
            sortBy = (sortBy == null || "Select one ...".equals(sortBy)) ? "" : sortBy;
            
            uim.setFind(find);
            uim.setSearch(search);
            if (!RutString.isEmptyString(sortBy)) {
                uim.setSortBy(sortBy);    
            }
            if (!RutString.isEmptyString(sortIn)) {
                uim.setSortIn(sortIn);
            }
            uim.setWild(wild);
        }
        
        // clear message
        session.removeAttribute("msg");
        
        if (pageAction.equalsIgnoreCase("new")) {
            uim.createRutPage(); 
            session.setAttribute(INSTANCE_NAME_UIM_DEFAULT, uim);
            
        } else if (pageAction.equalsIgnoreCase("search")) {
            uim.rutPage = null;
            uim.createRutPage(); 
            
            //begin: check whether record is not found or not 
            RutPage rutPage = uim.getRutPage();
            if (rutPage == null || rutPage.getItems() == null || rutPage.getItems().size() == 0) {
                session.setAttribute("msg", getErrorMessage("RCM.NO_RECORD_FOUND"));
            } 
            //end: check whether record is not found or not
            
            session.setAttribute(INSTANCE_NAME_UIM_DEFAULT, uim);
            
        } else if (pageAction.equalsIgnoreCase("linkPage")) {
            String pageNo = RutString.nullToStr(request.getParameter("pageNo"));
            String pageClick = RutString.nullToStr(request.getParameter("pageClick"));
            if (!RutString.isEmptyString(pageNo)) {
                uim.getRutPage().setPageNo((new Integer(pageNo)).intValue());
                
                // manager link page [optional]
                uim.manageLinkPageOptional(this, request);
                
                if (!RutString.isEmptyString(pageClick)) {
                    // find next or previous page
                    uim.queryDataByRutPage();
                }
            }
            
            session.setAttribute(INSTANCE_NAME_UIM_DEFAULT, uim);
            
        } else {
            // manager page action [optional]
            uim.managePageActionOptional(pageAction, this, request, session);
        
            session.setAttribute(INSTANCE_NAME_UIM_DEFAULT, uim);
        }
        
        target = (!RutString.isEmptyString(uim.getPageTarget())) ? uim.getPageTarget() : (RcmConstant.MISC_PAGE_URL + "/RcmStandardHelpOptimizeScn.jsp");
        request.setAttribute("target", target);
        System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: target = "+target);
        System.out.println("[RrcStandardHelpOptimizeSvc][doExecute]: Finished");
    }
    
}
