package com.easysoft.member.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.member.backend.manager.IAuthActionManager;
import com.easysoft.member.backend.manager.IRoleManager;
import com.easysoft.member.backend.model.Role;

/**
 * User: andy
 * Date: 14-2-21
 */
@Controller
@RequestMapping({"/core/admin/role"})
public class RoleController extends BaseController {
    @Autowired
    private IRoleManager roleManager;
    @Autowired
    private IAuthActionManager authActionManager;

    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        List roleList = roleManager.list();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("roleList",roleList);
        return new ModelAndView("core/admin/auth/rolelist",map);
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
        List roleList = roleManager.list();
        DataGridReturn dataGridReturn = new DataGridReturn(roleList.size(),roleList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"add"})
    public ModelAndView add(){
        List authList = authActionManager.list();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("authList",authList);
        map.put("isEdit",0);
        return new ModelAndView("core/admin/auth/addrole",map);
    }

    //保存修改
    @RequestMapping(params = {"saveEdit"})
    @ResponseBody
    public AjaxJson saveEdit(Role role,int[] acts){
        AjaxJson result = new AjaxJson();
        this.roleManager.edit(role, acts);
        result.setMsg("角色修改成功");
        return result;
    }

    //保存添加
    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(Role role,int[] acts){
        AjaxJson result = new AjaxJson();
        this.roleManager.add(role, acts);
        result.setMsg("角色添加成功");
        return result;
    }
}
