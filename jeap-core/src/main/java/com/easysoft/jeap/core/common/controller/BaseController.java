package com.easysoft.jeap.core.common.controller;

import com.easysoft.jeap.core.member.entity.AdminUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangxa on 2014/7/11.
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(AdminUser.UserStatus.class,new EnumEditor());
    }
}
