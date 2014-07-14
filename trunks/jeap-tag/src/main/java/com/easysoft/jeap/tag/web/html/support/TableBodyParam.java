package com.easysoft.jeap.tag.web.html.support;


import com.easysoft.jeap.tag.web.IListTaglibParam;

public class TableBodyParam implements IListTaglibParam {
	private String items;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}