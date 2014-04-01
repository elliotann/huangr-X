package com.easysoft.core.dispatcher;

import com.easysoft.core.model.Site;
import com.easysoft.core.model.JEAPApp;

/**
 * 前台页面实体
 * @author andy
 * @since : 1.0
 */
public class FacadePage {
	
	private Integer id;
	private Site site;
	private String uri;
	private JEAPApp app;
	

	public FacadePage(){

	}

	public FacadePage(Site site){
		this.site = site;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JEAPApp getApp() {
		return app;
	}

	public void setApp(JEAPApp app) {
		this.app = app;
	}
	
	
 
	

}