package com.easysoft.jeap.core.member.entity;

import com.easysoft.jeap.core.common.entity.IdEntity;
import com.easysoft.jeap.framework.utils.json.JsonInvisible;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
public class Menu extends IdEntity {
    private Integer pid;
    private String name;
    private String url;

    private List<Menu> children = new ArrayList<Menu>();

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
    @JsonInvisible("List")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
