package com.easysoft.component.form.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.form.dao.IAddFormPageMetaDao;
import com.easysoft.component.form.dao.IPageMetaDao;
import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.manager.IPageMetaManager;
import com.easysoft.component.form.model.AddFormPageMeta;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.FormField;
import com.easysoft.component.form.model.FormPageMeta.ShowType;
import com.easysoft.component.form.model.ListPageMeta;
@Service
@Transactional
public class PageMetaManager implements IPageMetaManager {
	@Autowired
	private IFormManager formManager;
	@Autowired
	private IPageMetaDao pageMetaDao;
	@Autowired
	private IAddFormPageMetaDao addFormPageMetaDao;
	public void savePageMetas(Integer formId,List<ListPageMeta> pageMetas) {
		//1、根据表单id查询所有pageMeta
		FormEntity fromEntity = formManager.getFormById(formId);
		//根据formId删除所有页面元素
		pageMetaDao.deleteByFormId(formId);
		
		
		int i=1;
		for(ListPageMeta pageMeta : pageMetas){
			FormField field = new FormField();
			field.setId(pageMeta.getFieldId());
			pageMeta.setForm(fromEntity);
			pageMeta.setField(field);
			pageMeta.setShowType(ShowType.LIST);
			pageMeta.setSort(i);
			pageMetaDao.save(pageMeta);
			i++;
		}
		
		
	}
	public void saveAddFormPageMetas(Integer formId,
			List<AddFormPageMeta> pageMetas) {
		//1、根据表单id查询所有pageMeta
		FormEntity fromEntity = formManager.getFormById(formId);
		//根据formId删除所有页面元素
		addFormPageMetaDao.deleteByFormId(formId);
		int i=1;
		for(AddFormPageMeta pageMeta : pageMetas){


			pageMeta.setForm(fromEntity);
			
			pageMeta.setShowType(ShowType.ADD_FORM);
			pageMeta.setSort(i);
			addFormPageMetaDao.save(pageMeta);
			i++;
		}
		
	}
	

}
