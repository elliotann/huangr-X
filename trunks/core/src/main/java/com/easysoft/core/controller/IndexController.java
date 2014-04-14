package com.easysoft.core.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.manager.IIndexItemManager;
import com.easysoft.core.model.IndexItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 14-4-14
 * Time: 下午12:49
 *
 * @since:
 */
@Controller
@RequestMapping({"/core/admin/index"})
public class IndexController extends BaseController{
    @Autowired
    private IIndexItemManager indexItemManager;
    private List<IndexItem> itemList;
    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        itemList  =  indexItemManager.list();
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("itemList",itemList);
        return new ModelAndView("core/admin/index",params);
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
