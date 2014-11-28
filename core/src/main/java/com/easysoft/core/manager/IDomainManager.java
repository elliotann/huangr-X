package com.easysoft.core.manager;

import java.util.List;

import com.easysoft.core.model.JEAPSiteDomain;

/**
 * 域名管理
 * @author andy
 */
public interface IDomainManager {
	
	/**
	 * 根据id获取域名
	 * @param id
	 * @return
	 */
	public JEAPSiteDomain get(Integer id);
	
	
	
	/**
	 * 修改域名
	 * @param domain
	 */
	public void edit(JEAPSiteDomain domain);
	
	
	
	
	/**
	 * 获取某用户的所有域名
	 * @param userid
	 * @return
	 */
	public List<JEAPSiteDomain> listUserDomain();
	
	
	
	/**
	 * 读取当前站点的所有域名
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List<JEAPSiteDomain> listSiteDomain();
	
	
	/**
	 * 读取某站点的所有域名
	 * @param userid
	 * @param siteid
	 * @return
	 */
	public List<JEAPSiteDomain> listSiteDomain(Integer siteid);
	
	
	
	/**
	 * 读取某个站点的域名个数
	 * @param siteid
	 * @return
	 */
	public int getDomainCount(Integer siteid);
	
}
