package com.es.jeap.core.component.permission.manager;

import com.es.framework.db.pager.PageOption;
import com.es.jeap.core.component.permission.entity.AdminUser;

public interface IAdminUserManager {
	public void save(AdminUser adminUser);
	public void update(AdminUser adminuser);
	public void deleteById(Integer id);
	public AdminUser queryUserById(Integer id);
	public PageOption queryUsers(PageOption pageOption);
	public AdminUser queryUserByName(String username,Integer id);
	public AdminUser queryUserByEmail(String email,Integer id);
}
