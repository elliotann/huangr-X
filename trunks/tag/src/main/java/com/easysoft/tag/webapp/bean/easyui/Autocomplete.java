package com.easysoft.tag.webapp.bean.easyui;

/**
 * User: andy
 * Date: 14-1-14
 * Time: 上午9:43
 *
 * @since:
 */
public class Autocomplete
{
    private String entityName;
    private String labelField;
    private String valueField;
    private String searchField;
    private String trem;
    private Integer maxRows;
    private Integer curPage;

    public String getSearchField()
    {
        return this.searchField;
    }
    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }
    public String getEntityName() {
        return this.entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    public String getTrem() {
        return this.trem;
    }
    public void setTrem(String trem) {
        this.trem = trem;
    }
    public String getLabelField() {
        return this.labelField;
    }
    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }
    public String getValueField() {
        return this.valueField;
    }
    public void setValueField(String valueField) {
        this.valueField = valueField;
    }
    public Integer getMaxRows() {
        return this.maxRows;
    }
    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }
    public Integer getCurPage() {
        if ((this.curPage == null) || (this.curPage.intValue() < 1)) {
            this.curPage = Integer.valueOf(1);
        }
        return this.curPage;
    }
    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }
}
