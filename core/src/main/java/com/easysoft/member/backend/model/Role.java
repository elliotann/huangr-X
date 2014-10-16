package com.easysoft.member.backend.model;


import com.easysoft.core.common.entity.IdEntity;
import com.easysoft.framework.db.NotDbField;

import javax.persistence.*;

/**
 * 角色实体
 * @author andy
 */
@Entity
@Table(name = "t_role")
public class Role extends IdEntity{

	private String rolename;
	private String rolememo;
	private int[] actids; //此角色权限id数组

	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolememo() {
		return rolememo;
	}
	public void setRolememo(String rolememo) {
		this.rolememo = rolememo;
	}
	@NotDbField
    @Transient
	public int[] getActids() {
		return actids;
	}
	public void setActids(int[] actids) {
		this.actids = actids;
	}
	
}
