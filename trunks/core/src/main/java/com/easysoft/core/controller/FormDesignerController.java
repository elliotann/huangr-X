package com.easysoft.core.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IFormManager;
import com.easysoft.core.model.FormEntity;
import com.easysoft.core.model.FormField;
import com.easysoft.framework.utils.JsonUtils;
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
 * Date: 14-3-30
 */
@Controller
@RequestMapping({"/core/admin/designer"})
public class FormDesignerController extends BaseController {
    @Autowired
    private IFormManager formManager;
    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        return new ModelAndView("form/config/list");
    }
    @RequestMapping(params = {"toDesigner"})
    public ModelAndView toDesigner(){
        return new ModelAndView("form/config/addForm");
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
        List userList= this.formManager.list();
        DataGridReturn dataGridReturn = new DataGridReturn(userList.size(),userList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"designer"})
    public ModelAndView designer(){
        return new ModelAndView("form/config/designer");
    }
    @RequestMapping(params = {"save"})
    @ResponseBody
    public AjaxJson save(String data,String tableName){
        AjaxJson result = new AjaxJson();
        System.out.println(data);
        Map<String,Class> map = new HashMap<String,Class>();
        map.put("fields", FormField.class);
        FormEntity formEntity = (FormEntity)JsonUtils.jsonToBean(data, FormEntity.class,map);

        formManager.addForm(formEntity);
        return result;
    }
}
