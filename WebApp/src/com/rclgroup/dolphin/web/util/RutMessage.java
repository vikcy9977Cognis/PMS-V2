/*-----------------------------------------------------------------------------------------------------------  
RutMessage.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;


public class RutMessage {
    private ResourceBundleMessageSource messageSource;
    
    public RutMessage() {
        messageSource = new ResourceBundleMessageSource();
    }
    
    public String getMessage(String code, Object[] args, Locale locale){
        String message;
        try{
            messageSource.setBasename("messages");
            message = messageSource.getMessage(code,args,locale);
        }catch(Exception e){
            //make error appearace to developer in oc4j log
            e.printStackTrace();
//            don't make error appearace to user of web application
//            message = "";

            //make error appearace to user of web application
//            message = "Please, contact administrator to solve this problem: [" + code;
            message = "* [" + code;
            for(int i=0;i<args.length;i++){
                message += "%"+args[i];
            }
            message += "]";
        }
        return message;
    }
    
    public String getMessage(String code, Object[] args){
        return getMessage(code,args,RutLocale.getDefaultLocale());
    }
    
    public String getMessage(String code){
        return this.getMessage(code,null,RutLocale.getDefaultLocale());
    }
    
    public String getErrorMessage(String code, Object[] args, Locale locale){
        String message;
        try{
            messageSource.setBasename("error_messages");
            message = messageSource.getMessage(code,args,locale);
        }catch(Exception e){
            //make error appearace to developer in oc4j log
            e.printStackTrace();
//            don't make error appearace to user of web application
//            message = "";

            //make error appearace to user of web application
//            message = "Please, contact administrator to solve this problem: [" + code;
            message = "* [" + code;
            for(int i=0;i<args.length;i++){
                message += "%"+args[i];
            }
            message += "]";
        }
        return message;
    }
    
    public String getErrorMessage(String code, Object[] args){
        return getErrorMessage(code,args,RutLocale.getDefaultLocale());
    }
    
    public String getErrorMessage(String code){
        return getErrorMessage(code,new Object[]{},RutLocale.getDefaultLocale());
    }
}
