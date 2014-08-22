package com.easysoft.tag.webapp.taglib.html.support;

import com.easysoft.framework.db.PageOption;
import com.easysoft.tag.webapp.bean.Grid;
import com.easysoft.tag.webapp.taglib.IListTaglibParam;
import com.easysoft.tag.webapp.taglib.IListTaglibProvider;

import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;

public class GridBodyProvider implements IListTaglibProvider {
	public List getData(IListTaglibParam _param, PageContext pageContext) {

		GridBodyParam param = (GridBodyParam) _param;
		String from = param.getFrom();

		Object obj = pageContext.getAttribute(from);
		if (obj == null){
			obj = pageContext.getRequest().getAttribute(from);
			if (obj == null){
				return new ArrayList();
			}
		}
		//	from	即可以是Page对象，也可以是Grid对象。
		PageOption pageOption = null;
		List list = null;
		if(obj instanceof PageOption){
			pageOption = (PageOption)obj;
			list  = (List) pageOption.getResult();
		}
		else if(obj instanceof Grid){
			pageOption = ((Grid)obj).getWebpage();
			list  = (List) pageOption.getResult();
		}else if(obj instanceof List){
			list = (List)obj;
		}
		
		return list;
	}
}
