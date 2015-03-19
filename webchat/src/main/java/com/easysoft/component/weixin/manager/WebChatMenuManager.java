package com.easysoft.component.weixin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.component.weixin.dao.IWebChatMenuDao;
import com.easysoft.component.weixin.model.WebChatMenu;

@Service
@Transactional
public class WebChatMenuManager implements IWebChatMenuManager {
	@Autowired
	private IWebChatMenuDao webChatMenuDao;
	public List<WebChatMenu> getAll() {
		return webChatMenuDao.queryForList();
		
	}

	public void add(WebChatMenu webChatMenu) {
		webChatMenuDao.save(webChatMenu);
		
	}

}
