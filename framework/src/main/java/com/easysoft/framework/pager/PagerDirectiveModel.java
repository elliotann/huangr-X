package com.easysoft.framework.pager;

import com.easysoft.framework.pager.impl.SimplePageHtmlBuilder;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;

/**
 * User: andy
 * Date: 13-8-8
 * Time: 上午9:33
 *
 * @since:
 */
public class PagerDirectiveModel implements TemplateDirectiveModel
{
    public void execute(Environment env, Map params, TemplateModel[] arg2, TemplateDirectiveBody arg3)
            throws TemplateException, IOException
    {
        String pageno = params.get("pageno").toString();
        String pagesize = params.get("pagesize").toString();
        String totalcount = params.get("totalcount").toString();
        int _pageNum = Integer.valueOf(pageno).intValue();
        int _totalCount = Integer.valueOf(totalcount).intValue();
        int _pageSize = Integer.valueOf(pagesize).intValue();
        SimplePageHtmlBuilder pageHtmlBuilder = new SimplePageHtmlBuilder(_pageNum, _totalCount, _pageSize);
        String html = pageHtmlBuilder.buildPageHtml();
        env.getOut().write(html);
    }
}
