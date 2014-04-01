package com.easysoft.framework.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface IRowMapperColumnFilter
{
  public  void filter(Map paramMap, ResultSet paramResultSet)
    throws SQLException;
}