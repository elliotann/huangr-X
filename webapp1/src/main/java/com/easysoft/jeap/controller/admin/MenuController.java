package com.easysoft.jeap.controller.admin;

import com.easysoft.jeap.core.common.vo.AjaxJson;
import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.core.member.manager.IMenuManager;
import com.easysoft.jeap.framework.db.PageOption;
import com.easysoft.jeap.framework.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/7/19.
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {
    @Autowired
    private IMenuManager menuManager;
    @RequestMapping("/list")
    public ModelAndView list(PageOption pageOption){
        List<Menu> menuList = menuManager.queryForAll();
        String menuStr = JsonUtil.beanToJsonArray(menuList);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("menus",menuStr);
        params.put("menuList",menuList);
        return new ModelAndView("/admin/menu/menuList",params);
    }
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(Integer id,Integer pid){
        Map<String,Object> params = new HashMap<String,Object>();
        Menu menu =  new Menu();
        if(id!=null&&id!=0){
            menu = menuManager.queryById(id);

        }
        if(pid!=null&&pid!=0){
            menu.setPid(pid);
        }
        params.put("menu",menu);
        List<Menu> menuList = menuManager.queryForAll();
        params.put("menus",menuList);
        return new ModelAndView("/admin/menu/addOrUpdateMenu",params);
    }
    @RequestMapping("/save")
    @ResponseBody
    public AjaxJson save(Menu menu){
        if(menu.getId()!=null&&menu.getId()!=0){
            menuManager.update(menu);
        }else{
            menuManager.save(menu);
        }

        return new AjaxJson();
    }
    @RequestMapping(value = "/getMenuJson",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMenuJson(){
        String result = menuManager.getMenuJson();
        return result;
    }

    @RequestMapping("/delMenu")
    @ResponseBody
    public AjaxJson delMenu(Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            menuManager.delMenu(id);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        }
        return ajaxJson;
    }
}
