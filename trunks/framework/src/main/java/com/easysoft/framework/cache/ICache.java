package com.easysoft.framework.cache;


/**
 * 缓存接口
 * @author andy
 *
 */
public interface ICache<T> {
	/**
	 * Get an item from the cache, nontransactionally
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 * @throws
	 */
	public T get(Object key);
	/**
	 * Add an item to the cache, nontransactionally, with
	 * failfast semantics
	 * @param key
	 * @param value
	 * @throws
	 */
	public void put(Object key, T value);
	/**
	 * Remove an item from the cache
	 */
	public void remove(Object key);
	/**
	 * Clear the cache
	 */
	public void clear();
}
