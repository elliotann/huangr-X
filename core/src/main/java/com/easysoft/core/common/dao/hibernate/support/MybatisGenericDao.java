package com.easysoft.core.common.dao.hibernate.support;

import com.easysoft.core.common.dao.IGenericDao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.Serializable;

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
}

