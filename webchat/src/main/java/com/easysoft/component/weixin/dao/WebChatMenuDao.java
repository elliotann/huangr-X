package com.easysoft.component.weixin.dao;

import org.springframework.stereotype.Repository;

import com.easysoft.component.weixin.model.WebChatMenu;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class WebChatMenuDao extends HibernateGenericDao<WebChatMenu, Integer> implements
		IWebChatMenuDao {

	
}
