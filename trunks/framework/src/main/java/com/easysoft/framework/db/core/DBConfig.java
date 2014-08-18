package com.easysoft.framework.db.core;

/**
 * User: andy
 * Date: 14-1-17
 * Time: 上午9:17
 *
 * @since: 1.0
 */
public class DBConfig {
    /**数据库服务器**/
    private String dbHost;
    /**数据库名**/
    private String dbName;
    /**数据库用户名**/
    private String dbUsername;
    /**数据库密码**/
    private String dbPassword;
    /**数据库类型**/
    private String dbType;

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
