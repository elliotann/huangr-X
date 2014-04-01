package com.easysoft.member.backend.vo;

import org.apache.commons.lang.xwork.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-27
 * Time: 下午10:18
 * To change this template use File | Settings | File Templates.
 */
public class AuthVO {
    private int isEdit;
    private String name;
    private String authid;
    private Integer[] menuids;

    public int getEdit() {
        return isEdit;
    }

    public void setEdit(int edit) {
        isEdit = edit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid;
    }

    public Integer[] getMenuids() {
        return menuids;
    }

    public void setMenuids(Integer[] menuids) {
        this.menuids = menuids;
    }

    public int getAuthIdInt(){
        if(StringUtils.isEmpty(getAuthid())){
            return 0;
        }else{
            return Integer.parseInt(getAuthid());
        }
    }
}
