package com.easysoft.action.component.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.manager.IPageMetaManager;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.FormField;
import com.easysoft.component.form.model.ListPageMeta;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;


/**
 * User: andy
 * Date: 14-3-30
 */
@Controller
@RequestMapping({"/core/admin/designer"})
public class FormDesignerAction extends BaseController {
    @Autowired
    private IFormManager formManager;
    @Autowired
    private IPageMetaManager pageMetaManager;
    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        return new ModelAndView("admin/component/form/formList");
    }
    @RequestMapping(params = {"toDesigner"})
    public ModelAndView toDesigner(){
        return new ModelAndView("admin/component/form/addForm");
    }
    @RequestMapping(params = {"pageSetting"})
    public ModelAndView pageSetting(Integer formId){
    	//根据id获取表单信息
    	FormEntity formEntity =formManager.getFormById(formId);
    	Map<String,Object> params = new HashMap<String,Object>();
    	
    	List<Map<String,Object>> json = new ArrayList<Map<String,Object>>();
    	
    	for(ListPageMeta field:formEntity.getPageMetas()){
    		Map<String,Object> fieldsJson = new HashMap<String,Object>();
    		fieldsJson.put("field", field.getField().getFieldName());
    		fieldsJson.put("title", field.getField().getDisplayName());
    		fieldsJson.put("width", 150);
    		fieldsJson.put("fieldId", field.getField().getId());
    		json.add(fieldsJson);
    	}

    	params.put("cols", JsonUtils.beanToJsonArray(json));
    	params.put("formEntity", formEntity);
        return new ModelAndView("admin/component/form/pageSetting",params);
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
    @RequestMapping(params = {"selectColumns"})
    public ModelAndView selectColumns(Integer formId){
    	FormEntity formEntity =formManager.getFormById(formId);
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("formEntity", formEntity);
        return new ModelAndView("admin/component/form/selectColumns",params);
    }
    @RequestMapping(params = {"saveColumns"})
    @ResponseBody
    public ModelAndView saveColumns(Integer formId,String data){
    	Object[] pageMetas = JsonUtils.getDTOArray(data, ListPageMeta.class);
    	List<ListPageMeta> listPageMetas = (List)Arrays.asList(pageMetas);
    	pageMetaManager.savePageMetas(formId, listPageMetas);
    	return null;
    }
    
}
