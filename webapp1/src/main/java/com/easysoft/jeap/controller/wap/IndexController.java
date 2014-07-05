package com.easysoft.jeap.controller.wap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by huangxa on 2014/7/4.
 */
@Controller
public class IndexController {
    @RequestMapping("/wap/main")
    public String wapIndex(String type){

        return "wap/main/main";


    }
    @RequestMapping("/admin/main")
    public String adminIndex(String type){

        return "adminthemes/default/main";


    }
}
