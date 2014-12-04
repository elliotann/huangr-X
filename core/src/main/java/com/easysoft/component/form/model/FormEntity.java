package com.easysoft.component.form.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.easysoft.core.common.entity.IdEntity;
import com.easysoft.framework.utils.DateUtil;

/**
 * User: andy
 * Date: 14-3-31
 * Time: 下午12:28
 *
 * @since:
 */
@Entity
@Table(name="t_form")
public class FormEntity extends IdEntity {

    public String tableName;
    public String formName;
    private String isSynDB="0";//是否同步数据库
    private String version="1.0.0";

    private String createTime = DateUtil.toString(new Date(),"yyyy-MM-dd HH:mm:ss");
    private List<FormField> fields;
    private List<ListPageMeta> pageMetas; 
    private List<AddFormPageMeta> addFormPageMetas; 
    private int formType= 1;
    private String code;
    /**
     * 主键生成策略
     */
    private String pkGeneratorPolicy;

    @Column(name="tablename")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    @Column(name="form_name")
    public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
    @Transient
    public List<FormField> getFields() {
        return fields;
    }
	public void setFields(List<FormField> fields) {
        this.fields = fields;
    }
	@Transient
    public List<ListPageMeta> getPageMetas() {
		return pageMetas;
	}

	public void setPageMetas(List<ListPageMeta> pageMetas) {
		this.pageMetas = pageMetas;
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

    @Column(name="form_type")
    public int getFormType() {
        return formType;
    }

    public void setFormType(int formType) {
        this.formType = formType;
    }
    @Column(name="pk_generator_policy")
    public String getPkGeneratorPolicy() {
        return pkGeneratorPolicy;
    }

    public void setPkGeneratorPolicy(String pkGeneratorPolicy) {
        this.pkGeneratorPolicy = pkGeneratorPolicy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Transient
	public List<AddFormPageMeta> getAddFormPageMetas() {
		return addFormPageMetas;
	}

	public void setAddFormPageMetas(List<AddFormPageMeta> addFormPageMetas) {
		this.addFormPageMetas = addFormPageMetas;
	}
    
}
