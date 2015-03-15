package com.easysoft.core.dispatcher.processor.backend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easysoft.core.context.ConnectType;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.processor.backend.support.BackPageGetter;
import com.easysoft.core.model.Site;
import com.easysoft.member.backend.manager.IUserService;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;

/**
 * 后台页面解析器
 * @author andy
 */
public class BackgroundProcessor implements Processor {
 
	/**
	 * 
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int model,HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		String uri = httpRequest.getServletPath(); 
		
		
		//用户身份校验
		 if( uri.startsWith("/admin")){
			 IUserService userService = UserServiceFactory.getUserService();
			 if(!userService.isUserLoggedIn()){
	
					String ctx = httpRequest.getContextPath();
					Response response = new StringResponse();
					response.setContent(ctx+"/core/admin/login.do?toLogin");
					response.setStatusCode("-1");
					return response;
			 }
		 }else{
			
		 }
		
		Processor processor  =null;
		
		Site site  = EsfContext.getContext().getCurrentSite();
 
		model = ConnectType.local;
		FacadePage page = new FacadePage(site);
 
		page.setUri(uri);
		
		if(uri.startsWith("/admin/plugin")){
			processor = new AjaxPluginProcessor();
		}else{
			processor = new BackPageGetter(page); //负责显示后台内容界面
		}
 		
		Response response = processor.process(model, httpResponse, httpRequest);

		return response;
	}
}
