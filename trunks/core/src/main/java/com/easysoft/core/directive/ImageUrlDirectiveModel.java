package com.easysoft.core.directive;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
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
 * Time: 上午9:34
 *
 * @since:
 */
public class ImageUrlDirectiveModel implements TemplateDirectiveModel
{
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException
    {
        String pic = params.get("pic").toString();
        String postfix = null;
        if (params.get("postfix") != null) {
            postfix = params.get("postfix").toString();
        }
        pic = getImageUrl(pic, postfix);
        env.getOut().write(pic);
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
