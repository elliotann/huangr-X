package com.easysoft.framework.db.dbsolution;

import java.sql.Connection;

/**
 * 
 * @author andy
 * 
 * 数据库导入导出解决方案接口
 * 
 */
public interface IDBSolution {

	public void setConnection(Connection conn);

    /**
     * 导入解决方案
     * @param xml
     * @return
     */
	public boolean dbImport(String xml);
	public boolean dbExport(String[] tables, String xml);
	public String dbExport(String[] tables, boolean dataOnly);
	public int deleteTable(String table);
	public void setPrefix(String prefix);
    public boolean dbSaasImport(String xml, int userid, int siteid);
    public String dbSaasExport(String[] tables, boolean dataOnly, int userid, int siteid);
    public String toLocalType(String type, String size);
    public int dropTable(String table);

    /**
     * 根据界面信息创建表
     * @param table
     */
    //public void createTable(DBTable table);


}
