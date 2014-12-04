package com.easysoft.component.form.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import com.easysoft.component.form.dao.IAddFormPageMetaDao;
import com.easysoft.component.form.model.AddFormPageMeta;
import com.easysoft.component.form.model.ListPageMeta;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.framework.db.PageOption;
@Repository
public class AddFormPageMetaDao extends HibernateGenericDao<AddFormPageMeta, Integer> implements
		IAddFormPageMetaDao {

	public List<AddFormPageMeta> queryForList(Map<String, Object> params){
		String hql = " from AddFormPageMeta f where f.form.id=:formId order by f.sort";
		return this.queryForHQL(hql, params);
	}
	
	public void deleteByFormId(Integer formId) {
		
		String sql = " delete from t_addform_page_meta  where form_id="+formId;
		this.excuteBySql(sql);
	}

}
