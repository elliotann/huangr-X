package com.easysoft.tag.webapp.bean;

import com.easysoft.framework.db.PageOption;

/*
* 数据列表对象基类，派生类支持如下特性
* 1. grid taglib - Server
* 2. datatable   - Browser
*/
public abstract class Grid {
	//	datatable 参数开始
	private int iDisplayStart;
	private int iDisplayLength;
	private String iSortCol_0;
	private int iSortingCols;
	private String sSearch;
	private String sEcho;
	//	datatable 参数结束

	private String json;
	

	public void setJson(String json) {
		this.json = json;
	}

	private PageOption webpage;
	
	public PageOption getWebpage() {
		if(webpage==null)
			webpage = execute(getPageNo(),getPageSize(),getOrder()); 
		return webpage;
	}
	public void setWebpage(PageOption webpage) {
		this.webpage = webpage;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(String iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public int getiSortingCols() {
		return iSortingCols;
	}
	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	/*
	 *	以下为参数转换代码，方便派生类调用
	 */
	public int getPageNo(){
		if(getiDisplayLength()==0){
			return 1;
		}
		return getiDisplayStart() / getiDisplayLength() + 1;
	}
	public int getPageSize(){
		if(getiDisplayLength()==0)
			return 10;
		
		return getiDisplayLength();
	}
	public String getOrder(){
		return "";
	}
	
	public abstract PageOption execute(int pageNo,int pageSize,String order);
}