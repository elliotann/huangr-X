package com.easysoft.core.dispatcher;
import com.easysoft.core.dispatcher.FacadePage;
import com.easysoft.core.dispatcher.core.Request;

/**
 * 抽象的前台页面解析器<br/>
 * 包含有一个FacadePage的属性，解析器根据此属性的信息解析页面。
 * @author andy
 * @version 1.0
 */
public abstract class AbstractFacadePageParser extends AbstractParser {

	protected FacadePage page;


	/**
	 * 强迫调用者传递FacadePage属性
	 * @param page
	 */
	public AbstractFacadePageParser(FacadePage page, Request request){
		 super(request);
		 this.page= page;
	}
	
	public FacadePage getPage(){
		return this.page;
	}
  

}