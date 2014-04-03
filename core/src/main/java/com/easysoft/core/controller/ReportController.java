package com.easysoft.core.controller;

import com.easysoft.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: andy
 * Date: 14-4-3
 * Time: 下午1:49
 *
 * @since:
 */
@Controller
@RequestMapping({"/core/admin/report"})
public class ReportController extends BaseController{
    @RequestMapping(params = {"test"})
    public ModelAndView test(){
        return new ModelAndView("core/admin/report/report");
    }
}
