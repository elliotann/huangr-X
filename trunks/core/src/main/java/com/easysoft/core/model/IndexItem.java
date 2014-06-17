package com.easysoft.core.model;

import javax.persistence.*;

/**
 * 首页项
 * @author andy
 * 2010-10-12下午03:56:00
 */
@Entity
@Table(name="t_index_item")
public class IndexItem {
	
	private Integer id;
	private String title;
	private String url;
	private int sort;
    @Column(name ="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    @Column(name ="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    @Column(name ="sort")
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
