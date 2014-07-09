package com.easysoft.jeap.framework.db;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Administrator on 2014/7/9.
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
    private String databaseType;// 数据库类型，不同的数据库有不同的分页方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        final StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        final BoundSql boundSql = delegate.getBoundSql();
        final Object obj = boundSql.getParameterObject();
        if (obj instanceof SearchPageUtil) {
            final SearchPageUtil page = (SearchPageUtil) obj;
            final String sql = boundSql.getSql();
            final String pageSql = this.getPageSql(page, sql);
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.databaseType = properties.getProperty("databaseType");
    }

    private String getPageSql(SearchPageUtil page, String sql) {
        final StringBuffer sqlBuffer = new StringBuffer(sql);
        if ("mysql".equalsIgnoreCase(databaseType)) {
            return getMysqlPageSql(page, sqlBuffer);
        }else if ("oracle".equalsIgnoreCase(databaseType)) {
            return getOraclePageSql(page, sqlBuffer);
        }
        return sqlBuffer.toString();

    }
}
