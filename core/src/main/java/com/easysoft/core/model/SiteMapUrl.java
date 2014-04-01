package com.easysoft.core.model;

/**
 * SiteMap中的URL实体
 * @author andy<br/>
 * version 1.0
 */
public class SiteMapUrl implements java.io.Serializable{
	private String loc;
	private Long lastmod;
	private String changefreq;//："always", "hourly", "daily", "weekly", "monthly", "yearly"
	private String priority;
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public Long getLastmod() {
		return lastmod;
	}
	public void setLastmod(Long lastmod) {
		this.lastmod = lastmod;
	}
	public String getChangefreq() {
		return changefreq;
	}
	public void setChangefreq(String changefreq) {
		this.changefreq = changefreq;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
}
