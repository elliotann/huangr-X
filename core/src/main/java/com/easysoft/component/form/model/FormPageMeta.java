package com.easysoft.component.form.model;


import javax.persistence.CascadeType;
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

	private String width;
	private int sort;
	private FormField field;
	private FormEntity form;
	private Integer fieldId;
	
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
	@OneToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="refDBFieldId")
	public FormField getField() {
		return field;
	}
	public void setField(FormField field) {
		this.field = field;
	}
	@ManyToOne
	@JoinColumn(name="form_id")
	public FormEntity getForm() {
		return form;
	}
	public void setForm(FormEntity form) {
		this.form = form;
	}
	@Transient
	public Integer getFieldId() {
		return fieldId;
	}
	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}
	
	
}
