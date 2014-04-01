package com.easysoft.core.dispatcher.processor.facade;


import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.Request;
import com.easysoft.core.dispatcher.core.RequestFactory;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.processor.Processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author andy
 * @since : 1.0
 */
public abstract class AbstractFacadeProcessor implements Processor {

	protected FacadePage page;
	protected HttpServletRequest httpRequest;
	protected HttpServletResponse httpResponse;
	protected int mode;
	protected Request request;

	/**
	 * 
	 * @param page
	 */
	public AbstractFacadeProcessor(FacadePage page) {
		this.page = page;
	}

	/**
	 * 
	 * @param mode
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		
		this.mode = mode;
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
		request = RequestFactory.getRequest(mode);
		
		return process();
	}

	protected abstract Response process();

}