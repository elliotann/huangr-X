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
@Table(name = "T_DEPLOYPACK")
public class DeployPackInfo {
	private Long id;
	private String deployPackName;
	private Date createTime;
	private RepInfo ri;  

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    public Long getId() {
  		return id;
  	}
    
  	public void setId(Long Id) {
  		this.id = Id;
  	}
  	
  	@Column(name = "DEPLOYPACK_NAME")
  	public String getDeployPackName() {
		return deployPackName;
	}

	public void setDeployPackName(String deployPackName) {
		this.deployPackName = deployPackName;
	}

	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
