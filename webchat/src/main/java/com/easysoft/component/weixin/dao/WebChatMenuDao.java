package com.easysoft.component.weixin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easysoft.component.weixin.model.WebChatMenu;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;

@Repository
public class WebChatMenuDao extends HibernateGenericDao<WebChatMenu, Integer> implements
		IWebChatMenuDao {

	public List<WebChatMenu> queryMenusByPid(Integer pid) {
		String hql = "";
		if(pid==null){
			hql = "from WebChatMenu m where m.parent.id is null";
		}else{
			hql = "from WebChatMenu m where m.parent.id ="+pid;
		}
		
		return this.queryForListByHql(hql);
	}

	
}
