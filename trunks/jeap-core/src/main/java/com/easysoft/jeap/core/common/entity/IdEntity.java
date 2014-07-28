package com.easysoft.jeap.core.common.entity;

import com.easysoft.jeap.framework.utils.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体公用部分
 * Created by huangxa on 2014/7/9.
 */
public abstract class IdEntity implements Serializable{
    /**主键**/
    protected Integer id;
    /**创建时间**/
    protected String createTime = DateUtil.formatDate2String(new Date());

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
