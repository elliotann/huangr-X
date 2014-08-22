package com.easysoft.tag.webapp.taglib.html;

import com.easysoft.component.form.manager.IFormManager;
import com.easysoft.component.form.model.FormEntity;
import com.easysoft.component.form.model.FormField;
import com.easysoft.framework.spring.SpringContextHolder;
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
        sb.append("<script type=\"text/javascript\">");
        sb.append(" var listgrid;");
        sb.append("$(function (){");
        sb.append("listgrid =$(\"#maingrid\").ligerGrid({");
        sb.append("height:'99%',");
        sb.append("url:'oaLeave.do?dataGrid&ajax=yes',");
        sb.append(" columns: [");
        if(formEntity.getFields()!=null){
            int i=0;
            for(FormField field : formEntity.getFields()){
                sb.append("{");
                sb.append("display:'"+field.getLabelName()+"'");
                sb.append(",name:'"+ StringUtil.formatDBFieldName(field.getFieldName())+"'");
                sb.append(",align:'center'");
                sb.append(",width:'auto'");
                sb.append(",minWidth:60");
                sb.append("}");
                if(i!=formEntity.getFields().size()-1){
                    sb.append(",");
                }
                i++;


            }
        }

        sb.append("]");
        sb.append(",toolbar:{");
        sb.append("items: [");
        sb.append("{ text: '增加', click: add, icon: 'add' },");
        sb.append("{ line: true },");
        sb.append("{ text: '修改', click: modify, icon: 'modify' },");
        sb.append("{ line: true },");
        sb.append("{ text: '删除', click: del, img: '${context }/js/ligerui/skins/icons/delete.gif' }");
        sb.append("]");
        sb.append("}");
        sb.append("});");
        sb.append("});");
        sb.append("</script>");


        sb.append("<div class=\"grid\">");
        sb.append("<div id=\"maingrid\">");
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
