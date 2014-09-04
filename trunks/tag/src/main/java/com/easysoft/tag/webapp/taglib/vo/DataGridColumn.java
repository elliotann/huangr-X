package com.easysoft.tag.webapp.taglib.vo;

/**
 * Created by huangxa on 2014/5/26.
 */
public class DataGridColumn {
    private String title;
    private String field;
    private String width;
    private String minWidth;
    private String align="center";
    private String renderFun;
    private String sortType;//排序类型
    private String id;//主要用于树模式下

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

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
