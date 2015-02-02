package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.member.backend.manager.IFunAndOperManager;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.FunAndOper;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: andy
 * Date: 14-5-12
 * Time: 下午4:18
 *
 * @since:
 */
@Service("funAndOperManager")
public class FunAndOperManager extends GenericService<FunAndOper> implements IFunAndOperManager {
    @Autowired
    private IRoleAuthManager roleAuthManager;
    
    public List<RoleAuth> queryFunAndOpersByRoleId(Integer roleId) {
        List<RoleAuth> roleAuths = roleAuthManager.queryRoleAuthListByRoleId(roleId);
        return roleAuths;
    }
}
