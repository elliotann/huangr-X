/*
 *
 *  * Copyright 1999-2011 jeap Group Holding Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.easysoft.core.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.log.query.IBnLogQuery;
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
@RequestMapping({"/core/admin/buslog"})
public class BusinessLogAction extends BaseController{
    @Autowired
    private IBnLogQuery bnLogQuery;
    @RequestMapping(params = {"list"})
    public String list(){
        return "admin/core/log/loglist";
    }
    
    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(Integer rows,Integer page,String operator){
        PageOption pageOption = new PageOption();
        pageOption.setPageSize(rows);
        pageOption.setCurrentPageNo(page);
        if(StringUtils.isNotEmpty(operator)){
            pageOption.addSearch("operator",operator);
        }
        bnLogQuery.queryForPage(pageOption);

        DataGridReturn dataGridReturn = new DataGridReturn(pageOption.getTotalCount(),(List<AdminUser>)pageOption.getResult());
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
}
