package com.easysoft.component.form.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="t_listpage_meta")
public class ListPageMeta extends FormPageMeta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5005619730665307203L;
	public enum ShowType{
		SHORT_SEARCH,
		SEARCH,
		LIST
	}
	private ShowType showType;
	@Column(name="show_type")
	@Enumerated(EnumType.STRING)
	public ShowType getShowType() {
		return showType;
	}
	public void setShowType(ShowType showType) {
		this.showType = showType;
	}
	
}
