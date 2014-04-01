package com.easysoft.core.dispatcher;


import com.easysoft.core.dispatcher.page.IPageParser;

/**
 * 页面包装器
 * @author andy
 */
public class PageWrapper implements IPageParser {
	protected IPageParser pageParser;
	public PageWrapper(IPageParser parser){
		this.pageParser = parser;
	}
	
	public String parse(String url) {
		 
		return this.pageParser.parse(url);
	}

}
