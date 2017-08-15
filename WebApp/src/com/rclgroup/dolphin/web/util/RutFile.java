/*-----------------------------------------------------------------------------------------------------------  
RutFile.java
------------------------------------------------------------------------------------------------------------- 
Copyright RCL Public Co., Ltd. 2007 
-------------------------------------------------------------------------------------------------------------
Author Wuttitorn Wuttijiaranai 02/03/10
- Change Log ------------------------------------------------------------------------------------------------  
## DD/MM/YY -User-     -TaskRef-      -Short Description  
-----------------------------------------------------------------------------------------------------------*/

package com.rclgroup.dolphin.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.List;


public class RutFile {
    
    public RutFile() {
        super();
    }
    
    public static void closeFileWriter(FileWriter fileWriter) {
        if (fileWriter != null) {
            try { 
                fileWriter.close(); 
            } catch (Exception er) { 
                er.printStackTrace(); 
            }
        }    
    }
    
    public static void writeMsgHeader(FileWriter fileWriter, String msgHeader) {
        if (fileWriter != null && !RutString.isEmptyString(msgHeader)) {
            try {
                fileWriter.write("########################################################\n");
                fileWriter.write("## Description : " + msgHeader + "\n");
                fileWriter.write("## Created Date : " + RutDate.getDefaultDateStringFromTimestamp(RutDate.getSysTimestamp()) + "\n");
                fileWriter.write("########################################################\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void writeMsgByList(FileWriter fileWriter, List arBean) {
        if (fileWriter != null && arBean != null && arBean.size() > 0) {
            try {
                String msg = null;
                for (int i=0;i<arBean.size();i++) {
                    msg = (String) arBean.get(i);
                    fileWriter.write(msg + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void createFileByList(String filePath, String fileName, String msgHeader, List arBean) {
        if (!RutString.isEmptyString(filePath) && !RutString.isEmptyString(fileName)) {
            FileWriter fileWriter = null; 
            try {
                String filePathFull = filePath.trim() + "/" + fileName.trim();
                
                File file = new File(filePathFull);
                fileWriter = new FileWriter(file, false);
                
                writeMsgHeader(fileWriter, msgHeader);
                writeMsgByList(fileWriter, arBean);
                fileWriter.write("\n");
                fileWriter.write("\n");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeFileWriter(fileWriter);
            }
        }
    }
    
    public static void extendFileByList(String filePath, String fileName, String msgHeader, List arBean) {
        if (!RutString.isEmptyString(filePath) && !RutString.isEmptyString(fileName)) {
            FileWriter fileWriter = null; 
            try {
                String filePathFull = filePath.trim() + "/" + fileName.trim();
                
                File file = new File(filePathFull);
                fileWriter = new FileWriter(file, true);
                
                writeMsgHeader(fileWriter, msgHeader);
                writeMsgByList(fileWriter, arBean);
                fileWriter.write("\n");
                fileWriter.write("\n");
                 
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeFileWriter(fileWriter);
            }
        }
    }
    
    public static boolean createFileByInputStream(InputStream inputStream, String fileName) {
        boolean isSuccess = false;
        if (inputStream != null && !RutString.isEmptyString(fileName)) {
            OutputStream outputStream = null;
            try { 
                File file = new File(fileName);
                outputStream = new FileOutputStream(file);
                
                int length = 0;
                byte buffer[] = new byte[1024];
                
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                
                isSuccess = true;
                
            } catch (Exception er) { 
                er.printStackTrace(); 
            } finally {
                if (outputStream != null) {
                    try { outputStream.close(); } catch (Exception e) { e.printStackTrace(); }
                }
                if (inputStream != null) {
                    try { inputStream.close(); } catch (Exception e) { e.printStackTrace(); }
                }
            }
        }
        return isSuccess;
    }
    
}
