package com.easysoft.core.code.pojo;

import com.easysoft.component.form.model.FormEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @description:代码生成实体
 * @author andy
 * @since : 1.0.0
 */
public class GenerateEntity implements java.io.Serializable{
	private static final long serialVersionUID = 7821940124097563556L;
	private String packageName;// 包名（小写）
	private String entityName;// 实体名（首字母大写）
	private String tableName; // 表名
	private String tableTitle;// 功能描述
	private String primaryKeyPolicy = "uuid";// 主键生成策略
	private String[] foreignKeys;// 子表：外键(中间逗号隔开)
	private Integer fieldRowNum;// 一行放几个字段
	private String projectPath;//工程路径
	/*
	 * --------------智能表单配置
	 */
    private FormEntity formEntity;
	/**
	 * 按钮SQL增强配置
	 */
	private Map<String,String[]> buttonSqlMap;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    public String getPrimaryKeyPolicy() {
		return primaryKeyPolicy;
	}

	public void setPrimaryKeyPolicy(String primaryKeyPolicy) {
		this.primaryKeyPolicy = primaryKeyPolicy;
	}

	public String[] getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(String[] foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public Integer getFieldRowNum() {
		return fieldRowNum;
	}

	public void setFieldRowNum(Integer fieldRowNum) {
		this.fieldRowNum = fieldRowNum;
	}


	

	public Map<String, String[]> getButtonSqlMap() {
		return buttonSqlMap;
	}

	public void setButtonSqlMap(Map<String, String[]> buttonSqlMap) {
		this.buttonSqlMap = buttonSqlMap;
	}



	public String getProjectPath() {
		String pt = projectPath;
		if(pt!=null){
			pt = pt.replace("\\", "/");
			if(!pt.endsWith("/")){
				pt = pt+"/";
			}
		}
		return pt;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	/**
	 * 深度复制
	 * @return
	 * @throws Exception
	 */
	public GenerateEntity deepCopy() throws Exception{  
        //将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(this);  
  
        //将流序列化成对象  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis);  
        return (GenerateEntity) ois.readObject();  
    }

    public FormEntity getFormEntity() {
        return formEntity;
    }

    public void setFormEntity(FormEntity formEntity) {
        this.formEntity = formEntity;
    }
}
