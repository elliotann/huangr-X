package com.easysoft.framework.cache;

import org.apache.log4j.Logger;


/**
 * 缓存代理抽象类
 * @author andy
 * @version 1.0
 * @param <T>
 */
public abstract class AbstractCacheProxy<T> {
	protected final Logger logger = Logger.getLogger(getClass());

	protected ICache<T> cache;
    public AbstractCacheProxy(){}
	public AbstractCacheProxy(String cacheName) {
		cache = CacheFactory.getCache(cacheName);
	}
	
	
}
