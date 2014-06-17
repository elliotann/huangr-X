package com.easysoft.core.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.manager.IComponentManager;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.framework.component.ComponentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 14-3-4
 * Time: 下午1:01
 *
 * @since:
 */
@Controller
@RequestMapping({"/core/admin/component"})
public class ComponentController extends BaseController {
    @Autowired
    private IComponentManager componentManager;
    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        List<ComponentView> componentList = this.componentManager.list();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("componentList",componentList);
        return new ModelAndView("/core/admin/component/list",params);
    }
    @RequestMapping(params = {"start"})
    @ResponseBody
    public AjaxJson start(String componentid){
        AjaxJson result = new AjaxJson();
        try{

            this.componentManager.start(componentid);
            result.setMsg("启动成功");

        } catch (RuntimeException e) {
            this.logger.error("启动组件[" + componentid + "]", e);
            result.setMsg(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    @RequestMapping(params = {"install"})
    @ResponseBody
    public AjaxJson install(String componentid)
    {
        AjaxJson result = new AjaxJson();
        try
        {
            this.componentManager.install(componentid);
            result.setMsg("安装成功");

        } catch (RuntimeException e) {
            this.logger.error("安装组件[" + componentid + "]", e);
            result.setMsg(e.getMessage());
            result.setSuccess(false);

        }
        return result;
    }
    @RequestMapping(params = {"unInstall"})
    @ResponseBody
    public AjaxJson unInstall(String componentid)
    {
        AjaxJson result = new AjaxJson();
        try
        {
            this.componentManager.unInstall(componentid);
            result.setMsg("卸载成功");
        } catch (RuntimeException e) {
            this.logger.error("卸载组件[" + componentid + "]", e);
            result.setMsg(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
}

