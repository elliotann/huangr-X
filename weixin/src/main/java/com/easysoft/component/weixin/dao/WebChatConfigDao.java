package com.easysoft.component.weixin.dao;

import org.springframework.stereotype.Repository;

import com.easysoft.component.weixin.model.WebChatConfig;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class WebChatConfigDao extends HibernateGenericDao<WebChatConfig, Integer> implements
		IWebChatConfigDao {

	
}
