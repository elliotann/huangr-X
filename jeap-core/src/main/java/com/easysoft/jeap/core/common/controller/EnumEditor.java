package com.easysoft.jeap.core.common.controller;

import com.easysoft.jeap.core.member.entity.AdminUser;

import java.beans.PropertyEditorSupport;

/**
 * Created by huangxa on 2014/7/11.
 */
public class EnumEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        for(AdminUser.UserStatus userStatus : AdminUser.UserStatus.values()){
            if(text.equals(userStatus.name())){
                this.setValue(userStatus);
                break;
            }
        }
    }
}
