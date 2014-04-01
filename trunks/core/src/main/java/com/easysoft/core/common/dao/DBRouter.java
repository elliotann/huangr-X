package com.easysoft.core.common.dao;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.db.IDBRouter;
import com.easysoft.core.solution.factory.DBSolutionFactory;
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
		if ("1".equals(ParamSetting.RUNMODE)) {
			return result;
		}

		Site site = EsfContext.getContext().getCurrentSite();
		Integer userid = site.getUserid();
		Integer siteid = site.getId();

		return result + "_" + userid + "_" + siteid;
	}

    @Override
    public void doSaasInstall(String xmlFile) {
        //开发模式
        if ("1".equals(ParamSetting.RUNMODE)) {
             return;
        }
        this.prefix = (this.prefix == null ? "" : this.prefix);
        DBSolutionFactory.dbImport(xmlFile, this.prefix);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
