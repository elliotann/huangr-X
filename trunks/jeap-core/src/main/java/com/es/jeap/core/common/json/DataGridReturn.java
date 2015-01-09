package com.es.jeap.core.common.json;

import java.util.List;

/**
 * 后台向前台返回JSON，用于ligerui的datagrid
 * 
 * @author 
 * 
 */
public class DataGridReturn<T> {

	public DataGridReturn(long total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	private long total;// 总记录数
	private List<T> rows;// 每行记录


	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
