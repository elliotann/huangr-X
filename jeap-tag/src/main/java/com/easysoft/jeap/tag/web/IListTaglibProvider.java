package com.easysoft.jeap.tag.web;

import javax.servlet.jsp.PageContext;
import java.util.List;

public interface IListTaglibProvider {
	public List getData(IListTaglibParam param, PageContext pageContext);
}
