package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.Company;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class CompanyDao extends HibernateGenericDao<Company,Integer> implements ICompanyDao {
    
    public Company queryByQry(Map<String, Object> condition) {
        return null;
    }
}
