package com.easysoft.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.easysoft.core.common.dao.hibernate.support.HibernateGenericDao;
import com.easysoft.core.dao.IThemeDao;
import com.easysoft.core.model.Theme;
@Repository
public class ThemeDao extends HibernateGenericDao<Theme, Integer> implements IThemeDao {

	

}
