package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class RoleDao extends HibernateGenericDao<Role,Integer> implements IRoleDao {
    
    public Role getRoleByName(Map<String, Object> condition) {
        return null;
    }
}
