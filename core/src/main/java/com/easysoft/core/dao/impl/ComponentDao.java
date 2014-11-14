package com.easysoft.core.dao.impl;


import java.util.Map;

import org.springframework.stereotype.Repository;


import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.core.dao.IComponentDao;
import com.easysoft.framework.component.ComponentView;

@Repository
public class ComponentDao extends HibernateGenericDao<ComponentView,Integer> implements IComponentDao {

	public ComponentView queryComponentByCompId(String componentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateByCondition(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

	

}
