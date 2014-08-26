package com.easysoft.tag.webapp.taglib.vo;

/**
 * @author : andy.huang
 * @since :
 */
public class SearchControl {
    private String label;//控件名称
    private String name;//控件name和id属性
    private String type;//控件类型

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
}
