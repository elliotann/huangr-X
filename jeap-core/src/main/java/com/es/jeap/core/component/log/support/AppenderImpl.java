package com.es.jeap.core.component.log.support;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.es.framework.db.pager.PageOption;
import com.es.jeap.core.component.log.entity.BnLogItem;
@Component("appender")
public class AppenderImpl implements Appender {

	@Autowired
	private SessionFactory sessionFactory;
	public void doAppend(BnLogItem bnLogItem) {
		sessionFactory.getCurrentSession().save(bnLogItem);
	}

	public List<BnLogItem> queryForPage(PageOption pageOption) {
		// TODO Auto-generated method stub
		return null;
	}

}
