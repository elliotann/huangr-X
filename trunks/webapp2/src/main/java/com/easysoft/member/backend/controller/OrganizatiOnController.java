package com.easysoft.member.backend.controller;


import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.dao.hibernate.DataGrid;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.utils.BeanUtils;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.OrganizatiOnServiceI;
import com.easysoft.member.backend.model.OrganizatiOnEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Title: Controller
 * @Description: 组织机构
 * @author onlineGenerator
 * @date 2014-08-28
 * @since : v1.0.0
 *
 */
@Controller
@RequestMapping("/core/admin/organization")
public class OrganizatiOnController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OrganizatiOnController.class);

	@Autowired
	private OrganizatiOnServiceI organizatiOnService;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 组织机构列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "organizatiOn")
	public ModelAndView organizatiOn() {
		return new ModelAndView("admin/component/oa/organizationList");
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
	public ModelAndView datagrid(OrganizatiOnEntity organizatiOn,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        List entityist= this.organizatiOnService.queryForTree();
        DataGridReturn dataGridReturn = new DataGridReturn(entityist.size(),entityist);
        String json = JsonUtils.beanToJsonArray(entityist);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
	}

    /**
    * 组织机构新增页面跳转
    *
    * @return
    */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(Integer pid, HttpServletRequest req) {
        OrganizatiOnEntity organizatiOnEntity = this.organizatiOnService.queryById(pid);
        req.setAttribute("organizatiOnEntity", organizatiOnEntity);
        return new ModelAndView("admin/component/oa/organization-add");
    }

    /**
     * 组织机构新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "view")
    public ModelAndView view(Integer id, HttpServletRequest req) {
        OrganizatiOnEntity organizatiOn = organizatiOnService.queryById(id);
        req.setAttribute("organizatiOn",organizatiOn);
        req.setAttribute("pid",id);
        return new ModelAndView("admin/component/oa/organization-view");
    }
    /**
    * 添加组织机构
    *
    * @param ids
    * @return
    */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(OrganizatiOnEntity organizatiOn, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "组织机构添加成功";
        try{
            organizatiOnService.save(organizatiOn);

        }catch(Exception e){
            e.printStackTrace();
            message = "组织机构添加失败";
        }
        j.setMsg(message);
        return j;
    }

    /**
    * 组织机构编辑页面跳转
    *
    * @return
    */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(OrganizatiOnEntity organizatiOn, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(organizatiOn.getId())) {
             organizatiOn = organizatiOnService.queryById(organizatiOn.getId());
            req.setAttribute("organizatiOn", organizatiOn);
        }
        return new ModelAndView("admin/component/oa/organizatiOn-add");
    }
    /**
    * 更新组织机构
    *
    * @param ids
    * @return
    */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(OrganizatiOnEntity organizatiOn, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "组织机构更新成功";
        OrganizatiOnEntity t = organizatiOnService.queryById(organizatiOn.getId());
        try {
            BeanUtils.copyBeanNotNull2Bean(organizatiOn, t);
            organizatiOnService.update(t);

        } catch (Exception e) {
            e.printStackTrace();
            message = "组织机构更新失败";

        }
        j.setMsg(message);
        return j;
    }

    /**
    * 删除组织机构
    *
    * @return
    */
    @RequestMapping(params = "delete")
    @ResponseBody
    public AjaxJson doDel(OrganizatiOnEntity organizatiOn, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        message = "组织机构删除成功";
        try{
            organizatiOnService.deleteById(organizatiOn.getId());

        }catch(Exception e){
            e.printStackTrace();
            message = "组织机构删除失败";

        }
        j.setMsg(message);
        return j;
    }

}
