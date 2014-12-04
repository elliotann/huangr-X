package com.easysoft.component.form.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_listpage_meta")
public class ListPageMeta extends FormPageMeta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5005619730665307203L;
	
	private FormField field;
	@OneToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="refDBFieldId")
	public FormField getField() {
		return field;
	}
	public void setField(FormField field) {
		this.field = field;
	}
}
