package com.easysoft.component.form.controller;

import com.easysoft.core.code.pojo.CreateFileProperty;
import com.easysoft.core.code.pojo.GenerateEntity;
import com.easysoft.core.code.support.CodeGenerator;
import com.easysoft.core.common.controller.BaseController;
import com.easysoft.core.common.vo.json.AjaxJson;
import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.framework.utils.StringUtil;
import freemarker.template.TemplateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private static final Log logger = LogFactory.getLog(CodeGenController.class);
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
        return new ModelAndView("admin/component/form/toGenCode",params);
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
            e.printStackTrace();
        } catch (IOException e) {
            result.setSuccess(false);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 同步数据库
     * @return
     */
    @RequestMapping(params={"synDb"})
    @ResponseBody
    public AjaxJson synDb(Integer formId){
        AjaxJson result = new AjaxJson();
        try{
            formManager.synDb(formId);
        }catch(Exception e){
            logger.error("同步数据库出错!",e);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }

        return result;
    }
}
