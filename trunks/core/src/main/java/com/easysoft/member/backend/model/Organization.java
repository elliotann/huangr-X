package com.easysoft.member.backend.model;

import com.easysoft.core.common.entity.IdEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : andy.huang
 * @since :
 */
public class Organization extends IdEntity{
    private String name;
    private String address;
    private String phone;
    private String tel;
    private String legalPerson;
    private int pid;
    private List<Organization> children = new ArrayList<Organization>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }
}
