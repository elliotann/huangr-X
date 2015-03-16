package com.easysoft.component.weixin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.component.weixin.manager.IWebChatConfigManager;
import com.easysoft.component.weixin.model.WebChatConfig;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.AdminUser;
@Controller
@RequestMapping({"/core/admin/webchat/config"})
public class WebChatConfigAction extends BaseController {
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
        return new ModelAndView("component/webchat/config/webchatconfiglist");
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
        pageOption.setData(webChatConfigManager.getAll());
        DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<AdminUser>)pageOption.getResult());
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"add"})
    public ModelAndView add() throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        return new ModelAndView("component/webchat/config/addWebChatConfig",map);
    }
    @RequestMapping(params = {"addSave"})
    @ResponseBody
    public AjaxJson  addSave(WebChatConfig webChatConfig) throws Exception {
        AjaxJson json = new AjaxJson();
        try{
        	webChatConfigManager.add(webChatConfig);
            json.setMsg("新增管理员成功");

        } catch (RuntimeException e) {
            e.printStackTrace();
            json.setMsg("新增用户失败!"+e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
}
