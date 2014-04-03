package ${bussiPackage}.service.${entityPackage};

import com.easysoft.core.common.service.IGenericService;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import java.io.Serializable;

public interface ${entityName}ServiceI extends IGenericService<${entityName}Entity>{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);

}
