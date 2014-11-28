package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class OrganizatiOnDao extends HibernateGenericDao<Organization,Integer> implements IOrganizatiOnDao {
    
    public List<Organization> queryByPid(int pid) {
        return null;
    }
}
