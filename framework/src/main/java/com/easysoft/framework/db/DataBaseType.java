package com.easysoft.framework.db;

/**
 * 数据库类型
 * User: andy
 * Date: 13-7-8
 * Time: 下午3:47
 * @since :1.0
 */
public enum DataBaseType {
    MY_SQL("MySQL"),ORACLE("Oracle"),SQL_SERVER("SQLServer");
    private String value;
    DataBaseType(String value){
        this.value = value;
    }
}
