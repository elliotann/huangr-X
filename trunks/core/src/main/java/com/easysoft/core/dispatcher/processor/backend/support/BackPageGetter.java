package com.easysoft.core.dispatcher.processor.backend.support;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.processor.facade.AbstractFacadeProcessor;
import com.easysoft.core.manager.IAdminThemeManager;
import com.easysoft.core.model.AdminTheme;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.spring.SpringContextHolder;
import com.easysoft.framework.utils.HttpUtil;
import com.easysoft.member.backend.manager.IAdminUserManager;
import com.easysoft.member.backend.model.AdminUser;

/**
 * 后台页面获取器
 * @author andy
 */
public class BackPageGetter extends AbstractFacadeProcessor {
	
	private IAdminThemeManager adminThemeManager;
    private IAdminUserManager adminUserManager;
	public BackPageGetter(FacadePage page) {
		super(page);
	}

	
	protected Response process() {

		Site site = EsfContext.getContext().getCurrentSite();//this.page.getSite();
		
		adminThemeManager = SpringContextHolder.getBean("adminThemeManager");
        this.adminUserManager = ((IAdminUserManager)SpringContextHolder.getBean("adminUserManager"));
		//读取后台使用的模板
		AdminTheme theme = adminThemeManager.get( site.getAdminthemeid());
		String path ="default";
		if(theme!=null){
			path = theme.getPath();
		}
		StringBuffer context = new StringBuffer();


		context.append(ParamSetting.ADMINTHEMES_STORAGE_PATH);
		context.append("/");
		context.append(path);
		
		StringBuffer  staticdomain = new StringBuffer();
		
		/***********************20100920修改：***************************/
//		//静态资源分离模式
//		if(ParamSetting.RESOURCEMODE.equals("1")){
//			staticdomain.append(ParamSetting.IMG_SERVER_DOMAIN);
//		}
		//静态资源合并模式
//		if(ParamSetting.RESOURCEMODE.equals("2")){
			if("/".equals(ParamSetting.CONTEXT_PATH) )
				staticdomain.append("");
			else	
				staticdomain.append(ParamSetting.CONTEXT_PATH);
//		}
		
		// 设置页面上变量的值
		httpRequest.setAttribute("context", staticdomain + context.toString()); //静态资源上下文		
		httpRequest.setAttribute("title",site.getSitename()); //站点名称
		httpRequest.setAttribute("ico",site.getIcofile());    //ico文件
		httpRequest.setAttribute("logo", site.getLogofile()) ; //logo文件
		httpRequest.setAttribute("version", ParamSetting.VERSION) ; //版本
		httpRequest.setAttribute("bkloginpicfile", site.getBkloginpicfile()); //后台登录logo
		httpRequest.setAttribute("bklogo", site.getBklogofile()==null?site.getLogofile():site.getBklogofile()); //后台主界面logo
		
		String uri = page.getUri();

		if (uri.startsWith("/admin/main")) { //后台首页
            AdminUser user = this.adminUserManager.getCurrentUser();
            user = this.adminUserManager.get(user.getId());
            httpRequest.setAttribute("user", user);
			uri = context.toString() + "/main.jsp";
//			request  = new GetPointJsWrapper(page, request); //包装积分获取js
			request = new HelpDivWrapper(page, request);
		
		} else if (uri.equals("/admin/") || uri.equals("/admin/index.jsp")) { //登录页面
			//读取记住的用户名
			String username  = HttpUtil.getCookieValue(httpRequest, "loginname");
			httpRequest.setAttribute("username", username);
			uri =  context.toString() + "/login.jsp";
		} else {
			
			if(ParamSetting.EXTENSION.equals("action")){
				uri = uri.replace(".do", ".action");
			}


			
			String ajax = httpRequest.getParameter("ajax");
			if(!"yes".equals(ajax)){ //非异步包装后台内容界面
				request = new BackTemplateWrapper(page, request); 
				request = new HelpDivWrapper(page, request);
				
			}
			
		}

		Response response = request.execute(uri, httpResponse, httpRequest);
	 
		return response;

	}

}
