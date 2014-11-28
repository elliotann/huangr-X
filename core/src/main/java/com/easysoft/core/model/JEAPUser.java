package com.easysoft.core.model;

import com.easysoft.core.common.entity.IdEntity;

import javax.persistence.*;
/**
 * 用户实体
 * @author andy
 * @since : 1.0
 */
@Entity
@Table(name="jeap_user")
public class JEAPUser extends IdEntity{


	private String username;
	private String companyname;
	private String password;
	private String address;
	private String legalrepresentative;
	private String linkman;
	private String tel;
	private String mobile;
	private String email;
	private String logofile;
	private String licensefile;
	private Integer defaultsiteid;
	private Integer usertype; // 0个人，1公司
	
	private JEAPUserDetail userDetail;
	

	public Integer getDefaultsiteid() {
		return defaultsiteid;
	}

	public void setDefaultsiteid(Integer defaultsiteid) {
		this.defaultsiteid = defaultsiteid;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalrepresentative() {
		return legalrepresentative;
	}

	public void setLegalrepresentative(String legalrepresentative) {
		this.legalrepresentative = legalrepresentative;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogofile() {
		return logofile;
	}

	public void setLogofile(String logofile) {
		this.logofile = logofile;
	}

	public String getLicensefile() {
		return licensefile;
	}

	public void setLicensefile(String licensefile) {
		this.licensefile = licensefile;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	@Transient
	public JEAPUserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(JEAPUserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


}
