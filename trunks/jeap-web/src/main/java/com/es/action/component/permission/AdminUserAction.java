package com.es.action.component.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.es.framework.db.pager.PageOption;
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
	public DataGridReturn datalist(PageOption pageOption,String username){
		pageOption.addSearch("username", username);
		adminUserManager.queryUsers(pageOption);
		DataGridReturn<AdminUser> dataGridReturn = new DataGridReturn<AdminUser>(pageOption.getTotalCount(),(List<AdminUser>)pageOption.getResult());
		return dataGridReturn;
	}
	@RequestMapping(params={"toAdd"})
	public String toAdd(){
		return "admin/core/users/addAdminUser";
	}
	@RequestMapping(params={"toEdit"})
	public ModelAndView toEdit(Integer id){
		AdminUser adminUser = adminUserManager.queryUserById(id);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("adminUser",adminUser);
		return new ModelAndView("admin/core/users/editAdminUser",params);
	}
	@RequestMapping(params={"add"})
	@ResponseBody
	public AjaxJsonResult add(AdminUser adminUser){
		adminUserManager.save(adminUser);
		AjaxJsonResult result = new AjaxJsonResult();
		return result;
	}
	@RequestMapping(params={"edit"})
	@ResponseBody
	public AjaxJsonResult edit(AdminUser adminUser){
		adminUserManager.update(adminUser);
		AjaxJsonResult result = new AjaxJsonResult();
		return result;
	}
	
	@RequestMapping(params={"checkNameExist"})
	@ResponseBody
	public boolean checkNameExist(String username,Integer id){
		return adminUserManager.queryUserByName(username,id)==null;
	}
	@RequestMapping(params={"checkEmailExist"})
	@ResponseBody
	public boolean checkEmailExist(String email,Integer id){
		return adminUserManager.queryUserByEmail(email,id)==null;
	}
	@RequestMapping(params={"delete"})
	@ResponseBody
	public AjaxJsonResult delete(Integer id){
		AjaxJsonResult result = new AjaxJsonResult();
		adminUserManager.deleteById(id);
		result.setMsg("删除成功!");
		return result;
	}
}
