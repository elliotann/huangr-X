package com.easysoft.member.backend.vo;

import com.easysoft.core.common.Express;
import com.easysoft.core.common.dao.hibernate.support.AbstractHibernateQry;

public class UserSearchCondition extends AbstractHibernateQry {
	@Express("like")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
