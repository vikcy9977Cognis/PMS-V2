/*-----------------------------------------------------------------------------------------------------------  
RutString.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Piyapong Ieumwananonthachai 10/10/07
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
01 14/05/09 WUT                       Added methods for getting string from parameter of request.
02 20/05/09 WUT                       Added method for checking empty string.
03 03/06/09 WUT                       Added method for debuging object.
04 12/10/09 WUT                       Added method for getting object id.
05 26/05/11 NIP                       Added method for get parameter value of request
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import java.io.BufferedReader;

import java.lang.reflect.Method;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import java.sql.Clob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;


public class RutString {
    public RutString() {
        super();
    }
    
    public static final String CASE_TYPE_NORMAL = "NORMAL";
    public static final String CASE_TYPE_UPPER = "UPPER";
    public static final String CASE_TYPE_LOWER = "LOWER";
    
    // ##02 BEGIN
    /**
     * Returns true, if the input string is empty, otherwise false
     * @param s
     * @return
     */
    public static boolean isEmptyString(String s) {
        if (s == null || "".equals(s.trim())) {
            return true;
        } else {
            return false;
        }
    }
    // ##02 BEGIN

    /**
     * Returns null, if the input string is empty, otherwise the input string
     * @param s
     * @return
     */
    public static String removeEmptyString(String s) {
        if (s == null)
            return null;
        if (s.equals(""))
            return null;
        else
            return s;
    }

    /**
     * Converts the first character in a string to upper case
     * @param s
     * @return
     */
    public static String toUpperFirstChar(String s) {
        if (s == null)
            return "";
        if (s.equals(""))
            return "";
        char c = Character.toUpperCase(s.charAt(0));
        return c + s.substring(1);
    }

    /**
     * Returns a trimmed string, if the string is not null, else null
     * @param s
     * @return
     */
    public static String trim(String s) {
        if (s == null)
            return s;
        if (s.length() == 0)
            return s;
        return s.trim();
    }
    
    public static String nullToStr(Clob cl){
        String retVal = "";
        if(cl == null){
            return retVal;
        }else{
            StringBuffer strOut = new StringBuffer();
            String aux;
            try{
                BufferedReader br = new BufferedReader(cl.getCharacterStream());
                while ((aux=br.readLine())!=null)
                strOut.append(aux);
                retVal = strOut.toString();

            }catch (java.sql.SQLException e1) {
                retVal = "";
            } catch (java.io.IOException e2) {
                retVal = "";
            }
        }
        return retVal;
    }
    
    public static String nullToStr(String str){
        return (str==null?"":str.trim());
    }
    
    public static String nullToStr(Object object){
        if (object == null)
            return "";
        String retValue = null;
        try {
          //  retValue = trim((String) object);
            retValue = object.toString().trim();
        } catch (Exception e) { 
            retValue = "";
        }
        return retValue;
    }
    
    public static String nullToStr(String str,String initStr){
        return (str==null?initStr:str.trim());
    }
    
    public static String nullOrBlankStrToStr(String str,String initStr){
        return ((str==null)||(str.equals(""))?initStr:str.trim());
    }
    
    public static String toUpperCase(String str){
        return nullToStr(str).toUpperCase();
    }
    
    public static String toUrlEncoding(String str){
        String strOut = ""+str;
        strOut = strOut.replaceAll("[&]","%26");
        strOut = strOut.replaceAll("[+]","%2B");
        strOut = strOut.replaceAll("[/]","%2F");
        strOut = strOut.replaceAll("[:]","%3A");
        strOut = strOut.replaceAll("[;]","%3B");
        strOut = strOut.replaceAll("[=]","%3D");
        strOut = strOut.replaceAll("[?]","%3F");
        strOut = strOut.replaceAll("[@]","%40");
        strOut = strOut.replaceAll("[ ]","%20");
        return strOut; 
        
    }
    
    public static String changeQuoteForSqlStatement(String str){
        String strOut = ""+str;
        return strOut.replaceAll("[']","''");    
    }
    
    public static String changeQuoteForHtml(String str) {
        String strOut = nullToStr(str);
        strOut = strOut.replaceAll("\'", "&#39;");
        strOut = strOut.replaceAll("\"", "&#34;");
        return strOut;  
    }
    
    // ##01 BEGIN
    public static String getParameterToString(HttpServletRequest request, String parameter) {
         return getParameterToString(request, parameter, "", CASE_TYPE_NORMAL);
    }
    
    public static String getParameterToStringUpper(HttpServletRequest request, String parameter) {
         return getParameterToString(request, parameter, "", CASE_TYPE_UPPER);
    }
    
    public static String getParameterToString(HttpServletRequest request, String parameter, String _default, String _caseType) {
        if (request != null && parameter != null && !"".equals(parameter)) {
            return convertStringToCaseType(nullToStr(request.getParameter(parameter), _default), _caseType);
        } else {
            return convertStringToCaseType(_default, _caseType);
        }
    }
    
    public static String convertStringToCaseType(String s, String caseType) {
        if (!isEmptyString(s)) {
            if (CASE_TYPE_UPPER.equals(caseType)) {
                return s.trim().toUpperCase();
            } else if (CASE_TYPE_LOWER.equals(caseType)) {
                return s.trim().toLowerCase();
            } else {
                return s.trim();
            }
        } else {
            return "";
        }
    }
    // ##01 END
    
    
    // ##05 BEGIN
    public static String[] getParametersToString(HttpServletRequest request, String parameter, String _default, String _caseType) {
         String arrValue[] = null;
         if (request != null && parameter != null && !"".equals(parameter)) {
             arrValue = request.getParameterValues(parameter);
             if(arrValue!=null)
                for(int i=0;i<arrValue.length;i++)
                    arrValue[i] = convertStringToCaseType(nullToStr(arrValue[i], _default), _caseType);
         } else {
                 //convertStringToCaseType(_default, _caseType);
         }
         
         return arrValue;
    }
    // ##05 END
    
    // ##03 BEGIN
    /**
     * Use to show data of object 
     * @param object
     * @param objectClass
     */
    public static void debugObject(Object object, Class objectClass) {
        try {
            if (object != null) {
                System.out.println(".... debugObject (Start) ....");
                Class dbClass = (object.getClass() == null) ? objectClass.getClass() : object.getClass();
                Method[] newMethod = dbClass.getMethods();
        
                for (int i = 0; i < newMethod.length; i++) {
                    Method getMethod = newMethod[i];
                    String getMethodName = getMethod.getName();
                
                    if (getMethodName != null) {
                        int nameIndex = getMethodName.indexOf("get");
                
                        if (nameIndex != -1) {
                            try {
                                Method subMethod = dbClass.getMethod(getMethodName, null);
                                
                                Object tmp = subMethod.invoke(object, null);
                                if (tmp!=null) {
                                    System.out.println(".....[" + getMethodName + "()].....= " + tmp);
                                } else {
                                    System.out.println(".....[" + getMethodName + "()].....= (value is null)");
                                }
                            } catch(Exception e) {
                                System.out.println(".....[" + getMethodName + "()].....= (Can't invoke)");
                            }
                        }
                    }
                }
                System.out.println(".... debugObject (End) ....");
            } else {
                System.out.println("..........[Object is Null]..........");
            } //end if (object != null && logger.isDebugEnabled()) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Use to show data of HashMap 
     * @param hBean
     */
    public static void debugObject(HashMap hBean) {
        try {
            if (hBean != null && hBean.size() > 0) { //if 1
                System.out.println(".... debugObject (Start) ....");
                ArrayList arList = new ArrayList(hBean.keySet());
                String keyValue = null;
                Object objectValue = null;
                for (int i = 0; i < arList.size(); i++) {
                    keyValue = (String) arList.get(i);
                    
                    try {
                        objectValue = hBean.get(keyValue);
                        if (objectValue != null) {
                            System.out.println(".....[" + keyValue + "()].....= " + objectValue.toString());
                        } else {
                            System.out.println(".....[" + keyValue + "()].....= (value is null)");
                        }
                    } catch(Exception e) {
                        System.out.println(".....[" + keyValue + "()].....= (Can't get value)");
                    }
                }
                System.out.println(".... debugObject (End) ....");
            } else {
                System.out.println("..........[HashMap is Null]..........");
            } //end if 1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    // ##03 END
    
    public static String getValueObjectByParameter(Object object, Class dbClass, String parameter) {
        String retValue = null;
        try {
            if (object != null && dbClass != null && !isEmptyString(parameter)) {
                String strPrefix = (parameter.substring(0, 1)).toUpperCase();
                String strTemp = parameter.substring(1);
                String getMethodName = "get" + strPrefix + strTemp;
                
                Method subMethod = dbClass.getMethod(getMethodName, null);
                Object tmp = subMethod.invoke(object, null);
                if (tmp!=null) {
                    retValue = tmp.toString();
                } else {
                    retValue = "";
                }
            } else {
                retValue = "";
            } //end if
        } catch (Exception e) {
            e.printStackTrace();
            retValue = "";
        }
        return retValue;
    }
    
    public static boolean getValueObjectByParameterBoolean(Object object, Class dbClass, String parameter) {
        boolean retValue = false;
        try {
            if (object != null && dbClass != null && !isEmptyString(parameter)) {
                String strPrefix = (parameter.substring(0, 1)).toUpperCase();
                String strTemp = parameter.substring(1);
                String getMethodName = "is" + strPrefix + strTemp;
                
                Method subMethod = dbClass.getMethod(getMethodName, null);
                Object tmp = subMethod.invoke(object, null);
                if (tmp!=null) {
                    retValue = ((Boolean) tmp).booleanValue();
                }
            } //end if
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValue;
    }
    
    public static int toInteger(String s) {
        return toInteger(s, 0);
    }
    
    public static int toInteger(String s, int _default) {
        int outValue = 0;
        try {
            outValue = Integer.parseInt(s);
        } catch(Exception e) {
            outValue = _default;
        }
        return outValue;
    }
    
    public static String[] toArrayString(String s) {
        String[] arValue = null;
        try {
            if (!RutString.isEmptyString(s)) {
                arValue = s.split("\\|");
            } else {
                arValue = new String[0];
            }
        } catch(Exception e) {
            arValue = new String[0];
        }
        return arValue;
    }
    
    public static String toString(HashMap hValue, String keyCode) {
        if (hValue != null && hValue.containsKey(keyCode)) {
            return nullToStr((String) hValue.get(keyCode));
        } else {
            return "";
        }
    }
    
    // ##04 BEGIN
    public static String getObjectId(Object bean) {
        if (bean != null) {
            return String.valueOf(bean.hashCode());
        } else {
            return "";
        }
    }
    // ##04 END
    
    public static HashMap mergeHashMap(HashMap _default, String[] arKeyValue, String[] arKeyValueUncheck) {
        HashMap hBean = (_default == null) ? new HashMap() : _default;
          
        // put value from arKeyValue
        if (arKeyValue != null && arKeyValue.length > 0) {
            for (int i=0;i<arKeyValue.length;i++) { //for 1
                if (!RutString.isEmptyString(arKeyValue[i])) { //if 1
                    hBean.put(arKeyValue[i], arKeyValue[i]);
                } //end if 1
            } //end for 1    
        }
          
        // remove data from arKeyValueUncheck
        if (arKeyValueUncheck != null && arKeyValueUncheck.length > 0) {
            for (int i=0;i<arKeyValueUncheck.length;i++) { //for 1
                if (!RutString.isEmptyString(arKeyValueUncheck[i])) { //if 1
                    hBean.remove(arKeyValueUncheck[i]);
                } //end if 1
            } //end for 1    
        }
          
        return hBean;                    
    }
    
    public static List createMessageList(String strMsg, String strPipe, String strParam) {
        StringTokenizer st = new StringTokenizer(strMsg, strPipe);
        List messageList = new ArrayList();
        HashMap hBean = null;
        
        while (st.hasMoreTokens()) {
            String message = st.nextToken();
            if (message == null || isEmptyString(message)) {
                break;
            } else {
                StringTokenizer subSt = new StringTokenizer(message, strParam);
                String msgCode = subSt.nextToken();
                List argumentList = new ArrayList();
                
                while (subSt.hasMoreTokens()) {
                    argumentList.add(subSt.nextToken());
                }
                
                hBean = new HashMap();
                hBean.put("MESSAGE_CODE", msgCode);
                hBean.put("MESSAGE_PARAMETER", argumentList);
                
                messageList.add(hBean);
            }
        }
        
        return messageList;
    }
    
    public static String convertStringToUnicode(String str) {
        StringBuffer ostr = new StringBuffer();
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);

            if ((ch >= 0x0020) && (ch <= 0x007e)) { // Does the char need to be converted to unicode? 
                ostr.append(ch); // No.
                
            } else { // Yes.
                ostr.append("\\u"); // standard unicode format.
                String hex = Integer.toHexString(str.charAt(i) & 0xFFFF); // Get hex value of the char. 
                for (int j=0; j<4-hex.length(); j++) { // Prepend zeros because unicode requires 4 digits
                    ostr.append("0");
                }
                ostr.append(hex.toLowerCase()); // standard unicode format.
            }
        }
        return (new String(ostr)); //Return the stringbuffer cast as a string.
    }
    
    public static String convertStringToUTF(String name) {   
        try {
            Charset utf8charset = Charset.forName("UTF-8");
            ByteBuffer inputBuffer = ByteBuffer.wrap(name.getBytes());
            CharBuffer data = utf8charset.decode(inputBuffer);
            
            ByteBuffer outputBuffer = utf8charset.encode(data);
            byte[] outputData = outputBuffer.array();
            name = new String(outputData, utf8charset.name());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return name;   
    } 
    
    public static String padLeft(String strValue, int size, String strPad) {   
        StringBuffer sb = new StringBuffer(strValue);   
        while (sb.length() < size) {     
            sb.insert(0, strPad);
        }   
        return sb.toString(); 
    } 
    
    public static String padRight(String strValue, int size, String strPad) {   
        StringBuffer sb = new StringBuffer(strValue);   
        while (sb.length() < size) {     
            sb.append(strPad);   
        }   
        return sb.toString(); 
    }
    
}
