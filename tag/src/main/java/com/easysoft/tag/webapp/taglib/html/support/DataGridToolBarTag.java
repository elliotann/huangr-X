package com.easysoft.tag.webapp.taglib.html.support;


import com.easysoft.tag.webapp.taglib.vo.ToolBar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by huangxa on 2014/5/27.
 */
public class DataGridToolBarTag extends TagSupport {
    private String title;
    private String clickFun;
    private String icon;
    private String line;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClickFun(String clickFun) {
        this.clickFun = clickFun;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int doEndTag() throws JspException {
        DataGridTag parent = (DataGridTag)findAncestorWithClass(this,DataGridTag.class);
        ToolBar toolBar = new ToolBar();
        toolBar.setTitle(title);
        toolBar.setClickFun(clickFun);
        toolBar.setIcon(icon);

        parent.setToolBars(toolBar);
        return EVAL_PAGE;
    }

}
