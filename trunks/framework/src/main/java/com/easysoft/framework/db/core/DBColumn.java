package com.easysoft.framework.db.core;

public class DBColumn {
	private String columnName;
	private String dataType;
	private String defaultValue;
	private String isPK;
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getIsPK() {
		return isPK;
	}
	public void setIsPK(String isPK) {
		this.isPK = isPK;
	}


}
