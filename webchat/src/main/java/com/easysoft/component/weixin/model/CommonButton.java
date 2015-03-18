package com.easysoft.component.weixin.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.easysoft.core.common.entity.IdEntity;

@Entity
@DiscriminatorValue("TF")
public class CommonButton extends Button{
	private String type;
	private String key;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
