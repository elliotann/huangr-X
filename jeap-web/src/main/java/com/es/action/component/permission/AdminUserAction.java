package com.es.action.component.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.framework.db.pager.PageOption;
import com.es.framework.utils.JsonUtils;
import com.es.jeap.core.common.json.DataGridReturn;
import com.es.jeap.core.component.permission.entity.AdminUser;
import com.es.jeap.core.component.permission.manager.IAdminUserManager;

@Controller
@RequestMapping({"/admin/user"})
public class AdminUserAction {
	@Autowired
	private IAdminUserManager adminUserManager;
	@RequestMapping(params={"list"})
	public String list(){
		return "admin/core/users/adminUserList";
	}
	@RequestMapping(params={"datalist"})
	@ResponseBody
	public String datalist(PageOption pageOption){
		adminUserManager.queryUsers(pageOption);
		DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<AdminUser>)pageOption.getResult());
		return JsonUtils.beanToJson(dataGridReturn);
	}
}

