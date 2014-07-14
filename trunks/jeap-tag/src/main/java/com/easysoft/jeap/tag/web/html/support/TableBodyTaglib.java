package com.easysoft.jeap.tag.web.html.support;

import java.awt.*;

/**
 * Created by huangxa on 2014/7/14.
 */
public class TableBodyTaglib extends ListTaglibSupport {
    @Override
    protected void loadProvider() {

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
