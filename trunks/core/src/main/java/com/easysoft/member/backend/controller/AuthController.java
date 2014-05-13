package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.IAuthActionManager;
import com.easysoft.member.backend.manager.IOperationBtnManager;
import com.easysoft.member.backend.manager.IPermissionManager;
import com.easysoft.member.backend.manager.IRoleAuthManager;
import com.easysoft.member.backend.model.*;
import com.easysoft.member.backend.vo.AuthOperationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    private IRoleAuthManager roleAuthManager;
    @Autowired
    private IMenuManager menuManager;
    @Autowired
    private IPermissionManager permissionManager;
    @Autowired
    private IOperationBtnManager operationBtnManager;
    @RequestMapping(params = {"add"})
    public ModelAndView add(int roleId){
        Map<String,Object> map = new HashMap<String,Object>();
        List<AuthAction> authActions = authActionManager.getAuthActionByRoleId(roleId);
        List<OperationBtn> operationBtns = operationBtnManager.queryForAll(OperationBtn.class);
        map.put("operationBtns",operationBtns);
        List<RoleAuth> roleAuths = roleAuthManager.findByProperty(RoleAuth.class,"role.id",roleId);
        if(!authActions.isEmpty()){
            map.put("isEdit",1);
            map.put("actid",authActions.get(0).getActid());
        }else{
            map.put("isEdit",0);
        }

        map.put("roleId",roleId);
        return new ModelAndView("core/admin/auth/auth_input",map);
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
        List<Menu> menuList  = menuManager.getMenuTree(0);
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
            com.easysoft.member.backend.model.AuthAction act = new com.easysoft.member.backend.model.AuthAction();

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
            com.easysoft.member.backend.model.AuthAction act = new com.easysoft.member.backend.model.AuthAction();
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
    @RequestMapping(params = {"getMenuTreeById"})
    @ResponseBody
    public String getMenuTreeById(Integer id){
        id=2;
        if(id==null||id==0){
            return "";
        }
        AjaxJson result = new AjaxJson();
        List<Menu> menus = menuManager.getMenuTree(id);
        DataGridReturn dataGridReturn = new DataGridReturn(menus.size(),menus);
        String json = JsonUtils.beanToJson(dataGridReturn);
        json = "{ rows: [{ \"id\": '01',\"children\": [{ \"id\": '0101', \"amount\":400 },{ \"id\": '0102',  \"children\":[{ \"id\": '010201', \"amount\": 200 },{ \"id\": '010202', \"amount\": 100 }]},{ \"id\": '0103', \"amount\": 100 }]},{ \"id\": '02', \"amount\": 100 },{ \"id\": '03', \"amount:\" 100 }],\"total\":0}";
        result.setObj(json);
        return json;
    }
    @RequestMapping(params = {"saveAuth"})
    @ResponseBody
    public AjaxJson saveAuth(String postdata,Integer roleId){
        AjaxJson result = new AjaxJson();
        AuthOperationVo authOperationVo = (AuthOperationVo)JsonUtils.jsonToBean(postdata, AuthOperationVo.class,null);
        String menu = authOperationVo.getMenu();

        String[] menusStr=menu.split(",");
        List<Integer> menuids = new ArrayList<Integer>();

        for(int i=0;i<menusStr.length;i++ ){
            String menuStr = menusStr[i];
            menuids.add(Integer.parseInt(menuStr.substring(1)));
        }

        String btn = authOperationVo.getBtn();

        String[] btnsStr = btn.split(",");
        List<FunAndOper> funAndOpers = new ArrayList<FunAndOper>();


        for(Integer menuId : menuids){
            FunAndOper funAndOper = new FunAndOper();
            Menu menuTemp = new Menu();
            menuTemp.setId(menuId);
            funAndOper.setMenu(menuTemp);
            String opers = "";

            for(int i=0;i<btnsStr.length;i++){
                String btnStr = btnsStr[i];
                if(menuId==Integer.parseInt(btnStr.substring(0,btnStr.indexOf("|")))){
                    opers += btnStr.substring(btnStr.indexOf("|")+1)+",";
                }

            }
            funAndOper.setOperation(opers);
            funAndOpers.add(funAndOper);

        }

        authActionManager.batAddRoleAuth(roleId,funAndOpers);
        return result;

    }
}
