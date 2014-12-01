package com.easysoft.component.form.dao.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easysoft.component.form.dao.IFormDao;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class FormDao extends HibernateGenericDao<FormEntity, Integer> implements IFormDao {

	public FormEntity queryFormByCode(String code) {
		String hql = "from FormEntity f where f.code=:code";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("code", code);
		List<FormEntity> formEntities = this.queryForHQL(hql, params);
		if(formEntities.isEmpty())
			return null;
		return formEntities.get(0);
	
	}

}
