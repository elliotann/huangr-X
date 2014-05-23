package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.common.vo.json.DataGridReturn;
import com.easysoft.core.manager.IMenuManager;
import com.easysoft.core.utils.http.FileUploader;
import com.easysoft.framework.utils.JsonUtils;
import com.easysoft.member.backend.model.Menu;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping({"/core/admin/menu"})
public class MenuController extends BaseController {
    @Autowired
    private IMenuManager menuManager;
    // 可接受的文件类型
    private static final String[] ACCEPT_TYPES    = {"txt", "pdf", "doc", ".Jpg", "*.zip", "*.RAR"};
    // 总上传文件大小限制
    private static final long MAX_SIZE            = 1024 * 1024 * 100;
    // 单个传文件大小限制
    private static final long MAX_FILE_SIZE        = 1024 * 1024 * 10;
    @RequestMapping(params = {"listframe"})
    public ModelAndView listframe(){

        return new ModelAndView("core/admin/menu/list_frame");
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
        return new ModelAndView("core/admin/menu/add",map);
    }





    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(Menu menu,@RequestParam MultipartFile icoFile){
        AjaxJson json = new AjaxJson();
        try{
            if(StringUtils.isNotEmpty(icoFile.getOriginalFilename())){
                menu.setIco(updateFile(icoFile));
            }
            this.menuManager.add(menu);
            json.setMsg("新增菜单成功");

        }catch(RuntimeException e){
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
        return new ModelAndView("core/admin/menu/edit",map);
    }
    @RequestMapping(params = {"saveEdit"})
    @ResponseBody
    public AjaxJson saveEdit(Menu menu,Integer menuId,@RequestParam MultipartFile icoFile,HttpServletRequest request,HttpServletResponse response){
        menu.setId(menuId);
        AjaxJson json = new AjaxJson();
        try{
            if(StringUtils.isNotEmpty(icoFile.getOriginalFilename())){
                FileUploader fileUploader = new FileUploader("adminthemes\\default\\images\\system\\ico\\",ACCEPT_TYPES,MAX_SIZE,MAX_FILE_SIZE);
                // 执行上传并获取操作结果
                FileUploader.Result result = fileUploader.upload(request, icoFile);
                menu.setIco(request.getContextPath()+"/adminthemes/default/images/system/ico/"+icoFile.getOriginalFilename());
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
    private String updateFile(MultipartFile file){
        String icoPath="/jeap/adminthemes/default/images/system/ico/";
        InputStream in = null;
        FileOutputStream out =null;
        try {
            in = file.getInputStream();

            File path = new File("E:\\jeap\\jeap\\WebRoot\\adminthemes\\default\\images\\system\\ico\\"+file.getOriginalFilename());
            if(!path.exists()){
                path.createNewFile();
            }
            out = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int byteread;
            while(in.read(buffer)!=-1){
                out.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            if(out!=null) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return icoPath+file.getOriginalFilename();
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
