package com.easysoft.jeap.tag.web.html.support;

import javax.servlet.jsp.tagext.Tag;
import java.awt.*;

/**
 * Created by huangxa on 2014/7/14.
 */
public class TableBodyTaglib extends ListTaglibSupport {
    private TableBodyProvider tableBodyProvider;
    public TableBodyTaglib(){
        this.tableBodyProvider = new TableBodyProvider();
    }
    @Override
    protected void loadProvider() {
        Tag tag = this.getParent();
        if(tag!=null){
            TableTaglib tableTaglib = (TableTaglib)tag;
            TableBodyParam bodyparam = new TableBodyParam();
            bodyparam.setItems(tableTaglib.getItems());
            this.param = bodyparam;
            this.tagProvider = this.tableBodyProvider;
        }
    }
    protected String postStart() {
        return "<tr>";
    }


    protected String postEnd() {
        return "</tr>";
    }


    protected String postStartOnce() {
        return "<tbody>";
    }


    protected String postEndOnce() {
        return "</tbody>";
    }
}
