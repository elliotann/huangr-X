package com.easysoft.core.common.dao.hibernate.parse;

import java.math.BigDecimal;

import com.easysoft.core.common.dao.hibernate.IHqlParse;
import com.easysoft.core.common.dao.hibernate.qbc.CriteriaQuery;
import com.easysoft.core.utils.hql.HqlGenerateUtil;

public class BigDecimalParseImpl implements IHqlParse {

	
	public void addCriteria(CriteriaQuery cq, String name, Object value) {
		if (HqlGenerateUtil.isNotEmpty(value))
			cq.eq(name, value);

	}

	
	public void addCriteria(CriteriaQuery cq, String name, Object value,
			String beginValue, String endValue) {
		if (HqlGenerateUtil.isNotEmpty(beginValue)) {
			cq.ge(name,
					beginValue.contains(".") ? BigDecimal
							.valueOf(Double.parseDouble(beginValue))
							: BigDecimal.valueOf(Long
									.parseLong(beginValue)));
		}
		if (HqlGenerateUtil.isNotEmpty(endValue)) {
			cq.le(name,
					beginValue.contains(".") ? BigDecimal
							.valueOf(Double.parseDouble(endValue))
							: BigDecimal.valueOf(Long
									.parseLong(endValue)));
		}
		if (HqlGenerateUtil.isNotEmpty(value)) {
			cq.eq(name, value);
		}
	}

}
