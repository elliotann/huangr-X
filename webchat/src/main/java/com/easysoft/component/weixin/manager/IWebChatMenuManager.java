package com.easysoft.component.weixin.manager;

import java.util.List;

import com.easysoft.component.weixin.model.WebChatMenu;

public interface IWebChatMenuManager {
	/**
	 * 查询所有菜单
	 */
	public List<WebChatMenu> getAll();
	
	/**
	 * 增加菜单
	 * @param webChatMenu
	 */
	public void add(WebChatMenu webChatMenu);
	/**
	 * 同步菜单
	 */
	public String syncMenu(String[] menuIds);
	
	public WebChatMenu get(Integer id);
	
	public void edit(WebChatMenu webChatMenu);
	
	public List<WebChatMenu> getMenuTree(Integer parentId);
}
