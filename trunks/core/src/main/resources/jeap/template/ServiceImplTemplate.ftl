package ${bussiPackage}.service.impl.${entityPackage};

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${bussiPackage}.service.${entityPackage}.${entityName}ServiceI;
import com.easysoft.core.common.service.impl.GenericService;
import ${bussiPackage}.entity.${entityPackage}.${entityName}Entity;
import java.util.UUID;
import java.io.Serializable;

@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl extends GenericService<${entityName}Entity> implements ${entityName}ServiceI {

	
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