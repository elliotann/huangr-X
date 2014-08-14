package com.easysoft.core.common.dao.hibernate.support;

import com.easysoft.core.common.dao.IGenericDao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public class MybatisGenericDao<T,PK extends Serializable> extends SqlMapClientDaoSupport implements IGenericDao<T,PK> {
    public static final String SQL_INSERT = "insert";
    private String sqlMapNamespace = "";

    public String getSqlMapNamespace() {
        return sqlMapNamespace;
    }

    public void setSqlMapNamespace(String sqlMapNamespace) {
        this.sqlMapNamespace = sqlMapNamespace;
    }

    @Override
    public void save(T entity) {
        this.getSqlMapClientTemplate().insert(sqlMapNamespace + "."+ SQL_INSERT,entity);
    }

    @Override
    public List<T> queryForList() {
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

