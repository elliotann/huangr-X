package com.easysoft.core.manager;

import com.easysoft.core.model.ThemeUri;

import java.util.List;

/**
 * uri隐射管理
 * @author andy
 */
public interface IThemeUriManager {
	
	
	/**
	 * 根据themeuri的id获取某个themeuri
	 * @param id
	 * @return
	 */
	public ThemeUri get(Integer id);
	
	
	
	public void add(ThemeUri  uri);
	
	
	
	
	/**
	 * 读取某个站点的所有uri列表
	 * @return
	 */
	public List<ThemeUri> list();
	
	
	/**
	 * 读取uri对应的ThemeUri实体
	 * @param uri
	 * @return ThemeUri实体
	 */
	public ThemeUri getPath(String uri);
	
	
	
	/**
	 * 更新uri
	 * @param uriList
	 */
	public void edit(List<ThemeUri> uriList);
	
	
	/**
	 * 修改一个uri
	 * @param themeUri
	 */
	public void edit(ThemeUri themeUri);
	
	
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	
	/**
	 * 清除
	 * 一般用于站点安装时
	 */
	public void clean();
	

}
