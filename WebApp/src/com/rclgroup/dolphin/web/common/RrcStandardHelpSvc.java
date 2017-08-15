/*-----------------------------------------------------------------------------------------------------------  
RrcStandardHelpSvc.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 09/11/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 26/03/08 PIE                       Modify to be part of new RCL template
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.common.RcmConstant;
import com.rclgroup.dolphin.web.common.RrcStandardHelpUim;
import com.rclgroup.dolphin.web.util.RutPage;
import com.rclgroup.dolphin.web.util.RutString;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public abstract class RrcStandardHelpSvc extends RrcStandardSvc {
    public RrcStandardHelpSvc() {
    }

    public abstract void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception;
    
    public void helpProcess(HttpServletRequest request,String helpUiModInstanceName,String helpScnName,RrcStandardHelpUim aUim,RrcStandardHelpUim aNewUim){        
        HttpSession session=request.getSession(false);
        
        System.out.println("1. [RrcStandardHelpSvc] [helpProcess] helpScnName:"+helpScnName);
        
        String pageAction = RutString.nullToStr(request.getParameter("pageAction"));
        String formName = RutString.nullToStr(request.getParameter("formName"));
        String retName = RutString.nullToStr(request.getParameter("retName"));
        String type = RutString.nullToStr(request.getParameter("type"));
        String find = RutString.nullToStr(request.getParameter("txtFind"));
        String search = RutString.nullToStr(request.getParameter("cmbSearch"));
        String wild = RutString.nullToStr(request.getParameter("chkWild"));
        String retFunc = RutString.nullToStr(request.getParameter("retFunc"));
        
        helpProcess(request,session,pageAction,formName,retName,retFunc,type,find,search,wild,aUim,aNewUim,helpUiModInstanceName,helpScnName);
    }
    
    public void helpProcess(HttpServletRequest request,HttpSession session,String pageAction,String formName,String retName,String retFunc,String type,String find,String search,String wild,RrcStandardHelpUim uim,RrcStandardHelpUim newUim,String helpUiModInstanceName,String helpScnName){
        String target = RcmConstant.ERR_PAGE_URL + "/RcmErrorPage.jsp";
        String helpURL = RcmConstant.HELP_PAGE_URL;
        System.out.println("2. [RrcStandardHelpSvc] [helpProcess] helpScnName:"+helpScnName);
        if (pageAction.equalsIgnoreCase("new")) {
            session.removeAttribute(helpUiModInstanceName);
            newUim.setFormName(formName);
            newUim.setRetName(retName);
            newUim.setRetFunc(retFunc);
            newUim.setType(type);
            newUim.setFind(RrcStandardHelpUim.FIND_DEFAULT);
            newUim.setSearch(RrcStandardHelpUim.SEARCH_DEFAULT);
            newUim.setWild(RrcStandardHelpUim.WILD_DEFAULT);
            System.out.println("3. [RrcStandardHelpSvc] [helpProcess] New pageAction createRutPage");
            newUim.createRutPage(); 
            session.setAttribute(helpUiModInstanceName,newUim);
            target = helpURL + helpScnName;  
            request.setAttribute("target", target);
        }else if (pageAction.equalsIgnoreCase("search")) {
            uim.setFind(find);
            uim.setSearch(search);
            uim.setWild(wild);
            System.out.println("4. [RrcStandardHelpSvc] [helpProcess] search pageAction createRutPage");
            uim.createRutPage(); 
            //begin: check whether record is not found or not 
            RutPage rutPage = uim.getRutPage();
            if(rutPage == null){
                session.setAttribute("msg",getErrorMessage("RCM.NO_RECORD_FOUND"));
            }else{
                List list = rutPage.getItems();
                if(list.size() == 0){
                    session.setAttribute("msg",getErrorMessage("RCM.NO_RECORD_FOUND"));
                }
            }
            //end: check whether record is not found or not 
            session.setAttribute(helpUiModInstanceName,uim);
            target = helpURL + helpScnName;
            request.setAttribute("target", target);
        }else if (pageAction.equalsIgnoreCase("linkPage")) {
            String pageNo = RutString.nullToStr(request.getParameter("pageNo"));
            String pageClick = RutString.nullToStr(request.getParameter("pageClick"));
            if(pageNo != ""){
                if(pageClick.equals("")){
                    uim.getRutPage().setPageNo(Integer.parseInt(pageNo));                              
                }else{       
                    uim.getRutPage().setPageNo(Integer.parseInt(pageNo));
                    if(uim.getRutPage().getVersionFlag() == RutPage.VERSION_02){
                        uim.queryDataByRutPage();  
                    }                    
                }
            }
            session.setAttribute(helpUiModInstanceName,uim);
            target = helpURL + helpScnName;
            request.setAttribute("target", target);
        }
    }
}
