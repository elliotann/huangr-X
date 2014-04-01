package com.easysoft.core.dispatcher.core;

import java.io.InputStream;

/**
 * @author andy
 * @since : 1.0
 */
public interface Response {

	public String getContent();
	
	public InputStream getInputStream();

	public String getStatusCode();

	public String getContentType();
	
	

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content);

	/**
	 * 
	 * @param code
	 */
	public void setStatusCode(String code);

	/**
	 * 
	 * @param contentType
	 */
	public void setContentType(String contentType);

}