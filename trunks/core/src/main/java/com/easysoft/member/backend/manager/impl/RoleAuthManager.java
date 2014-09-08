package com.easysoft.member.backend.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.member.backend.dao.IRoleAuthDao;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public RoleAuth queryRoleAuthByRoleIdAndFunId(Integer roleId, Integer funId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("roleId",roleId);
        params.put("funId",funId);
        return roleAuthDao.queryAuthByRoleIdAndFun(params);
    }

    @Override
    public void update(RoleAuth roleAuth) {
        roleAuthDao.update(roleAuth);
    }

    @Override
    public void save(RoleAuth roleAuth) {
        roleAuthDao.save(roleAuth);
    }

    @Override
    public void deleteByRoleId(int roleId) {
        roleAuthDao.deleteByRoleId(roleId);
    }
}
