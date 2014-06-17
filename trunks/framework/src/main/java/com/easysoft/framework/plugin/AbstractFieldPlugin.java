package com.easysoft.framework.plugin;

import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.plugin.field.IFieldSaveEvent;
import com.easysoft.framework.model.DataField;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractFieldPlugin extends AutoRegisterPlugin implements
        IFieldSaveEvent, IFieldDispalyEvent,IFieldValueShowEvent {

	
	/**
	 * 定义是否有选择值<br>
	 * 如下拉框或单选按钮类控件是有选择值控件
	 * @return
	 */
	public abstract int getHaveSelectValue();
	 
	
	
	/**
	 * 数据保存事件默认响应<br>
	 * 逻辑为以name为字段为字段名，值为request.getParameter(fieldname);
	 */
	public void onSave(Map article, DataField field) {
		HttpServletRequest request  = ThreadContextHolder.getHttpRequest();
		article.put(field.getEnglish_name(),request.getParameter(field.getEnglish_name()));
	}
	
 
	
	/**
	 * 数据显示默认响应事件<br>
	 * 逻辑为直接返回字段值<br>
	 * 如果为null返回空串
	 */
	public Object onShow(DataField field, Object value) {
		if(value!=null)
		return value.toString();
		else return "";
	}
	
	/**
	 * 根据字段类型，提供字段校验的html
	 * @param field 
	 * @return
	 */
	protected String  wrappValidHtml(DataField field ){
		
		StringBuffer html  = new StringBuffer();
		if(field.getIs_validate() ==1 ){
			html.append(" required=\"true\" " );
		}
 
		return html.toString();
	}
	

	
	/**
	 * 返回字段的数据类型
	 * 在创建字段时会掉用此方法
	 * @since 2.3 
	 */
	public String getDataType(){
		
		return "varchar(255)";
	}
    public abstract String getId();
}
