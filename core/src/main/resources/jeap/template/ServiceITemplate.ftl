package ${bussiPackage}.${entityPackage}.service;

import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;
import java.util.List;
public interface ${entityName}ServiceI{

 	public void save(${entityName}Entity ${entityName?uncap_first});
    public void update(${entityName}Entity ${entityName?uncap_first});
    public List<${entityName}Entity> queryForList();
}
