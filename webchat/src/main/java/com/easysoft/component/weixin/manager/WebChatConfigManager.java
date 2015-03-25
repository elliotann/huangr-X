package com.easysoft.component.weixin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.weixin.dao.IWebChatConfigDao;
import com.easysoft.component.weixin.model.WebChatConfig;

@Service
@Transactional
public class WebChatConfigManager implements IWebChatConfigManager {
	@Autowired
	private IWebChatConfigDao webChatConfigDao;
	public List<WebChatConfig> getAll() {
		
		return webChatConfigDao.queryForList();
	}
	public void add(WebChatConfig webChatConfig) {
		webChatConfigDao.save(webChatConfig);
		
	}
	public WebChatConfig get(Integer id) {
		
		return webChatConfigDao.queryById(id);
	}
	public void edit(WebChatConfig webChatConfig) {
		webChatConfigDao.update(webChatConfig);
		
	}
	
}
