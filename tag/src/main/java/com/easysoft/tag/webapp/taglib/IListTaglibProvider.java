package com.easysoft.tag.webapp.taglib;

import javax.servlet.jsp.PageContext;
import java.util.List;

public interface IListTaglibProvider {
	public List getData(IListTaglibParam param, PageContext pageContext);
}
