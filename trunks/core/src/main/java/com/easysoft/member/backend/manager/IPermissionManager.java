package com.easysoft.member.backend.manager;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.member.backend.model.*;
import com.easysoft.member.backend.vo.FunAndOperationVO;

import java.util.List;


/**
 * 权限管理接口
 * @author andy
 * @since : 1.0
 */
public interface IPermissionManager{

    public boolean checkHaveAuth(int actid);
	
	/**
	 * 为某个用户赋予某些角色<br>
	 * 会清除此用户的前角色，重新赋予
	 * @param userid  用户id
	 * @param roleids 角色id数组
	 */
	public void giveRolesToUser(int userid,int[] roleids);

    /**
     * 根据用户ID和点击的菜单ID查询此功能所拥有的操作权限
     * @param userid
     * @param acttype
     * @param menuId
     * @return
     */
	public List<OperationBtn> queryBtnByUsernameAndMenuId(Integer userid, String acttype,Integer menuId);
	
	
	/**
	 * 读取某用户的角色集合
	 * @param userid
	 * @return 此用户的角色集合
	 */
	public List<UserRole> getUserRoles(int userid);
	
	
	
	/**
	 * 读取某用户的权限点
	 * @param userid 用户id
	 * @param acttype 权限类型
	 * @return
	 */
	public List<RoleAuth> getUesrAct(int userid,String acttype);


	
	
	/**
	 * 删除某用户的所有角色
	 * @param userid 要删除角色的用户
	 */
	public void cleanUserRoles(int userid);

    public List<AuthAction> getAuthActionsByRoleId(int roleId);

    /**
     * 获取功能及其操作对象集合
     * @return
     */
    public List<FunAndOperationVO> getFunAndOperations();

    /**
     * 根据菜单id查询其操作权限
     * @param menuId
     * @return
     */
    public List<OperationBtn> getOperationBtnsByMenuId(Integer menuId);

    public boolean hasOperationByRoleAndMenu(Integer roleId,Integer menuId,String operId);
	
}
