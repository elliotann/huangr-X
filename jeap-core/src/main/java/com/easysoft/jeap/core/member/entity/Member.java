package com.easysoft.jeap.core.member.entity;

import com.easysoft.jeap.core.common.entity.IdEntity;

/**
 * Created by Administrator on 2014/7/10.
 */
public class Member extends IdEntity {

    private String username; //用户名,手机号
    private String area;//所属大区
    private String realName;//真实姓名
    private String weixinNo;//微信号
    private String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getWeixinNo() {
        return weixinNo;
    }

    public void setWeixinNo(String weixinNo) {
        this.weixinNo = weixinNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
