package com.easysoft.component.form.manager;

import java.util.List;

import com.easysoft.component.form.model.ListPageMeta;

public interface IPageMetaManager {
	public void savePageMetas(Integer formId,List<ListPageMeta> pageMetas);
}
