package com.es.action.component.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.es.framework.db.pager.PageOption;
import com.es.framework.utils.JsonUtils;
import com.es.jeap.core.common.json.AjaxJsonResult;
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
	public DataGridReturn datalist(PageOption pageOption){
		adminUserManager.queryUsers(pageOption);
		DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<AdminUser>)pageOption.getResult());
		return dataGridReturn;
	}
	@RequestMapping(params={"toAdd"})
	public String toAdd(){
		return "admin/core/users/addAdminUser";
	}
	@RequestMapping(params={"add"})
	@ResponseBody
	public AjaxJsonResult add(AdminUser adminUser){
		adminUserManager.save(adminUser);
		AjaxJsonResult result = new AjaxJsonResult();
		return result;
	}
	
	
	@RequestMapping(params={"checkNameExist"})
	@ResponseBody
	public boolean checkNameExist(String username){
		return adminUserManager.queryUserByName(username)==null;
	}
	@RequestMapping(params={"checkEmailExist"})
	@ResponseBody
	public boolean checkEmailExist(String email){
		return adminUserManager.queryUserByEmail(email)==null;
	}
	@RequestMapping(params={"delete"})
	public String delete(Integer id){
		return "";
	}
}
