package com.easysoft.core.freemarker.utils;

import com.easysoft.core.directive.factory.DirectiveFactory;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import freemarker.cache.MruCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * freemarker工具
 * 
 * @author andy
 * @since : 1.0
 */
public class FreeMarkerUtil {
	private FreeMarkerUtil() {
	}

	private static Configuration cfg;

	/**
	 * 获取servlet上下文件的Configuration
	 * 
	 * @param pageFolder
	 * @return
	 */
	public static Configuration getServletCfg(String pageFolder) {
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(ThreadContextHolder
				.getHttpRequest().getSession().getServletContext(), pageFolder);
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		return cfg;
	}

	public static Configuration getCfg(){
        Configuration cfg = new Configuration();
        cfg.setTemplateUpdateDelay(6000);
        cfg.setCacheStorage(new MruCacheStorage(20, 250));

        Map directiveMap = DirectiveFactory.getCommonDirective();
        Iterator keyIter = directiveMap.keySet().iterator();

        while (keyIter.hasNext()) {
            String key = (String)keyIter.next();

            cfg.setSharedVariable(key, (TemplateModel)directiveMap.get(key));
        }

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.CHINA);
        cfg.setEncoding(Locale.CHINA, "UTF-8");

        return cfg;
	}
	
	public static Configuration getFolderCfg(String pageFolder)
			throws IOException {
		cfg =getCfg();
		cfg.setDirectoryForTemplateLoading(new File(pageFolder));
		return cfg;
	}
}
