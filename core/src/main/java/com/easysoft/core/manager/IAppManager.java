package com.easysoft.core.manager;

import com.easysoft.core.model.JEAPApp;

import java.util.List;

/**
 * 应用管理 * 
 * @author andy
 * @sina 1.0
 */
public interface IAppManager {

	
	/**
	 * 添加一个应用
	 * @param app
	 */
	public void add(JEAPApp app);
	
	/**
	 * 获取所有应用列表
	 * @return
	 */
	public List<JEAPApp> list();
	
	
	/**
	 * 获取某个应用
	 * @param appid
	 * @return
	 */
	public JEAPApp get(String appid);

}
