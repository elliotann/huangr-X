package com.easysoft.member.backend.manager;


import com.easysoft.member.backend.model.AuthAction;

import java.util.List;

/**
 * 权限点管理接口
 * @author andy
 */
public interface IAuthActionManager {
	
	/**
	 *根据权限id获取权限
	 * @param autid
	 * @return
	 */
	public AuthAction get(int autid);
	
	
	
	/**
	 * 读取所有权限点
	 * @return
	 */
	public List<AuthAction> list();
	
	
	/**
	 * 添加一个权限点
	 * @param act
	 * @return 返回添加的权限点id
	 */
	public int add(AuthAction act);

    public int add(AuthAction auth,int roleId);
	
	
	
	/**
	 * 修改权限点
	 * @param act
	 */
	public void edit(AuthAction act);
	
	
	
	/**
	 * 删除某个权限点
	 * @param actid
	 */
	public void delete(int actid);

    public void deleteMenu(int actid, Integer[] menuidAr);

    public void addMenu(int actid, Integer[] menuidAr);
}
