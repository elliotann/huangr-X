package com.easysoft.core.solution.factory;


import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.db.dbsolution.IDBSolution;
import com.easysoft.framework.spring.SpringContextHolder;
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
        boolean result;
        if (ParamSetting.RUNMODE.equals("1")) {
            result = dbsolution.dbImport(xml);
        }else {
            Site site = EsfContext.getContext().getCurrentSite();
            Integer userid = site.getUserid();
            Integer siteid = site.getId();
            result = dbsolution.dbSaasImport(xml, userid.intValue(), siteid.intValue());
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return result;
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
        String result = "";
        if (ParamSetting.RUNMODE.equals("1")) {
            result = dbsolution.dbExport(tables, dataOnly);
        } else {
            Site site = EsfContext.getContext().getCurrentSite();
            Integer userid = site.getUserid();
            Integer siteid = site.getId();
            result = dbsolution.dbSaasExport(tables, dataOnly, userid.intValue(), siteid.intValue());
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
