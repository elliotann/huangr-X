package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.member.backend.dao.IRoleAuthDao;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: andy
 * Date: 14-5-13
 * Time: 下午12:32
 *
 * @since:
 */
@Service("roleAuthManager")
public class RoleAuthManager implements IRoleAuthManager {
    private static final List EMPTY_LIST = Collections.unmodifiableList(new ArrayList());
    @Autowired
    private IRoleAuthDao roleAuthDao;
    @Override
    public List<RoleAuth> queryRoleAuthListByRoleId(Integer roleId) {
        if(roleId==null){
            return EMPTY_LIST;
        }
        return roleAuthDao.queryAuthByRoleId(roleId);
    }
}
