package com.easysoft.framework.db;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringMapper
        implements ParameterizedRowMapper
{
    public Object mapRow(ResultSet rs, int rowNum)
            throws SQLException
    {
        String str = rs.getString(1);
        return str;
    }
}
