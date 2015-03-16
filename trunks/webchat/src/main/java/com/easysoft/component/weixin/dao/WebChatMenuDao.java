package com.easysoft.component.weixin.dao;

import org.springframework.stereotype.Repository;

import com.easysoft.component.weixin.model.Button;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class WebChatMenuDao extends HibernateGenericDao<Button, Integer> implements
		IWebChatMenuDao {

	
}
