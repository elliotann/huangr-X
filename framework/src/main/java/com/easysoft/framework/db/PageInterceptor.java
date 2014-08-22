package com.easysoft.framework.db;

import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        if (obj instanceof PageOption) {
            final PageOption page = (PageOption) obj;
            final String sql = boundSql.getSql();
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            Connection connection = (Connection) invocation.getArgs()[0];
            this.setTotalRecord(page, mappedStatement, connection);
            final String pageSql = this.getPageSql(page, sql);
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
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

    private String getPageSql(PageOption page, String sql) {
        final StringBuffer sqlBuffer = new StringBuffer(sql);
        if ("mysql".equalsIgnoreCase(databaseType)) {
            return getMysqlPageSql(page, sqlBuffer);
        }else if ("oracle".equalsIgnoreCase(databaseType)) {
            return getOraclePageSql(page, sqlBuffer);
        }
        return sqlBuffer.toString();

    }
    private String getMysqlPageSql(PageOption page, StringBuffer sqlBuffer){
        sqlBuffer.append(" limit ").append(page.getPageSize()*(page.getCurrentPageNo()-1)).append(",").append(page.getPageSize());
        return sqlBuffer.toString();
    }
    private String getOraclePageSql(PageOption page, StringBuffer sqlBuffer) {
        final int offset = ((int)page.getCurrentPageNo() - 1) * page.getPageSize() + 1;
        sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getPageSize());
        sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
        return sqlBuffer.toString();
    }
    private void setTotalRecord(PageOption page, MappedStatement mappedStatement, Connection connection) {
        final BoundSql boundSql = mappedStatement.getBoundSql(page);
        final String sql = boundSql.getSql();
        final String countSql = this.getCountSql(sql);
        final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        final BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings,page);
        final ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                final int totalRecord = rs.getInt(1);
                page.setTotalCount(totalRecord);
            }

        }catch(SQLException e){

        }finally {

        }
        }
    private String getCountSql(String sql) {
        final int index = sql.indexOf("from");
        return "select count(*) " + sql.substring(index);
    }
    private static class ReflectUtil {
        public static Object getFieldValue(Object obj, String fieldName) {
            Object result = null;
            final Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    result = field.get(obj);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        private static Field getField(Object obj, String fieldName) {
            Field field = null;
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            return field;
        }
        public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
            final Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    field.set(obj, fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
