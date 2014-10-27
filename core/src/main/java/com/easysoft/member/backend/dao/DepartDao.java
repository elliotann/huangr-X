package com.easysoft.member.backend.dao;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.member.backend.model.Depart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class DepartDao extends HibernateGenericDao<Depart,Integer> implements IDepartDao {
    
    public List<Depart> queryByOrgId(Integer orgId) {
        String hql = "from Depart d where d.compId=:compId";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("compId",orgId);
        return this.queryForHQL(hql,params);
    }

    
    public Depart queryByQry(Map<String, Object> condition) {
        return null;
    }

    
    public List<Depart> queryByCompIdAndPid(Map<String, Object> condition) {
        return null;
    }
}
