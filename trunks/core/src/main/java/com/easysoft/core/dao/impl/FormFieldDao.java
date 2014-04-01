package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.core.dao.IFormFieldDao;
import com.easysoft.core.model.FormField;
import org.springframework.stereotype.Repository;

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

}
