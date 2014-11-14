package com.easysoft.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.core.dao.IUserDao;
import com.easysoft.core.model.JEAPUser;

@Repository
public class UserDao extends HibernateGenericDao<JEAPUser, Integer> implements IUserDao {

	

}
