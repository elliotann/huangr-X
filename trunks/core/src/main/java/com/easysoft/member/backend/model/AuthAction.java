package com.easysoft.member.backend.model;

import javax.persistence.*;

/**
 * 权限点实体
 * @author andy
 * @since : 1.0
 */
@Entity
@Table(name="t_auth_action")
public class AuthAction {
	private int actid;
	private String name;
	private String type;
	private String objvalue;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="actid")
	public int getActid() {
		return actid;
	}
	public void setActid(int actid) {
		this.actid = actid;
	}
    @Column(name ="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @Column(name ="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    @Column(name ="objvalue")
	public String getObjvalue() {
		return objvalue;
	}
	public void setObjvalue(String objvalue) {
		this.objvalue = objvalue;
	}
	
}
