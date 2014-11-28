package com.easysoft.component.form.dao;

import com.easysoft.core.common.dao.IGenericDao;
import com.easysoft.component.form.model.FormEntity;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 上午11:07
 *
 * @since:
 */
public interface IFormDao extends IGenericDao<FormEntity,Integer> {
    public FormEntity queryFormByCode(String code);
}
