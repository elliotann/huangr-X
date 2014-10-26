package com.easysoft.framework.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * json日期处理器
 * User: Administrator
 * Date: 14-4-16
 * Time: 下午10:15
 * To change this template use File | Settings | File Templates.
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DateFormat dateFormat;
    public DateJsonValueProcessor(String format){
        if(format==null) {
            dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        }else{
            dateFormat = new SimpleDateFormat(format);
        }
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

 
    public Object processObjectValue(String s, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value){
        if(value==null){
            return null;
        }
        return dateFormat.format((Date)value);
    }
}
