package com.easysoft.core.listener;

import com.easysoft.core.manager.IAppManager;
import com.easysoft.core.model.JEAPApp;
import com.easysoft.core.model.IApp;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.spring.SpringContextHolder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

public class JEAPSessionListener implements HttpSessionListener {
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	public void sessionCreated(HttpSessionEvent se) {
		
	}
	
	public void sessionDestroyed(HttpSessionEvent se) {
		
		if(logger.isDebugEnabled()){
			logger.debug("session destroyed..");
		}
		
		//如果是已经安装状态
		if("YES".equals( ParamSetting.INSTALL_LOCK.toUpperCase())){
			
			if(logger.isDebugEnabled()){
				logger.debug("installed...");
			}
			
			Site site = (Site) se.getSession().getAttribute("site_key");
			String sessionid = se.getSession().getId();
			IAppManager appManager = SpringContextHolder.getBean("appManager");
			List<JEAPApp> appList  = appManager.list();
			for(JEAPApp esfApp :appList){

				String appid  = esfApp.getAppid();
				
				if(logger.isDebugEnabled()){
					logger.debug("call app["+appid+"] destory...");
				}
				
				
				IApp app = SpringContextHolder.getBean(appid);
				app.sessionDestroyed(sessionid,site);
			}
		}else{
			if(logger.isDebugEnabled()){
				logger.debug("not installed...");
			}
		}
	}

}
