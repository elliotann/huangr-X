package com.easysoft.core.controller;

import com.easysoft.core.code.pojo.CreateFileProperty;
import com.easysoft.core.code.pojo.GenerateEntity;
import com.easysoft.core.code.support.CodeGenerator;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.core.manager.IFormManager;
import com.easysoft.core.model.FormEntity;
import com.easysoft.framework.utils.StringUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午3:08
 *
 * @since:
 */
@Controller
@RequestMapping({"/core/admin/code"})
public class CodeGenController extends BaseController {
    @Autowired
    private IFormManager formManager;
    @RequestMapping(params = {"toGenerate"})
    public ModelAndView toGenerate(Integer formId){
        FormEntity formEntity = null;
        if(formId!=null&&formId!=0){
            formEntity = formManager.getFormById(formId);
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("formEntity",formEntity);
        params.put("entityName", StringUtil.formatDBName(formEntity.getTableName()));
        return new ModelAndView("core/admin/code/add",params);
    }

    @RequestMapping(params = {"generate"})
    @ResponseBody
    public AjaxJson generate(Integer formId,GenerateEntity generateEntity,CreateFileProperty fileProperty){
        AjaxJson result = new AjaxJson();
        FormEntity formEntity = null;
        if(formId!=null&&formId!=0){
            formEntity = formManager.getFormById(formId);
        }
        generateEntity.setFormEntity(formEntity);
        CodeGenerator generator = new CodeGenerator(fileProperty,generateEntity);
        try {
            generator.generateToFile();
        } catch (TemplateException e) {
            result.setSuccess(false);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            result.setSuccess(false);
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return result;
    }
}
