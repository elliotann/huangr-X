package com.easysoft.component.form.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easysoft.component.form.dao.IFormFieldDao;
import com.easysoft.component.form.model.FormField;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class FormFieldDao extends HibernateGenericDao<FormField, Integer>
		implements IFormFieldDao {

	public void delByFormId(Integer formId) {
		// TODO Auto-generated method stub
		
	}
	public List<FormField> queryForList(Map<String, Object> params){
		String hql = " from FormField f where f.form.id=:formId";
		return this.queryForHQL(hql, params);
	}

}
