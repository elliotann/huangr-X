package com.easysoft.member.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-21
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
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
    @RequestMapping(params = {"add"})
    public ModelAndView add(){
        List authList = authActionManager.list();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("authList",authList);
        map.put("isEdit",0);
        return new ModelAndView("core/admin/auth/roleinput",map);
    }

    //保存修改
    @RequestMapping(params = {"saveEdit"})
    @ResponseBody
    public AjaxJson saveEdit(Role role,int[] acts){
        AjaxJson result = new AjaxJson();
        this.roleManager.edit(role, acts);
        result.setMsg("角色修改成功");
       /* this.msgs.add("角色修改成功");
        this.urls.put("角色列表", "role!list.do");*/
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
