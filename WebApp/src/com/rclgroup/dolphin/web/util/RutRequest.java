/*-----------------------------------------------------------------------------------------------------------  
RutRequest.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 24/11/2009
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-       -Short Description  
01 25/05/11 NIP        PD_CR_240211-02 uploadReportFile and getReportFileNameByTimestamp function for report file.
02 11/07/11 NIP        PD_CR_240211-02 uploadReportFile and getReportFileNameByTimestamp function for report file.
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import com.rclgroup.dolphin.web.common.RcmConstant;

import java.io.File;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class RutRequest {
    public RutRequest() {
        super();
    }
    
    /**
     * get the session object
     * @param session
     * @param parameter
     * @param objectClass
     * @return session object
     */
    public static Object getSessionObject(HttpSession session, String parameter, Class objectClass) {
        Object bean = null;
        if (session != null && parameter != null && !"".equals(parameter) && objectClass != null) {
            if (session.getAttribute(parameter) != null &&  objectClass.isInstance(session.getAttribute(parameter))) {
                bean = session.getAttribute(parameter);
            }    
        }
        return bean;
    }
    
    /**
     * @param request
     * @param hParameter
     * @param parameterCode
     * @return Object
     */
    public static Object getParameterValueByCode(HttpServletRequest request, HashMap hParameter, String parameterCode) {
        if (hParameter != null && hParameter.containsKey(parameterCode)) {
            return hParameter.get(parameterCode);
        } else if (request != null) {
            return RutString.nullToStr(request.getParameter(parameterCode));
        } else {
            return null;
        }
    }
    
    /**
     * @param request
     * @param parameterCode
     * @return String
     */
    public static String getParameterValueByCode(HttpServletRequest request, String parameterCode) {
        String retValue = null;
        if (request != null && !RutString.isEmptyString(parameterCode)) {
            try {
                HashMap hParameter = (HashMap) request.getAttribute(RcmConstant.PARAMETER_NAME_FORM_MULTIPART);
                if (hParameter != null) {
                    retValue = RutString.nullToStr(hParameter.get(parameterCode));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (RutString.isEmptyString(retValue)) {
                retValue = RutString.nullToStr(request.getParameter(parameterCode));
            }
        }
        return retValue;
    }
    
    /**
     * @param request
     * @param parameterCode
     * @return String
     */
    public static Object getParameterOjbectByCode(HttpServletRequest request, String parameterCode) {
        Object retValue = null;
        if (request != null && !RutString.isEmptyString(parameterCode)) {
            try {
                HashMap hParameter = (HashMap) request.getAttribute(RcmConstant.PARAMETER_NAME_FORM_MULTIPART);
                if (hParameter != null) {
                    retValue = hParameter.get(parameterCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (retValue == null) {
                retValue = RutString.nullToStr(request.getParameter(parameterCode));
            }
        }
        return retValue;
    }
    
    /**
     * @param request
     * @return HashMap
     */
    public static HashMap loadParameterMultipart(HttpServletRequest request) {
        HashMap hBean = new HashMap();
        if (request != null && ServletFileUpload.isMultipartContent(request)) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List listItem = upload.parseRequest(request);
                
                if (listItem != null && listItem.size() > 0) {
                    Iterator iter = listItem.iterator();
                    String fieldName = null;
                    String fieldValue = null;
                    while (iter.hasNext()) {
                        Object object = iter.next();
                         
                        if (object instanceof FileItem ) {
                            FileItem  item = (FileItem ) object;
                             
                            if (item.isFormField()) {
                                fieldName = item.getFieldName();
                                fieldValue = item.getString();
                                //System.out.println("#### fieldName = "+fieldName+" : fieldValue = "+fieldValue+" : "+RutString.convertStringToUTF(fieldValue));
                                
                                if (!RutString.isEmptyString(fieldName)) {
                                    if (fieldValue != null) {
                                        hBean.put(new String(fieldName), new String(fieldValue.getBytes(), "UTF-8"));
                                        //hBean.put(new String(fieldName), fieldValue);
                                    } else {
                                        hBean.put(new String(fieldName), "");
                                    }
                                }
                            } else {
                                fieldName = item.getFieldName();
                                if (!RutString.isEmptyString(fieldName)) {
                                    hBean.put(new String(fieldName), item);
                                }
                            }
                        } //end if (object instanceof FileItemStream) {
                    } //end while (iter.hasNext()) {
                } //end if (listItem != null && listItem.size() > 0) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        } //end if (request != null && ServletFileUpload.isMultipartContent(request)) {
        return hBean;        
    }
    
    /**
     * @param screenCode
     * @return String
     */
    public static String getPrefixFileNameByScreenCode(String screenCode) {
        if (!RutString.isEmptyString(screenCode)) {
            return screenCode.trim().toLowerCase();
        } else {
            return "";
        }
    }
    
    /**
     * @param fileItem
     * @return String
     */
    public static String getFileExtensionByFileItem(FileItem fileItem) {
        String fileExt = "";
        if (fileItem != null) {
            try {
                String contentType = fileItem.getContentType();
                if ("image/bmp".equalsIgnoreCase(contentType)) {
                    fileExt = "bmp";
                } else if ("image/gif".equalsIgnoreCase(contentType)) {
                    fileExt = "gif";
                } else if ("image/jpeg".equalsIgnoreCase(contentType) || "image/pjpeg".equalsIgnoreCase(contentType)) {
                    fileExt = "jpg";
                } else if ("image/png".equalsIgnoreCase(contentType) || "image/x-png".equalsIgnoreCase(contentType)) {
                    fileExt = "png";
                } else if ("image/tiff".equalsIgnoreCase(contentType)) {
                    fileExt = "tiff";
                } else {
                    fileExt = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileExt;
    }
    
    /**
     * @param screenCode
     * @param fileItem
     * @return String
     */
    public static String getFileNameByTimestamp(String screenCode, FileItem fileItem) {
        return getPrefixFileNameByScreenCode(screenCode) + "_" + RutDate.getSysTimestamp().getTime() + "." + getFileExtensionByFileItem(fileItem);
    }
    
    /**
     * @param screenCode
     * @param fileItem
     * @return String
     */
    // ##01
    public static String getReportFileNameByTimestamp(String screenCode, FileItem fileItem) {
        String fileName = (fileItem.getName()!=null && !fileItem.getName().trim().equals(""))?fileItem.getName().trim().split("\\.")[0]:"";
        return fileName + "_" + RutDate.getSysTimestamp().getTime() + "." + "rdf";
    }
    // ##01
    
     /**
      * @param screenCode
      * @param fileItem
      * @return String
      */
     // ##02
     public static String getReportFileNameByTimestamp(String screenCode, String fileNameItem) {
         return fileNameItem /*+ "_" + RutDate.getSysTimestamp().getTime()*/ + "." + "rdf";
     }
     // ##02
    
    /**
     * @param screenCode
     * @param fileItem
     * @return String
     */
    public static String getFileNameByFileItem(String screenCode, FileItem fileItem) {
        String fileName = "";
        if (fileItem != null) {
            try {
                fileName = getPrefixFileNameByScreenCode(screenCode);
                fileName += (!RutString.isEmptyString(fileName)) ? "_" : "";
                fileName += fileItem.getFieldName();
                
                if (!RutString.isEmptyString(fileName)) {
                    fileName = fileName.trim().replaceAll(" ", "_").toLowerCase();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fileName += "." + getFileExtensionByFileItem(fileItem);
        return fileName;
    }
    
    /**
     * @param fileItem
     * @param uploadPath
     * @param fileName
     * @return HashMap
     */
    public static String uploadFile(FileItem fileItem, String uploadPath, String fileName) {
        String errorMessage = "";
        if (fileItem != null && !RutString.isEmptyString(uploadPath) && !RutString.isEmptyString(fileName)) {
            try {
                String fileContentType = fileItem.getContentType();
                long fileSize = fileItem.getSize();
                    
                System.out.println("[RutRequest][uploadFile]: fileContentType = "+fileContentType);
                System.out.println("[RutRequest][uploadFile]: fileSize = "+fileSize);
                    
                if (errorMessage == "" && fileSize > RcmConstant.RESOURCE_FILE_SIZE_MIX) {
                    errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_FILE_SIZE_OVER");
                }
                if (errorMessage == "") {
                    boolean isError = true;
                    for (int i = 0; i < RcmConstant.RESOURCE_FILE_CONTENT_TYPE.length; i++) {
                        if (RcmConstant.RESOURCE_FILE_CONTENT_TYPE[i].equalsIgnoreCase(fileContentType)) {
                            isError = false;
                            break;
                        }
                    }
                    
                    if (isError) {
                        errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_FILE_TYPE_NOT_IN_RANGE", new String[] {fileContentType});
                    }
                }
                
                if ("".equals(errorMessage)) {
                    // Upload file
                    fileItem.write(new File(uploadPath, fileName));    
                }
                
            } catch(Exception e) {
                e.printStackTrace();
                errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_INTERNAL_OCCURS_ERROR");
            }
        } else {
            errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_INTERNAL_OCCURS_ERROR");
        }
        return errorMessage;
    }
    
    /**
     * @param fileItem
     * @param uploadPath
     * @param fileName
     * @return HashMap
     */
    // ##01 BEGIN
    public static String uploadReportFile(FileItem fileItem, String uploadPath, String fileName) {
        String errorMessage = "";
        if (fileItem != null && !RutString.isEmptyString(uploadPath) && !RutString.isEmptyString(fileName)) {
            try {
                String fileContentType = fileItem.getContentType();
                long fileSize = fileItem.getSize();
                    
                System.out.println("[RutRequest][uploadReportFile]: fileContentType = "+fileContentType);
                System.out.println("[RutRequest][uploadReportFile]: fileSize = "+fileSize);
                    
                if (errorMessage == "" && fileSize > RcmConstant.REPORT_FILE_SIZE_MIX) {
                    errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_FILE_SIZE_OVER");
                }
                if (errorMessage == "") {
                    boolean isError = true;
                    for (int i = 0; i < RcmConstant.REPORT_FILE_CONTENT_TYPE.length; i++) {
                        if (RcmConstant.REPORT_FILE_CONTENT_TYPE[i].equalsIgnoreCase(fileContentType)) {
                            isError = false;
                            break;
                        }
                    }
                    
                    if (isError) {
                        errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_FILE_TYPE_NOT_IN_RANGE", new String[] {fileContentType});
                    }
                }
                
                if ("".equals(errorMessage)) {
                    // Upload file
                    fileItem.write(new File(uploadPath, fileName));    
                }
                
            } catch(Exception e) {
                e.printStackTrace();
                errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_INTERNAL_OCCURS_ERROR");
            }
        } else {
            errorMessage = new RutMessage().getErrorMessage("RCM.UPLOAD_FILE.UPLOAD.EXCEPTION_INTERNAL_OCCURS_ERROR");
        }
        return errorMessage;
    }
    // ##01 END
}
