/**
 * 
 */
package com.easysoft.build.web.listener;


import com.easysoft.build.web.WebAppManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * web应用程序监听器
 * 
 * @author 张均锋
 * 
 */
public class WebAppListener implements ServletContextListener {
	private final static Logger logger = Logger.getLogger(WebAppListener.class);

	private WebAppManager webAppManger;

	public void contextInitialized(ServletContextEvent event) {
		logger.info("web应用启动，正在初始化系统配置...");
		// 初始化配置
		try {
			webAppManger = new WebAppManager(event.getServletContext());
		} catch (Exception e) {
            logger.error("初始化分发平台配置出现异常，系统终止运行！",e);
			throw new RuntimeException("初始化分发平台配置出现异常，系统终止运行！", e);
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("web应用停止！");
		webAppManger.destroyed();
	}

}
