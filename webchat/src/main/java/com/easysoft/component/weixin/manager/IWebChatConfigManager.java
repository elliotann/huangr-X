package com.easysoft.component.weixin.manager;

import java.util.List;

import com.easysoft.component.weixin.model.WebChatConfig;

public interface IWebChatConfigManager {
	public List<WebChatConfig> getAll();
	public void add(WebChatConfig webChatConfig);
}
