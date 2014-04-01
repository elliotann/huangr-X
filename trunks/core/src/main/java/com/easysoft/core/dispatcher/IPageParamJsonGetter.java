package com.easysoft.core.dispatcher;


/**
 * 页面参数获取器
 * @author andy
 */
public interface IPageParamJsonGetter {
	
	/**
	 * 给定一个页面uri,得到此页面挂件参数的json格式字串
	 * @param uri
	 * @return
	 */
	public String getJson(String uri);
	
}
