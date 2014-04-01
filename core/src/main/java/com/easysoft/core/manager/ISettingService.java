package com.easysoft.core.manager;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

public interface ISettingService {

 
	/**
	 * 更新系统设置
	 * @param settings
	 * @throws SettingRuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public  void save(Map<String, Map<String, String>> settings) throws SettingRuntimeException;

	
	/**
	 * 读取全部设置
	 * @return
	 */
	public  Map<String,Map<String,String>>  getSetting();
	
	
	/**
	 * 读取某项设置值
	 * @param name 参数名
	 * @return 参数值
	 */
	public  String getSetting(String group, String name);

}