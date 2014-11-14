package com.easysoft.action.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IComponentManager;
import com.easysoft.framework.component.ComponentView;
import com.easysoft.framework.utils.JsonUtils;

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
        return new ModelAndView("/admin/core/component/componentList");
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	List<ComponentView> componentList = this.componentManager.list();
    	if(!componentList.isEmpty()){
    		for(ComponentView componentView : componentList){
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("id", componentView.getComponentid());
    			map.put("name", componentView.getName());
    			map.put("install_state", componentView.getInstall_state());
    			map.put("enable", componentView.getEnable_state());
    			
    			map.put("componentid", componentView.getComponentid());
    			if(!componentView.getPluginList().isEmpty()){
    				map.put("children", componentView.getPluginList());
    			}
    			result.add(map);
    		}
    	}
        String json = JsonUtils.beanToJsonArray(result);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
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
    @RequestMapping(params = {"stop"})
    @ResponseBody
    public AjaxJson stop(String componentid){
        AjaxJson result = new AjaxJson();
        try{

            this.componentManager.stop(componentid);
            result.setMsg("停用成功");

        } catch (RuntimeException e) {
            this.logger.error("停用组件[" + componentid + "]", e);
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

