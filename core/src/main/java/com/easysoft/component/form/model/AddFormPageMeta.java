package com.easysoft.component.form.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.easysoft.framework.utils.ReflectionUtil;
import com.easysoft.framework.utils.StringUtil;

@Entity
@Table(name="t_addform_page_meta")
public class AddFormPageMeta extends FormPageMeta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7350242224132029827L;
	/**
	 * 控件显示类型
	 */
	private String displayType;
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	
	private String name;
	private String title;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
     * 生成相应的html控件
     * @return
     */
    public String renderHtmlControl(Object obj){
        Object value=null;
        if(obj!=null){
            ReflectionUtil reflectionUtil = new ReflectionUtil(obj);
            value= reflectionUtil.getMethodValue(StringUtil.formatDBFieldName(this.getName()));
        }
        if(value==null){
            value="";
        }
        if("DATE".equals(displayType)){
        	return "<input name=\""+StringUtil.formatDBFieldName(this.getName())+"\" type=\"text\" id=\""+StringUtil.formatDBFieldName(this.getName())+"\" value=\""+value+"\" class=\"form-control\" onFocus=\"WdatePicker({isShowClear:false,readOnly:true})\"/>";
        }else{
        	return "<input name=\""+StringUtil.formatDBFieldName(this.getName())+"\" type=\"text\" id=\""+StringUtil.formatDBFieldName(this.getName())+"\" value=\""+value+"\" class=\"form-control\" />";
        }
        

    }

}
