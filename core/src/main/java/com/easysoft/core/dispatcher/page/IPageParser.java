package com.easysoft.core.dispatcher.page;

/**
 * 页面解析器接口
 * @author andy
 */
public interface IPageParser {
	
	/**
	 * 给定一个站点url，解析页面的html<br/>
	 * 
	 * @param url 全地址，包含其参数，含有?mode=yes为编辑模式
	 * @return
	 */
	public String parse(String url);
	
	
}
