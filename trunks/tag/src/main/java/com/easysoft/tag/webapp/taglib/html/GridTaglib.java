package com.easysoft.tag.webapp.taglib.html;

import com.easysoft.framework.db.PageOption;
import com.easysoft.framework.pager.IPageHtmlBuilder;
import com.easysoft.framework.pager.impl.SimplePageHtmlBuilder;
import com.easysoft.tag.webapp.bean.Grid;
import com.easysoft.tag.webapp.taglib.HtmlTaglib;
import com.easysoft.tag.webapp.taglib.html.support.GridAjaxPageHtmlBuilder;

/**
 * grid主标签
 * @author andy
 *
 */
public class GridTaglib extends HtmlTaglib {

	private String from;
	private String pager; //是否显示分页
	private String gridid;
	private String ajax; //是否异步
	
	protected String postStart() {
		//生成gridid
		gridid = ""+System.currentTimeMillis();
		return "<div class=\"gridbody\"  gridid='"+gridid+"' ><table class=\"bordered\">";
	}
	
	
	protected String postEnd() {
		StringBuffer str = new StringBuffer();
		str.append("</table>");
		if(pager==null || pager.equals("yes")){
			str.append(this.buildPageHtml());
		}
		str.append("</div>");
		return str.toString();
	}

	private String buildPageHtml(){

		/**读取并计算正确页数**/
		String tempPage = this.getRequest().getParameter("page");
		int pageNo = 1;
		if(tempPage!=null && !tempPage.toString().equals("")){
			pageNo = Integer.valueOf(tempPage.toString());
		}
		
		/**由上下文中取出分页对象，以便计算总数、分页数**/
		Object obj = this.pageContext.getAttribute(from);
		if(obj==null){
			obj = this.getRequest().getAttribute(from);
			if(obj==null){
				return "";
			}
			this.pageContext.setAttribute(from,obj);
		}
		
		//分析分页数据对象的类型
		PageOption pageOption = null;
		if(obj instanceof PageOption)
			pageOption = (PageOption)obj;
		else if(obj instanceof Grid)
			pageOption = ((Grid)obj).getWebpage();
		else
			return "";

		int pageSize = pageOption.getPageSize();
		long totalCount = pageOption.getTotalCount();
		
		IPageHtmlBuilder pageHtmlBuilder =null;
		if("yes".equals(ajax) ){
			//采用异步分页构建器//采用异步分页构建器
			pageHtmlBuilder =new GridAjaxPageHtmlBuilder( pageNo, totalCount,  pageSize,gridid);
		}
		else{
			//采用正常跳转分页构建器
			pageHtmlBuilder = new SimplePageHtmlBuilder(pageNo, totalCount,  pageSize); 
		}

		return pageHtmlBuilder.buildPageHtml();

	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getAjax() {
		return ajax;
	}

	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	
}
