package com.easysoft.core.dispatcher.processor.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.member.backend.manager.UserContext;

public class LogoutProcessor implements Processor {

	
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		
		WebSessionContext<UserContext> sessonContext = ThreadContextHolder.getSessionContext();
		Response response= new StringResponse();
		sessonContext.removeAttribute(UserContext.CONTEXT_KEY);
		response.setContent("<script>location.href='index.jsp'</script>");
		return response;
	}
 
}
