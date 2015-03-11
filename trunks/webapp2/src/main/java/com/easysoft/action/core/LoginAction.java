package com.easysoft.action.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
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
    public AjaxJson login(){
    	AjaxJson ajaxJson = new AjaxJson();
    	WebSessionContext<UserContext> sessonContext = ThreadContextHolder.getSessionContext();
		sessonContext.removeAttribute(UserContext.CONTEXT_KEY);
    	return ajaxJson;
    }
}
