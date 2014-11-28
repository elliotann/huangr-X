package com.easysoft.member.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 公司
 * @author : andy.huang
 * @since : 1.0
 */
@Entity
@Table(name = "t_company")
public class Company extends Organization {
    private String compNo;//公司編碼
    /**手机*/
    private String phone;
    /**电话*/
    private String tel;
    /**法人*/
    private String legalPerson;
    private String enShortName;
    @Column(name = "phone")
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    @Column(name = "tel")
    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }
    @Column(name = "LEGAL_PERSON")
    public String getLegalPerson(){
        return this.legalPerson;
    }
    public void setLegalPerson(String legalPerson){
        this.legalPerson = legalPerson;
    }
    @Column(name = "comp_no")
    public String getCompNo() {
        return compNo;
    }

    public void setCompNo(String compNo) {
        this.compNo = compNo;
    }
    @Column(name = "EN_SHORT_NAME")
    public String getEnShortName() {
        return enShortName;
    }

    public void setEnShortName(String enShortName) {
        this.enShortName = enShortName;
    }
}
