package com.easysoft.component.dform.service;

import com.easysoft.component.dform.model.CgformEnhanceJsEntity;
import com.easysoft.core.common.service.IGenericService;

public interface CgformEnhanceJsServiceI extends IGenericService{

	/**
	 * 根据cgJsType和formId查找数据
	 * @param cgJsType
	 * @param formId
	 * @return
	 */
	public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType,String formId);
}
