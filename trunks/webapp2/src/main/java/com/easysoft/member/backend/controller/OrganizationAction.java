package com.easysoft.member.backend.controller;


import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.dao.hibernate.DataGrid;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.utils.BeanUtils;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.ICompanyManager;
import com.easysoft.member.backend.manager.IDepartManager;
import com.easysoft.member.backend.manager.IOrganizationManager;

import com.easysoft.member.backend.model.AdminUser;
import com.easysoft.member.backend.model.Company;
import com.easysoft.member.backend.model.Depart;
import com.easysoft.member.backend.model.Organization;
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
public class OrganizationAction extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(OrganizationAction.class);

    @Autowired
    private IOrganizationManager organizatiOnService;

    @Autowired
    private IDepartManager departManager;
    @Autowired
    private ICompanyManager companyManager;

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
        return new ModelAndView("admin/core/org/organizationList");
    }

    /**
     * AJAX请求数据
     *
     */

    @RequestMapping(params = "dataGrid")
    public ModelAndView datagrid() {
        List entityist= this.organizatiOnService.queryOrganizationByTree(0);
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
    @RequestMapping(params = "view")
    public ModelAndView view(Integer id, String orgType,HttpServletRequest req) {
        Organization organizatiOn = organizatiOnService.queryByTypeAndId(orgType, id);

        req.setAttribute("pid",id);
        if(orgType.equals(Organization.OrgType.COMPANY.name())){
            Company company = (Company)organizatiOn;
            req.setAttribute("organization",company);
            return new ModelAndView("admin/core/org/organization-view");
        }else if(orgType.equals(Organization.OrgType.DEPT.name())){
            Depart depart = (Depart)organizatiOn;
            req.setAttribute("organization",depart);
            return new ModelAndView("admin/core/org/dept-view");
        }
        return null;
    }
    /**
     * 添加组织机构
     *
     * @param ids
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(Organization organization, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        message = "组织机构添加成功";
        try{
            organizatiOnService.save(organization);

        }catch(Exception e){
            e.printStackTrace();
            message = "组织机构添加失败";
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params={"addDept"})
    @ResponseBody
    public AjaxJson addDept(Depart depart){
        AjaxJson result = new AjaxJson();
        try{
            departManager.saveDepart(depart);
        }catch(Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping(params={"addCompany"})
    @ResponseBody
    public AjaxJson addCompany(Company company){
        AjaxJson result = new AjaxJson();
        try{
            companyManager.saveCompany(company);
        }catch(Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
    @RequestMapping(params={"updateCompany"})
    @ResponseBody
    public AjaxJson updateCompany(Company company){
        AjaxJson result = new AjaxJson();
        try{
            companyManager.updateCompany(company);
        }catch(Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 组织机构编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(Integer pid,String orgType, HttpServletRequest req) {
        if (pid!=null) {
            Organization organization = this.organizatiOnService.queryByTypeAndId(orgType,pid);
            if(orgType.equals(Organization.OrgType.COMPANY.name())){
                Company company = (Company)organization;
                Organization parent = this.organizatiOnService.queryByTypeAndId(orgType,company.getPid());
                req.setAttribute("organization", company);
                req.setAttribute("parent", parent);
                return new ModelAndView("admin/core/org/organization-update");
            }else if(orgType.equals(Organization.OrgType.DEPT.name())){

                req.setAttribute("organization", (Depart)organization);
                return new ModelAndView("admin/core/org/dept-add");
            }
        }
        return null;
    }


    /**
     * 删除组织机构
     *
     * @return
     */
    @RequestMapping(params = "delete")
    @ResponseBody
    public AjaxJson doDel(Organization organizatiOn, HttpServletRequest request) {
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

    /**
     * 组织机构新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(Integer pid,String orgType, HttpServletRequest req) {
        Organization organization = this.organizatiOnService.queryByTypeAndId(orgType, pid.intValue());
        if(orgType.equals(Organization.OrgType.COMPANY.name())){
            Company company = (Company)organization;
            req.setAttribute("organization", company);
            return new ModelAndView("admin/core/org/organization-add");
        }else if(orgType.equals(Organization.OrgType.DEPT.name())){
            Depart dept = (Depart)organization;
            Company company = companyManager.queryById(dept.getCompId());
            req.setAttribute("organization", dept);
            req.setAttribute("company", company);
            return new ModelAndView("admin/core/org/dept-add");
        }
        return null;
    }

    @RequestMapping(params = {"checkCompNoExist"})
    @ResponseBody
    public boolean checkCompNoExist(String compNo,Integer id){
        Company company = companyManager.queryByNoAndId(compNo,id);
        return company==null;

    }

    @RequestMapping(params = {"checkDeptNoExist"})
    @ResponseBody
    public boolean checkDeptNoExist(String deptNo,Integer id){
        Depart depart = departManager.queryByNoAndId(deptNo,id);
        return depart==null;

    }

}
