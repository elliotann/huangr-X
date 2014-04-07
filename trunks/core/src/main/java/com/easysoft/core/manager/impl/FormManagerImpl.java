package com.easysoft.core.manager.impl;

import com.easysoft.core.common.service.impl.GenericService;
import com.easysoft.core.dao.IFormDao;
import com.easysoft.core.dao.IFormFieldDao;
import com.easysoft.core.manager.IFormManager;
import com.easysoft.core.model.FormEntity;
import com.easysoft.core.model.FormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void delFormById(Integer id) {
        FormEntity result = getFormById(id);
        for(FormField field : result.getFields()){
            formFieldDao.delete(field);
        }
        formDao.delete(result);
    }
}
