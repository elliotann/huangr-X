package com.easysoft.tag.webapp.taglib.vo;

/**
 * Created by huangxa on 2014/5/26.
 */
public class DataGridColumn {
    private String title;
    private String field;
    private String width;
    private String minWidth;
    private String align;
    private String renderFun;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(String minWidth) {
        this.minWidth = minWidth;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getRenderFun() {
        return renderFun;
    }

    public void setRenderFun(String renderFun) {
        this.renderFun = renderFun;
    }
}
