package com.easysoft.framework.db;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerMapper implements ParameterizedRowMapper {

	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer  v = rs.getInt(1);
		return v;
	}

}
