package com.easysoft.component.form.dao;

import com.easysoft.component.form.model.ListPageMeta;
import com.easysoft.core.common.dao.IGenericDao;

public interface IPageMetaDao extends IGenericDao<ListPageMeta, Integer> {
	public void deleteByFormId(Integer formId);
}
