package com.easysoft.core.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.manager.IIndexItemManager;
import com.easysoft.core.model.IndexItem;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 首页项管理实现
 * @author andy
 */
@Service("indexItemManager")
public class IndexItemManager extends BaseSupport<IndexItem> implements IIndexItemManager {
	
	/**
	 * 添加首页项
	 */
	public void add(IndexItem item) {
		this.baseDaoSupport.insert("index_item", item);
	}

	
	/**
	 * 读取首页项列表
	 */
	public List<IndexItem> list() {
		String sql  ="select * from index_item order by sort";
		return this.baseDaoSupport.queryForList(sql, IndexItem.class);
	}
	
	public void clean() {
		this.baseDaoSupport.execute("truncate table index_item");
	}
	

 
}
