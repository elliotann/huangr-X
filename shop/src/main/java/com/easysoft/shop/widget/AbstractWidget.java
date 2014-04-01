package com.easysoft.shop.widget;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.easysoft.core.common.dao.spring.BaseSupport;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.core.model.Site;
import com.easysoft.core.widget.IWidget;
import com.easysoft.core.widget.nav.Nav;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.framework.utils.RequestUtil;
import com.easysoft.framework.utils.StringUtil;
import com.easysoft.member.backend.manager.impl.UserServiceFactory;
import com.easysoft.member.manager.IMemberManager;
import com.easysoft.member.model.Member;

/**
 * 基于freemarker的挂件基类
 * @author andy
 * @since : 1.0
 */
public abstract class AbstractWidget extends BaseSupport
        implements IWidget {
    protected boolean showHtml = true;
    protected FreeMarkerParser freeMarkerParser;
    private Map<String, String> urls;
    protected String folder;
    protected boolean disableCustomPage = false;
    protected String action;
    protected String pageName;
    private Map<String, String> actionPageMap;
    private IMemberManager memberManager;
    public String process(Map<String, String> params)
    {
        this.actionPageMap = new HashMap();

        HttpServletRequest request = ThreadContextHolder.getHttpRequest();

        String mustbelogin = (String)params.get("mustbelogin");
        if ("yes".equals(mustbelogin)) {
            Member member = memberManager.getCurrentMember();
            if (member == null) {
                String forward = RequestUtil.getRequestUrl(request);
                if (StringUtils.isNotEmpty(forward)) {
                    try {
                        if (forward.startsWith("/")) forward = forward.substring(1, forward.length());
                        forward = URLEncoder.encode(forward, "UTF-8");
                    }
                    catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                HttpServletResponse response = ThreadContextHolder.getHttpResponse();
                try {
                    response.sendRedirect("login.html?forward=" + forward);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

        this.action = request.getParameter("action");

        String html = show(params);
        return html;
    }

    public void update(Map<String, String> params)
    {
    }

    public boolean cacheAble()
    {
        return true;
    }

    private void putRequestParam(String reqparams, Map<String, String> params)
    {
        if (StringUtils.isNotEmpty(reqparams)) {
            HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
            String[] reqparamArray = StringUtils.split(reqparams, ",");
            for (String paramname : reqparamArray) {
                String value = httpRequest.getParameter(paramname);
                params.put(paramname, value);
            }
        }
    }

    private String show(Map<String, String> params)
    {
        this.freeMarkerParser = FreeMarkerParser.getInstance();
        this.freeMarkerParser.setClz(getClass());
        this.freeMarkerParser.setPageFolder(null);
        this.freeMarkerParser.setPageName(null);

        String reqparams = (String)params.get("reqparams");
        putRequestParam(reqparams, params);

        this.freeMarkerParser.putData(params);

        String customPage = (String)params.get("custom_page");
        this.folder = ((String)params.get("folder"));

        String showHtmlStr = (String)params.get("showhtml");
        this.showHtml = true;

        this.disableCustomPage = false;
        display(params);

        if (!this.disableCustomPage){
            if (StringUtils.isNotEmpty(customPage)) {
                this.pageName = customPage;
            }

            if ("yes".equals(params.get("actionpage"))) {
                if (StringUtils.isNotEmpty(this.action)) {
                    this.pageName = (customPage + "_" + this.action);
                }
            }
            else if (StringUtils.isNotEmpty(this.action)) {
                String actionPage = (String)params.get("action_" + this.action);
                if (StringUtils.isEmpty(actionPage)) {
                    actionPage = (String)this.actionPageMap.get(this.action);
                }
                if (StringUtils.isNotEmpty(actionPage)) {
                    this.pageName = actionPage;
                }

            }

            if (StringUtils.isNotEmpty(this.pageName)) {
                this.freeMarkerParser.setPageName(this.pageName);
            }

            if (StringUtils.isNotEmpty(this.folder)) {
                Site site = EsfContext.getContext().getCurrentSite();
                String contextPath = EsfContext.getContext().getContextPath();
                 this.freeMarkerParser.setPageFolder(contextPath + "/themes/" + site.getThemepath() + "/" + this.folder);
            }
            else if (StringUtils.isNotEmpty(customPage)) {
                Site site = EsfContext.getContext().getCurrentSite();
                String contextPath = EsfContext.getContext().getContextPath();
                this.freeMarkerParser.setPageFolder(contextPath + "/themes/" + site.getThemepath());
            }

        }

        if ((StringUtils.isNotEmpty(showHtmlStr)) && (showHtmlStr.equals("false"))) {
            this.showHtml = false;
        }

        if ((this.showHtml) || ("yes".equals(params.get("ischild"))))
        {
            String html = this.freeMarkerParser.proessPageContent();
            if ("yes".equals(params.get("ischild"))) {
                putData("widget_" + (String)params.get("widgetid"), html);
            }
            return html;
        }
        return "";
    }

    protected String getThemePath()
    {
        Site site = EsfContext.getContext().getCurrentSite();
        String contextPath = EsfContext.getContext().getContextPath();
        return contextPath + "/themes/" + site.getThemepath();
    }

    protected void disableCustomPage()
    {
        this.disableCustomPage = true;
    }
    protected void enableCustomPage() {
        this.disableCustomPage = false;
    }

    public String setting(Map<String, String> params) {
        this.freeMarkerParser = FreeMarkerParser.getInstance();
        this.freeMarkerParser.setClz(getClass());
        config(params);

        if (this.showHtml) {
            return this.freeMarkerParser.proessPageContent();
        }
        return "";
    }

    protected abstract void display(Map<String, String> paramMap);

    protected abstract void config(Map<String, String> paramMap);

    protected void putData(String key, Object value)
    {
        this.freeMarkerParser.putData(key, value);
    }

    protected void putData(Map<String, Object> map)
    {
        this.freeMarkerParser.putData(map);
    }

    protected Object getData(String key)
    {
        return this.freeMarkerParser.getData(key);
    }

    protected void setPathPrefix(String path)
    {
        this.freeMarkerParser.setPathPrefix(path);
    }

    public void setPageName(String pageName)
    {
        this.disableCustomPage = false;
        this.pageName = pageName;
    }

    public void setActionPageName(String pageName)
    {
        this.disableCustomPage = false;
        this.actionPageMap.put(this.action, pageName);
    }

    public void makeSureSetPageName(String pageName)
    {
        this.freeMarkerParser.setPageName(pageName);
    }

    public void setPageExt(String pageExt)
    {
        this.freeMarkerParser.setPageExt(pageExt);
    }

    public void setPageFolder(String pageFolder) {
        this.freeMarkerParser.setPageFolder(pageFolder);
    }

    protected void putNav(Nav nav)
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        List navList = (List)request.getAttribute("site_nav_list");
        navList = navList == null ? new ArrayList() : navList;
        navList.add(nav);
        request.setAttribute("site_nav_list", navList);
    }

    protected void setMsg(String msg)
    {
        putData("msg", msg);
    }

    protected void putUrl(String text, String url)
    {
        if (this.urls == null) {
            this.urls = new HashMap();
        }
        this.urls.put(text, url);
        putData("urls", this.urls);
        putData("jumpurl", url);
    }

    protected void showError(String msg)
    {
        disableCustomPage();
        setPageFolder(getThemePath());
        this.freeMarkerParser.setPageName("error");
        setMsg(msg);
    }

    protected void showJson(String json)
    {
        disableCustomPage();
        setPageFolder("/commons/");
        this.freeMarkerParser.setPageName("json");
        putData("json", json);
    }

    protected void showErrorJson(String message)
    {
        showJson("{result:0,message:'" + message + "'}");
    }

    protected void showSuccessJson(String message) {
        showJson("{result:1,message:'" + message + "'}");
    }

    protected void showError(String msg, String urlText, String url)
    {
        disableCustomPage();
        this.pageName = null;
        setPageFolder(getThemePath());
        this.freeMarkerParser.setPageName("error");
        setMsg(msg);
        if ((urlText != null) && (url != null))
            putUrl(urlText, url);
    }

    protected void showSuccess(String msg)
    {
        disableCustomPage();
        this.pageName = null;
        setPageFolder(getThemePath());
        this.freeMarkerParser.setPageName("success");
        setMsg(msg);
    }

    protected void showSuccess(String msg, String urlText, String url)
    {
        disableCustomPage();
        setPageFolder(getThemePath());
        this.freeMarkerParser.setPageName("success");
        setMsg(msg);
        if ((urlText != null) && (url != null))
            putUrl(urlText, url);
    }

    protected void putData2(String key, Object value)
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        Object params_temp = request.getAttribute("eop_page_params");

        Map pageParams = null;
        if (params_temp == null)
            pageParams = new HashMap();
        else {
            pageParams = (Map)params_temp;
        }
        pageParams.put(key, value);
        request.setAttribute("eop_page_params", pageParams);
    }

    protected int getPageNo()
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        String page = request.getParameter("page");
        return StringUtil.toInt(page, 1);
    }
}