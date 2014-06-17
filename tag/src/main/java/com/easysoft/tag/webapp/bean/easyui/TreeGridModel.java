package com.easysoft.tag.webapp.bean.easyui;

/**
 * User: andy
 * Date: 14-1-14
 * Time: 上午9:43
 *
 * @since:
 */
import java.io.Serializable;

public class TreeGridModel
        implements Serializable
{
    private String idField;
    private String textField;
    private String childList;
    private String parentId;
    private String parentText;
    private String code;
    private String src;
    private String roleid;
    private String icon;
    private String order;

    public String getOrder()
    {
        return this.order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getRoleid() {
        return this.roleid;
    }
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
    public String getParentText() {
        return this.parentText;
    }
    public void setParentText(String parentText) {
        this.parentText = parentText;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getSrc() {
        return this.src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public String getParentId() {
        return this.parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
    public String getChildList() {
        return this.childList;
    }
    public void setChildList(String childList) {
        this.childList = childList;
    }
}