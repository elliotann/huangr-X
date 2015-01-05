package com.es.jeap.core.component.permission.dao;
import org.springframework.stereotype.Repository;

import com.es.framework.db.support.HibernateGenericDao;
import com.es.jeap.core.component.permission.entity.AdminUser;
@Repository
public class AdminUserDao extends HibernateGenericDao<AdminUser,Integer> implements IAdminUserDao{

}
