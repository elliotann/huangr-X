package com.easysoft.jeap.core.member.entity;

import com.easysoft.jeap.core.common.entity.IdEntity;

/**
 * Created by Administrator on 2014/7/19.
 */
public class Menu extends IdEntity {
    private Integer pid;
    private String name;
    private String url;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
