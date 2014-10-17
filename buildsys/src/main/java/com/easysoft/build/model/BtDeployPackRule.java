package com.easysoft.build.model;

import javax.persistence.*;

@Entity
@Table(name = "BT_DEPLOYPACK_RULE")
public class BtDeployPackRule {
	
	private Long id;
	private RepInfo ri;
	private String deployPackName;
	private String passedTestes;
	private String passedPerson;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BT_DEPLOYPACK_RULE_GEN")
    @TableGenerator(name = "BT_DEPLOYPACK_RULE_GEN", table = "TB_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE",
    	pkColumnValue = "BT_DEPLOYPACK_RULE_ID", allocationSize = 1)
	@Column(name = "ID")
    public Long getId() {
  		return id;
  	}
    
  	public void setId(Long Id) {
  		this.id = Id;
  	}
  	
  	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "RESPINFO_ID")
	public RepInfo getRi() {
		return ri;
	}

	public void setRi(RepInfo ri) {
		this.ri = ri;
	}
	
	@Column(name = "DEPLOYPACK_NAME")
	public String getDeployPackName() {
		return deployPackName;
	}

	public void setDeployPackName(String deployPackName) {
		this.deployPackName = deployPackName;
	}

	@Column(name = "PASSED_TESTES")
	public String getPassedTestes() {
		return passedTestes;
	}

	public void setPassedTestes(String passedTestes) {
		this.passedTestes = passedTestes;
	}
	
	@Column(name = "PASSED_PERSON")
	public String getPassedPerson() {
		return passedPerson;
	}

	public void setPassedPerson(String passedPerson) {
		this.passedPerson = passedPerson;
	}

}
