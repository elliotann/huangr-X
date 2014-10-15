package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.Employ;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class EmployDao extends HibernateGenericDao<Employ,Integer> implements IEmployDao {
    @Override
    public Employ queryEmployByCondition(Map<String, Object> condition) {
        return null;
    }
}
