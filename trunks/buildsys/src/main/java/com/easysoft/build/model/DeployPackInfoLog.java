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
@Table(name = "BT_DEPLOYPACK_LOG")
public class DeployPackInfoLog {  
	
	private Long id;	
	private RepInfo ri;  
	private DeployPackInfo dp;		
	private String takePersonName;
	private Date takeTime;
	private String takeType;//0 普通包  1 加密包

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BT_DEPLOYPACK_LOG_GEN")
    @TableGenerator(name = "BT_DEPLOYPACK_LOG_GEN", table = "TB_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE",
    	pkColumnValue = "BT_DEPLOYPACK_LOG_ID", allocationSize = 1)
	@Column(name = "ID")
    public Long getId() {
  		return id;
  	}
    
  	public void setId(Long Id) {
  		this.id = Id;
  	}
  	
  	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "DEPLOYPACK_ID")
	public DeployPackInfo getDp() {
		return dp;
	}

	public void setDp(DeployPackInfo dp) {
		this.dp = dp;
	}
  	
	@Column(name = "TAKE_PERSON_NAME")
	public String getTakePersonName() {
		return takePersonName;
	}

	public void setTakePersonName(String takePersonName) {
		this.takePersonName = takePersonName;
	}

	@Column(name = "TAKE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	@Column(name = "TAKE_TYPE")
	public String getTakeType() {
		return takeType;
	}

	public void setTakeType(String takeType) {
		this.takeType = takeType;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "RESPINFO_ID")
	public RepInfo getRi() {
		return ri;
	}

	public void setRi(RepInfo ri) {
		this.ri = ri;
	}
}
