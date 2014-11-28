package com.easysoft.core.dao.impl;



import org.springframework.stereotype.Repository;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.core.dao.ISiteDao;
import com.easysoft.core.model.Site;
@Repository
public class SiteDao extends HibernateGenericDao<Site, Integer> implements ISiteDao {

	

}
