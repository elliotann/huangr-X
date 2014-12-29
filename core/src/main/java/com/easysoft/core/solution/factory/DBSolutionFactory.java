package com.easysoft.core.solution.factory;


import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.db.dbsolution.IDBSolution;
import com.easysoft.framework.utils.SpringContextHolder;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;


public class DBSolutionFactory {
	public static IDBSolution getDBSolution() {
		IDBSolution result = null;
		if (ParamSetting.DBTYPE.equals("1")) {
			result = SpringContextHolder.getBean("mysqlSolution");
		} else if (ParamSetting.DBTYPE.equals("2")) {
			result = SpringContextHolder.getBean("oracleSolution");
		} else if (ParamSetting.DBTYPE.equals("3")) {
			result = SpringContextHolder.getBean("sqlserverSolution");
		} else
			throw new RuntimeException("未知的数据库类型");
		return result;
	}
    public static boolean dbImport(String xml, String prefix){
        Connection conn = getConnection(null);
        IDBSolution dbsolution = getDBSolution();
        dbsolution.setPrefix(prefix);
        dbsolution.setConnection(conn);

        
        return  dbsolution.dbImport(xml);

    }
    //得到数据库连接
    public static Connection getConnection(JdbcTemplate jdbcTemplate) {
        if (jdbcTemplate == null)
            jdbcTemplate = (JdbcTemplate)SpringContextHolder.getBean("jdbcTemplate");
        try{
            return jdbcTemplate.getDataSource().getConnection();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static String dbExport(String[] tables, boolean dataOnly, String prefix) {
        Connection conn = getConnection(null);
        IDBSolution dbsolution = getDBSolution();
        dbsolution.setPrefix(prefix);
        dbsolution.setConnection(conn);
        String   result = dbsolution.dbExport(tables, dataOnly);
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
