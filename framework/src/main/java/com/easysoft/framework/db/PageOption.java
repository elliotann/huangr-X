package com.easysoft.framework.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 * @author andy
 */
@SuppressWarnings("serial")
public class PageOption implements Serializable {

	private static int DEFAULT_PAGE_SIZE = 10;

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
	private int rows = pageSize;
	
	
	private long start; // 当前页第一条数据在List中的位置,从0开始

	private Object data; // 当前页中存放的记录,类型一般为List

    private Map<String,Object> searchConditions = new HashMap<String,Object>();//条件集合
	private long totalCount; // 总记录数

    private int currentPageNo=1;//当前页
    private int page = currentPageNo;

	/**
	 * 构造方法，只构造空页.
	 */
	public PageOption() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public PageOption(long start, long totalSize, int pageSize, Object data) {
		setParam(start, totalSize, pageSize, data);
	}
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }
	public void setParam(long start, long totalSize, int pageSize, Object data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
	}

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    /**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public Object getResult() {
		return data;
	}

    public void setData(Object data) {
        this.data = data;
    }

    /**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * @see #getStartOfPage(int,int)
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public void addSearch(String key,Object value){
        searchConditions.put(key,value);
    }

    public Map<String, Object> getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(Map<String, Object> searchConditions) {
        this.searchConditions = searchConditions;
    }

    public int getStartRecord(){
        return (currentPageNo-1)*pageSize;
    }

	public int getRows() {
		return pageSize;
	}

	public void setRows(int rows) {
		this.pageSize = rows;
	}

	public int getPage() {
		return currentPageNo;
	}

	public void setPage(int page) {
		this.currentPageNo = page;
	}
    
}