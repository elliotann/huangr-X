package com.easysoft.tag.webapp.taglib.vo;

/**
 * @author : andy.huang
 * @since :
 */
public class SearchControl {
    private String label;//控件名称
    private String name;//控件name和id属性
    private String type;//控件类型
    private boolean shortSearch;
    private String url;//combo控件使用

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

    public String buildSearchControl(){
        StringBuilder sb = new StringBuilder();
        if("date".equals(type)){
            sb.append("<input class=\"Wdate form-control\" type=\"text\" id=\"d15\" onFocus=\"WdatePicker({isShowClear:false,readOnly:true})\"/>");
        }else if("select".equals(type)){
            sb.append("<input id=\"cc\" class=\"easyui-combobox combo\" name=\"dept\" data-options=\"valueField:'id',textField:'text',url:'"+url+"'\" style=\"width:206px;height:30px;\">");
        }else{
            sb.append("<input id=\""+this.getName()+"\" class=\"form-control\" type=\"text\" value=\"\" size=\"30\" style=\"width: 200px;\" placeholder=\""+this.getLabel()+"\" name=\""+this.getName()+"\">");
        }

        return sb.toString();
    }
}
