package com.easysoft.core.dispatcher.processor.factory;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.processor.backend.BackgroundProcessor;
import com.easysoft.core.dispatcher.processor.facade.*;
import com.easysoft.core.manager.IAppManager;
import com.easysoft.core.model.JEAPApp;
import com.easysoft.framework.utils.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author andy
 * @since : 1.0
 */
public abstract class ProcessorFactory {

	/**
	 * 
	 * @param uri
	 */
    public static Processor newProcessorInstance(String uri, HttpServletRequest request){
        Processor processor = null;

        if (uri.startsWith("/statics")) return null;
        if ((uri.startsWith("/install")) && (!uri.startsWith("/install.html"))) return null;

        if (uri.toLowerCase().equals("/sitemap.xml")) {
            return new SiteMapProcessor();
        }

        if (uri.toLowerCase().equals("/robots.txt")) return null;
        String ajax = request.getParameter("ajax");
        if("true".equals(ajax)) return null;
        IAppManager appManager = (IAppManager) SpringContextHolder.getBean("appManager");
        List<JEAPApp> appList = appManager.list();
        String path = request.getServletPath();
        for (JEAPApp app : appList) {
            if (app.getDeployment() == 1)
                continue;
            if (path.startsWith(app.getPath() + "/admin")) {
                if (isExinclude(path)) return null;

                processor = new BackgroundProcessor();
                return processor;
            }
            if (path.startsWith(app.getPath())) {
                return null;
            }
        }
        if(uri.contains("/designer/dialogs")){
            processor = new BackgroundProcessor();
            return processor;
        }
        if(uri.startsWith("/webpage/"))  return null;
        if (uri.startsWith("/validcode")) return null;
        if (uri.startsWith("/commons")) return null;
        if (uri.startsWith("/editor/")) return null;
        if (uri.startsWith("/eop/")) return null;
        if (uri.startsWith("/test/")) return null;
        if (uri.startsWith("/spring")) return null;
        if (uri.endsWith("favicon.ico")) return null;
        if(uri.startsWith("/detect")) return null;
        if(uri.endsWith("tracer")) return null;

        if (uri.indexOf("/headerresource") >= 0) {
            return new ResourceProcessor();
        }
        if (uri.startsWith("/resource/")) {
            return new WebResourceProcessor();
        }

        if (isExinclude(uri)) return null;

        if (uri.startsWith("/admin/")) {
            if (!uri.startsWith("/admin/themes/"))
                processor = new BackgroundProcessor();
        }
        else if (uri.startsWith("/widget"))
        {
            if (uri.startsWith("/widgetSetting/"))
                processor = new WidgetSettingProcessor();
            else if (!uri.startsWith("/widgetBundle/"))
            {
                processor = new WidgetProcessor();
            }
        }
        else {
            if ((uri.endsWith(".action")) || (uri.endsWith(".do"))) return null;
            if (ParamSetting.TEMPLATEENGINE.equals("on")) {
                processor = new FacadePageProcessor();
            }
        }
        return processor;
    }

    private static boolean isExinclude(String uri)
    {
        String[] exts = { "jpg", "gif", "js", "png", "css", "doc", "xls", "swf","json","txt","druid" };
        for (String ext : exts) {
            if (uri.toUpperCase().endsWith(ext.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

}