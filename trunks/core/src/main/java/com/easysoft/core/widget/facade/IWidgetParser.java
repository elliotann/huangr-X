package com.easysoft.core.widget.facade;

import java.util.Map;



/**
 * 挂件解析器接口
 * @author andy
 */
public interface IWidgetParser {
	
	/**
	 * 根据挂件参数 出挂件内容
	 * @param params
	 * @return
	 */
	public String parse(Map<String, String> params);
	
	

	
}
