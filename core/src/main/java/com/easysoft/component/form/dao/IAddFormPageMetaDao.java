package com.easysoft.component.form.dao;

import com.easysoft.component.form.model.AddFormPageMeta;
import com.easysoft.core.common.dao.IGenericDao;
public interface IAddFormPageMetaDao  extends IGenericDao<AddFormPageMeta, Integer>{
	public void deleteByFormId(Integer formId);
}
