package com.easysoft.component.weixin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.easysoft.core.common.entity.IdEntity;

/**
 * 微信公众号配置住息
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_webchat_config")
public class WebChatConfig extends IdEntity{
	private String appId;
	private String appsecret;
	private String weixinNo;
	@Column(name="appid")
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Column(name="appsecret")
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	@Column(name="weixin_no")
	public String getWeixinNo() {
		return weixinNo;
	}
	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}
	
}
