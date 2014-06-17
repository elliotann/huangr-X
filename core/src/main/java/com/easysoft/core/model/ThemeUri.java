package com.easysoft.core.model;

import com.easysoft.framework.db.NotDbField;

import java.util.regex.Pattern;

/**
 * @author andy
 */
public class ThemeUri extends Resource {
    private Integer themeid;
    private String uri;
    private String path;
    private String pagename;
    private int point;
    private int sitemaptype;
    private String keywords;
    private String description;
    private int httpcache;
    private Pattern pattern;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getThemeid() {
        return themeid;
    }

    public void setThemeid(Integer themeid) {
        this.themeid = themeid;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getSitemaptype() {
        return sitemaptype;
    }

    public void setSitemaptype(int sitemaptype) {
        this.sitemaptype = sitemaptype;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotDbField
    public Pattern getPattern() {
        if (this.pattern == null) {
            this.pattern = Pattern.compile("^" + this.uri + "$", 34);
        }
        return this.pattern;
    }

    public int getHttpcache() {
        return httpcache;
    }

    public void setHttpcache(int httpcache) {
        this.httpcache = httpcache;
    }
}