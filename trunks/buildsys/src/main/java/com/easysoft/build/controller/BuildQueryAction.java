package com.easysoft.build.controller;

import com.easysoft.build.manager.BuildConfigInfoService;
import com.easysoft.build.model.BuildConfigInfo;
import com.easysoft.build.vo.BuildConfigInfoSearchBean;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Controller
@RequestMapping({"/core/admin/buildQuery"})
public class BuildQueryAction extends BaseController {
    @Autowired
    private BuildConfigInfoService buildConfigInfoService;
    @RequestMapping(params = {"list"})
    public String list(){
        return "admin/core/build/listBuildPackView";
    }
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(Integer rows,Integer page,BuildConfigInfoSearchBean searchBean,String curBranch){
        PageOption pageOption = new PageOption();
        pageOption.setPageSize(rows);
        pageOption.setCurrentPageNo(page);
        buildConfigInfoService.getBuildConfigInfoList(searchBean, pageOption, curBranch);

        DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<AdminUser>)pageOption.getResult());
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
}
