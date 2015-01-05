package com.es.framework.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.es.framework.utils.DateUtils;

@MappedSuperclass
public abstract class IdEntity {
	private Integer id;
	private String createTime = DateUtils.formatData2String(new Date(), "yyyy-MM-dd:HH:mm:ss");
	private String createBy;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="create_time",nullable=false,length=18)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="create_by",nullable=false)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
