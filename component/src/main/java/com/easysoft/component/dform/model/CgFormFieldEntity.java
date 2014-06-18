package com.easysoft.component.dform.model;

/**
 * User: andy
 * Date: 14-1-14
 * Time: 上午9:08
 *
 * @since:
 */

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="cgform_field", schema="")
@DynamicUpdate(true)
@DynamicInsert(true)
@JsonAutoDetect
public class CgFormFieldEntity
        implements Serializable
{
    private String id;
    private String fieldName;
    private CgFormHeadEntity table;
    private Integer length;
    private Integer pointLength;
    private String type;
    private String isNull;
    private Integer orderNum;
    private String isKey;
    private String isShow;
    private String isShowList;
    private String showType;
    private String isQuery;
    private Integer fieldLength;
    private String fieldHref;
    private String fieldValidType;
    private String queryMode;
    private String content;
    private Date createDate;
    private String createBy;
    private String createName;
    private Date updateDate;
    private String updateBy;
    private String updateName;
    private String dictField;
    private String dictTable;
    private String dictText;
    private String mainTable;
    private String mainField;
    private String oldFieldName;
    private String fieldDefault;

    @Id
    @GeneratedValue(generator="paymentableGenerator")
    @GenericGenerator(name="paymentableGenerator", strategy="uuid")
    @Column(name="id", nullable=false, length=32)
    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Column(name="field_name", nullable=false, length=32)
    public String getFieldName()
    {
        return this.fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }
    @ManyToOne
    @JoinColumn(name="table_id", nullable=false, referencedColumnName="id")
    @JsonIgnore
    public CgFormHeadEntity getTable() {
        return this.table;
    }

    public void setTable(CgFormHeadEntity table)
    {
        this.table = table;
    }

    @Column(name="length", nullable=false, precision=10, scale=0)
    public Integer getLength()
    {
        return this.length;
    }

    public void setLength(Integer length)
    {
        this.length = length;
    }

    @Column(name="point_length", nullable=true, precision=10, scale=0)
    public Integer getPointLength()
    {
        return this.pointLength;
    }

    public void setPointLength(Integer pointLength)
    {
        this.pointLength = pointLength;
    }

    @Column(name="type", nullable=false, length=8)
    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    @Column(name="is_null", nullable=true, length=5)
    public String getIsNull()
    {
        return this.isNull;
    }

    public void setIsNull(String isNull)
    {
        this.isNull = isNull;
    }

    @Column(name="is_show", nullable=true, length=5)
    public String getIsShow()
    {
        return this.isShow;
    }

    public void setIsShow(String isShow)
    {
        this.isShow = isShow;
    }

    @Column(name="show_type", nullable=true, length=10)
    public String getShowType()
    {
        return this.showType;
    }

    public void setShowType(String showType)
    {
        this.showType = showType;
    }

    @Column(name="is_query", nullable=true, length=5)
    public String getIsQuery()
    {
        return this.isQuery;
    }

    public void setIsQuery(String isQuery)
    {
        this.isQuery = isQuery;
    }

    @Column(name="query_mode", nullable=true, length=10)
    public String getQueryMode()
    {
        return this.queryMode;
    }

    public void setQueryMode(String queryMode)
    {
        this.queryMode = queryMode;
    }

    @Column(name="content", nullable=false, length=200)
    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Column(name="create_date", nullable=true)
    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @Column(name="create_by", nullable=true)
    public String getCreateBy()
    {
        return this.createBy;
    }

    public void setCreateBy(String createBy)
    {
        this.createBy = createBy;
    }

    @Column(name="create_name", nullable=true, length=32)
    public String getCreateName()
    {
        return this.createName;
    }

    public void setCreateName(String createName)
    {
        this.createName = createName;
    }

    @Column(name="update_date", nullable=true)
    public Date getUpdateDate()
    {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    @Column(name="update_by", nullable=true, length=32)
    public String getUpdateBy()
    {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    @Column(name="update_name", nullable=true, length=32)
    public String getUpdateName()
    {
        return this.updateName;
    }

    public void setUpdateName(String updateName)
    {
        this.updateName = updateName;
    }

    @Column(name="order_num", nullable=true, length=4)
    public Integer getOrderNum()
    {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name="is_key", nullable=true, length=2)
    public String getIsKey()
    {
        return this.isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    @Column(name="field_length", nullable=true, length=10)
    public Integer getFieldLength()
    {
        return this.fieldLength;
    }

    public void setFieldLength(Integer field_length) {
        this.fieldLength = field_length;
    }

    @Column(name="field_href", nullable=true, length=200)
    public String getFieldHref()
    {
        return this.fieldHref;
    }

    public void setFieldHref(String field_href) {
        this.fieldHref = field_href;
    }

    @Column(name="field_valid_type", nullable=true, length=10)
    public String getFieldValidType()
    {
        return this.fieldValidType;
    }

    public void setFieldValidType(String field_valid_type) {
        this.fieldValidType = field_valid_type;
    }

    @Column(name="dict_field", nullable=true, length=100)
    public String getDictField()
    {
        return this.dictField;
    }

    public void setDictField(String dictField) {
        this.dictField = dictField;
    }

    @Column(name="dict_table", nullable=true, length=100)
    public String getDictTable()
    {
        return this.dictTable;
    }

    public void setDictTable(String dictTable) {
        this.dictTable = dictTable;
    }

    @Column(name="main_table", nullable=true, length=100)
    public String getMainTable()
    {
        return this.mainTable;
    }

    public void setMainTable(String mainTable) {
        this.mainTable = mainTable;
    }

    @Column(name="main_field", nullable=true, length=100)
    public String getMainField()
    {
        return this.mainField;
    }

    public void setMainField(String mainField) {
        this.mainField = mainField;
    }

    @Column(name="old_field_name", nullable=true, length=32)
    public String getOldFieldName()
    {
        return this.oldFieldName;
    }

    public void setOldFieldName(String oldFieldName) {
        this.oldFieldName = oldFieldName;
    }

    @Column(name="is_show_list", nullable=true, length=5)
    public String getIsShowList()
    {
        return this.isShowList;
    }

    public void setIsShowList(String isShowList) {
        this.isShowList = isShowList;
    }

    @Column(name="dict_text", nullable=true, length=100)
    public String getDictText()
    {
        return this.dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText;
    }
    @Column(name="field_default", nullable=true, length=20)
    public String getFieldDefault() {
        if (this.fieldDefault != null) {
            this.fieldDefault = this.fieldDefault.trim();
        }
        return this.fieldDefault;
    }

    public void setFieldDefault(String fieldDefault) {
        this.fieldDefault = fieldDefault;
    }
}