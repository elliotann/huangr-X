package com.easysoft.framework.db.impl;

import com.easysoft.framework.db.IDataSourceCreator;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * User: andy
 * Date: 13-7-19
 * Time: 下午12:49
 * @since: 1.0
 */
@Component("dataSourceCreator")
public class ComboPooledDataSourceCreator implements IDataSourceCreator {
    private static final Log logger = LogFactory.getLog(ComboPooledDataSourceCreator.class);
    @Autowired
    private DataSource dataSource;

    @Override
    public DataSource createDataSource(String driver, String url, String username, String password) {
        ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource)dataSource;
        try {
            comboPooledDataSource.setDriverClass(driver);
            comboPooledDataSource.setJdbcUrl(url);
            comboPooledDataSource.setUser(username);
            comboPooledDataSource.setPassword(password);
            return comboPooledDataSource;
        } catch (PropertyVetoException e) {
            logger.error("Get datasource error!url="+url,e);
        }

        return null;
    }


}
