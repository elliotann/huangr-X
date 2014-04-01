package com.easysoft.core.directive;

import com.easysoft.framework.utils.DateUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * User: andy
 * Date: 13-8-8
 * Time: 上午9:15
 *
 * @since:
 */
public class DateformateDirective
        implements TemplateDirectiveModel
{
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException
    {
        String timeStr = params.get("time").toString();
        String pattern = params.get("pattern").toString();
        long time = Long.valueOf(timeStr).longValue();
        if (time > 0L)
        {
            Date date = new Date(time);
            String str = DateUtil.toString(date, pattern);
            env.getOut().write(str);
        } else {
            env.getOut().write("");
        }
    }
}
