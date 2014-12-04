package com.easysoft.component.form.manager;

import java.util.List;

import com.easysoft.component.form.model.AddFormPageMeta;
import com.easysoft.component.form.model.ListPageMeta;

public interface IPageMetaManager {
	public void savePageMetas(Integer formId,List<ListPageMeta> pageMetas);
	public void saveAddFormPageMetas(Integer formId,List<AddFormPageMeta> pageMetas);
}
