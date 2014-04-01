package com.easysoft.component.dform.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.dform.model.CgformEnhanceJsEntity;
import com.easysoft.component.dform.service.CgformEnhanceJsServiceI;
import com.easysoft.core.common.service.impl.GenericService;

@Service("cgformenhanceJsService")
@Transactional
public class CgformEnhanceJsServiceImpl extends GenericService implements CgformEnhanceJsServiceI {

	/**
	 * 根据cgJsType和formId查找数据
	 * @param cgJsType
	 * @param formId
	 * @return
	 */
	
	public CgformEnhanceJsEntity getCgformEnhanceJsByTypeFormId(String cgJsType, String formId) {
		StringBuilder hql = new StringBuilder("");
		hql.append(" from CgformEnhanceJsEntity t");
		hql.append(" where t.formId='").append(formId).append("'");
		hql.append(" and  t.cgJsType ='").append(cgJsType).append("'");
		List<CgformEnhanceJsEntity> list = this.findHql(hql.toString());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}