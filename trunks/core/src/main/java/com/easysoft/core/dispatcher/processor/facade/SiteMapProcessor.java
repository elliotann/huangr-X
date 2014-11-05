package com.easysoft.core.dispatcher.processor.facade;

import com.easysoft.core.dispatcher.core.ContextType;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.manager.ISitemapManager;
import com.easysoft.framework.utils.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteMapProcessor implements Processor {

	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		ISitemapManager siteMapManager = SpringContextHolder.getBean("sitemapManager");
		String siteMapStr = siteMapManager.getsitemap();
		Response response = new StringResponse();
		response.setContent(siteMapStr);
		response.setContentType(ContextType.XML);
		return response;
	}

}
