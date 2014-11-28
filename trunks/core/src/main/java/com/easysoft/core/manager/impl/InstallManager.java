package com.easysoft.core.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.manager.IAppManager;
import com.easysoft.core.manager.ISiteManager;
import com.easysoft.core.manager.IUserManager;
import com.easysoft.core.model.JEAPUser;
import com.easysoft.core.model.Site;
import com.easysoft.core.solution.ISolutionInstaller;
import com.easysoft.core.solution.factory.DBSolutionFactory;
import com.easysoft.framework.db.dbsolution.IDBSolution;
@Service("installManager")
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class InstallManager {
	private IAppManager appManager;
	private ApplicationContext context;
	private IUserManager userManager ;
    @Autowired
	private ISiteManager siteManager;
	private ISolutionInstaller solutionInstaller ;
	public void install(String username,String password,String domain,String productid){
		
		Site site  = new Site();
		site.setUserid(1);
		site.setId(1);
		EsfContext context = new EsfContext();
		context.setCurrentSite(site);
		EsfContext.setContext(context);
 
		this.installUser( username, password, domain,productid);
 
	}
 

	/*
	 * 初始化JEAP 平台的用户
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void installUser(String username,String password,String domain,String productid){
		IDBSolution dbsolution = DBSolutionFactory.getDBSolution();
        //导入初始化基础表
		dbsolution.dbImport("file:com/easysoft/core/init.xml");
		
		JEAPUser JEAPUser = new JEAPUser();
		JEAPUser.setAddress("在这里输入详细地址");
		JEAPUser.setUsername(username);
		JEAPUser.setCompanyname("单机版用户");
		JEAPUser.setLinkman("在这里输入联系人信息");
		JEAPUser.setTel("0755-8888888");
		JEAPUser.setMobile("13713587424");
		JEAPUser.setEmail("360042212@qq.com");
		JEAPUser.setUsertype(1);
		JEAPUser.setPassword(password);
		Integer userid = userManager.createUser(JEAPUser);
		userManager.login(username, password);
		  
		Site site = new Site();
		site.setSitename("jeap");
		site.setThemeid(1);
		site.setAdminthemeid(1);
		site.setSitename(productid + "新建站点");
		site.setUserid(userid);
		site.setUsername(JEAPUser.getUsername());
		site.setUsertel(JEAPUser.getTel());
		site.setUsermobile(JEAPUser.getMobile());
		site.setUseremail(JEAPUser.getEmail());
		
		Integer siteid = siteManager.add(site,domain);
		
		solutionInstaller.install(userid,siteid, productid);
		//solutionInstaller.install(userid, siteid, "base");
	}
	

	public IAppManager getAppManager() {
		return appManager;
	}
	public void setAppManager(IAppManager appManager) {
		this.appManager = appManager;
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
 
 
	public ISolutionInstaller getSolutionInstaller() {
		return solutionInstaller;
	}


	public void setSolutionInstaller(ISolutionInstaller solutionInstaller) {
		this.solutionInstaller = solutionInstaller;
	}

	public IUserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}
	
	
}
