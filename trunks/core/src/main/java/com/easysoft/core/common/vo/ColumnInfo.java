package com.easysoft.core.common.vo;

/**
 * User: andy
 * Date: 14-1-14
 * Time: 上午9:17
 *
 * @since:
 */
public class ColumnInfo
{
    private String name;
    private Boolean regex;
    private Boolean searchable;
    private String search;
    private Boolean sortable;

    public String getSearch()
    {
        return this.search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getRegex()
    {
        return this.regex;
    }

    public void setRegex(Boolean regex)
    {
        this.regex = regex;
    }

    public Boolean getSearchable()
    {
        return this.searchable;
    }

    public void setSearchable(Boolean searchable)
    {
        this.searchable = searchable;
    }

    public Boolean getSortable()
    {
        return this.sortable;
    }

    public void setSortable(Boolean sortable)
    {
        this.sortable = sortable;
    }
}
