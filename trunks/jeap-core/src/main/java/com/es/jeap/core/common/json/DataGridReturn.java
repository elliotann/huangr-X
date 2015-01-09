package com.es.jeap.core.common.json;

import java.util.List;

/**
 * 后台向前台返回JSON，用于ligerui的datagrid
 * 
 * @author 
 * 
 */
public class DataGridReturn {

	public DataGridReturn(long total, List rows) {
		this.total = total;
		this.rows = rows;
	}

	private long total;// 总记录数
	private List rows;// 每行记录
	private List footer;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
