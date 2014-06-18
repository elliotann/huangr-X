package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.common.vo.json.ToolBarReturn;
import com.easysoft.core.context.EsfContext;
import com.easysoft.framework.db.Page;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.manager.IAdminUserManager;
import com.easysoft.member.backend.manager.IPermissionManager;
import com.easysoft.member.backend.manager.IRoleManager;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import com.easysoft.member.backend.model.AdminUser;
import com.easysoft.member.backend.model.FunAndOper;
import com.easysoft.member.backend.model.OperationBtn;
import com.easysoft.member.backend.model.RoleAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 14-1-16
 * Time: 下午1:26
 *
 * @since:
 */
@Controller
@RequestMapping({"/core/admin/user/userAdmin"})
public class UserAdminController extends BaseController{
    @Autowired
    private IAdminUserManager adminUserManager;
    @Autowired
    private IRoleManager roleManager;
    @Autowired
    private IPermissionManager permissionManager;

    @RequestMapping(params = {"list"})
    public ModelAndView list(Integer menuId) throws Exception{
        List<OperationBtn> operationBtns = permissionManager.queryBtnByUsernameAndMenuId(UserServiceFactory.getUserService().getCurrentUser().getUserid(), RoleAuth.AuthType.FUNCTION.name(),menuId);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("operationBtns",operationBtns);
        return new ModelAndView("core/admin/user/useradmin",map);
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(Page page){

        List userList= this.adminUserManager.list();
        DataGridReturn dataGridReturn = new DataGridReturn(userList.size(),userList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"add"})
    public ModelAndView add() throws Exception{
        int multiSite = EsfContext.getContext().getCurrentSite().getMulti_site();
        List roleList = roleManager.list();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("roleList",roleList);
        map.put("multiSite",multiSite);
        return new ModelAndView("core/admin/user/addUserAdmin",map);
    }

    @RequestMapping(params = {"edit"})
    public ModelAndView edit(Integer id) throws Exception {
        int multiSite = EsfContext.getContext().getCurrentSite().getMulti_site();
        List roleList = roleManager.list();
        List userRoles =permissionManager.getUserRoles(id);
        AdminUser adminUser = this.adminUserManager.get(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("roleList",roleList);
        map.put("multiSite",multiSite);
        map.put("userRoles",userRoles);
        map.put("adminUser",adminUser);
        return new ModelAndView("core/admin/user/editUserAdmin",map);
    }
    @RequestMapping(params = {"addSave"})
    @ResponseBody
    public AjaxJson  addSave(AdminUser adminUser,int[] roleids,HttpServletResponse response) throws Exception {
        AjaxJson json = new AjaxJson();
        try{

            adminUser.setRoleids(roleids);
            adminUserManager.add(adminUser);
            json.setMsg("新增管理员成功");

        } catch (RuntimeException e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    @RequestMapping(params = {"chkUserExist"})
    @ResponseBody
    public AjaxJson  chkUserExist(AdminUser adminUser,int[] roleids,HttpServletResponse response) throws Exception {
        AjaxJson json = new AjaxJson();
        try{

           System.out.println("heere");
            json.setMsg("新增管理员成功");

        } catch (RuntimeException e) {
            json.setMsg(e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    @RequestMapping(params = {"delete"})
    @ResponseBody
    public AjaxJson delete(Integer id) throws Exception {
        AjaxJson json = new AjaxJson();
        try {
            this.adminUserManager.delete(id);
            json.setMsg("管理员删除成功");
        } catch (RuntimeException e) {
            json.setMsg("管理员删除失败:" + e.getMessage());
            json.setSuccess(false);
        }

        return json;
    }

    @RequestMapping(params={"editSave"})
    @ResponseBody
    public AjaxJson editSave(String updatePwd, AdminUser adminUser,String newPassword,int[] roleids) throws Exception {
        AjaxJson json = new AjaxJson();
        try {
            if(updatePwd!=null){
                adminUser.setPassword(newPassword);
            }
            adminUser.setRoleids(roleids);
            this.adminUserManager.edit(adminUser);
            json.setMsg("修改管理员成功");

        } catch (RuntimeException e) {
            e.printStackTrace();
            this.logger.error(e,e.fillInStackTrace());
            json.setMsg("管理员修改失败:"+e.getMessage());
            json.setSuccess(false);
        }

        return json;
    }

    public String editPassword() throws Exception {
        return "editPassword";
    }
    @RequestMapping(params = {"checkNameExist"})
    @ResponseBody
    public boolean checkNameExist(String username,Integer userid){
        AdminUser adminUser = adminUserManager.getAdminUserByName(username,userid);
        if(adminUser!=null){
            return false;
        }

        return true;
    }
}
