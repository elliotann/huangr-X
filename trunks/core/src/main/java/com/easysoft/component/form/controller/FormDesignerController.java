package com.easysoft.component.form.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.FormField;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
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
        return new ModelAndView("admin/component/form/formList");
    }
    @RequestMapping(params = {"toDesigner"})
    public ModelAndView toDesigner(){
        return new ModelAndView("admin/component/form/addForm");
    }

    @RequestMapping(params = {"delete"})
    @ResponseBody
    public AjaxJson delete(Integer id) throws Exception {
        AjaxJson json = new AjaxJson();
        try {
            this.formManager.delFormById(id);
            json.setMsg("表单删除成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            json.setMsg("表单删除失败:" + e.getMessage());
            json.setSuccess(false);
        }

        return json;
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
    @RequestMapping(params = {"dbFormEle"})
    public ModelAndView dbFormEle(){
        return new ModelAndView("form/config/dbFormEle");
    }
    @RequestMapping(params = {"formList"})
    public ModelAndView formList(){
        return new ModelAndView("form/config/formlistdesigner");
    }
    @RequestMapping(params = {"formDesigner"})
    public ModelAndView formDesigner(){
        return new ModelAndView("form/config/formdesigner");
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
        }else{
            formEntity.setCreateBy(UserServiceFactory.getUserService().getCurrentUser().getUsername());
        }
        formManager.addForm(formEntity);
        return result;
    }

    @RequestMapping(params = {"getColumns"})
    public ModelAndView getColumns(Integer id,String type){
        String data = "";
        Map<String,Object> map = new HashMap<String, Object>();
        if(id!=null&&id!=0){
            FormEntity form = formManager.getFormById(id,type);

            data = JsonUtils.beanToJsonArray(form.getFields());
        }else{
            data = "[\n" +
                    "    {\"dataType\":\"INTEGER\",\"isInForeignKey\":false, \"ispk\":true,\"isNullable\":false, \"inputType\":\"digits\", " +
                    "\"isAutoKey\":true, \"sourceTableName\":\"\", \"sourceTableIDField\":\"\", \"sourceTableTextField\":\"\", " +
                    "\"fieldName\":\"id\", \"labelName\":\"主键\", \"type\":\"column\", \"icon\":\"images/table_key.png\" }\n" +
                    "]";
        }
        map.put("json",data);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"getDisColumns"})
    public ModelAndView getDisColumns(Integer id,String type){
        String data = "";
        Map<String,Object> map = new HashMap<String, Object>();
        if(id!=null&&id!=0){
            FormEntity form = formManager.getFormById(id,type);
            for(FormField field : form.getFields()){
                field.setFieldName(StringUtil.formatDBFieldName(field.getFieldName()));
            }
            data = JsonUtils.beanToJsonArray(form.getFields());
        }else{
            data = "[\n" +
                    "    {\"dataType\":\"INTEGER\",\"isInForeignKey\":false, \"ispk\":true,\"isNullable\":false, \"inputType\":\"digits\", " +
                    "\"isAutoKey\":true, \"sourceTableName\":\"\", \"sourceTableIDField\":\"\", \"sourceTableTextField\":\"\", " +
                    "\"fieldName\":\"id\", \"labelName\":\"主键\", \"type\":\"column\", \"icon\":\"images/table_key.png\" }\n" +
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
        return new ModelAndView("admin/component/form/editForm",map);
    }
}
