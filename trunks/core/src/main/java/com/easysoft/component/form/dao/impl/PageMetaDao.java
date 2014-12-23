package com.easysoft.component.form.dao.impl;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easysoft.component.form.dao.IPageMetaDao;
import com.easysoft.component.form.model.FormField;
import com.easysoft.component.form.model.ListPageMeta;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class PageMetaDao extends HibernateGenericDao<ListPageMeta, Integer> implements
		IPageMetaDao {

	public List<ListPageMeta> queryForList(Map<String, Object> params){
		String hql = " from ListPageMeta f where f.formId=:formId order by f.sort";
		return this.queryForHQL(hql, params);
	}

	public void deleteByFormId(Integer formId) {
		
		String sql = " delete from t_listpage_meta  where form_id="+formId;
		this.excuteBySql(sql);
	}
	

}
