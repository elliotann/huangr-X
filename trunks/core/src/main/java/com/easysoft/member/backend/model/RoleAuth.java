package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;

import javax.persistence.*;

/**
 * 角色功能
 * User: andy
 * Date: 14-5-12
 * Time: 上午11:58
 *
 * @since: 1.0
 */

public class RoleAuth extends IdEntity {
    private Integer roleId;
    private Integer funId;
    private String operids;//操作IDS

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getOperids() {
        return operids;
    }

    public void setOperids(String operids) {
        this.operids = operids;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
