package com.easysoft.jeap.tag.web.html.support;

import com.easysoft.jeap.framework.db.PageOption;
import com.easysoft.jeap.tag.web.IListTaglibParam;
import com.easysoft.jeap.tag.web.IListTaglibProvider;

import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2014/7/14.
 */
public class TableBodyProvider implements IListTaglibProvider {
    private static final List EMPTY_LIST = Collections.unmodifiableList(new ArrayList(0));
    @Override
    public List getData(IListTaglibParam param, PageContext pageContext) {
        TableBodyParam  bodyParam = (TableBodyParam)param;
        String items = bodyParam.getItems();
        Object obj = pageContext.getAttribute(items);
        if(obj==null){
            obj = pageContext.getRequest().getAttribute(items);
            if(obj==null){
                return EMPTY_LIST;
            }
        }
        if(obj instanceof List){
            return (List)obj;
        }else if(obj instanceof PageOption){
            PageOption pageOption = (PageOption)obj;
            return (List)pageOption.getData();
        }
        return EMPTY_LIST;
    }
}
