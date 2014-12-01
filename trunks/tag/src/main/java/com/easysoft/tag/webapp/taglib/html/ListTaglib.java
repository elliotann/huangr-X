package com.easysoft.tag.webapp.taglib.html;

import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.FormField;
import com.easysoft.framework.utils.SpringContextHolder;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.tag.webapp.taglib.HtmlTaglib;
import org.apache.commons.lang3.StringUtils;

/**
 * 列表标签
 * @author : andy.huang
 * @since :
 */
public class ListTaglib extends HtmlTaglib{
    private String formCode;//对应表单code

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    @Override
    protected String postStart() {
        if(StringUtils.isEmpty(formCode)){
            throw new RuntimeException("表单编码必须传入");
        }
        IFormManager fromManager = (IFormManager)SpringContextHolder.getBean("formManager");
        FormEntity formEntity  = fromManager.queryFormByCode(formCode);
        if(formEntity==null){
            throw new RuntimeException("表单不存在");
        }

        StringBuilder sb = new StringBuilder();
       
        sb.append("<table class=\"easyui-datagrid\"  style=\"width:auto;height:250px\"	data-options=\"singleSelect:true,collapsible:true,url:'/jeap1.0/core/admin/formTesT.do?dataGrid&ajax=true',method:'post'\">");
        sb.append("<thead><tr>");
        for(FormField field:formEntity.getFields()){
        	sb.append("	<th data-options=\"field:'"+field.getFieldName()+"',width:300,align:'center'\">");
        	sb.append(field.getDisplayName());
        	sb.append("</th>");
        }
        sb.append("</tr></thead>");
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    protected String postEnd() {

        StringBuilder sb = new StringBuilder();
        sb.append("</div></div>");
        sb.append("<div style=\"display:none;\">");
        return sb.toString();
    }
}
