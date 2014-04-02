package com.easysoft.core.dao;

import com.easysoft.core.common.dao.hibernate.IGenericDAO;
import com.easysoft.core.model.FormField;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 下午2:32
 *
 * @since:
 */
public interface IFormFieldDao extends IGenericDAO<FormField>{
    public void delByFormId(Integer formId);
}
