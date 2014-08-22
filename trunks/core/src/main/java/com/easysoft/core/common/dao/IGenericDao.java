package com.easysoft.core.common.dao;

import com.easysoft.framework.db.PageOption;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 新通用DAO接口
 * @author : andy.huang
 * @since : 1.0
 */
public interface IGenericDao<T,PK extends Serializable> {
    /**
     * 保存实体
     * @param entity
     */
    public void save(T entity);

    /**
     * 查询列表
     * @return
     */
    public List<T> queryForList();
    /**
     * 查询列表
     * @return
     */
    public List<T> queryForList(Map<String,Object> params);

    /**
     * 要页查询记录
     * @param pageOption
     * @return
     */
    public List<T> queryByPage(PageOption pageOption);

    public T queryById(PK id);

    public void update(T entity);

    public void deleteById(PK id);
}
