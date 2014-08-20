package ${bussiPackage}.${entityPackage}.service;

import ${bussiPackage}.${entityPackage}.entity.${entityName}Entity;

public interface ${entityName}ServiceI{

 	public void save(${entityName} ${entityName?uncap_first});
    public void update(${entityName} ${entityName?uncap_first});
    public List<${entityName}> queryForList();
}
