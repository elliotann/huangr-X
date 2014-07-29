/**
 * Copyright E-Soft Limited (c) 2014. All rights reserved.
 * This software is proprietary to and embodies the confidential
 * technology of E-Soft Limited.  Possession, use, or copying
 * of this software and media is authorized only pursuant to a
 * valid written license from E-Soft or an authorized sublicensor.
 */
package com.easysoft.jeap.controller.admin;

import com.easysoft.jeap.core.member.entity.AdminUser;
import com.easysoft.jeap.core.member.entity.Menu;
import com.easysoft.jeap.core.member.manager.IAdminUserManager;
import com.easysoft.jeap.core.member.manager.IMenuManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录
 * @author :andy.huang
 * @since :
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    private static final Log logger = LogFactory.getLog(LoginController.class);
    @Autowired
    private IMenuManager menuManager;
    @Autowired
    private IAdminUserManager adminUserManager;
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(String username,String password,HttpServletRequest request,HttpServletResponse response){

        try{
            Map<String,Object> params = new HashMap<String,Object>();
            AdminUser adminUser = adminUserManager.login(username,password);
            request.getSession().setAttribute("admin_user_key",adminUser);
            List<Menu> rootMenus = menuManager.queryMenusByPid(0);
            params.put("rootMenus",rootMenus);
            return new ModelAndView("adminthemes/default/main",params);
        }catch (Exception e){
            logger.error("登录失败!",e);
            try {
                response.sendRedirect("toLogin.do");
            } catch (IOException e1) {
                logger.error("跳转登录界面失败!",e1);
            }
        }
        return null;
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/adminthemes/default/login";
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "/adminthemes/default/login";
        }
        session.removeAttribute("admin_user_key");

        return "/adminthemes/default/login";
    }
}
