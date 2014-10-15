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

package com.easysoft.build.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BT_REPINFO")
public class RepInfo {
	
	private Long id;
	private String name;	
	private String svnUrl;
	private String svnRoot;
	private String branchRoot;
	private String svnUser;
	private String svnPassword;
	private String versionNo;
	private String versionPattern;
	private String versionSuffix;
	private String testUsers;
	private String deployUsers;
	private String ss;
	private Date addTime;
	private Date modifyTime;
	private String isValid;//0 作废  1  有效   2 生成      添加后是有效  有效的可以删除     有效后是生成     生成后可以作废
	
    private String spNo;
    private String createDate;   
    private String band;
    private String permitBand; 
    private String permitPatch;
	private String mainVersion;
    private String subVersion;
	
    private String jdkVersion;//jdk版本：缺省1.5
    private String srcEncoding;//源码编码:支持GBK和UTF-8，缺省GBK
    private String isWeekbug;//是否周BUG线

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BT_REPINFO_GEN")
    @TableGenerator(name = "BT_REPINFO_GEN", table = "TB_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE",
    	pkColumnValue = "BT_REPINFO_ID", allocationSize = 1)
	@Column(name = "ID")
    public Long getId() {
  		return id;
  	}
    
  	public void setId(Long Id) {
  		this.id = Id;
  	}
  	
  	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SVN_URL")
	public String getSvnUrl() {
		return svnUrl;
	}

	public void setSvnUrl(String svnUrl) {
		this.svnUrl = svnUrl;
	}

	@Column(name = "SVN_ROOT")
	public String getSvnRoot() {
		return svnRoot;
	}

	public void setSvnRoot(String svnRoot) {
		this.svnRoot = svnRoot;
	}

	@Column(name = "BRANCH_ROOT")
	public String getBranchRoot() {
		return branchRoot;
	}

	public void setBranchRoot(String branchRoot) {
		this.branchRoot = branchRoot;
	}

	@Column(name = "SVN_USER")
	public String getSvnUser() {
		return svnUser;
	}

	public void setSvnUser(String svnUser) {
		this.svnUser = svnUser;
	}

	@Column(name = "SVN_PASSWORD")
	public String getSvnPassword() {
		return svnPassword;
	}

	public void setSvnPassword(String svnPassword) {
		this.svnPassword = svnPassword;
	}

	@Column(name = "VERSION_NO")
	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	@Column(name = "VERSION_PATTERN")
	public String getVersionPattern() {
		return versionPattern;
	}

	public void setVersionPattern(String versionPattern) {
		this.versionPattern = versionPattern;
	}

	@Column(name = "VERSION_SUFFIX")
	public String getVersionSuffix() {
		return versionSuffix;
	}

	public void setVersionSuffix(String versionSuffix) {
		this.versionSuffix = versionSuffix;
	}

	@Column(name = "TEST_USERS")
	public String getTestUsers() {
		return testUsers;
	}

	public void setTestUsers(String testUsers) {
		this.testUsers = testUsers;
	}

	@Column(name = "DEPLOY_USERS")
	public String getDeployUsers() {
		return deployUsers;
	}

	public void setDeployUsers(String deployUsers) {
		this.deployUsers = deployUsers;
	}
	
	@Column(name = "SS_USERS")
	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	@Column(name = "ADD_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "IS_VALID")
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@Column(name = "SP_NO")
	public String getSpNo() {
		return spNo;
	}

	public void setSpNo(String spNo) {
		this.spNo = spNo;
	}

	@Column(name = "CREATE_DATE")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "BAND")
	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	@Column(name = "PERMIT_BAND")
	public String getPermitBand() {
		return permitBand;
	}

	public void setPermitBand(String permitBand) {
		this.permitBand = permitBand;
	}

	@Column(name = "PERMIT_PATCH")
	public String getPermitPatch() {
		return permitPatch;
	}

	public void setPermitPatch(String permitPatch) {
		this.permitPatch = permitPatch;
	}
	
	@Column(name = "MAIN_VERSION")
	public String getMainVersion() {
		return mainVersion;
	}

	public void setMainVersion(String mainVersion) {
		this.mainVersion = mainVersion;
	}

	@Column(name = "SUB_VERSION")
	public String getSubVersion() {
		return subVersion;
	}

	public void setSubVersion(String subVersion) {
		this.subVersion = subVersion;
	}

	@Column(name = "JDK_VERSION")
	public String getJdkVersion() {
		return jdkVersion;
	}

	public void setJdkVersion(String jdkVersion) {
		this.jdkVersion = jdkVersion;
	}

	@Column(name = "SRC_ENCODING")
	public String getSrcEncoding() {
		return srcEncoding;
	}

	public void setSrcEncoding(String srcEncoding) {
		this.srcEncoding = srcEncoding;
	}

	@Column(name = "IS_WEEKBUG")
	public String getIsWeekbug() {
		return isWeekbug;
	}

	public void setIsWeekbug(String isWeekbug) {
		this.isWeekbug = isWeekbug;
	}
  	
}
