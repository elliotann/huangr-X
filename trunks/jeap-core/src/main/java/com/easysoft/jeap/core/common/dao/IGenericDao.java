package com.easysoft.jeap.core.common.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * dao层通用接口
 * Created by huangxa on 2014/7/9.
 */
public interface IGenericDao<T,PK> {
    /**
     * 保存实体
     * @param entity
     */
    @Insert("MapperGD.insert.entity")
    public void save(T entity);
}
