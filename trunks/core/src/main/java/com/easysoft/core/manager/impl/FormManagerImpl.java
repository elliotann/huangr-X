package com.easysoft.core.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.core.dao.IFormDao;
import com.easysoft.core.dao.IFormFieldDao;
import com.easysoft.core.manager.IFormManager;
import com.easysoft.core.model.FormEntity;
import com.easysoft.core.model.FormField;
import com.easysoft.framework.utils.StringUtil;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 上午11:05
 *
 * @since:
 */
@Service("formManager")
public class FormManagerImpl extends GenericService<FormEntity> implements IFormManager {
    @Autowired
    private IFormDao formDao;
    @Autowired
    private IFormFieldDao formFieldDao;
    @Override
    public List list() {
        return formDao.queryForList("from FormEntity e",null);
    }

    @Override
    public void addForm(FormEntity entity) {
        formDao.saveOrUpdate(entity);
        if(entity.getId()!=null)
            formFieldDao.delByFormId(entity.getId());
        for(FormField field : entity.getFields()){
            field.setForm(entity);
            formFieldDao.saveOrUpdate(field);
        }
    }

    @Override
    public FormEntity getFormById(Integer id) {
        FormEntity result = formDao.get(id);
        if(result==null) return null;
        String hql = "from FormField f where f.form.id=?";
        Object[] params = new Object[]{id};
        List<FormField> fields = formFieldDao.queryForList(hql,params);
        result.setFields(fields);
        return result;
    }

    @Override
    public FormEntity getFormById(Integer id,String type) {
        FormEntity result = formDao.get(id);
        if(result==null) return null;
        String hql = "from FormField f where f.form.id=?";
        Object[] params = new Object[]{id};
        List<FormField> fields = formFieldDao.queryForList(hql,params);
        List<FormField> fieldsResult = new ArrayList<FormField>();
        if("form".equals(type)){
            for(FormField field : fields){
                if(field.isInform()){
                    fieldsResult.add(field);
                }
            }
            result.setFields(fieldsResult);
        }else{
            result.setFields(fields);
        }

        return result;
    }

    @Override
    public void delFormById(Integer id) {
        FormEntity result = getFormById(id);
        for(FormField field : result.getFields()){
            formFieldDao.delete(field);
        }
        formDao.delete(result);
    }

    @Override
    public void synDb(Integer formId) {
        //获取表单
        FormEntity formEntity = formDao.get(formId);
        Template t;
        t = getConfig("/org/jeecgframework/web/cgform/engine/hibernate").getTemplate("tableTemplate.ftl");
        Writer out = new StringWriter();
        //模板对于数字超过1000，会自动格式为1,,000(禁止转换)
        t.setNumberFormat("0.#####################");
        t.process(getRootMap(table,DbTableUtil.getDataType(session)), out);
        String xml = out.toString();
        logger.info(xml);
        createTable(xml, table, session);

    }
}
