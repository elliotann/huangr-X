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

package com.easysoft.build.controller;

/*import com.easysoft.build.manager.BuildDetailiDataService;
import com.easysoft.build.manager.BuildReposManager;
import com.easysoft.build.model.RepositoryInfo;*/
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Controller
@RequestMapping({"/core/admin/build"})
public class BuildAction extends BaseController {
  /*  @RequestMapping(params = {"add"})
    public ModelAndView toAddBuild(){
        RepositoryInfo repositoryInfo = BuildReposManager.getByName("jeap1.0");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("repos",repositoryInfo);
        return new ModelAndView("admin/core/build/addbuild",params);
    }
    
    @RequestMapping(params = {"toConfirm"})
    public ModelAndView toConfirm(){

        Map<String,Object> params = new HashMap<String,Object>();

        return new ModelAndView("admin/core/build/confirmbuild",params);
    }*/

}
