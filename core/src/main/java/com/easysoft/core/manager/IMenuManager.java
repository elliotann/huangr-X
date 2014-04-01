package com.easysoft.core.manager;

import com.easysoft.member.backend.model.Menu;

import java.util.List;

/**
 * 
 * 菜单管理
 * @author andy
 *         <p>
 *         2009-12-16 上午11:05:28
 *         </p>
 * @version 1.0
 */
public interface IMenuManager {
	
	 
	/**
	 * 添加菜单项
	 * @param menu
	 * @return
	 */
	public Integer add(Menu menu);
	
	
	/**
	 * 修改一个菜单项
	 * @param menu
	 */
	public void edit(Menu menu);
	
	
	/**
	 * 读取菜单列表
	 * @return
	 */
	public List<Menu> getMenuList();

	
	/**
	 * 获取某个菜单的详细信息
	 * @param id
	 * @return
	 */
	public Menu get(Integer id);
	
	

	/**
	 * 读取某菜单列表并形成Tree格式
	 * @param menuid 要读取的顶级菜单id ,0为读取所有菜单
	 * @return
	 * @since 2.1.3 
	 * @author andy
	 */
	public List<Menu> getMenuTree(Integer menuid);
	
	
	/**
	 * 删除一个菜单
	 * @param id
	 * @throws RuntimeException 如果存在子菜单则抛出此异常
	 */
	public void delete(Integer id) throws RuntimeException;
	
	
	/**
	 * 更新菜单排序
	 * @param ids
	 * @param sorts
	 */
	public void updateSort(Integer[] ids,Integer[] sorts);
	
	/**
	 * 清除
	 * 一般用于站点安装时
	 */
	public void clean();

    public Menu get(String title);

    public void delete(String title);
	
}
