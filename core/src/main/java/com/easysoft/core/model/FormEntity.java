package com.easysoft.core.model;

import com.easysoft.framework.utils.DateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: andy
 * Date: 14-3-31
 * Time: 下午12:28
 *
 * @since:
 */
@Entity
@Table(name = "t_form")
public class FormEntity implements Serializable {
    private Integer id;
    public String tableName;
    public String tableTitle;
    private String isSynDB="0";//是否同步数据库
    private String version="1.0.0";
    private String createBy;
    private String createTime = DateUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss");
    private List<FormField> fields;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="tablename")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    @Column(name="tableTitle")
    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }
    @Transient
    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> fields) {
        this.fields = fields;
    }
    @Column(name="is_syndb")
    public String getIsSynDB() {
        return isSynDB;
    }

    public void setIsSynDB(String synDB) {
        isSynDB = synDB;
    }


    @Column(name="version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @Column(name="create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Column(name="create_time")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}
