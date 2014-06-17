package com.easysoft.core.manager.cache;

import com.easysoft.core.dispatcher.core.EopException;
import com.easysoft.core.manager.IAppManager;
import com.easysoft.core.model.JEAPApp;
import com.easysoft.framework.cache.AbstractCacheProxy;
import com.easysoft.framework.cache.CacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * App Manager的缓存代理
 * @author andy
 * @version 1.0
 */
@Service("appManager")
public class AppCacheProxy extends AbstractCacheProxy<List<JEAPApp>> implements IAppManager {
	
	
	private IAppManager appManager;
	private static final String APP_LIST_CACHE_KEY = "applist";
    public AppCacheProxy(){}
	@Autowired
	public  AppCacheProxy(IAppManager appManager){
		super(CacheFactory.APP_CACHE_NAME_KEY  );
		this.appManager = appManager;
	}
	
	
	
	public void add(JEAPApp app) {
		cache.clear();
	    appManager.add(app);
	}

	
	public JEAPApp get(String appid) {
		
		if(logger.isDebugEnabled()){
			logger.debug("get app : "+ appid);
		}
		List<JEAPApp> appList = this.list();
	 
		for(JEAPApp app :appList){
			if(app.getAppid().equals(appid)){
				return app;
			 
			}
		}
		
		throw new  EopException("App not found");
	}

	
	public List<JEAPApp> list() {
		
		List<JEAPApp> appList = this.cache.get(APP_LIST_CACHE_KEY);
		
		if(appList==null){
			if(logger.isDebugEnabled()){
				logger.debug("get applist from database");
			}
			appList = appManager.list();
			 this.cache.put(APP_LIST_CACHE_KEY, appList);
		}else{
			if(logger.isDebugEnabled()){
				logger.debug("get applist from cache");
			}
		}
		return appList;
	}
	 
}
