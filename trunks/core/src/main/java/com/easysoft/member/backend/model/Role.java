package com.easysoft.member.backend.model;


import com.easysoft.framework.db.NotDbField;

import javax.persistence.*;

/**
 * 角色实体
 * @author andy
 */

public class Role {
	private int roleid;
	private String rolename;
	private String rolememo;
	private int[] actids; //此角色权限id数组
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="roleid")
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
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
