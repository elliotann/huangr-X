package com.easysoft.component.service.cms;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.component.entity.cms.ProvideLoanInfoEntity;
import java.io.Serializable;

public interface ProvideLoanInfoServiceI extends IGenericService<ProvideLoanInfoEntity>{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);

}
