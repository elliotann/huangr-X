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
package com.easysoft.build.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * 邮件配置信息
 * 
 * @author 张均锋
 * 
 */
public class MailConfiguration extends BaseConfiguration implements
		Serializable {

	private String host;

	private String user;

	private String password;

	private String addr;


	public MailConfiguration() {
		super();
	}

	public MailConfiguration(String configurePath) {
		super(configurePath);
	}
	
	@Override
	protected void doLoadConfig(File cfgFile) {
		try {
			Properties props = new Properties();
			InputStream fis = new FileInputStream(cfgFile);
			props.load(fis);
			readConfig(props);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readConfig(Properties props) {
		host = props.getProperty("host");
		user = props.getProperty("user");
		password = props.getProperty("password");
		addr = props.getProperty("addr");
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
