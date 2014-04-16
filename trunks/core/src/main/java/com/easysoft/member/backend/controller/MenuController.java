package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping({"/core/admin/menu"})
public class MenuController extends BaseController {
    @Autowired
    private IMenuManager menuManager;

    @RequestMapping(params = {"listframe"})
    public ModelAndView listframe(){

        return new ModelAndView("core/admin/menu/list_frame");
    }
    @RequestMapping(params = {"list"})
    public ModelAndView list(){

        return new ModelAndView("core/admin/menu/list");
    }

    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
        List<Menu> menuList  = this.menuManager.getMenuList();
        DataGridReturn dataGridReturn = new DataGridReturn(menuList.size(),menuList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"add"})
    public ModelAndView add(){
        List<Menu> menuList  = this.menuManager.getMenuList();


        Map<String,Object> map = new HashMap<String, Object>();
        map.put("menuList",menuList);
        return new ModelAndView("core/admin/menu/add",map);
    }

    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(Menu menu){
        AjaxJson json = new AjaxJson();
        try{
            this.menuManager.add(menu);
            json.setMsg("新增菜单成功");

        }catch(RuntimeException e){
            json.setMsg(e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
}
