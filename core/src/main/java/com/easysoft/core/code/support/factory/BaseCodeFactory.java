package com.easysoft.core.code.support.factory;

import com.easysoft.core.utils.CodeResourceUtil;
import freemarker.template.Configuration;

import java.io.IOException;
import java.util.Locale;

/**
 * User: andy
 * Date: 14-4-2
 * Time: 下午4:40
 *
 * @since:
 */
public class BaseCodeFactory {
    public Configuration getConfiguration()
            throws IOException
    {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(getClass(), CodeResourceUtil.FREEMARKER_CLASSPATH);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }
}
