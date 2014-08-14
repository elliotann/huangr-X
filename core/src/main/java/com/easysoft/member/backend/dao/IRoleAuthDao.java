package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.member.backend.model.RoleAuth;

import java.util.List;

/**
 * @author : andy.huang
 * @since : 1.0
 */
public interface IRoleAuthDao extends IGenericDao<RoleAuth,Integer> {
    /**
     * 根据角色猎取所有功能与操作权限
     * @param id
     * @return
     */
    public List<RoleAuth> queryAuthByRoleId(Integer id);
}
