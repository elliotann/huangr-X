package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.core.utils.http.FileUploader;
import com.easysoft.core.utils.http.MultipartFileValidator;
import com.easysoft.framework.utils.HttpUtil;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.Menu;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping({"/core/admin/menu"})
public class MenuController extends BaseController {
    private static final Log logger = LogFactory.getLog(MenuController.class);
    // 总上传文件大小限制
    private static final long MAX_SIZE            = 1024 * 1024 * 100;
    // 单个传文件大小限制
    private static final long MAX_FILE_SIZE        = 1024 * 1024 * 10;
    // 可接受的文件类型
    private static final String[] ACCEPT_TYPES    = {"txt", "pdf", "doc", ".Jpg", "*.zip", "*.RAR"};
    @Autowired
    private IMenuManager menuManager;
    private MultipartFileValidator fileValidator;

    private static final String UPDATE_PATH = "upload";
    @PostConstruct
    public void init(){
        fileValidator = new MultipartFileValidator();
    }

    @RequestMapping(params = {"list"})
    public ModelAndView list(){
        return new ModelAndView("core/admin/menu/list");
    }

    @RequestMapping(params = {"dataGrid"})
    public ModelAndView dataGrid(){
        List<Menu> menuList  = this.menuManager.getMenuList();
        DataGridReturn dataGridReturn = new DataGridReturn(menuList.size(),menuList);
        String json = JsonUtils.beanToJson(dataGridReturn);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"addOrUpdateGrid"})
    public ModelAndView addOrUpdateGrid(){
        List<Menu> menuList  = this.menuManager.getMenuList();

        String json = JsonUtils.beanToJsonArray(menuList);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("json",json);
        return new ModelAndView("admin/json_message",map);
    }
    @RequestMapping(params = {"add"})
    public ModelAndView add(){
        List<Menu> menuList  = this.menuManager.getMenuList();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("menuList",menuList);
        return new ModelAndView("core/admin/menu/addMenu",map);
    }





    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(Menu menu,@RequestParam(value = "iocFile",required = false) MultipartFile icoFile,HttpServletRequest req){
        AjaxJson json = new AjaxJson();
        try{
            if(icoFile!=null&&StringUtils.isNotEmpty(icoFile.getOriginalFilename())){
                menu.setIco(updateFile(icoFile,req));
            }
            this.menuManager.add(menu);
            json.setMsg("新增菜单成功");

        }catch(RuntimeException e){
            logger.error("新增菜单出错！",e);
            json.setMsg(e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    @RequestMapping(params = {"edit"})
    public ModelAndView edit(int id){
        List<Menu> menuList  = this.menuManager.getMenuList();
        Menu menu = this.menuManager.get(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("menuList",menuList);
        map.put("menu",menu);
        return new ModelAndView("core/admin/menu/editMenu",map);
    }
    @RequestMapping(params = {"saveEdit"})
    @ResponseBody
    public AjaxJson saveEdit(Menu menu,Integer menuId,@RequestParam(value = "iocFile",required = false) MultipartFile icoFile,HttpServletRequest request){
        menu.setId(menuId);
        AjaxJson json = new AjaxJson();
        try{
            if(icoFile!=null&&StringUtils.isNotEmpty(icoFile.getOriginalFilename())){
                menu.setIco(updateFile(icoFile,request));
            }
            this.menuManager.edit(menu);
            json.setMsg("修改菜单成功");
        }catch(RuntimeException e){
            this.logger.error(e.getMessage(), e);
            json.setMsg(e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }
    private String updateFile(MultipartFile file,HttpServletRequest req){
        String path="";
        try {
            fileValidator.validate(file);
            FileUploader uploader = new FileUploader(UPDATE_PATH,ACCEPT_TYPES,MAX_SIZE,MAX_FILE_SIZE);
            path =uploader.upload(req,file);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return path;
    }
    @RequestMapping(params = {"delete"})
    @ResponseBody
    public AjaxJson delete(Integer id){
        AjaxJson json = new AjaxJson();
        try{
            this.menuManager.delete(id);
            json.setMsg("删除菜单成功");
        }catch(RuntimeException e){
            this.logger.error(e.getMessage(), e);
            json.setMsg(e.getMessage());
            json.setSuccess(false);
        }
        return json;
    }


}