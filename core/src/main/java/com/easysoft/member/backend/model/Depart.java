package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;
import org.apache.commons.lang3.StringUtils;

/**
 * 部门
 * @author : andy.huang
 * @since :
 */
public class Depart extends IdEntity {
    private String name;
    private int pid;
    private int orgId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public boolean validate(){
        return StringUtils.isNotEmpty(name);
    }
}
