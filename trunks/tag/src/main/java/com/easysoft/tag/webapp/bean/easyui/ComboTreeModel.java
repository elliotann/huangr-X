package com.easysoft.tag.webapp.bean.easyui;

/**
 * User: andy
 * Date: 14-1-14
 * Time: 上午9:42
 *
 * @since:
 */
import java.io.Serializable;

public class ComboTreeModel
        implements Serializable
{
    private String idField;
    private String textField;
    private String iconCls;
    private String childField;
    private String srcField;

    public ComboTreeModel(String idField, String textField, String childField)
    {
        this.idField = idField;
        this.textField = textField;
        this.childField = childField;
    }
    public ComboTreeModel(String idField, String textField, String childField, String srcField) {
        this.idField = idField;
        this.textField = textField;
        this.childField = childField;
        this.srcField = srcField;
    }
    public String getIconCls() {
        return this.iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public String getChildField() {
        return this.childField;
    }
    public void setChildField(String childField) {
        this.childField = childField;
    }
    public String getIdField() {
        return this.idField;
    }
    public void setIdField(String idField) {
        this.idField = idField;
    }
    public String getTextField() {
        return this.textField;
    }
    public void setTextField(String textField) {
        this.textField = textField;
    }
    public String getSrcField() {
        return this.srcField;
    }
    public void setSrcField(String srcField) {
        this.srcField = srcField;
    }
}