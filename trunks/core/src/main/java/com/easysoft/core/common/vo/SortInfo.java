package com.easysoft.core.common.vo;

/**
 * User: andy
 * Date: 14-1-14
 * Time: 上午9:12
 *
 * @since:
 */
public class SortInfo
{
    private Integer columnId;
    private SortDirection sortOrder;

    public Integer getColumnId()
    {
        return this.columnId;
    }

    public void setColumnId(Integer columnId)
    {
        this.columnId = columnId;
    }

    public SortDirection getSortOrder()
    {
        return this.sortOrder;
    }

    public void setSortOrder(SortDirection sortOrder)
    {
        this.sortOrder = sortOrder;
    }
}