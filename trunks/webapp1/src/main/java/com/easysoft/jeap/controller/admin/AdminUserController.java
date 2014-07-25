package com.easysoft.jeap.controller.admin;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
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
    private static final Log logger = LogFactory.getLog(AdminUserController.class);
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
    public ModelAndView toAdd(Integer id){
        Map<String,Object> params = new HashMap<String,Object>();
        if(id!=null&&id!=0){
            AdminUser adminUser = adminUserManager.queryAdminUserById(id);
            params.put("adminUser",adminUser);
        }
        return new ModelAndView("/admin/adminuser/addAdminuser",params);
    }
    @RequestMapping("/save")
    @ResponseBody
    public AjaxJson save(AdminUser adminUser){
        if(adminUser.getId()!=null&&adminUser.getId()!=0){
            adminUserManager.update(adminUser);
        }else{
            adminUserManager.save(adminUser);
        }

        return new AjaxJson();
    }
    @RequestMapping("/validateUsername")
    @ResponseBody
    public boolean validateUsername(Integer id,String username){
        boolean exist = adminUserManager.isExistUsernameOrEmail(id,username,null);

        if(exist){
            return false;
        }else{
            return true;
        }


    }
    @RequestMapping("/validateEmail")
    @ResponseBody
    public boolean validateEmail(Integer id,String email){
        boolean exist = adminUserManager.isExistUsernameOrEmail(id,null,email);

        if(exist){
            return false;
        }else{
            return true;
        }
    }
    @RequestMapping("/delAdminUser")
    @ResponseBody
    public AjaxJson delAdminUser(Integer id){
        AjaxJson ajaxJson = new AjaxJson();
        try{
            adminUserManager.deleteById(id);
        }catch (Exception e){
            logger.error("删除失败!",e);
            ajaxJson.setSuccess(false);
        }
        return ajaxJson;
    }
}
