package com.easysoft.jeap.controller.admin;

import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.manager.IAdminUserManager;
import com.easysoft.jeap.framework.db.PageOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理员管理
 * Created by huangxa on 2014/7/9.
 */
@Controller
@RequestMapping("/admin/adminuser")
public class AdminUserController {
    @Autowired
    private IAdminUserManager adminUserManager;
    @RequestMapping("/list")
    public ModelAndView list(Integer currentPage){
        PageOption pageOption = adminUserManager.queryByPage();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pageOption",pageOption);
        return new ModelAndView("/admin/adminuser/adminuserList",params);
    }
}
