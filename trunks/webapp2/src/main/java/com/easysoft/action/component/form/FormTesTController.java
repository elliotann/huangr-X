package com.easysoft.action.component.form;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.dao.hibernate.DataGrid;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.framework.utils.BeanUtils;
import java.util.HashMap;
import java.util.Map;
import com.easysoft.component.form.entity.FormTesTEntity;
import com.easysoft.component.form.service.FormTesTServiceI;
/**   
 * @Title: Controller
 * @Description: 表单测试
 * @author onlineGenerator
 * @date 2014-12-01
 * @since : v1.0.0
 *
 */
@Controller
@RequestMapping("/core/admin/formTesT")
public class FormTesTController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FormTesTController.class);

	@Autowired
	private FormTesTServiceI formTesTService;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 表单测试列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "formTesT")
	public ModelAndView formTesT() {
		return new ModelAndView("admin/component/form/formTesTList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "dataGrid")
	public ModelAndView datagrid(FormTesTEntity formTesT,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        List entityist= this.formTesTService.queryForList();
        DataGridReturn dataGridReturn = new DataGridReturn(entityist.size(),entityist);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
	}

    /**
    * 表单测试新增页面跳转
    *
    * @return
    */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(FormTesTEntity formTesT, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(formTesT.getId())) {
            formTesT = formTesTService.queryById(formTesT.getId());
            req.setAttribute("formTesTPage", formTesT);
        }
        return new ModelAndView("admin/component/form/formTesT-add");
    }
    /**
    * 添加表单测试
    *
    * @param ids
    * @return
    */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(FormTesTEntity formTesT, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "表单测试添加成功";
        try{
            formTesTService.save(formTesT);

        }catch(Exception e){
            e.printStackTrace();
            message = "表单测试添加失败";
        }
        j.setMsg(message);
        return j;
    }

    /**
    * 表单测试编辑页面跳转
    *
    * @return
    */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(FormTesTEntity formTesT, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(formTesT.getId())) {
             formTesT = formTesTService.queryById(formTesT.getId());
            req.setAttribute("formTesT", formTesT);
        }
        return new ModelAndView("admin/component/form/formTesT-add");
    }
    /**
    * 更新表单测试
    *
    * @param ids
    * @return
    */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(FormTesTEntity formTesT, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "表单测试更新成功";
        FormTesTEntity t = formTesTService.queryById(formTesT.getId());
        try {
            BeanUtils.copyBeanNotNull2Bean(formTesT, t);
            formTesTService.update(t);

        } catch (Exception e) {
            e.printStackTrace();
            message = "表单测试更新失败";

        }
        j.setMsg(message);
        return j;
    }

    /**
    * 删除表单测试
    *
    * @return
    */
    @RequestMapping(params = "delete")
    @ResponseBody
    public AjaxJson doDel(FormTesTEntity formTesT, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        message = "表单测试删除成功";
        try{
            formTesTService.deleteById(formTesT.getId());

        }catch(Exception e){
            e.printStackTrace();
            message = "表单测试删除失败";

        }
        j.setMsg(message);
        return j;
    }

}
