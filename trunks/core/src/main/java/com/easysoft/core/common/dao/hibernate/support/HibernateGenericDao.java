package com.easysoft.core.common.dao.hibernate.support;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.framework.db.PageOption;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Repository
public class HibernateGenericDao<T,PK extends Serializable> implements IGenericDao<T,PK> {
    @Autowired
    protected SessionFactory sessionFactory;
    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);

    }

    @Override
    public List<T> queryForList() {
        return null;
    }

    @Override
    public List<T> queryForList(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<T> queryForListByHql(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<T> queryForPage(PageOption pageOption) {
        return null;
    }

    @Override
    public T queryById(PK id) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void deleteById(PK id) {

    }
}
