package com.easysoft.jeap.framework.db;

import java.io.Serializable;

/**
 * 分页对象
 * Created by huangxa on 2014/7/9.
 */
public class PageOption implements Serializable {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private int pageSize = DEFAULT_PAGE_SIZE;//每页显示条数
    private Object data; // 当前页中存放的记录,类型一般为List
    private long totalCount;//总记录数
    private long start; // 当前页第一条数据在List中的位置,从0开始
    public PageOption(){

    }

    public PageOption(long start,int pageSize,long totalCount,Object data){
        this.start = start;
        this.pageSize  = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public long getStart() {
        return start;
    }
}
