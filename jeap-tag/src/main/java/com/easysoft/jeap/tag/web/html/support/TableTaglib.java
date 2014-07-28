package com.easysoft.jeap.tag.web.html.support;

import com.easysoft.jeap.framework.db.PageOption;
import com.easysoft.jeap.tag.web.HtmlTaglib;

/**
 * Created by huangxa on 2014/7/14.
 */
public class TableTaglib extends HtmlTaglib {
    private boolean isPage = true;//是否分页
    private String items;//数据源
    @Override
    protected String postStart() {
        StringBuilder sb = new StringBuilder();
        //分页
        /*if(isPage){
            sb.append(buildPageSizeHtml());
        }
        sb.append("</div>");*/
        sb.append("<table id=\"smpl_tbl\" class=\"table table-bordered table-striped\">");
        return sb.toString();
    }

    @Override
    protected String postEnd() {
        StringBuilder sb = new StringBuilder("</table>");
        //分页
        if(isPage){
            sb.append(buildPageHtml());
        }

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
        Object obj = this.pageContext.getAttribute(items);
        PageOption page = null;
        if(obj==null){
            obj = this.pageContext.getRequest().getAttribute(items);
            if(obj == null){
                return "";
            }

            if(obj instanceof PageOption){
                page = (PageOption)obj;
            }
        }
        StringBuilder sb = new StringBuilder("<div>");
        sb.append("<div class=\"pagedetail\">");
        sb.append("显示 "+ (page.getPageSize()*(page.getCurrentPageNo()-1)+1)+" 至");
        if(page.getTotalCount()<page.getPageSize()){
            sb.append(page.getTotalCount());
        }else if(page.getTotalCount()>=page.getPageSize()){
            sb.append(page.getPageSize());
        }


        sb.append("总共 "+page.getTotalCount()+" 条");
        sb.append("</div>");
        sb.append("<div class=\"dataTables_paginate paging_bootstrap\">");
        sb.append("<ul class=\"pagination pagination-sm\">");
        if(page.getCurrentPageNo()<=1){
            sb.append(" <li class=\"prev disabled\"><a href=\"#\">上一页</a></li>");
        }else{
            sb.append(" <li class=\"prev\"><a href=\"#\" onclick=\"goPage("+(page.getCurrentPageNo()-1)+")\">上一页</a></li>");
        }
        for(int i=0;i<page.getTotalPage();i++){
            if(page.getCurrentPageNo()==(i+1)){
                sb.append(" <li class=\"active\"><a href=\"#\" onclick=\"goPage(\"+(i+1)+\")\">"+(i+1)+"</a></li>");
            }else{
                sb.append(" <li><a href=\"#\" onclick=\"goPage("+(i+1)+")\">"+(i+1)+"</a></li>");
            }
        }
        if(page.getCurrentPageNo()>=page.getTotalPage()){
            sb.append("<li class=\"next disabled\"><a href=\"#\">下一页</a></li>");
        }else{
            sb.append("<li class=\"xt\"><a href=\"#\" onclick=\"goPage("+(page.getCurrentPageNo()+1)+")\">下一页</a></li>");
        }

        sb.append("</ul>");
        sb.append("</div>");
        sb.append("</div>");

        /*sb.append("</div>");
        sb.append(" <div class=\"dataTables_paginate paging_full_numbers\" id=\"example_paginate\">");
        if(page.getCurrentPageNo()<=1){
            sb.append("<span class=\"first paginate_button paginate_button_disabled\" id=\"example_first\">首页</span>");
            sb.append("<span class=\"previous paginate_button paginate_button_disabled\" id=\"example_previous\">上一页</span>");
        }else{
            sb.append("<span class=\"first paginate_button\" id=\"example_first\" onclick=\"goPage(1)\">首页</span>");
            sb.append("<span class=\"previous paginate_button\" id=\"example_previous\" onclick=\"goPage("+(page.getCurrentPageNo()-1)+")\">上一页</span>");
        }
        sb.append("<span>");
        for(int i=0;i<page.getTotalPage();i++){
            if(page.getCurrentPageNo()==(i+1)){
                sb.append(" <span class=\"paginate_active\" onclick=\"goPage("+(i+1)+")\">"+(i+1)+"</span>");
            }else{
                sb.append("<span class=\"paginate_button\" onclick=\"goPage("+(i+1)+")\">"+(i+1)+"</span>");
            }
        }
        sb.append("</span>");
        if(page.getCurrentPageNo()>=page.getTotalPage()){
            sb.append("<span class=\"next paginate_button paginate_button_disabled\"  id=\"example_next\">下一页</span>");
            sb.append("<span class=\"last paginate_button paginate_button_disabled\" id=\"example_last\">尾页</span>");
        }else{


            sb.append("<span class=\"next paginate_button\"  id=\"example_next\" onclick=\"goPage("+(page.getCurrentPageNo()+1)+")\">下一页</span>");
            sb.append("<span class=\"last paginate_button\" id=\"example_last\" onclick=\"goPage("+page.getTotalPage()+")\">尾页</span>");
        }
        sb.append("</div>");*/

        sb.append("<script>");
        sb.append("function goPage(currentPage){");
        sb.append("location.href = \"list.do?currentPageNo=\"+currentPage;");
        sb.append("}");
        sb.append("</script>");
        return sb.toString();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}