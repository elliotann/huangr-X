package com.easysoft.component.form.daoimpl;
import org.springframework.stereotype.Repository;

import com.easysoft.component.form.dao.IFormTesTDao;
import com.easysoft.component.form.entity.FormTesTEntity;
import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
@Repository
public class FormTesTDaoImpl extends HibernateGenericDao<FormTesTEntity,Integer> implements IFormTesTDao {
}