/*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.easysoft.build.web;



import com.easysoft.build.cfg.MailConfiguration;
import com.easysoft.build.cfg.SysConfiguration;

import javax.servlet.ServletContext;

/**
 * web程序管理。初始化程序，加载配置信息。
 * 
 * @author 张均锋
 * 
 */
public class WebAppManager {

	/** servletcontext **/
	private static ServletContext _ctx;
	/** attribute name of WebAppManger **/
	private final static String ATTR_WEBAPPMANAGER = "com.byttersoft.patchbuild.web.WebAppManger";
	/** web应用绝对路径 **/
	private final String webAppPath;
	/** 系统配置信息 **/
	private final SysConfiguration sysConfiguration;
	/** 邮件配置信息 **/
	private final MailConfiguration mailConfiguration;

	public WebAppManager(ServletContext ctx) {
		_ctx = ctx;
		if (instance() != null)
			throw new RuntimeException("the WebAppManger has Already been instantiated!");
		webAppPath = _ctx.getRealPath("/");
		// 初始化配置
		sysConfiguration = new SysConfiguration(getClass().getResource("/sysconfig.properties").getFile());
		sysConfiguration.loadConfig();
		mailConfiguration = new MailConfiguration(getClass().getResource("/mail.properties").getFile());
		mailConfiguration.loadConfig();
		_ctx.setAttribute(ATTR_WEBAPPMANAGER, this);
	}

	public static WebAppManager instance() {
		return (WebAppManager) _ctx.getAttribute(ATTR_WEBAPPMANAGER);
	}

	public String getWebAppPath() {
		return webAppPath;
	}

	public SysConfiguration getSysConfiguration() {
		sysConfiguration.loadConfig();
		return sysConfiguration;
	}

	public MailConfiguration getMailConfiguration() {
		mailConfiguration.loadConfig();
		return mailConfiguration;
	}

	public void destroyed() {
		_ctx.removeAttribute(ATTR_WEBAPPMANAGER);
		// Do something
		System.out.println("stop webapp.");
	}

}
