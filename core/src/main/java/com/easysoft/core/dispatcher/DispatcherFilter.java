package com.easysoft.core.dispatcher;

import com.easysoft.core.ParamSetting;
import com.easysoft.core.context.EsfContext;
import com.easysoft.core.context.JeapContextIniter;
import com.easysoft.core.dispatcher.core.Response;
import com.easysoft.core.dispatcher.core.freemarker.FreeMarkerParser;
import com.easysoft.core.dispatcher.httpcache.HttpCacheManager;
import com.easysoft.core.dispatcher.processor.Processor;
import com.easysoft.core.dispatcher.processor.factory.ProcessorFactory;
import com.easysoft.core.utils.JspUtil;
import com.easysoft.framework.context.webcontext.ThreadContextHolder;
import com.easysoft.core.freemarker.utils.FreeMarkerUtil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统中心filter
 * @author andy
 */
public class DispatcherFilter implements Filter {

    public void init(FilterConfig config)
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException{

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String uri = httpRequest.getServletPath();
        try{
        	//静态资源
            if (uri.startsWith("/statics")) {
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
        
          
            JeapContextIniter.init(httpRequest, httpResponse);
           
            Processor processor = ProcessorFactory.newProcessorInstance(uri, httpRequest);

            if (processor == null) {
                chain.doFilter(request, response);
            }
            else
            {
                if ((uri.equals("/")) || (uri.endsWith(".html"))) {
                    boolean result = HttpCacheManager.getIsCached(uri);
                    if (result) return;
                }
                Response eapResponse = processor.process(0, httpResponse, httpRequest);

                InputStream in = eapResponse.getInputStream();

                if (in != null)
                {
                    byte[] inbytes = IOUtils.toByteArray(in);
                    httpResponse.setContentType(eapResponse.getContentType());
                    httpResponse.setCharacterEncoding("UTF-8");
                    httpResponse.setHeader("Content-Length", "" + inbytes.length);

                    OutputStream output = httpResponse.getOutputStream();
                    IOUtils.write(inbytes, output);
                } else {
                    chain.doFilter(request, response);
                }
            }

        }
        catch (RuntimeException exception)
        {
            exception.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            request.setAttribute("message", exception.getMessage());
            String content = JspUtil.getJspOutput("/commons/error.jsp", httpRequest, httpResponse);

            response.getWriter().print(content);
        }
        finally
        {
            ThreadContextHolder.remove();
            FreeMarkerParser.remove();
            EsfContext.remove();
        }
    }

    public void destroy() {
    }
    public String get404Html(String url) {
        String themeFld = ParamSetting.ESF_PATH + "/themes/";
        Map<String,String> data = new HashMap<String,String>();
        data.put("url", url);
        try
        {
            Configuration cfg = FreeMarkerUtil.getFolderCfg(themeFld);
            Template temp = cfg.getTemplate("404.html");
            ByteOutputStream stream = new ByteOutputStream();

            Writer out = new OutputStreamWriter(stream);
            temp.process(data, out);

            out.flush();
            String html = stream.toString();
            return html;
        }
        catch (Exception e) {
            e.printStackTrace();
        }return "";
    }
}