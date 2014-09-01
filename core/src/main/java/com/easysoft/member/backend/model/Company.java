package com.easysoft.member.backend.model;

/**
 * 公司
 * @author : andy.huang
 * @since :
 */
public class Company extends Organization {
    private String compNo;//公司編碼
    /**手机*/
    private String phone;
    /**电话*/
    private String tel;
    /**法人*/
    private String legalPerson;
    private String enShortName;
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

    public String getCompNo() {
        return compNo;
    }

    public void setCompNo(String compNo) {
        this.compNo = compNo;
    }

    public String getEnShortName() {
        return enShortName;
    }

    public void setEnShortName(String enShortName) {
        this.enShortName = enShortName;
    }
}
