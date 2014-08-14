package com.easysoft.member.backend.manager;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.member.backend.model.Role;

import java.util.List;

/**
 * 角色管理接口
 * @author andy
 */
public interface IRoleManager {
	
	/**
	 * 读取所有角色列表 
	 * @return
	 */
	public List<Role> list();
	
	
	/**
	 * 添加一个角色
	 * @param role 角色实体
	 */
	public void add(Role role);
	
	
	
	/**
	 * 修改角色<br>
	 * 将会同时修改此属于此角色用户的权限
	 * @param role  角色实体
	 * @param acts 此角色的权限集合
	 */
	public void update(Role role,int[] acts);

    /**
     * 根据ID删除角色
     * @param roleid
     */
    public void deleteById(int roleid);
	


    /**
     * 根据角色名称查询角色
     * @param roleName
     * @return
     */
    public Role getRoleByName(String roleName,int roleid);

    /**
     * 读取某个角色信息，同时读取此角色权限
     * @param roleid
     * @return 权限id存于role.actids数组中
     */
	public Role queryById(int roleid);



}
