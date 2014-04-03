package com.easysoft.component.service.impl.cms;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.service.cms.ProvideLoanInfoServiceI;
import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.component.entity.cms.ProvideLoanInfoEntity;
import java.util.UUID;
import java.io.Serializable;

@Service("provideLoanInfoService")
@Transactional
public class ProvideLoanInfoServiceImpl extends GenericService<ProvideLoanInfoEntity> implements ProvideLoanInfoServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);

 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);

 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);

 	}

}