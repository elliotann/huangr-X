package com.easysoft.core.dispatcher.processor.backend.support;

import com.easysoft.core.dispatcher.AbstractFacadePageParser;
import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.Request;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.utils.JspUtil;

/**
 * 后台页面包装器<br>
 * 用/admin/main_frame.jsp包装业务页面 <br>
 * @author andy
 */
public class BackTemplateWrapper extends AbstractFacadePageParser {
	
	public BackTemplateWrapper(FacadePage page, Request request) {
		super(page, request);
	}

	
	protected Response parse(Response response) {
		httpRequest.setAttribute("content", response.getContent());
		String content  = JspUtil.getJspOutput("/admin/main_frame.jsp", httpRequest, httpResponse);
		response.setContent(content);
		return response;
	}

}
