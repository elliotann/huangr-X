package com.easysoft.core.dispatcher.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author andy
 * @version 1.0
 */
public interface Request {



	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse, HttpServletRequest httpRequest);

	/**
	 * 
	 * @param uri
	 */
	public Response execute(String uri);

	
	/**
	 * 设置请求时的参数
	 * @param params
	 */
	public void setExecuteParams(Map<String,String> params);
	
}