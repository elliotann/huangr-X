package com.easysoft.tag.webapp.taglib.html.support;

import com.easysoft.tag.webapp.taglib.vo.DataGridColumn;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by Administrator on 14-5-25.
 */
public class DataGridColumnTag extends BodyTagSupport {
    private String title;
    private String field;
    private String width;
    private String minWidth;
    private String align;
    private String renderFun;
    private String sortType;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setMinWidth(String minWidth) {
        this.minWidth = minWidth;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public void setRenderFun(String renderFun) {
        this.renderFun = renderFun;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public int doEndTag() throws JspException {
        DataGridTag parent = (DataGridTag)findAncestorWithClass(this,DataGridTag.class);
        DataGridColumn column = new DataGridColumn();
        align = StringUtils.isEmpty(align)?"center":align;
        width = StringUtils.isEmpty(width)?"80":width;
        column.setAlign(align);
        column.setTitle(title);
        column.setField(field);
        column.setWidth(width);
        column.setMinWidth(minWidth);
        column.setRenderFun(renderFun);
        column.setSortType(sortType);
        column.setId(id);
        parent.setColumns(column);
        return EVAL_PAGE;
    }

}
