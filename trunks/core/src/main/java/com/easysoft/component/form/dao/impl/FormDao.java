package com.easysoft.component.form.dao.impl;



import org.springframework.stereotype.Repository;

import com.easysoft.component.form.dao.IFormDao;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class FormDao extends HibernateGenericDao<FormEntity, Integer> implements IFormDao {

	public FormEntity queryFormByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
