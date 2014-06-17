package com.easysoft.core.model;

import com.easysoft.core.annotation.JsonInvisible;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: andy
 * Date: 14-3-31
 * Time: 下午12:29
 *
 * @since:
 */
@Entity
@Table(name = "t_form_field")
public class FormField implements Serializable {
    private Integer id;
    /*================数据库字段===================*/
    private String fieldName;
    private String labelName;
    //数据类型
    private String dataType;
    private boolean ispk;
    private boolean isNullable;
    //数据类型长度
    private int dataTypeLength;

    /*================列表信息===================*/
    private boolean inlist;

    /*================表单信息===================*/
    //json用,不存数据库
    private String name;


    private String listwidth;
    private boolean insearch;
    private boolean search_newline;
    private boolean inform;
    private String type;
    //控件显示类型
    private String displayType;
    private String labelwidth;
    private String width;
    private String space;
    private boolean newline;
    private String group;

    private FormEntity form;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonInvisible("List")
    @ManyToOne
    @JoinColumn(name = "form_id")
    public FormEntity getForm() {
        return form;
    }

    public void setForm(FormEntity form) {
        this.form = form;
    }
    @Column(name="field_name")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    @Column(name="label_name")
    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
    @Column(name="is_PK")
    public boolean isIspk() {
        return ispk;
    }

    public void setIspk(boolean ispk) {
        this.ispk = ispk;
    }

    @Column(name="is_nullable")
    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }
    @Column(name="is_inlist")
    public boolean isInlist() {
        return inlist;
    }

    public void setInlist(boolean inlist) {
        this.inlist = inlist;
    }
    @Column(name="listwidth")
    public String getListwidth() {
        return listwidth;
    }

    public void setListwidth(String listwidth) {
        this.listwidth = listwidth;
    }
    @Column(name="is_insearch")
    public boolean isInsearch() {
        return insearch;
    }

    public void setInsearch(boolean insearch) {
        this.insearch = insearch;
    }
    @Column(name="is_search_newline")
    public boolean isSearch_newline() {
        return search_newline;
    }

    public void setSearch_newline(boolean search_newline) {
        this.search_newline = search_newline;
    }
    @Column(name="is_inform")
    public boolean isInform() {
        return inform;
    }

    public void setInform(boolean inform) {
        this.inform = inform;
    }
    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name="labelwidth")
    public String getLabelwidth() {
        return labelwidth;
    }

    public void setLabelwidth(String labelwidth) {
        this.labelwidth = labelwidth;
    }
    @Column(name="width")
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
    @Column(name="space")
    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }
    @Column(name="is_newline")
    public boolean isNewline() {
        return newline;
    }

    public void setNewline(boolean newline) {
        this.newline = newline;
    }
    @Column(name="for_group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    //数据类型
    @Column(name="data_type")
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    @Transient
    public String getName() {
        return fieldName;
    }

    public void setName(String name) {
        this.name = fieldName;
    }

    @Column(name="display_type")
    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }
    @Column(name="data_type_length")
    public int getDataTypeLength() {
        return dataTypeLength;
    }

    public void setDataTypeLength(int dataTypeLength) {
        this.dataTypeLength = dataTypeLength;
    }
    @Transient
    public String getDisplay() {
        return labelName;
    }
}
