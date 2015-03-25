package com.easysoft.action.component.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.component.weixin.manager.IWebChatConfigManager;
import com.easysoft.component.weixin.manager.IWebChatMenuManager;
import com.easysoft.component.weixin.model.WebChatConfig;
import com.easysoft.component.weixin.model.WebChatMenu;
import com.easysoft.component.weixin.utils.WeixinUtil;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.context.EsfContext;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.AdminUser;
import com.easysoft.member.backend.model.Depart;
import com.easysoft.member.backend.model.UserRole;
@Controller
@RequestMapping({"/core/admin/webchat/menu"})
public class WebChatMenuAction extends BaseController {
	@Autowired
	private IWebChatMenuManager webChatMenuManager;
	@Autowired
	private IWebChatConfigManager webChatConfigManager;
	 /**
     * 查询列表界面
     * @param menuId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = {"list"})
    public ModelAndView list() throws Exception{
        return new ModelAndView("component/webchat/menu/webchatmenulist");
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(Integer rows,Integer page,String username,String stype,String keyword){
        PageOption pageOption = new PageOption();

        //pageOption.setCurrentPageNo(page);
        if("0".equals(stype)){
        	username = keyword;
        }
        if(StringUtils.isEmpty(username)){
            username=null;
        }
        pageOption.setData(webChatMenuManager.getAll());
        DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<WebChatMenu>)pageOption.getResult());
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"menuDataGrid"})
    public ModelAndView menuDataGrid(Integer rows,Integer page,String username,String stype,String keyword){
        Map<String,Object> map = new HashMap<String, Object>();
        String json = JsonUtils.beanToJsonArray(webChatMenuManager.getMenuTree(null));
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"add"})
    public ModelAndView add() throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        return new ModelAndView("component/webchat/menu/addWebChatMenu",map);
    }
    @RequestMapping(params = {"addSave"})
    @ResponseBody
    public AjaxJson  addSave(WebChatMenu webChatMenu) throws Exception {
        AjaxJson json = new AjaxJson();
        try{
        	webChatMenuManager.add(webChatMenu);
            json.setMsg("新增菜单成功");

        } catch (RuntimeException e) {
            e.printStackTrace();
            json.setMsg("新增菜单失败!"+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    
    @RequestMapping(params = {"toSync"})
    public ModelAndView toSync(String menuIds) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("menuIds", menuIds);
        return new ModelAndView("component/webchat/menu/syncconfirm",map);
    }
    
    @RequestMapping(params = {"doSync"})
    @ResponseBody
    public AjaxJson doSync(Integer accountId,String menuIds) throws Exception{
    	 AjaxJson json = new AjaxJson();
    	String jsonStr = webChatMenuManager.syncMenu(menuIds.split(","));
    	WebChatConfig webChatConfig = webChatConfigManager.get(accountId);
    	JSONObject jsonObject = WeixinUtil.createMenu(webChatConfig.getAppId(), webChatConfig.getAppsecret(), jsonStr);
    	if(jsonObject!=null){
    		if(jsonObject.getInt("errcode")==0){
    			json.setMsg("同步成功");
    		}else{
    			json.setMsg("同步失败,"+jsonObject.getString("errmsg"));
    			json.setSuccess(false);
    		}
    	}else{
    		json.setSuccess(false);
    		json.setMsg("请求失败!");
    	}
        Map<String,Object> map = new HashMap<String, Object>();
        return json;
    }
    
    @RequestMapping(params = {"edit"})
    public ModelAndView edit(Integer id) throws Exception {
        
    	WebChatMenu webChatMenu = this.webChatMenuManager.get(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("webChatMenu",webChatMenu);
        return new ModelAndView("component/webchat/menu/editWebChatMenu",map);
    }
    @RequestMapping(params={"editSave"})
    @ResponseBody
    public AjaxJson editSave(WebChatMenu webChatMenu) throws Exception {
        AjaxJson json = new AjaxJson();
        try {
        	if(webChatMenu.getParent().getId()==null){
        		webChatMenu.setParent(null);
        	}
        	this.webChatMenuManager.edit(webChatMenu);
            json.setMsg("修改菜单成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            this.logger.error(e,e.fillInStackTrace());
            json.setMsg("修改菜单失败:"+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
}
