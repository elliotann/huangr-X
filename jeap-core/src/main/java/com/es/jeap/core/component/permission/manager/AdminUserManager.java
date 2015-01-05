package com.es.jeap.core.component.permission.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.es.jeap.core.component.permission.dao.IAdminUserDao;
import com.es.jeap.core.component.permission.entity.AdminUser;
@Service
public class AdminUserManager implements IAdminUserManager {
	@Autowired
	private IAdminUserDao adminUserDao;
	public void save(AdminUser adminUser) {
		// TODO Auto-generated method stub
		adminUserDao.save(adminUser);
	}

}
