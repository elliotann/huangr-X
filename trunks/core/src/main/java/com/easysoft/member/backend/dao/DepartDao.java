package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.Depart;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class DepartDao extends HibernateGenericDao<Depart,Integer> implements IDepartDao {
    @Override
    public List<Depart> queryByOrgId(Integer orgId) {
        return null;
    }

    @Override
    public Depart queryByQry(Map<String, Object> condition) {
        return null;
    }

    @Override
    public List<Depart> queryByCompIdAndPid(Map<String, Object> condition) {
        return null;
    }
}
