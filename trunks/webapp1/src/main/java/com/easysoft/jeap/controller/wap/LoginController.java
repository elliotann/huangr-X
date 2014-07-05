package com.easysoft.jeap.controller.wap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangxa on 2014/7/4.
 */
@Controller
@RequestMapping("/wap")
public class LoginController {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "wap/login";
    }
}
