package com.easysoft.core.dispatcher.processor.facade;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easysoft.core.dispatcher.IPageParamJsonGetter;
import com.easysoft.core.dispatcher.IPageUpdater;
import com.easysoft.core.dispatcher.SafeHttpRequestWrapper;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.page.IPageParser;
import com.easysoft.core.dispatcher.page.facade.support.HeaderResourcePageWrapper;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.processor.facade.support.PageEditModeWrapper;
import com.easysoft.framework.spring.SpringContextHolder;
import com.easysoft.framework.utils.RequestUtil;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;

/**
 * 前端页面处理器
 * 
 * @author andy
 * @since : 1.0
 */
public class FacadePageProcessor implements Processor {

	/**
	 * 
	 *响应页面的三种操作,通过参数_method来识别，并分别通过三个接口来完成操作： 
	 * <li>GET:解析页面： {@link com.easysoft.base.dispatcher.page.IPageParser}</li>
	 * <li>PUT:更新页面 ：{@link com.easysoft.base.dispatcher.IPageUpdater}</li>
	 * <li>PARAMJSON:获取页面挂件参数json串com.elliot.esf.api.facade.IPageParamJsonGetter</li>
	 * </br>
	 * 页面的url会被读取并做为解析实际页面文件地址的依据
	 * @param mode
	 * @param httpResponse
	 * @param httpRequest
	 */
    public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
        httpRequest = new SafeHttpRequestWrapper(httpRequest);
        String method = RequestUtil.getRequestMethod(httpRequest);
        String uri = RequestUtil.getRequestUrl(httpRequest);
        Response response = new StringResponse();

        if (method.equals("GET")){
            IPageParser parser = (IPageParser)SpringContextHolder.getBean("facadePagePaser");
            if ((UserServiceFactory.getUserService().isUserLoggedIn()) && (httpRequest.getParameter("mode") != null)){
                parser = new PageEditModeWrapper(parser);
            }
            parser = new HeaderResourcePageWrapper(parser);
            String content = parser.parse(uri);
            response.setContent(content);
        }

        if (method.equals("PUT")) {
            String params = httpRequest.getParameter("widgetParams");
            String content = httpRequest.getParameter("bodyHtml");
            IPageUpdater pageUpdater = (IPageUpdater) SpringContextHolder.getBean("facadePageUpdater");
            pageUpdater.update(uri, content, params);
            response.setContent("{'state':0,'message':'页面保存成功'}");
        }

        if (method.equals("PARAMJSON")) {
            IPageParamJsonGetter pageParamJsonGetter = (IPageParamJsonGetter)SpringContextHolder.getBean("pageParamJsonGetter");
            String json = pageParamJsonGetter.getJson(uri);
            response.setContent(json);
        }

        return response;
    }

}