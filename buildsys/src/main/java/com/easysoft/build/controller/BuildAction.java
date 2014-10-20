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

import com.easysoft.build.command.BuildCommand;
import com.easysoft.build.command.BuildCommandManager;
import com.easysoft.build.command.CommandContext;
import com.easysoft.build.manager.BuildDetailiDataService;
import com.easysoft.build.manager.BuildReposManager;
import com.easysoft.build.model.BuildConfig;
import com.easysoft.build.model.ChangedSVNFiles;
import com.easysoft.build.model.RepositoryInfo;
import com.easysoft.build.utils.SVNUtil;
import com.easysoft.build.web.BuildQueue;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tmatesoft.svn.core.SVNException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : andy.huang
 * @since :
 */
@Controller
@RequestMapping({"/core/admin/build"})
public class BuildAction extends BaseController {
    @RequestMapping(params = {"add"})
    public ModelAndView toAddBuild(){
        RepositoryInfo repositoryInfo = BuildReposManager.getByName("jeap1.0");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("repos",repositoryInfo);
        return new ModelAndView("admin/core/build/addbuild",params);
    }

    @RequestMapping(params = {"toConfirm"})
    public ModelAndView toConfirm(String keyword,String projects,HttpServletRequest req){

        Map<String,Object> params = new HashMap<String,Object>();
        try {
            ChangedSVNFiles changedFiles = SVNUtil.listChangedFiles("jeap1.0",
                    projects.split(";"), keyword.split(";"));
            params.put("changedFiles",changedFiles);
        } catch (SVNException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("keyword",keyword);
        return new ModelAndView("admin/core/build/confirmbuild",params);
    }
    @RequestMapping(params = {"doBuild"})
    public ModelAndView doBuild(String build_files,String comment,HttpServletRequest req){

        Map<String,Object> params = new HashMap<String,Object>();
        if (comment != null) {
            comment = comment.trim().replaceAll(">", "&gt;");
            comment = comment.replaceAll("<", "&lt;");
        }
        BuildConfig config = new BuildConfig();
        config.setVersion("jeap1.0");
        config.setDevelopers("360042212@qq.com");
        config.setCustomer("all");
        config.setComment(comment);
        HttpSession session = req.getSession();
        String vps = (String)session.getAttribute("keyword");
        config.setVps(vps);
        config.setId(vps.split(";")[0]);
        if(build_files.endsWith(",")){
            build_files = build_files.substring(0,build_files.length()-1);
        }
        String[] files = build_files.split(";");
        for (String file : files) {
            if (!config.addFile(file)) {
                System.err.println("文件路径不合法:" + file);
            }
        }

        try {
            BuildQueue.addBuild(config);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return new ModelAndView("admin/core/build/showbuildlog",params);
    }

    @RequestMapping(params = {"doCommand"})
    @ResponseBody
    public AjaxJson doCommand(HttpServletRequest req){
        AjaxJson result = new AjaxJson();
        CommandContext context = new CommandContext();
        context.init(req);
        BuildCommand command = BuildCommandManager.getCommand(context);
        HttpSession session = req.getSession();
        try {
            command.execute();
            session.setAttribute("message", "执行成功\\n" + command.getName() + context.getFileName());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg("执行失败\\n" + e.getMessage());
            e.printStackTrace();
            session.setAttribute("message", "执行失败\\n" + e.getMessage());
        }

        return result;
    }
}
