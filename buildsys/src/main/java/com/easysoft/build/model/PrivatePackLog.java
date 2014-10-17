package com.easysoft.build.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BT_PRIVATEPACK_LOG")
public class PrivatePackLog {
	private Long id;	
	private RepInfo ri;  			
	private String depends;
	private String takePersonName;
	private Date takeTime;
	private BuildConfigInfo bc;
	

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BT_DEPLOYPACK_LOG_GEN")
    @TableGenerator(name = "BT_DEPLOYPACK_LOG_GEN", table = "TB_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE",
    	pkColumnValue = "BT_DEPLOYPACK_LOG_ID", allocationSize = 1)
	@Column(name = "ID")
    public Long getId() {
  		return id;
  	}
    
  	public void setId(Long Id) {
  		this.id = Id;
  	} 
  	
	@Column(name = "TAKE_PERSON_NAME")
	public String getTakePersonName() {
		return takePersonName;
	}

	public void setTakePersonName(String takePersonName) {
		this.takePersonName = takePersonName;
	}

	@Column(name = "TAKE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "RESPINFO_ID")
	public RepInfo getRi() {
		return ri;
	}

	public void setRi(RepInfo ri) {
		this.ri = ri;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "BUILDFILE_ID")
	public BuildConfigInfo getBc() {
		return bc;
	}

	public void setBc(BuildConfigInfo bc) {
		this.bc = bc;
	}
	
	@Column(name = "DEPENDS")
	public String getDepends() {
		return depends;
	}

	public void setDepends(String depends) {
		this.depends = depends;
	}
}
