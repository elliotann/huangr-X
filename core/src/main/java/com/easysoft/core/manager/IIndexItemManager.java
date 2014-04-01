package com.easysoft.core.manager;

import com.easysoft.core.model.IndexItem;

import java.util.List;

/**
 * 后台首页项管理 
 * @author andy
 * 2010-10-12下午03:54:19
 */
public interface IIndexItemManager {
	
	
	/**
	 * 添加一个首页项
	 * @param item
	 */
	public void add(IndexItem item);
	
	
	/**
	 * 获取首页项列表
	 * @return
	 */
	public List<IndexItem> list();
	
	/**
	 * 清除首页项
	 * 一般用于站点安装时
	 */
	public void clean();
	
}
