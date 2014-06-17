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
    private String userName;
    /**数据库密码**/
    private String password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
