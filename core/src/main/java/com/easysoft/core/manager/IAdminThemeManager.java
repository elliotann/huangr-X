package com.easysoft.core.manager;

import com.easysoft.core.model.AdminTheme;

import java.util.List;

/**
 * 后主题管理
 * @author andy
 * @since : 1.0
 */
public interface IAdminThemeManager {

	
	/**
	 * 添加后台主题
	 * @param theme
	 * @param isCommon 是否是公共模板
	 */
	public Integer add(AdminTheme theme,boolean isCommon);
	
	
	
	/**
	 * 读取所有主题列表
	 * @return
	 */
	public List<AdminTheme> list();
	
	
	/**
	 * 读取某个主题详细
	 * @param themeid
	 * @return
	 */
	public AdminTheme get( Integer themeid);

	
	/**
	 * 清除
	 * 一般用于站点安装时
	 */
	public void clean();
	 

}