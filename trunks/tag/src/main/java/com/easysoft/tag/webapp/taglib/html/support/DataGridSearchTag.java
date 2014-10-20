package com.easysoft.tag.webapp.taglib.html.support;

import com.easysoft.tag.webapp.taglib.vo.DataGridColumn;
import com.easysoft.tag.webapp.taglib.vo.SearchControl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by Administrator on 14-5-25.
 */
public class DataGridSearchTag extends BodyTagSupport {
    private String label;
    private String name;
    private String type;
    private boolean shortSearch;//快捷搜索
    private String url;//combo用


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isShortSearch() {
        return shortSearch;
    }

    public void setShortSearch(boolean shortSearch) {
        this.shortSearch = shortSearch;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int doEndTag() throws JspException {
        DataGridTag parent = (DataGridTag)findAncestorWithClass(this,DataGridTag.class);
        SearchControl searchControl = new SearchControl();
        searchControl.setLabel(label);
        searchControl.setName(name);
        searchControl.setType(type);
        searchControl.setShortSearch(shortSearch);
        searchControl.setUrl(url);
        parent.setSearchControls(searchControl);
        return EVAL_PAGE;
    }

}
