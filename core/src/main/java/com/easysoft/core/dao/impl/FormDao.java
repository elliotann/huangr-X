package com.easysoft.core.dao.impl;

import com.easysoft.core.common.dao.hibernate.support.GenericDAO;
import com.easysoft.core.dao.IFormDao;
import com.easysoft.core.model.FormEntity;
import org.springframework.stereotype.Repository;

/**
 * User: andy
 * Date: 14-4-1
 * Time: 上午11:08
 *
 * @since:
 */
@Repository
public class FormDao extends GenericDAO<FormEntity> implements IFormDao {
    public FormDao() {
        super(FormEntity.class);
    }
}
