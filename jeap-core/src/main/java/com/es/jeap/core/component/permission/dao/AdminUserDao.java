package com.es.jeap.core.component.permission.dao;
import java.util.List;


import org.springframework.stereotype.Repository;

import com.es.framework.db.support.HibernateGenericDao;
import com.es.jeap.core.component.permission.entity.AdminUser;
@Repository
public class AdminUserDao extends HibernateGenericDao<AdminUser,Integer> implements IAdminUserDao{

	public AdminUser queryUserByName(String username) {
		String hql = "from AdminUser u where u.username='"+username+"'";

		List<AdminUser> adminUsers = this.queryForListByHql(hql);
		if(!adminUsers.isEmpty()) return adminUsers.get(0);
		return null;
	}

	public AdminUser queryUserByEmail(String email) {
		String hql = "from AdminUser u where u.email='"+email+"'";

		List<AdminUser> adminUsers = this.queryForListByHql(hql);
		if(!adminUsers.isEmpty()) return adminUsers.get(0);
		return null;
	}

}
