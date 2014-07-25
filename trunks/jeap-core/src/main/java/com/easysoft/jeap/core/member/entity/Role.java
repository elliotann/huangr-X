package com.easysoft.jeap.core.member.entity;


import com.easysoft.jeap.core.common.entity.IdEntity;
import org.apache.commons.lang.StringUtils;

/**
 * 角色实体
 * @author : andy.huang
 * @since :
 */
public class Role extends IdEntity {
    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean validate(){
        return StringUtils.isNotEmpty(getName());
    }
}
