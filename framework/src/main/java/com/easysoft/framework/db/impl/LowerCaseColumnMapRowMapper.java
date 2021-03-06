package com.easysoft.framework.db.impl;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * 小写key的columnrowmapper
 *  本类覆写了spring 的ColumnMapRowMapper，将字段名全部小写
 * @author andy
 * 2010-6-13上午11:01:34
 */
public class LowerCaseColumnMapRowMapper extends  ColumnMapRowMapper {

	@Override
	public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Map mapOfColValues = createColumnMap(columnCount);
		for (int i = 1; i <= columnCount; i++) {
			String key = getColumnKey(JdbcUtils.lookupColumnName(rsmd, i));
			key = key.toLowerCase();
			Object obj = null;
			String typename= rsmd.getColumnTypeName(i);
			if("NUMBER".equals(typename)){
				int scale = rsmd.getScale(i);
				int precision = rsmd.getPrecision(i);
				if(scale == 0){
					if(precision<10)
						obj = rs.getInt(i);
					else
						obj = rs.getLong(i);
				}else if(scale>0){
					obj = rs.getDouble(i);
				}
			}else{
				obj = getColumnValue(rs, i);
			}
			if(obj==null)
				obj = "";
			mapOfColValues.put(key, obj);
		}
		return mapOfColValues;
	}
}
