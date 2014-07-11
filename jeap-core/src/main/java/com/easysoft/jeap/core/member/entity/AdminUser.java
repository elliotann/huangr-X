package com.easysoft.jeap.core.member.entity;

import com.easysoft.jeap.core.common.entity.IdEntity;

/**
 * Created by huangxa on 2014/7/9.
 */
public class AdminUser extends IdEntity {
    public enum UserStatus{
        ACTIVE,//有效
        INACTIVE //已禁用
    }
    /**编码*/
    private String sn;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**真实姓名**/
    private String realName;

    private UserStatus status;

    private String email;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
