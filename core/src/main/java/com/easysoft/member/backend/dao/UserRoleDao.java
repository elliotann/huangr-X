package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class UserRoleDao extends HibernateGenericDao<UserRole,Integer> implements IUserRoleDao {
    @Override
    public List<UserRole> queryRolesByUserId(Integer userid) {
        return null;
    }
}
