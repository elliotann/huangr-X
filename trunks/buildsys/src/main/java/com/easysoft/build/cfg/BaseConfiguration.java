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

import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 应用配置信息
 * 
 * @author 张均锋
 * 
 */
public abstract class BaseConfiguration implements Serializable {
	protected final static Logger logger = Logger.getLogger(BaseConfiguration.class);

	private static final long serialVersionUID = 1L;
	/** 配置文件路径 **/
	private String configurePath;
	/** 配置文件最后载入时间 **/
	private long lastLoadTime;
	/** 配置文件 **/
	private File cfgFile;
	
	private ReentrantLock lock = new ReentrantLock();

	public BaseConfiguration() {
	}

	public BaseConfiguration(String configurePath) {
		this.configurePath = configurePath;
	}

	/**
	 * 加载配置信息，修改文件时，动态加载。
	 */
	public final void loadConfig() {
		try {
			lock.lock();
			if (cfgFile == null) {
				cfgFile = new File(configurePath);
			}
			if (this.lastLoadTime == cfgFile.lastModified()) {
				return;
			}
			doLoadConfig(cfgFile);
			this.lastLoadTime = cfgFile.lastModified();
			logger.info("加载配置文件：" + configurePath);
		} catch (Exception e) {
			throw new RuntimeException("加载配置文件失败！" + configurePath, e);
		} finally {
			lock.unlock();
		}

	}

	/**
	 * 执行解析配置内容信息
	 * 
	 * @param cfgFile
	 *            配置文件
	 */
	protected abstract void doLoadConfig(File cfgFile);

	public String getConfigurePath() {
		return configurePath;
	}

	public void setConfigurePath(String configurePath) {
		this.configurePath = configurePath;
	}

}
