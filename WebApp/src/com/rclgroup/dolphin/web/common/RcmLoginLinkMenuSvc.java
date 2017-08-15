/*-----------------------------------------------------------------------------------------------------------
RcmLoginSvc.java
-------------------------------------------------------------------------------------------------------------
Copyright RCL Public Co., Ltd. 2007
-------------------------------------------------------------------------------------------------------------
 Author Piyapong Ieumwananonthachai 09/11/07
- Change Log ------------------------------------------------------------------------------------------------
## DD/MM/YY -User-     -TaskRef-      -Short Description
01 07/04/08 PIE                       Modify to work in RCLWebApp.
02 12/03/12 NIP                       for check user Authentication(have in BSA Project) 
-----------------------------------------------------------------------------------------------------------*/
package com.rclgroup.dolphin.web.common;

import com.rclgroup.dolphin.web.dao.rcm.RcmUserDao;
import com.rclgroup.dolphin.web.model.rcm.RcmUserMod;
import com.rclgroup.dolphin.web.util.RutString;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

/**
 *    A main login servlet for link menu
 */
public class RcmLoginLinkMenuSvc extends RrcStandardSvc {

    public RcmLoginLinkMenuSvc() {
    }

    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws Exception {
        System.out.println("[RcmLoginLinkMenuSvc][execute]: Started");
        HttpSession session = request.getSession(true);
        String service = RutString.nullToStr(request.getParameter("service"));
        String target = "/RrcStandardSrv?service="+service;
        String pageAction = RutString.nullToStr(request.getParameter("pageAction"));
        String moduleCd = RutString.nullToStr(request.getParameter("moduleCd"));
        
        // Additional Params
        String role = RutString.nullToStr(request.getParameter("role"));
        String mtype = RutString.nullToStr(request.getParameter("mtype"));
        
        target = RcmConstant.SERV_URL + target;
        System.out.println("[RcmLoginLinkMenuSvc][execute]: target: " + target);
        
        RcmUserMod userMod = new RcmUserMod();
        RcmUserBean userBean = new RcmUserBean();

        String testdata = "";
        System.out.println("[RcmLoginLinkMenuSvc][execute]: start get cookie");
        Cookie[ ] cookies = request.getCookies ();// get cookie
        String[] arrGetUserdata = null;        
        if(cookies!=null && cookies.length>0){
                testdata+="Cookies--><br>";
                for(int i=0;i<cookies.length;i++){
                    
                    String name = cookies[i].getName ();
                    String value = cookies[i].getValue ();
                    
                    //String name = "RCL_AUTH_KEY";
                    //String value = "DEV_TEAM~DOLPHIN1~R~R~*~***~1788725770.24862.0000";
                    
                    testdata +=name+"="+value +"<br>";
                    if(name.equals("RCL_AUTH_KEY")){
                        System.out.println("[RcmLoginLinkMenuSvc][execute]: found cookie RCL_AUTH_KEY");
                        arrGetUserdata=value.split("~");
                    }
                }
            
            testdata += "-------------------------------------------------------------<br>";
            if(arrGetUserdata!=null && arrGetUserdata.length>0){
                System.out.println("[RcmLoginLinkMenuSvc][execute]: start set user data");
                RcmUserDao rcmUserDao = (RcmUserDao)getBean("rcmUserDao");
                
                userMod = rcmUserDao.findByKeyPrsnLogId(arrGetUserdata[0]);
                
                if(userMod!=null){
                    userBean = new RcmUserBean();
                    
                    userBean.setPrsnLogId(arrGetUserdata[0]);                    
                    userBean.setDescr(userMod.getDescr());
                    userBean.setFscCode(userMod.getFscCode());
                    userBean.setCountry(userMod.getCountry());
                    userBean.setPort(userMod.getPort());
                    userBean.setFscLvl1(arrGetUserdata[3]);
                    userBean.setFscLvl2(arrGetUserdata[4]);
                    userBean.setFscLvl3(arrGetUserdata[5]);
                    userBean.setFscName(userMod.getFscName());
                    if (arrGetUserdata[6].equals("1")) {
                        userBean.setFscDateFormat("DD/MM/YYYY");
                    } else {
                        userBean.setFscDateFormat("MM/DD/YYYY");
                    }
                    
                    if(rcmUserDao!=null)
                           userBean.setMenuAuthentication(rcmUserDao.getMenuAuthentication(userBean.getPrsnLogId()));// ##02 for check user Authentication
                    
                    System.out.println("[RcmLoginLinkMenuSvc][execute]: end set user data");
                }else{
                    System.out.println("[RcmLoginLinkMenuSvc][execute]: not found user in cookie RCL_AUTH_KEY");
                    System.out.println("[RcmLoginLinkMenuSvc][execute]: Finished");
                    response.sendRedirect(RcmConstant.SEALINER_PAGE_URL+"/callexp.jsp");
                    return;
                }
            }else{
                    System.out.println("[RcmLoginLinkMenuSvc][execute]: not found cookie RCL_AUTH_KEY");
                    System.out.println("[RcmLoginLinkMenuSvc][execute]: Finished");
                    response.sendRedirect(RcmConstant.SEALINER_PAGE_URL+"/callexp.jsp");
                    return;
            }
        }else{
            request.setAttribute("target", RcmConstant.ERR_PAGE_URL + "/RcmErrorPage.jsp");
            System.out.println("[RcmLoginLinkMenuSvc][execute]: cookies is not passed");
            System.out.println("[RcmLoginLinkMenuSvc][execute]: Finished");
            return;
        }
        
        // get session
        testdata+="Session--><br>";
        Enumeration mySession = session.getAttributeNames();
        while (mySession.hasMoreElements()) {
            String sessionName = (String)mySession.nextElement();
            Object sessionValue = (Object)session.getAttribute(sessionName);
            
            if(!sessionName.equals("testdata"))
                testdata +=sessionName+"="+sessionValue +"<br>";
        }
        
        if(session.getAttribute("testdata")!=null)
                session.removeAttribute("testdata");
        
        session.setAttribute("testdata", testdata);
        session.setAttribute("userBean", userBean);
       // request.setAttribute("target", target + "&pageAction=" + pageAction+"&moduleCd="+moduleCd);
        String strTarget = target + "&pageAction=" + pageAction+"&moduleCd="+moduleCd;
        if (role != null && !(role.trim().length() == 0)) {
            strTarget += "&role="+role;
        }
        if (mtype != null && !(mtype.trim().length() == 0)) {
            strTarget += "&mtype="+ mtype;
        }
        request.setAttribute("target", strTarget);
        
        System.out.println("[RcmLoginLinkMenuSvc][execute]: Finished");
    }
}
