package com.easysoft.core.directive;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.resource.Resource;
import com.easysoft.core.resource.impl.ResourceBuilder;
import com.easysoft.core.utils.UploadUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * User: andy
 * Date: 13-8-8
 * Time: 上午9:16
 *
 * @since:
 */
public class ResourceDirectiveModel extends AbstractResourceDirectiveModel
        implements TemplateDirectiveModel
{
    public void execute(Environment env, Map params, TemplateModel[] arg2, TemplateDirectiveBody arg3)
            throws TemplateException, IOException
    {
        String type = getValue(params, "type");

        if (StringUtils.isEmpty(type)) type = "javascript";

        if (type.equals("script")) type = "javascript";

        Resource resource = createResource(params);
        resource.setType(type);

        if ("javascript".equals(type)) {
            ResourceBuilder.putScript(resource);
        }

        if ("css".equals(type)) {
            ResourceBuilder.putCss(resource);
        }

        if ("image".equals(type)) {
            String src = params.get("src").toString();
            String postfix = getValue(params, "postfix");
            String imageurl = getImageUrl(src, postfix);
            env.getOut().write(imageurl);
        }
    }

    private String getImageUrl(String pic, String postfix)
    {
        if (StringUtils.isEmpty(pic))
            pic = ParamSetting.DEFAULT_IMG_URL;
        if (pic.toUpperCase().startsWith("HTTP"))
            return pic;
        if (pic.startsWith("fs:")) {
            pic = UploadUtil.replacePath(pic);
        }
        else {
            EsfContext ectx = EsfContext.getContext();

            if (!pic.startsWith("/")) {
                pic = "/" + pic;
            }

            if (("2".equals(ParamSetting.RESOURCEMODE)) || (ParamSetting.DEVELOPMENT_MODEL))
            {
                Site site = ectx.getCurrentSite();

                pic = ectx.getResDomain() + "/themes/" + site.getThemepath() + pic;
            }
            else
            {
                pic = ectx.getResDomain() + pic;
            }

        }

        if (StringUtils.isNotEmpty(postfix)) {
            return UploadUtil.getThumbPath(pic, postfix);
        }
        return pic;
    }
}
