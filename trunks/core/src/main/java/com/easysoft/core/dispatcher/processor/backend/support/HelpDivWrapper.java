package com.easysoft.core.dispatcher.processor.backend.support;

import com.easysoft.core.dispatcher.AbstractFacadePageParser;
import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.Request;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;

import javax.servlet.http.HttpServletRequest;

public class HelpDivWrapper extends AbstractFacadePageParser {

	public HelpDivWrapper(FacadePage page, Request request) {
		super(page, request);
	}

	@Override
	protected Response parse(Response response) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		String content  = response.getContent();
		content = content+"<div id=\"HelpCtn\" class=\"popup-info-box\"><div class=\"bl\"><div class=\"br\">";
		content = content+"<div class=\"bd user-info\"><span id=\"HelpClose\" class=\"closebtn\" ></span>";
		content = content +"<span id=\"HelpBody\"></span>";
		content = content +"</div>";
		content = content +"</div>";
		content = content +"</div>";
		content = content +"<div class=\"bt\">";
		content = content +"<div class=\"corner bt-l\"></div>";
		content = content +"<div class=\"mid\"></div>";
		content = content +"<div class=\"corner bt-r\"></div>";
		content = content +"</div>";
		content = content +"</div>";
		response.setContent(content);
		return response;
	}

}
