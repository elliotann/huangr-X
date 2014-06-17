package com.easysoft.core.manager.impl;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.manager.IBorderManager;
import com.easysoft.core.model.Border;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * saas式的边框管理 
 * @author andy
 */
@Service("borderManager")
public class BorderManagerImpl extends BaseSupport<Border> implements IBorderManager {
 
	public void clean() {
		this.baseDaoSupport.execute("truncate table border");
	}
	
	
	public void add(Border border) {
		this.baseDaoSupport.insert("border", border);
	}

	
	public void delete(Integer id) {
		this.baseDaoSupport.execute("delete from border where id=?", id);
	}

	
	public List<Border> list() {
		String sql  ="select * from  border";
		List<Border> list = this.baseDaoSupport.queryForList(sql, Border.class);
		return list;
	}

	
	public void update(Border border) {
		this.baseDaoSupport.update("border", border, "id="+border.getId());
	}

 
	
}
