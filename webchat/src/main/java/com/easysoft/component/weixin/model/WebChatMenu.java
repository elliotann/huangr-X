package com.easysoft.component.weixin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.easysoft.core.common.entity.IdEntity;
import com.easysoft.framework.json.annotation.JsonInvisible;
@Entity
@Table (name = "t_webchat_menu")
public class WebChatMenu extends IdEntity{
	private String name;
	private String type;
	private String key;
	private String url;
	private WebChatMenu parent;
	private List<WebChatMenu> children = new ArrayList<WebChatMenu>();
	private String text;

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name="menu_key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@JsonInvisible("List")
	@ManyToOne
	@JoinColumn(name="pid")
	public WebChatMenu getParent() {
		return parent;
	}

	public void setParent(WebChatMenu parent) {
		this.parent = parent;
	}
	@Transient
	public List<WebChatMenu> getChildren() {
		return children;
	}

	public void setChildren(List<WebChatMenu> children) {
		this.children = children;
	}
	
	public void addChild(WebChatMenu child){
		children.add(child);
	}
	@Transient
	public String getText() {
		return name;
	}
	
}
