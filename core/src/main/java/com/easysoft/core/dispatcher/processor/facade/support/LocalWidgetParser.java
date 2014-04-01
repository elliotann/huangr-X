package com.easysoft.core.dispatcher.processor.facade.support;

import com.easysoft.core.widget.IWidget;
import com.easysoft.core.widget.facade.IWidgetParser;
import com.easysoft.core.component.context.WidgetContext;
import com.easysoft.core.dispatcher.core.EopException;
import com.easysoft.framework.spring.SpringContextHolder;

import java.util.Map;

/**
 * 本地挂件解析器     
 * @author andy
 * @since : 1.0
 */
public class LocalWidgetParser implements IWidgetParser {

	 
	
	public String parse(Map<String, String> params) {
		if(params==null) throw new EopException("挂件参数不能为空");
		
		String widgetType = params.get("type");
		if(widgetType==null) throw new EopException("挂件类型不能为空");
        if (!WidgetContext.getWidgetState(widgetType).booleanValue()) return "此挂件已停用";
		try{
			IWidget widget = SpringContextHolder.getBean(widgetType);
			
			String content;
			if(widget==null) content=("widget["+widgetType+"]not found");
			else  {   
				content= widget.process(params); //解析挂件内容
				widget.update(params); //执行挂件更新操作
			}

			return content;
		}catch(Exception e){
			e.printStackTrace();
			return "widget["+widgetType+"]parse error ";
		}		
		
	}

}
