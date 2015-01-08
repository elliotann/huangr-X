package com.es.jeap.core.component.permission.dao;
import java.util.List;


import org.springframework.stereotype.Repository;

import com.es.framework.db.support.HibernateGenericDao;
import com.es.jeap.core.component.permission.entity.AdminUser;
@Repository
public class AdminUserDao extends HibernateGenericDao<AdminUser,Integer> implements IAdminUserDao{

	public AdminUser queryUserByName(String username,Integer id) {
		String hql = "from AdminUser u where u.username='"+username+"'";
		if(id!=null&&id!=0){
			hql += " and u.id!="+id;
		}

		List<AdminUser> adminUsers = this.queryForListByHql(hql);
		if(!adminUsers.isEmpty()) return adminUsers.get(0);
		return null;
	}

	public AdminUser queryUserByEmail(String email,Integer id) {
		String hql = "from AdminUser u where u.email='"+email+"'";
		if(id!=null&&id!=0){
			hql += " and u.id!="+id;
		}
		List<AdminUser> adminUsers = this.queryForListByHql(hql);
		if(!adminUsers.isEmpty()) return adminUsers.get(0);
		return null;
	}

}
