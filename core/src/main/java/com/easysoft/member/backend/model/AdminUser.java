package com.easysoft.member.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * 用户管理员
 * 
 * @author andy
 */
public class AdminUser implements  Serializable{
	
	private Integer userid;
	private String username;
	private String password;
	private int state;
	private String realname;
	private String userno;
	private String userdept;
	private String remark;
	private Long dateline;
	private int[] roleids;
	private int founder;
	private Integer siteid; //子站点id
    private List<AuthAction> authList;
    @Transient
    public List<AuthAction> getAuthList() { return this.authList; }

    public void setAuthList(List<AuthAction> authList) {
        this.authList = authList;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="userid")
    public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
    @Column(name ="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    @Column(name ="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    @Column(name ="state")
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
    @Column(name ="realname")
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
    @Column(name ="userno")
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
    @Column(name ="userdept")
	public String getUserdept() {
		return userdept;
	}
	public void setUserdept(String userdept) {
		this.userdept = userdept;
	}
    @Column(name ="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    @Column(name ="dateline")
	public Long getDateline() {
		return dateline;
	}
	public void setDateline(Long dateline) {
		this.dateline = dateline;
	}
    @Column(name ="founder")
	public int getFounder() {
		return founder;
	}
	public void setFounder(int founder) {
		this.founder = founder;
	}
	@Transient
	public int[] getRoleids() {
		return roleids;
	}
	public void setRoleids(int[] roleids) {
		this.roleids = roleids;
	}
    @Column(name ="siteid")
	public Integer getSiteid() {
		return siteid;
	}
	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}
}
