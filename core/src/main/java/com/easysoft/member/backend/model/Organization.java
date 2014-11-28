package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 機構
 * @author : andy.huang
 * @since :
 */
@MappedSuperclass
public class Organization extends IdEntity{
    public enum OrgType{
        COMPANY,
        DEPT
    }
    /**名称*/
    private String name;
    /**地址*/
    private String address;

    /**父机构*/
    private Integer pid;

    private String text;

    private OrgType orgType;

    private List<Organization> children = new ArrayList<Organization>();
    @Column(name="name")
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    @Column(name = "address")
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }


    @Column(name = "pid")
    public Integer getPid(){
        return this.pid;
    }
    public void setPid(Integer pid){
        this.pid = pid;
    }
    @Transient
    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }
    @Transient
    public boolean validate(){
        return StringUtils.isNotEmpty(name);
    }
    @Transient
    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.name = text;
    }
    @Column(name = "org_type", length = 20)
    @Enumerated(EnumType.STRING)
    public OrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrgType orgType) {
        this.orgType = orgType;
    }
}
