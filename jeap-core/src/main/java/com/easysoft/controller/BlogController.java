package com.easysoft.controller;

import com.easysoft.jeap.service.IUserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangxa on 2014/7/3.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IUserTableService userTableService;
    @RequestMapping("/index")
    public String index(){
        userTableService.getUser();
        return "blog/index";
    }
}
