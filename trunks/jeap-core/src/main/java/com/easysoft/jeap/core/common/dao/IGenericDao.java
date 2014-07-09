package com.easysoft.jeap.core.common.dao;

/**
 * dao层通用接口
 * Created by huangxa on 2014/7/9.
 */
public interface IGenericDao<T> {
    /**
     * 保存实体
     * @param entity
     */
    public void save(T entity);
}
