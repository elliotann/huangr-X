package com.es.action.component.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.es.jeap.core.common.json.AjaxJsonResult;
import com.es.jeap.core.common.json.DataGridReturn;
import com.es.jeap.core.component.permission.entity.AdminUser;
import com.es.jeap.core.component.permission.entity.Role;
import com.es.jeap.core.component.permission.manager.IRoleManager;

@Controller
@RequestMapping({"/admin/role"})
public class RoleAction {
	@Autowired
	private IRoleManager roleManager;
	@RequestMapping(params={"list"})
	public String list(){
		return "admin/core/role/roleList";
	}
	@RequestMapping(params={"datalist"})
	@ResponseBody
	public DataGridReturn datalist(String roleName){
		List<Role> roles = roleManager.queryForQry(roleName);
		DataGridReturn dataGridReturn = new DataGridReturn(roles.size(),roles);
		return dataGridReturn;
	}
	@RequestMapping(params={"toAdd"})
	public String toAdd(){
		return "admin/core/role/addRole";
	}
	@RequestMapping(params={"add"})
	@ResponseBody
	public AjaxJsonResult add(Role role){
		role.setCreateBy("admin");//todo:后续取登录用户
		roleManager.add(role);
		AjaxJsonResult result = new AjaxJsonResult();
		return result;
	}
	@RequestMapping(params={"toEdit"})
	public ModelAndView toEdit(Integer id){
		Role role = roleManager.queryById(id);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("role",role);
		return new ModelAndView("admin/core/role/editRole",params);
	}
}
