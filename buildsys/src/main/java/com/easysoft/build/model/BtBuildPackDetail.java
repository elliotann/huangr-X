package com.easysoft.build.model;

import javax.persistence.*;

@Entity
@Table(name = "BT_BUILDPACK_DETAIL")
public class BtBuildPackDetail {

	private Long id;
	private RepInfo ri; 
	private String buildPackName;	
	private String buildPackDetail;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BT_BUILDPACK_DETAIL_GEN")
    @TableGenerator(name = "BT_BUILDPACK_DETAIL_GEN", table = "TB_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VALUE",
    	pkColumnValue = "BT_BUILDPACK_DETAIL_ID", allocationSize = 1)
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
	
	@Column(name = "BUILDPACK_NAME")
	public String getBuildPackName() {
		return buildPackName;
	}

	public void setBuildPackName(String buildPackName) {
		this.buildPackName = buildPackName;
	}
	
	@Lob
    @Column(name = "BUILDPACK_DETAIL",columnDefinition = "CLOB")
	public String getBuildPackDetail() {
		return buildPackDetail;
	}

	public void setBuildPackDetail(String buildPackDetail) {
		this.buildPackDetail = buildPackDetail;
	}
	
}
