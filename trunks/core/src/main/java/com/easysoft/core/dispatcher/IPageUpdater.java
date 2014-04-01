package com.easysoft.core.dispatcher;


/**
 * 页面更新接口
 * @author andy
 */
public interface IPageUpdater {
	
	/**
	 * 给定页面url和挂件参数json字串，更新页面内容
	 * @param url
	 * @param paramJson
	 */
	public void update(String url,String pageContent,String paramJson);
	
}
