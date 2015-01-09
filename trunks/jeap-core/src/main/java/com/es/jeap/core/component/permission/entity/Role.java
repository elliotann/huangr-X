package com.es.jeap.core.component.permission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.es.framework.entity.IdEntity;
@Entity
@Table(name="t_role")
public class Role extends IdEntity{
	public enum RoleStatus{
		ACTIVE,
		INACTIVE
	}
	private String roleName;
	private RoleStatus status;
	@Column(name="role_name")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="staus")
	public RoleStatus getStatus() {
		return status;
	}
	public void setStatus(RoleStatus status) {
		this.status = status;
	}
}
