package com.easysoft.component.weixin.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.easysoft.core.common.entity.IdEntity;

@Entity
@DiscriminatorValue("PF")
public class ComplexButton  extends Button{
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
