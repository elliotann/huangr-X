package com.easysoft.action.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.manager.IIndexItemManager;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.core.model.IndexItem;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.context.webcontext.WebSessionContext;
import com.easysoft.member.backend.manager.UserContext;
import com.easysoft.member.backend.model.Menu;

/**
 * User: andy
 * Date: 14-4-14
 * Time: 下午12:49
 *
 * @since:
 */
@Controller
@RequestMapping({"/admin/index"})
public class IndexController extends BaseController{
    @Autowired
    private IIndexItemManager indexItemManager;
    @Autowired
    private IMenuManager menuManager;
    
    private List<IndexItem> itemList;
    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        itemList  =  indexItemManager.list();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("itemList",itemList);
        return new ModelAndView("admin/core/index",params);
    }
    @RequestMapping(params = {"main"})
    public ModelAndView main(){
    	List<Menu> menuList = menuManager.getMenuTree(0);
    	Map<String,Object> params = new HashMap<String, Object>();
        params.put("menuList",menuList);
    	return new ModelAndView("adminthemes/default/main",params);
    }
   
    public IIndexItemManager getIndexItemManager() {
        return indexItemManager;
    }
    public void setIndexItemManager(IIndexItemManager indexItemManager) {
        this.indexItemManager = indexItemManager;
    }
    public List<IndexItem> getItemList() {
        return itemList;
    }
    public void setItemList(List<IndexItem> itemList) {
        this.itemList = itemList;
    }

}
