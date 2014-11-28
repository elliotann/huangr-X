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
@Entity
@Table(name="t_role_auth")
public class RoleAuth extends IdEntity {
    private Integer roleId;
    private Integer funId;
    private String operids;//操作IDS

    @Column(name="fun_id")
    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }
    
    @Column(name="operids")
    public String getOperids() {
        return operids;
    }

    public void setOperids(String operids) {
        this.operids = operids;
    }
    @Column(name="role_id")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
