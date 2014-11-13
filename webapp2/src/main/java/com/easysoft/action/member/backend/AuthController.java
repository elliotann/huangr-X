package com.easysoft.action.member.backend;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.dao.IOperationBtnDao;
import com.easysoft.member.backend.manager.IAuthActionManager;
import com.easysoft.member.backend.manager.IFunAndOperManager;
import com.easysoft.member.backend.manager.IOperationBtnManager;
import com.easysoft.member.backend.manager.IPermissionManager;
import com.easysoft.member.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-2-23
 * Time: 上午8:16
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping({"/core/admin/auth"})
public class AuthController extends BaseController {
    @Autowired
    private IAuthActionManager authActionManager;
    @Autowired
    private IFunAndOperManager funAndOperManager;
    @Autowired
    private IMenuManager menuManager;
    @Autowired
    private IPermissionManager permissionManager;
    @Autowired
    private IOperationBtnDao operationBtnDao;
    @RequestMapping(params = {"add"})
    public ModelAndView add(int roleId){
        Map<String,Object> map = new HashMap<String,Object>();

        List<OperationBtn> operationBtns = operationBtnDao.queryForList();
        map.put("operationBtns",operationBtns);
        List<FunAndOper> funAndOpers = funAndOperManager.queryFunAndOpersByRoleId(roleId);

        if(funAndOpers!=null&&!funAndOpers.isEmpty()){
            map.put("isEdit",1);
            //map.put("actid",authActions.get(0).getActid());
            map.put("funAndOpers",funAndOpers);
        }else{
            map.put("isEdit",0);
        }

        map.put("roleId",roleId);
        return new ModelAndView("admin/core/auth/auth_input",map);
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(int roleId){
        List<Menu> menuList  = menuManager.getMenuTree(0,roleId);
        DataGridReturn dataGridReturn = new DataGridReturn(menuList.size(),menuList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"save"})
    @ResponseBody
    public AjaxJson save(@RequestParam(value = "menuids[]")Integer[] menuids,int roleId,int edit,int actid){
        AjaxJson result = new AjaxJson();

        if(edit==0){
            return this.saveAdd("权限点",menuids,roleId);
        }else {
            return this.saveEdit("权限点",menuids,actid);
        }

    }
    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(String name,Integer[] menuids,int roleId){
        AjaxJson result = new AjaxJson();
        try{
            AuthAction act = new AuthAction();

            act.setName(name);
            act.setType("menu");
            act.setObjvalue(StringUtil.arrayToString(menuids, ","));
            int authid = this.authActionManager.add(act,roleId);
            result.addAttribute("authid",authid);

        }catch(RuntimeException e){
            this.logger.error(e.getMessage(), e.fillInStackTrace());
            result.setSuccess(false);
            result.setMsg(e.getMessage());

        }
        return result;
    }
    @RequestMapping(params = {"saveEdit"})
    @ResponseBody
    public AjaxJson saveEdit(String name,Integer[] menuids,int authid){
        AjaxJson result = new AjaxJson();
        try{
            AuthAction act = new AuthAction();
            act.setName(name);
            act.setType("menu");
            act.setActid(authid);
            act.setObjvalue(StringUtil.arrayToString(menuids, ","));
            this.authActionManager.edit(act);
            result.addAttribute("authid",authid);

        }catch(RuntimeException e){
            this.logger.error(e.getMessage(), e.fillInStackTrace());
            result.setMsg(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }
    @RequestMapping(params = {"delete"})
    @ResponseBody
    public AjaxJson delete(int authid){
        AjaxJson result = new AjaxJson();
        try{
            this.authActionManager.delete(authid);
            result.addAttribute("authid",authid);

        }catch(RuntimeException e){
            this.logger.error(e.getMessage(), e.fillInStackTrace());
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
    @RequestMapping(params = {"getBtnByMenuId"})
    @ResponseBody
    public String getBtnByMenuId(Integer id,Integer roleId){

        if(id==null||id==0){
            return "";
        }
        AjaxJson result = new AjaxJson();
        List<OperationBtn> operationBtns = permissionManager.getOperationBtnsByMenuId(id);
        String json = "";
        if(operationBtns==null){
            return json;
        }
        for(OperationBtn btn : operationBtns){
            boolean isChecked = permissionManager.hasOperationByRoleAndMenu(roleId,id,btn.getId()+"");
            if(isChecked){
                json += "&nbsp;&nbsp;<input type='checkbox' name='btn"+id+"' id='"+btn.getCode()+"_"+id+"' onclick='checkOperation(this)' value='"+btn.getId()+"' checked/>"+btn.getName();
            }else{
                json += "&nbsp;&nbsp;<input type='checkbox' name='btn"+id+"' id='"+btn.getCode()+"_"+id+"' onclick='checkOperation(this)' value='"+btn.getId()+"'/>"+btn.getName();
            }

        }


        result.setObj(json);
        return json;
    }
    @RequestMapping(params = {"saveAuth"})
    @ResponseBody
    public AjaxJson saveAuth(Integer menuId,Integer roleId,Integer operId,boolean isCheck,@RequestParam(value = "menuIds")String menuIds){
        AjaxJson result = new AjaxJson();

        Object[] objects = JsonUtils.getDTOArray(menuIds, RoleAuth.class);
        RoleAuth[] roleAuths=new RoleAuth[objects.length];
        for(int i=0;i<objects.length;i++){
            roleAuths[i] = (RoleAuth)objects[i];
        }
       authActionManager.saveAuth(roleAuths);
        return result;

    }
}
