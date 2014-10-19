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
@Table(name = "T_BUILDCONFIG_LOG")
public class BuildConfigInfoLog {
	
	private Long id;
	private String operater;
	private String operaterCode;
	private String operaterName;
	private Date operaterTime;	
	private RepInfo ri;
	private BuildConfigInfo bc;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "OPERATER")
	public String getOperater() {
		return operater;
	}
	
	public void setOperater(String operater) {
		this.operater = operater;
	}
	
	@Column(name = "OPERATER_CODE")
	public String getOperaterCode() {
		return operaterCode;
	}
	
	public void setOperaterCode(String operaterCode) {
		this.operaterCode = operaterCode;
	}
	
	@Column(name = "OPERATER_NAME")
	public String getOperaterName() {
		return operaterName;
	}
	
	public void setOperaterName(String operaterName) {
		this.operaterName = operaterName;
	}
	
	@Column(name = "OPERATER_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getOperaterTime() {
		return operaterTime;
	}
	
	public void setOperaterTime(Date operaterTime) {
		this.operaterTime = operaterTime;
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
    @JoinColumn(name = "BUILEDFILE_ID")
	public BuildConfigInfo getBc() {
		return bc;
	}
	public void setBc(BuildConfigInfo bc) {
		this.bc = bc;
	}
	

}
