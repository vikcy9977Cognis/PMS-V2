package com.rclgroup.dolphin.web.ui.pms;

import com.rclgroup.dolphin.web.common.PmsConstant;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PmsServletContextListener implements ServletContextListener {
    private ServletContext context = null;

    public void contextInitialized(ServletContextEvent event)  {
        context = event.getServletContext();
        
        // Initialize context attribute
        context.setAttribute("pmsVersion", PmsConstant.PMS_VERSION);
        
        
        Throwable exception = null;
        boolean initSuccess = false;
        // Load PMS key file
        try {
           /*
            DesEncrypter encrypter2 = new DesEncrypter();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream("/Users/khomsun/Documents/pmlicens.txt"), "UTF-8"));
            String line= reader.readLine();
            String decrypted = encrypter2.decrypt(line);
            reader.close();
            
            
            System.out.println("Decypt:" + decrypted);
*/
         
            DesEncrypter encrypter = new DesEncrypter();
            
            InputStream in = context.getResourceAsStream("/WEB-INF/pmkey.dat");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line= reader.readLine();
             String decrypted = encrypter.decrypt(line);
             reader.close();

            context.setAttribute(PmsConstant.PM_KEY, decrypted);
            initSuccess = true;
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            exception = e;
        } catch (IOException e) {
            e.printStackTrace();
            exception = e;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            exception = e;
        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        }finally {
            if (!initSuccess) {
               // System.exit(1);
               // throw (Exception)exception;
                System.err.println("PMS Initialize not properly because error during get PM key");
            }
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        context = event.getServletContext();
    }
    
    class DesEncrypter {
      Cipher ecipher;

      Cipher dcipher;

        byte[] privateKey = new byte[] {0x00, 0x10, 0x20, 0x30, 0x40, 0x50, 0x60, 0x70};
        
        DesEncrypter() throws Exception {
        
          SecretKeyFactory sf = SecretKeyFactory.getInstance("DES");
          
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, sf.generateSecret(new DESKeySpec(privateKey)));
        dcipher.init(Cipher.DECRYPT_MODE, sf.generateSecret(new DESKeySpec(privateKey)));
        }

      public String encrypt(String str) throws Exception {
        // Encode the string into bytes using utf-8
        byte[] utf8 = str.getBytes("UTF8");

        // Encrypt
        byte[] enc = ecipher.doFinal(utf8);

        // Encode bytes to base64 to get a string
        return new sun.misc.BASE64Encoder().encode(enc);
      }

      public String decrypt(String str) throws Exception {
        // Decode base64 to get bytes
        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

        byte[] utf8 = dcipher.doFinal(dec);

        // Decode using utf-8
        return new String(utf8, "UTF8");
      }
    }
}
