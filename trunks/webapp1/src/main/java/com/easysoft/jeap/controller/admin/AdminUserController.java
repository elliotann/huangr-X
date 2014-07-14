package com.easysoft.jeap.controller.admin;

import com.easysoft.jeap.core.common.controller.BaseController;
import com.easysoft.jeap.core.common.vo.AjaxJson;
import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.manager.IAdminUserManager;
import com.easysoft.jeap.framework.db.PageOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class AdminUserController extends BaseController {
    @Autowired
    private IAdminUserManager adminUserManager;
    @RequestMapping("/list")
    public ModelAndView list(PageOption pageOption){
        adminUserManager.queryByPage(pageOption);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("pageOption",pageOption);
        return new ModelAndView("/admin/adminuser/adminuserList",params);
    }
    @RequestMapping("/toAdd")
    public String toAdd(Integer id){
        if(id!=null&&id!=0){

        }
        return "/admin/adminuser/addAdminuser";
    }
    @RequestMapping("/save")
    @ResponseBody
    public AjaxJson save(AdminUser adminUser){
        adminUserManager.save(adminUser);
        return new AjaxJson();
    }
}
