package com.easysoft.core.widget.nav;

import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.shop.widget.AbstractWidget;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网站 导航挂件
 * @author andy
 *
 */
public class SiteNavWidget extends AbstractWidget {

	
	protected void config(Map<String, String> params) {

	}

	
	protected void display(Map<String, String> params) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		List<Nav> navList  =(List<Nav>)request.getAttribute("site_nav_list");
		navList=navList == null?new ArrayList<Nav>():navList;
		this.putData("navList", navList);
		request.removeAttribute("site_nav_list");
	}

}
