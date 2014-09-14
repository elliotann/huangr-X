package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.manager.IDepartManager;
import com.easysoft.member.backend.manager.IOrganizationManager;
import com.easysoft.member.backend.model.Depart;

import com.easysoft.member.backend.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@RequestMapping("/core/admin/depart")
@Controller
public class DepartAction extends BaseController {
    @Autowired
    private IOrganizationManager organizatiOnService;
    @Autowired
    private IDepartManager departManager;
    @RequestMapping(params = {"toAdd"})
    public ModelAndView toAdd(Integer orgId){
        Map<String,Object> params = new HashMap<String,Object>();
        //机构
        Organization organizatiOnEntity = this.organizatiOnService.queryById(orgId);
        params.put("organization",organizatiOnEntity);
        //所有部门
        List<Depart> departs = departManager.queryByOrgId(orgId);
        params.put("departs",departs);
        return new ModelAndView("admin/core/org/depart-add",params);
    }
    @RequestMapping(params={"addSave"})
    @ResponseBody
    public AjaxJson addSave(Depart depart){
        AjaxJson result = new AjaxJson();
        try{
            departManager.saveDepart(depart);
        }catch(Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
    @RequestMapping(params={"queryDepartsByOrgId"})
    public ModelAndView queryDepartsByOrgId(Integer orgId){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("json", JsonUtils.beanToJsonArray(departManager.queryDeparts4Select(orgId)));
        return new ModelAndView("admin/json_message",params);

    }
}
