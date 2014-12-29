package com.easysoft.core.context;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.core.manager.IMultiSiteManager;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.core.model.MultiSite;
import com.easysoft.core.model.Site;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.utils.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * jeap上下文初始化
 * @author andy
 *
 */
public class JeapContextIniter {
	
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
		httpRequest.setAttribute("staticserver", ParamSetting.IMG_SERVER_DOMAIN);
		httpRequest.setAttribute("ext", ParamSetting.EXTENSION);
		httpRequest.setAttribute("ctx", httpRequest.getContextPath());
		String servletPath = httpRequest.getServletPath();
		if (servletPath.startsWith("/statics"))
			return;

		if( servletPath.startsWith("/install") ){
			Site site = new Site();
			site.setUserid(1);
			site.setId(1);
			site.setThemeid(1);
			EsfContext context = new EsfContext();
			context.setCurrentSite(site);
			EsfContext.setContext(context);
		}else{
			EsfContext context = new EsfContext();
			Site site = new Site();
			site.setUserid(1);
			site.setId(1);
			site.setThemeid(1);
			context.setCurrentSite(site);
			EsfContext.setContext(context);
			
			ISiteManager siteManager = SpringContextHolder.getBean("siteManager");
			site = siteManager.get("localhost");		 
		     
			context.setCurrentSite(site); 
			if(site.getMulti_site()==1){ //开启多站点功能
				String domain = httpRequest.getServerName();
				IMultiSiteManager multiSiteManager =  SpringContextHolder.getBean("multiSiteManager");
				MultiSite multiSite = multiSiteManager.get(domain);
				context.setCurrentChildSite(multiSite);
			}
			
			EsfContext.setContext(context);
			fmp.putData("site", site);
		}
		
		/**
		 * 设置freemarker的相关常量
		 */
		fmp.putData("ctx", httpRequest.getContextPath());
		fmp.putData("ext", ParamSetting.EXTENSION);
		fmp.putData("staticserver", ParamSetting.IMG_SERVER_DOMAIN);
	}
	
	
}
