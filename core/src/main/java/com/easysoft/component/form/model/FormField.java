package com.easysoft.component.form.model;


import com.easysoft.core.common.entity.IdEntity;
import com.easysoft.framework.json.annotation.JsonInvisible;
import com.easysoft.framework.utils.ReflectionUtil;
import com.easysoft.framework.utils.StringUtil;

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
@Table(name="t_form_field")
public class FormField extends IdEntity {

    /*================数据库字段===================*/
    private String fieldName;
    private String displayName;
    //数据类型
    private String dataType;
    private boolean isPK;
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
    private Integer formId;

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
    public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	
    
	@Column(name="is_PK")
    public boolean isPK() {
		return isPK;
	}

	public void setPK(boolean isPK) {
		this.isPK = isPK;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    /**
     * 生成相应的html控件
     * @return
     */
    public String renderHtmlControl(Object obj){
        Object value=null;
        if(obj!=null){
            ReflectionUtil reflectionUtil = new ReflectionUtil(obj);
            value= reflectionUtil.getMethodValue(StringUtil.formatDBFieldName(this.getFieldName()));
        }
        if(value==null){
            value="";
        }

        return "<input name=\""+StringUtil.formatDBFieldName(this.getFieldName())+"\" type=\"text\" id=\""+StringUtil.formatDBFieldName(this.getFieldName())+"\" value=\""+value+"\" class=\"form-control\" onFocus=\"WdatePicker({isShowClear:false,readOnly:true})\"/>";

    }
}
