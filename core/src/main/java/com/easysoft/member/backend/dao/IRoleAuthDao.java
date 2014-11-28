package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.RoleAuth;

import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since : 1.0
 */
public interface IRoleAuthDao extends IGenericDao<RoleAuth,Integer> {
    /**
     * 根据角色猎取所有功能与操作权限
     * @param roleId
     * @return
     */
    public List<RoleAuth> queryAuthByRoleId(Integer roleId);

    /**
     * 根据角色id与菜单id查询此权限是否已经存在
     * @return
     */
    public RoleAuth queryAuthByRoleIdAndFun(Map<String,Object> conditions);

    public void deleteByRoleId(int roleId);
}
