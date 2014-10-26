package com.easysoft.framework.db.dbsolution.impl;

import org.dom4j.Element;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

/**
 * SQLServer数据库导入导出
 * 
 * @author andy
 * 
 */
@Component("sqlserverSolution")
public class SQLServerSolution extends DBSolution {
    public String getDropSQL(String table)
/*     */   {
/* 128 */     String sql = new StringBuilder().append("if exists (select 1 from sysobjects where id = object_id('").append(table).append("')").append("and type = 'U') drop table ").append(table).toString();
/*     */
/* 133 */     return sql;
/*     */   }
    /*     */
/*     */   public String getSaasCreateSQL(Element action, int userid, int siteid)
/*     */   {
/* 138 */     String table = getSaasTableName(action.elementText("table"), userid, siteid);
/* 139 */     List fields = action.elements("field");
/* 140 */     String sql = new StringBuilder().append(getDropSQL(table)).append("!-->").toString();
/* 141 */     sql = new StringBuilder().append(sql).append("create table ").append(table).append(" (").toString();
/*     */
/* 143 */     String pk = "";
/* 144 */     for (int i = 0; i < fields.size(); i++) {
/* 145 */       String nl = "";
/* 146 */       Element field = (Element)fields.get(i);
/* 147 */       String name = new StringBuilder().append("[").append(field.elementText("name")).append("]").toString();
/* 148 */       String size = field.elementText("size");
/* 149 */       String type = toLocalType(field.elementText("type").toLowerCase(), size);
/*     */
/* 151 */       String option = field.elementText("option");
/* 152 */       String def = field.elementText("default");
/*     */
/* 154 */       if ("1".equals(option.substring(1, 2))) {
/* 155 */         nl = " not null";
/*     */       }
/* 157 */       if (def != null) {
/* 158 */         nl = new StringBuilder().append(nl).append(" default ").append(def).toString();
/*     */       }
/* 160 */       if ("1".equals(option.substring(0, 1))) {
/* 161 */         pk = new StringBuilder().append("constraint PK_").append(table.toUpperCase()).append(" primary key nonclustered (").append(name).append("),").toString();
/*     */
/* 163 */         nl = new StringBuilder().append(nl).append(" identity").toString();
/*     */       }
/*     */
/* 166 */       sql = new StringBuilder().append(sql).append(name).append(" ").append(type).append(nl).append(",").toString();
/*     */     }
/* 168 */     sql = new StringBuilder().append(sql).append(pk).toString();
/* 169 */     sql = new StringBuilder().append(sql.substring(0, sql.length() - 1)).append(")").toString();
/*     */
/* 171 */     return sql;
/*     */   }
    /**
	 * 解决id插入值问题开始
	 */
	private String currTable;

	private boolean setIdentity(String table, boolean on) {
		table = table + " " + (on ? "ON" : "OFF");
		try {
			Statement stat = conn.createStatement();
			stat.execute("SET IDENTITY_INSERT " + table);
			stat.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	protected void beforeImport() {
		currTable = null;
	}

	@Override
	protected boolean beforeInsert(String table, String fields, String values) {
		if (!table.equals(currTable)) {
			if (currTable != null)
				setIdentity(currTable, false);
			currTable = table;
			setIdentity(table, true);
		}
		return true;
	}

	@Override
	protected void afterImport() {
		if (currTable != null)
			setIdentity(currTable, false);
	}

	/**
	 * 解决id插入值问题结束
	 */


	/**
	 * 返回与当前类匹配的类型名称
	 * 
	 * @param type
	 * @return
	 */
	public String toLocalType(String type, String size) {
		if ("int".equals(type)) {
			if ("1".equals(size))
				return "smallint";
			else
				return "int";
		}

		if ("memo".equals(type))
			return "text";

		if ("datetime".equals(type))
			return "datetime";

		if ("long".equals(type))
			return "bigint";

		return type + "(" + size + ")";
	}



    @Override
	public String getCreateSQL(Element action) {
		String table = getTableName(action.elementText("table"));
		List<Element> fields = action.elements("field");

		String sql = "if exists (select 1 from sysobjects where id = object_id('"
				+ table
				+ "')"
				+ "and type = 'U') drop table "
				+ table
				+ EXECUTECHAR;
		sql = sql + "create table " + table + " (";

		String pk = "";
		for (int i = 0; i < fields.size(); i++) {
			String nl = "";
			Element field = fields.get(i);
			String name = field.elementText("name");
			String size = field.elementText("size");
			String type = toLocalType(field.elementText("type").toLowerCase(),
					size);
			String option = field.elementText("option");
			String def = field.elementText("default");

			if ("1".equals(option.substring(1, 2))) // 如果第二位为1，不允许空值
				nl = " not null";

			if (def != null)
				nl = nl + " default " + def;

			if ("1".equals(option.substring(0, 1))) { // 如果第一位为1，则为主键
				pk = "constraint PK_" + table.toUpperCase()
						+ " primary key nonclustered (" + name + "),";
				nl = nl + " identity";
			}

			sql = sql + name + " " + type + nl + ",";
		}
		sql = sql + pk;
		sql = sql.substring(0, sql.length() - 1) + ")";

		return sql;
	}



    /**
	 * 由基类调用的多态函数，返回当前类所要捕获的自定义function列表，与getFuncValue配合使用
	 */
	@Override
	public String[] getFuncName() {
		String[] name = { "time" };
		return name;
	}

	@Override
	public String getFieldValue(int fieldType, Object fieldValue) {
		if (Types.TIMESTAMP == fieldType) {
			Timestamp value = (Timestamp) fieldValue;
			return "time(" + value.getTime() + ")";
		} else
			return super.getFieldValue(fieldType, fieldValue);

	}

	@Override
	public String getDeleteSQL(String table) {
		String sql = "";
		return sql;
	}

    @Override
    public boolean dbSaasImport(String paramString, int paramInt1, int paramInt2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public String dbSaasExport(String[] tables, boolean dataOnly, int userid, int siteid) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
