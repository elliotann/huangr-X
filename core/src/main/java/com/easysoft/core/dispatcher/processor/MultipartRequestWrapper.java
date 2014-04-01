package com.easysoft.core.dispatcher.processor;

import javazoom.upload.MultipartFormDataRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {
	private MultipartFormDataRequest mrequest;
	public MultipartRequestWrapper(HttpServletRequest request,MultipartFormDataRequest _mrequest) {
		super(request);
		this.mrequest = _mrequest;
	}
	
	@Override
	public String getParameter(String name) {
		 
		return mrequest.getParameter(name);
	}

	@Override
	public Enumeration getParameterNames() {
	 
		return this.mrequest.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String name) {
		return this.mrequest.getParameterValues(name);
	}

	
 
}
