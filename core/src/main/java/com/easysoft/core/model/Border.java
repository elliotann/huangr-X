package com.easysoft.core.model;

/**
 * 边框实体 
 * @author andy
 */
public class Border extends Resource {
	private Integer id;
	private String borderid;
	private String bordername;
	private String themepath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBorderid() {
		return borderid;
	}
	public void setBorderid(String borderid) {
		this.borderid = borderid;
	}
	public String getBordername() {
		return bordername;
	}
	public void setBordername(String bordername) {
		this.bordername = bordername;
	}
	public String getThemepath() {
		return themepath;
	}
	public void setThemepath(String themepath) {
		this.themepath = themepath;
	}
	
}
