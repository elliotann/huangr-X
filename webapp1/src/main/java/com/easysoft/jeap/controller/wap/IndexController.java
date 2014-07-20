package com.easysoft.jeap.controller.wap;

import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.core.member.manager.IMenuManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by huangxa on 2014/7/4.
 */
@Controller
public class IndexController {
    @Autowired
    private  IMenuManager menuManager;
    @RequestMapping("/wap/main")
    public String wapIndex(String type){

        return "wap/main/main";


    }
    @RequestMapping("/admin/main")
    public ModelAndView adminIndex(String type){
        List<Menu> rootMenus = menuManager.queryMenusByPid(0);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("rootMenus",rootMenus);
        return new ModelAndView("adminthemes/default/main",params);


    }
}
