package com.easysoft.core.model;

import javax.persistence.*;

/**
 * @author andy
 * @since : 1.0
 */
@Entity
@Table(name="jeap_userdetail")
@PrimaryKeyJoinColumn(name = "id")
public class JEAPUserDetail {
	private Integer id;
    private JEAPUser jeapUser;
	private String bussinessscope;
	private String regaddress;
	private Long regdate;
	private Integer corpscope;
	private String corpdescript;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getBussinessscope() {
		return bussinessscope;
	}
	public void setBussinessscope(String bussinessscope) {
		this.bussinessscope = bussinessscope;
	}
	public String getRegaddress() {
		return regaddress;
	}
	public void setRegaddress(String regaddress) {
		this.regaddress = regaddress;
	}
	
	public Long getRegdate() {
		return regdate;
	}
	public void setRegdate(Long regdate) {
		this.regdate = regdate;
	}
	public Integer getCorpscope() {
		return corpscope;
	}
	public void setCorpscope(Integer corpscope) {
		this.corpscope = corpscope;
	}
	public String getCorpdescript() {
		return corpdescript;
	}
	public void setCorpdescript(String corpdescript) {
		this.corpdescript = corpdescript;
	}
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="userid")
    public JEAPUser getJeapUser() {
        return jeapUser;
    }

    public void setJeapUser(JEAPUser jeapUser) {
        this.jeapUser = jeapUser;
    }
}
