package com.easysoft.framework.db.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.easysoft.framework.db.IDataSourceCreator;
import com.easysoft.framework.db.core.DataSourceType;
import com.easysoft.framework.db.core.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-24
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
@Component("dynamicDataSourceCreator")
public class DynamicDataSourceCreator implements IDataSourceCreator {
    @Autowired
    private DataSource dataSource;

    public DataSource createDataSource(String driver, String url, String username, String password) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceType.dataSource_jeecg,druidDataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource)dataSource;
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }
}
