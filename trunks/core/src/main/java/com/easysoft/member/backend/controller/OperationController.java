package com.easysoft.member.backend.controller;

import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.member.backend.manager.IOperationBtnManager;
import com.easysoft.member.backend.model.Menu;
import com.easysoft.member.backend.model.OperationBtn;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作按钮ACTION
 * Created by huangxa on 2014/5/23.
 */
@Controller
@RequestMapping({"/core/admin/oper"})
public class OperationController extends BaseController {
    @Autowired
    private IOperationBtnManager operationBtnManager;
    @RequestMapping(params = {"add"})
    public ModelAndView add(Integer menuId){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("menuId",menuId);
        return new ModelAndView("core/admin/menu/addOperation",map);
    }
    @RequestMapping(params = {"saveAdd"})
    @ResponseBody
    public AjaxJson saveAdd(OperationBtn operationBtn,@RequestParam MultipartFile icoFile){
        AjaxJson json = new AjaxJson();
        try{
            if(StringUtils.isNotEmpty(icoFile.getOriginalFilename())){
                operationBtn.setIco(updateFile(icoFile));
            }
            this.operationBtnManager.save(operationBtn);
            json.setMsg("新增菜单成功");

        }catch(RuntimeException e){
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
}
