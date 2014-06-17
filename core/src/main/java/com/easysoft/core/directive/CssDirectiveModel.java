package com.easysoft.core.directive;

import com.easysoft.framework.resource.Resource;
import com.easysoft.core.resource.impl.ResourceBuilder;
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
 * Time: 上午9:32
 *
 * @since:
 */
public class CssDirectiveModel extends AbstractResourceDirectiveModel
        implements TemplateDirectiveModel
{
    public void execute(Environment env, Map params, TemplateModel[] arg2, TemplateDirectiveBody arg3)
            throws TemplateException, IOException
    {
        Resource resource = createResource(params);
        resource.setType("css");
        ResourceBuilder.putCss(resource);
    }
}
