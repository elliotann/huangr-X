package com.easysoft.component.form.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.component.form.model.FormField;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 下午2:32
 *
 * @since:
 */
public interface IFormFieldDao extends IGenericDao<FormField,Integer> {
    public void delByFormId(Integer formId);
}
