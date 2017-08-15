package com.rclgroup.dolphin.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class PmsFileUtil {

    public enum SortBy {
        Filename,
        ModifiedTime;
    }

    public enum SortDirection {
        ASC,
        DESC;
    }

   

   


    /**
     * Save file to specified path
     * @param filename Name of file to be saved
     * @param path Target path to save file to.
     * @param data File data content.
     * @throws FileNotFoundException Throw if file path invalid.
     * @throws IOException Throw if failure during save file.
     */
    public static void saveFile(final String filename, final String path,
                                final InputStream in) throws FileNotFoundException,
                                                             IOException {
        String fullpath = path;
        // verify file path
        if (!fullpath.endsWith(File.separator)) {
            fullpath += File.separator;
        }
        fullpath += filename;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fullpath);
            int length = 0;
            byte buffer[] = new byte[1024];

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.flush();
        } finally {
            if (in != null) {
                try {
                    in.close();
                }catch(Exception ex) {
                    
                }
            }
            if (out != null) {
                try {
                out.close();
                } catch(Exception ex) {}
            }
        }
    }


    /**
     * Copy specified sorce file to destination file.
     * @param dstFilename
     * @param dstFilepath
     * @param srcFilename
     * @param srcFilepath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyFile(final String dstFilename,
                                final String dstFilepath,
                                final String srcFilename,
                                final String srcFilepath) throws FileNotFoundException,
                                                                 IOException {
        String srcFullpath = srcFilepath;
        // verify file path
        if (!srcFullpath.endsWith(File.separator)) {
            srcFullpath += File.separator;
        }
        srcFullpath += srcFilename;

        String dstFullpath = dstFilepath;
        // verify file path
        if (!dstFullpath.endsWith(File.separator)) {
            dstFullpath += File.separator;
        }
        dstFullpath += dstFilename;
        // read from source file and write to destination file.
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(dstFullpath));
            in = new BufferedInputStream(new FileInputStream(srcFullpath));

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        } finally {
            // close file streams
            if (in != null) {
                in.close();
            }
            if (out != null) {

                out.close();
            }
        }
    }
    /**
     * Copy file content to the given output stream.
     * @param srcFilepath
     * @param out
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyFileToStream(String srcFilepath,
                                OutputStream out
                               ) throws FileNotFoundException,
                                                                 IOException {
      
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(srcFilepath));

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
           
        } finally {
            // close file streams
            if (in != null) {
                in.close();
            }
            
        }
    }
    /**
     * Delete filename from the given path
     * @param filename
     * @param filepath
     */
    public static boolean deleteFile(String filename,
                                     String filepath) throws IOException {
        String srcFullpath = filepath;
        // verify file path
        if (!srcFullpath.endsWith(File.separator)) {
            srcFullpath += File.separator;
        }
        srcFullpath += filename;

        File file = new File(srcFullpath);
        if (file.exists()) {
            return file.delete();
        }
        return false;

    }
    /**
     * Delete file at specified path.
     * @param strFullname
     * @return
     */
    public static boolean deleteFile(String strFullname) {
        File file = new File(strFullname);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Check the input filename existing in the given filepath
     * @param filename
     * @param filepath
     * @return true if file existed, otherwise return false.
     */
    public static boolean checkFileExists(String filename, String filepath) {
        String srcFullpath = filepath;
        // verify file path
        if (!srcFullpath.endsWith(File.separator)) {
            srcFullpath += File.separator;
        }
        srcFullpath += filename;

        File file = new File(srcFullpath);
        return file.exists();
    }
}
