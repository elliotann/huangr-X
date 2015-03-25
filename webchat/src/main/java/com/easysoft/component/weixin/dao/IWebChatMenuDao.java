package com.easysoft.component.weixin.dao;


import java.util.List;

import com.easysoft.component.weixin.model.WebChatMenu;
import com.easysoft.core.common.dao.IGenericDao;

public interface IWebChatMenuDao extends IGenericDao<WebChatMenu, Integer> {
	public List<WebChatMenu> queryMenusByPid(Integer pid);
}
