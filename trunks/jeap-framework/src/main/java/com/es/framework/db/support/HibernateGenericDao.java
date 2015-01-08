package com.es.framework.db.support;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.es.framework.db.IGenericDao;
import com.es.framework.db.pager.PageOption;

/**
 * hibernateDAO通用接口实现
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

    public void save(T entity) {
        sessionFactory.getCurrentSession().save(entity);

    }


    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }


    public List<T> queryForList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }

    public List<T> queryForList(Map<String, Object> params) {
        return null;
    }


    public List<T> queryForListByHql(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }


    public List<T> queryForPage(PageOption pageOption) {

        return null;
    }


    public List<T> queryForPage(PageOption pageOption, List<Criterion> criterions) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
        for(Criterion criterion : criterions){
            criteria.add(criterion);
        }
        pageOption.setTotalCount(criteria.list().size());
        System.out.println(pageOption.getStartRecord());
        System.out.println(pageOption.getPageSize());
        criteria.setFirstResult(pageOption.getStartRecord());
        criteria.setMaxResults(pageOption.getPageSize());
        List<T> results = criteria.list();
        pageOption.setData(results);

        return results;
    }


    public List<T> queryForHQL(String hql, Map<String, Object> params) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        if(params!=null&&params.size()>0){
            query.setProperties(params);
        }
        
        return query.list();
    }


    public T queryById(PK id) {
        return (T)sessionFactory.getCurrentSession().get(entityClass,id);
    }


    public void update(T entity) {
    	sessionFactory.getCurrentSession().update(entity);
    }


    public void deleteById(PK id) {
    	T entity = this.queryById(id);
    	sessionFactory.getCurrentSession().delete(entity);
    	
    }

	public void excuteBySql(String sql) {
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
		sqlQuery.executeUpdate();
	}
}
