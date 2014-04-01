package com.easysoft.core.dispatcher.processor;

import com.easysoft.core.dispatcher.core.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author andy
 * @since : 1.0
 */
public interface Processor {

	/**
	 * 
	 * @param mode
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest);

}