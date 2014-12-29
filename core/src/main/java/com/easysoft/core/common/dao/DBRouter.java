package com.easysoft.core.common.dao;

import com.easysoft.framework.db.IDBRouter;
import com.easysoft.framework.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 简单的分表方式SAAS数据路由器<br/>
 * 由模块名连接上用户id形成表名
 * @author andy
 * @version 1.0
 */
public class DBRouter implements IDBRouter {
    protected final Logger logger = Logger.getLogger(getClass());
    public static final String EXECUTECHAR = "!-->";
	private JdbcTemplate jdbcTemplate;

	// 表前缀
	private String prefix;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}



	public String getTableName(String moudle) {
		String result = StringUtil.addPrefix(moudle, prefix);
		return result;
		
	}


    public void doSaasInstall(String xmlFile) {
    	return;
      
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
