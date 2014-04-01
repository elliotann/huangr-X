package com.easysoft.core.dispatcher.processor.facade;

import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.StringResponse;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.processor.facade.support.LocalWidgetParser;
import com.easysoft.core.widget.facade.IWidgetParser;
import com.easysoft.framework.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 挂件处理器<br/>
 * 处理挂件异步读取,如在编辑模板时挂件的创建和挂件更新
 * @author andy
 * @version 1.0
 */
public class WidgetProcessor   implements Processor {


	
	/**
	 * 
	 * @param mode
	 * @param httpResponse
	 * @param httpRequest
	 */
	public Response process(int mode, HttpServletResponse httpResponse, HttpServletRequest httpRequest){
		
		/*
		 * request中有挂件数据形成需要的参数
		 */
	    Map<String,String> widgetParams = RequestUtil.paramToMap(httpRequest);
	    
	    
		/*
		 * 调用挂件获取接口
		 * 传递给上述挂件参数 
		 */
		IWidgetParser widgetParser = new LocalWidgetParser();
		String content = widgetParser.parse(widgetParams);
		Response response = new StringResponse();
		//content = StringUtil.compressHtml(content);
		response.setContent(content);
		return response;
		

	}

}