package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class RoleAuthDao extends HibernateGenericDao<RoleAuth,Integer> implements IRoleAuthDao {
    @Override
    public List<RoleAuth> queryAuthByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public RoleAuth queryAuthByRoleIdAndFun(Map<String, Object> conditions) {
        return null;
    }

    @Override
    public void deleteByRoleId(int roleId) {

    }
}
