package com.easysoft.framework.db;

import javax.sql.DataSource;

/**
 * 数据源创建
 * User: andy
 * Date: 13-7-19
 * Time: 下午12:47
 * @since: 1.0
 */
public interface IDataSourceCreator {
    /**
     * 创建数据源
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    public DataSource createDataSource(String driver, String url, String username, String password);

}
