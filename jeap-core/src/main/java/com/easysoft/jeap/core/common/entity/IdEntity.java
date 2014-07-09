package com.easysoft.jeap.core.common.entity;

import java.io.Serializable;

/**
 * 实体公用部分
 * Created by huangxa on 2014/7/9.
 */
public abstract class IdEntity implements Serializable{
    /**主键**/
    protected Integer id;
    /**创建时间**/
    protected String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
