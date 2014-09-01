package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 機構
 * @author : andy.huang
 * @since :
 */
public class Organization extends IdEntity{
    /**名称*/
    private String name;
    /**地址*/
    private String address;

    /**父机构*/
    private Integer pid;

    private String text;

    private List<Organization> children = new ArrayList<Organization>();

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }



    public Integer getPid(){
        return this.pid;
    }
    public void setPid(Integer pid){
        this.pid = pid;
    }

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    public boolean validate(){
        return StringUtils.isNotEmpty(name);
    }

    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.name = text;
    }
}
