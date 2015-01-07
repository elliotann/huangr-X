package com.es.jeap.core.component.permission.dao;

import com.es.framework.db.IGenericDao;
import com.es.jeap.core.component.permission.entity.AdminUser;

public interface IAdminUserDao extends IGenericDao<AdminUser, Integer> {
	public AdminUser queryUserByName(String username);
	public AdminUser queryUserByEmail(String email);
}
