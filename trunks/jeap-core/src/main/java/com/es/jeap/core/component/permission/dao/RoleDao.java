package com.es.jeap.core.component.permission.dao;

import org.springframework.stereotype.Repository;

import com.es.framework.db.support.HibernateGenericDao;
import com.es.jeap.core.component.permission.entity.Role;
@Repository
public class RoleDao extends HibernateGenericDao<Role, Integer> implements IRoleDao{

}
