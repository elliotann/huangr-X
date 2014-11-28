package com.easysoft.core.utils;

import com.easysoft.framework.utils.DateUtil;

import java.util.Date;

/**
 * 生成流程号
 * @author : andy.huang
 * @since : 1.0
 */
public class KeyGenerator {
    public static String generateKey(String prefix){
        String key = generateKeyPrefix(prefix)+"0001";
        return key;
    }

    private static String generateKeyPrefix(String prefix){
        return prefix+ DateUtil.toString(new Date(),"YYYYMMDDHHmmss");
    }
}
