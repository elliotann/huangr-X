package com.easysoft.core.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: andy
 * Date: 14-4-14
 * Time: 下午1:06
 *
 * @since:
 */
@Controller
@RequestMapping({"/core/admin/indexItem"})
public class IndexItemController extends BaseController{
    @RequestMapping(params = {"base"})
    public ModelAndView base(){
        Site site  = EsfContext.getContext().getCurrentSite();
        return new ModelAndView("core/admin/index/base");
    }
}
