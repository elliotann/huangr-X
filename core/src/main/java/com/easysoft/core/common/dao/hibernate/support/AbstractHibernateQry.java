package com.easysoft.core.common.dao.hibernate.support;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.easysoft.core.common.BaseSearchCondition;
import com.easysoft.core.common.Express;

public class AbstractHibernateQry extends BaseSearchCondition {

	@Override
	public String getSearchCondition(String alias) {
		return null;
	}

	public List<Criterion> getSearchCriteria() {
		List<Criterion> criterions = new ArrayList<Criterion>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Express.class)) {
				field.setAccessible(true);
				try {
					if (field.get(this) == null)
						continue;
					Express express = field.getAnnotation(Express.class);
					if ("like".equals(express.value())) {

						criterions.add(Restrictions.like(field.getName(),
								field.get(this) + "", MatchMode.ANYWHERE));
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return criterions;
	}

}
