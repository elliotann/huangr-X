package com.easysoft.action.component.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.component.weixin.manager.IWebChatMenuManager;
import com.easysoft.component.weixin.model.WebChatMenu;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.AdminUser;
@Controller
@RequestMapping({"/core/admin/webchat/menu"})
public class WebChatMenuAction extends BaseController {
	@Autowired
	private IWebChatMenuManager webChatMenuManager;
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
}
