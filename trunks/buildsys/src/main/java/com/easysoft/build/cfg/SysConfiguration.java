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



import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * 系统配置信息
 * 
 * @author 张均锋
 * 
 */
public class SysConfiguration extends BaseConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 分支文件模板地址 **/
	private String branchFileFord;
	/** Hudson模板文件地址 **/
	private String hudsonTempleFileFord;
	/** Hudson服务器安装地址 **/
	private String hudsonServerFileFord;
	/** 基准包地址 **/
	private String basePackageUrl;
	/** 项目管理系统数据库驱动 **/
	private String bpdmDriver;
	/** 项目管理系统数据库地址url **/
	private String bpdmDbUrl;
	/** 项目管理系统数据库用户名 **/
	private String bpdmDbUsername;
	/** 项目管理系统数据库密码 **/
	private String bpdmDbPassword;
	/** 项目管理系统webservice地址 **/
	private String bpdmWebserviceUrl;
	/** rtx服务器地址 **/
	private String rtxurl;
	/** rtx服务器端口 **/
	private int rtxport;
	/** rtx通知测试用户 **/
	private String rtxtestuser;

	/** 编译代码时所用jdk版本号及编译器路径 */
	private Map<String, String> jdkVersions = new TreeMap<String, String>();

	/** 编译代码时所选编码格式 */
	private String[] srcEncodings;

	public SysConfiguration() {
		super();
	}

	public SysConfiguration(String configurePath) {
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
		branchFileFord = props.getProperty("branchFileFord");
		hudsonTempleFileFord = props.getProperty("hudsonTempleFileFord");
		hudsonServerFileFord = props.getProperty("hudsonServerFileFord");
		basePackageUrl = props.getProperty("basePackageUrl");
		bpdmDriver = props.getProperty("bpdmDriver");
		bpdmDbUrl = props.getProperty("bpdmDbUrl");
		bpdmDbUsername = props.getProperty("bpdmDbUsername");
		bpdmDbPassword = props.getProperty("bpdmDbPassword");
		bpdmWebserviceUrl = props.getProperty("bpdmWebserviceUrl");
		rtxurl = props.getProperty("rtxurl");
		rtxport = Integer.parseInt(props.getProperty("rtxport"));
		rtxtestuser = props.getProperty("rtxtestuser");
		
		//读取jdk版本信息和支持的编码格式信息
		String jdkVersion = props.getProperty("jdkVersion");
		String srcEncoding = props.getProperty("srcEncoding");
		
		
		if (!StringUtils.isEmpty(jdkVersion)) {
			String[] _jdkVersions = jdkVersion.split(",");

			for (String jdk : _jdkVersions) {
				String javacPath = props.getProperty("jdk" + jdk);
				if (null != javacPath) {
					jdkVersions.put(jdk, javacPath + "/bin/javac");
				}
			}
		}
		if (!StringUtils.isEmpty(srcEncoding)) {
			srcEncodings = srcEncoding.split(",");
		}
	}

	public String getBranchFileFord() {
		return branchFileFord;
	}

	public void setBranchFileFord(String branchFileFord) {
		this.branchFileFord = branchFileFord;
	}

	public String getHudsonTempleFileFord() {
		return hudsonTempleFileFord;
	}

	public void setHudsonTempleFileFord(String hudsonTempleFileFord) {
		this.hudsonTempleFileFord = hudsonTempleFileFord;
	}

	public String getHudsonServerFileFord() {
		return hudsonServerFileFord;
	}

	public void setHudsonServerFileFord(String hudsonServerFileFord) {
		this.hudsonServerFileFord = hudsonServerFileFord;
	}

	public String getBasePackageUrl() {
		return basePackageUrl;
	}

	public void setBasePackageUrl(String basePackageUrl) {
		this.basePackageUrl = basePackageUrl;
	}

	public String getBpdmDriver() {
		return bpdmDriver;
	}

	public void setBpdmDriver(String bpdmDriver) {
		this.bpdmDriver = bpdmDriver;
	}

	public String getBpdmDbUrl() {
		return bpdmDbUrl;
	}

	public void setBpdmDbUrl(String bpdmDbUrl) {
		this.bpdmDbUrl = bpdmDbUrl;
	}

	public String getBpdmDbUsername() {
		return bpdmDbUsername;
	}

	public void setBpdmDbUsername(String bpdmDbUsername) {
		this.bpdmDbUsername = bpdmDbUsername;
	}

	public String getBpdmDbPassword() {
		return bpdmDbPassword;
	}

	public void setBpdmDbPassword(String bpdmDbPassword) {
		this.bpdmDbPassword = bpdmDbPassword;
	}

	public String getBpdmWebserviceUrl() {
		return bpdmWebserviceUrl;
	}

	public void setBpdmWebserviceUrl(String bpdmWebserviceUrl) {
		this.bpdmWebserviceUrl = bpdmWebserviceUrl;
	}

	public String getRtxurl() {
		return rtxurl;
	}

	public void setRtxurl(String rtxurl) {
		this.rtxurl = rtxurl;
	}

	public int getRtxport() {
		return rtxport;
	}

	public void setRtxport(int rtxport) {
		this.rtxport = rtxport;
	}

	public String getRtxtestuser() {
		return rtxtestuser;
	}

	public void setRtxtestuser(String rtxtestuser) {
		this.rtxtestuser = rtxtestuser;
	}

	public String[] getSrcEncodings() {
		return srcEncodings;
	}

	public void setSrcEncodings(String[] srcEncodings) {
		this.srcEncodings = srcEncodings;
	}

	/**
	 * 获取所有JDK版本
	 * 
	 * @return
	 */
	public Map<String,String> getJdkVersions() {
		return jdkVersions;
	}

	/**
	 * 获取指定的JDK编译器路径
	 * 
	 * @param jdkVersion JDK版本，such as:1.5
	 * @return
	 */
	public String getJavacPath(String jdkVersion) {		
		return (null == jdkVersion) ? null : jdkVersions.get(jdkVersion);
	}
}
