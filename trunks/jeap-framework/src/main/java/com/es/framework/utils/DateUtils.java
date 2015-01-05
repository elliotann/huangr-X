package com.es.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期处理工具类
 * @author huangxa
 *
 */
public class DateUtils {
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	/**
	 * 把日期根据格式转换成相应的字符串表示
	 * @param date
	 * @param pattern
	 * @return 返回转换过的字符串日期
	 */
	public static String formatData2String(Date date,String pattern){
		if(date == null){
			return "";
		}
		if(StringUtils.isEmpty(pattern)){
			pattern = DEFAULT_PATTERN;
		}
		DateFormat df = new SimpleDateFormat();
		return df.format(date);
	}

}
