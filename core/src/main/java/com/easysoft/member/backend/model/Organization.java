package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public class Organization extends IdEntity{
    /**名称*/
    private String name;
    /**地址*/
    private String address;
    /**手机*/
    private String phone;
    /**电话*/
    private String tel;
    /**法人*/
    private String legalPerson;
    /**父机构*/
    private Integer pid;

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

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }

    public String getLegalPerson(){
        return this.legalPerson;
    }
    public void setLegalPerson(String legalPerson){
        this.legalPerson = legalPerson;
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
}
