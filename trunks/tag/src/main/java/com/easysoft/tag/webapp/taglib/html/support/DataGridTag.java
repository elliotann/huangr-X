package com.easysoft.tag.webapp.taglib.html.support;

import com.easysoft.tag.webapp.taglib.vo.DataGridColumn;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 14-5-25.
 */
public class DataGridTag extends BodyTagSupport{
    private String action;
    private String style="ligerui";
    private String height;

    private List<DataGridColumn> columns = new ArrayList<DataGridColumn>();

    public void setAction(String action) {
        this.action = action;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setStyle(String style) {
        this.style = style;
    }



    @Override
    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if("ligerui".equals(style)){
                out.write(end());
            }else{
                out.write("other");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    @Override
    public void setBodyContent(BodyContent b) {
        super.setBodyContent(b);
    }

    @Override
    public void doInitBody() throws JspException {
        super.doInitBody();
    }

    @Override
    public int doAfterBody() throws JspException {
        return super.doAfterBody();
    }

    public String end(){
        height=(height==null)?"auto":height;
        StringBuilder sb = new StringBuilder("<script type=\"text/javascript\">");
        sb.append("var listgrid;");
        sb.append("$(function (){");
        sb.append("listgrid = $(\"#maingrid\").ligerGrid({");
        sb.append("height:'"+height+"',");
        sb.append("columns: [");
        int i=0;
        for(DataGridColumn column : columns){
            String width = StringUtils.isEmpty(column.getWidth())?"auto":column.getWidth();
            String minWidth = StringUtils.isEmpty(column.getMinWidth())?"auto":column.getMinWidth();;
            sb.append("{");
            sb.append("display:'"+column.getTitle()+"',");
            sb.append("name:'"+column.getField()+"',");
            sb.append("align:'"+column.getAlign()+"',");
            sb.append("width:'"+width+"',");
            sb.append("minWidth:'"+minWidth+"'");
            if(StringUtils.isNotEmpty(column.getRenderFun())){
                sb.append(",render:"+column.getRenderFun()+"");
            }
            if(i==columns.size()-1){
                sb.append("}");
            }else{
                sb.append("},");
            }

            i++;

        }

        sb.append("],");
        sb.append("url:'"+action+"',  pageSize:30 ,rownumbers:true,");
        sb.append("toolbar: { items: [");
        sb.append("{ text: '增加', click: addForm, icon: 'add' },");
        sb.append("{ line: true },");
        sb.append("{ text: '修改', click: modifyForm, icon: 'modify' },");
        sb.append("{ line: true },");
        sb.append("{ text: '删除', click: delUser, img: '${context }/js/ligerui/skins/icons/delete.gif' },");
        sb.append("{ line: true },");
        sb.append("{ text: '生成代码', click: generatorCode, icon: 'modify' }");
        sb.append("]}");
        sb.append("});");
        sb.append("});");
        sb.append("</script>");
        sb.append("<div class=\"grid\">");
        sb.append("<div id=\"maingrid\"></div>");
        sb.append("</div>");
        return sb.toString();
    }

    public void setColumns(DataGridColumn column){
        columns.add(column);
    }
}
