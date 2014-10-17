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
@Table(name = "BT_BUILDCONFIG")
public class BuildConfigInfo {
	
	private Long id;
	private String buildFileName;
	private String status;//0 已构建  1 开始测试  2 取消测试 3 测试通过  4 取消构建  5 已发布 
	private RepInfo ri;  
	private DeployPackInfo bd;	
	private String includsFiles;	
	private String deployUsers;
	private String buildComments;
	private String buildDepends;
	private String adder;
	private String startTester;
	private String cancelTester;
	private String passTester;
	private String buildDeleter;
	private Date startTestTime;
	private Date cancelTestTime;
	private Date passTestTime;
	private Date deployTime;
	private Date buildDeleteTime;
	private Date pdTime;
	private Date addTime;
	private String hasSql;
	private String sqlFiles;
	private String vpNames;
	private String includsModules;
	
	@Column(name = "HAS_SQL")
	public String getHasSql() {
		return hasSql;
	}

	public void setHasSql(String hasSql) {
		this.hasSql = hasSql;
	}

	@Column(name = "SQL_FILES")
	public String getSqlFiles() {
		return sqlFiles;
	}

	public void setSqlFiles(String sqlFiles) {
		this.sqlFiles = sqlFiles;
	}
	
	@Column(name = "ADD_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "PD_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPdTime() {
		return pdTime;
	}

	public void setPdTime(Date pdTime) {
		this.pdTime = pdTime;
	}

	@Column(name = "BUILD_DELET_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBuildDeleteTime() {
		return buildDeleteTime;
	}

	public void setBuildDeleteTime(Date buildDeleteTime) {
		this.buildDeleteTime = buildDeleteTime;
	}

	@Column(name = "DEPLOY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	@Column(name = "PASS_TEST_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPassTestTime() {
		return passTestTime;
	}

	public void setPassTestTime(Date passTestTime) {
		this.passTestTime = passTestTime;
	}

	@Column(name = "CANCEL_TEST_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCancelTestTime() {
		return cancelTestTime;
	}

	public void setCancelTestTime(Date cancelTestTime) {
		this.cancelTestTime = cancelTestTime;
	}

	@Column(name = "START_TEST_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTestTime() {
		return startTestTime;
	}

	public void setStartTestTime(Date startTestTime) {
		this.startTestTime = startTestTime;
	}

	@Column(name = "BUILD_DELETER")
	public String getBuildDeleter() {
		return buildDeleter;
	}

	public void setBuildDeleter(String buildDeleter) {
		this.buildDeleter = buildDeleter;
	}

	@Column(name = "PASS_TESTER")
	public String getPassTester() {
		return passTester;
	}

	public void setPassTester(String passTester) {
		this.passTester = passTester;
	}

	@Column(name = "CANCEL_TESTER")
	public String getCancelTester() {
		return cancelTester;
	}

	public void setCancelTester(String cancelTester) {
		this.cancelTester = cancelTester;
	}

	@Column(name = "START_TESTER")
	public String getStartTester() {
		return startTester;
	}

	public void setStartTester(String startTester) {
		this.startTester = startTester;
	}

	@Column(name = "ADDER")
	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}

	@Column(name = "BUILD_DEPENDS")
	public String getBuildDepends() {
		return buildDepends;
	}

	public void setBuildDepends(String buildDepends) {
		this.buildDepends = buildDepends;
	}

	@Column(name = "BUILD_COMMENTS")
	public String getBuildComments() {
		return buildComments;
	}

	public void setBuildComments(String buildComments) {
		this.buildComments = buildComments;
	}
	

	@Column(name = "DEPLOY_USERS")
	public String getDeployUsers() {
		return deployUsers;
	}

	public void setDeployUsers(String deployUsers) {
		this.deployUsers = deployUsers;
	}
	
	@Lob
    @Column(name = "INCLUDS_FILES",columnDefinition = "CLOB")
	public String getIncludsFiles() {
		return includsFiles;
	}

	public void setIncludsFiles(String includsFiles) {
		this.includsFiles = includsFiles;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    public Long getId() {
  		return id;
  	}
    
  	public void setId(Long Id) {
  		this.id = Id;
  	}
  	
  	@Column(name = "BUILEDFILENAME")
	public String getBuildFileName() {
		return buildFileName;
	}

	public void setBuildFileName(String buildFileName) {
		this.buildFileName = buildFileName;
	}
	
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "RESPINFO_ID")
	public RepInfo getRi() {
		return ri;
	}

	public void setRi(RepInfo ri) {
		this.ri = ri;
	}
  
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "BD_ID")
	public DeployPackInfo getBd() {
		return bd;
	}

	public void setBd(DeployPackInfo bd) {
		this.bd = bd;
	}	
	
	@Column(name = "VP_NAMES")
	public String getVpNames() {
		return vpNames;
	}

	public void setVpNames(String vpNames) {
		this.vpNames = vpNames;
	}

	@Column(name = "INCLUDS_MODULES")
	public String getIncludsModules() {
		return includsModules;
	}

	public void setIncludsModules(String includsModules) {
		this.includsModules = includsModules;
	}
	
}
