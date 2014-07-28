package com.easysoft.jeap.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2014/7/13.
 */
public class MD5Util {
    /**
     * MD5加密方法
     *
     * @param str
     *            String
     * @return String
     */
    public static String md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
        byte[] resultByte = messageDigest.digest(str.getBytes());
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < resultByte.length; ++i) {
            result.append(Integer.toHexString(0xFF & resultByte[i]));
        }
        return result.toString();
    }
    public static String decode(String keyword) {
        try {
            keyword = URLDecoder.decode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return keyword;
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.md5("1"));
    }
}
