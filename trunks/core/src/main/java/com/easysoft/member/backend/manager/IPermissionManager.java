package com.easysoft.member.backend.manager;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.member.backend.model.AuthAction;
import com.easysoft.member.backend.model.FunAndOper;
import com.easysoft.member.backend.model.OperationBtn;
import com.easysoft.member.backend.model.Role;
import com.easysoft.member.backend.vo.FunAndOperationVO;

import java.util.List;


/**
 * 权限管理接口
 * @author andy
 * @since : 1.0
 */
public interface IPermissionManager extends IGenericService {

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
	public List<Role> getUserRoles(int userid);
	
	
	
	/**
	 * 读取某用户的权限点
	 * @param userid 用户id
	 * @param acttype 权限类型
	 * @return
	 */
	public List<AuthAction> getUesrAct(int userid,String acttype);

    /**
     * 读取某用户的权限点
     * @param userid 用户id
     * @param acttype 权限类型
     * @return
     */
    public List<FunAndOper> getUesrAct4New(int userid,String acttype);
	
	
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
	
}
