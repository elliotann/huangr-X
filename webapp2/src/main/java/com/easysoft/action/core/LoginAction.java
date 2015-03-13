package com.easysoft.action.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.framework.utils.HttpUtil;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.IAdminUserManager;
import com.easysoft.member.backend.manager.UserContext;
@Controller
@RequestMapping({"/core/admin/login"})
public class LoginAction extends BaseController {
	
	@RequestMapping(params = {"logout"})
    @ResponseBody
    public AjaxJson logout(){
    	AjaxJson ajaxJson = new AjaxJson();
    	WebSessionContext<UserContext> sessonContext = ThreadContextHolder.getSessionContext();
		sessonContext.removeAttribute(UserContext.CONTEXT_KEY);
    	return ajaxJson;
    }
	@RequestMapping(params = {"login"})
    @ResponseBody
    public AjaxJson login(HttpServletResponse httpResponse,
			HttpServletRequest httpRequest){
		AjaxJson ajaxJson = new AjaxJson();
		String username = httpRequest.getParameter("username");
		String password = httpRequest.getParameter("password");
		String valid_code = httpRequest.getParameter("valid_code");
		
		try {
			
			/*
			 * 登录校验
			 */
			IAdminUserManager userManager =SpringContextHolder.getBean("adminUserManager");
			userManager.login(username, password);
 
			StringBuffer json = new StringBuffer();

            json.append("{\"result\":0}");
			String remember_login_name = httpRequest.getParameter("remember_login_name");
			if(!StringUtil.isEmpty(remember_login_name)){ //记住用户名
				HttpUtil.addCookie(httpResponse, "loginname", username, 365 * 24 * 60 * 60);
			}else{  //删除用户名
				HttpUtil.addCookie(httpResponse, "loginname", "", 0);
			}
			ajaxJson.setMsg("登录成功");
			 
		} catch (RuntimeException exception) {
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(exception.getMessage());
			exception.printStackTrace();
			this.logger.error(exception.getMessage(),exception.fillInStackTrace());
			Response response = new StringResponse();
			response.setContent("{\"result\":1,\"message\":\""+exception.getMessage()+"\"}");

		}	 
	

    	return ajaxJson;
    }
	@RequestMapping(params = {"toLogin"})
    public String toLogin(){
    	return "/adminthemes/default/login";
    }
}
