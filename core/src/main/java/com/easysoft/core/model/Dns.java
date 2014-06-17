package com.easysoft.core.model;

import java.io.Serializable;

/**
 * JEAP 平台DNS
 * @author andy
 * @version 1.0
 */
public class Dns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7525130004L;
	
	private String domain;
	private Site site;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	
	

}
