package com.easysoft.core.manager.impl;

import com.easysoft.core.manager.IAppManager;
import com.easysoft.core.model.JEAPApp;
import com.easysoft.framework.db.IDaoSupport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 应用管理
 * @author andy
 */
@Service("appManagerImpl")
public class AppManagerImpl implements IAppManager {

	private IDaoSupport<JEAPApp> daoSupport;
	
	public JEAPApp get(String appid) {
		String sql ="select * from eop_app where id=?";
		return this.daoSupport.queryForObject(sql, JEAPApp.class, appid);
	}

	
	public List<JEAPApp> list() {
		String sql ="select * from jeap_app";
		return this.daoSupport.queryForList(sql,  JEAPApp.class);
	}

	public IDaoSupport<JEAPApp> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<JEAPApp> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public void add(JEAPApp app) {
		this.daoSupport.insert("jeap_app", app);
	}

}
