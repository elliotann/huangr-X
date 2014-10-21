package com.easysoft.core.common.dao.hibernate.support;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.framework.db.PageOption;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */

public class HibernateGenericDao<T,PK extends Serializable> implements IGenericDao<T,PK> {
    @Autowired
    protected SessionFactory sessionFactory;
    private Class<?> entityClass;
    public HibernateGenericDao(){
        entityClass = (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);

    }

    @Override
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public List<T> queryForList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        return criteria.list();
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
    public List<T> queryForPage(PageOption pageOption, List<Criterion> criterions) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        for(Criterion criterion : criterions){
            criteria.add(criterion);
        }
        pageOption.setTotalCount(criteria.list().size());
        criteria.setFirstResult(pageOption.getStartRecord());
        criteria.setMaxResults(pageOption.getPageSize());
        List<T> results = criteria.list();

        return results;
    }

    @Override
    public List<T> queryForHQL(String hql, Map<String, Object> params) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if(params!=null&&params.size()>0){
            query.setProperties(params);
        }
        return query.list();
    }

    @Override
    public T queryById(PK id) {
        return (T)sessionFactory.getCurrentSession().get(entityClass,id);
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void deleteById(PK id) {

    }
}
