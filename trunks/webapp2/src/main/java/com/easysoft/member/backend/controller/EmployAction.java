package com.easysoft.member.backend.controller;


import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.manager.ICompanyManager;
import com.easysoft.member.backend.manager.IDepartManager;
import com.easysoft.member.backend.manager.IEmployManager;
import com.easysoft.member.backend.manager.IOrganizationManager;
import com.easysoft.member.backend.model.Company;
import com.easysoft.member.backend.model.Depart;
import com.easysoft.member.backend.model.Employ;
import com.easysoft.member.backend.model.Organization;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/core/admin/emp")
public class EmployAction extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(EmployAction.class);
    @Autowired
    private IEmployManager employManager;


    /**
     * 组织机构列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list() {
        return new ModelAndView("admin/core/emp/empList");
    }

    /**
     * AJAX请求数据
     *
     */

    @RequestMapping(params = "dataGrid")
    public ModelAndView datagrid(Integer rows,Integer page) {
        PageOption pageOption = new PageOption();
        pageOption.setPageSize(rows);
        pageOption.setCurrentPageNo(page);
        employManager.queryForPage(pageOption);
        DataGridReturn dataGrid = new DataGridReturn(pageOption.getTotalCount(),(List<Employ>)pageOption.getResult());
        String json = JsonUtils.beanToJson(dataGrid);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = "goAdd")
    public String goAdd(){
        return "admin/core/emp/emp-add";
    }
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(Integer id){
        Employ employ = employManager.queryById(id);
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("employ",employ);
        return new ModelAndView("admin/core/emp/emp-update",params);
    }

    @RequestMapping(params = "addSave")
    @ResponseBody
    public AjaxJson addSave(Employ employ){
        AjaxJson result = new AjaxJson();
        try{
            employManager.saveEmploy(employ);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;

    }
    @RequestMapping(params = "addUpdate")
    @ResponseBody
    public AjaxJson addUpdate(Employ employ){
        AjaxJson result = new AjaxJson();
        try{
            employManager.updateEmploy(employ);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;

    }
    @RequestMapping(params = "batchDel")
    @ResponseBody
    public AjaxJson batchDel(Integer[] ids){
        AjaxJson result = new AjaxJson();
        try{
            employManager.batchDel(ids);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;

    }
    @RequestMapping(params = "checkEmpNoExist")
    @ResponseBody
    public boolean checkEmpNoExist(String empNo,Integer id){
        return employManager.queryByNoAndId(empNo,id)==null;
    }
}
