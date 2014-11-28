package com.easysoft.core.widget.facade;

import java.util.Map;



/**
 * 挂件设置html解析器接口 
 * @author andy
 */
public interface IWidgetCfgHtmlParser  {
	
	/**
	 * 有挂件id为编辑
	 * 挂件id为null为新建
	 * @param widgetId
	 * @return
	 */
	public String pase(Map<String,String> params);

}
