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
    public AjaxJson save(String data,String tableName,Integer formId){
        AjaxJson result = new AjaxJson();

        Map<String,Class> map = new HashMap<String,Class>();
        map.put("fields", FormField.class);
        FormEntity formEntity = (FormEntity)JsonUtils.jsonToBean(data, FormEntity.class,map);
        if(formId!=null&&formId!=0){
            formEntity.setId(formId);
        }
        formManager.addForm(formEntity);
        return result;
    }

    @RequestMapping(params = {"getColumns"})
    public ModelAndView getColumns(Integer id){
        String data = "";
        Map<String,Object> map = new HashMap<String, Object>();
        if(id!=null&&id!=0){
            FormEntity form = formManager.getFormById(id);
            data = JsonUtils.beanToJsonArray(form.getFields());
        }else{
            data = "[\n" +
                    "    {\"isInForeignKey\":false, \"ispk\":true,\"isNullable\":true, \"inputType\":\"digits\", \"isAutoKey\":true, \"sourceTableName\":\"\", \"sourceTableIDField\":\"\", \"sourceTableTextField\":\"\", \"fieldName\":\"id\", \"text\":\"主键\", \"type\":\"column\", \"icon\":\"images/table_key.png\" }\n" +
                    "]";
        }
        map.put("json",data);
        return new ModelAndView("admin/json_message",map);
    }

    @RequestMapping(params = {"modify"})
    public ModelAndView modify(Integer id){
        Map<String,Object> map = new HashMap<String, Object>();
        FormEntity form = formManager.getFormById(id);
        map.put("form",form);
        return new ModelAndView("form/config/editForm",map);
    }
}
