package com.es.jeap.core.action;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.framework.db.pager.PageOption;
import com.es.jeap.core.common.json.DataGridReturn;
import com.es.jeap.core.component.permission.entity.AdminUser;

public abstract class BaseAction {
	@RequestMapping(params={"list"})
	public String list(){
		return listResult();
		
	}
	protected abstract String listResult();
}
