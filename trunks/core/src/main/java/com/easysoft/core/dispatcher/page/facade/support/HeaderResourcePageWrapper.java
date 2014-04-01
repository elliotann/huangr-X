package com.easysoft.core.dispatcher.page.facade.support;

import com.easysoft.core.context.EsfContext;
import com.easysoft.core.dispatcher.PageWrapper;
import com.easysoft.core.dispatcher.page.IPageParser;
import com.easysoft.core.model.Site;
import com.easysoft.framework.ParamSetting;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.core.resource.impl.ResourceBuilder;
import org.apache.commons.lang.StringUtils;
import org.mozilla.javascript.EvaluatorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * User: andy
 * Time: 上午10:22
 *
 * @since:
 */
public class HeaderResourcePageWrapper extends PageWrapper
{
    public static String THE_SSO_SCRIPT = "";

    public HeaderResourcePageWrapper(IPageParser parser)
    {
        super(parser);
    }

    public String parse(String url)
    {
        HttpServletRequest request = ThreadContextHolder.getHttpRequest();
        String content = super.parse(url);
        String pageid = (String)request.getAttribute("pageid");
        String tplFileName = (String)request.getAttribute("tplFileName");

        if ((StringUtils.isEmpty(pageid)) || (StringUtils.isEmpty(tplFileName))) {
            return content;
        }

        try
        {
            ResourceBuilder.reCreate(pageid, tplFileName);
        } catch (EvaluatorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer headerhtml = new StringBuffer();
        EsfContext ectx = EsfContext.getContext();
        Site site = ectx.getCurrentSite();

        String resdomain = ectx.getResDomain();

        String scriptpath = "";
        String csspath = "";

        if (!ParamSetting.DEVELOPMENT_MODEL) {
            if ("2".equals(ParamSetting.RESOURCEMODE))
            {
                resdomain = resdomain + "/themes/" + site.getThemepath();
                scriptpath = resdomain + "/js/headerresource?type=javascript&id=" + pageid;
                csspath = resdomain + "/css/headerresource?type=css&id=" + pageid;
                headerhtml.append("<script src=\"" + scriptpath + "\" type=\"text/javascript\"></script>");
                headerhtml.append("<link href=\"" + csspath + "\" rel=\"stylesheet\" type=\"text/css\" />");
            }
            else
            {
                if (ResourceBuilder.haveCommonScript()) {
                    String commonjs = resdomain + "/js/" + site.getThemepath() + "_common.js";
                    headerhtml.append("<script src=\"" + commonjs + "\" type=\"text/javascript\"></script>");
                }

                if (ResourceBuilder.haveCommonCss()) {
                    String commoncss = resdomain + "/css/" + site.getThemepath() + "_common.css";
                    headerhtml.append("<link href=\"" + commoncss + "\" rel=\"stylesheet\" type=\"text/css\" />");
                }

                if (ResourceBuilder.haveScript()) {
                    scriptpath = resdomain + "/js/" + site.getThemepath() + "_" + pageid + ".js";
                    headerhtml.append("<script src=\"" + scriptpath + "\" type=\"text/javascript\"></script>");
                }

                if (ResourceBuilder.haveCss()) {
                    csspath = resdomain + "/css/" + site.getThemepath() + "_" + tplFileName + ".css";
                    headerhtml.append("<link href=\"" + csspath + "\" rel=\"stylesheet\" type=\"text/css\" />");
                }

            }

        }

        headerhtml.append(ResourceBuilder.getNotMergeResource());

        content = content.replaceAll("#headerscript#", headerhtml.toString());

        if ("y".equals(request.getParameter("cpr"))) {
            content = content + THE_SSO_SCRIPT;
        }

        return content;
    }
}
