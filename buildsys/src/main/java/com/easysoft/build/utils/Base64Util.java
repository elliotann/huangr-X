 package com.easysoft.build.utils;

 import org.apache.commons.codec.binary.Base64;
 import org.apache.commons.codec.binary.Base64InputStream;
 import org.apache.commons.codec.binary.Base64OutputStream;

 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.InputStream;
 import java.io.OutputStream;

 public class Base64Util
 {
   public static final int BUFFER_SIZE = 4096;
   public static final String FILE_EXTENSION_TAG = "bytter";
 
   public static void encodeFile(String toEncodeFilePath, String encodedFilePath)
     throws Exception
   {
     encodeFile(toEncodeFilePath, encodedFilePath, "bytter");
   }
 
   public static void encodeFile(String toEncodeFilePath, String encodedFilePath, String key)
     throws Exception
   {
     byte[] buffer = new byte[4096];
     OutputStream encodeOutput = new Base64OutputStream(new FileOutputStream(encodedFilePath), true);
     if (key != null) {
       byte[] head = Base64.encodeBase64(key.getBytes());
       encodeOutput.write(head, 0, head.length);
     }
 
     int bytesRead = -1;
     InputStream input = new FileInputStream(toEncodeFilePath);
     try {
       while ((bytesRead = input.read(buffer, 0, 4096)) > 0)
         encodeOutput.write(buffer, 0, bytesRead);
     }
     finally {
       input.close();
       encodeOutput.close();
     }
   }
 
   public static void decodeFile(String encodedFilePath, String decodedFilePath)
     throws Exception
   {
     decodeFile(encodedFilePath, decodedFilePath, "bytter");
   }
 
   public static void decodeFile(String encodedFilePath, String decodedFilePath, String key)
     throws Exception
   {
     byte[] buffer = new byte[4096];
     InputStream decodeInput = new Base64InputStream(new FileInputStream(encodedFilePath), false);
     try {
       if (key != null) {
         byte[] encodeHead = Base64.encodeBase64(key.getBytes());
         byte[] head = new byte[encodeHead.length];
         decodeInput.read(head, 0, head.length);
         if (!new String(encodeHead).equals(new String(head)))
           throw new RuntimeException("非法密钥，解密失败！");
       }
     }
     finally {
       decodeInput.close();
     }
 
     OutputStream output = new FileOutputStream(decodedFilePath);
     try {
       int bytesRead = -1;
       while ((bytesRead = decodeInput.read(buffer, 0, 4096)) > 0)
         output.write(buffer, 0, bytesRead);
     }
     finally {
       output.close();
     }
   }
 }

