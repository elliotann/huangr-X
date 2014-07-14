package com.easysoft.jeap.tag.web.html;

import com.easysoft.jeap.tag.web.HtmlTaglib;
import org.apache.commons.lang.StringUtils;


/**
 * Created by huangxa on 2014/7/14.
 */
public class TableTdTaglib extends HtmlTaglib {
    private String style;
    private String clazz = "head1 sorting";//样式
    private String rowspan = "1";
    private String colspan = "1";
    @Override
    protected String postStart() {
        StringBuilder sb = new StringBuilder();
        String parentName = this.getParent().getClass().getName();
        //th
        if(StringUtils.isNotEmpty(parentName)&&parentName.endsWith("TableHeaderTaglib")){
            sb.append("<th");
        }else{//td
            sb.append("<td");
        }
        sb.append(" class=\""+clazz+"\"");
        sb.append(" rowspan=\""+rowspan+"\"");
        sb.append(" colspan=\""+colspan+"\"");
        if(StringUtils.isNotEmpty(style)){
            sb.append(" style=\""+style+"\"");
        }

        sb.append(">");
        return sb.toString();
    }

    @Override
    protected String postEnd() {
        StringBuilder sb = new StringBuilder();
        String parentName = this.getParent().getClass().getName();
        //th
        if(StringUtils.isNotEmpty(parentName)&&"TableHeaderTaglib".equals(parentName)){
            sb.append("</th>");
        }else{//td
            sb.append("</td>");
        }
        return sb.toString();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getRowspan() {
        return rowspan;
    }

    public void setRowspan(String rowspan) {
        this.rowspan = rowspan;
    }

    public String getColspan() {
        return colspan;
    }

    public void setColspan(String colspan) {
        this.colspan = colspan;
    }
}
