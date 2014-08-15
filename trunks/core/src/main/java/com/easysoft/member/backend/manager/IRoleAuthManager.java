package com.easysoft.member.backend.manager;

import com.easysoft.core.common.service.IGenericService;
import com.easysoft.member.backend.model.RoleAuth;

import java.util.List;

/**
 * User: andy
 * Date: 14-5-13
 * Time: 下午12:31
 *
 * @since:
 */
public interface IRoleAuthManager {
    public List<RoleAuth> queryRoleAuthListByRoleId(Integer roleId);

    public RoleAuth queryRoleAuthByRoleIdAndFunId(Integer roleId,Integer funId);

    public void save(RoleAuth roleAuth);

    public void update(RoleAuth roleAuth);
}
