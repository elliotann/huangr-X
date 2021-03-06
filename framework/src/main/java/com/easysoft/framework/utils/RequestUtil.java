package com.easysoft.framework.utils;

import com.easysoft.framework.context.webcontext.ThreadContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * httpRequest常用方法工具
 * @author andy
 * 2010-2-12上午11:34:48
 */
public abstract class RequestUtil {
    private RequestUtil() {
    }

    public static String getDomain() {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        String port = new StringBuilder().append("").append(request.getServerPort()).toString();
        if (port.equals("80"))
            port = "";
        else {
            port = new StringBuilder().append(":").append(port).toString();
        }

        String contextPath = request.getContextPath();
        if (contextPath.equals("/")) {
            contextPath = "";
        }

        String domain = new StringBuilder().append("http://").append(request.getServerName()).append(port).toString();
        domain = new StringBuilder().append(domain).append(contextPath).toString();
        return domain;
    }
	/**
	 * 将request中的参数转为Map
	 * @param request
	 * @return
	 */
	public static Map<String,String> paramToMap(HttpServletRequest request){
		Map<String,String> params = new HashMap<String, String>();
		
		Map rMap = request.getParameterMap();
		Iterator rIter = rMap.keySet().iterator();
		
		while(rIter.hasNext()){
			Object key = rIter.next();
			String value = request.getParameter(key.toString());
			if(key==null || value==null)continue;
			params.put(key.toString(), value.toString());
		}
		
		return params;
		
	}	
	
	
	public static String getRequestUrl(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		String queryString = request.getQueryString();

		String uri = request.getServletPath();

		if (uri == null) {
			uri = request.getRequestURI();
			uri = uri.substring(request.getContextPath().length());
		}

		return uri + ((pathInfo == null) ? "" : pathInfo)
				+ ((queryString == null) ? "" : ("?" + queryString));
	}
	
	/**
	 * 获取完整的url，包括域名端口等
	 * @return
	 */
	public static String getWholeUrl(HttpServletRequest request){
		String servername =request.getServerName();
		String path  = request.getServletPath();
		int port = request.getServerPort();
		
		String portstr="";
		if(port!=80){
			portstr=":"+port;
		}
		String url  = "http://"+servername+portstr+"/"+path;
		return url;
		
	}
	
	
	/**
	 * 获取Integer 的值
	 * @param request
	 * @param name 
	 * @return 如果没有返回null
	 */
	public static Integer getIntegerValue(HttpServletRequest request,String name){
		String value =request.getParameter(name); 
		if(StringUtil.isEmpty(value)){
			return null;
		}else{
			return Integer.valueOf(value);
		}
		
	}
	
	
	public static Double getDoubleValue(HttpServletRequest request,String name){
		String value =request.getParameter(name); 
		if(StringUtil.isEmpty(value)){
			return null;
		}else{
			return Double.valueOf(value);
		}
		
	}
	
	
	
	
	/**
	 * 获取int的值 
	 * @param request
	 * @param name
	 * @return 如果没有返回0
	 */
	public static int getIntValue(HttpServletRequest request,String name){
		String value =request.getParameter(name); 
		if(StringUtil.isEmpty(value)){
			return 0;
		}else{
			return Integer.valueOf(value);
		}
	}
	
	public static String getRequestMethod(HttpServletRequest request){
		String method  = request.getParameter("_method");
		method=method==null?"get":method;
		method=method.toUpperCase();
		return method;
	}
	
	
	
}
