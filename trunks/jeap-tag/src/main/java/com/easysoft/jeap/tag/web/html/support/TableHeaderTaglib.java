package com.easysoft.jeap.tag.web.html.support;

import com.easysoft.jeap.tag.web.HtmlTaglib;

/**
 * Created by huangxa on 2014/7/14.
 */
public class TableHeaderTaglib extends HtmlTaglib {
    @Override
    protected String postStart() {
        return "<thead><tr>";
    }

    @Override
    protected String postEnd() {
        return "</thead></tr>";
    }
}
