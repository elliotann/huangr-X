package com.easysoft.core.context;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.core.manager.IMultiSiteManager;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.core.model.MultiSite;
import com.easysoft.core.model.Site;
import com.easysoft.core.manager.IComponentManager;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.utils.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaasEsfContextIniter {
	public static void init(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse){
		
		FreeMarkerParser.set(new FreeMarkerParser());
		FreeMarkerParser fmp = FreeMarkerParser.getInstance();
		/**
		 * 将requst response及静态资源域名加入到上下文
		 */
		HttpSession session = httpRequest.getSession();
		ThreadContextHolder.getSessionContext().setSession(session);
		EsfContext.setHttpRequest(httpRequest);
		ThreadContextHolder.setHttpRequest(httpRequest);
		ThreadContextHolder.setHttpResponse(httpResponse);
		//FIXME 发现staticserver及ext已经在DispatcherFilter中设置过，此处是否为冗余？
		httpRequest.setAttribute("staticserver", ParamSetting.IMG_SERVER_DOMAIN);
		httpRequest.setAttribute("ext", ParamSetting.EXTENSION);
		String servletPath  =   httpRequest.getServletPath();
		
		//System.out.println("uri : "+ RequestUtil.getRequestUrl(httpRequest));
		if(  servletPath.startsWith("/statics") ) return ;
		
		Site site = null;
		if( servletPath.startsWith("/install") &&  !servletPath.startsWith("/install.html")){
			  site = new Site();
			site.setUserid(1);
			site.setId(1);
			site.setThemeid(2);
			EsfContext context = new EsfContext();
			context.setCurrentSite(site);
			EsfContext.setContext(context);
		}
		else{
		/** 
		 * 根据域名找到当前站点上下文
		 */
		String domain = httpRequest.getServerName();
		ISiteManager siteManager = SpringContextHolder.getBean("siteManager");
		site = siteManager.get(domain);
		EsfContext context = new EsfContext();
		context.setCurrentSite(site);
		EsfContext.setContext(context);
		if(site.getMulti_site()==1){ //开启多站点功能
			IMultiSiteManager multiSiteManager =  SpringContextHolder.getBean("multiSiteManager");
			MultiSite multiSite = multiSiteManager.get(domain);
			context.setCurrentChildSite(multiSite);
		}
		}
		
		
		/**
		 * 设置freemarker的相关常量
		 */
		//FIXME 下述三个变量均在DispatcherFilter中设置过，是否为冗余？
		fmp.putData("ctx", httpRequest.getContextPath());
		fmp.putData("ext", ParamSetting.EXTENSION);
		fmp.putData("staticserver", ParamSetting.IMG_SERVER_DOMAIN);
		fmp.putData("site", site);
        if (ParamSetting.INSTALL_LOCK.toUpperCase().equals("YES")) {
            IComponentManager componentManager = (IComponentManager) SpringContextHolder.getBean("componentManager");
            componentManager.saasStartComponents();
        }
	}
}
