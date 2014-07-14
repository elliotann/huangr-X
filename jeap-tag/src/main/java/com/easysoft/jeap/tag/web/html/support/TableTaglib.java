package com.easysoft.jeap.tag.web.html.support;

import com.easysoft.jeap.tag.web.HtmlTaglib;

/**
 * Created by huangxa on 2014/7/14.
 */
public class TableTaglib extends HtmlTaglib {
    private boolean isPage = true;//是否分页
    private String items;//数据源
    @Override
    protected String postStart() {
        StringBuilder sb = new StringBuilder("<div class=\"dataTables_wrapper\" id=\"example_wrapper\"><div id=\"example_length\" class=\"dataTables_length\">");
        //分页
        if(isPage){
            sb.append(buildPageSizeHtml());
        }
        sb.append("</div>");
        sb.append("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" id=\"example\" class=\"sTable2\">");
        return sb.toString();
    }

    @Override
    protected String postEnd() {
        StringBuilder sb = new StringBuilder("</table>");
        //分页
        if(isPage){
            sb.append(buildPageHtml());
        }
        sb.append("</div>");
        return sb.toString();
    }

    public boolean isPage() {
        return isPage;
    }

    public void setPage(boolean isPage) {
        this.isPage = isPage;
    }

    private String buildPageSizeHtml(){
        StringBuilder sb = new StringBuilder("<label>每页显示 <select name=\"example_length\" size=\"1\">");
        sb.append("<option value=\"10\" selected=\"selected\">10</option>");
        sb.append("<option value=\"20\">20</option>");
        sb.append("<option value=\"50\">50</option>");
        sb.append("</select>");
        sb.append("条</label>");
        return sb.toString();
    }

    private String buildPageHtml(){
        StringBuilder sb = new StringBuilder("<div class=\"dataTables_info\" id=\"example_info\">");
        sb.append("显示 ${pageOption.pageSize*(pageOption.currentPageNo-1)+1} 至");
        sb.append("<c:if test=\"${pageOption.totalCount<pageOption.pageSize}\">");
        sb.append("${pageOption.totalCount}");
        sb.append("</c:if>");
        sb.append("<c:if test=\"${pageOption.totalCount>=pageOption.pageSize}\">");
        sb.append("${pageOption.pageSize}");
        sb.append("</c:if>");
        sb.append("总共 ${pageOption.totalCount} 条");
        sb.append("</div>");
        return sb.toString();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
