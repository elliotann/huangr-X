package ${bussiPackage}.${entityPackage}.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ${bussiPackage}.${entityPackage}.service.${entityName}ServiceI;
import com.easysoft.core.common.service.impl.GenericService;
import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
import java.util.UUID;
import java.io.Serializable;
import java.util.List;
@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl implements ${entityName}ServiceI {
    @Autowired
    private I${entityName}Dao ${entityName?uncap_first}Dao;

 	
 	public void save(${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Dao.save(${entityName?uncap_first});
 	}
    public void update(${entityName} ${entityName?uncap_first}){
        ${entityName?uncap_first}Dao.update(${entityName?uncap_first});
    }
    public List<${entityName}> queryForList(){
        return ${entityName?uncap_first}Dao.queryForList();
    }
}