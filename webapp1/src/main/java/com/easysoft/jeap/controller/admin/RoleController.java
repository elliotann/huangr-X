package com.easysoft.jeap.controller.admin;

import com.easysoft.jeap.framework.db.PageOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {
    @RequestMapping("/list")
    public ModelAndView list(PageOption pageOption){
        return new ModelAndView("/admin/role/roleList");
    }
}
