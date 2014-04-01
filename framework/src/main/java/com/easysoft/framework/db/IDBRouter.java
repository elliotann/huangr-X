package com.easysoft.framework.db;

/**
 * 数据库路由器
 * @author andy
 */
public interface IDBRouter {
	
	/**
	 * 得到一个用户某模块的表名
	 * @param moudle 模块名
	 * @return
	 */
	public String getTableName(String moudle);
	


    public void doSaasInstall(String xmlFile);
	
	
	
	
}
