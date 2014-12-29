package com.easysoft.core.resource.impl;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.model.Site;
import com.easysoft.core.resource.ResourceStateManager;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.resource.Resource;
import com.easysoft.framework.resource.impl.SystemOutErrorReporter;
import com.easysoft.framework.utils.FileUtil;
import com.easysoft.framework.utils.StringUtil;
import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mozilla.javascript.EvaluatorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: andy
 * Date: 13-8-8
 * Time: 上午9:18
 *
 * @since:
 */
public class ResourceBuilder {
    protected static final Logger logger = Logger.getLogger(ResourceBuilder.class);

    private static Map<String, String> scriptMap = new HashMap<String,String>();
    private static Map<String, String> cssMap = new HashMap();
    private static Map<String, String> imageMap = new HashMap();

    private static Map<String, String> commonScriptStateMap = new HashMap();
    private static Map<String, String> commonCssStateMap = new HashMap();

    private static boolean imageCreated = false;
    private static boolean commonScriptCreated = false;
    private static boolean commonCssCreated = false;

    public static String getScript(String tplFileName)
    {
        return (String)scriptMap.get(tplFileName);
    }

    public static String getCss(String tplFileName)
    {
        return (String)cssMap.get(tplFileName);
    }

    public static void reCreate(String pageid, String tplFileName)
            throws EvaluatorException, IOException
    {
        if ((ParamSetting.DEVELOPMENT_MODEL) || ("2".equals(ParamSetting.RESOURCEMODE)))
        {
            String scriptContent = null;
            scriptContent = getScriptFromCache(pageid);

            if ((StringUtils.isEmpty(scriptContent)) || (ParamSetting.DEVELOPMENT_MODEL))
            {
                StringBuffer content = new StringBuffer();
                content.append(readFromDisk("headerScriptList"));
                content.append(readFromDisk("headerScriptCommonList"));

                scriptMap.put(pageid, content.toString());
            }

            String cssContent = getCssFromCache(pageid);

            if ((StringUtil.isEmpty(cssContent)) || (ParamSetting.DEVELOPMENT_MODEL))
            {
                StringBuffer content = new StringBuffer();
                content.append(readFromDisk("headerCssList"));
                content.append(readFromDisk("headerCssCommonList"));

                cssMap.put(pageid, content.toString());
            }

        }
        else
        {
            createCommonScript();
            createCommonCss();

            if ((!isScriptCreated(pageid)) || (!isCssCreated(pageid)) || (!isImageCreated()) || (ResourceStateManager.getHaveNewDisploy()))
            {
                dispatchRes(pageid, tplFileName);

                if (ResourceStateManager.getHaveNewDisploy())
                    ResourceStateManager.setDisplayState(0);
            }
        }
    }

    private static void createCommonScript()
            throws EvaluatorException, IOException
    {
        if ((!isCommonScriptCreated()) || (ResourceStateManager.getHaveNewDisploy()))
        {
            EsfContext ectx = EsfContext.getContext();
            String target = ectx.getResPath();
            String themepath = ectx.getCurrentSite().getThemepath();

            String scriptContent = readFromDisk("headerScriptCommonList");

            if (StringUtil.isEmpty(scriptContent)) {
                return;
            }

            String jspath = target + "/js/";
            FileUtil.createFolder(jspath);

            jspath = jspath + themepath + "_common.js";

            if (logger.isDebugEnabled()) {
                logger.debug(" create common script to->[" + jspath + "]");
            }

            FileUtil.write(jspath, scriptContent);

            setCommonScriptCreated();
        }
    }

    private static void createCommonCss()
            throws EvaluatorException, IOException
    {
        if ((!isCommonCssCreated()) || (ResourceStateManager.getHaveNewDisploy())) {
            EsfContext ectx = EsfContext.getContext();
            String target = ectx.getResPath();
            String themepath = ectx.getCurrentSite().getThemepath();

            String cssContent = readFromDisk("headerCssCommonList");

            if (StringUtil.isEmpty(cssContent)) {
                return;
            }

            String csspath = target + "/css/";
            FileUtil.createFolder(csspath);

            csspath = csspath + themepath + "_common.css";

            if (logger.isDebugEnabled()) {
                logger.debug("create common css to->[" + csspath + "]");
            }

            FileUtil.write(csspath, cssContent);
            setCommonCssCreated();
        }
    }

    public static boolean haveScript()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        List scriptList = (List)request.getAttribute("headerScriptList");
        return (scriptList != null) && (!scriptList.isEmpty());
    }

    public static boolean haveCommonScript()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        List scriptList = (List)request.getAttribute("headerScriptCommonList");
        return (scriptList != null) && (!scriptList.isEmpty());
    }

    public static boolean haveCommonCss()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        List cssList = (List)request.getAttribute("headerCssCommonList");
        return (cssList != null) && (!cssList.isEmpty());
    }

    public static boolean haveCss()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        List cssList = (List)request.getAttribute("headerCssList");
        return (cssList != null) && (!cssList.isEmpty());
    }

    private static String getScriptFromCache(String pageid)
    {
        String key = getKey(pageid);
        String scriptContent = scriptMap.get(key);

        return scriptContent;
    }

    private static String getCssFromCache(String pageid)
    {
        String key = getKey(pageid);
        String scriptContent = cssMap.get(key);

        return scriptContent;
    }

    private static boolean isScriptCreated(String pageid)
    {
        String key = getKey(pageid);
        return scriptMap.get(key) != null;
    }

    private static boolean isCommonScriptCreated()
    {
       
        return commonScriptCreated;
    }

    private static boolean isCommonCssCreated()
    {
        
        return commonCssCreated;
    }

    private static boolean isCssCreated(String pageid)
    {
        String key = getKey(pageid);
        return cssMap.get(key) != null;
    }

    private static boolean isImageCreated()
    {
        
        return imageCreated;
    }

    private static void setScriptCreated(String pageid)
    {
        String key = getKey(pageid);
        scriptMap.put(key, "created");
    }

    private static void setCommonScriptCreated()
    {
        
            commonScriptCreated = true;
        
    }

    private static void setCommonCssCreated()
    {
        
            commonCssCreated = true;
        
    }

    private static void setCssCreated(String pageid)
    {
        String key = getKey(pageid);
        cssMap.put(key, "created");
    }

    private static void setImageCreated(String pageid)
    {
        
            imageCreated = true;
        
    }

    private static String getKey(String pageid)
    {
        String key = pageid;
        Site site = EsfContext.getContext().getCurrentSite();

      

        return key;
    }

    private static void dispatchRes(String pageid, String tplFileName)
            throws EvaluatorException, IOException
    {
        EsfContext ectx = EsfContext.getContext();

        String target = ectx.getResPath();
        String themepath = ectx.getCurrentSite().getThemepath();

        boolean newDisploy = false;
        newDisploy = ResourceStateManager.getHaveNewDisploy();

        if ((haveScript()) && (
                (!isScriptCreated(pageid)) || (newDisploy))) {
            String scriptContent = readFromDisk("headerScriptList");

            String jspath = target + "/js/";
            FileUtil.createFolder(jspath);

            jspath = jspath + themepath + "_" + pageid + ".js";

            if (logger.isDebugEnabled()) {
                logger.debug("create script to -> [" + jspath + "]");
            }

            FileUtil.write(jspath, scriptContent);

            setScriptCreated(pageid);
        }

        if ((haveCss()) && (
                (!isCssCreated(tplFileName)) || (newDisploy))) {
            String cssContent = readFromDisk("headerCssList");
            String csspath = target + "/css/";
            FileUtil.createFolder(csspath);

            csspath = csspath + themepath + "_" + tplFileName + ".css";

            FileUtil.write(csspath, cssContent);

            if (logger.isDebugEnabled()) {
                logger.debug("create css to -> [" + csspath + "]");
            }

            setCssCreated(tplFileName);
        }

        if ((!isImageCreated()) || (newDisploy)) {
            String src = getResSourcePath() + "/themes/" + themepath + "/images/";
            String disk = target + "/images/";

            if (logger.isDebugEnabled()) {
                logger.debug("copy images to -> [" + disk + "]");
            }

            FileUtil.copyNewFile(src, disk);
            setImageCreated(pageid);
        }
    }

    public static String getResSourcePath()
    {
        String path = ParamSetting.ESF_PATH;

        if (path.endsWith("/")) path = path.substring(0, path.length() - 1);

        path = path + EsfContext.getContext().getContextPath();
        return path;
    }

    public static String getNotMergeResource()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        EsfContext ectx = EsfContext.getContext();
        String domain = ectx.getResDomain();

        List<Resource> scriptList = (List)request.getAttribute("headerScriptList");
        StringBuffer sb = new StringBuffer();

        if (scriptList != null) {
            for (Resource script : scriptList) {
                if (script.getMerge() == 0) {
                    String src = script.getSrc();
                    if (!src.startsWith("/")) {
                        src = "/" + src;
                    }
                    String path = domain + "/themes/" + ectx.getCurrentSite().getThemepath() + src;
                    sb.append("<script src=\"" + path + "\"  type=\"text/javascript\"></script>");
                }
            }
        }

        scriptList = (List)request.getAttribute("headerCssList");
        if (scriptList != null) {
            for (Resource script : scriptList) {
                if (script.getMerge() == 0) {
                    String src = script.getSrc();
                    if (!src.startsWith("/")) {
                        src = "/" + src;
                    }
                    String path = domain + "/themes/" + ectx.getCurrentSite().getThemepath() + src;
                    sb.append("<link  href=\"" + path + "\"  rel=\"stylesheet\" type=\"text/css\" />");
                }
            }
        }

        return sb.toString();
    }

    private static String readresource(Resource resource)
            throws EvaluatorException, IOException
    {
        StringWriter result = new StringWriter();
        String src = resource.getSrc();
        if (!src.startsWith("/")) src = "/" + src;
        EsfContext ctx = EsfContext.getContext();
        String respath = getResSourcePath();
        src = respath + "/themes/" + ctx.getCurrentSite().getThemepath() + src;

        String content = FileUtil.read(src, "UTF-8");

        if ((ParamSetting.SCRIPT_COMPRESS) && (resource.getCompress() == 1)) {
            SystemOutErrorReporter reporter = new SystemOutErrorReporter();
            if ("javascript".equals(resource.getType()))
            {
                JavaScriptCompressor compressor = new JavaScriptCompressor(new StringReader(content), reporter);
                compressor.compress(result, -1, true, false, false, false);

                return result.toString();
            }

            if ("css".equals(resource.getType()))
            {
                CssCompressor compressor = new CssCompressor(new StringReader(content));
                compressor.compress(result, -1);

                return result.toString();
            }

        }

        return content;
    }

    private static String readFromDisk(String type)
            throws EvaluatorException, IOException
    {
        StringBuffer sb = new StringBuffer();
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        List<Resource> scriptList = (List)request.getAttribute(type);
        if (scriptList != null) {
            for (Resource script : scriptList) {
                String src = script.getSrc();
                if (script.getMerge() == 1)
                {
                    sb.append(readresource(script));
                }
            }

        }

        return sb.toString();
    }

    public static void putScript(Resource resource)
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        if (resource.isIscommon()) {
            List scriptList = (List)request.getAttribute("headerScriptCommonList");
            scriptList = scriptList == null ? new ArrayList() : scriptList;
            scriptList.add(resource);
            request.setAttribute("headerScriptCommonList", scriptList);
        } else {
            List scriptList = (List)request.getAttribute("headerScriptList");
            scriptList = scriptList == null ? new ArrayList() : scriptList;
            scriptList.add(resource);
            request.setAttribute("headerScriptList", scriptList);
        }
    }

    public static void putCss(Resource resource) {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        if (resource.isIscommon()) {
            List cssList = (List)request.getAttribute("headerCssCommonList");
            cssList = cssList == null ? new ArrayList() : cssList;
            cssList.add(resource);
            request.setAttribute("headerCssCommonList", cssList);
        } else {
            List cssList = (List)request.getAttribute("headerCssList");
            cssList = cssList == null ? new ArrayList() : cssList;
            cssList.add(resource);
            request.setAttribute("headerCssList", cssList);
        }
    }

    public static void main(String[] args)
            throws IOException
    {
        String content = FileUtil.read("d:/style.css", "UTF-8");
        StringWriter result = new StringWriter();
        SystemOutErrorReporter reporter = new SystemOutErrorReporter();
        CssCompressor compressor = new CssCompressor(new StringReader(content));
        compressor.compress(result, -1);
        System.out.println(result.toString());
    }
}
