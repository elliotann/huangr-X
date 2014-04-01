package com.easysoft.core.common.dao.hibernate.parse;

import com.easysoft.core.common.dao.hibernate.IHqlParse;
import com.easysoft.core.common.dao.hibernate.qbc.CriteriaQuery;
import com.easysoft.core.utils.hql.HqlGenerateUtil;

/**
 * Integer解析拼装起
 * 
 * @author JueYue
 * @date 2014年1月17日
 * @version 1.0
 */
public class IntegerParseImpl implements IHqlParse {

	
	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		if (HqlGenerateUtil.isNotEmpty(value))
			cq.eq(name, value);
	}

	
	public void addCriteria(CriteriaQuery cq, String name, Object value,
			String beginValue, String endValue) {
		if (HqlGenerateUtil.isNotEmpty(beginValue)) {
			cq.ge(name, Integer.parseInt(beginValue));
		}
		if (HqlGenerateUtil.isNotEmpty(endValue)) {
			cq.le(name, Integer.parseInt(endValue));
		}
		if (HqlGenerateUtil.isNotEmpty(value))
			cq.eq(name, value);
	}

}
