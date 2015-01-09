package com.es.jeap.core.component.permission.manager;

import java.util.List;

import com.es.jeap.core.component.permission.entity.Role;

public interface IRoleManager {
	public List<Role> queryForQry(String roleName);
	public void add(Role role);
	public void update(Role role);
	public void deleteById(Integer id);
	public Role queryById(Integer id);
}
