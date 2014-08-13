package com.easysoft.core.common.dao;

import java.io.Serializable;

/**
 * 新通用DAO接口
 * @author : andy.huang
 * @since : 1.0
 */
public interface IGenericDao<T,PK extends Serializable> {
    public void save(T entity);
}
