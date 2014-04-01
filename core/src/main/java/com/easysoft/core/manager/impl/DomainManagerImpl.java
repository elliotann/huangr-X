package com.easysoft.core.manager.impl;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.IDomainManager;
import com.easysoft.core.model.JEAPSiteDomain;
import com.easysoft.core.model.Site;
import com.easysoft.framework.db.IDaoSupport;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 域名管理
 * 
 * @author andy
 */
@Service("domainManager")
public class DomainManagerImpl implements IDomainManager {

	private IDaoSupport<JEAPSiteDomain> daoSupport;

	
	
	public JEAPSiteDomain get(Integer id) {
		String sql = "select * from jeap_sitedomain where id = ?";
		return daoSupport.queryForObject(sql, JEAPSiteDomain.class, id);
	}

	
	public List<JEAPSiteDomain> listUserDomain() {
		Integer userid = EsfContext.getContext().getCurrentSite().getUserid();
		String sql = "select * from jeap_sitedomain where userid=?";
		return this.daoSupport.queryForList(sql, JEAPSiteDomain.class, userid);
	}

	
	public List<JEAPSiteDomain> listSiteDomain() {
		Site site = EsfContext.getContext().getCurrentSite();
		String sql = "select * from jeap_sitedomain where userid=? and siteid =?";
		return this.daoSupport.queryForList(sql, JEAPSiteDomain.class, site
				.getUserid(), site.getId());
	}
	
	public List<JEAPSiteDomain> listSiteDomain(Integer siteid) {
		String sql = "select * from jeap_sitedomain where  siteid =?";
		return this.daoSupport.queryForList(sql, JEAPSiteDomain.class, siteid);
	}
	
	public void edit(JEAPSiteDomain domain) {
		this.daoSupport.update("jeap_sitedomain", domain, " id = "
				+ domain.getId());
	}

	public IDaoSupport<JEAPSiteDomain> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<JEAPSiteDomain> daoSupport) {
		this.daoSupport = daoSupport;
	}

	
	public int getDomainCount(Integer siteid) {
		String sql = "select count(0)  from jeap_sitedomain where  siteid =?";
		return this.daoSupport.queryForInt(sql, siteid);
	}




}
