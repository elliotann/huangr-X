package com.easysoft.core.manager;

import com.easysoft.core.model.Border;

import java.util.List;

/**
 * 边框管理
 * @author andy
 */
public interface IBorderManager {
	
	public void add(Border border);
	public void update(Border border);
	public void delete(Integer id);
	public  List<Border> list();
	
	/**
	 * 清除某站点的边框数据
	 * 一般用于安装边框时的清除数据
	 */
	public void clean();
	
}
