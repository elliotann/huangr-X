package com.easysoft.jeap.framework.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理类
 * Created by huangxa on 2014/7/5.
 */
public class DateUtil {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
    /**
     * 把日期转换成字符串格式
     * @param date 传入的日期
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String formatDate2String(Date date,String pattern){
        if(date==null) return "";
        if(StringUtils.isEmpty(pattern)){
            pattern = DEFAULT_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }
}
