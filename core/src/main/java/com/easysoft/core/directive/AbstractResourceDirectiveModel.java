package com.easysoft.core.directive;

import com.easysoft.core.ParamSetting;
import com.easysoft.framework.resource.Resource;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * User: andy
 * Date: 13-8-7
 * Time: 下午5:26
 *
 * @since:
 */
public abstract class AbstractResourceDirectiveModel {
    protected Resource createResource(Map params)
    {
        String src = params.get("src").toString();
        String compress = getValue(params, "compress");
        String merge = getValue(params, "merge");

        String iscommon = getValue(params, "iscommon");

        if (StringUtils.isEmpty(merge)) merge = "true";
        if (StringUtils.isEmpty(compress)) compress = "true";

        Resource resource = new Resource();
        resource.setSrc(src);
        resource.setMerge("true".equals(merge) ? 1 : 0);
        resource.setCompress("true".equals(compress) ? 1 : 0);

        resource.setIscommon("true".equals(iscommon));

        if (ParamSetting.DEVELOPMENT_MODEL) {
            resource.setMerge(0);
            resource.setIscommon(false);
        }

        return resource;
    }

    protected String getValue(Map params, String name)
    {
        Object value_obj = params.get(name);
        if (value_obj == null) {
            return null;
        }

        return value_obj.toString();
    }
}
