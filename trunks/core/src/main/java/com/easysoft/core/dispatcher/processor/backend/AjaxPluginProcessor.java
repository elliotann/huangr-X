package com.easysoft.core.dispatcher.processor.backend;

import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.framework.plugin.IAjaxExecuteEnable;
import com.easysoft.framework.spring.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 插件异步执行器
 * @author andy
 *
 */
public class AjaxPluginProcessor implements Processor {

	@Override
	public Response process(int mode, HttpServletResponse httpResponse,
			HttpServletRequest httpRequest) {
		
		Response response  = new StringResponse();
		String beanid = httpRequest.getParameter("beanid");
		Object plugin = SpringContextHolder.getBean(beanid);
		
		/**
		 * 有效性验验
		 */
		if(plugin==null){
			response.setContent("error:plugin is null");
			return response;
		}
		
		if(! (plugin instanceof IAjaxExecuteEnable)){
			response.setContent("error:plugin is not a IAjaxExecuteEnable");
			return  response;
		}
		
		IAjaxExecuteEnable ajaxPlugin = (IAjaxExecuteEnable)plugin;
		
		String content = ajaxPlugin.execute();		
		response.setContent(content);
		return response;
	}

}
