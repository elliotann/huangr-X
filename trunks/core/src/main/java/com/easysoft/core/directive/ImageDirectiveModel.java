package com.easysoft.core.directive;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.resource.Resource;
import com.easysoft.core.utils.UploadUtil;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * User: andy
 * Time: 下午5:25
 *
 * @since:
 */
public class ImageDirectiveModel extends AbstractResourceDirectiveModel implements TemplateDirectiveModel {
    public void execute(Environment env, Map params, TemplateModel[] arg2, TemplateDirectiveBody arg3)
            throws TemplateException, IOException
    {
        Resource resource = createResource(params);
        resource.setType("image");

        String src = params.get("src").toString();
        String postfix = getValue(params, "postfix");
        String imageurl = getImageUrl(src, postfix);
        StringBuffer html = new StringBuffer();

        html.append("<img");
        html.append(" src=\"" + imageurl + "\"");

        Set keySet = params.keySet();
        Iterator itor = keySet.iterator();

        while (itor.hasNext()) {
            String name = (String)itor.next();
            if (("src".equals(name)) ||
                    ("postfix".equals(name))) continue;
            String value = getValue(params, name);
            if (StringUtils.isNotEmpty(value)) {
                html.append(" " + name + "=\"" + value + "\"");
            }

        }

        html.append(" />");
        env.getOut().write(html.toString());
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
