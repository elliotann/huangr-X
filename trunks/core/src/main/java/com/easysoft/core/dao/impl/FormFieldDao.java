package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.core.dao.IFormFieldDao;
import com.easysoft.core.model.FormField;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 下午2:33
 *
 * @since:
 */
@Repository
public class FormFieldDao extends GenericDAO<FormField> implements IFormFieldDao{
    public FormFieldDao(){
        super(FormField.class);
    }

    @Override
    public void delByFormId(Integer formId) {
        List<FormField> formFields = this.queryForList ("from FormField f where f.form.id="+formId);
        for(FormField field : formFields){
            this.delete(field);
        }
    }
}
