package com.easysoft.action.member.backend;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.manager.IAuthActionManager;
import com.easysoft.member.backend.manager.IRoleManager;
import com.easysoft.member.backend.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy
 * @since :1.0
 */
@Controller
@RequestMapping({"/core/admin/role"})
public class RoleAction extends BaseController {
    @Autowired
    private IRoleManager roleManager;
    @Autowired
    private IAuthActionManager authActionManager;

    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        List<Role> roleList = roleManager.list();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("roleList",roleList);
        return new ModelAndView("admin/core/auth/rolelist",map);
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(Integer rows,Integer page,String rolename){
        PageOption pageOption = new PageOption();
        roleManager.queryByPage(pageOption,rolename);
        DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<Role>)pageOption.getResult());
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
        return new ModelAndView("admin/core/auth/addrole",map);
    }

    //保存添加
    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(Role role){
        AjaxJson result = new AjaxJson();
        this.roleManager.add(role);
        result.setMsg("角色添加成功");
        return result;
    }
    @RequestMapping(params = {"checkNameExist"})
    @ResponseBody
    public boolean checkNameExist(String rolename,int roleid){
        Role role = roleManager.getRoleByName(rolename,roleid);
        if(role!=null){
            return false;
        }
        return true;
    }
    @RequestMapping(params = {"edit"})
    public ModelAndView edit(int roleid){
        List authList = authActionManager.list();
        Role role = this.roleManager.queryById(roleid);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("authList",authList);
        map.put("role",role);
        return new ModelAndView("admin/core/auth/addrole",map);
    }

    //保存修改
    @RequestMapping(params = {"saveEdit"})
    @ResponseBody
    public AjaxJson saveEdit(Role role,int[] acts){
        AjaxJson result = new AjaxJson();
        this.roleManager.update(role, acts);
        result.setMsg("角色修改成功");
        return result;
    }

    //删除角色
    @RequestMapping(params={"delete"})
    @ResponseBody
    public AjaxJson delete(int id){
        AjaxJson result = new AjaxJson();
        try{
            this.roleManager.deleteById(id);
            result.setMsg("角色删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("角色删除失败");
            result.setSuccess(false);
        }
        return result;
    }
    
    @RequestMapping(params = {"toTest"})
    public ModelAndView toTest(){
        
        return new ModelAndView("admin/core/auth/bankTest");
    }
    
    @RequestMapping(params = {"toTest2"})
    public ModelAndView toTest2(){
        System.out.println("toTest2");
        return new ModelAndView("admin/core/auth/bankTest2");
    }

}
