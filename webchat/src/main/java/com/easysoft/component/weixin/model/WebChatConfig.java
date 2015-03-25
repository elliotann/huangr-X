package com.easysoft.component.weixin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.easysoft.core.common.entity.IdEntity;
/**
 * 微信公众号配置住息
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_webchat_config")
public class WebChatConfig extends IdEntity{
	public enum ChatType{
		SubscriptionNo,
		ServiceNo,
		CompanyNo
		
	}
	private String title;//公众号名称
	private String appId;
	private String appsecret;
	private String weixinNo;
	private ChatType chatType;
	private String text;
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
	@Column(name="chat_type")
	@Enumerated(EnumType.STRING)
	public ChatType getChatType() {
		return chatType;
	}
	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Transient
	public String getText(){
		return this.title;
	}
	
}
