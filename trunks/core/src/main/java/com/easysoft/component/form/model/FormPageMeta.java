package com.easysoft.component.form.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.easysoft.core.common.entity.IdEntity;
@MappedSuperclass
public abstract class FormPageMeta extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum ShowType{
		SHORT_SEARCH,
		SEARCH,
		LIST,
		ADD_FORM
	}
	private String width;
	private int sort;
	
	private Integer formId;
	private Integer fieldId;
	private ShowType showType;
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	

	@Column(name="form_id")
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	@Transient
	public Integer getFieldId() {
		return fieldId;
	}
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}
	
	@Column(name="show_type")
	@Enumerated(EnumType.STRING)
	public ShowType getShowType() {
		return showType;
	}
	public void setShowType(ShowType showType) {
		this.showType = showType;
	}
	
}
