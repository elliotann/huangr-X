package com.easysoft.core.dispatcher.processor.backend;

import com.easysoft.core.context.ConnectType;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.LocalRequest;
import com.easysoft.core.dispatcher.core.Request;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.processor.backend.support.BackPageGetter;
import com.easysoft.core.dispatcher.processor.backend.support.MenuJsonGetter;
import com.easysoft.core.model.Site;
import com.easysoft.member.backend.manager.IUserService;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		 if( ! uri.startsWith("/admin/login")
			&& ! uri.startsWith("/admin/index.jsp") 
			&& !uri.equals("/admin/")
			&& !uri.equals("/admin")
			){
			 IUserService userService = UserServiceFactory.getUserService();
			 if(!userService.isUserLoggedIn()){
				 	List<String> msgs = new ArrayList<String>();
				 	Map<String ,String> urls = new HashMap<String,String>();
				 	msgs.add("您尚未登陆或登陆已经超时，请重新登录。");
				 	String ctx = httpRequest.getContextPath();
				 	urls.put("点进这里进入登录页面", ctx+"/admin/");
				 	httpRequest.setAttribute("msgs", msgs);
				 	httpRequest.setAttribute("urls", urls);
					httpRequest.setAttribute("target", "_top");	
					Request request = new LocalRequest();
					Response response = request.execute("/admin/message.jsp", httpResponse,
							httpRequest);
					return response;
			 }
		 }
		
		Processor processor  =null;
		
		Site site  = EsfContext.getContext().getCurrentSite();
 
		model = ConnectType.local;
		FacadePage page = new FacadePage(site);
 
		page.setUri(uri);
		
		if(uri.startsWith("/admin/menu.do")){
			processor = new MenuJsonGetter(page);
		}else if( uri.startsWith("/admin/login.do") ){
			processor = new LoginProcessor();
		}else if( uri.startsWith("/admin/logout.do") ) {
			processor = new LogoutProcessor();
		}else if(uri.startsWith("/admin/plugin")){
			processor = new AjaxPluginProcessor();
		}else{
			processor = new BackPageGetter(page); //负责显示后台内容界面
		}
 		
		Response response = processor.process(model, httpResponse, httpRequest);

		return response;
	}
}
