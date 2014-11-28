package com.easysoft.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.core.dao.ISiteDomainDao;
import com.easysoft.core.model.JEAPSiteDomain;

@Repository
public class SiteDomainDao extends HibernateGenericDao<JEAPSiteDomain, Integer> implements
		ISiteDomainDao {
	
	
}
