package com.easysoft.jeap.controller.admin;

import com.easysoft.jeap.core.common.vo.AjaxJson;
import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.entity.Role;
import com.easysoft.jeap.core.member.manager.IRoleManager;
import com.easysoft.jeap.framework.db.PageOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private IRoleManager roleManager;
    @RequestMapping("/list")
    public ModelAndView list(PageOption pageOption){
        roleManager.queryByPage(pageOption);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pageOption",pageOption);
        return new ModelAndView("/admin/role/roleList",params);
    }
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Integer id){
        Map<String,Object> params = new HashMap<String,Object>();
        if(id!=null&&id!=0){
            Role role = roleManager.queryById(id);
            params.put("role",role);
        }
        return new ModelAndView("/admin/role/addRole");
    }
    @RequestMapping("/save")
    @ResponseBody
    public AjaxJson save(Role role){
        if(role.getId()!=null&&role.getId()!=0){
            roleManager.update(role);
        }else{
            roleManager.save(role);
        }

        return new AjaxJson();
    }
}
