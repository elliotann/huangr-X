package com.easysoft.core.dispatcher.core;

import com.easysoft.core.utils.JspUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author andy
 * @version 1.0
 * @created 09-十月-2009 22:22:30
 */
public class LocalRequest implements Request {

	public LocalRequest(){

	}

	public void setExecuteParams(Map<String,String> params){
	}

	/**
	 * 
	 * @param uri
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response execute(String uri, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		
		String content = JspUtil.getJspOutput(uri, httpRequest, httpResponse);
		//content= StringUtil.compressHtml(content);
		Response response = new StringResponse();
		response.setContent(content);
		
		return response;
	}

	/**
	 * 
	 * @param uri
	 */
	public Response execute(String uri){
		return null;
	}

}