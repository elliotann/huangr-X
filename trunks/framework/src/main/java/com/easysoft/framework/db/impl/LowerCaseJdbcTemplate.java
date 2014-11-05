package com.easysoft.framework.db.impl;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 * 覆写jdbctemlate ，使用LowerCaseColumnMapRowMapper
 * @author andy
 * 2010-6-13上午11:05:32
 */

public class LowerCaseJdbcTemplate extends JdbcTemplate {
	@Override
	protected RowMapper getColumnMapRowMapper() {
		if("2".equals("2")){
			return new LowerCaseColumnMapRowMapper();
		}else{
			return new ColumnMapRowMapper();
		}
	}

}
